package edu.neu.pmbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class ProjectManagementBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementBackEndApplication.class, args);
	}

}
