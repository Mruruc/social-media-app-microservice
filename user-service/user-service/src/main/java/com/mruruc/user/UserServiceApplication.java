package com.mruruc.user;

import com.mruruc.user.model.User;
import com.mruruc.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

   // @Bean
    public CommandLineRunner runner(UserRepository userRepository) {
        return args -> {
            userRepository.saveAll(List.of(
                    User.builder()
                            .firstName("John")
                            .lastName("Doe")
                            .email("john.doe@example.com")
                            .password("password123")
                            .isAccountLocked(false)
                            .isAccountEnabled(true)
                            .build(),
                    User.builder()
                            .firstName("Jane")
                            .lastName("Smith")
                            .email("jane.smith@example.com")
                            .password("securepass")
                            .isAccountLocked(false)
                            .isAccountEnabled(true)
                            .build(),
                    User.builder()
                            .firstName("Alice")
                            .lastName("Johnson")
                            .email("alice.johnson@example.com")
                            .password("alice2023")
                            .isAccountLocked(true)
                            .isAccountEnabled(false)
                            .build(),
                    User.builder()
                            .firstName("Bob")
                            .lastName("Williams")
                            .email("bob.williams@example.com")
                            .password("bobpass")
                            .isAccountLocked(false)
                            .isAccountEnabled(true)
                            .build(),
                    User.builder()
                            .firstName("Emily")
                            .lastName("Davis")
                            .email("emily.davis@example.com")
                            .password("emily1234")
                            .isAccountLocked(false)
                            .isAccountEnabled(true)
                            .build(),
                    User.builder()
                            .firstName("Michael")
                            .lastName("Brown")
                            .email("michael.brown@example.com")
                            .password("michael5678")
                            .isAccountLocked(false)
                            .isAccountEnabled(true)
                            .build(),
                    User.builder()
                            .firstName("Sophia")
                            .lastName("Martinez")
                            .email("sophia.martinez@example.com")
                            .password("sophia123")
                            .isAccountLocked(false)
                            .isAccountEnabled(true)
                            .build(),
                    User.builder()
                            .firstName("James")
                            .lastName("Garcia")
                            .email("james.garcia@example.com")
                            .password("jamessecure")
                            .isAccountLocked(true)
                            .isAccountEnabled(false)
                            .build(),
                    User.builder()
                            .firstName("Olivia")
                            .lastName("Miller")
                            .email("olivia.miller@example.com")
                            .password("olivia6789")
                            .isAccountLocked(false)
                            .isAccountEnabled(true)
                            .build(),
                    User.builder()
                            .firstName("Ethan")
                            .lastName("Wilson")
                            .email("ethan.wilson@example.com")
                            .password("ethansecure")
                            .isAccountLocked(false)
                            .isAccountEnabled(true)
                            .build()
            ));
        };
    }

}
