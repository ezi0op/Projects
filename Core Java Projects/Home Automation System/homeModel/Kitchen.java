package homeModel;



import deviceModel.Device;
import deviceModel.DeviceType;

public class Kitchen extends Home {

	public Kitchen() {
		// TODO Auto-generated constructor stub
		super("Kitchen");
	}

	@Override
	public boolean validateDeviceType(Device device) {

		DeviceType type = device.getDeviceType();

		return type == DeviceType.FRIDGE | type == DeviceType.LIGHT || type == DeviceType.FAN || type == DeviceType.SPEAKER;
	}

}
