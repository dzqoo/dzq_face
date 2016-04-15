<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>人脸特征识别</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="./css/style.css">
     <script type="text/javascript" src="js/jquery.min.js"></script>
     <script>
     	$(function(){
     		setTime("loadResult",1000);//1s
     	});
     	function loadResult(){
     		var imgPath = $("#imgHeader").attr("src");
     		$.ajax({
     			type:"post",
     			url:"HandleFace",
     			data:{"imagePath":imgPath},
     			success:function(data){
     				var result = '<div class = "result">'+data+'</div>';
     				$("#contentBox").append(result); 	
     			}
     		});
     	}
     </script>
  </head>
  
  <body>
		<div class="result_box">
				<div class="image_box">
						<h1>人脸识别</h1>
						<img id="imgHeader" width="400px" height="400px" src= " ${requestScope.imagePath} " />
						<div class="loading  animateLoading">正在识别。。。</div>
				</div>
				<div id="contentBox">
				</div>
		</div>
  </body>
</html>
