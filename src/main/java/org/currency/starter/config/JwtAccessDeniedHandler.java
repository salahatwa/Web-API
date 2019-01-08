package org.currency.starter.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.currency.starter.exception.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;


@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    
    @Autowired
    public Jackson2JsonObjectMapper jackson2JsonObjectMapper;
    
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException e) throws IOException, ServletException {
        
    	ErrorMessage error=new ErrorMessage();
    	error.setHttp_status(HttpStatus.FORBIDDEN.toString());
    	error.setMessage(e.getLocalizedMessage());
    	error.setErrorDetails("");
    	error.setErrorDetails("");
    	error.setDate(new Date());
    	
        try {
            String json = jackson2JsonObjectMapper.toJson(error);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.getWriter().write(json);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        
    }

}
