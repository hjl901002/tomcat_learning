<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.sql.*, javax.sql.*, javax.naming.*" %>

<%
	request.setCharacterEncoding("gb2312");
	
	String name = request.getParameter("name");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	if(null == name || null == title || null == content) {
		response.sendRedirect("index.jsp");
		return;
	}
	
	if(name.equals("") || title.equals("")) {
		response.sendRedirect("say.html");
		return;
	}
	
	String fromIP = request.getRemoteAddr();
	
	Context ctx = new InitialContext();
	DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/bookstore");
	Connection conn = ds.getConnection();
	
	PreparedStatement pstmt = conn.prepareStatement(
		"insert into guestbook(gst_user, gst_title, gst_content, gst_ip, gst_time) values (?, ?, ?, ?, ?)"
	);
	pstmt.setString(1, name);
	pstmt.setString(2, title);
	pstmt.setString(3, content);
	pstmt.setString(4, fromIP);
	pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
	
	pstmt.executeUpdate();
	pstmt.close();
	conn.close();
	response.sendRedirect("index.jsp");
%>