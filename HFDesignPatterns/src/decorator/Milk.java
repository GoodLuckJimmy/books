package decorator;

public class Milk extends CondimentDecorator {
	Beverage beverage;

	public Milk(Beverage beverage) {
		this.beverage = beverage;
	}

	public String getDescription() {
		return beverage.getDescription() + ", ������ũ";
	}

	public double cost() {
		return .10 + beverage.cost();
	}
}

