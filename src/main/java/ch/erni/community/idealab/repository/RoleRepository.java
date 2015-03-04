package ch.erni.community.idealab.repository;

import ch.erni.community.idealab.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author rap
 */
public interface RoleRepository extends MongoRepository<Role, Integer> {

}
