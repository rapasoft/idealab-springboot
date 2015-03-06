package ch.erni.community.idealab.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author rap
 */
@Getter
@Builder
@Document
public class Comment {

	@Email
	@Indexed
	private String userEmail;

	private String content;

	@Setter
	private Date created;

}
