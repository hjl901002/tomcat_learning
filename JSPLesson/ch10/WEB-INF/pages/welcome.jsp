<%@ page contentType="text/html;charset=GB2312" %>
<jsp:useBean id="user" scope="session"
	type="org.huangjl.ch10.model2.beans.UserBean" />
��ӭ�㣬<jsp:getProperty name="user" property="name" />