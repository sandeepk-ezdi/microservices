package com.ezdi.rolebasedaccess.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import com.ezdi.rolebasedaccess.exception.CustomAccessDeniedHandler;
import com.ezdi.rolebasedaccess.filter.EzdiCustomRoleReplacementFilter;



public class EzdiWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
	@Autowired
	EzdiCustomRoleReplacementFilter ezdiCustomRoleFilter;
	
	@Autowired
	CustomAccessDeniedHandler customHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(ezdiCustomRoleFilter, AnonymousAuthenticationFilter.class);
		http.exceptionHandling().accessDeniedHandler(customHandler.getCustomAccessDeniedHandler());
		//http.addFilterAfter(ezdiCustomRoleFilter, FilterSecurityInterceptor.class);
	}
	
	
	
	@Bean(name = "ezdiCustomRoleFilterRegistration")
    public FilterRegistrationBean ezdiCustomRoleFilterRegistration(final EzdiCustomRoleReplacementFilter filter) {
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }
	
	
	
	/*
	@Bean
	public EzdiCustomRoleReplacementFilter ezdiCustomRoleFilter(){
		return new EzdiCustomRoleReplacementFilter();
	}
	*/
	
	
}
