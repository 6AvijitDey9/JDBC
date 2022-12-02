package Com.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Student {
	//register mysql driver
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/avijit_dey", "root", "root");
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from customer");
			//stmt.executeUpdate("insert in customer values('AD01', 'Avijit', 'Dey', 'KA', 7059349, '1956-02-28', 10000.95)");
			if(rs.isClosed()) {
				System.out.println("Connection is closed");
			}
			else {
				System.out.println("Connection is given");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

