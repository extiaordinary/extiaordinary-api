package fr.esgi.extiaordinaryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "fr.esgi.extiaordinaryapi.repository")
@EntityScan(basePackages = "fr.esgi.extiaordinaryapi.entity")
public class ExtiaordinaryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExtiaordinaryApiApplication.class, args);
    }

}
