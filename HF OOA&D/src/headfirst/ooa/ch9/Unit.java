package headfirst.ooa.ch9;

import java.util.*;

public class Unit {
	
	private String type;
	private int id;
	private String name;
	private List weapons;
	private Map properties;

	public Unit(int id) {
		this.id = id;
	}
	
	public void addWeapon(Weapon weapon) {
		if (weapons == null) {
			weapons = new LinkedList();
		}
		weapons.add(weapon);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List getWeapons() {
		return weapons;
	}

	public void setWeapons(List weapons) {
		this.weapons = weapons;
	}

	public Object getProperty(String property) {
		if (properties == null) {
			return null;
		}
		return properties.get(property);
	}

	public void setProperty(String property, Object value) {
		if (properties == null) {
			properties = new HashMap();
		}
		properties.put(property, value);
	}
	
	

}
