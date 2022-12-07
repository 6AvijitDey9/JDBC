package Com.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;

public class MenuDriven {
		Scanner sc =new Scanner(System.in);
		int e_id, e_age, e_salary;
		String e_name, e_city;
		public void saveMenuDriven() throws SQLException{
			System.out.println("Enter Emp ID: ");
			e_id = sc.nextInt();
			System.out.println("Enter Emp name: ");
			e_name = sc.next();
			System.out.println("Enter Emp age: ");
			e_age = sc.nextInt();
			System.out.println("Enter Emp city: ");
			e_city = sc.next();
			System.out.println("Enter Emp salary: ");
			e_salary = sc.nextInt();
			Connection conn = Helper.con();
			PreparedStatement stmt = conn.prepareStatement("Insert into MenuDriven values(?,?,?,?,?)");
			stmt.setInt(1, e_id);
			stmt.setString(2, e_name);
			stmt.setInt(3, e_age);
			stmt.setString(4, e_city);
			stmt.setInt(5, e_salary);
			stmt.executeUpdate();
		}
		public void fetchMenuDriven() throws SQLException{
			Connection conn = Helper.con();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from MenuDriven");
			while (rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getInt(5));
			}
		}
		public void updateMenuDriven() throws SQLException{
			Connection conn = Helper.con();
			Statement stmt = conn.createStatement();
			System.out.println("Enter employee City: ");
			e_city = sc.next();
			stmt.executeUpdate("update MenuDriven set e_city= '"+e_city+"' where e_id= "+e_id);
		}
		public void deleteMenuDriven() throws SQLException{
			Connection conn = Helper.con();
			Statement stmt = conn.createStatement();
			System.out.println("Enter employee Id: ");
			e_id = sc.nextInt();
			stmt.executeUpdate("Delete from MenuDriven where e_id= "+e_id);
		}
	}


