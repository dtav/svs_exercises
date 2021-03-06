package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domain.Book;
import domain.Magazine;
import domain.Publication;

public class JDBCBookDao implements PublicationDao {

	public JDBCBookDao() {

	}

	private Connection connectToDB() throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/libraryJDBC_Hibernate";
		String username = "postgres";
		String password = "postgressvs";
		return DriverManager.getConnection(url, username, password);
	}

	public void registerBook(Book b) {
		Connection c = null;
		try {
			c = connectToDB();
			PreparedStatement registrationStatement = c
					.prepareStatement("insert into book (isbn, title) values (?, ?)");

			registrationStatement.setString(1, b.getIsbn());
			registrationStatement.setString(2, b.getTitle());

			registrationStatement.execute();
			registrationStatement.close();

			System.out.println("registered! ");
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

	public void registerMagazine(Magazine m) {
		Connection c = null;
		try {
			c = connectToDB();
			PreparedStatement registrationStatement = c
					.prepareStatement("insert into magazine (issn, title) values (?, ?)");

			registrationStatement.setString(1, m.getIssn());
			registrationStatement.setString(2, m.getTitle());

			registrationStatement.execute();
			registrationStatement.close();

			System.out.println("registered! ");
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

	public void unregisterBook(Book b) {
		Connection c = null;
		try {
			c = connectToDB();
			PreparedStatement unregisterStatement = c.prepareStatement("delete from book where isbn = ? and title = ?");
			unregisterStatement.setString(1, b.getIsbn());
			unregisterStatement.setString(2, b.getTitle());

			int i = unregisterStatement.executeUpdate();

			unregisterStatement.close();

			if (i > 0) {
				System.out.println("unregistered! ");
			} else {
				System.out.println("no such book");
			}
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

	
	public void unregisterMagazine(Magazine m) {
		Connection c = null;
		try {
			c = connectToDB();
			PreparedStatement unregisterStatement = c.prepareStatement("delete from magazine where issn = ? and title = ?");
			unregisterStatement.setString(1, m.getIssn());
			unregisterStatement.setString(2, m.getTitle());

			int i = unregisterStatement.executeUpdate();

			unregisterStatement.close();

			if (i > 0) {
				System.out.println("unregistered! ");
			} else {
				System.out.println("no such magazine");
			}
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
	public void listBooks() {
		Connection c = null;
		try {
			c = connectToDB();
			String sqlq = "Select * from book";
			Statement statement = null;
			try {
				statement = c.createStatement();
				ResultSet rs = statement.executeQuery(sqlq);

				while (rs.next()) {
					int id = rs.getInt("id");
					String isbn = rs.getString("isbn");
					String title = rs.getString("title");
					System.out.println(id + " | " + isbn + " | " + title);
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

	public void listMagazines() {
		Connection c = null;
		try {
			c = connectToDB();
			String sqlq = "Select * from magazine";
			Statement statement = null;
			try {
				statement = c.createStatement();
				ResultSet rs = statement.executeQuery(sqlq);

				while (rs.next()) {
					int id = rs.getInt("id");
					String issn = rs.getString("issn");
					String title = rs.getString("title");
					System.out.println(id + " | " + issn + " | " + title);
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

	public void updateRegistration(Long id, String title) {
		Connection c = null;
		try {
			c = connectToDB();
			String sqlq = "Select * from book";
			Statement statement = null;
			try {
				statement = c.createStatement();
				ResultSet rs = statement.executeQuery(sqlq);

				while (rs.next()) {
					if (id == rs.getLong("id")) {
						String isbn = rs.getString("isbn");
						String newTitle = title;
						Book newBook = new Book(isbn, newTitle);
						newBook.setId(id);

						PreparedStatement updateStatement = c.prepareStatement("update Book set title = ? where id= ?");

						updateStatement.setString(1, newBook.getTitle());
						updateStatement.setLong(2, newBook.getId());

						updateStatement.executeUpdate();
						updateStatement.close();

					}
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

	public void listPublications() {
		System.out.println("===== Books =====");
		listBooks();
		System.out.println("===== Magazines =====");
		listMagazines();
		System.out.println("=====================");

	}

	// Unimplemented methods are for Hibernate and would take unreasonable time
	// to implement them in JDBC too.

	public void unregisterPublication(Publication p) {
		// TODO Auto-generated method stub

	}

	public void updateRegistration(Book b1, Book b2) {
		// TODO Auto-generated method stub

	}

	public void updateRegistration(Magazine m1, Magazine m2) {
		// TODO Auto-generated method stub

	}

	public boolean validateEntry(Publication p) {
		// TODO Auto-generated method stub
		return false;
	}

	public long getIdFromTitle(Publication p) {
		// TODO Auto-generated method stub
		return 0;
	}


}
