package deviceModel;

enum ACMode {
	COOL, HEAT, FAN, DRY, AUTO
}

public class AC extends Device implements TemperatureControllable {

	int temp;

	ACMode mode = ACMode.AUTO;

	public AC() {
		// TODO Auto-generated constructor stub
		super();
	}

	// Parameterized Constructor
	public AC(boolean isOn, String manufacturer, String modelNumber, DeviceStates deviceStatus) {
		super(isOn, manufacturer, modelNumber, deviceStatus);

	}

	// Setters and Getters
	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	@Override
	public String toString() {
		return super.toString() + "AC [temp=" + temp + ", mode=" + mode + "]";
	}

	@Override
	public void increaseTemperature(int t) {
		// TODO Auto-generated method stub
		this.temp = this.temp + t;
		System.out.println("Current Temperature : " + this.temp);

	}

	@Override
	public void decreaseTemperature(int t) {
		// TODO Auto-generated method stub
		this.temp = this.temp - t;
		System.out.println("Current Temperature : " + this.temp);
	}

	@Override
	public double getCurrentTemperature() {
		// TODO Auto-generated method stub
		return this.temp;

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
		System.out.println("Aircondition is : " + this.DeviceStatus);

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
		System.out.println("Aircondition is : " + this.DeviceStatus);
	}

	@Override
	public void setMode(String mode) {
		// TODO Auto-generated method stub
		if (isOn) {
			this.mode = ACMode.COOL;
		} else {
			this.mode = ACMode.DRY;
		}
		System.out.println("AC Mode : " + this.mode);
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

		System.out.println("Total Active Time for AC : " + ": " + (activeTime / 1000) + " seconds");

	}

	@Override
	public DeviceType getDeviceType() {
		// TODO Auto-generated method stub

		return DeviceType.AC;

	}

}
