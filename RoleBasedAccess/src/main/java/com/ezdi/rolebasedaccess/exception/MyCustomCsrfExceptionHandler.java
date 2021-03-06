package com.ezdi.rolebasedaccess.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class MyCustomCsrfExceptionHandler implements AccessDeniedHandler{
	
	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException exception)
			throws IOException, ServletException {
		
		 res.sendError(403, "Get lost....You donot have  permission for this role !!!");
		
	}

}
