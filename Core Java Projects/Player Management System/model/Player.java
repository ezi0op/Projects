package model;

public class Player {

	int jerseyNo;
	String name;
	int runs;
	int wickets;
	int matchesPlayed;
	DOB dob;

	public Player(int jerseyNo, String name, int runs, int wickets, int matchesPlayed, DOB dob) {
		this.jerseyNo = jerseyNo;
		this.name = name;
		this.runs = runs;
		this.wickets = wickets;
		this.matchesPlayed = matchesPlayed;
		this.dob = dob;
	}

	public DOB getDob() {
		return dob;
	}

	public int getJerseyNo() {
		return jerseyNo;
	}

	public void setJerseyNo(int jerseyNo) {
		this.jerseyNo = jerseyNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public int getMatchesPlayed() {
		return matchesPlayed;
	}

	public void setMatchesPlayed(int matchesPlayed) {
		this.matchesPlayed = matchesPlayed;
	}

	public void setDob(DOB dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Jersey No : " + jerseyNo + "\nName      : " + name + "\nRuns      : " + runs + "\nWickets   : "
				+ wickets + "\nMatches   : " + matchesPlayed + "\nDOB       : " + dob
				+ "\n------------------------------";
	}

}
