package ch8.연습문제;

public class VidoePlayer extends Player {

	@Override
	public void player() {
		System.out.println("Video: play");
	}

	@Override
	public void loop() {
		System.out.println("Video: loop");
	}

	@Override
	public void stop() {
		System.out.println("Video: stop");
	}

}
