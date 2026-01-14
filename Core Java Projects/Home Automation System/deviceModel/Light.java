package deviceModel;

enum LightColor {
	WHITE, WARM_WHITE, COOL_WHITE,
}

enum LightMode {
	NORMAL, NIGHT, OFF
}

public class Light extends Device implements BrightnessControllable {
	int brightness;
	LightColor lightColor;
	LightMode mode;

	public Light() {
		// TODO Auto-generated constructor stub
		super();
	}

	// Paraterized Constructor
	public Light(boolean isOn, String manufacturer, String modelNumber, DeviceStates deviceStatus) {
		super(isOn, manufacturer, modelNumber, deviceStatus);

		this.mode = LightMode.OFF;
		this.lightColor = LightColor.WHITE;
	}

	// Setter and Getters
	public int getBrightness() {
		return brightness;
	}

	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}

	@Override
	public String toString() {
		return super.toString() + "Light [brightness=" + brightness + ", lightColor=" + lightColor + ", mode=" + mode
				+ "]";
	}

	@Override
	public void increaseBrightness(int b) {
		// TODO Auto-generated method stub
		this.brightness = this.brightness + b;
		System.out.println("Increased LightBrightness : " + this.brightness);

	}

	@Override
	public void decreaseBrightness(int b) {
		// TODO Auto-generated method stub
		this.brightness = this.brightness - b;
		System.out.println("Decreased LightBrightness : " + this.brightness);
	}

	@Override
	public void getCurrentBrightness() {
		// TODO Auto-generated method stub
		System.out.println("Current LightBrightness : " + this.getBrightness());
	}

	@Override
	public void toggleNightMode() {
		// TODO Auto-generated method stub
		if (!isOn) {
			System.out.println("Light is OFF. Cannot toggle night mode. ");
			return;
		}

		if (mode == LightMode.NIGHT) {
			mode = LightMode.NORMAL;
			brightness = 80;
			lightColor = LightColor.WHITE;
		} else {
			mode = LightMode.NIGHT;
			brightness = 30;
			lightColor = LightColor.WARM_WHITE;
		}
		System.out.println("Light mode toggled. Current mode: " + mode);
	}

	@Override
	public void setMode() {
		// TODO Auto-generated method stub
		if (isOn) {
			if (brightness > 50) {
				mode = LightMode.NORMAL;
				lightColor = LightColor.WHITE;
			} else {
				mode = LightMode.NIGHT;
				lightColor = LightColor.WARM_WHITE;
			}
		} else {
			mode = LightMode.OFF;
		}
		System.out.println("Light Mode : " + mode);
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
		System.out.println("Light is : " + this.DeviceStatus);

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
		System.out.println("Light is : " + this.DeviceStatus);
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

		System.out.println("Total Active Time for Light : " + ": " + (activeTime / 1000) + " seconds");

	}

	@Override
	public DeviceType getDeviceType() {
		// TODO Auto-generated method stub

		return DeviceType.LIGHT;
	}

}
