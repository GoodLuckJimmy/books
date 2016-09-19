package iterator;

import java.util.*;

class VP
{
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
}


class Division
{
	private VP[] VPs = new VP[100];
	private int number = 0;
	private String name;
	
	public Division(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void add(String n) {
		VP vp = new VP(n, name);
		VPs[number++] = vp;
	}
	
	public DivisionIterator iterator() {
		return new DivisionIterator(VPs);
	}
}


class DivisionIterator implements Iterator {

	private VP[] VPs;
	private int location = 0;
	
	public DivisionIterator(VP[] v) {
		VPs = v;
	}
	@Override
	public boolean hasNext() {
		if (location < VPs.length && VPs[location] != null) {
			return true;
		}
		return false;
	}

	@Override
	public VP next() {
		return VPs[location++];
	}
	
	public void remove() {
		
	}
	
}


class IteratorTest {
	Division division;
	DivisionIterator iterator;
	
	public static void main(String[] args) {
		IteratorTest t = new IteratorTest();
	}
	
	public IteratorTest() {
		division = new Division("Sales");
		
		division.add("Ted");
		division.add("Bob");
		division.add("Alice");
		division.add("Carol");
		
		iterator = division.iterator();
		
		while (iterator.hasNext()) {
			VP vp = iterator.next();
			vp.print();
		}
	}

}
