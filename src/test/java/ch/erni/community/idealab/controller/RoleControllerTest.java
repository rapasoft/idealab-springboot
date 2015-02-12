package ch.erni.community.idealab.controller;

import ch.erni.community.idealab.model.Role;
import ch.erni.community.idealab.service.RoleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoleControllerTest {

	@Mock
	private RoleService roleService;

	private RoleController roleController;

	@Before
	public void setUp() {
		roleController = new RoleController();
		roleController.roleService = roleService;
	}

	@Test
	public void shouldCreateUser() throws Exception {
		final Role savedRole = stubServiceToReturnStoredUser();
		final Role role = new Role();
		Role returnedUser = roleController.createRole(role);
		// verify role was passed to RoleRepository
		verify(roleService, times(1)).save(role);
		assertEquals("Returned role should come from the service", savedRole, returnedUser);
	}

	private Role stubServiceToReturnStoredUser() {
		final Role role = new Role();
		when(roleService.save(any(Role.class))).thenReturn(role);
		return role;
	}

}