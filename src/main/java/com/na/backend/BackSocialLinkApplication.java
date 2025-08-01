package com.na.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackSocialLinkApplication implements CommandLineRunner {

	@Value("${server.port}")
	private String PUERTO;

	public static void main(String[] args) {
		SpringApplication.run(BackSocialLinkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.printf("SE HA INICIADO  EL SERVICIO DE BACKEND GENERAL CON EL PUERTO %s", PUERTO + "\n");
	}

}
