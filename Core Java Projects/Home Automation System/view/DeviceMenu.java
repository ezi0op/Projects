package view;

import java.util.*;

import controller.*;
import deviceModel.*;

public class DeviceMenu {
	public static void showDeviceMenu(Device device) {

		Scanner sc = new Scanner(System.in);

		int choice = -1;

		do {
			System.out.println(
					"\n🎛️ BASIC CONTROLS - " + device.getManufacturer() + " (" + device.getDeviceType() + ")");
			System.out.println("Status: " + device.getDeviceStatus());
			System.out.println("1. 🔛 Turn ON");
			System.out.println("2. 🔴 Turn OFF");
			System.out.println("3. ℹ️ Show Status");
			System.out.println("4. ⏱️ Record Usage Time");
			System.out.println("5. 📊 Total Usage Time");
			System.out.println("6. ⚙️ Advanced Controls");
			System.out.println("0. ⬅️ Back");
			System.out.print("Enter choice: ");
			try {
				choice = sc.nextInt();
				sc.nextLine();

				switch (choice) {

				case 1:
					device.turnON();
					break;

				case 2:
					device.turnOFF();
					break;

				case 3:
					device.getStatus();
					break;

				case 4:
					device.recordUsageTime();
					System.out.println("Usage time recorded.");
					break;
				case 5:
					device.getTotalActiveTime();
					break;
				case 6:
					DeviceMenu.showCapabilityMenu(device);
					break;
				case 0:
					System.out.println("Returning to previous menu...");
					break;

				default:
					System.out.println("Invalid choice!");
				}
			} catch (InputMismatchException e) {
				// TODO: handle exception
				System.out.println("❌ Please enter a valid number!");
				sc.nextLine();
				return;
			}
		} while (choice != 0);

	}

