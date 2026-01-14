package controller;

import deviceModel.TemperatureControllable;

public class TemperatureController {

	TemperatureControllable temp;
	
	

	public TemperatureController(TemperatureControllable temp) {
		this.temp = temp;
	}

	public void getStatus() {
			 temp.getStatus();
		}

	public void increaseTemperature(int t) {
		temp.increaseTemperature(t);
	}

	public void decreaseTemperature(int t) {
		temp.decreaseTemperature(t);
	}

	public void getCurrentTemperature() {
		System.out.println("Current Temperature : " + temp.getCurrentTemperature());
	}

	public void setMode(String mode) {
		temp.setMode(mode);
	}

}
