package ch.erni.community.idealab.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author rap
 */
@Entity
@Getter
@Setter
public class State {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@ManyToMany
	@JoinTable(name = "STATE_ROLE")
	private List<Role> permittedRoles;

	@OneToMany
	@JoinColumn(name = "STATE_ID")
	private List<Action> permittedActions;

}
