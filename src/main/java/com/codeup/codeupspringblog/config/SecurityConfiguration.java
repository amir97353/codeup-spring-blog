package com.codeup.codeupspringblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;


// Informs spring that this class is to configure the Spring application
@Configuration
// Will allow us to edit the MVC security for our application
@EnableWebSecurity
public class SecurityConfiguration {
    // Dependency that we inject, so that we can retrieve details about the user who is trying to log in.
    private com.codeup.kotlinspringblog.services.UserDetailsLoader usersLoader;

    public SecurityConfiguration(com.codeup.kotlinspringblog.services.UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }


    // The @Bean annotation means that the class itself is being managed by Spring.

    // Is a class that is managed by Spring, specifically to hash and unhash our User passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // This class is used to manage the users Authentication status.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // This class will provide filters for our Spring security for different URL mappings.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests

                        .requestMatchers("/posts/create", "/posts/*/edit").authenticated()

                        .requestMatchers("/", "/posts", "/posts/*", "/sign-up", "/login", "/randomStudent").permitAll()

                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                )

                .formLogin((login) -> login.loginPage("/login").defaultSuccessUrl("/posts"))

                .logout((logout) -> logout.logoutSuccessUrl("/login?logout"))
                .httpBasic(withDefaults());
        return http.build();
    }
}
