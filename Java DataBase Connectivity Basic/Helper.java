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
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/avijit_dey", "root", "root"); //Selecting path with username and password
	}

	public static void main(String[] args) throws SQLException {
		MenuDriven md = new MenuDriven(); //Creating MenuDriven class object
		Scanner sc = new Scanner(System.in);
		System.out.println("(Press 1 for Save)(Press 2 for Fetch)(Press 3 for Update)(Press 4 for delete)");
		int var = sc.nextInt();
		if(var==1) {
			md.saveMenuDriven();} //Executing opeartion of MenuDriven class in main method
		if(var==2) {
			md.fetchMenuDriven();}
		if(var==3) {
			md.updateMenuDriven();}
		if(var==4) {
			md.deleteMenuDriven();}
		}
	}

