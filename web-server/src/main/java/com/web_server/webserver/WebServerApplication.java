package com.web_server.webserver;

import com.web_server.webserver.controller.WebAccountsController;
import com.web_server.webserver.service.WebAccountsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(useDefaultFilters=false)
public class WebServerApplication {

	public static final String ACCOUNTS_SERVICE_URL = "http://account-service";

	public static void main(String[] args) {
		SpringApplication.run(WebServerApplication.class, args);
	}

	@LoadBalanced
	@Bean
	RestTemplate restTemplate () {
		return new RestTemplate();
	}

	@Bean
	public WebAccountsService accountsService () {
		return new WebAccountsService(ACCOUNTS_SERVICE_URL);
	}

	@Bean
	public WebAccountsController accountsController () {
		return new WebAccountsController(accountsService());
	}
}
