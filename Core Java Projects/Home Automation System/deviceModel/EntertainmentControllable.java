package deviceModel;

public interface EntertainmentControllable {
	void volumeUp(int s);
	void volumeDown(int s);
	void mute();
	void unmute();
	void getCurrentVolume();
	void play();
	void pause();
	void stop();
}
