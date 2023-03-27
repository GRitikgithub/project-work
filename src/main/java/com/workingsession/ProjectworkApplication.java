package com.workingsession;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Project Working Session",version = "1.0",description = "First working session"))
public class ProjectworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectworkApplication.class, args);
	}

}
