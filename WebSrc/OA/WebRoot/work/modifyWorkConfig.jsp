<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>OA办公系统-修改日程安排</title>
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
			<strong style="margin-left: 9px;">当前位置->修改日程安排</strong>
		</div>
		<hr class="line"/>
			
		<div class="left" id="leftPanel">
			<jsp:include page="../menu/workMenu.jsp"></jsp:include>
		</div>

		<div id="middleButton">
			<a id="middleLink" style="height: 40px; width: 10px; cursor: pointer;" onclick="change();">
				<img style=" background-image:url('./images/middleButtonTiny1.jpg');  margin-top: 140px; border-style:none; width: 10px; height: 40px;" src="../images/middleButtonTiny1.jpg"/>
				<span class="popBox">单击这里打开/折叠左侧树状视图</span>
			</a>
		</div>

		<div class="right" id="rightPanel">
  		<form action="modifyWorkConfig.do" method="post"> 
  		
  		<table class="table" width="100%" cellspacing="0">
  		<thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">修改日程安排</thead>
  		<tr>
  			<td class="tableColumeHead" >日程安排名称：</td>
  			<td class="generalError">
  				<input type="text" style="width: 60%;" name="sch_title" value="${ requestScope.schedule.schTitle }" />*<html:errors property="sch_title"/>
  			</td>
  		</tr>
  		<tr>
  			<td class="tableColumeHead">开始时间：</td>
  			<td class="generalError">
  				<input type="text" style="width: 60%;" name="sch_begintime" value="<fmt:formatDate value="${requestScope.schedule.schBegintime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>" onfocus="calendar()" />*<html:errors property="sch_begintime"/>
  			</td>
  		</tr>
  		<tr>
  			<td class="tableColumeHead">结束时间：</td>
  			<td class="generalError">
  				<input type="text" name="sch_endtime" style="width: 60%;" value="<fmt:formatDate value="${requestScope.schedule.schEndtime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>" onfocus="calendar()" />*<html:errors property="sch_endtime"/>
  			</td>
  		</tr>
  		<tr>
  			<td class="tableColumeHead" style="width: 100px;">安排内容：</td>
  			<td class="generalError">
  				<textarea rows="8" style="width: 60%;" name="sch_content" >${ requestScope.schedule.schContent }</textarea>	*<html:errors property="sch_content"/>
  			</td>
  		</tr>
  		<tr>
  			<td class="tableColumeHead">被安排人：</td>
  			<td>${requestScope.suTo.suUsername}</td>
  		</tr>
  		<tr>
  			<td colspan="2">
	  			<input type="submit" value="提交" class="shortButton" />
				<input type="hidden" name="sch_id" value="${ requestScope.schedule.schId}"/>
			</td>
  			
  		</tr>
			
		</table>

		</form>
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>



