<jsp:useBean id="cart" scope="session"
	class="org.huangjl.ch09.bookstore.CartBean" />
<%
	String strBookId = request.getParameter("id");
	if(null == strBookId || "".equals(strBookId)){
		response.sendRedirect("index.jsp");
		return;
	}else{
		cart.deleteItem(Integer.valueOf(strBookId));
		response.sendRedirect("showcart.jsp");
	}
%>