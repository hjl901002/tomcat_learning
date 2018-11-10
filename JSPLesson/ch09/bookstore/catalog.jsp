<%@ page contentType="text/html; charset=GB2312" %>
<%@ include file="common.jsp" %>
<%@ page import="java.util.Collection, java.util.Iterator"%>
<jsp:useBean id="cart" scope="session" class="org.huangjl.ch09.bookstore.CartBean" />

<html>
	<head><title>��ӭ�����������</title></head>
	<body>
		<jsp:include page="additem.jsp" flush="false" />
		
		<h1>����վ���۵�ͼ���У�</h1><p>
		<%
			Collection<BookBean> cl = bookdb.getBooks();
			Iterator<BookBean> it = cl.iterator();
		%>
		<table>
			<tr>
				<th>����</th>
				<th>�۸�</th>
				<th>����</th>
			</tr>
			<%
				while(it.hasNext()){
					BookBean book = (BookBean)it.next();
					String title = book.getTitle();
					int bookId = book.getId();
					float price = book.getPrice();
			%>
			<tr>
				<td><a href="bookinfo.jsp?id=<%=bookId%>">��<%=title%>��</a></td>
				<td><%=price%></td>
				<td><a href="catalog.jsp?add=<%=bookId%>">���빺�ﳵ</a></td>
			</tr>
			<%
				}
			%>
		</table><p>
		���ﳵ������<%=cart.getNumOfItems()%>��ͼ��
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="showcart.jsp">�鿴���ﳵ</a>
	</body>
</html>