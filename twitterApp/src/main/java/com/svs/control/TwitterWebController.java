package com.svs.control;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.svs.domain.Member;
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
	
	
	@ModelAttribute("tweetEdit")
	public Tweet tweetEdit() {
		return new Tweet();
	}

	@ModelAttribute("tweets")
	public List<Tweet> tweets() {
		List<Tweet> listOfTweets = twitterService.getTweetList();
		Collections.sort(listOfTweets, (o1, o2) -> o1.getTimestamp().compareTo(o2.getTimestamp()));
		Collections.reverse(listOfTweets);
		return listOfTweets;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerTweet(@ModelAttribute("tweet") Tweet tweet) {
		System.out.println("VO Register POST");

		Tweet tw = new Tweet(tweet.getContent(), tweet.getMember());
		twitterService.saveTweet(tw);
		return "redirect:/tweets";
	}
	
	@RequestMapping(value="editTweet",method = RequestMethod.POST)
	public String editTweetPost(@ModelAttribute("tweetEdit") Tweet tweet) {
		System.out.println("VO EDIT POST");
		String newContent = tweet.getContent();
		Member sameUser = tweet.getMember();
		Timestamp sameTimestamp = tweet.getTimestamp();
		Tweet newTweet = new Tweet();
		newTweet.setContent(newContent);
		newTweet.setMember(sameUser);
		newTweet.setTimestamp(sameTimestamp);
		newTweet.setId(tweet.getId());		
		twitterService.editTweet(newTweet);
		return "redirect:/tweets";
	}

	@RequestMapping(value="/deleteTweet/{id}", method = RequestMethod.GET)
	public String deleteTweet(@PathVariable("id") long id) {
		Tweet t = new Tweet();
		t.setId(id);
		this.twitterService.removeTweet(t);
		System.out.println("TROLOLOLOLO");
		return "redirect:/tweets";
	}
	
	@RequestMapping(value = "/editTweet/{id}", method = RequestMethod.GET)
	public String editTweet(@PathVariable("id") Long id, Model model) {
		final Tweet tweet = twitterService.getTweetById(id);
		model.addAttribute("tweetEdit", tweet);
		return "tweets";
	}

}
