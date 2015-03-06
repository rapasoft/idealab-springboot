package ch.erni.community.idealab.repository;

import ch.erni.community.idealab.model.Idea;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * @author rap
 */
public interface IdeaRepository extends MongoRepository<Idea, String>, IdeaRepositoryCustom, QueryDslPredicateExecutor<Idea> {

}
