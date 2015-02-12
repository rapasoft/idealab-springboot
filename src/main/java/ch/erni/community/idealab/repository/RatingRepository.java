package ch.erni.community.idealab.repository;

import ch.erni.community.idealab.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rap
 */
public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
