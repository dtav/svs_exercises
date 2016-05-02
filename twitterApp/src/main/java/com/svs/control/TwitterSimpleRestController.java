package com.svs.control;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.svs.domain.Tweet;
import com.svs.domain.TweetSimple;
import com.svs.service.TwitterService;

@RestController
@RequestMapping("/api/simple")
public class TwitterSimpleRestController {

	@Autowired
	TwitterService twitterService;
	
	//Why don't I just use List<Tweet> ??
	//Because Tweet has a field Member in it, and rest just loops recursively Tweet >member >Tweet list > Tweet ...
	//TweetSimple is a mapping Tweet --> TweetSimple where Member field is just exchanged with member_id field of simple type long
	//fix ASAP
	
	//fixed in com.svs.control.TwitterRestController
	
	@RequestMapping(method = RequestMethod.GET)
	public List<TweetSimple> listTweets() {

		List<TweetSimple> listOfTweets = twitterService.listSimpleTweets(twitterService.getTweetList());
		
		Collections.sort(listOfTweets, (o1, o2) -> o1.getTimestamp().compareTo(o2.getTimestamp()));
		Collections.reverse(listOfTweets);
		return listOfTweets;

	}
	
	//dummy copy
	@RequestMapping(method=RequestMethod.POST)
	public Tweet registerTweet(@RequestBody Tweet tweet){
		twitterService.saveTweet(tweet);
		return tweet;
	}


	

}
