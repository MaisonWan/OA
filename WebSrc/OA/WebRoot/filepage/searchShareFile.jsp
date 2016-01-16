
<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-共享文件夹</title>
    	<link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>
	<link href="favicon.ico" rel="icon" type="image/x-icon"/>
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
			<strong style="margin-left: 9px;">当前位置->共享文件夹</strong>
		</div>
		<hr class="line"/>
			
		<div class="left" id="leftPanel">
			<jsp:include page="../menu/fileMenu.jsp"/>
		</div>

		<div id="middleButton">
			<a id="middleLink" style="height: 40px; width: 10px; cursor: pointer;" onclick="change();">
				<img style=" background-image:url('./images/middleButtonTiny1.jpg');  margin-top: 140px; border-style:none; width: 10px; height: 40px;" src="../images/middleButtonTiny1.jpg"/>
				<span class="popBox">单击这里打开/折叠左侧树状视图</span>
			</a>
		</div>

		<div class="right" id="rightPanel">
  				
  		<form action="SearchShareFile.do" method="post">
  		<table width="100%" class="table" cellspacing="0">
  		<thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">查找共享文件</thead>
  			<tr>
  				<td class="tableColumeHead">
  					<input type="radio" name="type" value="id" 
					<c:if test="${ requestScope.type == 'id' }"> checked</c:if>/>员工号
					<input type="radio" name="type" value="name" 
					<c:if test="${ requestScope.type == null }"> checked</c:if>
					<c:if test="${ requestScope.type == 'name' }"> checked</c:if>/>姓名
  				</td>
  				<td>
  					<input type="text" name="key" value="${ requestScope.key }"/><html:errors property="key"/>
  				</td>
  				<td class="tableColumeHead">
  					文件名:
  				</td>
  				<td>
  					<input type="text" name="fileName" value="${ requestScope.filename }"/>
					<html:errors property="fileName"/><html:errors property="type"/>
  				</td>
  				<td>
  					<input type="submit" value="搜索" class="shortButton"/>
  				</td>
  			</tr>
  		</table>
			
			
			
			
		</form>
		
		<logic:present name="fileList">
		<table class="shorterTable" cellspacing="0" width="100%">
		<thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">共享文件</thead>
			<tr>
				<td class="tableColumeHead">文件名</td>
				<td class="tableColumeHead">所属文件夹</td>
				<td class="tableColumeHead">所有人</td>
				<td class="tableColumeHead">上传日期</td>
				<td class="tableColumeHead">下载</td>
			</tr>
		<logic:iterate id="list" name="fileList" type="com.icss.hit.hibernate.vo.Files">
			<tr onmouseover="this.style.backgroundColor = '#ffffff';" onmouseout="this.style.backgroundColor = '#fefccc';">
				<td>${ list.FName }</td>
				<td>${ list.fileFolder.ffName }</td>
				<td>${ list.fileFolder.sysUser.suUsername }</td>
				<td>${ list.FTime }</td>
				<td><a href="DownLoad?id=${list.FId }">下载</a></td>
			</tr>	
		</logic:iterate>
		</table>
		</logic:present>
		<logic:present name="noShare">
		<logic:notPresent name="fileList">
		    	<div class="notFound" align="center">
		    		<strong class="notFoundText">
		    			找不到符合条件的共享文件
		    		</strong>
		    	</div>
		</logic:notPresent>
		</logic:present>
  				
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>












