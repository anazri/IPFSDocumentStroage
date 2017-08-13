package documentStorage.controller;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo", "documentStorage.controller", "documentStorage.dao", "documentStorage.service", "documentStroage.repository"})
public class IpfsDocumentStroageApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpfsDocumentStroageApplication.class, args);
	}
}
