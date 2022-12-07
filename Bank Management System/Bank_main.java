package Com.Bank.main;
import java.sql.SQLException;
import java.util.Scanner;
import Com.Bank.bms.Bank_bms;

public class Bank_main {
	public static void stop() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("....................................\nPress 1 to go to main menu : or, Press 0 to exit :");
		int variable = sc.nextInt();
		if(variable==0) {
			System.out.println("....................................\n"
					+ "You have sucessfully exited from our server......\n"
					+ "Thank you for using our service......\nPlease visit back soon.........");
			return;
		}
		else if(variable==1) {
		  main(null);
		}
		else {
			System.out.println("Please choose a valid option :");
			stop();
		}
	}
	public static void main(String[] args) throws SQLException  { //Main Method
		Scanner sc = new Scanner(System.in); //Scanner Class
		Bank_bms bb=new Bank_bms(); //Creating object of Bank_bms class
		System.out.println("Press 1 for Create ac :\nPress 2 for Deposit :\n"
				+ "Press 3 for Withdrawal :\nPress 4 for Showing balance :\n"
				+ "Press 5 for Pin change :\nPress 6 for closing ac :"
				+ "\n<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>"); //Print line and it will do the user's selected operation
		int variable = sc.nextInt();
		System.out.println("...............................");
		switch(variable) { 
		case 1 : 
			bb.create_ac();
			System.out.println(".........................\n"
					+ "Thank you for using our service\nPlease visit back soon");
			stop();
			break;
		case 2 :  
			bb.deposit_ac();
			System.out.println(".........................\n"
					+ "Thank you for using our service\nPlease visit back soon");
			stop();
			break;
		case 3 :
			bb.withdrawbal_ac();
			System.out.println(".........................\n"
					+ "Thank you for using our service\nPlease visit back soon");
			stop();
			break;
		case 4 :
			bb.showbal_ac();
			System.out.println(".........................\n"
					+ "Thank you for using our service\nPlease visit back soon");
			stop();
			break;
		case 5 :
			bb.pin_change_ac();
			System.out.println(".........................\n"
					+ "Thank you for using our service\nPlease visit back soon");
			stop();
			break;
		case 6:
			bb.close_ac();
			stop();
			break;
		 }
	 }
  }
