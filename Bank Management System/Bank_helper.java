package Com.Bank.helper;
import java.sql.*; 
	public class Bank_helper {
		static {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		public static Connection con() throws SQLException{
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/assignments", "root", "root");
		}
}
