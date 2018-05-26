package org.huangjl.ch05.servlet;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

public class LoginServlet2 extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
			String action = req.getParameter("action");
			if("chk".equals(action)) {
				String name = req.getParameter("user");
				String pwd = req.getParameter("password");
				if((name != null) && (pwd != null)){
					if(name.equals("zhangsan") && pwd.equals("1234")) {
						StringBuffer sb = new StringBuffer();
						sb.append("username-");
						sb.append(name);
						sb.append("&password-");
						sb.append(pwd);
						
						Cookie cookie = new Cookie("userinfo", sb.toString());
						cookie.setMaxAge(1800);
						resp.addCookie(cookie);
						resp.sendRedirect("greet2");
						return;
					} else{
						resp.setContentType("text/html;charset=gb2312");
						PrintWriter out = resp.getWriter();
						out.println("用户名或密码错误，请<a href=login2>重新登录</a>");
						return;
					}
				}
			}else {
				resp.setContentType("text/html;charset=gb2312");
				PrintWriter out = resp.getWriter();
				out.println("<html>");
				out.println("<meta http-equiv=\"Pragma\" content=\"no-cache\">");
				out.println("<head><title>登录页面</title></head>");
				out.println("<body>");
				out.println("<p>");
				out.println("<form method=post action=login2?action=chk>");
				
				out.println("<table>");
				
				out.println("<tr>");
				out.println("<td>请输入用户名</td>");
				out.println("<td><input type=text name=user /></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td>请输入密码</td>");
				out.println("<td><input type=password name=password /></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td><input type=reset value=重填></td>");
				out.println("<td><input type=submit value=登录></td>");
				out.println("</tr>");
				
				out.println("</table></form></body></html>");
				out.close();
			}
	}
		
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		doGet(req, resp);
	}
}