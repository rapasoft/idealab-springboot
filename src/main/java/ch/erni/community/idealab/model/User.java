package ch.erni.community.idealab.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author rap
 */
@Entity
@Getter
@Setter
public class User implements UserDetails {

	public static final User GUEST = new GuestUser();

	@Id
	private String email;

	@NotNull
	private String name;

	@NotNull
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLE")
	private List<Role> roles = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<Rating> ratings = new ArrayList<>();

	@OneToMany(mappedBy = "owner")
	private List<Idea> ideas = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Getter
	private static class GuestUser extends User {

		private final String email = "N/A";

		private final String name = "Guest user";

		private final List<Role> roles = Collections.singletonList(Role.GUEST);

	}
}