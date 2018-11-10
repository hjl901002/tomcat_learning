<%@ page contentType="text//html;charset=GB2312" %>
<%@ include file="common.jsp"%>
<%@ page import="java.util.Collection,java.util.Iterator" %>
<jsp:useBean id="cart" scope="session"
	class="org.huangjl.ch09.bookstore.CartBean" />
<html>
	<head><title>��ӭ�����������</title></head>
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
			out.println("�Բ���û���ҵ�����������ͼ�顣");
			out.println("<a href=\"index.jsp\">����</a>");
			return;
		}
	%>
		<table>
			<tr>
				<th>����</th>
				<th>�۸�</th>
				<th>�鿴</th>
				<th>����</th>
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
				<td><a href="bookinfo.jsp?id=<%=bookId%>">��<%=title%>��</a></td>
				<td><%=price%></td>
				<td><a href="bookinfo.jsp?id=<%=bookId%>">��ϸ��Ϣ</a></td>
				<td>
					<a href="search.jsp?keyword=<%=strKeyword%>&add=<%=bookId%>">���빺�ﳵ</a>
				</td>
			</tr>
		}
	<%
		}
	%>
		</table><p>
		���ﳵ������<%=cart.getNumOfItems() %>��ͼ��
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="showcart.jsp">�鿴���ﳵ</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="index.jsp">�ص���ҳ</a>
	</body>
</html>