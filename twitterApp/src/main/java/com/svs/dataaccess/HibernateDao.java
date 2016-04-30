package com.svs.dataaccess;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.svs.domain.Tweet;

@Component
public class HibernateDao implements PersistenceDao {
	SessionFactory sessionFactory;
	
	@Autowired
	public HibernateDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<Tweet> getTweetList() {
		Session s = null;
		List<Tweet> results = null;
		try {
			s = sessionFactory.openSession();
			Query q = s.createQuery("from Tweet");
			results = q.list();
			return results;
		} catch (RuntimeException re) {
			re.printStackTrace();
		} finally {
			s.close();
		}
		return results;
		
	}

	
	@Override
	public void saveTweet(Tweet t) {
		Session s = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(t);
			tx.commit();

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			s.close();
		}
		
	}


}
