package org.currency.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"org.currency.starter.repository"})
@EntityScan(basePackages = {"org.currency.starter.domain"})
//--spring.profiles.active=dev
public class CurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyApplication.class, args);
	}
}
