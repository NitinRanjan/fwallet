package com.fab.hotel.fwallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FwalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(FwalletApplication.class, args);
	}

}
