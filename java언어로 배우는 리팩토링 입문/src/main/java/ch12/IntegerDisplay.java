package ch12;

import java.awt.*;
import java.awt.event.*;

public class IntegerDisplay extends Frame implements ActionListener, ValueListener {

	private static final long serialVersionUID = 1L;
	
	private final Label octalLabel = new Label("0");
	private final Label decimaLabel = new Label("0");
	private final Label hexadecimaLabel = new Label("0");
	private final Button incrementButton = new Button("+");
	private final Button decrementButton = new Button("-");
	
	private Value value = new Value(0);
	
	public IntegerDisplay() {
		super("IntegerDisplay");
		
		setLayout(new GridLayout(4, 2));
		add(new Label("Octal:"));
		add(octalLabel);
		add(new Label("Decimal:"));
		add(decimaLabel);
		add(new Label("Hexadecimal:"));
		add(hexadecimaLabel);
		add(incrementButton);
		add(decrementButton);
		
		incrementButton.addActionListener(this);
		decrementButton.addActionListener(this);

		value.addValueListener(this);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == incrementButton) {
			setValue(value.getValue() + 1);
		} else if (e.getSource() == decrementButton) {
			setValue(value.getValue() -1);
		}
	}
	
	public int getValue() {
		return value.getValue();
	}
	
	public void setValue(int value) {
		this.value.setValue(value);
	}

	@Override
	public void valueChaged(ValueChangeEvent e) {
		if (e.getSource() == value) {
			octalLabel.setText(Integer.toString(this.value.getValue(), 8));
			decimaLabel.setText(Integer.toString(this.value.getValue(), 10));
			hexadecimaLabel.setText(Integer.toString(this.value.getValue(), 16));
		}
	}

}
