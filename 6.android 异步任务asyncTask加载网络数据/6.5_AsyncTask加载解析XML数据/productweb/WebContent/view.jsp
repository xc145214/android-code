<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = getServletContext().getContextPath();
	Map<String, Object> map = (Map<String, Object>) request
	.getAttribute("map");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>显示详细记录</title>
</head>
<body>
	产品名称：
	<%=map.get("name")%>
	<br> 产品产地：
	<%=map.get("address")%>
	<br> 产品价格：
	<%=map.get("price")%>
	<br> 产品图片：
	
	<img src="<%=path+"//upload//"+map.get("img")%>">
	<br>
	<input type="button" name="submit" value="返回列表界面" onclick="javascript:window.history.back();">
</body>
</html>