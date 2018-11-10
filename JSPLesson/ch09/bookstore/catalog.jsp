<%@ page contentType="text/html; charset=GB2312" %>
<%@ include file="common.jsp" %>
<%@ page import="java.util.Collection, java.util.Iterator"%>
<jsp:useBean id="cart" scope="session" class="org.huangjl.ch09.bookstore.CartBean" />

<html>
	<head><title>欢迎光临网上书店</title></head>
	<body>
		<jsp:include page="additem.jsp" flush="false" />
		
		<h1>本网站销售的图书有：</h1><p>
		<%
			Collection<BookBean> cl = bookdb.getBooks();
			Iterator<BookBean> it = cl.iterator();
		%>
		<table>
			<tr>
				<th>书名</th>
				<th>价格</th>
				<th>购买</th>
			</tr>
			<%
				while(it.hasNext()){
					BookBean book = (BookBean)it.next();
					String title = book.getTitle();
					int bookId = book.getId();
					float price = book.getPrice();
			%>
			<tr>
				<td><a href="bookinfo.jsp?id=<%=bookId%>">《<%=title%>》</a></td>
				<td><%=price%></td>
				<td><a href="catalog.jsp?add=<%=bookId%>">加入购物车</a></td>
			</tr>
			<%
				}
			%>
		</table><p>
		购物车中现有<%=cart.getNumOfItems()%>种图书
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="showcart.jsp">查看购物车</a>
	</body>
</html>