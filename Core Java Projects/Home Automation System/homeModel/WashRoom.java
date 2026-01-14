package homeModel;




import deviceModel.Device;
import deviceModel.DeviceType;

public class WashRoom extends Home {
	
	public WashRoom() {
		// TODO Auto-generated constructor stub
	super("Wash Home");
	}
	@Override
	public boolean validateDeviceType(Device device) {

		DeviceType type = device.getDeviceType();

		return type == DeviceType.SHOWER | type == DeviceType.LIGHT || type == DeviceType.HEATER;
	}
	
	
}
