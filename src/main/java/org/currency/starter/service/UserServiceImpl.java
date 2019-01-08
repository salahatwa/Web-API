package org.currency.starter.service;

import java.util.Arrays;
import java.util.Optional;

import org.currency.starter.domain.Authority;
import org.currency.starter.domain.AuthorityName;
import org.currency.starter.domain.User;
import org.currency.starter.dto.UserDTO;
import org.currency.starter.exception.GeneralException;
import org.currency.starter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException(String.format("No user found with username '%s'.", username)));
		return user;
	}

	/*
	 * @Override public Optional<User> whoami(HttpServletRequest req) { return
	 * userRepository
	 * .findByUsername(jwtTokenHandler.getUsername(tokenAuthenticationService.
	 * getTokenFromRequest(req))); }
	 */

	@Override
	public User update(User user, UserDTO params) {
		params.getEmail().ifPresent(user::setEmail);
		params.getEncodedPassword().ifPresent(user::setPassword);
		params.getUsername().ifPresent(user::setUsername);
		return userRepository.save(user);
	}

	@Override
	public Optional<Optional<User>> findUser(Long id) {
		return Optional.of(userRepository.findById(id));

	}

	@Override
	public User createUser(User user) throws GeneralException {
		Authority auth = new Authority();
		auth.setName(AuthorityName.ROLE_USER);
		user.setAuthorities(Arrays.asList(auth));
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user = userRepository.save(user);
		return user;
	}


	@Override
	public User save(User user) {
		user=userRepository.save(user);
		return user;
	}

	@Override
	public Optional<User> findByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		return user;
	}

	@Override
	public Optional<User> findByUsernameAndPassword(Optional<String> username, String password) {
		Optional<User> user=userRepository.findByUsernameAndPassword(username,password);
		return user;
	}
}
