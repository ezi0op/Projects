package controller;

import java.time.*;
import java.util.*;

import model.*;

public class PlayerDao {

	List<Player> PL = new ArrayList<Player>();

	{
		PL.add(new Player(7, "Dhoni", 10500, 1, 350, new DOB(7, 8, 1988)));
		PL.add(new Player(18, "Kohli", 12000, 4, 280, new DOB(12, 2, 1998)));
		PL.add(new Player(45, "Rohit", 9500, 8, 250, new DOB(27, 7, 1998)));
		PL.add(new Player(12, "Yuvraj", 8700, 111, 300, new DOB(17, 2, 1978)));
		PL.add(new Player(99, "Bumrah", 300, 150, 90, new DOB(22, 2, 1998)));
		PL.add(new Player(33, "Hardik", 3500, 80, 120, new DOB(11, 10, 1993)));
		PL.add(new Player(77, "Jadeja", 2500, 180, 190, new DOB(6, 12, 1988)));
		PL.add(new Player(10, "Sachin", 18426, 154, 463, new DOB(24, 4, 1973)));
		PL.add(new Player(11, "Dravid", 10889, 5, 344, new DOB(11, 1, 1973)));
		PL.add(new Player(24, "Ganguly", 11363, 100, 311, new DOB(8, 7, 1972)));
		PL.add(new Player(55, "Raina", 5615, 36, 226, new DOB(28, 12, 1986)));
		PL.add(new Player(90, "Ashwin", 707, 442, 116, new DOB(17, 9, 1986)));
		PL.add(new Player(93, "Shami", 220, 195, 90, new DOB(3, 9, 1990)));
		PL.add(new Player(63, "Gill", 3200, 0, 70, new DOB(8, 9, 1999)));
		PL.add(new Player(17, "Pant", 2800, 5, 110, new DOB(4, 10, 1997)));
		PL.add(new Player(1, "Rahul", 5100, 3, 170, new DOB(18, 4, 1992)));
	}

	public boolean addPlayer(Player p) {
		return PL.add(p);
	}

	public Player deletePlayer(int jNo) {

		Iterator<Player> it = PL.iterator();

		while (it.hasNext()) {
			Player p = it.next();

			if (p.getJerseyNo() == jNo) {
				it.remove();
				return p;
			}
		}

		return null;
	}

	public Player searchPlayer(int jno) {

		for (Player p : PL) {
			if (p.getJerseyNo() == jno) {
				return p;
			}
		}
		return null;
	}

	public void displayAllPlayer() {
		if (PL.isEmpty()) {
			System.out.println("No player available!");
			return;
		}

		System.out.println("\n========== Player List ==========\n");
		for (Player p : PL) {
			System.out.println(p);
		}
	}

	public void SortByRunAsc() {
		Collections.sort(PL, new MyRunComparator());
	}

	public void SortByRunDesc() {
		Collections.sort(PL, new MyRunDescComparator());
	}

	public void SortByWicketAsc() {
		Collections.sort(PL, new MyWicketComparator());
	}

	public void SortByWicketDesc() {
		Collections.sort(PL, new MyWicketDescComparator());
	}

	public void verifyBirthday() {
		LocalDate today = LocalDate.now();
		boolean found = false;

		for (Player p : PL) {
			LocalDate dob = p.getDob().getDate();

			if (dob.getDayOfMonth() == today.getDayOfMonth() && dob.getMonthValue() == today.getMonthValue()) {
				System.out.println("🎉 Happy Birthday " + p.getName() + " 🎂");
				found = true;
			}
		}

		if (!found) {
			System.out.println("No player has birthday today!");
		}
	}

	public boolean updatePlayerRuns(int jerseyNo, int runs) {

		for (Player p : PL) {
			if (p.getJerseyNo() == jerseyNo) {
				p.setRuns(runs);
				return true;
			}
		}
		return false;
	}

	public boolean updatePlayerWickets(int jerseyNo, int wickets) {

		for (Player p : PL) {
			if (p.getJerseyNo() == jerseyNo) {
				p.setWickets(wickets);
				return true;
			}
		}
		return false;
	}

	public boolean updatePlayerMatches(int jerseyNo, int matches) {

		for (Player p : PL) {
			if (p.getJerseyNo() == jerseyNo) {
				p.setMatchesPlayed(matches);
				return true;
			}
		}
		return false;
	}

}
