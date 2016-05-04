package library;

//
//+ updateRegistration using Publications
//v0.3
//+ toString implementation
//+ cleanTables implementation ?

import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import service.LibraryService;
import util.Logger;
import dataaccess.*;
import domain.*;
import javassist.expr.Instanceof;

public class Library {

	public static void main(String[] args) {
		// disabling log info for a while
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		
		Configuration conf = new Configuration();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties())
				.build();
		SessionFactory sf = conf.addAnnotatedClass(Book.class).addAnnotatedClass(Magazine.class)
				.addAnnotatedClass(Publication.class).buildSessionFactory(serviceRegistry);

	

		LibraryService ls = new LibraryService(new JDBCBookDao());
		LibController lc = new LibController(ls);
		
		lc.exec();

	}

}
