package org.currency.starter.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.currency.starter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * We’ll use JWTAuthenticationFilter to implement a filter that -
 * 
 * reads JWT authentication token from the Authorization header of all the
 * requests validates the token loads the user details associated with that
 * token. Sets the user details in Spring Security’s SecurityContext. Spring
 * Security uses the user details to perform authorization checks. We can also
 * access the user details stored in the SecurityContext in our controllers to
 * perform our business logic.
 * 
 * @author salah atwa
 *
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private Config config;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String authToken = request.getHeader(config.getJwt().getHeader());
		System.out.println(authToken);

		if (authToken != null && authToken.startsWith("Bearer "))
			authToken = authToken.substring(7);

		String username = jwtTokenUtil.getUsernameFromToken(authToken);

		logger.info("checking authentication for user :{} ", username);

		try {
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				// It is not compelling necessary to load the use details from the database. You
				// could also store the information
				// in the token and read it from it. It's up to you ;)
				UserDetails userDetails = this.userService.loadUserByUsername(username);

				// For simple validation it is completely sufficient to just check the token
				// integrity. You don't have to call
				// the database compellingly. Again it's up to you ;)
				if (jwtTokenUtil.validateToken(authToken, userDetails)) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					logger.info("authenticated user " + username + ", setting security context");
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		chain.doFilter(request, response);
	}
}