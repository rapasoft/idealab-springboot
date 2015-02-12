package ch.erni.community.idealab.controller;

/**
 * @author rap
 */

import ch.erni.community.idealab.model.Role;
import ch.erni.community.idealab.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleService roleService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Role createRole(@RequestBody @Valid Role role) {
		return roleService.save(role);
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public Role readRole(@RequestBody Integer id) {
		return roleService.fetch(id);
	}

	@RequestMapping(value = "/read_all", method = RequestMethod.GET)
	public List<Role> readRoles() {
		return roleService.fetchAllRoles();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String deleteRole(@PathVariable Integer id) {
		roleService.deleteRole(id);
		return "OK";
	}
}
