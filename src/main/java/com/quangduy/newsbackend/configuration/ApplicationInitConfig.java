package com.quangduy.newsbackend.configuration;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.quangduy.newsbackend.entity.User;
import com.quangduy.newsbackend.repository.UserRepository;
import com.quangduy.newsbackend.utils.Role;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {
    BCryptPasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());
                User user = User.builder()
                        .username("admin")
                        .name("admin")
                        .email("admin@admin.com")
                        .created_at(LocalDateTime.now())
                        .updated_at(LocalDateTime.now())
//                        .roles(roles)
                        .password(passwordEncoder.encode("admin"))
                        .build();
                userRepository.save(user);
                log.info("CREATE ADMIN ROLE");
            }
        };
    }
}
