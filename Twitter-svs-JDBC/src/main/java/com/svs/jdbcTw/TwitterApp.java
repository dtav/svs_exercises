package com.svs.jdbcTw;

import com.svs.jdbcTw.control.TwitterController;
import com.svs.jdbcTw.dataaccess.JdbcDao;
import com.svs.jdbcTw.service.TwitterService;

public class TwitterApp {
	
	
	public static void main(String[] args) {
		TwitterController tc = new TwitterController(new TwitterService(new JdbcDao()));
		tc.exec();
	}
	
	

}
