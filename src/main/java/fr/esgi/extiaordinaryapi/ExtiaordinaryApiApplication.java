package fr.esgi.extiaordinaryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "fr.esgi.extiaordinaryapi" )
@EnableJpaRepositories(basePackages = "fr.esgi.extiaordinaryapi.repository")
@EntityScan("fr.esgi.extiaordinaryapi.entity")
public class ExtiaordinaryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExtiaordinaryApiApplication.class, args);
    }

}
