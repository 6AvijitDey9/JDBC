package Com.Bank.bms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;

import Com.Bank.helper.Bank_helper;

class wrongpinexception extends SQLException{ //Custom exception for wrong pin enter
	public wrongpinexception(String str) {
		super(str);
	}
}
class notenoughbalexception extends SQLException{ //If account holder don't have enough balance Custom exception 
	public notenoughbalexception(String str) {
		super(str);
	}
}
class acnumberexception extends SQLException{ //Custom Exception for entering wrong account number
	public acnumberexception(String str) {
		super(str);
	}
}
class acalreadyexist extends SQLException{ //Custom exception if the account number already exist
	public acalreadyexist(String str) {
		super(str);
	}
}
class namenotmatched extends SQLException{ //Custom exception if the account holder name and account number not matched
	public namenotmatched(String str) {
		super(str);
	}
}

public class Bank_bms {
	Scanner sc=new Scanner(System.in); //Scanner Class
	int ac_no, pin, count=0; double bal, balance; String ac_hol, ifsc, branch, ac_type;
	
	public void create_ac() throws SQLException, acalreadyexist{ 
		System.out.println("Account number should not be start with 0...\n"
				+ "Give total three number as an account number....\n"
				+ "Please enter your account number: "); //Taking user inputs
		ac_no = sc.nextInt();
		while(!Pattern.matches("[1-9]{1}[0-9]{2}", String.valueOf(ac_no))) { // Account number should be start with non zero number and after that 2 digits
			System.out.println("Please follow the specific pattern for account number and try again: ");
			ac_no = sc.nextInt();
		}
		System.out.println("Please enter account holder UserName: ");
		ac_hol=sc.next();
		System.out.println("IFSC code starts with three chracters and three digits after that....\n"
				+ "Please follow the pattern\nNow please enter ifsc code: ");
		ifsc=sc.next();
		while (!Pattern.matches("[A-Za-z]{3}[0-9]{3}", String.valueOf(ifsc))) { // IFSC code must start with three letter of A-Z or, a-z and ends with three letter of 1-9
			System.out.println("Please follow the specific pattern for ifsc code and try again: ");
			ifsc = sc.next();
		}
		System.out.println("Please enter your Branch: ");
		branch=sc.next();
		System.out.println("Account type should be Savings/Fd only...\n"
				+ "Please note this is case sensitive....\nPlease enter your account type: ");
		ac_type=sc.next();
		while(!ac_type.equals("Savings") && !ac_type.equals("Fd")) { //We only provide Savings/Fd accounts services 
			System.out.println("Currently we accept Savings/Fd accounts only\nPlease try agian:");
			ac_type=sc.next();
		}
		System.out.println("Please enter your current balance: ");
		bal=sc.nextDouble();
		System.out.println("Pin should not be start with 0...\n"
				+ "Give total four number as Pin....\nPlease enter your Pin: ");
		pin=sc.nextInt();
		while(!Pattern.matches("[1-9]{1}[0-9]{3}", String.valueOf(pin))) { // Pin should be start with non zero number and after that 3 digits
			System.out.println("Please follow the specific pattern for Pin and try again: ");
			pin = sc.nextInt();
		}
		Connection conn=Bank_helper.con(); //Connecting to Database 
		Statement stmt1 = conn.createStatement(); 
		ResultSet rst = stmt1.executeQuery("select account_no from bank"); //Database query
		while(rst.next()) { //Loop to check if there's anything at database
			if(ac_no==rst.getInt(1)) { //To check if the account number is exist or not
				count+=1;
				System.out.println("Account already created with this account number........");				
				throw new acalreadyexist("\nPlease check the account number carefully and re_enter the details......\n\n ");
				}
			else {
				count=-1;
				}
			}	
		if(count==-1) { //If there is no account number same as user input
			System.out.println("Your account will be created shortly.....\n"
					+ "No existing account found with this account number......");
			PreparedStatement stmt = conn.prepareStatement("insert into bank values(?,?,?,?,?,?,?)"); //Statement to insert values in database. ? mark denotes how many columns the table has.
			stmt.setInt(1, ac_no); stmt.setString(2, ac_hol); stmt.setString(3, ifsc); stmt.setString(4, branch);
			stmt.setString(5, ac_type); stmt.setDouble(6, bal); stmt.setInt(7, pin); stmt.executeUpdate();
			System.out.println("........Congo, Details inserted sucessfully.........\n"
					+ "The details of our account holders are: \n");
			}
		ResultSet rst2 = stmt1.executeQuery("select account_no from bank");
		if(rst2.next()==false) { //If the database is empty
			System.out.println("Your account will be created shortly.....\n"
					+ "No existing account found with this account number......");
			PreparedStatement stmt = conn.prepareStatement("insert into bank values(?,?,?,?,?,?,?)");
			stmt.setInt(1, ac_no); stmt.setString(2, ac_hol); stmt.setString(3, ifsc); stmt.setString(4, branch);
			stmt.setString(5, ac_type); stmt.setDouble(6, bal); stmt.setInt(7, pin); stmt.executeUpdate();
			System.out.println("........Congo, Details inserted sucessfully.........\n"
					+ "The details of our account holders are: \n");
		}
		ResultSet rs=stmt1.executeQuery("select * from bank"); //Selecting the whole data from bank table to print 
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
		if(!rs.next()) { //If there is no account found for the pin number
			throw new wrongpinexception("\nPin not matched......\nHave you forgot it?.....\nPlease update it...\n\n");
		}
		else {
			System.out.println("Account found....\nYour current balance is:\n"+rs.getDouble(1)); //It will show user's current balance
			System.out.println("..............................\nPlease enter amount to deposit money: ");
			bal=sc.nextDouble();
			stmt.executeUpdate("update bank set balance=balance+"+bal+"where pin="+pin); //Executing updates on table to add user's given amount with the existing amount	
			System.out.println("..............Amount Successfully added.............");
		}
	}
	public void withdrawbal_ac() throws SQLException, acnumberexception, wrongpinexception, notenoughbalexception{
		System.out.println("To Withdraw amount please enter your existing account number:");
		ac_no = sc.nextInt();
		Connection conn=Bank_helper.con();
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("select * from bank where account_no="+ac_no);
		if(!rs.next()) { //If there's no details found with this account number
			throw new acnumberexception("\nAccount number not matched.....\nPlease re-recheck and enter again......\n");//Throwing custom exception
		}
		else {
			System.out.println("........Account Details Found........");
			System.out.println("Please enter your Pin: ");
			pin=sc.nextInt();
			ResultSet rs1=stmt.executeQuery("select * from bank where pin="+pin);
			if(!rs1.next()) { //If there's no details found with this account number
				throw new wrongpinexception("\nPin not matched......\nHave you forgot it?.....\nPlease update it...\n\n"); //Throwing custom exception
			}
			else {
				System.out.println("........Pin Matched........");
				System.out.println("Now, Please enter withdrawal amount: ");
				bal=sc.nextDouble();
				ResultSet rs3=stmt.executeQuery("select balance from bank where pin="+pin); 
				if(rs3.next()) 
				balance=rs3.getDouble(1);
				if(bal>balance) { //If the account balance is less than withdrawal amount
					throw new notenoughbalexception("\nSorry, you don't have enough balance to withdraw.....\n\n"); //Custom exception 
				}
				else {
					stmt.executeUpdate("update bank set balance=balance-"+bal+"where pin="+pin); //Executing update on database. If there is enough amount it will get decreased
					ResultSet rs2=stmt.executeQuery("select balance from bank where pin="+pin);
					if(rs2.next())
					System.out.println("Withdrawal complete......\nYour current account balance......\n"+rs2.getDouble(1)); //It will show user current balance after withdrawal 
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
		if(!rs.next()) { //If there is no account found with the account number
			throw new acnumberexception("\nAccount number not matched.....\nPlease re-recheck and enter again......\n\n"); //Custom exception
		}
		else {
			System.out.println("Acount details found, Your Current balance is:\n"+rs.getDouble(1)); //If found then..
		}
	}
	public void pin_change_ac() throws SQLException, wrongpinexception{
		System.out.println("Please enter your Current Balance........\n"
				+ "We will detect your pin and help you to change it.........");
		bal=sc.nextDouble();
		Connection cnn=Bank_helper.con();
		Statement stmt=cnn.createStatement();
		ResultSet rs=stmt.executeQuery("select pin from bank where balance="+bal);
		if(!rs.next()) { //If user inputs wrong balance details 
			throw new wrongpinexception("\nPin not matched......\nHave you forgot it?.....\nPlease update it...\n\n"); //Custom Exception 
		}
		else {
			System.out.println("Our System has detected your current pin: \n"+rs.getInt(1));
			System.out.println("Pin changing process Started........\nPlease enter your updated Pin......");
			pin=sc.nextInt();
			stmt.executeUpdate("update bank set pin='"+pin+"'where balance="+bal); //Executing pin update on database if the balance matched with any account
			System.out.println("Pin changing process done........");
		}
	}	
	public void close_ac() throws SQLException, acnumberexception{
		System.out.println("To Close your account please enter your existing account number:");
		ac_no = sc.nextInt();
		Connection conn=Bank_helper.con();
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("select * from bank where account_no="+ac_no);
		if(!rs.next()) { //If no account found with the given account number
			throw new acnumberexception("\nAccount number not found\nPlease re-check and enter again........\n\n"); //Custom Exception
		}
		else {
			System.out.println("Account details found....\nPlease enter Account holder UserName Properly:");
			ac_hol = sc.next();
			ResultSet rs1 = stmt.executeQuery("Select * from bank where account_holder='"+ac_hol+"'and account_no="+ac_no); //Storing the data if there any result matches with both criteria
			if(!rs1.next()) {
				throw new namenotmatched("\nAccount Holder name not matched with Account number.....\nDo check and try again.....\n\n");
			}
			else {
				System.out.println("Account Holder name matched with Account number.....");
			stmt.executeUpdate("delete from bank where account_holder='"+ac_hol+"'and account_no="+ac_no); //If any account found with the account number and name it will just delete the account details
			System.out.println("Account closing success......\nThank you for using our service......\n"
					+ "Feel free to contact us if you have any issue with our service.......");
			}
		}	
	}	
}
