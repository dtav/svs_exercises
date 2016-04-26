package com.librarySpring;

import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;

import com.librarySpring.control.LibraryController;

@SpringBootApplication
@ComponentScan

public class LibraryApplication implements CommandLineRunner, ApplicationContextAware {

	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		// java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;
	}

	@Override
	public void run(String... arg0) throws Exception {
		applicationContext.getBean(LibraryController.class).exec();
	}

}
