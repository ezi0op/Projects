package model;

import java.time.LocalDate;


public class DOB {

	private LocalDate date;

	public DOB(int day,int month,int year) {
		this.date = LocalDate.of(year, month, day);
	}

	public LocalDate getDate() {
		return date;
	}

	

	@Override
	public String toString() {
		return date.toString();
	}

	
	
	

}
