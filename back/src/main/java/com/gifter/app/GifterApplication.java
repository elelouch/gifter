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

import java.util.List;

@SpringBootApplication
public class GifterApplication {
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


            GifterUser elo = new GifterUser();
            elo.setPassword(encoder.encode("admin123"));
            elo.setFirstName("Elias");
            elo.setLastName("Rojas");
            elo.setEmail("elo@gifter.com");
            elo.setUsername("elo01");
            elo.setEnabled(true);
            elo.setRole(Role.USER);

            GifterUser nana = new GifterUser();
            nana.setFirstName("Nany");
            nana.setLastName("Doxxed");
            nana.setPassword(encoder.encode("admin123"));
            nana.setEmail("nana@gifter.com");
            nana.setUsername("nana09");
            nana.setEnabled(true);
            nana.setRole(Role.USER);

            repository.saveAll(List.of(elo,nana, admin));
        };
    }
}
