package model;

import java.util.Scanner;

//======================= BankController ======================

public class BankController {
	BankModel model;
	BankView view;
	Scanner sc = new Scanner(System.in);

	public BankController(BankModel m, BankView v) {
		this.model = m;
		this.view = v;
	}

	public void createAccount() {
		while (true) {
			System.out.println("\n=============== ACCOUNT MENU ==============");
			System.out.println("1. Open Saving Account");
			System.out.println("2. Open Salary Account");
			System.out.println("3. Open Current Account");
			System.out.println("4. Open Loan Account");
			System.out.println("5. Exit");
			System.out.print("Enter choice: ");
			System.out.println("----------------------------------------------");
			int choice = sc.nextInt();

			Account a = null;

			switch (choice) {

			case 1:
				System.out.println("Account No :");
				int Acc = sc.nextInt();
				System.out.println("Holder Name : ");
				String name = sc.next();
				System.out.println("Initial Amount : ");
				double amt = sc.nextDouble();
				System.out.println("Enter Email ID : ");
				String eml1 = sc.next();
				System.out.println("AQB : ");
				double aqb = sc.nextDouble();
				a = new SavingAccount(Acc, name, amt, eml1, AccountStatus.ACTIVE, aqb);
				break;
			case 2:
				System.out.println("Account No :");
				int Acc7 = sc.nextInt();
				System.out.println("Holder Name : ");
				String name8 = sc.next();
				System.out.println("Initial Amount : ");
				double amt4 = sc.nextDouble();
				System.out.println("Enter Email ID : ");
				String eml2 = sc.next();
				System.out.println("AQB : ");
				double aqb9 = sc.nextDouble();
				a = new SalaryAccount(Acc7, name8, amt4, eml2, AccountStatus.ACTIVE, aqb9);
				break;
			case 3:
				System.out.println("Account No :");
				int Acc1 = sc.nextInt();
				System.out.println("Holder Name : ");
				String name2 = sc.next();
				System.out.println("Initial Amount : ");
				double amt3 = sc.nextDouble();
				System.out.println("Enter Email ID : ");
				String eml3 = sc.next();
				System.out.println("Enter OverDraftLimit : ");
				double odl = sc.nextDouble();

				a = new CurrentAcc(Acc1, name2, amt3, eml3, AccountStatus.ACTIVE, odl);
				break;
			case 4:
				System.out.println("Account No :");
				int Acc6 = sc.nextInt();
				System.out.println("Holder Name : ");
				String name23 = sc.next();
				System.out.println("Initial Amount : ");
				double amt5 = sc.nextDouble();
				System.out.println("Enter Email ID : ");
				String eml6 = sc.next();
				System.out.println("Enter Loan Amount : ");
				double odl1 = sc.nextDouble();
				System.out.println("Enter TotalEMI PAID : ");
				double tlp4 = sc.nextDouble();
				a = new LoanAccount(Acc6, name23, amt5, eml6, AccountStatus.ACTIVE, odl1, LoanStatus.ACTIVE, tlp4);
				break;
			case 5:
				System.out.println("Exiting Account Creation.....");
				return;
			default:
				System.out.println("Invalid Option !");
				continue;
			}
			model.addACC(a);
			System.out.println("Account created Successfully!");
		}

	}

	void deposit() {
		System.out.println("Enter Acc No : ");
		int acc = sc.nextInt();
		System.out.println("Enter Amount Deposit");
		double amt = sc.nextDouble();

		Account a = model.findAcc(acc);

		if (a == null) {

			return;
		}
		a.deposit(acc, amt);
		model.recordBankTrans(TransType.CREDIT, amt, "Deposit AccNo " + acc, a.getCurrentBal());
		a.deposit(acc, amt);
	}

	void withdraw() {
		System.out.println("Enter Acc No : ");
		int acc = sc.nextInt();
		System.out.println("Enter Amount Withdraw");
		double amt = sc.nextDouble();

		Account a = model.findAcc(acc);

		if (a == null) {
			System.out.println("Account not found !");
			return;
		}

		a.withdraw(acc, amt);
		model.recordBankTrans(TransType.DEBIT, amt, "Withdraw AccNo " + acc, a.getCurrentBal());
		a.withdraw(acc, amt);
	}

	void applyInterest() {
		System.out.println("Enter Account No :");
		int accNo = sc.nextInt();
		Account a = model.findAcc(accNo);
		if (a == null) {
			System.out.println("Account not found!");
			return;
		}

		double interest = a.calculateTotalInterest();
		a.currentBal = a.currentBal + interest;

		a.recordTrans(TransType.CREDIT, interest, "INTEREST");
		System.out.println("Interest Applied : " + interest);
	}

	void eod() {
		Report rep = new Report();
		rep.showReport(model);
	}

