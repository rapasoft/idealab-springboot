package ch.erni.community.idealab.repository;

import ch.erni.community.idealab.model.Idea;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rap
 */
public interface IdeaRepository extends JpaRepository<Idea, Integer> {
}
