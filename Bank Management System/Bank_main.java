package Com.Bank.main;
import java.sql.SQLException;
import java.util.Scanner;
import Com.Bank.bms.Bank_bms;

public class Bank_main {
	public static void main(String[] args) throws SQLException  {
		Scanner sc = new Scanner(System.in);
		Bank_bms bb=new Bank_bms();
		System.out.println("Press 1 for Create ac :\nPress 2 for Deposit :\n"
				+ "Press 3 for Withdrawal :\nPress 4 for Showing balance :\n"
				+ "Press 5 for Pin change :\nPress 6 for closing ac :"
				+ "\n.......................................");
		int variable = sc.nextInt();
		System.out.println("...............................");
		switch(variable) { 
		case 1 : 
			bb.create_ac();
			System.out.println(".........................\n"
					+ "Thank you for using our service\nPlease visit back soon");
			break;
		case 2 :  
			bb.deposit_ac();
			System.out.println(".........................\n"
					+ "Thank you for using our service\nPlease visit back soon");
			break;
		case 3 :
			bb.withdrawbal_ac();
			System.out.println(".........................\n"
					+ "Thank you for using our service\nPlease visit back soon");
			break;
		case 4 :
			bb.showbal_ac();
			System.out.println(".........................\n"
					+ "Thank you for using our service\nPlease visit back soon");
			break;
		case 5 :
			bb.pin_change_ac();
			System.out.println(".........................\n"
					+ "Thank you for using our service\nPlease visit back soon");
			break;
		case 6:
			bb.close_ac();
			break;
		}
	}
}