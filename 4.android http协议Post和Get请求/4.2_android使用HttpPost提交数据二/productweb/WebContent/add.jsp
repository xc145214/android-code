<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
//获得工程的路径
	String path = getServletContext().getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加产品信息</title>
</head>
<body>
	<form action="<%=path %>/ProductAction?action_flag=add" method="POST" name="form1" enctype="multipart/form-data">
		产品名称：<input type="text" name="name" value=""><br> 产品产地：<input
			type="text" name="address" value=""><br> 产品价格：<input
			type="text" name="price" value=""><br> 产品图片：<input
			type="file" name="img" value="" size="40"><br> <input
			type="submit" name="submit" value="添加产品信息">
	</form>
</body>
</html>