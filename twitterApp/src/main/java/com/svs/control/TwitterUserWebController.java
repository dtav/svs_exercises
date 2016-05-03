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
@RequestMapping("/members")
public class TwitterUserWebController {

	@Autowired
	private TwitterService twitterService;

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "members";
	}

	@ModelAttribute("member")
	public Member member() {
		return new Member();
	}

	@ModelAttribute("members")
	public List<Member> members() {
		List<Member> listOfMembers = twitterService.getMemberList();
		
		return listOfMembers;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerMember(@ModelAttribute("member") Member member) {
		Member newMember = new Member();
		newMember.setUsername(member.getUsername());		
		return "redirect:/members";
	}

	

	@RequestMapping(value = "/deleteMember/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") long id) {
		Member m = new Member();
		m.setId(id);
		
		this.twitterService.removeAllTweetsByMember(m);
		this.twitterService.removeMember(m);
		System.out.println("REMOVED MEMBER");
		return "redirect:/members";
	}

//	@RequestMapping(value = "/editTweet/{id}", method = RequestMethod.GET)
//	public String editTweet(@PathVariable("id") Long id, Model model) {
//		final Tweet tweet = twitterService.getTweetById(id);
//		model.addAttribute("tweet", tweet);
//		return "tweets";
//	}

}
