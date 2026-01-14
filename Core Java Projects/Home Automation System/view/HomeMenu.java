package view;

import java.util.*;
import controller.*;
import deviceModel.*;
import homeModel.*;

public class HomeMenu {
	static Scanner sc = new Scanner(System.in);

	static HomeDao homeDao = new HomeDao();
	static DeviceDao deviceDao = new DeviceDao();
	static HomeController homeController = new HomeController();
	static {
		Home bedRoom = new BedRoom();
		Home hall = new Hall();
		Home kitchen = new Kitchen();
		Home washRoom = new WashRoom();

		homeDao.addRoom("Bed Home");
		homeDao.addRoom("Hall");
		homeDao.addRoom("Kitchen");
		homeDao.addRoom("Wash Home");

		Home daoBedRoom = homeDao.getRoomByName("Bed Home");
		Home daoHall = homeDao.getRoomByName("Hall");
		Home daoKitchen = homeDao.getRoomByName("Kitchen");
		Home daoWashRoom = homeDao.getRoomByName("Wash Home");

		daoBedRoom.addDevice(new AC(false, "LG", "LG-AC-101", DeviceStates.IDLE));
		daoBedRoom.addDevice(new Fan(true, "Usha", "FAN-111", DeviceStates.ON));
		daoBedRoom.addDevice(new Light(true, "Philips", "LGT-101", DeviceStates.ON));

		daoHall.addDevice(new TV(true, "Sony", "TV-SONY-55", DeviceStates.ON));
		daoHall.addDevice(new Speaker(true, "JBL", "SP-JBL-01", DeviceStates.ON, 45, true));

		daoKitchen.addDevice(new Fridge(true, "LG", "FR-LG-345", DeviceStates.ON));
		daoKitchen.addDevice(new Light(false, "Syska", "LGT-202", DeviceStates.OFF));

		daoWashRoom.addDevice(new Shower(false, "Jaquar", "SH-777", DeviceStates.IDLE, 55));
		daoWashRoom.addDevice(new Heater(false, "Havells", "HT-901", DeviceStates.OFF));
	}

