package homeModel;


import deviceModel.*;

public class BedRoom extends Home {

	public BedRoom() {
		// TODO Auto-generated constructor stub
	super("Bed Home");
	}
	@Override
	public boolean validateDeviceType(Device device) {

		DeviceType type = device.getDeviceType();

		return type == DeviceType.AC | type == DeviceType.LIGHT || type == DeviceType.FAN|| type == DeviceType.HEATER|| type == DeviceType.SPEAKER|| type == DeviceType.TV;
	}

}
