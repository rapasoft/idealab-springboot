package ch.erni.community.ldap.exception;

import ch.erni.community.ldap.data.DefaultCredentials;

/**
 * @author rap
 */
public class CredentialsFileNotFoundException extends Exception {

	static final String EXPLANATION = "Please create `security.properties` file in resources directory and enter mentioned properties.\n" +
			"For username please use full DN name, like `CN=<SecondName FirstName>,OU=Employees,OU=User,OU=ERNI Consulting,DC=erni2,DC=ch`.\n" +
			"Additionally for unit tests please also define `" + DefaultCredentials.LDAP_DOMAIN_SHORTNAME + "` property. It is your domain short name, like `rap`.";

	public CredentialsFileNotFoundException() {
		super("In order to connect you must create a `security.properties` file with `" + DefaultCredentials.LDAP_USER + "` and `" + DefaultCredentials.LDAP_PASSWORD + "` properties defined.\n" + EXPLANATION);
	}
}
