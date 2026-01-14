package homeModel;

import deviceModel.Device;
import deviceModel.DeviceType;

public class Hall extends Home {
	public Hall() {
		// TODO Auto-generated constructor stub
	super("Hall");
	}
	
	@Override
	public boolean validateDeviceType(Device device) {

		DeviceType type = device.getDeviceType();

		return type == DeviceType.AC | type == DeviceType.LIGHT || type == DeviceType.FAN || type == DeviceType.HEATER || type == DeviceType.SPEAKER || type == DeviceType.TV;
	}

}
