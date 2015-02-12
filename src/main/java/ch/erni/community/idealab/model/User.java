package ch.erni.community.idealab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author rap
 */
@Entity
@Data
public class User {

	@Id
	private String email;

	private String name;

	@ManyToMany
	@JoinTable(name = "USER_ROLE")
	private List<Role> roles;

	@OneToMany(mappedBy = "user")
	private List<Rating> ratings;

	@OneToMany(mappedBy = "owner")
	private List<Idea> ideas;

}