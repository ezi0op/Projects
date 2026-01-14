package homeModel;

import java.util.*;

import deviceModel.*;

public  class Home {
	String roomName;

	List<Device> devices = new ArrayList<Device>();

	public Home(String roomName) {
		// TODO Auto-generated constructor stub
		this.roomName = roomName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public void addDevice(Device device) {

		if (!validateDeviceType(device)) {
			System.out.println(device.getDeviceType() + " not allowed in " + roomName);
			return;
		}
		devices.add(device);
		
	}

	public void showDevices() {
		if (devices.isEmpty()) {
			System.out.println("No devices available in " + roomName);
		}

		System.out.println("Devices in " + roomName + ":");
		for (int i = 0; i < devices.size(); i++) {
			System.out.println(
					(i + 1) + ". " + devices.get(i).getManufacturer() + " (" + devices.get(i).getDeviceType() + " )");
		}
	}
	
	public boolean removeDevice(String deviceName) {
		Iterator<Device> it= devices.iterator();
		while(it.hasNext()) {
			Device d = it.next();
			if(d.getManufacturer().equalsIgnoreCase(deviceName)) {
				it.remove();
				System.out.println("Device removed: "+deviceName);
				return true;
			}
		}
		return false;
	}

	

	public boolean validateDeviceType(Device device) {
		return true;
	}

}
