package com.app.component;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledComponent {

	Logger logger = LoggerFactory.getLogger(ScheduledComponent.class);

	@Scheduled(initialDelay = 1, fixedRate = 1000)
	public void run() {
	    logger.info("Current time is :: " + Calendar.getInstance().getTime());
	}
	
	@Scheduled(fixedDelay = 10000)
	public void run1() {
	    logger.info("Current time is :: " + Calendar.getInstance().getTime());
	}
	@Scheduled(cron = "0 10 10 10 * ?")
	public void run3() {
	    logger.info("Current time is :: " + Calendar.getInstance().getTime());
	}
}
