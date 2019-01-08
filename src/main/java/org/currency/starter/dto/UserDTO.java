package org.currency.starter.dto;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import javax.validation.constraints.Size;

import org.currency.starter.domain.Authority;
import org.currency.starter.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDTO {

	private static Logger LOGGER = LoggerFactory.getLogger(UserDTO.class);

	private static final String ROLE_USER = "ROLE_USER";

	private String email;

	@Size(min = 8, max = 100)
	private String password;

	private String username;

	private Authority authority;


	private String newpassword;

	private String phone;
	private String state;
	private String city;
	private String address;
	private String website;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Optional<String> getUsername() {
		return Optional.ofNullable(username);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserDTO() {
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public Optional<String> getEmail() {
		return Optional.ofNullable(email);
	}

	public Optional<String> getEncodedPassword() {
		return Optional.ofNullable(password).map(p -> new BCryptPasswordEncoder().encode(p));
	}

	public User toUser(Authority authority) {
		User user = new User();
		user.setUsername(username);
		user.setAuthorities(Arrays.asList(authority));
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		user.setEmail(email);
		user.setPhone(phone);
		user.setState(state);
		user.setCity(city);
		user.setWebsite(website);
		user.setAddress(address);
		return user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UsernamePasswordAuthenticationToken toAuthenticationToken() {
		LOGGER.info("Login Using Email: {}", email);
		return new UsernamePasswordAuthenticationToken(email, password, getAuthorities());
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(() -> ROLE_USER);
	}


	@Override
	public String toString() {
		return username + ":" + email + ":" + password;
	}
}