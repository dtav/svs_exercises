package library;

import java.sql.*;

public class DDL_book {
	Connection connection;
	final static String url = "jdbc:postgresql://localhost:5432/testing";
	final static String username = "postgres";
	final static String password = "postgressvs";

	
	public Connection getConnection(){
		return this.connection;
	}
	
	public boolean createConnection() throws SQLException {
		this.connection = DriverManager.getConnection(this.url, this.username, this.password);
		if (this.connection != null) {
			return true;
		}
		return false;
	}

	// auto
	public void createTable() {
		Statement creationStatement = null;
		try {
			creationStatement = this.connection.createStatement();
		} catch (SQLException e) {
			System.out.println("Connection not available, call createConnection() first");
			e.printStackTrace();
		}
		String creationQuery = "CREATE TABLE BOOK ( id INTEGER, isbn VARCHAR(13), title VARCHAR, PRIMARY KEY (id))";
		try {
			creationStatement.executeUpdate(creationQuery);
			System.out.println("Successfully created the Book table");
		} catch (SQLException e) {
			System.out.println("Statement was not executed, check if table exists!");
			e.printStackTrace();
		} finally {
			try {
				creationStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
