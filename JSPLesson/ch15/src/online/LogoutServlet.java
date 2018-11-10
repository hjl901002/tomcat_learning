package org.huangjl.ch15.online;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException{
		resp.setContentType("text/html;charset=gb2312");
		
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		session.invalidate();
		
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>退出登录</title></head><body>");
		out.println(user.getName() + "，你已退出登录<br>");
		out.println("<a href=login.html>重新登录</a>");
		out.println("</body></html>");
		out.close();
	}
}