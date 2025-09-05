package com.aidev.ai.support.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class EnvLogger {

    private static final Logger log = LoggerFactory.getLogger(EnvLogger.class);
    private final Environment environment;

    public EnvLogger(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void logActiveProfiles() {
        String[] profiles = environment.getActiveProfiles();
        if (profiles.length == 0) {
            String message = "🔹 No active profile set. Using default configuration.";
            log.info(message);
            System.out.println(message);
        } else {
            String message = "🔹 Active Profiles: " + String.join(", ", profiles);
            log.info(message);
            System.out.println(message);
        }
    }
}