package deviceModel;

enum ShowerMode {
	NORMAL, ECO, HOT, COLD, OFF
}

public class Shower extends Device implements WaterFlowControllable {

	int waterFlowLevel;

	ShowerMode mode;

	public Shower() {
		// TODO Auto-generated constructor stub
		super();
	}

	// Paraeterized Constructor
	public Shower( boolean isOn, String manufacturer, String modelNumber, DeviceStates deviceStatus,
			int waterFlowLevel) {
		super( isOn, manufacturer, modelNumber, deviceStatus);

		this.waterFlowLevel = waterFlowLevel;

		this.mode = ShowerMode.OFF;
	}

	// Setters and Getters
	public int getWaterFlowLevel() {
		return waterFlowLevel;
	}

	public void setWaterFlowLevel(int waterFlowLevel) {
		this.waterFlowLevel = waterFlowLevel;
	}

	@Override
	public String toString() {
		return super.toString() + "Shower [waterFlowLevel=" + waterFlowLevel + ", mode=" + mode + "]";
	}

	@Override
	public void increaseWaterFlow(int s) {
		// TODO Auto-generated method stub
		this.waterFlowLevel = this.waterFlowLevel + s;
		System.out.println("Increased WaterFlow Level : " + this.waterFlowLevel);

	}

	@Override
	public void decreaseWaterFlow(int s) {
		// TODO Auto-generated method stub
		this.waterFlowLevel = this.waterFlowLevel - s;
		System.out.println("Decreased WaterFlow Level : " + this.waterFlowLevel);
	}

	@Override
	public void getCurrentWaterFlowLevel() {
		// TODO Auto-generated method stub
		System.out.println("Current WaterFlow Level : " + this.getWaterFlowLevel());
	}

	@Override
	public void getStatus() {
		// TODO Auto-generated method stub
		if (this.getDeviceStatus() == DeviceStates.ON) {
			System.out.println("Device is ON!");
		} else if (this.getDeviceStatus() == DeviceStates.OFF) {
			System.out.println("Device is OFF!");
		}

	}

	@Override
	public void turnON() {
		// TODO Auto-generated method stub
		this.DeviceStatus = DeviceStates.ON;
		if (!isOn) {
			this.isOn = true;
			lastChangedTime = System.currentTimeMillis();
		}
		mode = ShowerMode.NORMAL;
		System.out.println("Shower  : " + this.DeviceStatus);

	}

	@Override
	public void turnOFF() {
		// TODO Auto-generated method stub
		if (isOn) {
			this.DeviceStatus = DeviceStates.OFF;
			long now = System.currentTimeMillis();
			totalUsageTime = totalUsageTime + (now - lastChangedTime);
			this.isOn = false;
			lastChangedTime = now;
		}
		System.out.println("Shower  : " + this.DeviceStatus);
	}

	@Override
	public void recordUsageTime() {
		// TODO Auto-generated method stub
		if (isOn) {
			long now = System.currentTimeMillis();
			totalUsageTime = totalUsageTime + (now - lastChangedTime);
			lastChangedTime = now;
		}

	}

	@Override
	public void getTotalActiveTime() {
		// TODO Auto-generated method stub

		long activeTime;
		if (isOn) {
			long now = System.currentTimeMillis();
			activeTime = totalUsageTime + (now - lastChangedTime);
		} else {
			activeTime = totalUsageTime;
		}

		System.out.println("Total Active Time for Shower : " + ": " + (activeTime / 1000) + " seconds");

	}

	@Override
	public DeviceType getDeviceType() {
		// TODO Auto-generated method stub

		return DeviceType.SHOWER;
	}

}
