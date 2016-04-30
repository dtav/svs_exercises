package com.svs.dataaccess;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.svs.domain.Member;
import com.svs.domain.Tweet;


@Configuration
public class HibernateConfiguration {

	@Bean
	public SessionFactory sessionFactory(){
		org.hibernate.cfg.Configuration conf = new org.hibernate.cfg.Configuration();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties())
				.build();
		SessionFactory sf = conf.addAnnotatedClass(Tweet.class).addAnnotatedClass(Member.class).buildSessionFactory(serviceRegistry);

		
		
		
		return sf;
		
	}
	
}
