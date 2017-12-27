package com.example.demo.config;

import com.example.demo.module.security.core.MyCustomAuthenticationFilter;
import com.example.demo.module.security.core.MyFilterSecurityInterceptor;
import com.example.demo.module.security.core.MyUserDetailService;
import com.example.demo.module.user.service.PersistentTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailService myUserDetailService;
	@Autowired
	private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
	@Autowired
	private MyCustomAuthenticationFilter myCustomAuthenticationFilter;
	@Autowired
	private PersistentTokenService persistentTokenService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/admin/**")
				.authenticated();

		http.formLogin()
				.loginPage("/adminlogin")
				.loginProcessingUrl("/adminlogin")
				.failureUrl("/adminlogin?error")
				.defaultSuccessUrl("/admin/dashboard")
				.permitAll();

//		http.rememberMe().key("remember-me").rememberMeServices(persistentTokenBasedRememberMeServices());

		http.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/adminlogin")
				.deleteCookies("JSESSIONID", "remember-me");

//		myCustomAuthenticationFilter.setAuthenticationManager(authenticationManager);

		http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
		http.addFilterBefore(myCustomAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		http.csrf().ignoringAntMatchers("/favicon.ico");
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/static/**");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		authProvider.setUserDetailsService(myUserDetailService);
//		ReflectionSaltSource saltSource = new ReflectionSaltSource();
//		saltSource.setUserPropertyToUse("username");
//		authProvider.setSaltSource(saltSource);
		auth.authenticationProvider(authProvider);
		auth.userDetailsService(myUserDetailService);
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(myUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
//	}

	@Bean
	public PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices services = new PersistentTokenBasedRememberMeServices("remember-me"
				, myUserDetailService, persistentTokenService);
		services.setAlwaysRemember(true);
		return services;
	}

}
