package com.anunciadores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class AnunciadoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnunciadoresApplication.class, args);
	}

}
