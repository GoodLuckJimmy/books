package composites;
/*
 *  객체와 객체의 그룹을 구분없이 하나의 인터페이스로 다룰 수 있게 한다.
 *  객체(Leaf)와 구성(composite)을 트리로 구성하여 하나의 인터페이스에서 사용하도록 고려한 패턴이다. 
 *  스타에서 각 개별 유닛을 부대지정했을때 동시에 통합 컨트롤 하듯...
 */
import java.util.*;

abstract class Corporate {
	public String getName() {
		return "";
	}
	
	public void add(Corporate c) {
	}
	
	Iterator iterator() {
		return null;
	}
	
	public void print() {
	}
}


class VP extends Corporate {
	private String name;
	private String division;
	
	public VP(String n, String d) {
		name = n;
		division = d;
	}
	
	public String getName() {
		return name;
	}
	
	public void print() {
		System.out.println("Name: " + name + " Division: " + division);
	}
	
	public Iterator iterator() {
		return new VPIterator(this);
	}
}


class VPIterator implements Iterator
{
	private VP vp;
	public VPIterator(VP v) {
		vp = v;
	}
	
	@Override
	public VP next() {
		return vp;
	}

	@Override
	public boolean hasNext() {
		return false;
	}
	
	public void remove() {
	}
}


class Division extends Corporate {
	private Corporate[] corprate = new Corporate[100];
	private int number = 0;
	private String name;
	
	public Division(String n) {
		name = n;
	}
	
	public void add(Corporate c) {
		corprate[number++] = c;
	}
	
	public Iterator iterator() {
		return new DivisionIterator(corprate);
	}
	
	public void print() {
		Iterator iterator = iterator();
		
		while (iterator.hasNext()) {
			Corporate c = (Corporate) iterator.next();
			c.print();
		}
	}
}


class DivisionIterator implements Iterator {
	private Corporate[] corporate;
	private int location = 0;
	
	public DivisionIterator(Corporate[] c) {
		corporate = c;
	}

	@Override
	public boolean hasNext() {
		if (location < corporate.length && corporate[location] != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Corporate next() {
		return corporate[location++];
	}
	
	public void remove() {
	}
	
}


class Corporation extends Corporate {
	private ArrayList<Corporate> corporate = new ArrayList<Corporate>();
	
	public Corporation() {
	}
	
	public void add(Corporate c) {
		corporate.add(c);
	}
	
	public void print() {
		Iterator iterator = corporate.iterator();
		
		while (iterator.hasNext()) {
			Corporate c = (Corporate) iterator.next();
			c.print();
		}
	}
}


class CompositesTest {

	Corporation corporation;
	
	public static void main(String[] args) {
		CompositesTest t = new CompositesTest();
	}
	
	public CompositesTest() {
		corporation = new Corporation();
		
		Division rnd = new Division("R&D");
		rnd.add(new VP("Steve", "R&D"));
		rnd.add(new VP("Mike", "R&D"));
		rnd.add(new VP("Nancy", "R&D"));
		
		Division sales = new Division("Sales");
		sales.add(new VP("Ted", "Sales"));
		sales.add(new VP("Bob", "Sales"));
		sales.add(new VP("Carol", "Sales"));
		sales.add(new VP("Alice", "Sales"));
		
		Division western =  new Division("Western Sales");
		western.add(new VP("Wally", "Western Sales"));
		western.add(new VP("Andre", "Western Sales"));
		
		sales.add(western);
		
		VP vp = new VP("Cary", "At Large");
		corporation.add(rnd);
		corporation.add(sales);
		corporation.add(vp);
		
		corporation.print();
	}

}
