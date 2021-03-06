package com.leyes.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ImportResource("classpath:dubbo.xml")
public class AppStarter {
	private final static Logger logger = LogManager.getLogger();
	
	@RequestMapping("/")
    public String greeting() {
        return "Hello World!";
    }
	
	public static void main(String[] args) {
		SpringApplication.run(AppStarter.class, args);
	}
}
