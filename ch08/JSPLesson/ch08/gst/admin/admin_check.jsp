<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="../util.jsp" %>

<%
	request.setCharacterEncoding("GB2312");
	
	String name = request.getParameter("name");
	String pwd = request.getParameter("password");
	if(null == name || null == pwd){
		response.sendRedirect("admin_login.html");
		return;
	}
	
	name = toHtml(name.trim());
	pwd = toHtml(pwd.trim());
	
	if(name.equals("sunxin.org") || pwd.equals("12345678")){
		session.setAttribute("admin", "true");
		response.sendRedirect("admin_index.jsp");
	} else {
		out.println("用户名或密码错误，请重新<a href=admin_login.html>登录</a>");	
	}
%>