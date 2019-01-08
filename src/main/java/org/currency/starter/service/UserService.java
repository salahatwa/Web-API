package org.currency.starter.service;

import java.util.Optional;

import org.currency.starter.domain.User;
import org.currency.starter.dto.UserDTO;
import org.currency.starter.exception.GeneralException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface UserService extends org.springframework.security.core.userdetails.UserDetailsService {

	static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	User update(User user, UserDTO params);

	Optional<Optional<User>> findUser(Long id);
	
//	public Optional<User> whoami(HttpServletRequest req);

	public User createUser(User user) throws GeneralException;

	User save(User user);

	Optional<User> findByUsername(String string);

	Optional<User> findByUsernameAndPassword(Optional<String> username, String password);


}