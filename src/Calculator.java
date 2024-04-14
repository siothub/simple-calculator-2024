import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Calculator  extends JFrame  implements ActionListener {

//	declaring javax.swing.*
	Font font = new Font("Montserrat", Font.BOLD, 35);
	Font fontTextField = new Font("Montserrat", Font.BOLD, 50);
	JTextField textField = new JTextField();
	JButton[] numberButtons = new JButton[10];

	JButton clear = new JButton("ce");
	JButton delete = new JButton("c");
	JButton addition = new JButton("+");
	JButton subtraction = new JButton("-");
	JButton multiplication = new JButton("*");
	JButton division = new JButton("/");
	JButton equals = new JButton("=");
	JButton decimal = new JButton(".");
	JButton[] operatorButtons = new JButton[8];

//	daclaring operation
	ArrayList<Double> solve = new ArrayList<>();
	double result = 0.0;
	char operator = ' ';

	Calculator () throws MalformedURLException {
//		frame
		this.setTitle("Calculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(416,639);
		this.getContentPane().setBackground( Color.decode("0x353535"));
		this.setLayout(null);
		this.setResizable(false);

//		number buttons
		for (int i = 0; i < numberButtons.length; i++) {
			numberButtons[i] = new JButton();
			numberButtons[i].setText(String.valueOf(i));
			numberButtons[i].setFont(font);
			numberButtons[i].addActionListener(this);
			numberButtons[i].setBackground(Color.decode("0x505050"));
			numberButtons[i].setForeground(Color.white);
			numberButtons[i].setFocusable(false);
			numberButtons[i].setBorderPainted(false);
		}

//		adding operator buttons to array
		operatorButtons[0] = clear;
		operatorButtons[1] = delete;
		operatorButtons[2] = addition;
		operatorButtons[3] = subtraction;
		operatorButtons[4] = multiplication;
		operatorButtons[5] = division;
		operatorButtons[6] = equals;
		operatorButtons[7] = decimal;

//		operator buttons
		for (int i = 0; i < operatorButtons.length; i++) {
			operatorButtons[i].setFont(font);
			operatorButtons[i].addActionListener(this);
			operatorButtons[i].setBackground(Color.decode("0x353535"));
			operatorButtons[i].setForeground(Color.white);
			operatorButtons[i].setFocusable(false);
			operatorButtons[i].setBorderPainted(false);
		}
		delete.setBackground(Color.decode("0xFF8300"));
		equals.setBackground(Color.decode("0xFF8300"));
		decimal.setBackground(Color.decode("0x505050"));


//		text field
		textField.setEditable(false);
		textField.setForeground(Color.white);
		textField.setCaretColor(Color.BLACK);
		textField.setBackground(Color.BLACK);
		textField.setFont(fontTextField);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);

//		panels
		JPanel panelText = new JPanel();
		panelText.setLayout(new BorderLayout());
		panelText.setBounds(0,0,400,100);

		panelText.add(textField, BorderLayout.CENTER);


		JPanel panelRightBorder = new JPanel();
		panelRightBorder.setLayout(new GridLayout(5,1));
		panelRightBorder.setBounds(0,100,100,500);

		panelRightBorder.add(delete);
		panelRightBorder.add(numberButtons[7]);
		panelRightBorder.add(numberButtons[4]);
		panelRightBorder.add(numberButtons[1]);
		panelRightBorder.add(decimal);


		JPanel panelRightInner = new JPanel();
		panelRightInner.setLayout(new GridLayout(5,1));
		panelRightInner.setBounds(100,100,100,500);

		panelRightInner.add(clear);
		panelRightInner.add(numberButtons[8]);
		panelRightInner.add(numberButtons[5]);
		panelRightInner.add(numberButtons[2]);
		panelRightInner.add(numberButtons[0]);


		JPanel panelLeftInner = new JPanel();
		panelLeftInner.setLayout(new GridLayout(3,1));
		panelLeftInner.setBounds(200,200,100,300);

		panelLeftInner.add(numberButtons[9]);
		panelLeftInner.add(numberButtons[6]);
		panelLeftInner.add(numberButtons[3]);


		JPanel panelLeftBorder = new JPanel();
		panelLeftBorder.setLayout(new GridLayout(4,1));
		panelLeftBorder.setBounds(300,100,100,400);

		panelLeftBorder.add(addition);
		panelLeftBorder.add(subtraction);
		panelLeftBorder.add(multiplication);
		panelLeftBorder.add(division);

		JPanel panelEquals = new JPanel();
		panelEquals.setLayout(new GridLayout());
		panelEquals.setBounds(200,500,200,100);

		panelEquals.add(equals);


//		icon
		URL icon = new URL("https://cdn2.iconfinder.com/data/icons/ios7-inspired-mac-icon-set/512/Calculator_512.png");
		ImageIcon calculator = new ImageIcon(icon, "Icon");
		this.setIconImage(calculator.getImage());

//		addind
		this.add(panelText);
		this.add(panelRightBorder);
		this.add(panelRightInner);
		this.add(panelLeftInner);
		this.add(panelLeftBorder);
		this.add(panelEquals);

		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {

//		number buttons
		for (int i = 0; i < numberButtons.length; i++) {
			if (e.getSource()==numberButtons[i]) {
				textField.setText(textField.getText().concat(String.valueOf(numberButtons[i].getText())));
			}
		}

//		decimals
		if (e.getSource()==decimal) {
			textField.setText(textField.getText().concat("."));
		}

//		operations
		if (e.getSource() == addition) {
			solve.add(Double.parseDouble(String.valueOf(textField.getText())));
			operator = '+';
			textField.setText("");
		}
		if (e.getSource() == subtraction) {
//			testing if the input is supposed to be a negative number
			if (textField.getText().length() == 0) {
				textField.setText("-");
			} else {
				solve.add(Double.parseDouble(String.valueOf(textField.getText())));
				operator = '-';
				textField.setText("");
			}
		}
		if (e.getSource() == multiplication) {
			solve.add(Double.parseDouble(String.valueOf(textField.getText())));
			operator = '*';
			textField.setText("");
		}
		if (e.getSource() == division) {
			solve.add(Double.parseDouble(String.valueOf(textField.getText())));
			operator = '/';
			textField.setText("");
		}

//		equals
		if (e.getSource()==equals) {
			solve.add(Double.parseDouble(String.valueOf(textField.getText())));

			switch (operator) {
				case '+':
					result = solve.get(solve.size() - 2) + solve.get(solve.size() - 1);
					break;
				case '-':
					result = solve.get(solve.size() - 2) - solve.get(solve.size() - 1);
					break;
				case '*':
					result = solve.get(solve.size() - 2) * solve.get(solve.size() - 1);
					break;
				case '/':
					result = solve.get(solve.size() - 2) / solve.get(solve.size() - 1);
					break;
			}

//			formating display result
			if (result == Math.round(result)) {
				textField.setText(String.valueOf(Math.round(result)));
			} else {
				textField.setText(String.valueOf(result));
			}

//			preparing for the next operation
			solve.removeAll(solve);
			solve.add(result);
			operator = ' ';
		}

//		clear all
		if (e.getSource()==clear) {
			textField.setText("");
			solve.removeAll(solve);
		}

//		delete character
		if (e.getSource()==delete) {
			String digits = textField.getText();
			textField.setText("");
			for (int i = 0; i < digits.length() - 1; i++) {
				textField.setText(textField.getText() + digits.charAt(i));
			}
		}
//		end
	}
}
