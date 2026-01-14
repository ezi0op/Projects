package model;

import java.time.LocalDate;
import java.time.Period;

//==================================================================
//ENUMS : AccountStatus, LoanStatus
//==================================================================

enum AccountStatus {
	ACTIVE, INACTIVE, CLOSED, FREEZED, BLOCKED, PENDING_APPROVAL, HOLD, SUSPENDED
}

enum LoanStatus {
	ACTIVE, CLOSED, DEFAULTED, PENDING, PREPAID
}

//========================= Account =========================

public abstract class Account {
	int custAccNo;
	String custName;
	double currentBal;
	String emailId;
	AccountStatus accStatus;
	static double dailyWithdrawalLimit;

	static {
		dailyWithdrawalLimit = 1000000;
	}

	public Account() {
		this.custAccNo = 00;
		this.custName = "Not given";
		this.currentBal = 00;
		this.emailId = "NOT GIVEN";
		this.accStatus = AccountStatus.ACTIVE;

	}// DefaultConstructor

	Account(int custAccNo, String custName, double currentBal, String emailId, AccountStatus accStatus) {
		this.custAccNo = custAccNo;
		this.custName = custName;
		this.currentBal = currentBal;
		this.emailId = emailId;
		this.accStatus = accStatus;
	}// Paramterized Constructor

	// Setters and Getters
	int getCustNo() {
		return custAccNo;
	}

	void setCustNo(int custNo) {
		this.custAccNo = custNo;
	}

	String getCustName() {
		return custName;
	}

	void setCustName(String custName) {
		this.custName = custName;
	}

	double getCurrentBal() {
		return currentBal;
	}

	void setCurrentBal(double currentBal) {
		this.currentBal = currentBal;
	}

	String getEmailId() {
		return emailId;
	}

	void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	AccountStatus getAccStatus() {
		return accStatus;
	}

	void setAccStatus(AccountStatus accStatus) {
		this.accStatus = accStatus;
	}

	double getDailyWithdrawalLimit() {
		return dailyWithdrawalLimit;
	}

	static void setDailyWithdrawalLimit(double dailyWithdrawalLimit) {
		Account.dailyWithdrawalLimit = dailyWithdrawalLimit;
	}

	abstract void withdraw(int accNo, double amt);

	abstract void deposit(int accNo, double amt);

	abstract void currentBal(int accNo);

	abstract double calculateTotalInterest();

	Transaction[] trans = new Transaction[100];
	int tcount = 0;

	void closeAcc() {
		this.accStatus = AccountStatus.CLOSED;
		System.out.println("Account Closed Successfully!");
	}

	void freezeAcc() {
		accStatus = AccountStatus.FREEZED;
		System.out.println("Account Freezed Successfully!");
	}

	void unfreezeAcc() {
		if (this.accStatus == AccountStatus.CLOSED) {
			System.out.println("Account is CLOSED. Cannot unfreeze.");
			return;
		}
		this.accStatus = AccountStatus.ACTIVE;
		System.out.println("Account Unfreezed Successfully");
	}

	protected void recordTrans(TransType type, double amt, String desc) {
		if (tcount < trans.length) {
			trans[tcount] = new Transaction(type, tcount + 1, desc, amt, LocalDate.now(), this.currentBal);
			tcount++;
		} else {
			System.out.println("Transaction history full!");
		}
	}

	void calculateTotalCredit() {
		double totalCredit = 0;
		for (int i = 0; i < tcount; i++) {
			if (trans[i].getTransType() == TransType.CREDIT) {
				totalCredit = totalCredit + trans[i].getTransAmt();
			}
		}
		System.out.println("Total Credit : " + totalCredit);
	}

	void calculateTotalDebit() {
		double totalDebit = 0;
		for (int i = 0; i < tcount; i++) {
			if (trans[i].getTransType() == TransType.DEBIT) {
				totalDebit = totalDebit + trans[i].getTransAmt();
			}
		}
		System.out.println("Total Dredit : " + totalDebit);
	}

