package ch12;

import java.util.ArrayList;
import java.util.List;

public class Value {
	private int value = 0;
	private final List<ValueListener> listeners = new ArrayList<>();
	
	public Value(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
		notifyToListeners();
	}
	
	private void notifyToListeners() {
		for (ValueListener listener : this.listeners) {
			listener.valueChaged(new ValueChangeEvent(this));
		}
	}
	public void addValueListener(ValueListener listener) {
		this.listeners.add(listener);
	}

}
