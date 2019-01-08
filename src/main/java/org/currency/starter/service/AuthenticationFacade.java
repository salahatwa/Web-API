package org.currency.starter.service;

import org.currency.starter.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {
 
    @SuppressWarnings("unchecked")
	@Override
    public Object getCurrentUser() {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if (principal instanceof UserDetails) {
    	  return (User)principal;
    	} else {
    	  String username = principal.toString();
    	  System.out.println("Login Error:"+username);
    	  return null;
    	}
    }
}
