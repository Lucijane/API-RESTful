package com.testepedidos.principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {
	    "com.testepedidos.dados",
		"com.testepedidos.dto",
		"com.testepedidos.modelos",
		 "com.testepedidos.servicos",
	    "com.testepedidos.controles"
	})
@EntityScan(basePackages = "com.testepedidos.modelos")
@EnableJpaRepositories(basePackages = "com.testepedidos.dados")

public class DesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

}
