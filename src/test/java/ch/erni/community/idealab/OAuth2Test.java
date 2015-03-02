package ch.erni.community.idealab;

import ch.erni.community.idealab.configuration.OAuth2ServerConfiguration;
import ch.erni.community.idealab.configuration.WebMvcConfiguration;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author rap
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Application.class, OAuth2ServerConfiguration.class, WebMvcConfiguration.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class OAuth2Test {

	@Autowired
	WebApplicationContext context;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
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
	@DatabaseSetup("/data/insert.xml")
	public void ideasAuthorized() throws Exception {
		String authorization = "Basic "
				+ new String(Base64Utils.encode("clientapp:123456".getBytes()));
		String contentType = MediaType.APPLICATION_JSON + ";charset=UTF-8";

		String content = mvc
				.perform(
						post("/oauth/token")
								.header("Authorization", authorization)
								.contentType(
										MediaType.APPLICATION_FORM_URLENCODED)
								.param("username", "user")
								.param("password", "")
								.param("grant_type", "password")
								.param("scope", "read write")
								.param("client_id", "clientapp")
								.param("client_secret", "123456"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.access_token", is(notNullValue())))
				.andExpect(jsonPath("$.token_type", is(equalTo("bearer"))))
				.andExpect(jsonPath("$.refresh_token", is(notNullValue())))
				.andExpect(jsonPath("$.expires_in", is(greaterThan(4000))))
				.andExpect(jsonPath("$.scope", is(equalTo("read write"))))
				.andReturn().getResponse().getContentAsString();


		String accessToken = content.substring(17, 53);

		mvc.perform(get("/ideas")
				.header("Authorization", "Bearer " + accessToken))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$._embedded.ideas[0].name", is("Take over the world")));
	}

}
