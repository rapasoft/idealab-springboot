package ch.erni.community.idealab.repository;

import ch.erni.community.idealab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author rap
 */
public interface UserRepository extends JpaRepository<User, String> {

	User findOneByEmail(String email);

}
