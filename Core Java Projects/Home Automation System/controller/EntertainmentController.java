package controller;

import deviceModel.EntertainmentControllable;

public class EntertainmentController {

	EntertainmentControllable ec;
	
	
	public EntertainmentController(EntertainmentControllable ec) {
		this.ec = ec;
	}
	public void volumeUp(int s) {
		ec.volumeUp(s);
	}
	public void volumeDown(int s) {
		ec.volumeDown(s);
	}
	public void mute() {
		ec.mute();
	}
	public void unmute() {
		ec.unmute();
	}
	public void getCurrentVolume() {
		ec.getCurrentVolume();
	}
	public void play() {
		ec.play();
	}
	public void pause(){
		ec.pause();
	}
	public void stop(){
		ec.stop();
	}
	
}
