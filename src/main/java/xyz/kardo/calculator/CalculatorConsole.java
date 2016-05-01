package xyz.kardo.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalculatorConsole implements Runnable {
	
	Calculator calculator;
	BufferedReader reader;
	
	public CalculatorConsole(){
		calculator = new Calculator();
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	
	public static void main(String[] args) {
		new CalculatorConsole().run();
	}

	public void run() {
		char input = 's';
		while(input != 'q'){
			System.out.print("Calculator (q to quit)> ");
			try {
				input = (char) reader.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			calculator.inputDigit(input);
			System.out.println(calculator.getOutput());
		}
	}
}
