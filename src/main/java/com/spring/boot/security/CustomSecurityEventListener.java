package com.spring.boot.security;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.stereotype.Component;

@Component
public class CustomSecurityEventListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {

	public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
		System.out.println("***********  Authentication failure reason : " + event.getException().getMessage());
	}
}
