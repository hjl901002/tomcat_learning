package org.huangjl.ch06.servlet;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.sql.*;

public class DBExceptionServlet extends HttpServlet {
	
	public void init() throws ServletException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException ce) {
			throw new UnavailableException("加载数据库驱动失败！");
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		Connection  conn = null;
		Statement stmt = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "12345678");
			stmt = conn.createStatement();
			stmt.executeUpdate("delete from jobs where job_id=13");
		}catch(SQLException se) {
			getServletContext().log("getServletContext().log()::数据库操作失败！" + se.toString());
			log("GenericServlet.log()::数据库操作失败！" + se.toString());
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "数据库操作出现问题，请联系管理员。");
		}finally {
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException se) {
					log("关闭Statement失败！", se);
				}
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException se) {
					log("关闭数据库连接失败！", se);
				}
				conn = null;
			}
		}
	}
}