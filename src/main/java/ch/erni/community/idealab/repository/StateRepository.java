package ch.erni.community.idealab.repository;

import ch.erni.community.idealab.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rap
 */
public interface StateRepository extends JpaRepository<State, Integer> {
}
