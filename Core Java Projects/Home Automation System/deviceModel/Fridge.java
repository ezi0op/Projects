package deviceModel;

enum FridgeMode {
	NORMAL, FAST_COOL, DEFROST
}

public class Fridge extends Device implements TemperatureControllable {

	int temperatureSetting;
	FridgeMode mode = FridgeMode.DEFROST;

	public Fridge() {
		// TODO Auto-generated constructor stub
		super();
	}

	// Parameterized Constructor
	public Fridge(boolean isOn, String manufacturer, String modelNumber, DeviceStates deviceStatus) {
		super(isOn, manufacturer, modelNumber, deviceStatus);

	}

	// Setters and Getters

	public int getTemperatureSetting() {
		return temperatureSetting;
	}

	public void setTemperatureSetting(int temperatureSetting) {
		this.temperatureSetting = temperatureSetting;
	}

	@Override
	public String toString() {
		return super.toString() + "Fridge [temperatureSetting=" + temperatureSetting + ", mode=" + mode + "]";
	}

	@Override
	public void increaseTemperature(int t) {
		// TODO Auto-generated method stub
		this.temperatureSetting = this.temperatureSetting + t;
		System.out.println("Increase Temperature : " + this.temperatureSetting);

	}

	@Override
	public void decreaseTemperature(int t) {
		// TODO Auto-generated method stub
		this.temperatureSetting = this.temperatureSetting - t;
		System.out.println("Decrease Temperature : " + this.temperatureSetting);
	}

	@Override
	public double getCurrentTemperature() {
		// TODO Auto-generated method stub
		return this.temperatureSetting;

	}

	@Override
	public void getStatus() {
		// TODO Auto-generated method stub
		if (this.getDeviceStatus() == DeviceStates.ON) {
			System.out.println("Device is ON!");
		} else if (this.getDeviceStatus() == DeviceStates.OFF) {
			System.out.println("Device is OFF!");
		} else if (this.getDeviceStatus() == DeviceStates.IDLE) {
			System.out.println("Device is IDLE!");
		} else if (this.getDeviceStatus() == DeviceStates.ERROR) {
			System.out.println("Device has Some ERROR!!");
		} else if (this.getDeviceStatus() == DeviceStates.DISCONNECTED) {
			System.out.println("Device is DISCONNECTED!!!!");
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
		System.out.println("Fridge is : " + this.DeviceStatus);

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
		System.out.println("Fridge is : " + this.DeviceStatus);
	}

	@Override
	public void setMode(String mode) {
		// TODO Auto-generated method stub
		if (isOn) {
			this.mode = FridgeMode.FAST_COOL;
		} else {
			this.mode = FridgeMode.NORMAL;
		}
		System.out.println("Fridge Mode : " + this.mode);
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

		System.out.println("Total Active Time for Fridge : " + ": " + (activeTime / 1000) + " ms");

	}

	@Override
	public DeviceType getDeviceType() {
		// TODO Auto-generated method stub

		return DeviceType.FRIDGE;
	}

}
