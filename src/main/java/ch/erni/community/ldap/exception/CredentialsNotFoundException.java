package ch.erni.community.ldap.exception;

import ch.erni.community.ldap.data.DefaultCredentials;

/**
 * @author rap
 */
public class CredentialsNotFoundException extends Exception {

	public CredentialsNotFoundException() {
		super("Properties `" + DefaultCredentials.LDAP_USER + "` and `" + DefaultCredentials.LDAP_PASSWORD + "` must be set in order to connect to ActiveDirectory.\n" +
				CredentialsFileNotFoundException.EXPLANATION);
	}

}
