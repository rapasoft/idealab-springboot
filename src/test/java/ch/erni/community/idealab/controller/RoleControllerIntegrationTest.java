package ch.erni.community.idealab.controller;

import ch.erni.community.idealab.Application;
import ch.erni.community.idealab.model.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author rap
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class RoleControllerIntegrationTest {

	RestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testCreateRole() {
		Role role = new Role();
		role.setName("RoleName");
		Role result = restTemplate.postForObject("http://localhost:8080/role/create", role, Role.class);

		assertNotNull(result.getId());
		assertEquals("RoleName", role.getName());
	}

	@Test
	public void testDeleteRole() {
		Role role = new Role();
		role.setName("RoleName");
		Role post = restTemplate.postForObject("http://localhost:8080/role/create", role, Role.class);

		restTemplate.delete("http://localhost:8080/role/delete/" + post.getId());

		Role deleted = restTemplate.getForObject("http://localhost:8080/role/read", Role.class, post.getId());

		assertNull(deleted.getName());
	}

}
