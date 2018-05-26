package org.huangjl.ch06.servlet;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

public class ExceptionServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		try {
			int a = 5;
			int b = 0;
			int c = a/b;
		}catch(ArithmeticException ae) {
			req.setAttribute("javax.servlet.error.exception", ae);
			req.setAttribute("javax.servlet.error.request_uri", req.getRequestURI());
			
			RequestDispatcher rd = req.getRequestDispatcher("ExcepHandler2");
			rd.forward(req, resp);
		}
	}
}