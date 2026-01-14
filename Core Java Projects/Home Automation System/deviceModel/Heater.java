package deviceModel;

enum HeaterMode {
	LOW, HIGH, AUTO, ECO
}

public class Heater extends Device implements TemperatureControllable {

	int heatLevel;
	HeaterMode heatingMode = HeaterMode.ECO;

	public Heater() {
		// TODO Auto-generated constructor stub
		super();
	}

	// Parameterized constructor
	public Heater(boolean isOn, String manufacturer, String modelNumber, DeviceStates deviceStatus) {
		super(isOn, manufacturer, modelNumber, deviceStatus);

	}

	// Setters and getters
	public int getHeatLevel() {
		return heatLevel;
	}

	public void setHeatLevel(int heatLevel) {
		this.heatLevel = heatLevel;
	}

	@Override
	public String toString() {
		return super.toString() + "Heater [heatLevel=" + heatLevel + ", heatingMode=" + heatingMode + "]";
	}

	@Override
	public void increaseTemperature(int t) {
		// TODO Auto-generated method stub
		this.heatLevel = this.heatLevel + t;
		System.out.println("Increase HeatLevel : " + this.heatLevel);

	}

	@Override
	public void decreaseTemperature(int t) {
		// TODO Auto-generated method stub
		this.heatLevel = this.heatLevel - t;
		System.out.println("Decrease HeatLevel : " + this.heatLevel);
	}

	@Override
	public double getCurrentTemperature() {
		// TODO Auto-generated method stub
		return this.heatLevel;

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
		System.out.println("Heater is : " + this.DeviceStatus);

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
		System.out.println("Heater is : " + this.DeviceStatus);
	}

	@Override
	public void setMode(String mode) {
		// TODO Auto-generated method stub
		if (isOn) {
			this.heatingMode = HeaterMode.AUTO;
		} else {
			this.heatingMode = HeaterMode.LOW;
		}

		System.out.println("Heater Heating Mode : " + this.heatingMode);

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

		System.out.println("Total Active Time for Heater : " + ": " + (activeTime / 1000) + " ms");

	}

	@Override
	public DeviceType getDeviceType() {
		// TODO Auto-generated method stub
		return DeviceType.HEATER;
	}

}
