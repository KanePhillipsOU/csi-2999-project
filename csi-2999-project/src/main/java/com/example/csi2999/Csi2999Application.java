package com.example.csi2999;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.example.csi2999"})
public class Csi2999Application {

	public static void main(String[] args) {
		SpringApplication.run(Csi2999Application.class, args);
	}

}