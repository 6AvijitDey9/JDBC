package Com.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Helper {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static Connection con() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/avijit_dey", "root", "root");
	}

	public static void main(String[] args) throws SQLException {
		MenuDriven md = new MenuDriven();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter 1 to continue inserting data values or, enter 2 to stop and execute");
		while(sc.nextInt()!=2) {
			md.saveMenuDriven();
			System.out.println("Press 1 to continue or, Press 2 to Stop");}
			md.fetchMenuDriven();
			md.updateMenuDriven();
			md.deleteMenuDriven();
		}
	}

