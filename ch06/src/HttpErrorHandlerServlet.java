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
		
		out.println("<html><head><title>����ҳ��</title></head>");
		out.println("<body>");
		
		switch(status_code) {
			case 401:
				break;
			case 404:
				out.println("<h2>HTTP״̬���룺" + status_code + "</h2>");
				out.println("�������ʵ�ҳ�沢�����ڣ� �����ѱ�ת�Ƶ�����λ�á�");
				out.println("<p>�����������⣬��<a href=mailto:admin@huangjl.org>��ϵ����Ա</a>");
				break;
			default:
				break;
		}
		out.println("</body></html>");
		out.close();
	}
}