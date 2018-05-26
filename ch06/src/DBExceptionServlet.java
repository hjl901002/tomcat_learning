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
			throw new UnavailableException("�������ݿ�����ʧ�ܣ�");
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
			getServletContext().log("getServletContext().log()::���ݿ����ʧ�ܣ�" + se.toString());
			log("GenericServlet.log()::���ݿ����ʧ�ܣ�" + se.toString());
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "���ݿ�����������⣬����ϵ����Ա��");
		}finally {
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException se) {
					log("�ر�Statementʧ�ܣ�", se);
				}
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException se) {
					log("�ر����ݿ�����ʧ�ܣ�", se);
				}
				conn = null;
			}
		}
	}
}