package org.huangjl.ch06.servlet;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

public class ExceptionHandlerServlet extends HttpServlet {
	
	protected void service(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		
		resp.setContentType("text/html;charset=gb2312");
		PrintWriter out = resp.getWriter();
		
		out.println("<html><head><title>����ҳ��</title></head>");
		out.println("<body>");
		
		out.println("Ӧ�ó������г���");
		out.println("<p>�����ԭ�򣺷������ļ��п��ܱ�ɾ������<a href=mailto:admin@huangjl.org>�������Ա</a>");
		
		out.println("</body></html>");
		out.close();
	}
}