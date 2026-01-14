package controller;

import java.util.*;

import homeModel.Home;

public class HomeDao {

	private List<Home> homes = new ArrayList<>();

	public void addRoom(String roomName) {

		if (roomName == null || roomName.isEmpty()) {
			System.out.println("Cannot add null or empty Room");
			return;
		}
		String trimmedName =roomName.trim();
			
		for (Home home:homes) {
			if (home.getRoomName().equalsIgnoreCase(trimmedName)) {
				System.out.println("Room already exists: " + trimmedName);
				return;
			}
		}

		homes.add(new Home(trimmedName));
		
	}

	public boolean removeRoom(String roomName) {

		if (roomName == null || roomName.trim().isEmpty()) {
            System.out.println("Room name cannot be null or empty.");
            return false;
        }

        String trimmedName = roomName.trim();

        
        Iterator<Home> iterator = homes.iterator();
        while (iterator.hasNext()) {
            Home home = iterator.next();
            if (home.getRoomName().equalsIgnoreCase(trimmedName)) {
                iterator.remove();
                System.out.println("Room removed: " + trimmedName);
                return true;
            }
        }

        System.out.println("Room not found: " + trimmedName);
        return false;
	}

	public List<Home> getRooms() {
		return homes;
	}
	public Home getRoomByName(String roomName) {
        if (roomName == null || roomName.trim().isEmpty()) {
            return null;
        }

        String trimmedName = roomName.trim();
        for (Home home : homes) {
            if (home.getRoomName().equalsIgnoreCase(trimmedName)) {
                return home;
            }
        }
        return null;
    }
	public void showAllRooms() {
        if (homes.isEmpty()) {
            System.out.println("No Room available.");
            return;
        }

        System.out.println("\n===== AVAILABLE ROOMS =====");
        for (int i = 0; i < homes.size(); i++) {
            System.out.println((i + 1) + ". " + homes.get(i).getRoomName());
        }
        System.out.println("===========================\n");
    }

}
