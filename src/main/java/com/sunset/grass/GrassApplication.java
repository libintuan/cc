package com.sunset.grass;

import com.sunset.grass.registor.ImportBeanGrassDefinitionRegistrar;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//@MapperScan("com.sunset.grass.dao")
@SpringBootApplication
@Import(ImportBeanGrassDefinitionRegistrar.class)
public class GrassApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrassApplication.class, args);
	}

}
