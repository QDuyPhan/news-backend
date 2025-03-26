package com.quangduy.newsbackend.configuration;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.quangduy.newsbackend.constant.PredefinedRole;
import com.quangduy.newsbackend.entity.Role;
import com.quangduy.newsbackend.entity.User;
import com.quangduy.newsbackend.repository.RoleRepository;
import com.quangduy.newsbackend.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {
    BCryptPasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin123";

    UserRepository userRepository;

    RoleRepository roleRepository;

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            createAccount(userRepository, roleRepository);
        };
    }

    private void createAccount(UserRepository userRepository, RoleRepository roleRepository) {
        if (userRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {
            log.info("Initializing application.....");
            Role adminRole = roleRepository.save(Role.builder()
                    .name(PredefinedRole.ADMIN_ROLE)
                    .description("Admin role")
                    .build());

            roleRepository.save(Role.builder()
                    .name(PredefinedRole.USER_ROLE)
                    .description("User role")
                    .build());

            var role = new HashSet<Role>();
            role.add(adminRole);

            User user = User.builder()
                    .name(PredefinedRole.ADMIN_ROLE)
                    .username(ADMIN_USER_NAME)
                    .password(passwordEncoder.encode(ADMIN_PASSWORD))
                    .email("admin@gmail.com")
                    .roles(role)
                    .created_at(LocalDateTime.now())
                    .updated_at(LocalDateTime.now())
                    .build();
            userRepository.save(user);
            log.info("CREATE ADMIN ROLE");
        }
    }
}
