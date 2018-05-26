package org.huangjl.ch06.servlet;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

public class HttpErrorHandlerServlet extends HttpServlet {
	
	protected void service(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		
		resp.setContentType("text/html;charset=gb2312");
		PrintWriter out = resp.getWriter();
		
		Integer status_code = (Integer)req.getAttribute("javax.servlet.error.status_code");
		
		out.println("<html><head><title>错误页面</title></head>");
		out.println("<body>");
		
		switch(status_code) {
			case 401:
				break;
			case 404:
				out.println("<h2>HTTP状态代码：" + status_code + "</h2>");
				out.println("您所访问的页面并不存在， 或者已被转移到其他位置。");
				out.println("<p>如有其他问题，请<a href=mailto:admin@huangjl.org>联系管理员</a>");
				break;
			default:
				break;
		}
		out.println("</body></html>");
		out.close();
	}
}