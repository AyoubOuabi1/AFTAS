package com.ayoub.aftas.aftas.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.ayoub.aftas.aftas.Config.Constant.APIVersion;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(APIVersion+"/auth/**")
                                .permitAll()
                                .requestMatchers(APIVersion+"/competition/**").hasAnyRole("MANAGER","JURY","USER")
                                .requestMatchers(APIVersion+"/fish/**").hasAnyRole("MANAGER")
                                .requestMatchers(APIVersion+"/hunting/**").hasAnyRole("MANAGER","JURY")
                                .requestMatchers(APIVersion+"/level/**").hasAnyRole("MANAGER")
                                .requestMatchers(APIVersion+"/member**").hasAnyRole("MANAGER")
                                .requestMatchers(APIVersion+"/ranking**").hasAnyRole("MANAGER","JURY","USER")
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

        ;

        return http.build();
    }
}
