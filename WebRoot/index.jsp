<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<body>
	<div>
		<table>
			<form action="up" method="post" enctype="multipart/form-data" >
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="uname" value=""
					placeholder="请输入用户名" /></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="pwd" value=""
					placeholder="请输入密码" /></td>
			</tr>
			<tr>
				<td>头像:</td>
				<td><input type="file" name="pic" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="提交" /></td>
			</tr>
			</form>
		</table>
	</div>
</body>
</html>
