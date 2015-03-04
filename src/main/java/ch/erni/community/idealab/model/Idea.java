package ch.erni.community.idealab.model;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.util.Date;

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
	private Integer id;

	@Size(min = 6)
	private String name;

	@Size(min = 15)
	private String description;

	@CreatedDate
	private Date created;

	@LastModifiedDate
	private Date modified;

	@Email
	private String ownerEmail;

}
