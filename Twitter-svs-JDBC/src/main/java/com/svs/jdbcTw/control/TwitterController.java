package com.svs.jdbcTw.control;

import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import com.svs.jdbcTw.domain.Tweet;
import com.svs.jdbcTw.service.TwitterService;

public class TwitterController {

	TwitterService ts;

	public TwitterController(TwitterService ts) {
		this.ts = ts;
	}

	public void exec() {
		Scanner s = new Scanner(System.in);

		while (true) {
			printMenu();
			String input = s.nextLine();
			int choice = NumberUtils.toInt(input, 0);
			if (choice == 1) {
				System.out.println("Enter your username: ");
				String username = s.nextLine();
				System.out.print("Enter your tweet: ");
				String content = s.nextLine();
				Tweet t = new Tweet(content, username);
				ts.saveTweet(t);
				System.out.println("Tweet saved!");

			} else if (choice == 2) {
				ts.listTweets();
			} else if (choice == 3) {
				System.out.println("Exiting, bye");
				s.close();
				System.exit(0);
			} else {
				System.out.println("Wrong input, try again");
			}
		}
	}

	public void printMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("1. New tweet\n");
		sb.append("2. List tweets\n");
		sb.append("3. Exit\n");
		sb.append("Your Choice: ");

		System.out.print(sb.toString());
	}

}
