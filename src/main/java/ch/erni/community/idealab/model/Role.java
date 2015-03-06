package ch.erni.community.idealab.model;

import ch.erni.community.idealab.enums.SecurityRoles;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.List;

/**
 * @author rap
 */
@Document
@Builder
public class Role implements GrantedAuthority {

	public static final List<Role> GRANTED_AUTHORITIES = Collections.singletonList(Role.builder().name(SecurityRoles.USER.asSpringRole()).build());

	@Id
	private String name;

	@Override
	public String getAuthority() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Role role = (Role) o;

		return !(name != null ? !name.equals(role.name) : role.name != null);

	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Role: " + name;
	}
}
