package ch8.연습문제;

public class MusicPlayer extends Player {

	@Override
	public void player() {
		System.out.println("Music: play");
	}

	@Override
	public void loop() {
		System.out.println("Music: loop");
	}

	@Override
	public void stop() {
		System.out.println("Music: stop");
	}

}
