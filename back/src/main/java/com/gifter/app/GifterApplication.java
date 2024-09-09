package com.gifter.app;

import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.entity.Role;
import com.gifter.app.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GifterApplication {
    Logger logger = LoggerFactory.getLogger(GifterApplication.class);
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(GifterApplication.class, args);
    }

    @Bean
    public CommandLineRunner startup() {
        return args -> {
            GifterUser admin = new GifterUser();
            admin.setPassword(encoder.encode("admin123"));
            admin.setEmail("admin@gifter.com");
            admin.setUsername("admin");
            admin.setEnabled(true);
            admin.setRole(Role.ADMIN);

            repository.save(admin);
            logger.info("admin initialized");
        };
    }
}
