package dev.evgeni.peopleapi.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private DataSource defaultDb;

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager userManager = new JdbcUserDetailsManager(defaultDb);

        UserDetails user = User.builder().username("ivan")
                .password(passwordEncoder().encode("123456")).roles("ADMIN").build();

        if (!userManager.userExists("ivan")) {
            userManager.createUser(user);
        }
        return userManager;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder aMB = http.getSharedObject(AuthenticationManagerBuilder.class);
        aMB.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());

        return aMB.build();
    }


}
