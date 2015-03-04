package ch.erni.community.ldap.data;

import ch.erni.community.ldap.exception.CredentialsFileNotFoundException;
import ch.erni.community.ldap.exception.CredentialsNotFoundException;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author rap
 */
public class SecurityPropertiesTest {

	@Test(expected = CredentialsFileNotFoundException.class)
	public void testFailOnMissingPropertiesFile() throws Exception {
		DefaultCredentials defaultCredentials = new DefaultCredentials("/missing.properties");

		defaultCredentials.getCredentials();
	}

	@Test(expected = CredentialsNotFoundException.class)
	public void testFailOnEmptyPropertiesFile() throws Exception {
		DefaultCredentials defaultCredentials = new DefaultCredentials("/empty.properties");

		defaultCredentials.getCredentials();
	}

	@Test
	public void testSecurityPropertiesFilePresent() throws Exception {
		DefaultCredentials defaultCredentials = new DefaultCredentials();

		assertNotNull(defaultCredentials.getCredentials());

		assertNotNull(defaultCredentials.getDomainShortName());
		assertNotNull(defaultCredentials.getPassword());
		assertNotNull(defaultCredentials.getUser());
	}

}
