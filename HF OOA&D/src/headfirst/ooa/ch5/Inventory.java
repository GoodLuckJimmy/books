package headfirst.ooa.ch5;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Inventory {
	private List<Instrument> inventory;

	public Inventory() {
		inventory = new LinkedList<>();
	}

	public void addInstrument(String serialNumber, double price, InstrumentSpec spec) {
		Instrument instrument = null;
		
		if (spec instanceof GuitarSpec) {
			instrument = new Guitar(serialNumber, price, (GuitarSpec)spec);
		} else if (spec instanceof MandolinSpec) {
			instrument = new Mandolin(serialNumber, price, (MandolinSpec)spec);
		}
		inventory.add(instrument);
	}

	public Instrument get(String serialNumber) {
		for (Iterator<Instrument> i = inventory.iterator(); i.hasNext();) {
			Instrument instrument = i.next();
			if (instrument.getSerialNumber().equals(serialNumber)) {
				return instrument;
			}
		}
		return null;
	}

	public List<Instrument> search(GuitarSpec searchSpec) {
		List<Instrument> matchingGuitars = new LinkedList<>();
		for (Iterator<Instrument> i = inventory.iterator(); i.hasNext();) {
			Guitar guitar = (Guitar) i.next();
			if (guitar.getSpec().matches(searchSpec))
				matchingGuitars.add(guitar);
		}
		return matchingGuitars;
	}
	
	public List<Instrument> search(MandolinSpec searchSpec) {
		List<Instrument> matchingMandolins = new LinkedList<>();
		for (Iterator<Instrument> i = inventory.iterator(); i.hasNext();) {
			Mandolin mandolin = (Mandolin) i.next();
			if (mandolin.getSpec().matches(searchSpec))
				matchingMandolins.add(mandolin);
		}
		return matchingMandolins;
	}
}
