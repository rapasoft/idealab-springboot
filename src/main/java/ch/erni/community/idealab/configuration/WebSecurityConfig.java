package ch.erni.community.idealab.configuration;

import ch.erni.community.idealab.enums.SecurityRoles;
import ch.erni.community.idealab.service.SimpleUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author rap
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SimpleUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()

				.authorizeRequests()
				.antMatchers("/roles/**").hasRole(SecurityRoles.ADMIN.name())
				.antMatchers("/users/**").hasRole(SecurityRoles.ADMIN.name())
				.antMatchers("/ideas/**").hasRole(SecurityRoles.USER.name())
				.antMatchers("/resources/**").permitAll()
				.anyRequest().permitAll()

				.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()

				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
				.permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	/*
	// LDAP - Does not work right now
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
				.ldapAuthentication()
				.userSearchFilter("(&(sAMAccountName={0})(objectCategory=Person)(objectClass=user))")
				.userSearchBase("OU=Employees,OU=User,OU=ERNI Consulting,DC=erni2,DC=ch")
				.contextSource()
				.url("ldap://dunajec.erni2.ch:389/DC=erni2,DC=ch")
				.managerDn("CN=Rajzák Pavol,OU=Employees,OU=User,OU=ERNI Consulting,DC=erni2,DC=ch")
				.managerPassword(SecurityConstants.PASSWORD);
	}
	*/

}
