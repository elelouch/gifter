package com.gifter.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class GifterApplication {
	public static void main(String[] args) {
		SpringApplication.run(GifterApplication.class, args);
	}
}
