package dataaccess;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import domain.Book;
import domain.Magazine;
import domain.Publication;

@Configuration
public class HibernateConfiguration {

	@Bean
	public SessionFactory sessionFactory(){
		org.hibernate.cfg.Configuration conf = new org.hibernate.cfg.Configuration();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties())
				.build();
		SessionFactory sf = conf.addAnnotatedClass(Book.class).addAnnotatedClass(Magazine.class)
				.addAnnotatedClass(Publication.class).buildSessionFactory(serviceRegistry);

		
		
		
		return sf;
		
	}
}
