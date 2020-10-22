package com.sdbawa.mmts.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author simar bawa
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.sdbawa.mmts.domain")
@EnableJpaAuditing
@EnableAsync
public class ApplicationConfig {
}
