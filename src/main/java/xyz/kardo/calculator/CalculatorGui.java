/* Kardo Jõeleht
 * 
 * An attempt at making a graphical user interface
 * for the calculator.
 */
package xyz.kardo.calculator;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorGui implements MouseListener{

	Calculator calculator;
	JFrame window;
	JTextField outputField;
	String buttonLabelsInOrder = "123+456-789*C0=/";
	List<JButton> buttons = new ArrayList<JButton>();
	
	public CalculatorGui(){
		window = new JFrame("Calculator");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createGuiElements(window);
		window.setPreferredSize(new Dimension(300, 500));
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

		int length = buttonLabelsInOrder.length();
		for (int i = 0; i < length; i++) {
			JButton newButton = new JButton(String.valueOf(buttonLabelsInOrder.charAt(i)));
			newButton.addMouseListener(this);
			buttons.add(newButton);
			buttonPanel.add(newButton);
		}
		frame.add(buttonPanel);
	}

	private void createOutputField(JPanel frame) {
		outputField = new JTextField("0");
		outputField.setHorizontalAlignment(JTextField.RIGHT);
		outputField.setFocusable(false);
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
			
			String output = calculator.getOutput();
			if(output.equals("")){
				output = "0";
			}
			
			outputField.setText(output);
		}
	}

}
