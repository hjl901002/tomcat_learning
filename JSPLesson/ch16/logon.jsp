<%@ page contenetType="text/html;charset=GB2312"%>
<form>
	<table>
		<tr>
			<td>用户名：</td>
			<td><input type="text" name="name" /></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input type="password" name="password" /></td>
		</tr>
		<tr>
			<input type="hidden" name="origin_uri" value="${requestScope.orgin_uri}" />
		</tr>
		<tr>
			<td><input type="reset" value="重填" /></td>
			<td><input type="submit" value="提交" /></td>
		</tr>
	</table>
</form>