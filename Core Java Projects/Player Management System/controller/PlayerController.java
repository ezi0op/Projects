package controller;

import java.util.List;
import java.util.Scanner;

import model.*;

public class PlayerController {
	Scanner sc = new Scanner(System.in);

	List<Player> Pl;

	PlayerDao pDao = new PlayerDao();

	public void addPlayer(Player p) {

		if (pDao.addPlayer(p)) {
			System.out.println("Player added SuccessFully!");
		} else {
			System.out.println("Player not added SuccessFully!");
		}

	}

	public void deletePlayer(int jNo) {
		Player p = pDao.deletePlayer(jNo);

		if (p != null) {
			System.out.println("Player deleted successfully!");
		} else {
			System.out.println("Player not found!");
		}
	}

	public void searchPlayer(int jNo) {

		Player p = pDao.searchPlayer(jNo);

		if (p != null) {
			System.out.println("\n========== Player Found ==========\n");
			System.out.println(p);
		} else {
			System.out.println("\n❌ Player with Jersey No " + jNo + " not found!");
		}
	}

	public void DisplayAllPlayer() {
		// TODO Auto-generated method stub

		pDao.displayAllPlayer();
	}

	public void sortedPlayer() {

		while (true) {
			System.out.println("\n================ Sort Players ================\n");
			System.out.println("1.Sort by Runs\n");
			System.out.println("2.Sort by Wickets\n");
			System.out.println("3.Back to Menu\n");
			System.out.println("==============================================\n");
			System.out.println("Enter your choice: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				while (true) {
					System.out.println("\n================ Sort By Runs ================\n");
					System.out.println("1.MinimumRuns\n");
					System.out.println("2.MaximumRuns\n");
					System.out.println("3.Back to Menu\n");
					System.out.println("==============================================\n");

					System.out.println("Enter your option: \n");
					int choice1 = sc.nextInt();

					switch (choice1) {
					case 1:
						pDao.SortByRunAsc();
						pDao.displayAllPlayer();
						break;
					case 2:
						pDao.SortByRunDesc();
						pDao.displayAllPlayer();
						break;
					case 3:
						System.out.println("Returning Sub-menu....");
						return;

					}
					if (choice1 == 3)
						break;
				}
				break;

			case 2:
				while (true) {
					System.out.println("\n================ Sort By Wickets ================\n");
					System.out.println("1.MinimumWickets\n");
					System.out.println("2.MaximumWickets\n");
					System.out.println("3.Back to Menu\n");
					System.out.println("==============================================\n");

					System.out.println("Enter your option: \n");
					int choice2 = sc.nextInt();

					switch (choice2) {
					case 1:
						pDao.SortByWicketAsc();
						pDao.displayAllPlayer();
						break;
					case 2:
						pDao.SortByWicketDesc();
						pDao.displayAllPlayer();
						break;
					case 3:
						System.out.println("Returning to sub-menu....");
						break;

					}
					if (choice2 == 3)
						break;
				}
				break;

			case 3:
				System.out.println("Returning to main-menu....");
				return;
			default:
				System.out.println("Invalid choice!!!");

			}
		}
	}

	public void updatePlayer() {

		while (true) {

			System.out.println("\n========= UPDATE PLAYER =========");
			System.out.println("1. Update Runs");
			System.out.println("2. Update Wickets");
			System.out.println("3. Update Matches");
			System.out.println("4. Back");
			System.out.print("Enter choice: ");

			int ch = sc.nextInt();

			System.out.print("Enter Jersey No: ");
			int jNo = sc.nextInt();

			boolean status = false;

			switch (ch) {
			case 1:
				System.out.print("Enter new Runs: ");
				status = pDao.updatePlayerRuns(jNo, sc.nextInt());
				break;

			case 2:
				System.out.print("Enter new Wickets: ");
				status = pDao.updatePlayerWickets(jNo, sc.nextInt());
				break;

			case 3:
				System.out.print("Enter new Matches: ");
				status = pDao.updatePlayerMatches(jNo, sc.nextInt());
				break;

			case 4:
				return;

			default:
				System.out.println("Invalid choice!");
				continue;
			}

			if (status) {
				System.out.println("✅ Player updated successfully!");
			} else {
				System.out.println("❌ Player not found!");
			}
		}
	}

	public void VerifyBirthday() {

		pDao.verifyBirthday();
	}

}
