package book_table_create;

import java.sql.*;

public class book_table_create {

	public static void main(String[] args) throws ClassNotFoundException {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.out.println("postgresql driver problem!!!");
			e.printStackTrace();
		}

		try {
			String url = "jdbc:postgresql://localhost:5432/testing";
			String username = "postgres";
			String password = "postgressvs";

			Connection conn = DriverManager.getConnection(url, username, password);

			if (conn != null) {
				System.out.println("Connection successfull \n");
			}

			String sqlq = "CREATE TABLE BOOK " + " (id INTEGER not NULL," + " isbn VARCHAR(13), "
					+ " title VARCHAR(255), " + " PRIMARY KEY (id))";

			Statement st = conn.createStatement();
			st.executeUpdate(sqlq);
			st.close();

		} catch (SQLException e) {
			System.out.println("Connection failed \n");
			e.printStackTrace();
		}
	}

}
