<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
  	<title>OA办公系统-个人信息</title>
    <link href="favicon.ico" rel="shortcut icon" />
    
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
			<strong style="margin-left: 9px;">当前位置->个人信息</strong>
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
  			<br/>
  			<table class="table" cellpadding="6" cellspacing="0"  style="height:200px; " width="100%">
			<thead class="tableHead" style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;" align="center"> ${ requestScope.userInfo.suUsername }的个人信息</thead>
		    <tr>
		      <td rowspan="6" style="width:150px; height:200px; ">
			  	<img src="<%=request.getContextPath()%>/headphoto/<c:out value="${ requestScope.userInfo.suPhotos }" default="noPic.png"></c:out>"/>
			  </td>
		      <td class="tableColumeHead">员工号</td>
		      <td colspan="3" style="font-size: large; text-align: center;">${ requestScope.userInfo.suUid }</td>
		      
		    </tr>
		    <tr>
		      <td class="tableColumeHead">姓名</td>
		      <td align="center">${ requestScope.userInfo.suUsername } </td>
		      <td class="tableColumeHead">性别</td>
		      <td align="center">${requestScope.userInfo.suSex} </td>
		    </tr>
		    <tr>
		      <td class="tableColumeHead">所属部门 </td>
		      <td align="center">${ requestScope.userInfo.sysDept.sdName } </td>
		      <td class="tableColumeHead">职位</td>
		      <td align="center">${requestScope.userInfo.sysPosition.spsName } </td>
		    </tr>
		    <tr>
		      <td class="tableColumeHead">办公电话</td>
		      <td align="center">${requestScope.userInfo.suTel} </td>
		      <td class="tableColumeHead">移动电话</td>
		      <td align="center">${requestScope.userInfo.suCellphone } </td>
		    </tr>
		    <tr>
		      <td class="tableColumeHead">系统角色</td>
		      <td align="center">${requestScope.userInfo.sysRole.srName } </td>
		      <td class="tableColumeHead">电子邮件</td>
		      <td align="center">${requestScope.userInfo.suEmail } </td>
		    </tr>
		    <tr>
		      <td class="tableColumeHead">个人爱好</td>
		      <td colspan="4" align="center">${requestScope.userInfo.suHobby } </td>
		    </tr>
		    
			<tr style="height:40px; text-align:left; overflow:hidden">
				<td colspan="5" class="tableColumeHead" align="center">个人简介：${ requestScope.userInfo.suInfos } </td>
			</tr>
		  </table>
		 
		  	<div align="center" style="width: 400px; margin: 0px auto; margin-top: 5px;">
				<input type="button" value="修 改" class="shortButton" onclick="javascript:location.href='userInfoUpdateAction.do'" align="left"/> 
  				<a href="./otherUserDetail.do?userId=2" align="right" style="font-size: 15px; margin: -6px 0px 6px 10px;" class="link">他人资料</a>
    		</div>
    		</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>

  
  
  
