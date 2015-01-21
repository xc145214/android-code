<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%
  String path = getServletContext().getContextPath();
  //获得数据表的记录
  List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
   function addproduct(){
	   window.location.href="<%=path%>/add.jsp";
   }
   
   function delProduct(pid){
	   window.location.href="<%=path%>/ProductAction?action_flag=del&id="+pid;
   }
   function viewProduct(pid){
	   window.location.href="<%=path%>/ProductAction?action_flag=view&id="+pid;
   }
</script>
</head>
<body>
	<center>
		<table border="1" bordercolor="#CCCCCC" cellpadding="1"
			cellspacing="1" width="300">
			<tr>
				<td>名称</td>
				<td>产品</td>
				<td>价格</td>
				<td>操作</td>
			</tr>
			<% 
           if(list!=null&&!list.isEmpty()){
        	   for(int i=0;i<list.size();i++){
           
         %>
			<tr>
				<td><%=list.get(i).get("name") %></td>
				<td><%=list.get(i).get("address") %></td>
				<td><%=list.get(i).get("price") %></td>
				<td><a href="javascript:delProduct('<%=list.get(i).get("id") %>');">删除</a>&nbsp;<a href="javascript:viewProduct('<%=list.get(i).get("id") %>')">查看</a></td>
			</tr>
			<%
        	   }
           }
         %>
         <tr>
         <td colspan="4" align="center">
             <input type="button" value="添加产品" onclick="addproduct();">
         </td>
         </tr>
		</table>
	</center>
</body>
</html>