package db.dbProject;

import db.dbProject.project.controller.GetApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackageClasses = GetApi.class)
public class DbProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbProjectApplication.class, args);
	}

}
