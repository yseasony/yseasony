package org.yseasony.exam.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.yseasony.exam.entity.Authority;
import org.yseasony.exam.entity.Role;
import org.yseasony.exam.entity.User;
import org.yseasony.exam.services.UserSvcImpl;

import com.google.common.collect.Sets;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserSvcImpl userSvcImpl;

	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		User user = userSvcImpl.findUserByLoginName(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户" + username + " 不存在");
		}

		Map<String, Set<?>> map = obtainGrantedAuthorities(user);
		Set<GrantedAuthority> grantedAuths = (Set<GrantedAuthority>) map
				.get("authSet");
		Set<String> authButtons = (Set<String>) map.get("authButtons");
		boolean enabled = user.getEnabled();
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		UserEx userEx = new UserEx(user.getLoginName(), user.getPassword(),
				enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, grantedAuths);
		userEx.setAuthButtons(authButtons);
		UserDetails userdetails = userEx;
		return userdetails;
	}

	private Map<String, Set<?>> obtainGrantedAuthorities(User user) {
		Map<String, Set<?>> map = new HashMap<String, Set<?>>(2);
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		Set<String> authButtons = Sets.newHashSet();
		if (user.getRoleList() != null) {
			for (Role role : user.getRoleList()) {
				for (Authority authority : role.getAuthorities()) {
					authSet.add(new GrantedAuthorityImpl(authority
							.getPrefixedName()));
					authButtons.add(authority.getButtonName());
				}
			}
		}
		map.put("authSet", authSet);
		map.put("authButtons", authButtons);
		return map;
	}

}
