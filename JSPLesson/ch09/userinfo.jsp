<%@ page contentType="text/html;charset=GB2312" %>
<jsp:useBean id="user" scope="session" type="org.huangjl.ch09.beans.UserBean" />

���������<jsp:getProperty name="user" property="name" /><br/>
����Ա�<%
			int sex = user.getSex();
			if(1 == sex)
				out.println("��");
			else if(0 == sex)
				out.println("Ů");
			%><br/>
���ѧ����<jsp:getProperty name="user" property="education" /><br/>
���E-mail��<jsp:getProperty name="user" property="email" /><br/>