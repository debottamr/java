package com.app.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;

public class ApplicationStartupRunner1 implements CommandLineRunner {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationStartupRunner1.class);
    @Override
    public void run(String... args) throws Exception {
    	LOGGER.info("Application Started !!");
    }
}
