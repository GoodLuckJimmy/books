package composite;

// Leaf의 역할. 내용물을 표시하는 역할. 내부에는 다른것을 넣을 수 없다
public class File extends Entry {
	private String name;
	private int size;
	
	public File(String name, int size) {
		this.name = name;
		this.size = size;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	protected void printList(String prefix) {
		System.out.println(prefix + "/" + this);
	}

}
