package Com.Student.insert;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Scanner;
    import Com.Student.helper.Help_assignment;

	public class insert_table {
		Scanner sc = new Scanner(System.in);
		int e_id,e_age,e_salary;
		String e_name, e_city;
		//saving employee details in database
		public void saveMenuDrivenn()throws SQLException{
	    	System.out.println("Enter employee id :");
	    	e_id=sc.nextInt();
	    	System.out.println("enter employee name :");
	    	e_name=sc.next();
	    	System.out.println("Enter employee age :");
	    	e_age=sc.nextInt();
	    	System.out.println("enter employee city :");
	    	e_city=sc.next();
	    	System.out.println("Enter employee salary :");
	    	e_salary=sc.nextInt();
	    	Connection conn=Help_assignment.con();
	    	PreparedStatement stmt=conn.prepareStatement("insert into Menudrivenn values(?,?,?,?,?)");
	    	stmt.setInt(1, e_id);
	    	stmt.setString(2, e_name);
	    	stmt.setInt(3, e_age);
	    	stmt.setString(4, e_city);
	    	stmt.setInt(5, e_salary);
	    	stmt.executeUpdate();
	    	ResultSet rs=stmt.executeQuery("select * from MenuDrivenn");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getInt(5));			
			}	
			
		}
	}

