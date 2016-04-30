package com.svs.control;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.svs.domain.Tweet;
import com.svs.service.TwitterService;

@RestController
@RequestMapping("/api/tweets")
public class TwitterRestController {

	@Autowired
	TwitterService twitterService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Tweet> listTweets() {

		List<Tweet> listOfTweets = twitterService.getTweetList();
		Collections.reverse(listOfTweets);
		return listOfTweets;

	}


	

}