	public static void showRoomMenu() {

		int choice = -1;

		do {
			System.out.println("\n🏠 SMART HOME CONTROL CENTER 🏠");
			System.out.println("──────────────────────────────");
			System.out.println("1. ➕ Add New Room");
			System.out.println("2. 🗑️  Remove Room");
			System.out.println("3. 📋 Show All Rooms");
			System.out.println("4. 🚪 Select Room");
			System.out.println("5. 🔌 Add Device");
			System.out.println("6. 🏠 Show All Devices in House");
			System.out.println("0. 🚪 Exit");
			System.out.print("Enter your choice: ");
			try {
				choice = sc.nextInt();
				sc.nextLine();

				switch (choice) {
				case 1:
					addRoom();
					break;
				case 2:
					removeRoom();
					break;
				case 3:
					homeDao.showAllRooms();
					break;
				case 4:
					selectRoomAndManage();
					break;
				case 5:
					addDeviceToRegistry();
					break;
				case 6:
					showAllDevicesInHouse();
					break;
				case 0:
					System.out.println("Goodbye! Thank you for using Smart Home System.");
					break;
				default:
					System.out.println("Invalid choice! Try again.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("❌ Please enter a valid number!");
				sc.nextLine();
			}
		} while (choice != 0);

	}

	public static void addRoom() {

		System.out.print("Enter Room name: ");
		String roomName = sc.nextLine().trim();
		if (!roomName.isEmpty()) {
			homeDao.addRoom(roomName);
		} else {
			System.out.println("Room name cannot be empty!");
		}

	}

	public static void removeRoom() {

		System.out.print("Enter Room name: ");
		String roomName = sc.nextLine().trim();
		if (!roomName.isEmpty()) {
			homeDao.removeRoom(roomName);
		} else {
			System.out.println("Room name cannot be empty!");
		}

	}

	public static void selectRoomAndManage() {

		homeDao.showAllRooms();
		if (homeDao.getRooms().isEmpty()) {
			return;
		}

		System.out.print("Enter Room number to select: ");
		int roomNum = -1;

		try {
			roomNum = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("❌ Please enter a valid number!");
			sc.nextLine();
			return;
		}

		List<Home> homes = homeDao.getRooms();

		Home selectedRoom = homes.get(roomNum-1);
		homeController.setCurrentRoom(selectedRoom);
		System.out.println("Now managing: " + selectedRoom.getRoomName());

		manageSelectedRoom(selectedRoom);

	}

	public static void manageSelectedRoom(Home home) {

		try {
			int choice;

			do {
				System.out.println("\n🔹 Currently Managing: " + home.getRoomName() + " 🔹");
				System.out.println("──────────────────────────────────");
				System.out.println("1. 👀 Show Devices in This Home");
				System.out.println("2. ➕ Add Device to This Home");
				System.out.println("3. 🗑️ Remove Device from This Home");
				System.out.println("4. 🎮 Select Device & Control");
				System.out.println("0. ⬅️ Back to Main Menu");
				System.out.print("Enter choice: ");

				choice = sc.nextInt();
				sc.nextLine();

				switch (choice) {
				case 1:
					home.showDevices();
					break;
				case 2:
					addDeviceToCurrentRoom(home);
					break;
				case 3:
					removeDeviceFromCurrentRoom(home);
					break;
				case 4:
					selectAndControlDevice(home);
					break;
				case 0:
					System.out.println("Returning to main menu...");
					break;
				default:
					System.out.println("Invalid choice!");
					break;
				}
			} while (choice != 0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error Manage Home " + e.getMessage());
			sc.nextLine();
			return;
		}

	}

	public static void addDeviceToCurrentRoom(Home home) {

		deviceDao.showAllDevices();
		if (deviceDao.getAllDevices().isEmpty()) {
			System.out.println("No devices registered yet. Add devices first from main menu.");
			return;
		}

		System.out.print("Enter device manufacturer name to add : " + home.getRoomName() + ": ");
		String manufacturer = sc.nextLine().trim();

		Device device = deviceDao.getDeviceByName(manufacturer);
		if (device == null) {
			System.out.println("❌ Device not found!");
			return;
		}

		home.addDevice(device);

	}

	private static void removeDeviceFromCurrentRoom(Home home) {

		home.showDevices();
		if (home.getDevices().isEmpty()) {
			return;
		}

		System.out.print("Enter device  name to remove: ");
		String name = sc.nextLine().trim();

		boolean removed = home.removeDevice(name);
		if (removed) {
			System.out.println("Device removed successfully.");
		} else {
			System.out.println("Device not found in this home.");
		}

	}

	private static void selectAndControlDevice(Home home) {

		home.showDevices();
		if (home.getDevices().isEmpty()) {
			System.out.println("No devices in this home.");
			return;
		}

		System.out.print("Enter device number to control: ");
		int devNum = -1;

		try {

			devNum = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			// TODO: handle exception
			System.out.println("❌ Please enter a valid number!");
			sc.nextLine();
			return;
		}

		List<Device> devices = home.getDevices();

		Device selectedDevice = devices.get(devNum);
		System.out.println(
				"Controlling: " + selectedDevice.getManufacturer() + " (" + selectedDevice.getDeviceType() + ")");

		DeviceMenu.showDeviceMenu(selectedDevice);

	}

	private static void addDeviceToRegistry() {

		System.out.println("\n🔌 ADD NEW DEVICE");
		System.out.println("─────────────────────────────");
		System.out.println("Select device type:");
		System.out.println("1. 📺 TV");
		System.out.println("2. 🔊 Speaker");
		System.out.println("3. ❄️ AC");
		System.out.println("4. 💨 Fan");
		System.out.println("5. 💡 Light");
		System.out.println("6. 🔥 Heater");
		System.out.println("7. 🚿 Shower");
		System.out.println("8. ❄️ Fridge");
		System.out.println("─────────────────────────────");
		System.out.print("Choice: ");
		int type = -1;

		try {
			type = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			// TODO: handle exception
			System.out.println("❌ Please enter a valid number!");
			sc.nextLine();
			return;
		}

		System.out.print("Enter manufacturer: ");
		String manufacturer = sc.nextLine().trim();
		System.out.print("Enter model number: ");
		String model = sc.nextLine().trim();

		Device device = null;
		boolean isOn = false;
		DeviceStates status = DeviceStates.IDLE;

		switch (type) {
		case 1:
			device = new TV(isOn, manufacturer, model, status);
			break;
		case 2:
			device = new Speaker(isOn, manufacturer, model, status, 50, true);
			break;
		case 3:
			device = new AC(isOn, manufacturer, model, status);
			break;
		case 4:
			device = new Fan(isOn, manufacturer, model, status);
			break;
		case 5:
			device = new Light(isOn, manufacturer, model, status);
			break;
		case 6:
			device = new Heater(isOn, manufacturer, model, status);
			break;
		case 7:
			device = new Shower(isOn, manufacturer, model, status, 5);
			break;
		case 8:
			device = new Fridge(isOn, manufacturer, model, status);
			break;
		default:
			System.out.println("Invalid device type!");
			return;
		}

		deviceDao.addDevice(device);

	}

	private static void showAllDevicesInHouse() {
		System.out.println("\n🏠 ALL DEVICES IN THE HOUSE 🏠");
		System.out.println("────────────────────────────────────");

		boolean foundAny = false;

		for (Home home : homeDao.getRooms()) {
			if (!home.getDevices().isEmpty()) {
				foundAny = true;
				System.out.println("\n📍 " + home.getRoomName() + ":");
				for (int i = 0; i < home.getDevices().size(); i++) {
					Device d = home.getDevices().get(i);
					System.out.println((i + 1) + ". " + d.getManufacturer() + " (" + d.getDeviceType() + ") - "
							+ d.getDeviceStatus());
				}
			}
		}
		if (!foundAny) {
	        System.out.println("   😔 No devices found in any room.");
	    }
	}

}
