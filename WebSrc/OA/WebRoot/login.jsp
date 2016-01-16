<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%
	String path=request.getContextPath();
%>

<html> 
	<head>
	
	<link href="favicon.ico" rel="shortcut icon" />
	<link rel="stylesheet" rev="stylesheet" href="css/general_style.css"/>
	<script type="text/javascript" src="./js/autosuggest.js"></script>
	<script type="text/javascript">
		window.onload=function()
		{
			var oTextbox = new AutoSuggestControl(document.getElementById("username"),new SuggestionProvider(document.getElementById("username")));
		}
	</script>
	<title>OA办公协同系统--登陆页面</title>
	</head>
	<body style="background-color: #999999; background-image: url('./images/nodes.jpg');">
	
	
	<div style=" height: 500px; width: 800px; margin:5px auto; top:20px; position: relative; padding-top:10px; background-image: url('./images/he_3.png'); background-repeat: no-repeat; ">
	
	<div style="width: 620px; height: auto; margin:5px auto; background-color: #FFFFCC; margin-top: 100px; border-bottom-style:groove; border-right-style:groove; z-index: 1; filter:alpha(Opacity=100, FinishOpacity=75, Style=2, StartX=0, StartY=0, FinishX=500, FinishY=100);">
		<form action="Login.do" method="post">
		
		<table style="width: 620px;">
		<thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">欢迎来到OA办公协同系统</thead>
			<tr>
				<td>
					<img alt="登陆图片" src="images/login.jpg"  width="300px" height="170px"/>
				</td>
				<td>
					<table>
						<tr>
							<td align="center">员工号</td>
							<td><input type="text" name="username" id="username" size="20" maxLength="25" value="${ requestScope.form.username }" class="inputBox" autoComplete="off"/></td>
							<td class="generalError" width="125px"><html:errors property="username" /></td>
						</tr>
						<tr>
							<td align="center">密   码</td>
							<td><input type="password" name="password" size="20" maxLength="25" class="inputBox"/></td>
							<td class="generalError"><html:errors property="password" /></td>
						</tr>
						<tr>
							<td align="center">验证码</td>
							<td><input type="text" name="code" size="5" maxLength="4" value="${ requestScope.form.code }" class="inputBox"/></td>
							<td class="generalError"><html:errors property="code" /></td>
						</tr>
						<tr>
							<td></td>
							<td><img src="<%=path%>/GetValidateCode" width="125" height="35" /></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<input type="submit" value="登陆" class="shortButton"/>
								<input type="reset" value="重置" class="shortButton"/>
							</td>
							<td class="generalError">.<html:errors property="login" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		
		</form>
		</div>
		</div>
	</body>
</html>

