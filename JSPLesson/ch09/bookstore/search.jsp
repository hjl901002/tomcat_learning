<%@ page contentType="text//html;charset=GB2312" %>
<%@ include file="common.jsp"%>
<%@ page import="java.util.Collection,java.util.Iterator" %>
<jsp:useBean id="cart" scope="session"
	class="org.huangjl.ch09.bookstore.CartBean" />
<html>
	<head><title>欢迎光临网上书店</title></head>
	<body>
		<jsp:include page="additem.jsp" flush="false" />
		
	<%
		String strKeyword = request.getParameter("keyword");
		if(null == strKeyword || strKeyword.equals("")){
			response.sendRedirect("catalog.jsp");
			return;
		}
		Collection<BookBean> cl = bookdb.searchBook(strKeyword);
		if(cl.size() <= 0){
			out.println("对不起，没有找到符合条件的图书。");
			out.println("<a href=\"index.jsp\">返回</a>");
			return;
		}
	%>
		<table>
			<tr>
				<th>书名</th>
				<th>价格</th>
				<th>查看</th>
				<th>购买</th>
			</tr>
	<%
		Iterator<BookBean> it = cl.iterator();
		while(it.hasNext()){
			BookBean book = (BookBean)it.next();
			String title = book.getTitle();
			int bookId = book.getId();
			float price = book.getPrice();
	%>
		Iterator<BookBean> it = cl.iterator();
		while(it.hasNext()){
			BookBean book = it.next();
			String title = book.getTile();
			int bookId = book.getTile();
			float price = book.getPrice();
	%>
			<tr>
				<td><a href="bookinfo.jsp?id=<%=bookId%>">《<%=title%>》</a></td>
				<td><%=price%></td>
				<td><a href="bookinfo.jsp?id=<%=bookId%>">详细信息</a></td>
				<td>
					<a href="search.jsp?keyword=<%=strKeyword%>&add=<%=bookId%>">加入购物车</a>
				</td>
			</tr>
		}
	<%
		}
	%>
		</table><p>
		购物车中现有<%=cart.getNumOfItems() %>种图书
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="showcart.jsp">查看购物车</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="index.jsp">回到主页</a>
	</body>
</html>