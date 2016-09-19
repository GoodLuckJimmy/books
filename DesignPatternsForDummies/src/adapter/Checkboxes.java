package adapter;
// class 단위로도 가능 
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class CheckboxAdapter extends Checkbox {
	public CheckboxAdapter(String n) {
		super(n);
	}
	
	public boolean isSelected() {
		return getState();
	}
}


class Checkboxes extends JFrame implements ItemListener {
	CheckboxAdapter checks[];
	JTextField text;
	
	public Checkboxes() {
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		checks = new CheckboxAdapter[4];
		
		for (int i = 0; i < checks.length - 1; i++) {
			checks[i] = new CheckboxAdapter("Check " + i);
			checks[i].addItemListener(this);
			contentPane.add(checks[i]);
		}
		
		text = new JTextField(30);
		contentPane.add(text);
	}
	
	public void itemStateChanged(ItemEvent e) {
		String outString = new String("Selected: ");
		for (int i = 0; i < checks.length - 1; i++) {
			if (checks[i].isSelected()) {
				outString += " checkbox " + i;
			}
		}
		text.setText(outString);
	}
	
	public static void main(String[] args) {
		final Checkboxes f = new Checkboxes();
		f.setBounds(100, 100, 400, 300);
		f.setVisible(true);
		f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}