	String summary() {
		return custAccNo + "|" + custName + "|" + currentBal + "|" + accStatus;
	}

}

//======================= SalaryAccount ======================

class SalaryAccount extends Account {
	double AQB;
	int monthWithoutTrans;
	LocalDate salCreditDate = LocalDate.of(2025, 6, 8);

	SalaryAccount() {
		super();
		this.AQB = 00;

	}

	SalaryAccount(int custAccNo, String custName, double currentBal, String emailId, AccountStatus accStatus,
			double aQB) {
		super(custAccNo, custName, currentBal, emailId, accStatus);
		this.AQB = aQB;

	}

	// Setters and Getters
	double getAQB() {
		return AQB;
	}

	void setAQB(double aQB) {
		AQB = aQB;
	}

	int getMonthWithoutTrans() {
		return monthWithoutTrans;
	}

	void setMonthWithoutTrans(int monthWithoutTrans) {
		this.monthWithoutTrans = monthWithoutTrans;
	}

	@Override
	void withdraw(int accNo, double amt) {

		if (accNo != this.custAccNo) {
			System.out.println("Wrong AccNo");
			return;
		}
		if (this.accStatus != AccountStatus.ACTIVE) {
			System.out.println("Account is FREEZED / BLOCKED / CLOSED!");
			return;
		}
		long months = Period.between(this.salCreditDate, LocalDate.now()).toTotalMonths();
		if (months >= 2) {
			System.out.println("Last Transaction was Two or More Months than ago");
			this.accStatus = AccountStatus.FREEZED;
			return;
		}

		if (amt <= 0) {
			System.out.println("Amount must Greater than 0!");
			return;
		}

		if (amt > this.currentBal) {
			System.out.println("Below Current Balance");
			return;
		}
		// Salary account has no minimum balance
		this.currentBal = this.currentBal - amt;

		recordTrans(TransType.DEBIT, amt, "WithDrawal");

		System.out.println("Withdrawl SuccessFull! . New Current Balance is " + this.currentBal);
	}

	@Override
	void deposit(int accNo, double amt) {
		if (accNo != this.custAccNo) {
			System.out.println("Wrong AccNo");
			return;
		}
		if (this.accStatus != AccountStatus.ACTIVE) {
			System.out.println("Account is FREEZED / BLOCKED / CLOSED!");
			return;
		}

		if (amt <= 0) {
			System.out.println("Amount must Greater than 0!");
			return;
		}

		this.currentBal = this.currentBal + amt;

		recordTrans(TransType.CREDIT, amt, "DEPOSIT");

		System.out.println("Deposit Succesful! New Balance: " + this.currentBal);
	}

	@Override
	void currentBal(int accNo) {
		if (accNo != this.custAccNo) {
			System.out.println("Wrong AccNo");
			return;
		}
		if (this.accStatus != AccountStatus.ACTIVE) {
			System.out.println("Account is FREEZED / BLOCKED / CLOSED!");
			return;
		}

		System.out.println("Current Balance : " + this.currentBal);
	}

	@Override
	double calculateTotalInterest() {
		// TODO Auto-generated method stub
		double intrest = 0;
		return intrest;
	}

}

//======================= SavingAccount ======================

class SavingAccount extends Account {
	static double minBal;
	static double interestRate;
	double aqb;
	static double transLimit;
	static double penaltyForBelowMinBal;

	static {
		minBal = 3000;
	}

	static {
		interestRate = 2.5;
	}

	static {
		transLimit = 100000;
	}

	static {
		penaltyForBelowMinBal = 5000;
	}

	SavingAccount() {
		super();
		this.aqb = 00;

	}

	SavingAccount(int custAccNo, String custName, double currentBal, String emailId, AccountStatus accStatus,
			double aQB) {
		super(custAccNo, custName, currentBal, emailId, accStatus);
		this.aqb = aQB;

	}

