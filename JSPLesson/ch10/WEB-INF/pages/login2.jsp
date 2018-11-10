<%@ page contentType="text/html;charset=GB2312" %>
<%
	//获取上下文路径，对于servlet2.5规范，还可以使用下面注释中的代码
	//String path = application.getContextPath();
	String path = request.getContextPath();
%>
<html>
	<head><title>登录页面</title></head>
	<body>
		<form method="post" action="<%=path %>/controller">
			<input type="hidden" name="action" value="login" />
			用户名：<input type="text" name="name" /><br/>
			密码：<input type="password" name="password" /><p/>
			<input type="reset" value="重填" />
			<input type="submit" value="登录" />
		</form>
	</body>
</html>