package org.huangjl.ch06.servlet;

import javax.servlet.*;
import java.io.*;
import java.util.Properties;
import javax.servlet.http.*;

public class FileExceptionServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
			FileInputStream fis = new FileInputStream("config.inc");
			Properties pps = new Properties();
			pps.load(fis);
			//读取属性的代码，省略。
			
			fis.close();
	}
}