	// Setters and Getters
	double getMinBal() {
		return minBal;
	}

	static void setMinBal(double minBal) {
		SavingAccount.minBal = minBal;
	}

	static double getInterestRate() {
		return interestRate;
	}

	static void setInterestRate(double interestRate) {
		SavingAccount.interestRate = interestRate;
	}

	double getAQB() {
		return aqb;
	}

	void setAQB(double aQB) {
		aqb = aQB;
	}

	double getTransLimit() {
		return transLimit;
	}

	void setTransLimit(double transLimit) {
		transLimit = SavingAccount.transLimit;
	}

	double getPenaltyForBelowMinBal() {
		return penaltyForBelowMinBal;
	}

	void setPenaltyForBelowMinBal(double penaltyForBelowMinBal) {
		penaltyForBelowMinBal = SavingAccount.penaltyForBelowMinBal;
	}

	void withdraw(int accNo, double amt) {
		if (accNo != this.custAccNo) {
			System.out.println("Wrong AccNo");
			return;
		}
		if (this.accStatus != AccountStatus.ACTIVE) {
			System.out.println("Account is FREEZED / BLOCKED / CLOSED!");
			return;
		}

		if (amt <= 0) {
			System.out.println("Amount must Greater than 0!");
			return;
		}

		if (amt > dailyWithdrawalLimit) {
			System.out.println("Withdrawal Limit exceeded!");
			return;
		}
		double amount = this.currentBal - amt;
		if (amount < minBal) {
			System.out.println("Insufficient Balance!");
			return;
		}

		this.currentBal = this.currentBal - amt;

		recordTrans(TransType.DEBIT, amt, "WithDrawal");

		System.out.println("Withdrawl SuccessFull! . New Current Balance is " + this.currentBal);
	}

	@Override
	void deposit(int accNo, double amt) {
		if (accNo != this.custAccNo) {
			System.out.println("Wrong AccNo");
			return;
		}
		if (this.accStatus != AccountStatus.ACTIVE) {
			System.out.println("Account is FREEZED / BLOCKED / CLOSED!");
			return;
		}

		if (amt <= 0) {
			System.out.println("Amount must Greater than 0!");
			return;
		}

		this.currentBal = this.currentBal + amt;

		this.aqb = this.aqb + amt;

		recordTrans(TransType.CREDIT, amt, "DEPOSIT");
		System.out.println("Deposit Succesful! New Balance: " + this.currentBal);
	}

	@Override
	void currentBal(int accNo) {
		if (accNo != this.custAccNo) {
			System.out.println("Wrong AccNo");
			return;
		}
		if (this.accStatus != AccountStatus.ACTIVE) {
			System.out.println("Account is FREEZED / BLOCKED / CLOSED!");
			return;
		}

		System.out.println("Current Balance for Saving Account No " + this.custAccNo + "  :  " + this.currentBal);

		double interest = (this.aqb * interestRate) / 100;
		double balanceWithInterest = this.currentBal + interest;

		System.out.println("AQB  Balance: " + this.aqb);
		System.out.println("Interest (@ " + interestRate + "%):" + interest);
		System.out.println("Balance after adding interest : " + balanceWithInterest);
	}

	@Override
	double calculateTotalInterest() {
		double totalInterest = (this.aqb * interestRate) / 100;
		return totalInterest;
	}

}

//======================== LoanAccount =======================

class LoanAccount extends Account {
	double loanAmt;
	LoanStatus loanStatus;
	static double interestRate;
	int loanMonths = 12;
	double totalEmiPaid;

	static {
		interestRate = 2.5;
	}

	public LoanAccount() {
		super();
		this.loanAmt = 00;
		this.loanStatus = LoanStatus.PENDING;
		this.totalEmiPaid = 00;

	}

