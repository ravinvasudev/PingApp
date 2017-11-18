package com.docler.ping.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * A class for application configuration. It creates an instance for
 * {@link AppProperties} and {@link newScheduledThreadPool} to be shared among
 * services.
 * 
 * 
 * @author Ravin Vasudev
 * @version 1.0
 */
@Configuration
public class AppConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public static AppProperties appProperties() {
		return new AppProperties();
	}

	@Bean
	public ScheduledExecutorService scheduledExecutorService(@Value("${threads?:10}") Integer threads) {
		return Executors.newScheduledThreadPool(threads);
	}

}
