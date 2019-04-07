package br.com.gleisonandrade.bancoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
		  basePackages = "br.com.gleisonandrade.bancoapi.repositories", repositoryImplementationPostfix = "CustomImpl")
public class BancoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoApiApplication.class, args);
	}

}
