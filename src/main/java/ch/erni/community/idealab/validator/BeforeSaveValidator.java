package ch.erni.community.idealab.validator;

import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author rap
 */
@Component
public class BeforeSaveValidator extends AbstractMongoEventListener {

	@Autowired
	@Qualifier("localValidatorFactoryBean")
	private Validator validator;

	@Override
	public void onBeforeSave(Object source, DBObject dbo) {
		Set<ConstraintViolation<Object>> violations = validator.validate(source);

		if (violations.size() > 0) {
			throw new ConstraintViolationException(violations);
		}
	}
}