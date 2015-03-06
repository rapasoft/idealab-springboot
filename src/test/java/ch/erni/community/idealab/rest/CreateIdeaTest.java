package ch.erni.community.idealab.rest;

import ch.erni.community.idealab.Application;
import ch.erni.community.idealab.configuration.OAuth2ServerConfiguration;
import ch.erni.community.idealab.configuration.WebMvcConfiguration;
import ch.erni.community.idealab.generator.MongoDBDataGenerator;
import ch.erni.community.idealab.model.Idea;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author rap
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Application.class, OAuth2ServerConfiguration.class, WebMvcConfiguration.class})
public class CreateIdeaTest {

	@Autowired
	WebApplicationContext context;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	@Autowired
	private MongoDBDataGenerator mongoDBDataGenerator;

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mongoDBDataGenerator.generateSomeData();
		mvc = MockMvcBuilders.webAppContextSetup(context)
				.addFilter(springSecurityFilterChain)
				.build();
	}

	@Test
	public void createNewIdea() throws Exception {
		String accessToken = RestUtil.authenticateAndGetAccessToken(mvc);

		mvc.perform(post("/ideas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(testIdea())
				.header("Authorization", "Bearer " + accessToken))
				.andExpect(status().isCreated());
	}

	private String testIdea() throws JsonProcessingException {
		Idea idea = Idea.builder()
				.name("IT Idea")
				.description("Some description. It must be something bigger than 15 characters.")
				.ownerEmail("pavol.rajzak@erni.sk").build();
		Gson gson = new Gson();
		return gson.toJson(idea);
	}

}
