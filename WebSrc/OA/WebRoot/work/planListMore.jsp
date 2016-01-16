<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-待办事项列表</title>
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
			<strong style="margin-left: 9px;">当前位置->待办事项列表</strong>
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
  			<logic:present name="allWorkPlanList">
			<table class="table" width="100%" cellspacing="0">
				<thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">
					待办事项列表
				</thead>
				<tr>
					<td class="tableColumeHead">主题</td>
					<td class="tableColumeHead">内容</td>
					<td class="tableColumeHead">开始时间</td>
					<td class="tableColumeHead">结束时间</td>
					<td class="tableColumeHead">发起人</td>
					<td class="tableColumeHead">是否已读</td>
					<td class="tableColumeHead">操作</td>
					<td class="tableColumeHead">标记为完成</td>
				</tr>
			<logic:iterate id="allWorkPlan" name="allWorkPlanList" type="com.icss.hit.hibernate.vo.Schedule">
				<tr onmouseover="this.style.backgroundColor = '#ffffff';" onmouseout="this.style.backgroundColor = '#fefccc';">
				 <td><a href="readPlan.do?flag=planListMore&schId=${ allWorkPlan.schId }" onclick="this.href+='&page='+document.getElementById('pageNo').value">${ allWorkPlan.schTitle }</a> </td>
				 <td><a href="readPlan.do?flag=planListMore&schId=${ allWorkPlan.schId }" onclick="this.href+='&page='+document.getElementById('pageNo').value">${ allWorkPlan.schContent }</a></td>
				 <td>${ allWorkPlan.schBegintime }</td>
				 <td>${ allWorkPlan.schEndtime }</td>
				 <td><c:if test="${ allWorkPlan.sysUserBySuIdTo.suId == allWorkPlan.sysUserBySuIdFrom.suId }">本人</c:if>
				  	 <c:if test="${ allWorkPlan.sysUserBySuIdTo.suId != allWorkPlan.sysUserBySuIdFrom.suId }">${ allWorkPlan.sysUserBySuIdFrom.suUsername }</c:if>
				 </td>
				 <td><c:if test="${ allWorkPlan.schRead == '0' }"><a href="readPlan.do?flag=planListMore&schId=${ allWorkPlan.schId }" onclick="this.href+='&page='+document.getElementById('pageNo').value">未读</a></c:if>
				  	 <c:if test="${ allWorkPlan.schRead == '1' }">已读</c:if>
				 </td>
				 <td><c:if test="${ allWorkPlan.sysUserBySuIdTo.suId == allWorkPlan.sysUserBySuIdFrom.suId }"><a href="deletePlan.do?flag=planListMore&schId=${ allWorkPlan.schId }" onclick="this.href+='&page='+document.getElementById('pageNo').value">删除</a></c:if>
				  	 <c:if test="${ allWorkPlan.sysUserBySuIdTo.suId != allWorkPlan.sysUserBySuIdFrom.suId }">----</c:if>
				 </td>
				 <td><a href="completePlan.do?flag=planListMore&schId=${ allWorkPlan.schId }" onclick="this.href+='&page='+document.getElementById('pageNo').value">完成</a></td>
				</tr>
			</logic:iterate>
			</table>
			${ requestScope.pageString }
	</logic:present>
	<logic:notPresent name="allWorkPlanList">
				<div class="notFound" align="center">
		    		<strong class="notFoundText">
		    			没有待办事项
		    		</strong>
		    	</div>
			</logic:notPresent>
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>

















