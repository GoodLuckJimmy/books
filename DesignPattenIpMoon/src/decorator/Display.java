package decorator;

// Componet역할. 기능추가시 핵심역할.케이크
public abstract class Display {
	
	public abstract int getColumns();
	public abstract int getRows();
	public abstract String getRowText(int row);
	public final void show() {
		for (int i = 0; i < getRows(); i++) {
			System.out.println(getRowText(i));
		}
	}

}
