package ch.erni.community.idealab.repository;

import ch.erni.community.idealab.exceptions.UserAlreadyVotedException;
import ch.erni.community.idealab.model.Comment;
import ch.erni.community.idealab.model.Idea;
import ch.erni.community.idealab.model.Vote;
import ch.erni.community.ldap.exception.UserNotFoundException;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @author rap
 */
public interface IdeaRepositoryCustom {

	void addVote(String ideaId, Vote vote) throws UserAlreadyVotedException;

	void addComment(String ideaId, Comment comment);

	void saveValidateUser(Idea idea) throws UserNotFoundException;

	@RestResource(exported = false)
	boolean userAlreadyVoted(String ideaId, String userEmail);

}
