package org.yseasony.acg.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserEx extends User {

	private Set<String> authButtons = new HashSet<String>();
	
	private static final long serialVersionUID = -5187709601670613640L;

	public UserEx(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}

	public Set<String> getAuthButtons() {
		return authButtons;
	}

	public void setAuthButtons(Set<String> authButtons) {
		this.authButtons = authButtons;
	}

}
