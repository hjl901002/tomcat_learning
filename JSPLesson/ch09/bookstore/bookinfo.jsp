<%@ page contentType="text/html;charset=GB2312"%>
<%@ include file="common.jsp"%>
<jsp:useBean id="cart" scope="session"
	class="org.huangjl.ch09.bookstore.CartBean"/>
<html>
	<head><title>欢迎光临网上书店</title></head>
	<body>
		<jsp:include page="additem.jsp" flush="false"/>
		<%
			String strBookId = request.getParameter("id");
			if(null == strBookId || "".equals(strBookId)){
				response.sendRedirect("catalog.jsp");
			}else{
				int bookId = Integer.parseInt(strBookId);
				BookBean book = bookdb.getBook(bookId);
		%>
		<table border="1">
			<tr>
				<th>书名</th>
				<th>作者</th>
				<th>出版社</th>
				<th>出版日期</th>
				<th>价格</th>
			</tr>
			<tr>
				<td>《<%=book.getTitle() %>》</td>
				<td><%=book.getAuthor() %></td>
				<td><%=book.getBookconcern() %></td>
				<td><%=book.getPublish_date() %></td>
				<td><%=book.getPrice() %></td>
			</tr>
		</table>
		<%
			if(cart.isExist(new Integer(bookId))){
				out.println("该图书已在购物车中<br>");
			}else{
		%>
		<a href="bookinfo.jsp?add=<%=bookId%>&id=<%=bookId%>">加入购物车</a>
		<br>
		<%
			}
		%>
		购物车中现有<%=cart.getNumOfItems() %>种图书
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="showCart.jsp">查看购物车</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="catalog.jsp">查看所有图书</a>
		<%
			}
		%>
	</body>
</html>