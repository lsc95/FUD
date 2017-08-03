<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>所有用户信息</title>
  </head>
  
  <body>
	<div>
		<table>
			<tr>
				<td>用户id</td>
				<td>用户名</td>
				<td>头像</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${list}" var="user">
				<tr>
					<td>${user.uid }</td>
					<td>${user.uname }</td>
					<td>
						<img src="upload/${user.photoName}" />
					</td>
					<td>
						<a href="down?uid=${user.uid}" >下载</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
  </body>
</html>
