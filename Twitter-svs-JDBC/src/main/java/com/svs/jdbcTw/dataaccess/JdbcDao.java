package com.svs.jdbcTw.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.svs.jdbcTw.domain.Tweet;

public class JdbcDao implements PersistanceDao {

	private Connection connectToDB() throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/TwitterJDBC";
		String username = "postgres";
		String password = "postgressvs";
		return DriverManager.getConnection(url, username, password);
	}

	public void saveTweet(Tweet t) {
		Connection c = null;
		try {
			c = connectToDB();
			PreparedStatement st = c
					.prepareStatement("insert into tweet (content, username, timestamp) values (?, ?, ?)");

			st.setString(1, t.getContent());
			st.setString(2, t.getUsername());
			st.setLong(3, t.getTimestamp());

			st.execute();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void listTweets() {
		Connection c = null;
		try {
			c = connectToDB();
			String sqlq = "Select * from tweet";
			Statement statement = null;
			try {
				statement = c.createStatement();
				ResultSet rs = statement.executeQuery(sqlq);

				while (rs.next()) {
					long id = rs.getLong("id");
					String content = rs.getString("content");
					String username = rs.getString("username");
					long ms = rs.getLong("timestamp");
					Timestamp t = new Timestamp(ms);
					System.out.println(id + " | " + content + " | " + username + " | " + t.toString());
				}
				statement.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public List<Tweet> getTweetList() {
		Connection c = null;
		List<Tweet> allTweets = new ArrayList<Tweet>();
		try {
			c = connectToDB();
			String sqlq = "Select * from tweet";
			Statement statement = null;
			try {
				statement = c.createStatement();
				ResultSet rs = statement.executeQuery(sqlq);

				while (rs.next()) {
					long id = rs.getLong("id");
					String content = rs.getString("content");
					String username = rs.getString("username");
					long ms = rs.getLong("timestamp");
					Timestamp t = new Timestamp(ms);
					Tweet tw = new Tweet();
					tw.setContent(content);
					tw.setUsername(username);
					tw.setId(id);
					tw.setTimestamp(ms);
					allTweets.add(tw);
					System.out.println("added to List");
				}
				statement.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return allTweets;
	}

}
