package ch.erni.community.idealab.service;

import ch.erni.community.idealab.model.User;
import ch.erni.community.idealab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author rap
 */
@Service
public class SimpleUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findOneByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("Could not find user with email: " + email);
		}

		return user;
	}

}
