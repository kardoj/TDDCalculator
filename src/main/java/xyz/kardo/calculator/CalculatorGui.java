/* Kardo Jõeleht
 * 
 * An attempt at making a graphical user interface
 * for the calculator.
 */
package xyz.kardo.calculator;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorGui implements MouseListener{

	Calculator calculator;
	JFrame window;
	JTextField outputField;
	
	public CalculatorGui(){
		window = new JFrame("Calculator");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createGuiElements(window);
		window.pack();
		window.setVisible(true);
		window.addMouseListener(this);
		
		calculator = new Calculator();
	}
	
	private void createGuiElements(JFrame window) {
		JPanel frame = new JPanel(new GridLayout(2, 1));
		createOutputField(frame);
		createButtons(frame);
		window.add(frame);
	}

	private void createButtons(JPanel frame) {
		JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
		JButton oneButton = new JButton("1");
		JButton twoButton = new JButton("2");
		JButton threeButton = new JButton("3");
		JButton addButton = new JButton("+");
		JButton fourButton = new JButton("4");
		JButton fiveButton = new JButton("5");
		JButton sixButton = new JButton("6");
		JButton subtractButton = new JButton("-");
		JButton sevenButton = new JButton("7");
		JButton eightButton = new JButton("8");
		JButton nineButton = new JButton("9");
		JButton multiplyButton = new JButton("*");
		JButton resetButton = new JButton("C");
		JButton zeroButton = new JButton("0");
		JButton calculateButton = new JButton("=");
		JButton divideButton = new JButton("/");
		buttonPanel.add(oneButton);
		buttonPanel.add(twoButton);
		buttonPanel.add(threeButton);
		buttonPanel.add(addButton);
		buttonPanel.add(fourButton);
		buttonPanel.add(fiveButton);
		buttonPanel.add(sixButton);
		buttonPanel.add(subtractButton);
		buttonPanel.add(sevenButton);
		buttonPanel.add(eightButton);
		buttonPanel.add(nineButton);
		buttonPanel.add(multiplyButton);
		buttonPanel.add(resetButton);
		buttonPanel.add(zeroButton);
		buttonPanel.add(calculateButton);
		buttonPanel.add(divideButton);
		frame.add(buttonPanel);
		
		oneButton.addMouseListener(this);
		twoButton.addMouseListener(this);
		threeButton.addMouseListener(this);
		addButton.addMouseListener(this);
		fourButton.addMouseListener(this);
		fiveButton.addMouseListener(this);
		sixButton.addMouseListener(this);
		subtractButton.addMouseListener(this);
		sevenButton.addMouseListener(this);
		eightButton.addMouseListener(this);
		nineButton.addMouseListener(this);
		multiplyButton.addMouseListener(this);
		resetButton.addMouseListener(this);
		zeroButton.addMouseListener(this);
		calculateButton.addMouseListener(this);
		divideButton.addMouseListener(this);
	}

	private void createOutputField(JPanel frame) {
		outputField = new JTextField("0");
		outputField.setHorizontalAlignment(JTextField.RIGHT);
		frame.add(outputField);		
	}

	public static void main(String[] args) {
		new CalculatorGui();
	}

	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {
		if(e.getSource() instanceof JButton){
			char digit = ((JButton) e.getSource()).getText().charAt(0);
			calculator.inputDigit(digit);
			outputField.setText(calculator.getOutput());
		}
	}

}
