package creational_dp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class JDBCSingleton {
	// Step 1
	// create a JDBCSingleton class.
	// static member holds only one instance of the JDBCSingleton class.

	private static JDBCSingleton jdbc;

	// JDBCSingleton prevents the instantiation from any other class.
	private JDBCSingleton() {
	}

	public static JDBCSingleton getInstance() {
		if (jdbc == null) {
			jdbc = new JDBCSingleton();
		}
		return jdbc;
	}

	// to get the connection from methods like insert, view etc.
	private static Connection getConnection() throws ClassNotFoundException, SQLException {

		Connection con = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521", "rahul", "system");
		return con;

	}

	// to insert the record into the database
	public int insert(String name, String pass) throws SQLException {
		Connection c = null;

		PreparedStatement ps = null;

		int recordCounter = 0;

		try {

			c = this.getConnection();
			ps = c.prepareStatement("insert into userdata(uname,upassword)values(?,?)");
			ps.setString(1, name);
			ps.setString(2, pass);
			recordCounter = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (c != null) {
				c.close();
			}
		}
		return recordCounter;
	}

	// to view the data from the database
	public void view(String name) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = this.getConnection();
			ps = con.prepareStatement("select * from userdata where uname=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Name= " + rs.getString(2) + "\t" + "Paasword= " + rs.getString(3));

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	// to update the password for the given username
	public int update(String name, String password) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;

		int recordCounter = 0;
		try {
			c = this.getConnection();
			ps = c.prepareStatement(" update userdata set upassword=? where uname='" + name + "' ");
			ps.setString(1, password);
			recordCounter = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (ps != null) {
				ps.close();
			}
			if (c != null) {
				c.close();
			}
		}
		return recordCounter;
	}

	// to delete the data from the database
	public int delete(int userid) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		int recordCounter = 0;
		try {
			c = this.getConnection();
			ps = c.prepareStatement(" delete from userdata where uid='" + userid + "' ");
			recordCounter = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (c != null) {
				c.close();
			}
		}
		return recordCounter;
	}
}

public class SingletonPattern {

	static int count = 1;
	static int choice;

	public static void main(String[] args) throws IOException {

		JDBCSingleton jdbc = JDBCSingleton.getInstance();

		Scanner scan=new Scanner(System.in);
		do {
			System.out.println("DATABASE OPERATIONS");
			System.out.println(" --------------------- ");
			System.out.println(" 1. Insertion ");
			System.out.println(" 2. View      ");
			System.out.println(" 3. Delete    ");
			System.out.println(" 4. Update    ");
			System.out.println(" 5. Exit      ");

			System.out.print("\n");
			System.out.print("Please enter the choice what you want to perform in the database: ");

			try {
				choice = scan.nextInt();
			} catch (NumberFormatException e) {
				System.out.println("Invalid Entry.");
			}
			switch (choice) {

			case 1: {
				System.out.print("Enter the username you want to insert data into the database: ");
				String username = scan.nextLine();
				System.out.print("Enter the password you want to insert data into the database: ");
				String password = scan.nextLine();

				try {
					int i = jdbc.insert(username, password);
					if (i > 0) {
						System.out.println((count++) + " Data has been inserted successfully");
					} else {
						System.out.println("Data has not been inserted ");
					}

				} catch (Exception e) {
					System.out.println(e);
				}

				System.out.println("Press Enter key to continue...");
				System.in.read();

			} // End of case 1
				break;
			case 2: {
				System.out.print("Enter the username : ");
				String username = scan.nextLine();

				try {
					jdbc.view(username);
				} catch (Exception e) {
					System.out.println(e);
				}
				System.out.println("Press Enter key to continue...");
				System.in.read();

			} // End of case 2
				break;
			case 3: {
				System.out.print("Enter the userid,  you want to delete: ");
				int userid = scan.nextInt();

				try {
					int i = jdbc.delete(userid);
					if (i > 0) {
						System.out.println((count++) + " Data has been deleted successfully");
					} else {
						System.out.println("Data has not been deleted");
					}

				} catch (Exception e) {
					System.out.println(e);
				}
				System.out.println("Press Enter key to continue...");
				System.in.read();

			} // End of case 3
				break;
			case 4: {
				System.out.print("Enter the username,  you want to update: ");
				String username = scan.nextLine();
				System.out.print("Enter the new password ");
				String password = scan.nextLine();

				try {
					int i = jdbc.update(username, password);
					if (i > 0) {
						System.out.println((count++) + " Data has been updated successfully");
					}

				} catch (Exception e) {
					System.out.println(e);
				}
				System.out.println("Press Enter key to continue...");
				System.in.read();

			} // end of case 4
				break;

			default:
				return;
			}

		} while (choice != 4);
	}
}
