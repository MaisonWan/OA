<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-会议室预约</title>
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
	<script type="text/javascript" src="./js/c.js"></script>
  </head>
  
  <body>
  
  	<div id="head">
  		<a href="<%=request.getContextPath()%>/Home.do" style="width:140px;">
  			f<img id="headPicture" alt="转到首页" style="top: 0px; left: 20px; width: 140px; height: 120px; position:relative; border: 0px navy none;" src="./images/headHover2.jpg" onmouseover="changePicture();" onmouseout="backPicture();"></img>
  		</a>
  	</div>
	<div id="nav">
		<jsp:include page="../menu.jsp"></jsp:include>
	</div>
	<div id="content" style="font-size:13px;">
		<div style="color: #FFFFFF; padding-top: 6px;">
			<img alt="箭头" src="./images/arrow.gif" style=" position:relative; top: 3px; left: 6px;"/>
			<strong style="margin-left: 9px;">当前位置->可用会议室</strong>
		</div>
		<hr class="line"/>
			
		<div class="left" id="leftPanel">
			<jsp:include page="../menu/meetingMenu.jsp"></jsp:include>
		</div>

		<div id="middleButton">
			<a id="middleLink" style="height: 40px; width: 10px; cursor: pointer;" onclick="change();">
				<img style=" background-image:url('./images/middleButtonTiny1.jpg');  margin-top: 140px; border-style:none; width: 10px; height: 40px;" src="../images/middleButtonTiny1.jpg"/>
				<span class="popBox">单击这里打开/折叠左侧树状视图</span>
			</a>
		</div>

		<div class="right" id="rightPanel">
  					
  	<form action="searchAvailableRoom.do" method="post">
  	<table width="100%" class="shorterTable" cellspacing="0">
  		<tr>
  			<td class="tableColumeHead">
  				会议室名称:
  			</td>
  			<td class="tableColumeHead">
  				容纳人数:
  			</td>
  			<td class="tableColumeHead">
  				起始时间:
  			</td>
  			<td class="tableColumeHead">
  				结束时间:
  			</td>
  			<td class="tableColumeHead">
  				&nbsp;
  			</td>
  		</tr>
  		<tr cellspacing="0">
  			<td>
  				<input type="text" name="name" value="${ requestScope.searchName }" style="width:100%;"/>
  			</td>
  			<td>
  				<input type="text" name="containNum" 
  				value="<c:if test="${ requestScope.searchContainNum != -1}">${requestScope.searchContainNum}</c:if>" style="width:100%;"/>
  			</td>
  			<td>
  				<input type="text" name="beginTime" value="${ requestScope.searchBeginTime }" style="width:100%;" onfocus="calendar()"/>
  			</td>
  			<td>
  				<input type="text" name="endTime" value="${ requestScope.searchEndTime }" style="width:100%;" onfocus="calendar()"/>
  			</td>
  			<td>
  				<input type="submit" value="查询" class="shortButton" />
  			</td>
  		</tr>
  	</table>

    </form>
	    <logic:present name="availableRoomList">
				<table class="shorterTable"  cellspacing="0" width="100%">

				<tr>
					<td class="tableColumeHead">地点</td>
					<td class="tableColumeHead">可容纳人数</td>
					<td class="tableColumeHead">备注</td>
					<td class="tableColumeHead">操作</td> 
				</tr>
				<logic:iterate id="availableRoom" name="availableRoomList" type="com.icss.hit.hibernate.vo.Room">
					<tr onmouseover="this.style.backgroundColor = '#ffffff';" onmouseout="this.style.backgroundColor = '#fefccc';">
					 <td>${ availableRoom.RName } </td>
					 <td>${ availableRoom.RContain}</td>
					 <td><c:out value="${ availableRoom.RInfo }" default="无" ></c:out></td>
					 <td><a href="roomRedirect.do?id=${ availableRoom.RId }">预约</a></td>
					</tr>
				</logic:iterate>
				</table>
				${ requestScope.pageString }
		</logic:present>
  		
  		<logic:present name="SeachNull">
			<div class="notFound" align="center">
			    <strong class="notFoundText">
			    	查找为空！！没有数据！！
			    </strong>
			</div>
		</logic:present>			
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>









