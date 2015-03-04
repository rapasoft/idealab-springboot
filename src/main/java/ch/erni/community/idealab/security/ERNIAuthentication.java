package ch.erni.community.idealab.security;

import ch.erni.community.idealab.enums.SecurityRoles;
import ch.erni.community.idealab.model.Role;
import ch.erni.community.ldap.data.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

/**
 * @author rap
 */
public class ERNIAuthentication implements Authentication {

	private final UserDetails userDetails;

	private String credentials;

	private boolean authenticated;

	public ERNIAuthentication(UserDetails userDetails, String credentials, boolean authenticated) {
		this.userDetails = userDetails;
		this.credentials = credentials;
		setAuthenticated(authenticated);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(Role.builder().name(SecurityRoles.USER.asSpringRole()).build());
	}

	@Override
	public String getCredentials() {
		return credentials;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return userDetails;
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
		this.authenticated = authenticated;
	}

	@Override
	public String getName() {
		return userDetails.getFirstName() + " " + userDetails.getSecondName();
	}

}
