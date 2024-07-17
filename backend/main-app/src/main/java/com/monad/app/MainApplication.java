package com.monad.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.monad.app", "com.monad.service.impl", "com.monad.repository.impl"})
@ComponentScan(basePackages = {
		"com.monad.app",
		"com.monad.service.impl",
		"com.monad.service",
		"com.monad.repository"
})
@EnableJpaRepositories(basePackages = "com.monad.repository")
@EntityScan(basePackages = "com.monad.repository.entities")
public class MainApplication {
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}
