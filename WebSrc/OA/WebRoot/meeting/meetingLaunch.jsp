<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>OA办公系统-发起会议</title>
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
    <script type="text/javascript" src="./js/c.js"></script>
    
    <script language="javascript">
    	String.prototype.trim   =   function()
		{
         	//   用正则表达式将前后空格
         	//   用空字符串替代。
        	 return   this.replace(/(^\s*)|(\s*$)/g,"");
		}
    	
    	function MeetingLanuch()
    	{
    		document.forms[0].action = "launchMeeting.do";
   			document.forms[0].submit();
    	}
    	function backForward()
    	{
    		document.forms[0].action = "meetingAttend.do";
   			document.forms[0].submit();
    	}
    </script> 
  </head>
  <body>
  <div id="head">
  		<a href="<%=request.getContextPath()%>/Home.do" style="width:140px;">
  			b<img id="headPicture" alt="转到首页" style="top: 0px; left: 20px; width: 140px; height: 120px; position:relative; border: 0px navy none;" src="./images/headHover2.jpg" onmouseover="changePicture();" onmouseout="backPicture();"></img>
  		</a>
  	</div>
	<div id="nav">
		<jsp:include page="../menu.jsp"></jsp:include>
	</div>
	<div id="content" style="font-size:13px;">
		<div style="color: #FFFFFF; padding-top: 6px;">
			<img alt="箭头" src="./images/arrow.gif" style=" position:relative; top: 3px; left: 6px;"/>
			<strong style="margin-left: 9px;">当前位置->发起会议</strong>
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
  				
  	<form method="post" name="msgform"> 
  	<input id="sum" name="idArray" type="hidden" /><!-- 用于写上去的员工号的真实对应的ID号 -->
  	<html:errors property="UserError"/>
  	<!-- 用于新建留言用的 -->
  	<table width="100%">
  	
  	<tr>
  	<td >
  	
  	<table align="center" cellspacing="0" id="myMessageTable" class="table" style="height: 320px;" width="100%">
		  <thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">
		  	发起会议
		  </thead>
		  
		
		  <tr>
		    <td class="tableColumeHead">会议参与员工号:</td>
		    <td class="generalError" style="padding: 0px 10px 0px 8px;">
		    <div id="address" style="overflow:scroll; width: auto; height: 50px; background-color: white; border:"></div>
		    
		    *<html:errors property="sum"/>
		    </td>
		  </tr>
		  <tr>
		    <td class="tableColumeHead">会议标题:</td>
		    <td class="generalError"><input id="meetingTitle" name="meetingTitle" type="text" style="width: 98%;" value="<c:out value="${ form.meetingTitle }" default=""></c:out>" /><html:errors property="mesTitle"/></td>
		  </tr>
		  <tr>
		    <td class="tableColumeHead">会议室选择:</td>
		    <td class="generalError">
			   <select name="meetingRoom" style="width:200px;">
			    	<option value="0">-请选择-</option>
			    	<logic:present name="roomList">
					<logic:iterate id="room" name="roomList" type="com.icss.hit.hibernate.vo.Room">
						<option value="<bean:write name="room" property="RId"/>" <c:if test="${ form.meetingRoom == room.RId }">selected</c:if>>
							<bean:write name="room" property="RName"/>
						</option>
					</logic:iterate>
					</logic:present>
				</select>
				*<html:errors property="meetingRoom"/>
			</td>
		  </tr>
		  <tr>
		    <td class="tableColumeHead">会议时间:</td>
		    <td class="generalError"><input id="beginTime" name="beginTime" style="width: 120px;" type="text" onFocus="calendar()" value="<c:out value="${ form.beginTime }" default=""></c:out>"/>  -  <input id="endTime" style="width: 120px;" name="endTime" type="text" onFocus="calendar()" value="<c:out value="${ form.endTime }" default=""></c:out>"/>*
		    <html:errors property="time"/><html:errors property="conflict"/><html:errors property="timeAfter"/></td>
		  </tr>
		  <tr>
		    <td class="tableColumeHead">会议内容:</td>
		    <td style="padding: 0px 10px 0px 8px;" class="generalError">
		    	<textarea id="meetingContent" name="meetingContent" rows="4" cols="50" style="height: 100%; width: 99%"><c:out value="${ form.meetingContent }" default=""></c:out></textarea>
		    	*<html:errors property="content"/>
		    </td>
		  </tr>
		  <tr>
		    
		    <td colspan="2">
			   <input class="shortButton" name="submit" type="submit" value="发起会议" onClick="MeetingLanuch()"/> 
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   <input type="submit" name="Return" class="shortButton" value="返回" onClick="backForward()"/>
		    </td>
		    
		  </tr>
	</table>
	
	
	
	</td>
	<td align="left" style="width: 170px; height: 345px;">
	<div style="overflow: scroll; width: 170px; height: 345px;">
		<strong style="color: navy;">
			公司内部通讯录
		</strong>
		<jsp:include page="../menu/addressBook.jsp"></jsp:include>
	</div>
			
			
	</td>
	</tr>
	</table>
  </form>
    		
    		
    	</div>
    	
    	</div>
    	<div id="bottom"></div>
  </body>
</html>
