package de.h4ck4t0n;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJSONDoc
public class CarshareApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarshareApplication.class, args);
	}
}
