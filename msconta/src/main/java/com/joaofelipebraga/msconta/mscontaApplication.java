package com.joaofelipebraga.msconta;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@EnableDiscoveryClient
@EnableRabbit
@SpringBootApplication
public class mscontaApplication {

	public static void main(String[] args) {
		SpringApplication.run(mscontaApplication.class, args);
	}

}
