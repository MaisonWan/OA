
<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-角色管理</title>
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
  		<a href="../home/home.html" style="width:140px;">
  			<img id="headPicture" alt="转到首页" style="top: 0px; left: 20px; width: 140px; height: 120px; position:relative; border: 0px navy none;" src="./images/headHover2.jpg" onmouseover="changePicture();" onmouseout="backPicture();"></img>
  		</a>
  	</div>
	<div id="nav">
		<jsp:include page="../menu.jsp"></jsp:include>
	</div>
	<div id="content" style="font-size:13px;">
		<div style="color: #FFFFFF; padding-top: 6px;">
			<img alt="箭头" src="./images/arrow.gif" style=" position:relative; top: 3px; left: 6px;"/>
			<strong style="margin-left: 9px;">当前位置->角色管理</strong>
		</div>
		<hr class="line"/>
			
		<div class="left" id="leftPanel">
			<jsp:include page="../menu/sysMenu.jsp"></jsp:include>
		</div>

		<div id="middleButton">
			<a id="middleLink" style="height: 40px; width: 10px; cursor: pointer;" onclick="change();">
				<img style=" background-image:url('./images/middleButtonTiny1.jpg');  margin-top: 140px; border-style:none; width: 10px; height: 40px;" src="../images/middleButtonTiny1.jpg"/>
				<span class="popBox">单击这里打开/折叠左侧树状视图</span>
			</a>
		</div>

		<div class="right" id="rightPanel">
  				
  				<form action="changeRole.do" method="post">
    <logic:present name="userInfoDetails">
    <input type="hidden" name = "dept" id ="dept" value="${ requestScope.dept }" />
    <input type="hidden" name = "page" id ="page" value="${ requestScope.page }" />
    <input type="hidden" name = "suId" id ="suId" value="${ userInfoDetails.suId }" />
    <table class="table" width="100%" cellspacing="0" >
	    <thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">
	    	分配角色
	    </thead>
	     <tr>
	       <td class="tableColumeHead" >员工号</td><td>${ userInfoDetails.suUid }</td>
	     </tr>
	     <tr>
	       <td class="tableColumeHead">姓名</td><td>${ userInfoDetails.suUsername }</td>
	     </tr>
	     <tr>
	       <td class="tableColumeHead">部门</td><td>${ userInfoDetails.sysDept.sdName }</td>
	     </tr>
	     <tr>
	       <td class="tableColumeHead">联系方式</td><td>${ userInfoDetails.suCellphone }</td>
	     </tr>
	     <tr>
	       <td class="tableColumeHead" style="width: 100px;">系统角色</td>
	       <td>
	       	<select name="role" id="role">
				<logic:present name="roleNameList">
					<logic:iterate id="roleName" name="roleNameList" type="com.icss.hit.hibernate.vo.SysRole">
						<c:if test="${roleName.srId != 1}">
							<option value="<bean:write name="roleName" property="srId"/>"
							<c:if test="${ userInfoDetails.sysRole.srId == roleName.srId }"> selected</c:if>>
								<bean:write name="roleName" property="srName"/>
							</option>
						</c:if>
					</logic:iterate>
				</logic:present>
			</select>
	       </td>
	     </tr>
	     <tr>
	     	<td colspan="2">
				<input type="submit" value="提交" class="shortButton" />
	     		<input type="button" value="返回" class="shortButton" onclick="window.location.href='roleArrange.do?dept=${ requestScope.dept }&page=${ requestScope.page }'"/>
	     	</td>
	     </tr>
	    </table>
	   </logic:present>
	   </form>
  				
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>























