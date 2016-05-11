/* Kardo Jõeleht
 * 
 * The main "brain".
 */
package xyz.kardo.calculator;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Calculator {
	
	private String allowedOperators = "*+-/";
	private char operator;
	private String firstOperand;
	private String secondOperand;
	
	public Calculator(){
		reset();
	}
	
	public String getOutput() {
		return (firstOperand + operator + secondOperand).trim();
	}

	public void reset() {
		operator = ' ';
		firstOperand = "";
		secondOperand = "";
	}

	public void calculate() {
		boolean operatorIsSet = operator != ' ';
		boolean bothOperandsAreSet = !firstOperand.equals("") && !secondOperand.equals("");
		
		if(operatorIsSet && bothOperandsAreSet){
			boolean divisionByZero = secondOperand.equals("0");
			if(divisionByZero){
				reset();
			} else {
				chooseAndDoTheCalculation(operator);
			}
		}
	}
	
	private void chooseAndDoTheCalculation(char operator){
		double calculation = 0;
		if(operator == '*'){
			calculation = Double.valueOf(firstOperand) * Double.valueOf(secondOperand);
		} else if(operator == '+'){
			calculation = Double.valueOf(firstOperand) + Double.valueOf(secondOperand);
		} else if(operator == '-'){
			calculation = Double.valueOf(firstOperand) - Double.valueOf(secondOperand);
		} else if(operator == '/'){
			calculation = Double.valueOf(firstOperand) / Double.valueOf(secondOperand);
		}
		reset();
		firstOperand = replaceCommaWithDot(String.valueOf(calculation));
		firstOperand = removeFloatingZeroes(firstOperand);
	}
	
	private String removeFloatingZeroes(String operand){
		String[] parts = operand.split("\\.");
		if(parts.length >= 2 && Long.valueOf(parts[1]) == 0){
			return parts[0];
		}
		return operand;
	}
	
	private String replaceCommaWithDot(String number) {
		return number.replace(',', '.');
	}

	public void inputDigit(char digit) {
		boolean firstOperandIsSet = !firstOperand.equals("");
		boolean operatorIsSet = operator != ' ';
		boolean secondOperandIsSet = !secondOperand.equals("");
		boolean digitIsAValidOperator = allowedOperators.indexOf(digit) != -1;
		
		if(digit == 'C'){
			reset();
		} else if(digit == '='){
			calculate();
		} else if(firstOperandIsSet && !secondOperandIsSet && digitIsAValidOperator){	
			operator = digit;
		} else if(firstOperandIsSet && operatorIsSet && !digitIsAValidOperator){
			secondOperand = inputDigitOrReplaceLeadingZero(secondOperand, digit);
		} else if(!operatorIsSet && !secondOperandIsSet && !digitIsAValidOperator){
			firstOperand = inputDigitOrReplaceLeadingZero(firstOperand, digit);
		} else if(firstOperandIsSet && operatorIsSet && secondOperandIsSet && digitIsAValidOperator){
			calculate();
			operator = digit;
		}
	}
	
	private String inputDigitOrReplaceLeadingZero(String operand, char digit){
		if(operand.equals("0")){
			return String.valueOf(digit);
		} else {				
			operand += String.valueOf(digit);
			return operand;
		}		
	}

}
