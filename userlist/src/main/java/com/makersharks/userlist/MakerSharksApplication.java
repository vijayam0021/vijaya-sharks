package com.makersharks.userlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class MakerSharksApplication {

	public static void main(String[] args) {

		SpringApplication.run(MakerSharksApplication.class, args);
		System.out.println("Hello !");
	}


}
