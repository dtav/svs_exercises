package library;

//
//+ updateRegistration using Publications
//v0.3
//+ toString implementation
//+ cleanTables implementation ?

import java.util.logging.Level;

import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class Library implements CommandLineRunner, ApplicationContextAware {

	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

		SpringApplication.run(Library.class, args);

	}

	@Override
	public void run(String... arg0) throws Exception {
		applicationContext.getBean(LibraryController.class).exec();

	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;

	}

}
