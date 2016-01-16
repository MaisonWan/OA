


<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-添加新员工</title>
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
			<strong style="margin-left: 9px;">当前位置->添加新员工</strong>
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
  				
  				<form action="confirmAddWorker.do" method="post"> 
  		
  			<table class="shorterTable" cellspacing="0" width="100%">
  			
  			<thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">添加新员工</thead>
  				<tr>
  					<td rowspan="7" style="width:120px; height:200px; padding: 0px 0px;">
  						<img src="./headphoto/<c:out value="${ requestScope.userInfo.suPhotos }" default="noPic.png"></c:out>"/>
  					</td>
  					<td class="tableColumeHead" style="width: 55px; padding: 0px 0px;">编号:</td>
  					<td class="generalError" style="padding: 0px 0px;">
  						<input type="text" name="suUid" id="suUid" style="width: 100px;"/>
  						*<html:errors property="su_uid"/>
  					</td>
  					<td class="tableColumeHead" style="width: 60px; padding: 0px 0px;">办公电话:</td>
  					<td class="generalError" style="padding: 0px 0px;">
  						<input type="text"  name="suTel" id="suTel" style="width: 150px;"/>
  						<html:errors property="su_tel"/>
  					</td>
  					
  				</tr>
  				<tr>
  					<td class="tableColumeHead" style="width: 55px; padding: 0px 0px;">姓名:</td>
  					<td class="generalError" style="padding: 0px 0px;">
  						<input type="text" name="suUsername" id="suUsername" style="width: 100px;"/>
  						*<html:errors property="su_username"/>
  					</td>
  					<td class="tableColumeHead" style="width: 60px; padding: 0px 0px;">移动电话:</td>
  					<td class="generalError" style="padding: 0px 0px;">
  						<input type="text" style="width: 150px;" name="suCellphone" id="suCellphone" />
  						<html:errors property="su_cellphone"/>
  					</td>
  					
  				</tr>
  				<tr>
  					
  					<td class="tableColumeHead" style="width: 55px; padding: 0px 0px;">性别:</td>
  					<td >
  						<input name="suSex" id="suSex" type="radio" value="男" checked />男
						<input name="suSex" id="suSex" type="radio" value="女" />女
					</td>
  					<td class="tableColumeHead" style="width: 60px; padding: 0px 0px;">电子邮件:</td>
  					<td class="generalError" style="padding: 0px 0px;">
  						<input type="text" style="width: 150px;" name="suEmail" id="suEmail" />
  						<html:errors property="su_email"/>
  					</td>
  					
  				</tr>
  				<tr>
  					<td class="tableColumeHead" style="width: 55px; padding: 0px 0px;">密码:</td>
  					<td class="generalError" style="padding: 0px 0px;">
  						<input type="text" name="suPassword" id="suPassword" style="width: 100px;"/>
  						*<html:errors property="su_password"/>
  					</td>
  					<td class="tableColumeHead" rowspan="4" style="width: 60px; padding: 0px 0px;">个人爱好:</td>
  					<td rowspan="4" class="generalError" style="padding: 0px 0px;">

  						<textarea style="width: 150px; height: 100px;" name="suHobby" id="suHobby" ></textarea>
						<html:errors property="su_hobby"/>
  					</td>
  				</tr>
  				<tr>
  					
  					<td class="tableColumeHead" style="width: 55px; padding: 0px 0px;">部门:</td>
  					<td class="generalError" style="padding: 0px 0px;">
  						<select name="suDept" id="suDept" style="width: 110px;">
						    <logic:present name="deptList">
								<logic:iterate id="dept" name="deptList" type="com.icss.hit.hibernate.vo.SysDept">
									<option value="<bean:write name="dept" property="sdId"/>">
										<bean:write name="dept" property="sdName"/>
									</option> 
								</logic:iterate>
							</logic:present>
						</select>
  					</td>
  					
  					
  				</tr>
  				<tr>
  					
  					<td class="tableColumeHead" style="width: 55px; padding: 0px 0px;">职位:</td>
  					<td style="padding: 0px 0px;">
  						<select name="suPos" id="suPos" style="width: 110px;">
						    <logic:present name="posList">
								<logic:iterate id="pos" name="posList" type="com.icss.hit.hibernate.vo.SysPosition">
									<option value="<bean:write name="pos" property="spsId"/>">
										<bean:write name="pos" property="spsName"/>
									</option> 
								</logic:iterate>
							</logic:present>
						</select>
  					</td>
  					
  				</tr>
  				<tr>
  					<td class="tableColumeHead" style="width: 55px; padding: 0px 0px;">系统角色</td>
				       <td style="padding: 0px 0px;">
				       	<select name="suRole" id="suRole" style="width: 110px;">
							<logic:present name="roleNameList">
								<logic:iterate id="roleName" name="roleNameList" type="com.icss.hit.hibernate.vo.SysRole">
									<c:if test="${ roleName.srId != 1}">
									<option value="<bean:write name="roleName" property="srId"/>">
										<bean:write name="roleName" property="srName"/>
									</option> 
									</c:if>
								</logic:iterate>
							</logic:present>
						</select>
				       </td>
  				</tr>
  			<tr>
  				<td class="tableColumeHead" height="70px">个人简介：</td>
  				<td colspan="4" class="generalError" style="padding: 5px auto;">
  					<textarea style="width: 460px; height: 50px;" name="suInfo" id="suInfo"></textarea>
  					<html:errors property="su_info"/>
  				</td>
  			</tr>
  			</table>
   			<div align="center">
   				<input type="submit" value="提交"  class="shortButton"/>
			</div>
		</form>
  				
  				
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>














