<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-日程安排</title>
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
	<script type="text/javascript">
		function deletesch(id){
			if( confirm('确认要删除?')){
				location.href = "DeleteWorkPlan.do?id=" + id;
			}
			else{
				
			}
		}
	</script>
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
			<strong style="margin-left: 9px;">当前位置->日程安排</strong>
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
		
  				<logic:present name="schList">
    <table class="shorterTable" cellpadding="0" cellspacing="0">
    
	<thead class="tableHead" style="text-align:center; font-size: large;color: black; background-color: #dbdbda; font-family: 方正姚体;">
	<logic:present name="DateOfSearch">
		<logic:iterate id="date" name="DateOfSearch">
		   <bean:write name="date" format="yyyy/MM/dd" />
		</logic:iterate>
	</logic:present>
	每日安排
	</thead>
		<tr align="right">
		</tr>
		<tr align="center"  >
				<td class="tableColumeHead" ><span class="style1">日程安排</span></td>
				<td class="tableColumeHead" ><span class="style1">日期段</span></td>
				<td class="tableColumeHead" ><span class="style1">时间段</span></td>
				<td class="tableColumeHead" ><span class="style1">是否完成</span></td>
				<td class="tableColumeHead" ><span class="style1">被安排人</span></td>
				<td class="tableColumeHead" ><span class="style1">操 作</span></td>
		 </tr>
		 <logic:iterate id="list" name="schList" type="com.icss.hit.hibernate.vo.Schedule">
		 <tr align="center" height="22px" onmouseover="this.style.backgroundColor = '#ffffff';" onmouseout="this.style.backgroundColor = '#fefccc';">
				<td >
					<a href="<%=request.getContextPath() %>/WorkPlanDetail.do?id=${ list.schId }">${ list.schTitle }</a></td>
				<td ><bean:write name="list" property="schBegintime" format="yyyy/MM/dd"/>-
				<bean:write name="list" property="schEndtime" format="yyyy/MM/dd"/></td>
				<td ><bean:write name="list" property="schBegintime" format="HH:mm"/>
				-<bean:write name="list" property="schEndtime" format="HH:mm"/></td>
				<td >
					<c:if test="${ list.schComplete == '0'}">未完成</c:if>
					<c:if test="${ list.schComplete == '1'}">完成</c:if>
				</td>
				<td >
					${ list.sysUserBySuIdTo.suUsername }
				</td>
				<td >
					<c:if test="${ list.schComplete == '0'}">
						<a href="toModifyWorkConfig.do?schId=${ list.schId }">修改</a> / 
						<a href="#" onclick="deletesch(${ list.schId });">删除</a>				
					</c:if>
					<c:if test="${ list.schComplete == '1'}">  /  </c:if>
				</td>
		  </tr>
		  </logic:iterate>
		</table>
    	${ requestScope.page }
    </logic:present>
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>










