package ch.erni.community.idealab.repository;

import ch.erni.community.idealab.exceptions.UserAlreadyVotedException;
import ch.erni.community.idealab.model.*;
import ch.erni.community.ldap.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author rap
 */
@Service
public class IdeaRepositoryImpl implements IdeaRepositoryCustom {

	@Autowired
	private IdeaRepository ideaRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void addVote(String ideaId, Vote vote) throws UserAlreadyVotedException {
		if (userAlreadyVoted(ideaId, vote.getUserEmail())) {
			throw new UserAlreadyVotedException();
		}

		Idea idea = ideaRepository.findOne(ideaId);
		idea.getVotes().add(vote);
		ideaRepository.save(idea);
	}

	@Override
	public void addComment(String ideaId, Comment comment) {
		Idea idea = ideaRepository.findOne(ideaId);
		comment.setCreated(new Date());
		idea.getComments().add(comment);
		ideaRepository.save(idea);
	}

	@Override
	public void saveValidateUser(Idea idea) throws UserNotFoundException {
		if (userRepository.findOne(idea.getOwnerEmail()) == null) {
			throw new UserNotFoundException("User with email address " + idea.getOwnerEmail() + "cannot be found in the database");
		}

		ideaRepository.save(idea);
	}

	@Override
	public boolean userAlreadyVoted(String ideaId, String userEmail) {
		QIdea qIdea = new QIdea("idea");
		QVote qVote = new QVote("vote");
		qVote.userEmail.eq(userEmail);

		Idea idea = ideaRepository.findOne(qIdea.votes.any().userEmail.eq(userEmail));

		return idea != null;
	}

}
