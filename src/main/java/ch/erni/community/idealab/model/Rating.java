package ch.erni.community.idealab.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author rap
 */
@Entity
@Getter
@Setter
public class Rating {

	@Id
	@GeneratedValue
	private Integer id;

	@Min(value = 1)
	@Max(value = 5)
	private Integer value;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

}
