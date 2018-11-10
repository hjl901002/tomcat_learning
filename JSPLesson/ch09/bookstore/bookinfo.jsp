<%@ page contentType="text/html;charset=GB2312"%>
<%@ include file="common.jsp"%>
<jsp:useBean id="cart" scope="session"
	class="org.huangjl.ch09.bookstore.CartBean"/>
<html>
	<head><title>��ӭ�����������</title></head>
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
				<th>����</th>
				<th>����</th>
				<th>������</th>
				<th>��������</th>
				<th>�۸�</th>
			</tr>
			<tr>
				<td>��<%=book.getTitle() %>��</td>
				<td><%=book.getAuthor() %></td>
				<td><%=book.getBookconcern() %></td>
				<td><%=book.getPublish_date() %></td>
				<td><%=book.getPrice() %></td>
			</tr>
		</table>
		<%
			if(cart.isExist(new Integer(bookId))){
				out.println("��ͼ�����ڹ��ﳵ��<br>");
			}else{
		%>
		<a href="bookinfo.jsp?add=<%=bookId%>&id=<%=bookId%>">���빺�ﳵ</a>
		<br>
		<%
			}
		%>
		���ﳵ������<%=cart.getNumOfItems() %>��ͼ��
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="showCart.jsp">�鿴���ﳵ</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="catalog.jsp">�鿴����ͼ��</a>
		<%
			}
		%>
	</body>
</html>