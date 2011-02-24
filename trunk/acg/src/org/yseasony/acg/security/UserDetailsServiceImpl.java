package org.yseasony.acg.security;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.yseasony.acg.entity.Authority;
import org.yseasony.acg.entity.Role;
import org.yseasony.acg.entity.User;
import org.yseasony.acg.services.UserSvcImpl;

import com.google.common.collect.Sets;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserSvcImpl userSvcImpl;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		User user = userSvcImpl.findUserByLoginName(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户" + username + " 不存在");
		}

		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		UserDetails userdetails = new org.springframework.security.core.userdetails.User(
				user.getLoginName(), user.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				grantedAuths);

		return userdetails;
	}

	private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		if (user.getRoleList() != null) {
			for (Role role : user.getRoleList()) {
				for (Authority authority : role.getAuthorityList()) {
					authSet.add(new GrantedAuthorityImpl(authority
							.getPrefixedName()));
				}
			}
		}
		return authSet;
	}

}
