package ch.erni.community.idealab.service;

import ch.erni.community.idealab.model.Role;
import ch.erni.community.idealab.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rap
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	@Transactional
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public List<Role> fetchAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public void deleteRole(Integer id) {
		roleRepository.delete(id);
	}

	@Override
	public Role fetch(Integer id) {
		return roleRepository.findOne(id);
	}
}
