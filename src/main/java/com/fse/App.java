package com.fse;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
	final static Logger logger = Logger.getLogger(App.class);
    public static void main( String[] args )
    {
    	logger.info("Starting Spring Boot Application....");
    	SpringApplication.run(App.class, args);
    	logger.info("Spring Boot Application started....");
    	
    }
}

