package fr.esgi.extiaordinaryapi.config;

import fr.esgi.extiaordinaryapi.repository.SeanceRepository;
import fr.esgi.extiaordinaryapi.service.SeanceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public SeanceService seanceService(SeanceRepository seanceRepository) {
        return new SeanceService(seanceRepository);
    }
}
