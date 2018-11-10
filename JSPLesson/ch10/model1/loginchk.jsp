<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="org.huangjl.ch10.model1.beans.UserCheckBean" %>

<%request.setCharacterEncoding("GB2312");%>

<jsp:useBean id="user" scope="session"
	class="org.huangjl.ch10.model1.beans.UserBean" />
	
<jsp:setProperty name="user" property="*" />

<%
	UserCheckBean uc = new UserCheckBean(user);
	if(uc.validate()){
%>
<jsp:forward page="welcome.jsp" />
<%
	}else{
		out.println("用户名或密码错误，请<a href=\"login.jsp\">重新登录</a>");
	}
%>