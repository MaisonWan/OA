<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-个人信息修改</title>
    <link href="favicon.ico" rel="shortcut icon" />
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link type="text/css" rel="stylesheet" rev="stylesheet" href="../css/general_style.css"/>
	<link type="text/css" rel="stylesheet" rev="stylesheet" href="../css/dtree.css"/>
	<script  type="text/javascript" src="./js/menu.js"></script>
	<script type="text/javascript" src="./js/tree.js"></script>
	<script type="text/javascript" src="./js/head.js"></script>
  </head>
  
  <body>
  
  	<div id="head">
  		<a href="<%=request.getContextPath()%>/Home.do" style="width:140px;">
  			<img id="headPicture" alt="转到首页" style="top: 0px; left: 20px; width: 140px; height: 120px; position:relative; border: 0px navy none;" src="./images/headHover2.jpg" onmouseover="changePicture();" onmouseout="backPicture();"></img>
  		</a>
  	</div>
	<div id="nav">
		<jsp:include page="../menu.jsp"></jsp:include>
	</div>
	<div id="content" style="font-size:13px;">
		<div style="color: #FFFFFF; padding-top: 6px;">
			<img alt="箭头" src="./images/arrow.gif" style=" position:relative; top: 3px; left: 6px;"/>
			<strong style="margin-left: 9px;">当前位置->个人信息修改</strong>
		</div>
		<hr class="line"/>
			
		<div class="left" id="leftPanel">
			<jsp:include page="../menu/infoMenu.jsp"></jsp:include>
		</div>

		<div id="middleButton">
			<a id="middleLink" style="height: 40px; width: 10px; cursor: pointer;" onclick="change();">
				<img style=" background-image:url('./images/middleButtonTiny1.jpg');  margin-top: 140px; border-style:none; width: 10px; height: 40px;" src="../images/middleButtonTiny1.jpg"/>
				<span class="popBox">单击这里打开/折叠左侧树状视图</span>
			</a>
		</div>

		<div class="right" id="rightPanel">
  			<form action="userInfoUpdated.do" method="post"> 
  		
  			<table class="shorterTable" cellspacing="0" width="100%">
  			
  			
  				<tr>
  					<td rowspan="6" style="width:150px; height:200px;">
  						<img src="./headphoto/<c:out value="${ requestScope.userInfo.suPhotos }" default="noPic.png"></c:out>"/>
  					</td>
  					<td class="tableColumeHead">编号:</td>
  					<td>${ requestScope.userInfo.suUid }</td>
  					<td class="tableColumeHead">办公电话:</td>
  					<td class="generalError">
  						<input type="text" style="width: 150px;" name="su_tel" value="${requestScope.userInfo.suTel}"/>
  						<html:errors property="su_tel"/>
  					</td>
  					
  				</tr>
  				<tr>
  					<td class="tableColumeHead">姓名:</td>
  					<td>${ requestScope.userInfo.suUsername }</td>
  					<td class="tableColumeHead">移动电话:</td>
  					<td class="generalError">
  						<input type="text" style="width: 150px;" name="su_cellphone" value="${ requestScope.userInfo.suCellphone }"/>
  						<html:errors property="su_cellphone"/>
  					</td>
  					
  				</tr>
  				<tr>
  					
  					<td class="tableColumeHead">性别:</td>
  					<td>${requestScope.userInfo.suSex}</td>
  					<td class="tableColumeHead">电子邮件:</td>
  					<td class="generalError">
  						<input type="text" style="width: 150px;" name="su_email" value="${ requestScope.userInfo.suEmail }"/>
  						<html:errors property="su_email"/>
  					</td>
  					
  				</tr>
  				<tr>
  					<td class="tableColumeHead">部门:</td>
  					<td>${ requestScope.userInfo.sysDept.sdName }</td>
  					<td class="tableColumeHead" rowspan="3">个人爱好:</td>
  					<td rowspan="3" class="generalError">

  						<textarea style="width: 150px; height: 100px;" name="su_hobby" >${ requestScope.userInfo.suHobby }</textarea>
						<html:errors property="su_hobby"/>
  						

  					</td>
  					
  				</tr>
  				<tr>
  					
  					<td class="tableColumeHead">职位:</td>
  					<td>${requestScope.userInfo.sysPosition.spsName }</td>
  					
  				</tr>
  				<tr>
  					<td class="tableColumeHead">系统角色:</td>
  					<td>${requestScope.userInfo.sysRole.srName }</td>
  					
  				</tr>
  			<tr>
  				<td class="tableColumeHead" height="70px">个人简介：</td>
  				<td colspan="4" class="generalError" style="padding: 5px auto;">
  					<textarea style="width: 442px; height: 50px;" name="su_info" >${ requestScope.userInfo.suInfos }</textarea>
  					<html:errors property="su_info"/>
  				</td>
  				
  			</tr>
  			</table>
   			<div align="center">
   				<a href="../OA/Password.do" class="link">修改密码</a>
				
				<input type="submit" value="提交"  class="shortButton"/>
				<input type="hidden" name="su_id" value="${ requestScope.userInfo.suId }"/>
   			</div>
		</form>
    	</div>
    </div>
    <div id="bottom"></div>
  </body>
</html>




















