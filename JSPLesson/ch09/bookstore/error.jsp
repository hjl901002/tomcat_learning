<%@ page contentType="text/html;charset=GB2312" %>
<%@ page isErrorPage="true" %>
<html>
	<head><title>错误页面</title></head>
	<body>
		<h1>Web应用程序发生错误</h1>
		错误原因：<%=exception.toString() %>
	</body>
</html>