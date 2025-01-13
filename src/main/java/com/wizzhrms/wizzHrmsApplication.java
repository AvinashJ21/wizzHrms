package com.wizzhrms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EntityScan(basePackages = {"com.wizzhrms.entity"}) 
@EnableJpaAuditing
public class wizzHrmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(wizzHrmsApplication.class, args);
	}

}
