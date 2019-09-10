package com.ems.configure;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.ems")
public class EmsConfigure {
	@Bean
	public Connection getConnection() throws Exception{
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/ems","sakha-lenovo-6","welcome");
		return con;
	}
	

}
