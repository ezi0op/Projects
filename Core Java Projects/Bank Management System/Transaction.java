package model;

import java.time.LocalDate;

//======================== TransType ==========================

enum TransType {
	DEBIT, CREDIT
}

//======================= Transaction =========================

public class Transaction {
	TransType transType;
	long transId;
	String transDesc;
	double transAmt;
	LocalDate transDate;
	double postBal;

	Transaction() {

	}// Default Constructor

	Transaction(TransType transType, long transId, String transDesc, double transAmt, LocalDate transDate,
			double postBal) {

		this.transType = transType;
		this.transId = transId;
		this.transDesc = transDesc;
		this.transAmt = transAmt;
		this.transDate = transDate;
		this.postBal = postBal;
	}// Parametrized Constructor

	// setter and getters
	public TransType getTransType() {
		return transType;
	}

	public void setTransType(TransType transType) {
		this.transType = transType;
	}

	public long getTransId() {
		return transId;
	}

	public void setTransId(long transId) {
		this.transId = transId;
	}

	public String getTransDesc() {
		return transDesc;
	}

	public void setTransDesc(String transDesc) {
		this.transDesc = transDesc;
	}

	public double getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(double transAmt) {
		this.transAmt = transAmt;
	}

	public LocalDate getTransDate() {
		return transDate;
	}

	public void setTransDate(LocalDate transDate) {
		this.transDate = transDate;
	}

	@Override
	public String toString() {
		return "TxnID: " + transId + " | Type: " + transType + " | Amount: " + transAmt + " | Date: " + transDate
				+ " | Desc: " + transDesc + " | PostBal: " + postBal;
	}

}