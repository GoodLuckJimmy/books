package decorator;

public class Milk extends CondimentDecorator {
	Beverage beverage;

	public Milk(Beverage beverage) {
		this.beverage = beverage;
	}

	public String getDescription() {
		return beverage.getDescription() + ", ½ºÆÀ¹ÐÅ©";
	}

	public double cost() {
		return .10 + beverage.cost();
	}
}

