package ch.erni.community.idealab.generator;

import ch.erni.community.idealab.model.Idea;
import ch.erni.community.idealab.model.Role;
import ch.erni.community.idealab.model.User;
import com.mongodb.MongoTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;

/**
 * @author rap
 */
@Component
public class MongoDBDataGenerator {

	@Autowired
	private MongoTemplate mongoTemplate;

	public void generateSomeData() {
		try {
			mongoTemplate.getDb().dropDatabase();
		} catch (MongoTimeoutException m) {
			throw new IllegalStateException("Could not connect to mongoDB instance. Is it running? ", m);
		}

		User user = User.builder()
				.email("user")
				.name("user")
				.password("")
				.roles(Role.GRANTED_AUTHORITIES)
				.build();

		Idea idea = Idea.builder()
				.name("Test idea")
				.description("The plan to take over the world. Step 1: Say pretty please. Step 2: If step 1 does not work use weapons.")
				.created(new Date(System.currentTimeMillis()))
				.ownerEmail("user@erni.sk")
				.build();

		mongoTemplate.save(user);
		mongoTemplate.save(idea);
	}

}
