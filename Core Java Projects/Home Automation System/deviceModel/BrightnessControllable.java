package deviceModel;

public interface BrightnessControllable {
	
	void increaseBrightness(int b);
	void decreaseBrightness(int b);
	void getCurrentBrightness();
	void toggleNightMode();
	void setMode();
}
