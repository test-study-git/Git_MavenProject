<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>Title</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">   
<link href="${pageContext.request.contextPath}/css/body.css" rel="stylesheet" type="text/css"/> 
</head>  
<body>   	
</body>
</html>
