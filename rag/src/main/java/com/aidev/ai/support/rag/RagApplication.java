package com.aidev.ai.support.rag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class RagApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RagApplication.class);

		// Detect Docker or Local
		if (new File("/.dockerenv").exists()) {
			app.setAdditionalProfiles("docker");
			System.out.println("🔹 Running with Docker profile");
		} else {
			app.setAdditionalProfiles("local");
			System.out.println("🔹 Running with Local profile");
		}

		app.run(args);
	}

}
