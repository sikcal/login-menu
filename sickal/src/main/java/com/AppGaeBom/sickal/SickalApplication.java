package com.AppGaeBom.sickal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//맞는진모르겠지만 추가한것들
//@EntityScan("com.domain")
//@EnableJpaRepositories("com")
public class SickalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SickalApplication.class, args);
	}

}
