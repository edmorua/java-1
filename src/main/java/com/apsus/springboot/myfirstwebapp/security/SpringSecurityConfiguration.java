package com.apsus.springboot.myfirstwebapp.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfiguration {

    //LDAP or Database
    //In memory

    //InMemoryUserDetailsManager

    String username = "admin";
    String password = "dumyy";
    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager(){
        UserDetails userDetails = creteNewUser("admin", "dummy");
        UserDetails userDetails2 = creteNewUser("admin2", "dummy2");
        UserDetails userDetails3 = creteNewUser("edmo", "cart34");
        return new InMemoryUserDetailsManager(userDetails, userDetails2, userDetails3);
    }

    private UserDetails creteNewUser(String username, String password) {
        return User.builder()
                .username(username)
                .password(password)
                .passwordEncoder(passwordEncoder()::encode)
                .roles("USER", "ADMIN")
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
