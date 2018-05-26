package org.huangjl.ch05.servlet;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

public class GreetServlet2 extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		if(null != cookies && cookies.length != 0) {
			String name = null;
			String pwd = null;
			
			for(int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				String cName = cookie.getName();
				
				if(cName.equals("userinfo")) {
					String cValue = cookie.getValue();
					String[] userInfo = cValue.split("&");
					
					for(int j = 0; j < userInfo.length; j++) {
						String[] value = userInfo[j].split("-");
						if(value[0].equals("username")) {
							name = value[1];
						}
						if(value[0].equals("password")) {
							pwd = value[1];
						}
					}
				}
			}
			
			if("zhangsan".equals(name) && "1234".equals(pwd)) {
				resp.setContentType("text/html;charset=gb2312");
				PrintWriter out = resp.getWriter();
				out.println("<html>");
				out.println("<meta http-equiv=\"Pragma\" content=\"no-cache\">");
				out.println("<head><title>»¶Ó­Ò³Ãæ</title></head>");
				out.println("<body>");
				out.println(name + "£¬»¶Ó­Äã");
				out.println("<a href=login2>ÖØÐÂµÇÂ¼</a>");
				out.println("</body></html>");
				out.close();
				return;
			}
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("login2");
		rd.forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		doGet(req, resp);
	}
}