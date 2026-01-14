package model;

import java.util.Scanner;

//======================== BankSystem =========================

public class BankSystem {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		BankModel model = new BankModel();
		BankView view = new BankView();
		BankController c = new BankController(model, view);

		// Sample Accounts
		model.addACC(new SavingAccount(101, "Amit", 50000, "amit@gmail.com", AccountStatus.ACTIVE, 100000));
		model.addACC(new SalaryAccount(102, "Jay", 70000, "jay@gmail.com", AccountStatus.ACTIVE, 200000));
		model.addACC(new CurrentAcc(103, "Vishal", 80000, "vishal@gmail.com", AccountStatus.ACTIVE, 56000));
		model.addACC(
				new LoanAccount(104, "Bira", 0, "bira@gmail.com", AccountStatus.ACTIVE, 150000, LoanStatus.ACTIVE, 0));

		model.addACC(new SavingAccount(105, "Rohit", 45000, "rohit@gmail.com", AccountStatus.ACTIVE, 90000));
		model.addACC(new SavingAccount(106, "Sneha", 60000, "sneha@gmail.com", AccountStatus.ACTIVE, 120000));
		model.addACC(new SavingAccount(107, "Pooja", 35000, "pooja@gmail.com", AccountStatus.ACTIVE, 80000));
		model.addACC(new SavingAccount(108, "Karan", 72000, "karan@gmail.com", AccountStatus.ACTIVE, 150000));

		model.addACC(new SalaryAccount(109, "Ankit", 55000, "ankit@gmail.com", AccountStatus.ACTIVE, 180000));
		model.addACC(new SalaryAccount(110, "Neha", 68000, "neha@gmail.com", AccountStatus.ACTIVE, 200000));
		model.addACC(new SalaryAccount(111, "Sagar", 75000, "sagar@gmail.com", AccountStatus.ACTIVE, 220000));
		model.addACC(new SalaryAccount(112, "Megha", 82000, "megha@gmail.com", AccountStatus.ACTIVE, 250000));

		model.addACC(new CurrentAcc(113, "Vikas", 90000, "vikas@gmail.com", AccountStatus.ACTIVE, 50000));
		model.addACC(new CurrentAcc(114, "Nikhil", 120000, "nikhil@gmail.com", AccountStatus.ACTIVE, 70000));
		model.addACC(new CurrentAcc(115, "Aarti", 100000, "aarti@gmail.com", AccountStatus.ACTIVE, 60000));
		model.addACC(new CurrentAcc(116, "Rahul", 85000, "rahul@gmail.com", AccountStatus.ACTIVE, 45000));

		model.addACC(new LoanAccount(117, "Manish", 0, "manish@gmail.com", AccountStatus.ACTIVE, 300000,
				LoanStatus.ACTIVE, 0));
		model.addACC(new LoanAccount(118, "Komal", 0, "komal@gmail.com", AccountStatus.ACTIVE, 500000,
				LoanStatus.ACTIVE, 0));
		model.addACC(new LoanAccount(119, "Aakash", 0, "aakash@gmail.com", AccountStatus.ACTIVE, 250000,
				LoanStatus.PENDING, 0));
		model.addACC(new LoanAccount(120, "Tanvi", 0, "tanvi@gmail.com", AccountStatus.ACTIVE, 400000,
				LoanStatus.ACTIVE, 50000));

		model.addACC(new SavingAccount(121, "Harsh", 28000, "harsh@gmail.com", AccountStatus.FREEZED, 60000));
		model.addACC(new SalaryAccount(122, "Isha", 50000, "isha@gmail.com", AccountStatus.BLOCKED, 150000));
		model.addACC(new CurrentAcc(123, "Omkar", 70000, "omkar@gmail.com", AccountStatus.SUSPENDED, 30000));
		model.addACC(
				new LoanAccount(124, "Rina", 0, "rina@gmail.com", AccountStatus.CLOSED, 0, LoanStatus.CLOSED, 200000));

		while (true) {
			view.showMainMenu();
			int mainChoice = sc.nextInt();

			switch (mainChoice) {
			case 1:
				view.showAccountMenu();
				int ch = sc.nextInt();
				switch (ch) {

				case 1:
					c.createAccount();
					break;
				case 2:
					c.closeAcc();
					break;
				case 3:
					c.showAccStatus();
					break;

				case 4:
					model.showAllAccounts();
					break;
				case 0:
					break;
				default:
					System.out.println("Invalid Account Menu Choice!");
				}
				break;

			case 2:
				view.showTransactionMenu();
				int transChoice = sc.nextInt();
				switch (transChoice) {
				case 1:
					c.deposit();
					break;
				case 2:
					c.withdraw();
					break;
				case 3:
					c.showAccountTransactions();
					break;
				case 4:
					model.showAllTrans();
					break;
				case 0:
					break;
				default:
					System.out.println("Invalid Transaction Choice!");
				}
				break;
			case 3:
				view.showLoanMenu();
				int loanChoice = sc.nextInt();
				switch (loanChoice) {
				case 1:
					c.applyLoan();
					break;
				case 2:
					c.payEmi();
					break;
				case 3:
					c.calculateLoanInstallment();
					break;
				case 4:
					c.showAccountTransactions();
				default:
					break;
				}

				break;

			case 4:
				view.showReportMenu();
				int reportChoice = sc.nextInt();
				switch (reportChoice) {
				case 1:
					c.applyInterest();
					break;
				case 2:
					c.eod();
					break;

				default:
					System.out.println("Invalid Report Choice!");
				}
				break;
			case 0:
				System.out.println("Thank you for using Bank System!");
				System.exit(0);

			default:
				System.out.println("Invalid Main Menu Choice!");
			}

		}
	}

}
