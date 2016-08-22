package com.brogrammer.spring.cloud.client.task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

/**
 * @author Pezhman Jahanmard
 */
@SpringBootApplication
@EnableTask
public class TaskClientApplication {
	@Bean
	public CommandLineRunner commandLineRunner() {
		return new HelloWorldCommandLineRunner();
	}

	public static void main(String[] args) {
		SpringApplication.run(TaskClientApplication.class, args);
	}

	public static class HelloWorldCommandLineRunner implements CommandLineRunner {

		@Override
		public void run(String... strings) throws Exception {
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Hello World!");
		}
	}
}
