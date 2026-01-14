package controller;

import java.util.*;
import deviceModel.*;

public class DeviceDao {

	List<Device> devices = new ArrayList<Device>();

	public void addDevice(Device d) {
		// TODO Auto-generated method stub

		if (d == null) {
			System.out.println("Cannot add null device");
			return;
		}

		for (Device dev : devices) {
			if (dev.getManufacturer().equalsIgnoreCase(d.getManufacturer())) {
				System.out.println("Device already exists: " + d.getManufacturer());
				return;
			}
		}

		devices.add(d);
		System.out.println("Device added: " + d.getManufacturer());
	}

	public void removeDevice(String name) {
		// TODO Auto-generated method stub
		if (name == null) {
			return;
		}
		for (Device d : devices) {
			if (d.getManufacturer().equalsIgnoreCase(name)) {
				devices.remove(d);
				System.out.println("Device removed: " + name);
				return;
			}
		}
		System.out.println("Device not found: " + name);
	}

	public Device getDeviceList(String name) {
		// TODO Auto-generated method stub
		if (name == null) {
			return null;
		}
		for (Device device : devices) {
			if (device.getManufacturer().equalsIgnoreCase(name))
				return device;

		}
		return null;

	}

	public List<Device> getAllDevices() {
		// TODO Auto-generated method stub
		return devices;
	}

	public void showAllDevices() {
		if (devices.isEmpty()) {
			System.out.println("No devices available");
			return;
		}

		System.out.println("---- Device List ----");
		for (Device d : devices) {
			System.out.println(d.getManufacturer() + " | " + d.getDeviceType() + " | " + d.getDeviceStatus());
		}
	}

	public Device getDeviceByName(String manufacturer) {
		// TODO Auto-generated method stub
		if (manufacturer == null || manufacturer.trim().isEmpty()) {
			return null;
		}

		String trimmedName = manufacturer.trim();
		for (Device device : devices) {
			if (device.getManufacturer().equalsIgnoreCase(trimmedName)) {
				return device;
			}
		}
		return null;
	}

}
