package controller;

import deviceModel.BrightnessControllable;

public class BrightnessController {

	BrightnessControllable bc;

	public BrightnessController(BrightnessControllable bc) {
		this.bc = bc;
	}

	public void increaseBrightness(int b) {
		bc.increaseBrightness(b);
	}

	public void decreaseBrightness(int b) {
		bc.decreaseBrightness(b);
	}

	public void getCurrentBrightness() {
		bc.getCurrentBrightness();
	}

	public void toggleNightMode() {
		bc.toggleNightMode();
	}

	public void setMode() {
		bc.setMode();
	}
}
