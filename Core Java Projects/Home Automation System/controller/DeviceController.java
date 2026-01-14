package controller;

import deviceModel.*;

public class DeviceController {
	
	

	public void turnON(Device d) {
		d.turnON();
	}

	public void turnOFF(Device d) {
		d.turnOFF();
	}

	public void showStatus(Device d) {
		d.getStatus();
	}

	public void recordUsage(Device d) {
		d.recordUsageTime();
	}

	

	public void showDeviceType(Device d) {
		d.getDeviceType();
	}
	
	
	

}
