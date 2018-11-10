<%@ page contentType="text/html;charset=GB2312" %>
<jsp:useBean id="user" scope="session" type="org.huangjl.ch09.beans.UserBean" />

你的姓名：<jsp:getProperty name="user" property="name" /><br/>
你的性别：<%
			int sex = user.getSex();
			if(1 == sex)
				out.println("男");
			else if(0 == sex)
				out.println("女");
			%><br/>
你的学历：<jsp:getProperty name="user" property="education" /><br/>
你的E-mail：<jsp:getProperty name="user" property="email" /><br/>