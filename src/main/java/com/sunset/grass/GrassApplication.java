package com.sunset.grass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.sunset.grass.dao")
@SpringBootApplication
public class GrassApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrassApplication.class, args);
	}

}
