package com.webcalc;

public class Expression {
	private float operand1;
	private float operand2;
	private String operator;
	private float result;

	public Expression() {
		// TODO Auto-generated constructor stub
	}

	public Expression(String operator, float operand1, float operand2) {
		this.operator = operator;
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.computateResult();
	}

	public float getOperand1() {
		return operand1;
	}

	public void setOperand1(float operand1) {
		this.operand1 = operand1;
	}

	public float getOperand2() {
		return operand2;
	}

	public void setOperand2(float operand2) {
		this.operand2 = operand2;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public void computateResult(){
		if (this.operator.equals("+")){
			this.result = this.operand1+this.operand2;
		}
		if (this.operator.equals("-")){
			this.result = this.operand1-this.operand2;
		}
		if (this.operator.equals("*")){
			this.result = this.operand1*this.operand2;
		}
		if (this.operator.equals("/")){
			this.result = this.operand1/this.operand2;
		}
		if (this.operator.equals("^")){
			this.result = (float) Math.pow(this.operand1, this.operand2);
		}
			
	}
	
	public float getResult(){
		return this.result;
	}
	
	public String toString(){
		String returnValue = operand1 + " " + operator + " " + operand2 + " = " + result+"\n";
		return returnValue; 
	}

}
