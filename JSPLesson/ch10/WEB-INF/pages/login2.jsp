<%@ page contentType="text/html;charset=GB2312" %>
<%
	//��ȡ������·��������servlet2.5�淶��������ʹ������ע���еĴ���
	//String path = application.getContextPath();
	String path = request.getContextPath();
%>
<html>
	<head><title>��¼ҳ��</title></head>
	<body>
		<form method="post" action="<%=path %>/controller">
			<input type="hidden" name="action" value="login" />
			�û�����<input type="text" name="name" /><br/>
			���룺<input type="password" name="password" /><p/>
			<input type="reset" value="����" />
			<input type="submit" value="��¼" />
		</form>
	</body>
</html>