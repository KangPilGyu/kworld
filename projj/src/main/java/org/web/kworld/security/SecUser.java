package org.web.kworld.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SecUser extends User{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 7119527134548448336L;
	
	private int m_id;
	private String m_name;
	
	public SecUser(String username, String password, boolean enabled, int m_id, String m_name, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.m_id=m_id;
		this.m_name=m_name;
	}

	public int getM_id() {
		return m_id;
	}

	public String getM_name() {
		return m_name;
	}
	
}
