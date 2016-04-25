package com.webcalc;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/history")
public class ShowHistory extends HttpServlet {

	private static final long serialVersionUID = -8800139920627161230L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("ShowHistory");
		resp.setContentType("text/html");
		History outputH = (History) req.getSession().getAttribute("history");

		resp.getWriter().println("<textarea rows=\"10\" cols=\"50\">");

		Iterator<Expression> listIterator = outputH.getList().listIterator();
		while (listIterator.hasNext()) {
			resp.getWriter().println(listIterator.next().toString());
			System.out.println("sent line");
		}

		resp.getWriter().println("</textarea>");
		resp.getWriter().flush();
		resp.getWriter().close();

	}

}
