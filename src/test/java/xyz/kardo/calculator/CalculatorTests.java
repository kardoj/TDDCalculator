/*
 * Kardo Jõeleht
 */
package xyz.kardo.calculator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorTests {
	public static Calculator calculator;
	
	@BeforeClass
	public static void initialize(){
		calculator = new Calculator();
	}
	
	@Before
	public void resetCalculator(){
		calculator.reset();
	}

	@Test
	public void calculator(){
		assertEquals("", calculator.getOutput());
	}
	
	@Test
	public void resetFunction(){
		inputDigitSequence("3/71");
		calculator.reset();
		assertEquals("", calculator.getOutput());
	}
	
	@Test
	public void inputFirstDigit(){
		calculator.inputDigit('5');
		assertEquals("5", calculator.getOutput());
	}
	
	@Test
	public void inputMoreDigitsBeforeOperand(){
		inputDigitSequence("35");
		assertEquals("35", calculator.getOutput());
	}
	
	@Test
	public void inputOperatorDigitAfterFirstOperand(){
		inputDigitSequence("5*");
		assertEquals("5*", calculator.getOutput());
	}
	
	@Test
	public void inputOperatorDigitBeforeFirstOperand(){
		calculator.inputDigit('*');
		assertEquals("", calculator.getOutput());
	}
	
	@Test
	public void inputTwoOperatorsAfterFirstOperand(){
		inputDigitSequence("5**");
		assertEquals("5*", calculator.getOutput());
	}
	
	@Test
	public void inputOperatorAfterOperandOperatorAndOperand(){
		inputDigitSequence("4*5*");
		assertEquals("20*", calculator.getOutput());
	}
	
	@Test
	public void multiply(){
		inputDigitSequence("5*7=");
		assertEquals("35", calculator.getOutput());
	}
	
	@Test
	public void add(){
		inputDigitSequence("5+7=");
		assertEquals("12", calculator.getOutput());
	}
	
	@Test
	public void subtract(){
		inputDigitSequence("5-7=");
		assertEquals("-2", calculator.getOutput());
	}
	
	@Test
	public void divide(){
		inputDigitSequence("10/5=");
		assertEquals("2", calculator.getOutput());
	}
	
	@Test
	public void divideUneven(){
		inputDigitSequence("10/3=");
		assertEquals("3.333333333", calculator.getOutput());
	}
	
	@Test
	public void divideUnevenDoubleDigits(){
		inputDigitSequence("40/3=");
		assertEquals("13.33333333", calculator.getOutput());		
	}
	
	@Test
	public void calculate(){
		inputDigitSequence("3*4=");
		assertEquals("12", calculator.getOutput());
	}
	
	@Test
	public void calculateAfterUnevenDivision(){
		inputDigitSequence("10/3+5=");
		assertEquals("8.333333333", calculator.getOutput());
	}
	
	@Test
	public void calculateBeforeFirstOperand(){
		calculator.inputDigit('=');
		assertEquals("", calculator.getOutput());
	}
	
	@Test
	public void calculateBeforeSecondOperand(){
		inputDigitSequence("30/=");
		assertEquals("30/", calculator.getOutput());
	}
	
	@Test
	public void resetWithC(){
		inputDigitSequence("5*3C");
		assertEquals("", calculator.getOutput());
	}
	
	private void inputDigitSequence(String sequence){
		int length = sequence.length();
		for(int i=0; i<length; i++){
			calculator.inputDigit(sequence.charAt(i));
		}
	}

}
