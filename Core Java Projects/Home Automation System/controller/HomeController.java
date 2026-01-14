package controller;

import java.util.*;

import deviceModel.Device;
import homeModel.Home;

public class HomeController {
	Home home;
	Scanner sc=new Scanner(System.in);
	public void addDevice(Device d) {
		home.addDevice(d);
	}
	public void showDevices() {
		home.showDevices();
	}
	public void removeDevice() {
		System.out.println("Enter Device to remove");
		String dev=sc.next();
		home.removeDevice(dev);
	}
	
	public void validateDevice(Device device) {
		home.validateDeviceType(device);
	}
	
	public void setCurrentRoom(Home home) {
        if (home == null) {
            System.out.println("Cannot set current Room to null!");
            return;
        }
        this.home = home;
        System.out.println("Current Room set to: " + home.getRoomName());
    }
	public Home getCurrentRoom() {
        return home;
    }
}
