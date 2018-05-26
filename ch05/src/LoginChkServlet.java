package org.huangjl.ch05.servlet;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

public class LoginChkServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
			req.setCharacterEncoding("gb2312");
			String name = req.getParameter("user");
			String pwd = req.getParameter("password");
			
			if (null == name || null == pwd || name.equals("") || pwd.equals("")){
				resp.sendRedirect("login");
				return;
			}else {
				HttpSession session = req.getSession();
				session.setAttribute("user", name);
				resp.sendRedirect("greet");
				return;
			}
		}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		doGet(req, resp);
	}
}