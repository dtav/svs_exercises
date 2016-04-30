package com.svs.dataaccess;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.svs.domain.Tweet;
import com.svs.domain.User;

@Component
public class HibernateDao implements PersistenceDao {
	SessionFactory sessionFactory;
	
	@Autowired
	public HibernateDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	


	public String retreiveTweet() {
		// TODO Auto-generated method stub
		return null;
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

	public List<Tweet> getTweetListDate(Date d) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Tweet> getTweetByUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void saveTweet(String content, String username) {
		Tweet t = new Tweet(content, username);
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



	@Override
	public Tweet getTweetByID(Long id) {
		List<Tweet> ls = getTweetList();
		ListIterator<Tweet> lsIterator = ls.listIterator();
		while (lsIterator.hasNext()){
			Tweet tw = lsIterator.next();
			if (tw.getId() == id){
				return tw;
			}
			
		}
		return null;
	}

}