	public static void showCapabilityMenu(Device device) {

		Scanner sc = new Scanner(System.in);

		int choice = -1;

		do {
			System.out.println("\n⚙️ ADVANCED FEATURES - " + device.getManufacturer());

			if (device instanceof TemperatureControllable)
				System.out.println("1. Temperature Controls");

			if (device instanceof SpeedControllable)
				System.out.println("2. Speed Controls");

			if (device instanceof BrightnessControllable)
				System.out.println("3. Brightness Controls");

			if (device instanceof EntertainmentControllable)
				System.out.println("4. Entertainment Controls");

			if (device instanceof WaterFlowControllable)
				System.out.println("5. Water Flow Controls");

			System.out.println("0. Back");
			System.out.print("Enter choice: ");
			try {

				choice = sc.nextInt();
				sc.nextLine();

				switch (choice) {

				case 1: {
					if (device instanceof TemperatureControllable)
						showTempOption((TemperatureControllable) device);
				}
					break;
				case 2: {
					if (device instanceof SpeedControllable)
						showSpeedOption((SpeedControllable) device);
				}
					break;
				case 3: {
					if (device instanceof BrightnessControllable)
						showBrightnessOptions((BrightnessControllable) device);
				}
					break;
				case 4: {
					if (device instanceof EntertainmentControllable)
						showEntertainmentOptions((EntertainmentControllable) device);
				}
					break;
				case 5: {
					if (device instanceof WaterFlowControllable)
						showWaterFlowOption((WaterFlowControllable) device);
				}
					;
				case 0:
					System.out.println("Back...");
					break;
				default:
					System.out.println("Invalid choice!");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("❌ Please enter a valid number!");
				sc.nextLine();
			}
		} while (choice != 0);

	}

	public static void showTempOption(TemperatureControllable device) {

		Scanner sc = new Scanner(System.in);

		int choice = -1;

		do {
			System.out.println("\n🌡️ TEMPERATURE CONTROL");
			System.out.println("─────────────────────────────");
			System.out.println("1. ℹ️  Show Status");
			System.out.println("2. 🔥 Increase Temperature");
			System.out.println("3. ❄️  Decrease Temperature");
			System.out.println("4. 🌡️  Current Temperature");
			System.out.println("5. ⚙️  Set Mode");
			System.out.println("0. ⬅️  Back");
			System.out.println("─────────────────────────────");
			System.out.print("Choose: ");
			try {
				choice = sc.nextInt();
				sc.nextLine(); // consume newline

				switch (choice) {

				case 1:
					device.getStatus();
					break;

				case 2:
					System.out.print("Enter temperature to increase: ");
					int inc = sc.nextInt();
					device.increaseTemperature(inc);
					break;

				case 3:
					System.out.print("Enter temperature to decrease: ");
					int dec = sc.nextInt();
					device.decreaseTemperature(dec);
					break;

				case 4:
					System.out.println("Current Temperature : " + device.getCurrentTemperature() + " °C");

					break;

				case 5: {
					System.out.print("Enter mode (e.g., COOL, HEAT, AUTO, ECO): ");
					String mode = sc.nextLine().trim();
					if (!mode.isEmpty()) {
						device.setMode(mode);
					} else {
						System.out.println("Mode not changed (empty input).");
					}
				}
					break;

				case 0:
					System.out.println("Exiting Temperature Menu...");
					break;

				default:
					System.out.println("Invalid choice!");
				}
			} catch (InputMismatchException e) {
				System.out.println("❌ Please enter a valid number!");
				sc.nextLine();
				return;
			}
		} while (choice != 0);

	}

	public static void showBrightnessOptions(BrightnessControllable device) {
		Scanner sc = new Scanner(System.in);

		int choice = -1;

		do {
			System.out.println("\n💡 BRIGHTNESS CONTROL");
			System.out.println("─────────────────────────────");
			System.out.println("1. 🔆 Increase Brightness");
			System.out.println("2. 🔅 Decrease Brightness");
			System.out.println("3. ℹ️  Current Brightness");
			System.out.println("4. 🌙 Toggle Night Mode");
			System.out.println("5. ⚙️  Set Mode");
			System.out.println("0. ⬅️  Back");
			System.out.println("─────────────────────────────");
			System.out.print("Choose: ");
			try {
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {

				case 1:
					System.out.print("Enter brightness value to increase: ");
					int inc = sc.nextInt();
					device.increaseBrightness(inc);
					break;

				case 2:
					System.out.print("Enter brightness value to decrease: ");
					int dec = sc.nextInt();
					device.decreaseBrightness(dec);
					break;

				case 3:
					device.getCurrentBrightness();
					break;

				case 4:
					device.toggleNightMode();
					break;

				case 5:
					device.setMode();
					break;

				case 0:
					System.out.println("Exiting Brightness Menu...");
					break;

				default:
					System.out.println("Invalid choice!");
				}
			} catch (InputMismatchException e) {
				System.out.println("❌ Please enter a valid number!");
				sc.nextLine();
			}
		} while (choice != 0);

	}

	public static void showEntertainmentOptions(EntertainmentControllable device) {
		Scanner sc = new Scanner(System.in);

		int choice = -1;
		int value;

		do {
			System.out.println("\n🎵 ENTERTAINMENT CONTROL");
			System.out.println("─────────────────────────────");
			System.out.println("1. 🔊 Volume Up");
			System.out.println("2. 🔉 Volume Down");
			System.out.println("3. 🔇 Mute");
			System.out.println("4. 🔊 Unmute");
			System.out.println("5. ℹ️  Current Volume");
			System.out.println("6. ▶️  Play");
			System.out.println("7. ⏸️  Pause");
			System.out.println("8. ⏹️  Stop");
			System.out.println("0. ⬅️  Back");
			System.out.println("─────────────────────────────");
			System.out.print("Choose: ");
			try {
				choice = sc.nextInt();
				sc.nextLine();

				switch (choice) {

				case 1:
					System.out.print("Increase volume by: ");
					value = sc.nextInt();
					device.volumeUp(value);
					break;

				case 2:
					System.out.print("Decrease volume by: ");
					value = sc.nextInt();
					device.volumeDown(value);
					break;

				case 3:
					device.mute();
					break;

				case 4:
					device.unmute();
					break;

				case 5:
					device.getCurrentVolume();
					break;

				case 6:
					device.play();
					break;

				case 7:
					device.pause();
					break;

				case 8:
					device.stop();
					break;

				case 0:
					System.out.println("Exiting Entertainment Menu...");
					break;

				default:
					System.out.println("Invalid choice!");
				}
			} catch (InputMismatchException e) {
				System.out.println("❌ Please enter a valid number!");
				sc.nextLine();
			}
		} while (choice != 0);

	}

	public static void showSpeedOption(SpeedControllable device) {
		Scanner scn = new Scanner(System.in);

		int choice = -1;

		do {
			System.out.println("\n💨 SPEED CONTROL");
			System.out.println("─────────────────────────────");
			System.out.println("1. ⬆️  Increase Speed");
			System.out.println("2. ⬇️  Decrease Speed");
			System.out.println("3. 🔄 Change Mode");
			System.out.println("4. ℹ️  Current Speed");
			System.out.println("0. ⬅️  Back");
			System.out.println("─────────────────────────────");
			System.out.print("Choose: ");
			try {
				choice = scn.nextInt();
				scn.nextLine();
				switch (choice) {

				case 1:
					System.out.print("Enter speed increment: ");
					int inc = scn.nextInt();
					device.increaseSpeed(inc);
					break;

				case 2:
					System.out.print("Enter speed decrement: ");
					int dec = scn.nextInt();
					device.decreaseSpeed(dec);
					break;

				case 3:
					device.changeMode();
					break;

				case 4:
					device.getCurrentSpeed();
					break;

				case 0:
					System.out.println("Exiting Speed Menu...");
					break;

				default:
					System.out.println("Invalid choice!");
				}
			} catch (InputMismatchException e) {
				System.out.println("❌ Please enter a valid number!");
				scn.nextLine();
			}
		} while (choice != 0);

	}

	public static void showWaterFlowOption(WaterFlowControllable device) {
		Scanner sc = new Scanner(System.in);

		int choice = -1;
		int value;

		do {
			System.out.println("\n🚰 WATER FLOW CONTROL");
			System.out.println("─────────────────────────────");
			System.out.println("1. ➕ Increase Flow");
			System.out.println("2. ➖ Decrease Flow");
			System.out.println("3. ℹ️  Current Flow Level");
			System.out.println("0. ⬅️  Back");
			System.out.println("─────────────────────────────");
			System.out.print("Choose: ");
			try {
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {

				case 1:
					System.out.print("Enter value to increase: ");
					value = sc.nextInt();
					device.increaseWaterFlow(value);
					break;

				case 2:
					System.out.print("Enter value to decrease: ");
					value = sc.nextInt();
					device.decreaseWaterFlow(value);
					break;

				case 3:
					device.getCurrentWaterFlowLevel();
					break;

				case 0:
					System.out.println("Exiting Water Flow Menu...");
					break;

				default:
					System.out.println("Invalid choice!");
				}
			} catch (InputMismatchException e) {
				System.out.println("❌ Please enter a valid number!");
				sc.nextLine();
			}
		} while (choice != 0);

	}
}