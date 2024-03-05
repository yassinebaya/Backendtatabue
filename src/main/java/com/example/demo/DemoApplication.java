package com.example.demo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.AccoubtService;
@RestController
@CrossOrigin("*")
@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoApplication.class);
    }
	//@Bean
	CommandLineRunner commandLineRunner(AccoubtService accoubtService){

		return args->{
		 	//accoubtService.addnewRole("PUBLIC");
			//accoubtService.addnewRole("ADMIN");
			//accoubtService.addnewRole("STAGAIRE");
			//accoubtService.addNewUser("public","123");
			//accoubtService.addNewUser("user2","123");
			
			//accoubtService.addRoleToUser("user1","USER");
		

		};

	}



}
