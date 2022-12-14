package com.edutecno.jpacrud;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.edutecno.jpacrud")
@PropertySource("classpath:database.properties")
public class AppConfig {

	//Se inyecta el environment
	@Autowired
	Environment environment;
	
	//Se setea la base de datos
	@Bean
	DataSource dataSource() {
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setUrl(environment.getProperty("url"));
		dmds.setUsername(environment.getProperty("dbuser"));
		dmds.setPassword(environment.getProperty("dbpassword"));
		dmds.setDriverClassName(environment.getProperty("driver"));
		return dmds;	
	}
	
}
