package library;

import java.sql.*;


public class LibraryApplication {
	
	public static void main(String[] args) {
		// initializing book table
		DDL_book bookTable = new DDL_book();
		try {
			bookTable.createConnection();
		} catch (SQLException e) {
			System.out.println("Connection to database failed");
			e.printStackTrace();
		}
		bookTable.createTable();
		
		Connection dbConnection = bookTable.getConnection();
		
		

	}
}
