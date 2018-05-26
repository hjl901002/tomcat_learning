package org.huangjl.ch05.servlet;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import org.huangjl.ch05.util.OutputSessionInfo;

public class GreetServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		HttpSession session = req.getSession();
		String user = (String)session.getAttribute("user");
		
		if (null == user){
			resp.sendRedirect("login");
		}else {
			resp.setContentType("text/html;charset=gb2312");
			PrintWriter out = resp.getWriter();
			out.println("<html><head><title>»¶Ó­Ò³Ãæ</title></head>");
			out.println("<body>");
			
			OutputSessionInfo.printSessionInfo(out, session);
			
			out.println("<p>");
			out.println("»¶Ó­Äã£¬" + user + "</p>");
			out.println("<a href=login>ÖØÐÂµÇÂ¼</a>");
			out.println("<a href=logout>×¢Ïú</a>");
			out.println("</body></html>");
			out.close();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		doGet(req, resp);
	}
}