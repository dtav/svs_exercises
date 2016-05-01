package com.svs.control;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.metamodel.relational.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.svs.domain.Member;
import com.svs.domain.Tweet;
import com.svs.service.TwitterService;
import com.svs.util.TimeUtils;
import com.svs.util.TweetsGenerator;

@Component
public class TwitterController {
	TwitterService twService;

	@Autowired
	public TwitterController(TwitterService twService) {
		this.twService = twService;
	}

	public void exec() throws ParseException {
		Scanner sc = new Scanner(System.in);
		String inputString;
		Integer inputInteger;
		boolean loggedIn = false;
		Member member = null;
		while (true) {
			if (!loggedIn) {
				printMenu();
				inputString = sc.nextLine();
				inputInteger = NumberUtils.toInt(inputString, 5);
				switch (inputInteger) {
				case 1:
					String userName = memberPrompt(sc);
					loggedIn = true;
					member = new Member();
					member.setUsername(userName);
					break;
				case 2:
					String userEmail = emailPrompt(sc);
					loggedIn = true;
					member = new Member();
					member.setEmail(userEmail);
					break;
				case 3:
					List<Tweet> receivedTweetList = twService.getTweetList();
					Collections.sort(receivedTweetList, (o1, o2) -> o1.getTimestamp().compareTo(o2.getTimestamp()));
					Collections.reverse(receivedTweetList);
					printListOfTweets(receivedTweetList);
					break;
				case 4:
					System.out.println("Input \"day, month\" or just \"day\" : ");
					String chosenTime = sc.nextLine();
					Date d = TimeUtils.getFullDateFromDay(chosenTime);
					printListOfTweets(twService.getTweetListLaterThan(d));
					break;
				case 5:
					System.out.println("Input number of tweets to be auto generated: ");
					String num = sc.nextLine();
					int n = NumberUtils.toInt(num, 0);
					List<Tweet> autoGeneratedTweetsList = TweetsGenerator.generateTweets(n);
					ListIterator<Tweet> iterateAutoGen = autoGeneratedTweetsList.listIterator();
					while(iterateAutoGen.hasNext()){
						Tweet tweet = iterateAutoGen.next();
						twService.saveTweet(tweet);
					}
					System.out.println("WOW, done!");
					break;
				case 6:
					System.exit(0);
				default:
					System.out.println("Wrong input, try again");
					break;

				}

			} else {
				System.out.println("logged in out of loop");
				printSubMenu(member.getUsername());
				inputString = sc.nextLine();
				inputInteger = NumberUtils.toInt(inputString, 5);
				switch (inputInteger) {
				case 1:
					System.out.println("Input tweet content:");
					String tweetContent = sc.nextLine();
					Tweet tweet = new Tweet(tweetContent, member);
					twService.saveTweet(tweet);					
					break;
				case 2:
					printListOfTweets(twService.getTweetListByUserName(member.getUsername()));
					break;
				case 3:
					loggedIn = false;
					break;
				default:
					System.out.println("Wrong input, try again");
					break;
				}
			}

		}

	}

	public void printMenu() {
		StringBuilder sbMenu = new StringBuilder();

		sbMenu.append("**********\n");
		sbMenu.append("1. Log in with username\n");
		sbMenu.append("2. Log in with e-mail address\n");
		sbMenu.append("3. List all tweets\n");
		sbMenu.append("4. List all tweets after\n");
		sbMenu.append("5. Auto generate tweets\n");
		sbMenu.append("6. Exit\n");
		sbMenu.append("**********\n");
		sbMenu.append("Your choice: ");

		System.out.println(sbMenu.toString());
	}

	public void printSubMenu(String member) {
		StringBuilder sbSub = new StringBuilder();

		sbSub.append("**** Currently logged in: " + member + " ****\n");
		sbSub.append("1. Add new Tweet\n");
		sbSub.append("2. List your tweets\n");
		sbSub.append("3. Log out\n");
		sbSub.append("************\n");
		sbSub.append("Your choice: ");

		System.out.println(sbSub.toString());
	}

	public String memberPrompt(Scanner s) {
		System.out.println("Please enter your username: ");
		String username = s.nextLine();
		return username;
	}

	public String emailPrompt(Scanner s) {
		System.out.println("Please enter your e-mail address: ");
		String email = s.nextLine();
		return email;
	}

	public void printListOfTweets(List<Tweet> ls) {
		ListIterator<Tweet> twIterator = ls.listIterator();
		StringBuilder twTableString = new StringBuilder();
		String header = Tweet.getHeader();
		twTableString.append(header);
		while (twIterator.hasNext()) {
			Tweet t = twIterator.next();
			String row = t.toString();
			twTableString.append(row);
			twTableString.append("\n");
		}
		System.out.println(twTableString.toString());
	}

}
