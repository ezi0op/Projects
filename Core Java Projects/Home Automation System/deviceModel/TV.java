package deviceModel;

enum EntertainmentMode {
	PLAYING, PAUSED, STOPPED, MUTED
}

enum TVMode {
	PLAYING, PAUSED, STOPPED, MUTED, UNMUTED, ON, OFF
}

public class TV extends Device implements EntertainmentControllable {

	int vol;
	TVMode mode;

	public TV() {
		// TODO Auto-generated constructor stub
		super();
	}

	// parameterized constructor
	public TV(boolean isOn, String manufacturer, String modelNumber, DeviceStates deviceStatus) {
		super(isOn, manufacturer, modelNumber, deviceStatus);

	}

	// setters and getters
	public int getVol() {
		return vol;
	}

	public void setVol(int vol) {
		this.vol = vol;
	}

	@Override
	public String toString() {
		return super.toString() + "TV [vol=" + vol + ", mode=" + mode + "]";
	}

	@Override
	public void volumeUp(int s) {
		// TODO Auto-generated method stub

		this.vol = this.vol + s;
		System.out.println("TV Volume : " + this.vol);

	}

	@Override
	public void volumeDown(int s) {
		// TODO Auto-generated method stub
		this.vol = this.vol - s;
		System.out.println("TV Volume : " + this.vol);
	}

	@Override
	public void mute() {
		// TODO Auto-generated method stub
		this.mode = TVMode.MUTED;
		System.out.println("TV Mode : " + this.mode);
	}

	@Override
	public void unmute() {
		// TODO Auto-generated method stub
		this.mode = TVMode.PLAYING;
		System.out.println("TV Mode : " + this.mode);

	}

	@Override
	public void getCurrentVolume() {
		// TODO Auto-generated method stub
		System.out.println("Current Volume  : " + this.getVol());
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		this.mode = TVMode.PLAYING;
		System.out.println("Mode : " + this.mode);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		this.mode = TVMode.PAUSED;
		System.out.println("Mode : " + this.mode);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		this.mode = TVMode.STOPPED;
		System.out.println("Mode : " + this.mode);

	}

	@Override
	public void getStatus() {
		// TODO Auto-generated method stub
		if (this.getDeviceStatus() == DeviceStates.ON) {
			System.out.println("Device is ON!");
		} else if (this.getDeviceStatus() == DeviceStates.OFF) {
			System.out.println("Device is OFF!");
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
		System.out.println("TV is : " + this.DeviceStatus);

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
		System.out.println("TV is : " + this.DeviceStatus);
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

		System.out.println("Total Active Time for TV : " + ": " + (activeTime / 1000) + " seconds");

	}

	@Override
	public DeviceType getDeviceType() {
		// TODO Auto-generated method stub

		return DeviceType.TV;
	}

}
