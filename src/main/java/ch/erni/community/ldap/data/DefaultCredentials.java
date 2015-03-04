package ch.erni.community.ldap.data;

import ch.erni.community.ldap.exception.CredentialsFileNotFoundException;
import ch.erni.community.ldap.exception.CredentialsNotFoundException;

import java.util.Properties;

/**
 * @author rap
 */
public class DefaultCredentials {

	public static final String LDAP_USER = "ldap.user";

	public static final String LDAP_PASSWORD = "ldap.password";

	public static final String LDAP_DOMAIN_SHORTNAME = "ldap.domainShortName";

	public static final String SECURITY_PROPERTIES = "/security.properties";

	private String user;

	private String password;

	private String domainShortName;

	public DefaultCredentials() throws CredentialsFileNotFoundException, CredentialsNotFoundException {
		this(SECURITY_PROPERTIES);
	}

	DefaultCredentials(String propertyPath) throws CredentialsFileNotFoundException, CredentialsNotFoundException {
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getResourceAsStream(propertyPath));
		} catch (Exception e) {
			throw new CredentialsFileNotFoundException();
		}
		user = (String) properties.get(LDAP_USER);
		password = (String) properties.get(LDAP_PASSWORD);
		domainShortName = (String) properties.get(LDAP_DOMAIN_SHORTNAME);

		if (user == null || password == null || domainShortName == null) {
			throw new CredentialsNotFoundException();
		}
	}

	public Credentials getCredentials() {
		return new Credentials(user, password);
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getDomainShortName() {
		return domainShortName;
	}
}
