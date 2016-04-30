package com.svs.control;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.svs.domain.Tweet;
import com.svs.domain.User;
import com.svs.service.TwitterService;

@Component
public class TwitterController {
	TwitterService twService;

	@Autowired
	public TwitterController(TwitterService twService) {
		this.twService = twService;
	}

	public void exec() {
		Scanner sc = new Scanner(System.in);
		String inputString;
		Integer inputInteger;
		boolean loggedIn = false;
		User user = new User();
		while (true) {
			if (!loggedIn) {
				printMenu();
				inputString = sc.nextLine();
				inputInteger = NumberUtils.toInt(inputString, 5);
				switch (inputInteger) {
				case 1:
					String userName = userPrompt(sc);
					loggedIn = true;
					user.setNick(userName);
					break;
				case 2:
					String userEmail = emailPrompt(sc);
					loggedIn = true;
					user.setNick(userEmail);
					break;
				case 3:
					List<Tweet> receivedTweetList = twService.getTweetList();
					Collections.reverse(receivedTweetList);
					printListOfTweets(receivedTweetList);
					break;
				case 4:
					System.exit(0);
				default:
					System.out.println("Wrong input, try again");
					break;

				}

			} else {
				System.out.println("logged in out of loop");
				printSubMenu(user.getNick());
				inputString = sc.nextLine();
				inputInteger = NumberUtils.toInt(inputString, 5);
				switch (inputInteger) {
				case 1:
					System.out.println("Input tweet content:");
					String tweetContent = sc.nextLine();
					String tweetUser = user.getNick();
					twService.saveTweet(tweetContent, tweetUser);
					break;
				case 2:
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
		sbMenu.append("4. Exit\n");
		sbMenu.append("**********\n");
		sbMenu.append("Your choice: ");

		System.out.println(sbMenu.toString());
	}

	public void printSubMenu(String user) {
		StringBuilder sbSub = new StringBuilder();

		sbSub.append("**** Currently logged in: " + user + "****\n");
		sbSub.append("1. Add new Tweet\n");
		sbSub.append("2. List your tweets\n");
		sbSub.append("3. Log out\n");
		sbSub.append("************\n");
		sbSub.append("Your choice: ");

		System.out.println(sbSub.toString());
	}

	public String userPrompt(Scanner s) {
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
		}
		System.out.println(twTableString.toString());
	}

}