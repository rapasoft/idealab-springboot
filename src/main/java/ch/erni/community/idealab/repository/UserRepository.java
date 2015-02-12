package ch.erni.community.idealab.repository;

import ch.erni.community.idealab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rap
 */
public interface UserRepository extends JpaRepository<User, String> {
}
