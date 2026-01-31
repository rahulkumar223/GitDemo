package com.springsecurity.config;

import com.springsecurity.jwt.AuthEntryPointJwt;
import com.springsecurity.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Autowired
    DataSource dataSource;

    @Autowired
    private AuthEntryPointJwt authEntryPointJwt;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Security configured");

        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/public/free").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated());

        //  http.formLogin(withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(AbstractHttpConfigurer::disable);

       // http.httpBasic(withDefaults());
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        // Configure Unauthorized request handler
        http.exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPointJwt));

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        System.out.println("Users are saved....");
//
//        UserDetails user1 = User.withUsername("testUser")
//                //  .password("{noop}test123")
//                .password(passwordEncoder().encode("test123"))
//                .roles("USER")
//                .build();
//        UserDetails admin = User.withUsername("testAdmin")
////                .password("{noop}admin123")
//                .password(passwordEncoder().encode("admin123"))
//                .roles("ADMIN")
//                .build();
//
//
//        // Configure - SELLER , MANAGER , READER
////        return new InMemoryUserDetailsManager(user1, admin);
//
//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//        userDetailsManager.createUser(user1);
//        userDetailsManager.createUser(admin);
//
//        return userDetailsManager;
//
//    }


    @Bean
    public UserDetailsService userDetailsService() {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public CommandLineRunner saveData(UserDetailsService userDetailsService) {

        System.out.println("User's data saved");

        return args -> {
            JdbcUserDetailsManager manager = (JdbcUserDetailsManager) userDetailsService;

            UserDetails user1 = User.withUsername("testUser")
                    //  .password("{noop}test123")
                    .password(passwordEncoder().encode("test123"))
                    .roles("USER")
                    .build();
            UserDetails user2 = User.withUsername("testUser2")
                    //  .password("{noop}test123")
                    .password(passwordEncoder().encode("test1234"))
                    .roles("USER")
                    .build();
            UserDetails admin = User.withUsername("testAdmin")
//                .password("{noop}admin123")
                    .password(passwordEncoder().encode("admin123"))
                    .roles("ADMIN")
                    .build();

            manager.createUser(user1);
            manager.createUser(user2);
            manager.createUser(admin);

        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // use custom algo- if you any
        return new BCryptPasswordEncoder();
    }

}
