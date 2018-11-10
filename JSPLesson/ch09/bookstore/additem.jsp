<%@ include file="common.jsp" %>
<jsp:useBean id="cart" scope="session" 
	class="org.huangjl.ch09.bookstore.CartBean" />
<%
	String strBookId = request.getParameter("add");
	if(strBookId != null && !"".equals(strBookId)){
		int bookId = Integer.parseInt(strBookId);
		BookBean book = bookdb.getBook(bookId);
		cart.addItem(new Integer(bookId), book);
	}
%>