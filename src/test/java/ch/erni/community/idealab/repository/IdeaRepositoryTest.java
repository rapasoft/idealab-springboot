package ch.erni.community.idealab.repository;

import ch.erni.community.idealab.Application;
import ch.erni.community.idealab.configuration.OAuth2ServerConfiguration;
import ch.erni.community.idealab.configuration.WebMvcConfiguration;
import ch.erni.community.idealab.exceptions.UserAlreadyVotedException;
import ch.erni.community.idealab.model.Comment;
import ch.erni.community.idealab.model.Idea;
import ch.erni.community.idealab.model.Vote;
import ch.erni.community.ldap.exception.UserNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author rap
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Application.class, OAuth2ServerConfiguration.class, WebMvcConfiguration.class})
public class IdeaRepositoryTest {

	@Autowired
	private IdeaRepository ideaRepository;

	private Idea idea;

	@Before
	public void before() {
		createNewIdea();
	}

	@Test
	public void testCreateNewIdea() {
		assertNotNull(idea.getId());
	}

	@Test(expected = UserNotFoundException.class)
	public void testCreateNewIdeaWithUserValidation() throws UserNotFoundException {
		ideaRepository.saveValidateUser(idea);
	}

	@Test
	public void testAddComment() {
		Comment comment = Comment.builder().userEmail("pavol.rajzak@erni.sk").content("Test comment").build();

		ideaRepository.addComment(idea.getId(), comment);

		Idea saved = ideaRepository.findOne(idea.getId());

		assertEquals(1, saved.getComments().size());

		saved.getComments().forEach(savedComment -> {
			assertNotNull(savedComment.getCreated());
			assertNotNull(savedComment.getUserEmail());
			assertNotNull(savedComment.getContent());
		});
	}

	@Test
	public void testAddVote() throws UserAlreadyVotedException {
		Vote vote = Vote.builder().userEmail("pavol.rajzak@erni.sk").value(3).build();

		ideaRepository.addVote(idea.getId(), vote);

		Idea saved = ideaRepository.findOne(idea.getId());
		assertNotNull(saved.getVotes());
		saved.getVotes().forEach(
				savedVote -> {
					assertNotNull(vote.getUserEmail());
					assertNotNull(vote.getValue());
				}
		);
	}

	@Test(expected = UserAlreadyVotedException.class)
	public void testAddVoteAgain() throws UserAlreadyVotedException {
		Vote vote = Vote.builder().userEmail("pavol.rajzak@erni.sk").value(3).build();

		ideaRepository.addVote(idea.getId(), vote);
		ideaRepository.addVote(idea.getId(), vote);
	}

	private void createNewIdea() {
		idea = Idea.builder()
				.id("TEST_ID")
				.name("IT Idea")
				.description("Some description. It must be something bigger than 15 characters.")
				.ownerEmail("pavol.rajzak@erni.sk").build();
		ideaRepository.save(idea);
	}

	@After
	public void after() {
		ideaRepository.deleteAll();
	}

}
