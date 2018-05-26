package org.huangjl.ch06.servlet;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

public class ExceptionHandlerServlet extends HttpServlet {
	
	protected void service(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		
		resp.setContentType("text/html;charset=gb2312");
		PrintWriter out = resp.getWriter();
		
		out.println("<html><head><title>错误页面</title></head>");
		out.println("<body>");
		
		out.println("应用程序运行出错。");
		out.println("<p>如错误原因：服务器文件有可能被删除。请<a href=mailto:admin@huangjl.org>报告管理员</a>");
		
		out.println("</body></html>");
		out.close();
	}
}