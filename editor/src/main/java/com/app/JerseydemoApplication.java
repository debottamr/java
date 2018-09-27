package com.app;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.app.controller.UserResource;

@SpringBootApplication
@EnableScheduling
public class JerseydemoApplication extends SpringBootServletInitializer {
	private static Logger logger = LoggerFactory.getLogger(JerseydemoApplication.class);
	@Bean
	ResourceConfig resourceConfig() {
		return new ResourceConfig().register(UserResource.class);
	}

	public static void main(String[] args) {
		  logger.info("Starting app");
	      SpringApplication.run(JerseydemoApplication.class);

	}
}