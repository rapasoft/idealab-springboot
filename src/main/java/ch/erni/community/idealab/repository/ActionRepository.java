package ch.erni.community.idealab.repository;

import ch.erni.community.idealab.model.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rap
 */
public interface ActionRepository extends JpaRepository<Artifact, Integer> {
}
