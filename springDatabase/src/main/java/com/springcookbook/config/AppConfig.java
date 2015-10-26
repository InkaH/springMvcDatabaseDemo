package com.springcookbook.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Spring configuration class. (Java class replacement to spring-servlet.xml.)
 * 
 * @author Inka
 */

// This declares it as a Spring configuration class
@Configuration
//This enables Spring's ability to receive and process web requests
@EnableWebMvc
//This scans the com.springcookbook.controller package for Spring components
@ComponentScan(basePackages = "com.springcookbook.controller")

public class AppConfig {
	
	//  Renders the path to a view with the given prefix and suffix.
	 @Bean
	    public ViewResolver viewResolver() {
	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setViewClass(JstlView.class);
	        viewResolver.setPrefix("/WEB-INF/views/");
	        viewResolver.setSuffix(".jsp");
	 
	        return viewResolver;
	    }
	 
	 //  Create DataSource bean with the database connection details.
	 //  A connection (a Datasource object) to a database named db1 on the 3306 port using the user1 user is created.
	 @Bean
	 public DataSource dataSource() {
	 DriverManagerDataSource dataSource = new
	 DriverManagerDataSource();
	 dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	 dataSource.setUrl("jdbc:mysql://localhost:3306/db1");
	 dataSource.setUsername("user1");
	 dataSource.setPassword("pass1");
	 return dataSource;
	 }
	 
	 //  The JdbcTemplate bean is a Spring object that provides convenient methods to query a
	 //  database using JDBC. It uses the previously defined DataSource bean.
	 @Bean
	 public JdbcTemplate jdbcTemplate(DataSource dataSource) {
	 return new JdbcTemplate(dataSource);
	 }
}
