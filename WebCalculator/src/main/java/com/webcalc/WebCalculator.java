package com.webcalc;
//v1.0b
//5 operations, history output servlet
//textarea scrollable entries
//working with tomcat 8 / java 1.8 / localhost


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc")
public class WebCalculator extends HttpServlet {
	private static final long serialVersionUID = 453400722400503671L;

	private History history;

	public void init() {
		this.history = new History(new ArrayList<Expression>());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession();
		System.out.println("doPost entered");
		String output = "problem encountered";
		resp.setContentType("text/html");

		Expression expr = new Expression();
		expr.setOperand1(Float.parseFloat(req.getParameter("firstOperand")));
		expr.setOperand2(Float.parseFloat(req.getParameter("secondOperand")));
		expr.setOperator(req.getParameter("operator"));
		expr.computateResult();
		this.history.add(expr);
		s.setAttribute("history", this.history);
		output = expr.toString();
		resp.getWriter().println(output);

	}

}
