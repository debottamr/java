package com.app.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.app.config.App;

@Order(value=2)
@Component
class ApplicationStartupRunnerTwo implements CommandLineRunner {
    private static final Logger LOGGER = LogManager.getLogger(App.class);
 
    @Override
    public void run(String... args) throws Exception {
    	LOGGER.info("ApplicationStartupRunnerTwo run method Started !!");
    }
}