package deviceModel;

enum FanMode {
	LOW, MEDIUM, HIGH, AUTO
}

public class Fan extends Device implements SpeedControllable {
	int speed;
	FanMode mode = FanMode.LOW;

	public Fan() {
		// TODO Auto-generated constructor stub
		super();
	}

	// Parameterizwed Constructor
	public Fan(boolean isOn, String manufacturer, String modelNumber, DeviceStates deviceStatus) {
		super(isOn, manufacturer, modelNumber, deviceStatus);

	}

	// Setters and Getters
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public FanMode getMode() {
		return mode;
	}

	public void setMode(FanMode mode) {
		this.mode = mode;
	}

	@Override
	public String toString() {
		return super.toString() + "Fan [speed=" + speed + ", mode=" + mode + "]";
	}

	@Override
	public void increaseSpeed(int s) {
		// TODO Auto-generated method stub

		this.speed = this.speed + s;
		System.out.println("Increase Fan Speed : " + this.speed);
	}

	@Override
	public void decreaseSpeed(int s) {
		// TODO Auto-generated method stub
		this.speed = this.speed - s;
		System.out.println("Decrease Fan Speed : " + this.speed);
	}

	@Override
	public void changeMode() {
		// TODO Auto-generated method stub
		if (this.getMode() == FanMode.LOW) {
			this.setMode(FanMode.MEDIUM);
			System.out.println("Fan mode changed to MEDIUM");
		} else if (this.getMode() == FanMode.MEDIUM) {
			this.setMode(FanMode.HIGH);
			System.out.println("Fan mode changed to HIGH");
		} else if (this.getMode() == FanMode.HIGH) {
			this.setMode(FanMode.LOW);
			System.out.println("Fan mode changed to LOW");
		}

	}

	@Override
	public void getCurrentSpeed() {
		// TODO Auto-generated method stub
		System.out.println("Fan Speed : " + this.speed);
	}

	@Override
	public void turnON() {
		// TODO Auto-generated method stub
		this.DeviceStatus = DeviceStates.ON;
		if (!isOn) {
			this.isOn = true;
			lastChangedTime = System.currentTimeMillis();
		}
		System.out.println("Fan is : " + this.DeviceStatus);

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
		System.out.println("Fan is : " + this.DeviceStatus);
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

		System.out.println("Total Active Time for Fan : " + ": " + (activeTime / 1000) + " sec");

	}

	@Override
	public DeviceType getDeviceType() {
		// TODO Auto-generated method stub

		return DeviceType.FAN;
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

}
