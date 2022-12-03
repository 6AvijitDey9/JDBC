package Com.Bank.bms;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import Com.Bank.helper.Bank_helper;

class wrongpinexception extends SQLException{
	public wrongpinexception(String str) {
		super(str);
	}
}
class notenoughbalexception extends SQLException{
	public notenoughbalexception(String str) {
		super(str);
	}
}
class acnumberexception extends SQLException{
	public acnumberexception(String str) {
		super(str);
	}
}

public class Bank_bms {
	Scanner sc=new Scanner(System.in);
	int ac_no, pin; double bal, balance; String ac_hol, ifsc, branch, ac_type;
	
	public void create_ac() throws SQLException{
		System.out.println("Please enter your account number: ");
		ac_no = sc.nextInt();
		System.out.println("Please enter account holder UserName: ");
		ac_hol=sc.next();
		System.out.println("Please enter ifsc code: ");
		ifsc=sc.next();
		System.out.println("Please enter your Branch: ");
		branch=sc.next();
		System.out.println("Please enter your account type: ");
		ac_type=sc.next();
		System.out.println("Please enter your current balance: ");
		bal=sc.nextDouble();
		System.out.println("Please enter your Pin: ");
		pin=sc.nextInt();
		Connection conn=Bank_helper.con();
		PreparedStatement stmt = conn.prepareStatement("insert into bank values(?,?,?,?,?,?,?)");
		stmt.setInt(1, ac_no); stmt.setString(2, ac_hol); stmt.setString(3, ifsc); stmt.setString(4, branch);
		stmt.setString(5, ac_type); stmt.setDouble(6, bal); stmt.setInt(7, pin); stmt.executeUpdate();
		System.out.println("........Congo, Details inserted sucessfully.........\n");
		ResultSet rs=stmt.executeQuery("select * from bank");
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)
			+" "+rs.getString(5)+" "+rs.getDouble(6)+" "+rs.getInt(7));			
		}	
	}
	public void deposit_ac() throws SQLException, wrongpinexception{
		System.out.println("To deposit amount please enter your existing account Pin:"
				+ "\n..........................\n"
				+ "Our smart detector will detect your account and show your current balance..... ");
		pin=sc.nextInt();
		Connection conn=Bank_helper.con();
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("select balance from bank where pin="+pin);
		if(!rs.next()) {
			throw new wrongpinexception("Pin not matched......\nHave you forgot it?.....\nPlease update it...");
		}
		else {
			System.out.println("Account found....\nYour current balance is:\n"+rs.getDouble(1));
			System.out.println("..............................\nPlease enter amount to deposit money: ");
			bal=sc.nextDouble();
			stmt.executeUpdate("update bank set balance=balance+"+bal+"where pin="+pin);	
			System.out.println("..............Amount Successfully added.............");
		}
	}
	public void withdrawbal_ac() throws SQLException, acnumberexception, wrongpinexception, notenoughbalexception{
		System.out.println("To Withdraw amount please enter your existing account number:");
		ac_no = sc.nextInt();
		Connection conn=Bank_helper.con();
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("select * from bank where account_no="+ac_no);
		if(!rs.next()) {
			throw new acnumberexception("Account number not matched.....\nPlease re-recheck and enter again......");
		}
		else {
			System.out.println("........Account Details Found........");
			System.out.println("Please enter your Pin: ");
			pin=sc.nextInt();
			ResultSet rs1=stmt.executeQuery("select * from bank where pin="+pin);
			if(!rs1.next()) {
				throw new wrongpinexception("Pin not matched......\nHave you forgot it?.....\nPlease update it...");
			}
			else {
				System.out.println("........Pin Matched........");
				System.out.println("Now, Please enter withdrawal amount: ");
				bal=sc.nextDouble();
				ResultSet rs3=stmt.executeQuery("select balance from bank where pin="+pin);
				if(rs3.next()) 
				balance=rs3.getDouble(1);
				if(bal>balance) {
					throw new notenoughbalexception("Sorry, you don't have enough balance to withdraw.....");
				}
				else {
					stmt.executeUpdate("update bank set balance=balance-"+bal+"where pin="+pin);
					ResultSet rs2=stmt.executeQuery("select balance from bank where pin="+pin);
					if(rs2.next())
					System.out.println("Withdrawal complete......\nYour current account balance......\n"+rs2.getDouble(1));
				}
			}
		}
	}
	public void showbal_ac() throws SQLException, acnumberexception{
		System.out.println("To Display amount please enter your existing account number:");
		ac_no = sc.nextInt();
		Connection conn=Bank_helper.con();
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("select balance from bank where account_no="+ac_no);
		if(!rs.next()) {
			throw new acnumberexception("Account number not matched.....\nPlease re-recheck and enter again......");
		}
		else {
			System.out.println("Acount details found, Your Current balance is:\n"+rs.getDouble(1));
		}
	}
	public void pin_change_ac() throws SQLException, wrongpinexception{
		System.out.println("Please enter your Current Balance........\n"
				+ "We will detect your pin and help you to change it.........");
		bal=sc.nextDouble();
		Connection cnn=Bank_helper.con();
		Statement stmt=cnn.createStatement();
		ResultSet rs=stmt.executeQuery("select pin from bank where balance="+bal);
		if(!rs.next()) {
			throw new wrongpinexception("Pin not matched......\nHave you forgot it?.....\nPlease update it...");
		}
		else {
			System.out.println("Our System has detected your current pin: \n"+rs.getInt(1));
			System.out.println("Pin changing process Started........\nPlease enter your updated Pin......");
			pin=sc.nextInt();
			stmt.executeUpdate("update bank set pin='"+pin+"'where balance="+bal);
			System.out.println("Pin changing process done........");
		}
	}	
	public void close_ac() throws SQLException, acnumberexception{
		System.out.println("To Close your account please enter your existing account number:");
		ac_no = sc.nextInt();
		Connection conn=Bank_helper.con();
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("select * from bank where account_no="+ac_no);
		if(!rs.next()) {
			throw new acnumberexception("Account number not found\nPlease re-check and enter again........");
		}
		else {
			stmt.executeUpdate("delete from bank where account_no="+ac_no);
			System.out.println("Account closing success......\nThank you for using our service......\n"
					+ "Feel free to contact us if you have any issue with our service.......");
		}
	}	
}
