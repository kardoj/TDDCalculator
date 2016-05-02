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
		calculator.inputDigit('3');
		calculator.inputDigit('/');
		calculator.inputDigit('7');
		calculator.inputDigit('1');
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
		calculator.inputDigit('3');
		calculator.inputDigit('5');
		assertEquals("35", calculator.getOutput());
	}
	
	@Test
	public void inputOperatorDigitAfterFirstOperand(){
		calculator.inputDigit('5');
		calculator.inputDigit('*');
		assertEquals("5*", calculator.getOutput());
	}
	
	@Test
	public void inputOperatorDigitBeforeFirstOperand(){
		calculator.inputDigit('*');
		assertEquals("", calculator.getOutput());
	}
	
	@Test
	public void inputTwoOperatorsAfterFirstOperand(){
		calculator.inputDigit('5');
		calculator.inputDigit('*');
		calculator.inputDigit('*');
		assertEquals("5*", calculator.getOutput());
	}
	
	@Test
	public void inputOperatorAfterOperandOperatorAndOperand(){
		calculator.inputDigit('4');
		calculator.inputDigit('*');
		calculator.inputDigit('5');
		calculator.inputDigit('*');
		assertEquals("20*", calculator.getOutput());
	}
	
	@Test
	public void multiply(){
		calculator.inputDigit('5');
		calculator.inputDigit('*');
		calculator.inputDigit('7');
		calculator.inputDigit('=');
		assertEquals("35", calculator.getOutput());
	}
	
	@Test
	public void add(){
		calculator.inputDigit('5');
		calculator.inputDigit('+');
		calculator.inputDigit('7');
		calculator.inputDigit('=');
		assertEquals("12", calculator.getOutput());
	}
	
	@Test
	public void subtract(){
		calculator.inputDigit('5');
		calculator.inputDigit('-');
		calculator.inputDigit('7');
		calculator.inputDigit('=');
		assertEquals("-2", calculator.getOutput());
	}
	
	@Test
	public void divide(){
		calculator.inputDigit('1');
		calculator.inputDigit('0');
		calculator.inputDigit('/');
		calculator.inputDigit('5');
		calculator.inputDigit('=');
		assertEquals("2", calculator.getOutput());
	}
	
	@Test
	public void divideUneven(){
		calculator.inputDigit('1');
		calculator.inputDigit('0');
		calculator.inputDigit('/');
		calculator.inputDigit('3');
		calculator.inputDigit('=');
		assertEquals("3.333333333", calculator.getOutput());
	}
	
	@Test
	public void divideUnevenDoubleDigits(){
		calculator.inputDigit('4');
		calculator.inputDigit('0');
		calculator.inputDigit('/');
		calculator.inputDigit('3');
		calculator.inputDigit('=');
		assertEquals("13.33333333", calculator.getOutput());		
	}
	
	@Test
	public void calculate(){
		calculator.inputDigit('3');
		calculator.inputDigit('*');
		calculator.inputDigit('4');
		calculator.inputDigit('=');
		assertEquals("12", calculator.getOutput());
	}
	
	@Test
	public void calculateAfterUnevenDivision(){
		calculator.inputDigit('1');
		calculator.inputDigit('0');
		calculator.inputDigit('/');
		calculator.inputDigit('3');
		calculator.inputDigit('+');
		calculator.inputDigit('5');
		calculator.inputDigit('=');
		assertEquals("8.333333333", calculator.getOutput());
	}
	
	@Test
	public void calculateBeforeFirstOperand(){
		calculator.inputDigit('=');
		assertEquals("", calculator.getOutput());
	}
	
	@Test
	public void calculateBeforeSecondOperand(){
		calculator.inputDigit('3');
		calculator.inputDigit('0');
		calculator.inputDigit('/');
		calculator.inputDigit('=');
		assertEquals("30/", calculator.getOutput());
	}
	
	@Test
	public void resetWithC(){
		calculator.inputDigit('5');
		calculator.inputDigit('*');
		calculator.inputDigit('3');
		calculator.inputDigit('C');
		assertEquals("", calculator.getOutput());
	}

}
