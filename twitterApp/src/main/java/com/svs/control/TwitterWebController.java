package com.svs.control;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.svs.domain.Tweet;
import com.svs.service.TwitterService;

@Controller
@RequestMapping("/tweets")
public class TwitterWebController {

	@Autowired
	private TwitterService twitterService;

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "tweets";
	}

	@ModelAttribute("tweet")
	public Tweet tweet() {
		return new Tweet();
	}

	@ModelAttribute("tweets")
	public List<Tweet> tweets() {
		List<Tweet> listOfTweets = twitterService.getTweetList();
		Collections.reverse(listOfTweets);
		return listOfTweets;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerTweet(@ModelAttribute("tweet") Tweet tweet) {
		Tweet tw = new Tweet(tweet.getContent(), tweet.getMember());
		twitterService.saveTweet(tw);
		return "redirect:/tweets";
	}

//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public String editEntry(@PathVariable("id") Long id, Model model) {
//		Tweet tweet = twitterService.getTweetByID(id);
//		if (tweet != null) {
//			System.out.println(tweet.toString());
//		}
//		return "redirect:/books";
//
//	}

}
