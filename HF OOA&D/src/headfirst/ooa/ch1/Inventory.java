package headfirst.ooa.ch1;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Inventory {
  private List<Guitar> guitars;

  public Inventory() {
    guitars = new LinkedList<Guitar>();
  }

  public void addGuitar(String serialNumber, double price, GuitarSpec spec) {

    Guitar guitar =  new Guitar(serialNumber, price, spec);
    guitars.add(guitar);
  }

  public Guitar getGuitar(String serialNumber) {
    for (Iterator<Guitar> i = guitars.iterator(); i.hasNext(); ) {
      Guitar guitar = (Guitar)i.next();
      if (guitar.getSerialNumber().equals(serialNumber)) {
        return guitar;
      }
    }
    return null;
  }

  public List<Guitar> search(GuitarSpec searchSpec) {

    List<Guitar> matchingGuitars = new LinkedList<Guitar>();

    for (Iterator<Guitar> i = guitars.iterator(); i.hasNext(); ) {

      Guitar guitar = (Guitar)i.next();
      
      if (guitar.getSpec().matches(searchSpec)) {
    	  matchingGuitars.add(guitar);
      }

    }
    return matchingGuitars;
  }
  
  
}
