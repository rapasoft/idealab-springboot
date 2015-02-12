package ch.erni.community.idealab.service;

import ch.erni.community.idealab.model.Role;

import java.util.List;

/**
 * @author rap
 */
public interface RoleService {

	Role save(Role role);

	List<Role> fetchAllRoles();

	void deleteRole(Integer id);

	Role fetch(Integer id);
}
