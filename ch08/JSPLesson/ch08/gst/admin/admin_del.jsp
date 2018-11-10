<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.sql.*,javax.sql.*,javax.naming.*" %>
<%
	String admin = (String)session.getAttribute("admin");
	if(admin == null || !admin.equals("true")){
		out.println("你无权访问这个页面！！！");
		return;
	}
%>
<%
	String strID = request.getParameter("gst_id");
	int id = Integer.parseInt(strID);
	
	Context ctx = new InitialContext();
	DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/bookstore");
	Connection conn = ds.getConnection();
	
	PreparedStatement pstmt = conn.prepareStatement("delete from guestbook where gst_id=?");
	pstmt.setInt(1, id);
	pstmt.executeUpdate();
	response.sendRedirect("admin_index.jsp");
	pstmt.close();
	conn.close();
%>