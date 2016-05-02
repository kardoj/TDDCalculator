/* Kardo Jõeleht
 * 
 * The main "brain".
 */
package xyz.kardo.calculator;

import java.math.RoundingMode;
import java.text.DecimalFormat;

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
			firstOperand = roundCalculationToLength(calculation);
			firstOperand = replaceCommaWithDot(firstOperand);
		}
	}
	
	private String replaceCommaWithDot(String number) {
		return number.replace(',', '.');
	}

	private String roundCalculationToLength(double calculation){
		int commaIndex = String.valueOf(calculation).indexOf('.');
		String formatString = buildFormatString(commaIndex);
		DecimalFormat format = new DecimalFormat(formatString);
		format.setRoundingMode(RoundingMode.HALF_UP);
		return format.format(calculation);
	}
	
	// The purpose of this method is to always get the calculation to be the same length, 11 characters
	private String buildFormatString(int commaIndex){
		int formatLength = 11;
		StringBuilder builder = new StringBuilder(formatLength);
		for(int i=0; i<formatLength; i++){
			if(i == commaIndex){
				builder.append(".");
			} else {
				builder.append("#");
			}
		}
		return builder.toString();
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
			secondOperand += String.valueOf(digit);
		} else if(!operatorIsSet && !secondOperandIsSet && !digitIsAValidOperator){
			firstOperand += String.valueOf(digit);
		} else if(firstOperandIsSet && operatorIsSet && secondOperandIsSet && digitIsAValidOperator){
			calculate();
			operator = digit;
		}
	}

}
