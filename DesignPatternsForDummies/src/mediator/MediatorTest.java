package mediator;

import java.io.*;

class Welcome {
	Mediator mediator;
	String response = "n";
	
	public Welcome(Mediator m) {
		mediator = m;
	}
	
	public void go() {
		System.out.println("Do you want to shop? [y/n]? ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			response = reader.readLine();
		} catch (IOException e) {
			System.out.println("Error");
		}
		
		if (response.equals("y")) {
			mediator.handle("welcome.shop");
		} else {
			mediator.handle("welcome.exit");
		}
	}
}


class Shop {
	Mediator mediator;
	String response = "n";
	
	Shop(Mediator m) {
		mediator = m;
	}
	
	public void go() {
		System.out.println("Are you ready to purchase? [y/n]? ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			response = reader.readLine();
		} catch (IOException e) {
			System.err.println("Error");
		}
		
		if (response.equals("y")) {
			mediator.handle("shop.purchase");
		} else {
			mediator.handle("shop.exit");
		}
	}
}


class Purchase {
	Mediator mediator;
	String response = "n";
	
	Purchase(Mediator m) {
		mediator = m;
	}
	
	public void go() {
		System.out.println("Buy the item now? [y/n]? ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			response = reader.readLine();
		} catch (IOException e) {
			System.err.println("Error");
		}
		
		if (response.equals("y")) {
			System.out.println("Thanks for your purchase.");
		} 
		
		mediator.handle("purchase.exit");
	}
}


class Exit {
	Mediator mediator;

	public Exit(Mediator m) {
		mediator = m;
	}

	public void go() {
		System.out.println("Please come again sometime.");
	}
}


class Mediator {
	Welcome welcome;
	Shop shop;
	Purchase purchase;
	Exit exit;
	
	public Mediator() {
		welcome = new Welcome(this);
		shop = new Shop(this);
		purchase = new Purchase(this);
		exit = new Exit(this);
	}
	
	public void handle(String state) {
		if(state.equals("welcome.shop")){
			shop.go();
		} else if(state.equals("shop.purchase")){
			purchase.go();
		} else if(state.equals("purchase.exit")){
			exit.go();
		} else if(state.equals("welcome.exit")){
			exit.go();
		} else if(state.equals("shop.exit")){
			exit.go();
		} else if(state.equals("purchase.exit")){
			exit.go();
		}
	}
	
	public Welcome getWelcome() {
		return welcome;
	}
}


public class MediatorTest {

	public static void main(String[] args) {
		MediatorTest t = new MediatorTest();
	}
	
	public MediatorTest() {
		Mediator mediator = new Mediator();
		mediator.getWelcome().go();
	}
}
