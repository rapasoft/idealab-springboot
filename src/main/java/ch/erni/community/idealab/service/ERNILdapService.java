package ch.erni.community.idealab.service;

import ch.erni.community.idealab.enums.SecurityRoles;
import ch.erni.community.idealab.model.Role;
import ch.erni.community.idealab.model.User;
import ch.erni.community.ldap.LdapService;
import ch.erni.community.ldap.LdapServiceImpl;
import ch.erni.community.ldap.data.UserDetails;
import ch.erni.community.ldap.exception.UserNotFoundException;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author rap
 */
@Service
public class ERNILdapService {

	private LdapService ldapService = new LdapServiceImpl();

	@Getter(lazy = true)
	private final List<UserDetails> employees = ldapService.fetchEmployees();

	public User findByShortName(String domainShortName) throws UserNotFoundException {
		return getEmployees()
				.stream()
				.filter(userDetails -> userDetails.getDomainUserName().equals(domainShortName))
				.map(userDetails ->
						User.builder()
								.email(userDetails.getEmail())
								.name(userDetails.getFullName())
								.roles(Collections.singletonList(Role.builder().name(SecurityRoles.USER.asSpringRole()).build()))
								.build())
				.findFirst()
				.orElseThrow(new UserNotFoundException("Could not find user with domain short name " + domainShortName));
	}
}
