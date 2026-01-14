package model;

import java.util.Scanner;

public class BankView {

    private Scanner sc = new Scanner(System.in);

    public void showMainMenu() {

        System.out.println("\n================================================");
        System.out.println("              WELCOME TO BANK SYSTEM             ");
        System.out.println("================================================");
        System.out.println("1. Account Management");
        System.out.println("2. Transaction Operations");
        System.out.println("3. Loan Services");
        System.out.println("4. Reports & Statements");
        System.out.println("0. Exit");
        System.out.println("------------------------------------------------");
        System.out.print("Enter Choice : ");
    }

    public void showAccountMenu() {

        System.out.println("\n------------- ACCOUNT MANAGEMENT ---------------");
        System.out.println("1. Open New Account");
        System.out.println("2. Close Account");
        System.out.println("3. Show Account Status");
        System.out.println("4. Show All Accounts");
        System.out.println("0. Back");
        System.out.println("------------------------------------------------");
        System.out.print("Enter Choice : ");
    }

    public void showTransactionMenu() {

        System.out.println("\n------------- TRANSACTION MENU -----------------");
        System.out.println("1. Deposit Money");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Show Transaction History");
        System.out.println("4. Show All Bank Transactions");
        System.out.println("0. Back");
        System.out.println("------------------------------------------------");
        System.out.print("Enter Choice : ");
    }

    public void showLoanMenu() {

        System.out.println("\n---------------- LOAN SERVICES -----------------");
        System.out.println("1. Apply Loan");
        System.out.println("2. Pay EMI");
        System.out.println("3. Calculate EMI");
        System.out.println("4. Loan Status");
        System.out.println("0. Back");
        System.out.println("------------------------------------------------");
        System.out.print("Enter Choice : ");
    }

    public void showReportMenu() {

        System.out.println("\n-------------- REPORTS & EOD -------------------");
        System.out.println("1. Apply Interest");
        System.out.println("2. End Of Day (EOD) Report");
        System.out.println("0. Back");
        System.out.println("------------------------------------------------");
        System.out.print("Enter Choice : ");
    }
}