	public LoanAccount(int custAccNo, String custName, double currentBal, String emailId, AccountStatus accStatus,
			double loanAmt, LoanStatus loanStatus, double totalEmiPaid) {
		super(custAccNo, custName, currentBal, emailId, accStatus);

		this.loanAmt = loanAmt;
		this.loanStatus = loanStatus;
		this.totalEmiPaid = totalEmiPaid;

	}

	// Setters and Getters
	double getLoanAmt() {
		return loanAmt;
	}

	void setLoanAmt(double loanAmt) {
		this.loanAmt = loanAmt;
	}

	LoanStatus getLoanStatus() {
		return loanStatus;
	}

	void setLoanStatus(LoanStatus loanStatus) {
		this.loanStatus = loanStatus;
	}

	static double getInterestRate() {
		return interestRate;
	}

	static void setInterestRate(double interestRate) {
		LoanAccount.interestRate = interestRate;
	}

	double getTotalEmiPaid() {
		return totalEmiPaid;
	}

	void setTotalEmiPaid(double totalEmiPaid) {
		this.totalEmiPaid = totalEmiPaid;
	}

	void withdraw(int accNo, double amt) {
		if (accNo != this.custAccNo) {
			System.out.println("Wrong AccNo");
			return;
		}
		if (this.accStatus != AccountStatus.ACTIVE) {
			System.out.println("Account is FREEZED / BLOCKED / CLOSED!");
			return;
		}

		if (amt <= 0) {
			System.out.println("Amount must Greater than 0!");
			return;
		}

		if (amt > loanAmt) {
			System.out.println("Requested amount exceeds remaining loan balance!");
			return;
		}
		this.currentBal = this.currentBal + amt;
		this.loanAmt = this.loanAmt - amt;
		recordTrans(TransType.DEBIT, amt, "WithDrawal");
		System.out.println("Withdrawl SuccessFull! . New Current Balance is " + this.currentBal
				+ ", Remaining Loan Amount is " + this.loanAmt);
	}

	@Override
	void deposit(int accNo, double amt) {
		if (accNo != this.custAccNo) {
			System.out.println("Wrong AccNo");
			return;
		}
		if (this.accStatus != AccountStatus.ACTIVE) {
			System.out.println("Account is FREEZED / BLOCKED / CLOSED!");
			return;
		}

		if (amt <= 0) {
			System.out.println("Amount must Greater than 0!");
			return;
		}

		double interestToPay = 0;

		if (amt >= interestToPay) {
			amt = amt - interestToPay;
			interestToPay = 0;
		} else {
			interestToPay = interestToPay - amt;
			amt = 0;
		}

		loanAmt = loanAmt - amt;
		totalEmiPaid = totalEmiPaid + amt;
		recordTrans(TransType.CREDIT, amt, "DEPOSIT");
		if (loanAmt <= 0) {
			loanStatus = LoanStatus.PREPAID;
			loanAmt = 0;
			System.out.println("Loan fully Repaid!");
		}

		System.out.println("Deposit succesfull! \nTotal EMI Paid : " + this.totalEmiPaid
				+ "\n Remaining Loan Amount is " + this.loanAmt);
	}

	@Override
	void currentBal(int accNo) {
		if (accNo != this.custAccNo) {
			System.out.println("Wrong AccNo");
			return;
		}
		if (this.accStatus != AccountStatus.ACTIVE) {
			System.out.println("Account is FREEZED / BLOCKED / CLOSED!");
			return;
		}
		System.out.println("Current Balance is " + this.loanAmt);
		System.out.println("Total EMI Paid : " + this.totalEmiPaid);

	}

	@Override
	double calculateTotalInterest() {
		double totalInterest = (Math.abs(this.loanAmt) * interestRate) / 100;
		return totalInterest;
	}

