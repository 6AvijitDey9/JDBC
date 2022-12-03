package Com.Student.fetch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Com.Student.helper.Help_assignment;

public class fetch_table {
	//fetching employee details in the database (display)
			public void fetchMenuDrivenn() throws SQLException{
				Connection conn = Help_assignment.con();
				Statement stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery("select * from MenuDrivenn");
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getInt(5));			
				}	
			}
}
