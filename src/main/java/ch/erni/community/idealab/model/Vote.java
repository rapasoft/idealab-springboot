package ch.erni.community.idealab.model;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author rap
 */
@Getter
@Builder
@Document
public class Vote {

	@Email
	private String userEmail;

	@Min(0)
	@Max(5)
	private Integer value;

}
