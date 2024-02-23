package com.ayoub.aftas.aftas;

import com.ayoub.aftas.aftas.entities.PermissionEntity;
import com.ayoub.aftas.aftas.entities.RoleEntity;
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

import java.util.Set;

@RestController
@RequestMapping("/")
@SpringBootApplication
public class AftasApplication {

    @Autowired
    private  PermissionService permissionService;

    @Autowired
    private  RoleService roleService;


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
            PermissionEntity crudFish = permissionService.savePermission(
                    PermissionEntity.builder()
                            .name("CRUD_FISH")
                            .build()
            );
            PermissionEntity readCompetitions = permissionService.savePermission(
                    PermissionEntity.builder()
                            .name("READ_COMPETITIONS")
                            .build()
            );
            PermissionEntity insertUpdateDeleteCompetitions = permissionService.savePermission(
                    PermissionEntity.builder()
                            .name("INSERT_UPDATE_DELETE_COMPETITIONS")
                            .build()
            );

            PermissionEntity crudLevel = permissionService.savePermission(
                    PermissionEntity.builder()
                            .name("CRUD_LEVEL")
                            .build()
            );

            PermissionEntity crudMember = permissionService.savePermission(
                    PermissionEntity.builder()
                            .name("CRUD_MEMBER")
                            .build()
            );

            PermissionEntity crudRanking = permissionService.savePermission(
                    PermissionEntity.builder()
                            .name("CRUD_RANKING")
                            .build()
            );
            PermissionEntity crudHunting = permissionService.savePermission(
                    PermissionEntity.builder()
                            .name("CRUD_HUNTING")
                            .build()
            );

            roleService.saveRole(
                    RoleEntity.builder()
                         .name("USER")
                         .permissions(Set.of(readCompetitions,crudRanking))
                         .build()
            );
            roleService.saveRole(
                    RoleEntity.builder()
                         .name("MANAGER")
                         .permissions(Set.of(crudFish,readCompetitions,insertUpdateDeleteCompetitions,crudHunting,crudLevel,crudMember,crudRanking))
                         .build()
            );
            roleService.saveRole(
                    RoleEntity.builder()
                       .name("JURY")
                       .permissions(Set.of(readCompetitions,insertUpdateDeleteCompetitions,crudHunting,crudRanking))
                       .build()
            );
        };
    }*/

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
