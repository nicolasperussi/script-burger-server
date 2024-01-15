package com.nicolasperussi.scriptburger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.nicolasperussi.scriptburger")
public class ScriptburgerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScriptburgerApplication.class, args);
	}

}
