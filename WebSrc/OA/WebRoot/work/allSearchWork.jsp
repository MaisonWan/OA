

<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-日程综合搜索</title>
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
			<strong style="margin-left: 9px;">当前位置->日程综合搜索</strong>
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
		
		
  				
		<form action="AllSearchWork.do" method="post">
			 <table class="shorterTable" width="100%" cellspacing="0">
			 <thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">
			 	日程安排综合查找
			 </thead>
			 <tr>
			 
			 	<td>安排人 : </td>
			 	<td>开始时间 :</td>
			 	<td>结束时间 :</td>
			 	<td>是否完成 :</td>
			 	<td>&nbsp;<html:errors property="complete"/></td>
			 	
			 </tr>
			 <tr>
			 <td>
					<input type="text" name="name" size="10" value="${ requestScope.name }"/>
					<html:errors property="name"/>
			</td>
			<td>		
					 <input type="text" name="beginTime" size="15" value="${ requestScope.begin }" onFocus="calendar()"/>
					<html:errors property="beginTime"/>
			</td>
			<td>		
					 <input type="text" name="endTime" size="15" value="${ requestScope.end }" onFocus="calendar()"/>
					<html:errors property="endTime"/>
			</td>
			<td>		
					 
					<select name="complete">
						<option value="2">请选择</option>
						<option value="0"
							<c:if test="${ requestScope.complete == '0' }"> selected</c:if>
						>未完成</option>
						<option value="1"
							<c:if test="${ requestScope.complete == '1' }"> selected</c:if>
						>完成</option>
					</select>
			
					
			</td>
			<td >
					<input type="submit" value="查询" class="shortButton"/>
			</td>
				</tr>
			</table>
		</form>	
		<br/>
	<logic:present name="list">
	<table class="shorterTable" cellspacing="0" width="100%">
		<tr>
			<td class="tableColumeHead">标题</td>
			<td class="tableColumeHead">开始时间</td>
			<td class="tableColumeHead">结束时间</td>
			<td class="tableColumeHead">发起人</td>
			<td class="tableColumeHead">是否完成</td>
		</tr>
		<logic:iterate id="schList" name="list" type="com.icss.hit.hibernate.vo.Schedule">
		<tr onmouseover="this.style.backgroundColor = '#ffffff';" onmouseout="this.style.backgroundColor = '#fefccc';">
			<td>${ schList.schTitle }</td>
			<td>${ schList.schBegintime }</td>
			<td>${ schList.schEndtime }</td>
			<td>${ schList.sysUserBySuIdFrom.suUsername }</td>
			<td><c:if test="${ schList.schComplete == '0'}">未完成</c:if>
			<c:if test="${ schList.schComplete == '1'}">完成</c:if></td>
		</tr>
		</logic:iterate>
	</table>
		${ requestScope.pageString }
	</logic:present>
	
	<logic:notPresent name="list">
		    	<div class="notFound" align="center">
		    		<strong class="notFoundText">
		    			找不到符合条件的日程安排
		    		</strong>
		    	</div>
		    </logic:notPresent>
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>








