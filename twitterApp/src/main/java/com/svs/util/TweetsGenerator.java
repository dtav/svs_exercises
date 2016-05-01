package com.svs.util;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomUtils;

import com.svs.domain.Member;
import com.svs.domain.Tweet;

public class TweetsGenerator {

	public static List<Tweet> generateTweets(int n){
		Random rand = new Random();
		int userSerial = rand.nextInt(99999);
		List<Tweet> generatedList = new ArrayList<Tweet>();
		Member member = new Member();
		member.setUsername("Auto_Generator"+userSerial);
		for (int i = 0 ; i < n; i ++){
			Tweet tweet = new Tweet();
			Date date = new Date(Math.abs(System.currentTimeMillis() - RandomUtils.nextLong(0, 1000000000L)));
			Timestamp timestamp = new Timestamp(date.getTime());
			tweet.setContent("Auto-Generated tweet no. "+i);
			tweet.setTimestamp(timestamp);
			tweet.setMember(member);
			generatedList.add(tweet);
			System.out.println("generated tweet number: "+i);
		}
		
		return generatedList;
	
}
}
