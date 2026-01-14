package controller;

import deviceModel.SpeedControllable;

public class SpeedController {
	SpeedControllable sc;

	public SpeedController(SpeedControllable sc) {
		this.sc = sc;
	}

	public void increaseSpeed(int s) {
		sc.increaseSpeed(s);
	}

	public void decreaseSpeed(int s) {
		sc.decreaseSpeed(s);
	}

	public void changeMode() {
		sc.changeMode();
	}

	public void getCurrentSpeed() {
		sc.getCurrentSpeed();
	}
}
