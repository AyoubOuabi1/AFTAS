package com.ayoub.aftas.aftas;

import com.ayoub.aftas.aftas.dto.CompetitionDto;
import com.ayoub.aftas.aftas.entities.PermissionEntity;
import com.ayoub.aftas.aftas.entities.RoleEntity;
import com.ayoub.aftas.aftas.services.CompetitionService;
import com.ayoub.aftas.aftas.services.PermissionService;
import com.ayoub.aftas.aftas.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@RestController
@RequestMapping("/")
@SpringBootApplication
public class AftasApplication {

    @Autowired
    private CompetitionService competitionService;




    public static void main(String[] args) {
        SpringApplication.run(AftasApplication.class, args);
    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    /*@Bean
    CommandLineRunner configRunner(){
        return (args) -> {
            List<CompetitionDto> competitionList = seed();
            for (CompetitionDto competition : competitionList) {
                competitionService.save(competition);
            }
        };
    }*/
    public static List<CompetitionDto> seed() {
        List<CompetitionDto> competitions = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            CompetitionDto competition = CompetitionDto.builder()
                    .id((long) (i + 1))
                    .code("CODE" + (i + 1))
                    .date(LocalDate.now().plusDays((i + 1)))
                    .startTime(new Time(System.currentTimeMillis())) // Current time
                    .endTime(new Time(System.currentTimeMillis() + 3600000*12)) // End time after 1 hour
                    .numberOfParticipants(random.nextInt(100) + 1) // Random number of participants between 1 and 100
                    .location("Location " + (i + 1))
                    .status("open")
                    .amount(random.nextInt() * 2) // Random amount between 0 and 100
                    .build();

            competitions.add(competition);
        }

        return competitions;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
