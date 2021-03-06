package ch.erni.community.idealab.security;

import ch.erni.community.ldap.LdapService;
import ch.erni.community.ldap.LdapServiceImpl;
import ch.erni.community.ldap.data.AuthenticationResult;
import ch.erni.community.ldap.exception.CredentialsFileNotFoundException;
import ch.erni.community.ldap.exception.CredentialsNotFoundException;
import ch.erni.community.ldap.exception.UserNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author rap
 */
@Component
public class ERNILdapAuthenticationProvider implements AuthenticationProvider {

	LdapService ldap;

	void createLdapConnection() throws CredentialsNotFoundException, CredentialsFileNotFoundException {
		ldap = new LdapServiceImpl();
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			createLdapConnection();

			AuthenticationResult authenticationResult = ldap.authenticate(authentication.getName(), authentication.getCredentials().toString());

			return new ERNIAuthentication(authenticationResult.getUserDetails(), authentication.getCredentials().toString(), authenticationResult.isAuthenticated());
		} catch (CredentialsNotFoundException e) {
			throw new BadCredentialsException("Could not find credentials information! " + e.getMessage());
		} catch (UserNotFoundException e) {
			throw new BadCredentialsException("Selected credentials are not valid! Either username or password is wrong. " + e.getMessage());
		} catch (CredentialsFileNotFoundException e) {
			throw new BadCredentialsException("System is not configured properly. " + e.getMessage());
		}

	}

	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}
}
