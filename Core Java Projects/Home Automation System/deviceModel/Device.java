package deviceModel;

public abstract class Device {

	boolean isOn;
	long lastChangedTime;
	long totalUsageTime;
	int pRating;
	String manufacturer;
	String modelNumber;
	DeviceStates DeviceStatus = DeviceStates.IDLE;
	DeviceType devType;

	public Device() {
		// TODO Auto-generated constructor stub

	}

	// Paramterized Constructor
	public Device( boolean isOn, String manufacturer, String modelNumber, DeviceStates deviceStatus) {
		
		this.isOn = isOn;
		this.manufacturer = manufacturer;
		this.modelNumber = modelNumber;
		this.DeviceStatus = deviceStatus;
	}

	// Setters and Getters
	

	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}

	public long getLastChanged() {
		return lastChangedTime;
	}

	public void setLastChanged(long lastChanged) {
		this.lastChangedTime = lastChanged;
	}

	public int getpRating() {
		return pRating;
	}

	public void setpRating(int pRating) {
		this.pRating = pRating;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public DeviceStates getDeviceStatus() {
		return DeviceStatus;
	}

	public void setDeviceStatus(DeviceStates deviceStatus) {
		DeviceStatus = deviceStatus;
	}

	public DeviceType getDevType() {
		return devType;
	}

	

	@Override
	public String toString() {
		return "Device [isOn=" + isOn + ", lastChangedTime=" + lastChangedTime + ", totalUsageTime=" + totalUsageTime
				+ ", pRating=" + pRating + ", manufacturer=" + manufacturer + ", modelNumber=" + modelNumber
				+ ", DeviceStatus=" + DeviceStatus + ", devType=" + devType + "]";
	}

	public abstract void turnON();

	public abstract void turnOFF();

	public abstract void getStatus();

	public abstract void recordUsageTime();

	public abstract void getTotalActiveTime();

	public abstract DeviceType getDeviceType();

}
