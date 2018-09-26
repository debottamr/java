package com.app.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.app.config.App;

@Component
@Order(value=1)
public class ApplicationStartupRunner implements CommandLineRunner {
    private static final Logger LOGGER = LogManager.getLogger(App.class);
 
    @Override
    public void run(String... args) throws Exception {
    	LOGGER.info("ApplicationStartupRunner1  run method Started !!");
    }
}
