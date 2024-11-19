package com.gmail.bergrin.SpringSecurytyApp.config;

import com.gmail.bergrin.SpringSecurytyApp.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final PersonDetailsService personDetailsService;

  @Autowired
  public SecurityConfig(PersonDetailsService personDetailsService) {
    this.personDetailsService = personDetailsService;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    // Замените NoOpPasswordEncoder.getInstance() на вашу реализацию PasswordEncoder
    return NoOpPasswordEncoder.getInstance();
  }


  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    // Configure AuthenticationManagerBuilder
    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.userDetailsService(personDetailsService);  ///*authenticationProvider(authProvider)*/
    // Get AuthenticationManager
    AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

    http.csrf(AbstractHttpConfigurer::disable);

    http.authenticationManager(authenticationManager)
        .authorizeHttpRequests((authz) -> authz
            .requestMatchers("/", "/auth/**").permitAll()
            .anyRequest().authenticated()
        );

    http.formLogin((formLogin) ->
        formLogin
            .loginPage("/auth/login")
            .loginProcessingUrl("/process_login")
            .defaultSuccessUrl("/hello", true)
            .failureUrl("/auth/login?error")
    );

    return http.build();
  }


}
