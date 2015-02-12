package ch.erni.community.idealab.repository;

import ch.erni.community.idealab.model.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rap
 */
public interface ArtifactRepository extends JpaRepository<Artifact, Integer> {
}
