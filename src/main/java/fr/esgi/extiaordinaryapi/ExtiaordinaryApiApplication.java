package fr.esgi.extiaordinaryapi;

import fr.esgi.extiaordinaryapi.config.JpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(JpaConfig.class)
public class ExtiaordinaryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExtiaordinaryApiApplication.class, args);
    }

}
