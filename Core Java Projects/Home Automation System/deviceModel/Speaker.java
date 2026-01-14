package deviceModel;

enum AudioMode {
	PLAYING, PAUSED, STOPPED, MUTED, OFF
}

public class Speaker extends Device implements EntertainmentControllable {

	int volume;
	AudioMode audioModes;

	public Speaker() {
		// TODO Auto-generated constructor stub
		super();
	}

	// Pararmterized Constructor
	public Speaker(boolean isOn, String manufacturer, String modelNumber, DeviceStates deviceStatus,
			int volume, boolean bluetoothEnabled) {
		super(isOn, manufacturer, modelNumber, deviceStatus);

		this.volume = volume;
		this.audioModes = AudioMode.OFF;
	}

	// Setters and getters
	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return super.toString() + "Speaker [volume=" + volume + ", audioModes=" + audioModes + "]";
	}

	@Override
	public void volumeUp(int s) {
		// TODO Auto-generated method stub

		this.volume = this.volume + s;
		System.out.println("Volume : " + this.volume);
	}

	@Override
	public void volumeDown(int s) {
		// TODO Auto-generated method stub
		this.volume = this.volume - s;
		System.out.println("Volume : " + this.volume);
	}

	@Override
	public void mute() {
		// TODO Auto-generated method stub
		this.audioModes = AudioMode.MUTED;
		System.out.println("Speaker Mode : " + audioModes);
	}

	@Override
	public void unmute() {
		// TODO Auto-generated method stub
		this.audioModes = AudioMode.PLAYING;
		System.out.println("Speaker Mode : " + audioModes);
	}

	@Override
	public void getCurrentVolume() {
		// TODO Auto-generated method stub
		System.out.println("Current Volume : " + this.getVolume());
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		this.audioModes = AudioMode.PLAYING;
		System.out.println("Speaker Mode : " + this.audioModes);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		this.audioModes = AudioMode.PAUSED;
		System.out.println("Speaker Mode : " + this.audioModes);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		this.audioModes = AudioMode.STOPPED;
		System.out.println("Speaker Mode : " + this.audioModes);

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
		System.out.println("Speaker is : " + this.DeviceStatus);

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
		System.out.println("Speaker is : " + this.DeviceStatus);
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

		System.out.println("Total Active Time for Speaker : " + ": " + (activeTime / 1000) + " ms");

	}

	@Override
	public DeviceType getDeviceType() {
		// TODO Auto-generated method stub

		return DeviceType.SPEAKER;
	}

}
