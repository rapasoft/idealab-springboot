package ch.erni.community.idealab.validator;

import ch.erni.community.idealab.Application;
import ch.erni.community.idealab.configuration.OAuth2ServerConfiguration;
import ch.erni.community.idealab.configuration.WebMvcConfiguration;
import ch.erni.community.idealab.model.Idea;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author rap
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Application.class, OAuth2ServerConfiguration.class, WebMvcConfiguration.class})
public class ValidationTest {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	public void shouldThrowValidationException() {
		Idea user = Idea.builder().name("short").description("even shorter").build();

		try {
			mongoTemplate.save(user);
			fail();
		} catch (ConstraintViolationException e) {
			assertEquals(2, e.getConstraintViolations().size());
		}
	}

}
