package ch.erni.community.idealab.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rap
 */
@Entity
@Data
@NoArgsConstructor
public class Role {

	public static final String ID = "id";

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@ManyToMany(mappedBy = "roles")
	private List<User> users = new ArrayList<>();

	@ManyToMany(mappedBy = "permittedRoles")
	private List<State> states = new ArrayList<>();

}
