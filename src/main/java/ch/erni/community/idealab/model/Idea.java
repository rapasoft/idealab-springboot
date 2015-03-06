package ch.erni.community.idealab.model;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author rap
 */
@Getter
@Setter
@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Idea {

	@Id
	private String id;

	@Size(min = 6)
	private String name;

	@Size(min = 15)
	private String description;

	@Email
	@Indexed
	private String ownerEmail;

	@CreatedDate
	private Date created;

	@LastModifiedDate
	private Date modified;

	private Set<Vote> votes = new HashSet<>();

	private Set<Comment> comments = new HashSet<>();

}
