package com.example.list;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ListApplication {
	public static void main(String[] args) {
		SpringApplication.run(ListApplication.class, args);
	}

}
