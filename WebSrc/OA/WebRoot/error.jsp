<%@ page language="java" isErrorPage="true" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.io.PrintWriter" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	response.setHeader("refresh","3;URL=login.jsp") ;

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"/>
    
    <title>出错页面</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="favicon.ico" rel="shortcut icon" />
	<link rel="stylesheet" rev="stylesheet" href="css/general_style.css"/>

  </head>
  
  <body style="background-image: url('./images/errorBackground.jpg');">
  	<div style="width: 600px; height: 200px; margin: 50px auto;">
  		<table cellspacing="0">
  			<tr>
  				<td>
  					&quot;<img style="width: 200px; height: 200px;" alt="出错" src="./images/errorLogo.png" />
  				</td>
  				<td >
  					<div style="background-color: #fdf9bf; width: 400px; height: 200px; border-style: groove;"> 
  						<p class="passage" style="line-height: 25px; font-size: 18px; font-family: 方正姚体; color: navy; background-color: #faf67c;">
  							<strong>警告：</strong>
  						</p>
  						<p class="passage" style="line-height: 25px; font-size: 18px; font-family: 方正姚体;">
  							尊敬的用户 系统系统出现未知错误，请与系统管理员联系
  							3秒之后将自动跳转
  							如果没有跳转，请点<a href="login.jsp" class="link" style="position: relative; left: 300px;">这里</a>
  						</p>
  					</div>
  				</td>
  			</tr>
  		</table>
  	</div>
  </body>
</html>
