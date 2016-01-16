<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-员工信息管理</title>
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
			<strong style="margin-left: 9px;">当前位置->员工信息管理</strong>
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
  				
  				<form action="changeInfo.do" method="post"> 
  			<input type="hidden" name = "dept" id ="dept" value="${ requestScope.dept }" />
    		<input type="hidden" name = "page" id ="page" value="${ requestScope.page }" />
  			<table class="shorterTable" cellspacing="0" width="100%">
  			
  			<thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">修改员工信息</thead>
  				<tr>
  					<td rowspan="6" style="width:150px; height:200px;">
  						<img src="./headphoto/<c:out value="${ requestScope.userInfo.suPhotos }" default="noPic.png"></c:out>"/>
  					</td>
  					<td class="tableColumeHead">编号:</td>
  					<td ><input type="text" name="suUid" id="suUid" style="width: 100px;" value="${ requestScope.userInfo.suUid }" /></td>
  					<td class="tableColumeHead">办公电话:</td>
  					<td >
  						${requestScope.userInfo.suTel}
  					</td>
  					
  				</tr>
  				<tr>
  					<td class="tableColumeHead" >姓名:</td>
  					<td><input type="text" name="suUsername" style="width: 100px;" id="suUsername" value="${ requestScope.userInfo.suUsername }" /></td>
  					<td class="tableColumeHead">移动电话:</td>
  					<td >
  						${ requestScope.userInfo.suCellphone }
  					</td>
  					
  				</tr>
  				<tr>
  					
  					<td class="tableColumeHead" >性别:</td>
  					<td>
  						<input name="suSex" id="suSex" type="radio" value="男"
						<c:if test="${ requestScope.userInfo.suSex == null }"> checked</c:if>
						<c:if test="${ requestScope.userInfo.suSex == '男' }"> checked</c:if>/>男
						<input name="suSex" id="suSex" type="radio" value="女" 
						<c:if test="${ requestScope.userInfo.suSex == '女' }">checked</c:if>/>女
					</td>
  					<td class="tableColumeHead">电子邮件:</td>
  					<td>
  						${ requestScope.userInfo.suEmail }
  					</td>
  					
  				</tr>
  				<tr>
  					<td class="tableColumeHead" >部门:</td>
  					<td>
  						<select name="suDept" id="suDept" style="width: 100px;">
						    <logic:present name="deptList">
								<logic:iterate id="dept" name="deptList" type="com.icss.hit.hibernate.vo.SysDept">
									<option value="<bean:write name="dept" property="sdId"/>" 
										<c:if test="${ requestScope.userInfo.sysDept.sdId == dept.sdId }"> selected</c:if>>
										<bean:write name="dept" property="sdName"/>
									</option> 
								</logic:iterate>
							</logic:present>
						</select>
  					</td>
  					<td class="tableColumeHead" rowspan="2">个人爱好:</td>
  					<td rowspan="2" >
						${ requestScope.userInfo.suHobby }
					</td>
  					
  				</tr>
  				<tr>
  					
  					<td class="tableColumeHead">职位:</td>
  					<td>
  						<select name="suPos" id="suPos" style="width: 100px;">
						    <logic:present name="posList">
								<logic:iterate id="pos" name="posList" type="com.icss.hit.hibernate.vo.SysPosition">
									<option value="<bean:write name="pos" property="spsId"/>" 
										<c:if test="${ requestScope.userInfo.sysPosition.spsId == pos.spsId }"> selected</c:if>>
										<bean:write name="pos" property="spsName"/>
									</option> 
								</logic:iterate>
							</logic:present>
						</select>
  					</td>
  				</tr>
  			<tr>
  				<td class="tableColumeHead" height="70px">个人简介：</td>
  				<td colspan="4"  style="padding: 5px auto;">
  					${ requestScope.userInfo.suInfos }
  				</td>
  				
  			</tr>
  			<tr>
  				<td colspan="4">
  					
	   				<input type="submit" value="提交"  class="shortButton"/>
					<input type="button" value="返回" class="shortButton" onclick="window.location.href='roleArrange.do?dept=${ requestScope.dept }&page=${ requestScope.page }'"/>
					<input type="hidden" name="su_id" id="su_id" value="${ requestScope.userInfo.suId }"/>
					
  				</td>
  				<td>
  					<a href="/OA/addWorker.do" class="link">添加新员工</a>
  				</td>
  			</tr>
  			</table>
   			
		</form>
  				
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>













