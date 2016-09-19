package adapter;


interface AceInterface {
	public void setName(String n);
	public String getName();
}


class AceClass implements AceInterface {
	String name;
	
	@Override
	public void setName(String n) {
		name = n;
	}

	@Override
	public String getName() {
		return name;
	}
}


interface AcmeInterface {
	public void setFirstName(String f);
	public void setLastName(String l);
	public String getFirstName();
	public String getLastName();
}


class AcmeClass implements AcmeInterface{
	String firstName;
	String lastName;

	@Override
	public void setFirstName(String f) {
		firstName = f;
	}
	@Override
	public void setLastName(String l) {
		lastName = l;
	}
	@Override
	public String getFirstName() {
		return firstName;
	}
	@Override
	public String getLastName() {
		return lastName;
	}
	

}


class AceToAcmeAdapter implements AcmeInterface {
	AceClass aceObject;
	String firstName;
	String lastName;

	public AceToAcmeAdapter(AceClass a) {
		aceObject = a;
		firstName = aceObject.getName().split(" ")[0];
		lastName = aceObject.getName().split(" ")[1];
	}

	@Override
	public void setFirstName(String f) {
		firstName = f;
	}

	@Override
	public void setLastName(String l) {
		lastName = l;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}
	
}


class AdapterTest {

	public static void main(String[] args) {
		AceClass aceObject = new AceClass();
		aceObject.setName("Cary Grant");
		
		AceToAcmeAdapter adapter = new AceToAcmeAdapter(aceObject);
		
		System.out.println("Customer's first name: " + adapter.getFirstName());
		System.out.println("Customer's last name: " + adapter.getLastName());
	}

}
