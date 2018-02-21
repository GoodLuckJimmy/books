package composite;

import java.util.ArrayList;
import java.util.Iterator;

// Composite의 역할. '그릇'을 나타내는 역할을 하며 Leaf나 Composite 역할을 넣을 수 있다.
public class Directory extends Entry {
	private String name;
	private ArrayList<Entry> directory = new ArrayList<>();
	
	public Directory(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getSize() {
		int size = 0;
		Iterator<Entry> it = directory.iterator();
		while(it.hasNext()) {
			Entry entry = it.next();
			size += entry.getSize(); // composite 핵심부. entry는 File의 인스턴스일지 모르고, Directory의 인스턴스일지도 모름.
		}
		return size;
	}
	
	@Override
	public Entry add(Entry entry) {
		directory.add(entry);
		return this;
	}

	@Override
	protected void printList(String prefix) {
		System.out.println(prefix + "/" + this);
		Iterator<Entry> it = directory.iterator();
		while(it.hasNext()) {
			Entry entry = it.next();
			entry.printList(prefix + "/" + name);
		}
	}

}
