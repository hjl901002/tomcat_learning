package org.huangjl.ch05.servlet;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import org.huangjl.ch05.util.OutputSessionInfo;

public class LoginServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=gb2312");
		
		HttpSession session = req.getSession();
		String user = (String)session.getAttribute("user");
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<meta http-equiv=\"Pragma\" content=\"nocache\">");
		out.println("<head><title>µ«¬º“≥√Ê</title></head>");
		out.println("<body>");
		
		OutputSessionInfo.printSessionInfo(out, session);
		
		out.println("<p>");
		out.println("<form method=post action=loginchk>");
		
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>«Î ‰»Î”√ªß√˚</td>");
		if (null == user){
			out.println("<td><input type=text name=user /></td>");
		}else {
			out.println("<td><input type=text name=user value=" + user + " /></td>");
		}
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>«Î ‰»Î√‹¬Î</td>");
		out.println("<td><input type=password name=password /></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td><input type=reset value=÷ÿÃÓ /></td>");
		out.println("<td><input type=submit value=µ«¬º /></td>");
		out.println("</tr>");
		
		out.println("</table>");
		
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		doGet(req, resp);
	}
}