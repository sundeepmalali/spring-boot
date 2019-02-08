package com.spring.boot;

import org.springframework.beans.factory.annotation.Value;

//@Configuration
public class AppConfig {

//	@Bean
	public AppMessage message(@Value("${app.message}") String message) {
		AppMessage msg = new AppMessage();
		msg.setMessage("This is java config message : " + message);
		return msg;
	}
	
//	@Bean
//	@Profile("prod")
//	public AppMessage message(@Value("${app.message}") String message) {
//		AppMessage msg = new AppMessage();
//		msg.setMessage("This is java config message : " + message);
//		return msg;
//	}
//
//	@Bean
//	@Profile("dev")
//	public AppMessage messageDev(@Value("${app.message.dev}") String message) {
//		AppMessage msg = new AppMessage();
//		msg.setMessage("This is java config message : " + message);
//		return msg;
//	}
}