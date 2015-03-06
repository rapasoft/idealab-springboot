package ch.erni.community.idealab.service;

import ch.erni.community.idealab.model.Role;
import ch.erni.community.idealab.model.User;
import ch.erni.community.ldap.data.UserDetails;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ERNILdapServiceTest {

	private ERNILdapService erniLdapService;

	@Before
	public void before() {
		erniLdapService = new ERNILdapService();
	}

	@Test
	public void testFindByShortName() throws Exception {
		User rap = erniLdapService.findByShortName("rap");
		assertNotNull(rap);

		assertEquals("Pavol Rajzák", rap.getName());
		assertEquals("pavol.rajzak@erni.sk", rap.getEmail().toLowerCase());
		assertEquals(true, rap.isAccountNonExpired());
		assertEquals(true, rap.isAccountNonLocked());
		assertEquals(true, rap.isCredentialsNonExpired());
		assertEquals(true, rap.isEnabled());
		assertEquals(Role.GRANTED_AUTHORITIES, rap.getAuthorities());

		rap.getAuthorities().forEach(role -> {
			assertNotNull(role.getAuthority());
		});
	}

	@Test
	public void testGetEmployees() throws Exception {
		List<UserDetails> userDetailsList = erniLdapService.getEmployees();

		assertNotNull(userDetailsList);
		assertTrue(!userDetailsList.isEmpty());

		// Let's assume there will always be at least 100 employees ;)
		assertTrue(userDetailsList.size() > 100);

		userDetailsList.forEach(
				userDetails -> assertNotNull(userDetails.getDomainUserName())
		);
	}
}