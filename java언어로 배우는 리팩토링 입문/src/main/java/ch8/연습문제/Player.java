package ch8.연습문제;

public abstract class Player {
	public static final int MUSIC = 0;
	public static final int VIDEO = 1;
	
	public static int getMusic() {
		return MUSIC;
	}

	public static int getVideo() {
		return VIDEO;
	}
	
	public static Player create(int type) {
		switch (type) {
		case MUSIC:
			return new MusicPlayer();
		case VIDEO:
			return new VidoePlayer();
		default:
			throw new IllegalArgumentException("type = " + type);
		}
	}
	
	public abstract void player();
	public abstract void loop();
	public abstract void stop();

}
