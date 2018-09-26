package com.app.container;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class Container {

	@Bean
	public ConfigurableServletWebServerFactory webServerFactory()
	{
	    JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
	    factory.setPort(9000);
	    factory.setContextPath("/myapp");
	    factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
	    return factory;
	}
}
