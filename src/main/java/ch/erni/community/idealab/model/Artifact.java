package ch.erni.community.idealab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author rap
 */
@Entity
@Data
public class Artifact {

	@Id
	@GeneratedValue
	private Integer id;

	private String title;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	@Lob
	private String content;

}
