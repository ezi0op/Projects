package view;

import java.util.*;

import model.*;
import controller.*;

public class PlayerView {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		PlayerController pc = new PlayerController();

		System.out.println("\n"
				+ "╔══════════════════════════════════════════════════════════════════════════════════════════════╗\n"
				+ "║                                                                                              ║\n"
				+ "║           ██████╗ ██╗      █████╗ ██╗   ██╗███████╗██████╗     ███╗   ███╗ ██████╗ ███████╗   ║\n"
				+ "║           ██╔══██╗██║     ██╔══██╗╚██╗ ██╔╝██╔════╝██╔══██╗    ████╗ ████║██╔════╝ ██╔════╝   ║\n"
				+ "║           ██████╔╝██║     ███████║ ╚████╔╝ █████╗  ██████╔╝    ██╔████╔██║██║  ███╗███████╗   ║\n"
				+ "║           ██╔═══╝ ██║     ██╔══██║  ╚██╔╝  ██╔══╝  ██╔══██╗    ██║╚██╔╝██║██║   ██║╚════██║   ║\n"
				+ "║           ██║     ███████╗██║  ██║   ██║   ███████╗██║  ██║    ██║ ╚═╝ ██║╚██████╔╝███████║   ║\n"
				+ "║           ╚═╝     ╚══════╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝  ╚═╝    ╚═╝     ╚═╝ ╚═════╝ ╚══════╝   ║\n"
				+ "║                                                                                              ║\n"
				+ "║                     Welcome to the Player Management System!                                 ║\n"
				+ "╚══════════════════════════════════════════════════════════════════════════════════════════════╝\n");

		while (true) {

			System.out.println(
					"\n" + "╔══════════════════════════════════ MAIN MENU ══════════════════════════════════╗\n"
							+ " ║                                                                              ║\n"
							+ " ║  1. Add New Player                                                           ║\n"
							+ " ║  2. Delete Player                                                            ║\n"
							+ " ║  3. Update Player Details                                                    ║\n"
							+ " ║  4. Display All Players                                                      ║\n"
							+ " ║  5. Display Players (Sorted)                                                 ║\n"
							+ " ║  6. Search Player by Jersey Number                                           ║\n"
							+ " ║  7. Verify Player Birthday                                                   ║\n"
							+ " ║  8. Exit                                                                     ║\n"
							+ " ║                                                                              ║\n"
							+ "╚══════════════════════════════════════════════════════════════════════════════╝");

			System.out.print("\nEnter your choice: ");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:

				System.out.println("\nJerseyNo :");
				int jerseyNo = sc.nextInt();
				sc.nextLine();
				System.out.println("Name :");
				String name = sc.next();
				System.out.println("Runs :");
				int runs = sc.nextInt();
				sc.nextLine();

				System.out.println("Wickets :");
				int wickets = sc.nextInt();
				sc.nextLine();

				System.out.println("MatchesPlayed :");
				int matchesPlayed = sc.nextInt();
				sc.nextLine();

				System.out.print("DOB (dd mm yyyy): ");
				int day = sc.nextInt();
				int month = sc.nextInt();
				int year = sc.nextInt();

				DOB dob = new DOB(day, month, year);

				pc.addPlayer(new Player(jerseyNo, name, runs, wickets, matchesPlayed, dob));
				break;
			case 2:
				System.out.println("\n==============================================\n");
				System.out.println("\nEnter the Player JerseyNo to Delete  :");
				int no = sc.nextInt();
				System.out.println("\n==============================================\n");
				pc.deletePlayer(no);
				break;
			case 3:
				pc.updatePlayer();
				break;
			case 4:
				pc.DisplayAllPlayer();
				break;
			case 5:
				pc.sortedPlayer();
				break;
			case 6:
				System.out.print("\nEnter JerseyNo to Search Player: ");
				int jNo = sc.nextInt();
				pc.searchPlayer(jNo);
				break;
			case 7:
				pc.VerifyBirthday();
				break;
			case 8:
				System.out.println("Exiting program. Goodbye!");
				System.exit(0);
				break;

			default:
				System.out.println("Invalid choice! Please try again.");

			}

		}

	}
}
