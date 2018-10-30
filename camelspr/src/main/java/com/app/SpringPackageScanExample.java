package com.app;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringPackageScanExample {
	public static final void main(String[] args) throws Exception {
		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
				"springPackageScanApplicationContext.xml");
		CamelContext camelContext = SpringCamelContext.springCamelContext(
				appContext, false);
		try {
			camelContext.start();
			Thread.sleep(4000);
		} finally {
			camelContext.stop();
			appContext.close();
		}
	}
}