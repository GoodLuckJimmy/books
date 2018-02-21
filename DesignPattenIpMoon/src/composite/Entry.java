package composite;

// Component의 역할. Leaf역할과 Composite역할을 동일시 하는 역할.
public abstract class Entry {
	
	public abstract String getName();
	public abstract int getSize();
	public Entry add(Entry entry) throws FileTreatmentException {
		throw new FileTreatmentException();
	}
	public void printList() {
		printList("");
	}
	
	protected abstract void printList(String prefix);
	
	public String toString() {
		return getName() + " (" + getSize() + ")";
	}

}
