package com.svs.dataaccess;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.svs.domain.Member;
import com.svs.domain.Tweet;
import com.svs.domain.TweetSimple;

@Component
public class HibernateDao implements PersistenceDao {
	SessionFactory sessionFactory;

	@Autowired
	public HibernateDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Tweet> getTweetList() {
		
		Session s = null;
		List<Tweet> results = new ArrayList<Tweet>();
		try {
			s = this.sessionFactory.openSession();
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

	@Override
	public List<Tweet> getTweetListByUserName(String username) {
		List<Tweet> allTweets = getTweetList();
		List<Tweet> filteredTweets = new ArrayList<Tweet>();
		ListIterator<Tweet> iterateAll = allTweets.listIterator();
		while (iterateAll.hasNext()) {
			Tweet current = iterateAll.next();
			long CurrId = current.getMember().getId();
			String CurrUsername = getUsernameById(CurrId);
			// System.out.println(current.toString());
			// System.out.println(CurrUsername);
			if (CurrUsername.equals(username)) {
				filteredTweets.add(current);
			}
		}
		return filteredTweets;
	}

	@Override
	public String getUsernameById(long id) {
		Session s = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			List<Member> results;
			Query q = s.createQuery("from Member");
			results = q.list();
			ListIterator<Member> iterateMembers = results.listIterator();
			while (iterateMembers.hasNext()) {
				Member m = iterateMembers.next();
				if (m.getId() == id) {
					return m.getUsername();
				}
			}
			tx.commit();

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			s.close();
		}
		return null;

	}

	@Override
	public List<Tweet> getTweetListLaterThan(Date d) {
		Session s = sessionFactory.openSession();
		Transaction tx = null;
		List<Tweet> filteredResults = new ArrayList<Tweet>();
		try {
			tx = s.beginTransaction();
			List<Tweet> results;
			Query q = s.createQuery("from Tweet");
			results = q.list();
			ListIterator<Tweet> iterateTweets = results.listIterator();
			while (iterateTweets.hasNext()) {
				Tweet currTweet = iterateTweets.next();
				Timestamp currTimestamp = currTweet.getTimestamp();
				if (currTimestamp.getTime() >= d.getTime()) {
					filteredResults.add(currTweet);
				}

			}

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			s.close();
		}
		return filteredResults;

	}

	@Override
	public List<TweetSimple> listSimpleTweets(List<Tweet> normalTweets) {
		List<TweetSimple> twSimple = new ArrayList<TweetSimple>();
		ListIterator<Tweet> liNormal = normalTweets.listIterator();
		while (liNormal.hasNext()){
			Tweet tn = liNormal.next();
			TweetSimple ts = new TweetSimple();
			ts.setContent(tn.getContent());
			ts.setId(tn.getId());
			ts.setMember_id(tn.getMember().getId());
			ts.setTimestamp(tn.getTimestamp());
			twSimple.add(ts);			
		}
		return twSimple;
	}

	@Override
	public void removeTweet(Tweet t) {
		Session s = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(t);
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
