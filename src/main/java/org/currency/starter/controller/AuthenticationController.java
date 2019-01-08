package org.currency.starter.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.currency.starter.config.Config;
import org.currency.starter.config.JwtTokenUtil;
import org.currency.starter.domain.Authority;
import org.currency.starter.domain.AuthorityName;
import org.currency.starter.domain.User;
import org.currency.starter.dto.UserDTO;
import org.currency.starter.exception.ErrorMessage;
import org.currency.starter.exception.GeneralException;
import org.currency.starter.exception.NotValidOldPasswordException;
import org.currency.starter.service.AuthorityService;
import org.currency.starter.service.IAuthenticationFacade;
import org.currency.starter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = { "Authentication Controller" })
public class AuthenticationController {
	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;
	

	@Autowired
	private IAuthenticationFacade authenticationFacade;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private Config config;

	public AuthenticationController() {
		
	}

	@PostMapping(value = "${jwt.authentication-path}")
	public AuthenticationResponse login(@RequestBody AuthenticationRequest request, Device device) throws GeneralException{
		System.out.println(request.toString());
		// Perform the security
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Reload password post-security so we can generate token
		final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails, device);

		// Return the token
		return new AuthenticationResponse(token, AuthorityUtils.authorityListToSet(userDetails.getAuthorities()));
	}

	@PostMapping("${jwt.unauthentication.register}")
	public @ResponseBody  AuthenticationResponse registerUser(@RequestBody UserDTO userDTO, Device device) throws GeneralException{

		User user = new User();
		Assert.notNull(userDTO, "Parameter user can not be null!");
		Assert.hasLength(userDTO.getUsername().get(), "Username can not be empty!");
		Assert.hasLength(userDTO.getPassword(), "password can not be blank!");
		Assert.notNull(userDTO.getEmail(), "Parameter user can not be null!");

		boolean check = userService.findByUsername(userDTO.getUsername().get()).isPresent();

		if (check)
			throw new GeneralException("username already exists!");

		// check = userRepository.findByEmail(userDTO.getEmail().get()).isPresent();
		//
		// if (check)
		// throw new GeneralException("The mailbox has been registered!");

		Authority auth=new Authority();
		auth.setName(AuthorityName.ROLE_USER);
		
		auth= authorityService.saveAuthority(auth);
		user = userDTO.toUser(auth);

		Date now = Calendar.getInstance().getTime();

		user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

		user.setEnabled(true);
		user.setLastPasswordResetDate(now);
		user = userService.save(user);

		AuthenticationRequest request=new AuthenticationRequest();
		request.setUsername(user.getUsername());
		request.setPassword(userDTO.getPassword());
		
		AuthenticationResponse response=tologin(request, device);
		return response;
	}
	
	private AuthenticationResponse tologin(AuthenticationRequest request, Device device) throws GeneralException{
		// Perform the security
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
 
		// Reload password post-security so we can generate token
		final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails, device);

		// Return the token
		return new AuthenticationResponse(token, AuthorityUtils.authorityListToSet(userDetails.getAuthorities()));
	}

	@GetMapping(value = "${jwt.authentication-path}/detail")
	public User getUserDetails() throws GeneralException{
		Object user = authenticationFacade.getCurrentUser();

		if (user instanceof User)
		{
			LOG.info("CURRENT USER:{}",((User) user).getUsername());
			return (User) user;
		}
		else
		{
			LOG.error("No User Logged In");
			throw new GeneralException("User Not Logged in");
//			return null;
		}
	}
	
	@PutMapping(value = "${jwt.authentication-path}/changepassword")
	public AuthenticationResponse changePassword(HttpServletRequest request,@RequestBody UserDTO userDTO, Device device) throws GeneralException{
	
//		User user = userDTO.toUser(userDTO.getAuthority());
		// Perform the security
		String token = request.getHeader(config.getJwt().getHeader()).substring(7);
		
		System.out.println("My Token:"+token);
		
		String username = jwtTokenUtil.getUsernameFromToken(token);
		
		User user = (User) userService.loadUserByUsername(username);

		if(user!=null)
		{
			if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {

				user.setEmail(userDTO.getEmail().get());
				user.setUsername(userDTO.getUsername().get());
				user.setPhone(userDTO.getPhone());
				user.setState(userDTO.getState());
				user.setCity(userDTO.getCity());
				user.setWebsite(userDTO.getWebsite());
				user.setAddress(userDTO.getAddress());
				user.setPassword(bCryptPasswordEncoder.encode(userDTO.getNewpassword()));
				
				user=userService.save(user);
				AuthenticationRequest authrequest=new AuthenticationRequest();
				authrequest.setUsername(username);
				authrequest.setPassword(userDTO.getNewpassword());
				AuthenticationResponse response=tologin(authrequest, device);
               return response;
			} else
			{
				throw new GeneralException("Your Token Expired please try later");
			}
	
		}else
		{
			throw new NotValidOldPasswordException("Invalid Old Password");
		}
		//		SecurityContextHolder.getContext().setAuthentication(authentication);
//
//		// Reload password post-security so we can generate token
//		final UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
//		final String token = jwtTokenUtil.generateToken(userDetails, device);
//
//		// Return the token
//		return new AuthenticationResponse(token, AuthorityUtils.authorityListToSet(userDetails.getAuthorities()));
	}

	@ExceptionHandler(value= {AuthenticationException.class})
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException exception) {
		LOG.error(exception.getMessage(), exception);
		return handleMessage(HttpStatus.NOT_FOUND.toString(), exception.getMessage());
	}

	private ResponseEntity<ErrorMessage> handleMessage(String code, String message) {
		ErrorMessage error=new ErrorMessage();
    	error.setHttp_status(code);
    	error.setMessage(message);
    	error.setErrorDetails("");
    	error.setErrorDetails("");
    	error.setDate(new Date());
    	return ResponseEntity.badRequest().body(error);
	}
}