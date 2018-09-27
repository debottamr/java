package jmsserver;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
 
public class Main
{
    public static void main(String[] args)
    {
        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(JMSApplication.class, args);
 
        //Get JMS template bean reference
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
 
        // Send a message
        System.out.println("Sending a message.");
        jmsTemplate.convertAndSend("jms.message.endpoint", new Message(1001L, "test body", new Date()));
    }
}