package org.huangjl.ch06.servlet;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

public class ExceptionHandlerServlet2 extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, java.io.IOException {
			resp.setContentType("text/html;charset=GB2312");
			PrintWriter out = resp.getWriter();
			
			out.println("<html><head><title>����ҳ��</title></head>");
			out.println("<body>");
			
			String uri = (String)req.getAttribute("javax.servlet.error.request_uri");
			Object excep = req.getAttribute("javax.servlet.error.exception");
			
			out.println(uri + "���д���");
			out.println("<p>����ԭ��" + excep);
			
			out.println("</body></html>");
			out.close();
	}
}