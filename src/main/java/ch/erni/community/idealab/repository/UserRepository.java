package ch.erni.community.idealab.repository;

import ch.erni.community.idealab.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author rap
 */
public interface UserRepository extends MongoRepository<User, String> {

	User findOneByEmail(String email);

}
