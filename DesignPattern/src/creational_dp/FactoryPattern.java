package creational_dp;

import java.util.Scanner;

abstract class Account {
	protected Double interestRate;

	abstract void getInterestRate();
	
	public void getTotal(Double Money, Double year) {
		System.out.println(Money*year*interestRate);
	}
}

class Current extends Account {
	public void getInterestRate() {
		interestRate = 1.0;
	}
}

class Saving extends Account {

	@Override
	public void getInterestRate() {
		interestRate = 4.5;
	}
}

class FixedDeposit extends Account {

	@Override
	void getInterestRate() {
		interestRate = 6.5;
	}
}

class AccountFactory {

	public Account getAccountType(String accountName) {
		if (accountName == null) {
			return null;
		} else if (accountName.equalsIgnoreCase("Current"))
			return new Current();
		else if (accountName.equalsIgnoreCase("Saving"))
			return new Saving();
		else if (accountName.equalsIgnoreCase("FixedDeposite"))
			return new FixedDeposit();
		else
			return null;
	}
}

public class FactoryPattern {

	public static void main(String[] args) {
		AccountFactory account=new AccountFactory();
		
		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter your Account Type :");
		String accountType=scan.nextLine();
		
		System.out.println("Enter the money that you want to save :");
		Double money=scan.nextDouble();
		
		System.out.println("for how many year :");
		Double year=scan.nextDouble();
		
		Account ac=account.getAccountType(accountType);
		
		System.out.println("You "+money+" Rs money save in "+accountType+" Account so after "+year+" years you get: ");
		ac.getInterestRate();
		ac.getTotal(money, year);
	}

}
