package com.formacionbdi.springboot.app.gateway.springbootserviciogatewayserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootServicioGatewayServApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioGatewayServApplication.class, args);
	}

}
