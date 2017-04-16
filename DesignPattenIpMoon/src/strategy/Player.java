package strategy;

public class Player {
	
	private String name;
	private Strategy strategy;
	private int windcount;
	private int losecount;
	private int gamecount;
	public Player(String name, Strategy strategy) {
		this.name = name;
		this.strategy = strategy;
	}
	
	public Hand nextHand() {
		return strategy.nextHand();
	}
	
	public void win() {
		strategy.study(true);
		windcount++;
		gamecount++;
	}
	
	public void lose() {
		strategy.study(false);
		losecount++;
		gamecount++;
	}
	
	public void even() {
		gamecount++;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", strategy=" + strategy + ", windcount=" + windcount + ", losecount="
				+ losecount + ", gamecount=" + gamecount + "]";
	}
	

}
