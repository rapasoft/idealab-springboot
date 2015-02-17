package ch.erni.community.idealab.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

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
@Getter
@Setter
@NoArgsConstructor
public class Role implements GrantedAuthority {

	public static final Role GUEST = new GuestRole();

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@ManyToMany(mappedBy = "roles")
	private List<User> users = new ArrayList<>();

	@ManyToMany(mappedBy = "permittedRoles")
	private List<State> states = new ArrayList<>();

	@Override
	public String getAuthority() {
		return name;
	}

	private static class GuestRole extends Role {
		private final String name = "GUEST";
	}
}
