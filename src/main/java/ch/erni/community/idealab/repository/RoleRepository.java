package ch.erni.community.idealab.repository;

import ch.erni.community.idealab.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author rap
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
