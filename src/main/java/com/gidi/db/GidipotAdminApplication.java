package com.gidi.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@ComponentScan({"com.kleens.db","com.kleens.webApp.Controller"})
@SpringBootApplication
public class GidipotAdminApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GidipotAdminApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GidipotAdminApplication.class, args);
	}
}
