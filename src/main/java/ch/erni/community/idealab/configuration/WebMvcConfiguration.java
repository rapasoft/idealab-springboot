package ch.erni.community.idealab.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author rap
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"ch.erni.community.idealab.configuration", "ch.erni.community.idealab"})
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}

}
