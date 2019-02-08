package com.spring.boot.actuator;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;

@Component
public class CustomEndpoint implements Endpoint<String> {

	public String getId() {
		return "customep";
	}

	public boolean isEnabled() {
		return true;
	}

	public boolean isSensitive() {
		return true;
	}

	public String invoke() {
		return "Successful custom endpoint test";
	}

}
