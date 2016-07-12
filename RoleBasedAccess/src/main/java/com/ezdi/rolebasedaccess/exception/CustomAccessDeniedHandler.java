package com.ezdi.rolebasedaccess.exception;

import java.util.LinkedHashMap;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.DelegatingAccessDeniedHandler;
import org.springframework.security.web.csrf.CsrfException;
import org.springframework.stereotype.Component;

/*
 * This class returns customized AccessDeniedHandler to handle different kind of Exceptions.
 */
@Component
public class CustomAccessDeniedHandler {

	public AccessDeniedHandler getCustomAccessDeniedHandler(){
		LinkedHashMap<Class<? extends AccessDeniedException>,AccessDeniedHandler> handlers = new LinkedHashMap<>();
		handlers.put(AuthorizationServiceException.class, new MyCustomAuthorizationServiceExceptionHandler());
		handlers.put(CsrfException.class, new MyCustomCsrfExceptionHandler());
		DelegatingAccessDeniedHandler myHandler = new DelegatingAccessDeniedHandler(handlers, new AccessDeniedHandlerImpl());
		return myHandler;
	}
}
