package creational_dp;

import java.util.Scanner;

interface Bank {
	String getBankName();
}

class HDFC implements Bank {
	private final String BNAME;

	public HDFC() {
		BNAME = "HDFC BANK";
	}

	@Override
	public String getBankName() {
		return BNAME;
	}
}

class ICICI implements Bank {
	private final String BNAME;

	public ICICI() {
		BNAME = "ICICI BANK";
	}

	@Override
	public String getBankName() {
		return BNAME;
	}
}

class SBI implements Bank {
	private final String BNAME;

	public SBI() {
		BNAME = "SBI BANK";
	}

	@Override
	public String getBankName() {
		return BNAME;
	}
}

abstract class Loan {
	protected double rate;

	abstract void getInterestRate(double rate);

	public void calculateLoanPayment(double loanamount, int years) {
		double EMI;
		int n;

		n = years * 12;
		rate = rate / 1200;

		EMI = ((rate * Math.pow((1 + rate), n)) / ((Math.pow((1 + rate), n)) - 1)) * loanamount;

		System.out.println("your's monthly EMI is " + EMI + " for the amount " + loanamount + " you have borrowed ");

	}

}

class HomeLoan extends Loan {
	public void getInterestRate(double r) {
		rate = r;
	}
}

class BussinessLoan extends Loan {
	public void getInterestRate(double r) {
		rate = r;
	}
}

class EducationLoan extends Loan {
	public void getInterestRate(double r) {
		rate = r;
	}
}

abstract class AbstractFactory {
	public abstract Bank getBank(String bank);

	public abstract Loan getLoan(String loan);

}

class BankFactory extends AbstractFactory {
	@Override
	public Bank getBank(String bank) {
		if (bank == null) {
			return null;
		}

		if (bank.equalsIgnoreCase("HDFC")) {
			return new HDFC();
		} else if (bank.equalsIgnoreCase("ICICI")) {
			return new ICICI();
		} else if (bank.equalsIgnoreCase("SBI")) {
			return new SBI();
		}
		return null;
	}

	@Override
	public Loan getLoan(String loan) {
		return null;
	}
}

class LoanFactory extends AbstractFactory {

	@Override
	public Bank getBank(String bank) {
		return null;
	}

	@Override
	public Loan getLoan(String loan) {
		if (loan == null) {
			return null;
		}

		if (loan.equalsIgnoreCase("Home")) {
			return new HomeLoan();
		} else if (loan.equalsIgnoreCase("Business")) {
			return new BussinessLoan();
		} else if (loan.equalsIgnoreCase("Education")) {
			return new EducationLoan();
		}
		return null;
	}

}

class FactoryCreator {
	public static AbstractFactory getFactory(String choice) {
		if (choice.equalsIgnoreCase("Bank")) {
			return new BankFactory();
		} else if (choice.equalsIgnoreCase("Loan")) {
			return new LoanFactory();
		}
		return null;
	}
}

public class AbstractFactoryPattern {

	public static void main(String args[]) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the name of Bank from where you want to take loan amount: ");
		String bankName = scan.nextLine();

		System.out.println(
				"Enter the type of loan you want to take, like home loan or bussiness loan or education loan : ");
		String loanName = scan.nextLine();

		AbstractFactory bankFactory = FactoryCreator.getFactory("Bank");
		Bank b = bankFactory.getBank(bankName);

		System.out.println("Enter the interest rate for " + b.getBankName() + ": ");
		double rate = scan.nextDouble();

		System.out.println("Enter the loan amount you want to take: ");
		double loanAmount = scan.nextDouble();

		System.out.println("Enter the number of years to pay your entire loan amount: ");
		int years = scan.nextInt();

		System.out.println("you are taking the loan from " + b.getBankName());

		AbstractFactory loanFactory = FactoryCreator.getFactory("Loan");

		Loan l = loanFactory.getLoan(loanName);

		l.getInterestRate(rate);
		l.calculateLoanPayment(loanAmount, years);

	}
}

