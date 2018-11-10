<%@ page contentType="text/html;charset=GB2312" %>
<%
	request.setCharacterEncoding("GB2312");
%>
<jsp:useBean id="user" scope="session" class="org.huangjl.ch09.beans.UserBean" />
<jsp:setProperty name="user" property="*" />
<jsp:setProperty name="user" property="email" param="mail" />

×¢²á³É¹¦!