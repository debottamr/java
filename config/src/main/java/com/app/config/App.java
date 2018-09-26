package com.app.config;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.app.init.ApplicationStartupRunner1;

@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration(excludeName = { "multipartResolver", "mbeanServer" })
public class App extends SpringBootServletInitializer implements CommandLineRunner {
	private static final Logger LOGGER = LogManager.getLogger(App.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(App.class, args);
		LOGGER.info("Info level log message");
		LOGGER.debug("Debug level log message");
		LOGGER.error("Error level log message");
		String[] beanNames = ctx.getBeanDefinitionNames();

		Arrays.sort(beanNames);

		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("Application Started 2!!");

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}

	@Bean
	public ApplicationStartupRunner1 schedulerRunner() {
		return new ApplicationStartupRunner1();
	}
}
