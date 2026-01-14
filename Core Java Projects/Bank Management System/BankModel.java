package model;

//======================== BankModel ==========================

public class BankModel {
	Account[] accs;
	int accCount;
	Transaction[] allTrns;
	int allTranCount;
	long trnSeq = 2000;

	static final int ACC = 500;
	static final int ALL_TRAN = 500;

	BankModel() {
		accs = new Account[ACC];
		allTrns = new Transaction[ALL_TRAN];
		accCount = 0;
		allTranCount = 0;
	}

	Account findAcc(int accNo) {
		for (int i = 0; i < accCount; i++) {
			if (accs[i].getCustNo() == accNo) {
				return accs[i];
			}
		}
		System.out.println("----------------------------");
		System.out.println("--- Account not found! ---- ");
		System.out.println("----------------------------");
		return null;
	}

	void addACC(Account a) {
		if (accCount < ACC) {
			accs[accCount++] = a;
		}
		return;
	}

	void deleteAcc(int accNo) {
		boolean found = false;
		for (int a = 0; a < accCount; a++) {
			if (accs[a].getCustNo() == accNo) {
				accs[a] = accs[accCount - 1];
				accCount--;
				System.out.println("----------------------------");
				System.out.println("---- Account removed! -------");
				System.out.println("----------------------------");
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println("Account not Found!");
		}

	}

	void showAllAccounts() {
		if (accCount == 0) {
			System.out.println("No accountss available");
			return;
		}

		System.out.println("\n--------- All ACCOUNTS --------");
		for (int i = 0; i < accCount; i++) {
			Account a = accs[i];
			System.out.println("AccNo: " + a.getCustNo() + " | Type: " + a.getClass().getSimpleName() + " | Balance: "
					+ a.getCurrentBal() + " | Status: " + a.getAccStatus());
		}
		System.out.println("----------------------------");
	}

	void showAllTrans() {
		if (allTranCount == 0) {
			System.out.println("No bank trans Found!");
			return;
		}

		System.out.println("\n====================== All Bank TRANSACTIONS ============================");

		for (int i = 0; i < allTranCount; i++) {
			System.out.println(allTrns[i]);
		}
		System.out.println("==============================================================");
	}

	void recordBankTrans(TransType type, double amt, String desc, double postBal) {

		if (allTranCount >= ALL_TRAN) {
			System.out.println("Bank transaction storage full!");
			return;
		}

		long txnId = ++trnSeq;

		allTrns[allTranCount++] = new Transaction(type, txnId, desc, amt, java.time.LocalDate.now(), postBal);
	}

	public Account getAccountByNo(int accNo) {

		for (int i = 0; i < accCount; i++) {
			if (accs[i] != null && accs[i].getCustNo() == accNo) {
				return accs[i];
			}
		}
		return null;
	}

}