	void payEmi(int accNo, double emi) {
		if (accNo != this.custAccNo) {
			System.out.println("Wrong AccNo");
			return;
		}
		if (this.accStatus != AccountStatus.ACTIVE) {
			System.out.println("Account is FREEZED / BLOCKED / CLOSED!");
			return;
		}

		if (emi <= 0) {
			System.out.println("Emi amount must be greater than 0!");
			return;
		}
		if (emi > loanAmt) {
			emi = loanAmt;
		}

		loanAmt = loanAmt - emi;
		totalEmiPaid = totalEmiPaid + emi;
		recordTrans(TransType.DEBIT, emi, "EMI PAYMENT");
		if (loanAmt <= 0) {
			loanStatus = LoanStatus.PREPAID;
			System.out.println("Loan fully Repaid!");
		}

		System.out.println(" EMI Paid : " + emi + "\n Remaining Loan Amount is " + this.loanAmt + "\n Total EMI Paid : "
				+ this.totalEmiPaid);
	}

	double calculateEMI() {
		double p = Math.abs(loanAmt);
		double r = LoanAccount.interestRate / 12 / 100;
		int n = loanMonths;

		double emi = (p * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
		return emi;
	}

}

//======================== CurrentAcc =========================

class CurrentAcc extends Account {
	double overDraftLimit;
	static int transLimitPerDay;

	static {
		transLimitPerDay = 70;
	}

	CurrentAcc() {
		super();
		this.overDraftLimit = 00;

	}

	CurrentAcc(int custAccNo, String custName, double currentBal, String emailId, AccountStatus accStatus,
			double overDraftLimit) {
		super(custAccNo, custName, currentBal, emailId, accStatus);
		this.overDraftLimit = overDraftLimit;

	}

	// Setters and Getters
	double getOverDraftLimit() {
		return overDraftLimit;
	}

	void setOverDraftLimit(double overDraftLimit) {
		this.overDraftLimit = overDraftLimit;
	}

	int getTransLimitPerDay() {
		return transLimitPerDay;
	}

	void setTransLimitPerDay(int transLimitPerDay) {
		CurrentAcc.transLimitPerDay = transLimitPerDay;
	}

	@Override
	void withdraw(int accNo, double amt) {
		if (accNo != this.custAccNo) {
			System.out.println("Wrong AccNo");
			return;
		}
		if (this.accStatus != AccountStatus.ACTIVE) {
			System.out.println("Account is FREEZED / BLOCKED / CLOSED!");
			return;
		}

		if (amt <= 0) {
			System.out.println("Amount must Greater than 0!");
			return;
		}

		if (amt > dailyWithdrawalLimit) {
			System.out.println("Withdrawal Limit exceeded!");
			return;
		}

		double availBal = currentBal + overDraftLimit;
		if (amt > availBal) {
			System.out.println("Insufficient Balance!");
			return;
		}

		this.currentBal = this.currentBal - amt;
		recordTrans(TransType.DEBIT, amt, "WithDrawal");
		System.out.println("Withdrawl SuccessFull! . New Current Balance is " + this.currentBal);
	}

	@Override
	void deposit(int accNo, double amt) {
		if (accNo != this.custAccNo) {
			System.out.println("Wrong AccNo");
			return;
		}
		if (this.accStatus != AccountStatus.ACTIVE) {
			System.out.println("Account is FREEZED / BLOCKED / CLOSED!");
			return;
		}

		if (amt <= 0) {
			System.out.println("Amount must Greater than 0!");
			return;
		}

		this.currentBal = this.currentBal + amt;
		recordTrans(TransType.CREDIT, amt, "DEPOSIT");
		System.out.println("Deposit Succesful! New Balance: " + this.currentBal);
	}

	@Override
	void currentBal(int accNo) {
		if (accNo != this.custAccNo) {
			System.out.println("Wrong AccNo");
			return;
		}
		if (this.accStatus != AccountStatus.ACTIVE) {
			System.out.println("Account is FREEZED / BLOCKED / CLOSED!");
			return;
		}

		System.out.println("Current Balance : " + this.currentBal);
	}

	@Override
	double calculateTotalInterest() {
		double intrest = 0;
		return intrest;

	}

}
