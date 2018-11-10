<%@ page contentType="text/html; charset=GB2312" %>

<html>
	<head><title>欢迎光临网上书店</title></head>
	<body>
		<center>
			<h1>欢迎光临网上书店</h1>
			搜索图书<br>
			<form method="GET" action="search.jsp">
				请输入关键字：<input type="text" name="keyword" />
				<input type="submit" value="搜索" />
			</form>
			<br><a href="catalog.jsp">查看所有图书</a>
		</center>
	</body>
</html>