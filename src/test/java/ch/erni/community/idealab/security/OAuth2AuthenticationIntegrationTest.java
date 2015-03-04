package ch.erni.community.idealab.security;

import ch.erni.community.idealab.Application;
import ch.erni.community.idealab.configuration.OAuth2ServerConfiguration;
import ch.erni.community.idealab.configuration.WebMvcConfiguration;
import ch.erni.community.idealab.generator.MongoDBDataGenerator;
import ch.erni.community.idealab.rest.RestUtil;
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

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author rap
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Application.class, OAuth2ServerConfiguration.class, WebMvcConfiguration.class})
public class OAuth2AuthenticationIntegrationTest {

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
	public void ideasUnauthorized() throws Exception {
		mvc.perform(get("/ideas")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.error", is("unauthorized")));
	}

	@Test
	public void ideasAuthorized() throws Exception {
		String accessToken = RestUtil.authenticateAndGetAccessToken(mvc);

		mvc.perform(get("/ideas")
				.header("Authorization", "Bearer " + accessToken))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$._embedded.ideas[0].name", is("Test idea")));
	}


}
