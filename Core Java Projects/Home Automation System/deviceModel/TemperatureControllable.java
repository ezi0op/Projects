package deviceModel;

public interface TemperatureControllable {
	
	void getStatus();
	void increaseTemperature(int t);
	void decreaseTemperature(int t);
	double getCurrentTemperature();
	void setMode(String mode);
	
}
