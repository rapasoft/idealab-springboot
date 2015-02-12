package ch.erni.community.idealab.repository;

import ch.erni.community.idealab.Application;
import ch.erni.community.idealab.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class UserRepositoryTest {

	private RestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testCreateNewUser() {
		User user = new User();
		user.setEmail("test@erni.ch");
		user.setName("Test user");

		ResponseEntity<User> result = restTemplate.postForEntity("http://localhost:8080/users", user, User.class);

		assertNotNull(result);
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

}