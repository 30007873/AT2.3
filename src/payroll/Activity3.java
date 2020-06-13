/**
 * 
 */
package payroll;

import java.util.Scanner;

import payroll.domain.Payroll;
import payroll.service.PayrollService;

/**
 * @author jga85
 *
 */
public class Activity3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PayrollService payrollService = new PayrollService();
		Scanner scanner = new Scanner(System.in);
		System.out.println("WELCOME TO PAYROLL SYSTEM. \n Enter the number of pay items: ");
		int items = scanner.nextInt();
		
		System.out.println("The Payroll items: Unsorted");
		Payroll unsortedPayroll = payrollService.getPayroll(items);
		System.out.println(unsortedPayroll);
		
		System.out.println();
		System.out.println("Enter Sorting Strategy: Bubble or Insertion or Selection : ");
		String sortingStrategy = scanner.next();
		
		Payroll payroll = payrollService.getSortedPayroll(unsortedPayroll, sortingStrategy);
		System.out.println(payroll);
		scanner.close();
	}
}
