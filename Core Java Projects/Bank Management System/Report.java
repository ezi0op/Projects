package model;


//========================= Report ============================


public class Report {
	double totalDepsoit = 0;
	double totalWithdrawal = 0;

	public void showReport(BankModel model) {
		totalDepsoit = 0;
		totalWithdrawal = 0;
		for (int i = 0; i < model.accCount; i++) {
			Account a = model.accs[i];

			for (int j = 0; j < a.tcount; j++) {
				Transaction t = a.trans[j];

				if (t.transType == TransType.CREDIT) {
					totalDepsoit = totalDepsoit + t.transAmt;
				} else if (t.transType == TransType.DEBIT) {
					totalWithdrawal = totalWithdrawal + t.transAmt;
				}
			}

		}
		System.out.println("============= DAILY REPORT =============");
		System.out.println("Total Deposits : " + totalDepsoit);
		System.out.println("Total Withdrawals : " + totalWithdrawal);
		System.out.println("========================================");
	}

}
