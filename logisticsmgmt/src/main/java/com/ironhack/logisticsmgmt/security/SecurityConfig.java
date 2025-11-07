package com.ironhack.logisticsmgmt.security;

import com.ironhack.logisticsmgmt.security.filters.CustomAuthenticationFilter;
import com.ironhack.logisticsmgmt.security.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * This is the main configuration class for security in the application. It enables web security,
 * sets up the password encoder, and sets up the security filter chain.
 */
@Configuration
@EnableWebSecurity // indicates it is a security config class using spring web security
@RequiredArgsConstructor
public class SecurityConfig {

    // UserDetailsService is an interface provided by Spring Security that defines a way to retrieve user information
    private final UserDetailsService userDetailsService;

    // Autowired instance of the AuthenticationManagerBuilder (provided by Spring Security)
    private final AuthenticationManagerBuilder authManagerBuilder;


    /**
     * Bean definition for AuthenticationManager
     *
     * @param authenticationConfiguration the instance of AuthenticationConfiguration
     * @return an instance of the AuthenticationManager
     * @throws Exception if there is an issue getting the instance of the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Bean definition for SecurityFilterChain
     *
     * @param http the instance of HttpSecurity
     * @return an instance of the SecurityFilterChain
     * @throws Exception if there is an issue building the SecurityFilterChain
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CustomAuthenticationFilter instance created
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authManagerBuilder.getOrBuild());

        // set the URL that the filter should process
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");


        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/login/**").permitAll()// public endpoint, we could add more if we wanted to
                        .requestMatchers("api/greet").permitAll()
                        .requestMatchers("api/greet/personal").hasAnyAuthority("ROLE_USER")
                        .requestMatchers(GET, "/api/users").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers(POST, "/api/users").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(POST, "/api/roles").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(POST, "/api/roles/add-to-user").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(GET, "/api/checkins").hasAnyAuthority("ROLE_CONTROLLER")
                        .requestMatchers(POST, "/api/checkins").hasAnyAuthority("ROLE_CONTROLLER")
                        .requestMatchers(DELETE, "/api/checkins").hasAnyAuthority("ROLE_SUPERVISOR")
                        .requestMatchers(GET, "/api/routes").hasAnyAuthority("ROLE_USER")
                        .requestMatchers(POST, "/api/routes").hasAnyAuthority("ROLE_CONTROLLER")
                        .requestMatchers(DELETE, "/api/routes").hasAnyAuthority("ROLE_SUPERVISOR")
                        .requestMatchers(GET, "/api/dispatchers").hasAnyAuthority("ROLE_USER")
                        .requestMatchers(POST, "/api/dispatchers").hasAnyAuthority("ROLE_SUPERVISOR")
                        .requestMatchers(PUT, "/api/dispatchers").hasAnyAuthority("ROLE_SUPERVISOR")
                        .requestMatchers(DELETE, "/api/dispatchers").hasAnyAuthority("ROLE_SUPERVISOR")
                        .requestMatchers(GET, "/api/employees").hasAnyAuthority("ROLE_USER")
                        .requestMatchers(POST, "/api/employees").hasAnyAuthority("ROLE_SUPERVISOR")
                        .requestMatchers(PUT, "/api/employees").hasAnyAuthority("ROLE_SUPERVISOR")
                        .requestMatchers(DELETE, "/api/employees").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(GET, "/api/drivers").hasAnyAuthority("ROLE_USER")
                        .requestMatchers(POST, "/api/drivers").hasAnyAuthority("ROLE_CONTROLLER")
                        .requestMatchers(PUT, "/api/drivers").hasAnyAuthority("ROLE_CONTROLLER")
                        .requestMatchers(DELETE, "/api/drivers").hasAnyAuthority("ROLE_SUPERVISOR")
                        .requestMatchers(GET, "/api/companies").hasAnyAuthority("ROLE_USER")
                        .requestMatchers(POST, "/api/drivers").hasAnyAuthority("ROLE_SUPERVISOR")
                        .requestMatchers(PUT, "/api/drivers").hasAnyAuthority("ROLE_SUPERVISOR")
                        .requestMatchers(DELETE, "/api/drivers").hasAnyAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()); // any other endpoints require authentication

        // add the custom authentication filter to the http security object
        http.addFilter(customAuthenticationFilter);

        // Add the custom authorization filter before the standard authentication filter.
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        // Build the security filter chain to be returned.
        return http.build();
    }
}
