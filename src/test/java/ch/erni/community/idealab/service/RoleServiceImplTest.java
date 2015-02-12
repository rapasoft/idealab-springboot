package ch.erni.community.idealab.service;

import ch.erni.community.idealab.model.Role;
import ch.erni.community.idealab.repository.RoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceImplTest {

	@Mock
	private RoleRepository roleRepository;

	private RoleServiceImpl roleService;

	@Before
	public void init() {
		roleService = new RoleServiceImpl();
		roleService.roleRepository = roleRepository;
	}

	@Test
	public void shouldSaveNewRole() {
		final Role savedRole = stubRepositoryToReturnRoleOnSave();
		final Role Role = new Role();
		final Role returnedRole = roleService.save(Role);
		// verify repository was called with Role
		verify(roleRepository, times(1)).save(Role);
		assertEquals("Returned Role should come from the repository", savedRole, returnedRole);
	}

	private Role stubRepositoryToReturnRoleOnSave() {
		Role Role = new Role();
		when(roleRepository.save(any(Role.class))).thenReturn(Role);
		return Role;
	}

}