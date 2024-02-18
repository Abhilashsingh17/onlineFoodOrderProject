package com.abhilash.onlineSale.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    /*
     *
     * // USING DEFAULT DATABASE TO GET/SAVE LOGIN DETAILS
     * // USERS AND AUTHORITIES ARE DEFAULT TABLE USED BY SPRING BOOT TO STORE USER
     * AND ROLE
     *
     * @Bean
     * public UserDetailsManager userDetailsManager(DataSource dataSource) {
     * JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
     * jdbcUserDetailsManager.setDataSource(dataSource);
     *
     * return jdbcUserDetailsManager;
     * }
     */

    // USING OUR CUSTOM TABLE TO RETRIEVE THE LOGIN USER DATA AND USER ROLES
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager
                .setUsersByUsernameQuery(
                        "select email_id,password,active_status from user where email_id = ?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select email_id, role from user_role where email_id = ?");

        return jdbcUserDetailsManager;

    }

    // USING PASSWORD ENCODER TO SAVE PASSWORD IN BCRYPT
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    /*
    enable support for HTTP method overriding, allowing HTML forms to use methods like DELETE, PUT, and PATCH.
     */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configure -> configure
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/").hasAnyRole("CUSTOMER", "EMPLOYEE", "ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/background/**").permitAll()
                        .requestMatchers("/customAuthenticate").permitAll()

                        .anyRequest()
                        .authenticated()

                )
                // authenticateTheUser
                .formLogin(form -> form
                        .loginPage("/public/loginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .defaultSuccessUrl("/", true)
                        .permitAll())

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        // .logoutSuccessUrl("/public/loginPage")
                        .permitAll())
                .exceptionHandling(configurer -> configurer.accessDeniedPage("/accessDenied")

                )
                // .sessionManagement(session -> session
                // .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                // )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

}
