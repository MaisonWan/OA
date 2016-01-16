
<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-会议室申请</title>
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
  			<img id="headPicture" alt="转到首页" style="top: 0px; left: 20px; width: 140px; height: 120px; position:relative; border: 0px navy none;" src="./images/headHover2.jpg" onmouseover="changePicture();" onmouseout="backPicture();"></img>
  		</a>
  	</div>
	<div id="nav">
		<jsp:include page="../menu.jsp"></jsp:include>
	</div>
	<div id="content" style="font-size:13px;">
		<div style="color: #FFFFFF; padding-top: 6px;">
			<img alt="箭头" src="./images/arrow.gif" style=" position:relative; top: 3px; left: 6px;"/>
			<strong style="margin-left: 9px;">当前位置->会议室申请</strong>
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
  					
  		<form method="post" action="/OA/roomRegist.do" id="roomForm">
	
	
	
		<!-- 下面添加的Form是为了保存当添错数据的时候，不要让数据丢失而干的事情 -->
		<table class="shorterTable" cellspacing="0" width="100%">
			<thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">会议室申请</thead>
			<tr>
				<td class="tableColumeHead">会议名称:</td>
				<td class="generalError">
					<input id="meetingName" style="width: 60%;" name="meetingName" type="text" value="<c:out value="${ form.meetingName }" default=""></c:out>" />*<html:errors property="meetingName"/>
				</td>
				
			</tr>
			<tr>
				<td class="tableColumeHead">会议地点:</td>
				<td  class="generalError">
						<select name="meetingPlace" style="width: 61%;">
		    				<option value="0">-请选择-</option>
		    				<logic:present name="roomList">
								<logic:iterate id="room" name="roomList" type="com.icss.hit.hibernate.vo.Room">
									<option value="<bean:write name="room" property="RId"/>" 
											<c:if test="${ param.id == room.RId }">selected</c:if> <c:if test="${ form.meetingPlace == room.RId }">selected</c:if>>
											<bean:write name="room" property="RName"/>
									</option>
								</logic:iterate>
							</logic:present>
						</select>
						*<html:errors property="meetingPlace"/>
				</td>
				
			</tr>
			<tr>
				<td class="tableColumeHead">会议开始时间:</td>
			  	<td class="generalError"><input id="beginTime" style="width: 60%;" name="beginTime" type="text" onfocus="calendar()" value="<c:out value="${ form.beginTime }" default=""></c:out>" />*<html:errors property="beginTime"/></td>
			</tr>
			<tr>
				<td class="tableColumeHead">会议结束时间:</td>
				<td class="generalError"><input id="endTime" style="width: 60%;" name="endTime" type="text" onfocus="calendar()" value="<c:out value="${ form.endTime }" default=""></c:out>" />*<html:errors property="endTime"/> <html:errors property="timeError"/><html:errors property="timeConflict"/></td>
				
			</tr>
			
			<tr>
				<td class="tableColumeHead">
					会议参与人数:
				</td>
				<td class="generalError">
				<input id="attendSum" style="width: 60%;" name="attendSum" type="text" value="<c:out value="${ form.attendSum }" default=""></c:out>" />*<html:errors property="attendSum"/><html:errors property="Integer"/>		  </td>
				
			</tr>
			<tr>
				<td class="tableColumeHead" style="width: 120px;">
					申请原因及设备要求:		
				</td>
				<td class="generalError">
					<textarea id="meetingContent" name="meetingContent" rows="8" style="width: 60%;"><c:out value="${ form.meetingContent }" default=""></c:out></textarea>*<html:errors property="meetingContent"/>
				</td>
				
			</tr>
			<tr>
				<td colspan="2">
					<button name="submit1" type="submit" class="longButton">申请</button>
				</td>
			</tr>
			
		</table>
	
	
	</form>	
  					
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>












