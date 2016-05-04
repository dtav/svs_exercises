package com.librarySpring.dataaccess;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.librarySpring.domain.Book;
import com.librarySpring.domain.Loan;
import com.librarySpring.domain.Magazine;
import com.librarySpring.domain.Member;
import com.librarySpring.domain.Membership;
import com.librarySpring.domain.Publication;

@Configuration
public class HibernateConfiguration {

	@Bean
	public SessionFactory sessionFactory(){
		org.hibernate.cfg.Configuration conf = new org.hibernate.cfg.Configuration();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties())
				.build();
		SessionFactory sf = conf.addAnnotatedClass(Book.class).addAnnotatedClass(Magazine.class)
				.addAnnotatedClass(Publication.class).addAnnotatedClass(Member.class).addAnnotatedClass(Membership.class).addAnnotatedClass(Loan.class).buildSessionFactory(serviceRegistry);

		
		
		
		return sf;
		
	}
	
}
