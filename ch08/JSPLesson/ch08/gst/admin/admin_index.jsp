<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.sql.*,javax.sql.*,javax.naming.*" %>

<%
	String admin = (String)session.getAttribute("admin");
	if(admin == null || !admin.equals("true")){
		out.println("你无权访问这个页面！！！");
		return;
	}
%>
<html>
	<head>
		<title>网上书店留言板</title>
	</head>
	<body>
		<a href="../say.html"></a><br>
		<%
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/bookstore");
			Connection conn = ds.getConnection();
			
			//创建可滚动的结果集。
			Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
				
			ResultSet rs = stmt.executeQuery("select * from guestbook order by gst_time desc");
			
			//移动游标至结果集的最后一行
			rs.last();
			
			//得到当行的行数，也就得到了数据库中留言的总数
			int rowCount = rs.getRow();
			if(rowCount == 0) {
				out.println("当前没有任何留言！");
				return;
			} else {
		%>
				共有<strong><%=rowCount%></strong>条留言&nbsp;&nbsp;&nbsp;&nbsp;
		<%
			}
			String strCurPage = request.getParameter("page");
			
			//表示当前页数
			int curPage;
			
			if(strCurPage == null)
				curPage = 1;
			else
				curPage = Integer.parseInt(strCurPage);
			
			//定义每页显示的留言数
			int countPerPage = 5;
			
			//计算显示所有留言需要的总页数
			int pageCount = (rowCount + countPerPage - 1)/countPerPage;
			
			//移动游标至结果集中指定的行，如果显示的是第一页，curPage=1，
			//游标移动到第一行
			rs.absolute((curPage - 1) * countPerPage + 1);
			//如果是第一页，则显示不带链接的文字，如果不是第一页
			//则给用户提供跳转到第一页和上一页的链接
			if(curPage == 1) {
		%>
				第一页&nbsp;&nbsp;&nbsp;&nbsp;
				上一页&nbsp;&nbsp;&nbsp;&nbsp;
		<%
			} else {
		%>
				<a href="admin_index.jsp?page=<%=1%>">第一页</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="admin_index.jsp?page=<%=curPage-1%>">上一页</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
		<%
			}
			//如果当前页是最后一页，则显示不带链接的文字，如果不是最后一页，
			//则用户提供跳转到最后一页和下一页的链接
			if(curPage == pageCount) {
		%>
				下一页&nbsp;&nbsp;&nbsp;&nbsp;
				最后页&nbsp;&nbsp;&nbsp;&nbsp;
		<%	
			} else {
		%>
				<a href="admin_index.jsp?page=<%=curPage-1%>">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="admin_index.jsp?page=<%=pageCount%>">最后页</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<%
			}
			
			int i = 0;
			//以循环的方式取出每页要显示的数据，因为在前面针对要显示的页数，
			//调用了rs.absolute((curPage-1)*countPerPage+1);
			//所以是从游标所在位置取出当前页要显示的数据
			while(i < countPerPage && !rs.isAfterLast()){
				out.println("<hr color=\"blue\" size=\"2\"><br>");
				out.println("用户名：" + rs.getString("gst_user"));
				out.println("&nbsp;&nbsp;");
				
				Timestamp ts = rs.getTimestamp("gst_time");
				long lms = ts.getTime();
				Date date = new Date(lms);
				Time time = new Time(lms);
				
				out.println("留言时间：" + date + " " + time);
				
				out.println("&nbsp;&nbsp;");
				out.println("用户IP：" + rs.getString("gst_ip") + "<br>");
				out.println("主题：" + rs.getString("gst_title") + "<br>");
				out.println("内容：" + rs.getString("gst_content") + "<br>");
				out.println("<a href=admin_del.jsp?gst_id=" + rs.getInt(1) + ">删除</a>");
				i++;
				rs.next();
			}
			rs.close();
			stmt.close();
			conn.close();
		%>
		
	</body>
</html>