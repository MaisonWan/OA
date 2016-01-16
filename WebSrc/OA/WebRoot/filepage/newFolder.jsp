<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<link rel="stylesheet" rev="stylesheet" href="../css/general_style.css"/>
<title>添加文件夹</title>
<script type="text/javascript">
function closeWindow(){
	var fname = document.myform.filename.value;
	if( fname == "" ){
		alert('文件名不能为空');
	}
	else if( fname.length > 255 ){
		alert('文件名过长');
	}
	else{
		window.returnValue = fname;
		window.close();
	}
}

</script>
</head>
<body>
	<div style="margin: 0px auto; background-color: #fbf36d; width: 300px; height: 100px;" >
	<form method="post" name="myform">
		<table> 
		
			<tr style="font-size:medium; font-family:方正姚体;">
				<td colspan="2">请输入您的文件件名称</td>
			</tr>
			
			<tr> 
				<td><input name="filename" type="text"/></td>
				<td align="center">		
					<input name="done" type="button" class="shortButton" value="确认" onclick="closeWindow();"/>
				</td>
			</tr>
		</table></form>
	</div>
</body>
</html>

