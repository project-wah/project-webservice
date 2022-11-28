package com.project.wah.projectwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ProjectWebserviceApplication {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ProjectWebserviceApplication.class);
		application.addListeners(new ApplicationPidFileWriter());
		application.run(args);
	}
}
