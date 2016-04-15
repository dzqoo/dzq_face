<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>人脸识别系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="./css/style.css">
  </head>
  
  <body>
  	<form action="" method="post"  enctype="multipart/form-data">
  		<div class=uploadBox >
   			<h1>人脸识别</h1>
   			<p><input  type="file"  name="imgFile" /></p>
   			<input  type="button"  value="上传" class="uploadBtn" />
   		</div>
   	</form>
  </body>
</html>
