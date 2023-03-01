package fr.esgi.extiaordinaryapi.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "fr.esgi.extiaordinaryapi.repository")
@EntityScan(basePackages = "fr.esgi.extiaordinaryapi.entity")
public class JpaConfig {
}
