package Com.Student.helper;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import Com.Student.fetch.fetch_table;
import Com.Student.insert.insert_table;
import Com.Student.update.update_table;

public class Help_assignment {
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

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		insert_table it = new insert_table();
		fetch_table ft = new fetch_table();
		update_table ut = new update_table();
		System.out.println("1 for Insert :\n2 for Display :\n3 for Update :\n4 for Delete :\n5 for Exit :");
		int variable = sc.nextInt();
		System.out.println("-------------------------------");
		switch(variable) {
		case 1 : 
		  it.saveMenuDrivenn();
		  break;
		case 2 :  
			ft.fetchMenuDrivenn();
			break;
		case 3 :
			ut.updateMenuDrivenn();
			break;
		case 4 :
			ut.deleteMenuDrivenn();
			break;
		case 5 :
			System.exit(0);
			break;
		}
	}}

