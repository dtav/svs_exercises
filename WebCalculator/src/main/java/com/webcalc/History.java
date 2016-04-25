package com.webcalc;

import java.util.ArrayList;
import java.util.Iterator;

public class History {
	private ArrayList<Expression> listOfExpressions;
	
	public History(ArrayList<Expression> listExpr){
		this.listOfExpressions = listExpr;
	}
	
	public void add(Expression e){
		this.listOfExpressions.add(e);
	}
	
	public void clear(){
		this.listOfExpressions.clear();
	}
	
	public int getLength(){
		return this.listOfExpressions.size();
	}
	
	public String toString(){
		StringBuilder returnValue = new StringBuilder();
		Iterator<Expression> listIterator = this.listOfExpressions.listIterator();
		while (listIterator.hasNext()){
			Expression e = listIterator.next();
			returnValue.append(e.toString());
			returnValue.append("\n");
		}
		return returnValue.toString();
	}
	
	public ArrayList<Expression> getList(){
		return this.listOfExpressions;
	}

}