	void listAcc() {
		System.out.println("\n========== ACCOUNTS ==========");
		for (int i = 0; i < model.accCount; i++) {
			System.out.println(model.accs[i]);
		}
	}

	void showAccStatus() {
		System.out.println("Enter Account Number : ");
		int accNo = sc.nextInt();

		Account a = model.findAcc(accNo);

		if (a == null) {
			System.out.println("Account not found!");
			return;
		}

		String type = a.getClass().getSimpleName();

		System.out.println("\n=========== ACCOUNT STATUS =========");
		System.out.println("Account No : " + a.custAccNo);
		System.out.println("Holder Name : " + a.custName);
		System.out.println("Account Type : " + type);
		System.out.println("Balance : " + a.currentBal);
		System.out.println("Account Status : " + a.accStatus);
		System.out.println("======================================");

	}

	void calculateLoanInstallment() {
		System.out.println("Enter Loan Account No : ");
		int accNo = sc.nextInt();

		Account a = model.findAcc(accNo);

		if (a == null || a.getClass() != LoanAccount.class) {
			System.out.println("Loan Account not found!");
			return;
		}

		LoanAccount loan = (LoanAccount) a;

		double emi = loan.calculateEMI();
		double interest = loan.calculateTotalInterest();

		System.out.println("\n============ LOAN EMI DETAILS =========");
		System.out.println("Account NO : " + loan.custAccNo);
		System.out.println("Principal : " + loan.loanAmt);
		System.out.println("Interest Rate : " + LoanAccount.interestRate + "%");
		System.out.println("Loan Period : " + loan.loanMonths + "Months");
		System.out.println("Monthly EMI : " + emi);
		System.out.println("Total Interest : " + interest);
		System.out.println("=========================================");

	}

	void closeAcc() {
		System.out.println("Enter Account No :");
		int accNo = sc.nextInt();

		Account a = model.findAcc(accNo);

		if (a == null) {
			System.out.println("--------------- Account not found! -----------------");
			return;
		}

		if (a.accStatus == AccountStatus.CLOSED) {
			System.out.println("Account is already CLOSED.");
			return;
		}
		System.out.println("Are you sure you want to close this account? (yes/no): ");
		String ab = sc.next();

		if (!ab.equals("yes")) {
			System.out.println("Account closing Cancelled.");
			return;
		}

		a.closeAcc();
		System.out.println("------------------ Account closed Successfully! ---------------------");

	}

	void showAccountTransactions() {
		System.out.println("Enter Account No :");
		int accNo = sc.nextInt();

		Account a = model.findAcc(accNo);

		if (a == null) {
			System.out.println("-------------- Account not found! -------------------");
			return;
		}

		System.out.println("\n=============== TRANSACTION HISTORY ==================");
		System.out.println("Account : " + a.custAccNo + " | Holder Name : " + a.custName);
		System.out.println("--------------------------------------------------------");

		if (a.tcount == 0) {
			System.out.println("No transactions found for this account.");
			return;
		}

		for (int i = 0; i < a.tcount; i++) {
			Transaction t = a.trans[i];
			System.out.println("Trans ID :" + t.transId + " | TYPE : " + t.transType + " | Amount : " + t.transAmt
					+ " | Trans Date :" + t.transDate + " | Trans Desc : " + t.transDesc);
		}
		System.out.println("========================================================");

	}

	public void applyLoan() {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter Account Number : ");
		int accNo = sc.nextInt();

		Account acc = model.getAccountByNo(accNo);

		if (acc == null) {
			System.out.println("Account not found!");
			return;
		}

		if (!(acc instanceof LoanAccount)) {
			System.out.println("This is not a Loan Account!");
			return;
		}

		LoanAccount loan = (LoanAccount) acc;

		if (loan.getLoanStatus() == LoanStatus.ACTIVE) {
			System.out.println("Loan already active!");
			return;
		}

		System.out.print("Enter Loan Amount : ");
		double amount = sc.nextDouble();

		if (amount <= 0) {
			System.out.println("Invalid loan amount!");
			return;
		}

		loan.setLoanAmt(amount);
		loan.setLoanStatus(LoanStatus.ACTIVE);

		System.out.println("Loan Applied Successfully!");
		System.out.println("Loan Amount : " + amount);
	}

	public void payEmi() {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter Account Number : ");
		int accNo = sc.nextInt();

		Account acc = model.getAccountByNo(accNo);

		if (acc == null) {
			System.out.println("Account not found!");
			return;
		}

		if (!(acc instanceof LoanAccount)) {
			System.out.println("This is not a Loan Account!");
			return;
		}

		LoanAccount loan = (LoanAccount) acc;

		if (loan.getLoanStatus() != LoanStatus.ACTIVE) {
			System.out.println("Loan is not active!");
			return;
		}

		System.out.print("Enter EMI Amount : ");
		double emi = sc.nextDouble();

		loan.payEmi(accNo, emi);
	}

}