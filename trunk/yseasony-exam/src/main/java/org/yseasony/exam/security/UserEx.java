package org.yseasony.exam.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserEx extends User {

	private Set<String> authButtons = new HashSet<String>();

	private static final long serialVersionUID = -5187709601670613640L;

	public UserEx(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
	}

	public HashMap<String,Boolean> getAuthButtons() {
		HashMap<String,Boolean> authButtons = new HashMap<String,Boolean>();
		Iterator<String> it = this.authButtons.iterator();
		while (it.hasNext()) {
			String s = it.next();
			if (s.indexOf("_") != -1) {
				String[] values = s.split("_");
				for (String v : values) {
					authButtons.put(v, false);
				}
			}
		}
		return authButtons;
	}

	public void setAuthButtons(Set<String> authButtons) {
		this.authButtons = authButtons;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return super.getPassword();
	}

}
