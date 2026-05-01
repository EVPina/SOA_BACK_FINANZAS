package com.soa.soafinanzas;

import org.springframework.boot.SpringApplication;

public class TestSoafinanzasApplication {

	public static void main(String[] args) {
		SpringApplication.from(SoafinanzasApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
