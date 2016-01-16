
<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-共享名片</title>
	<link href="favicon.ico" rel="shortcut icon" />
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link type="text/css" rel="stylesheet" rev="stylesheet" href="../css/general_style.css"/>
	<link type="text/css" rel="stylesheet" rev="stylesheet" href="../css/dtree.css"/>
	<script type="text/javascript" src="./js/tree.js"></script>
	<script type="text/javascript" src="./js/head.js"></script>
	<script type="text/javascript" src="./js/autosuggest.js"></script>
	<script type="text/javascript">
		window.onload=function()
		{
			var oTextbox = new AutoSuggestControl(document.getElementById("suUser"),new SuggestionProvider(document.getElementById("suUser")));
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
			<strong style="margin-left: 9px;">当前位置->共享名片</strong>
		</div>
		<hr class="line"/>
			
		<div class="left" id="leftPanel">
			<jsp:include page="../menu/cardMenu.jsp"></jsp:include>
		</div>

		<div id="middleButton">
			<a id="middleLink" style="height: 40px; width: 10px; cursor: pointer;" onclick="change();">
				<img style=" background-image:url('./images/middleButtonTiny1.jpg');  margin-top: 140px; border-style:none; width: 10px; height: 40px;" src="../images/middleButtonTiny1.jpg"/>
				<span class="popBox">单击这里打开/折叠左侧树状视图</span>
			</a>
		</div>

		<div class="right" id="rightPanel">
  			
  			<form action="searchShareCardUser.do" method="post">
  			
  			<table class="table" cellpadding="6" cellspacing="0" width="100%">
  			<thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">
  				搜索员工的共享名片夹
  			</thead>
  				<tr> 
  					<td>
  						<input name="type" id="type" type="radio" value="name" 
						<c:if test="${ requestScope.searchType == null }"> checked</c:if> 
						<c:if test="${ requestScope.searchType == 'name' }"> checked</c:if>/>姓名
  					</td>
  					<td>
  						<input name="type" id="type" type="radio" value="id" 
						<c:if test="${ requestScope.searchType == 'id' }"> checked</c:if>/>员工号
  					</td>
  					<td><input name="suUser" id="suUser" type="text" size="20" value="${ requestScope.searchKey }" maxLength="25" autoComplete="off"/></td>
  					<td>
  						性别
						<select name="suSex">
						<option value="0">-请选择-</option>
						<option value="男" 
							<c:if test="${ requestScope.searchSex == '男' }"> selected</c:if>
							>男</option>
							<option value="女" 
							<c:if test="${ requestScope.searchSex == '女' }"> selected</c:if>
						>女</option>
						</select>
  					</td>
  					<td>
  						<select name="suDept">
		    				<option value="0">-请选择-</option>
		    				<logic:present name="deptList">
								<logic:iterate id="dept" name="deptList" type="com.icss.hit.hibernate.vo.SysDept">
									<option value="<bean:write name="dept" property="sdId"/>" 
									<c:if test="${ requestScope.searchDept == dept.sdId }"> selected</c:if>>
									<bean:write name="dept" property="sdName"/></option>
								</logic:iterate>	
							</logic:present>
						</select>
  					</td>
  					<td class="generalError">
  						<input type="submit" value="搜索" class="shortButton" /> *<html:errors property="suUserError"/>
  					</td>
  				</tr>
  			</table>
  			
  			
		</form>
	
		<logic:present name="searchShareCardUser">
			<table class="widerTable" cellspacing="0" width="100%">
			<tr>
				<td class="tableColumeHead">员工编号</td>
				<td class="tableColumeHead">姓名</td>
				<td class="tableColumeHead">性别</td>
				<td class="tableColumeHead">部门</td>
				<td class="tableColumeHead">&nbsp;</td>
			</tr>
			<logic:iterate id="otherInfo" name="searchShareCardUser" type="com.icss.hit.hibernate.vo.SysUser">
				<tr onmouseover="this.style.backgroundColor = '#ffffff';" onmouseout="this.style.backgroundColor = '#fefccc';">
				 <td style="padding: 2px 40px 2px 40px;">${ otherInfo.suUid } </td>
				 <td style="padding: 2px 40px 2px 40px;">${ otherInfo.suUsername}</td>
				 <td style="padding: 2px 40px 2px 40px;">${ otherInfo.suSex }</td>
				 <td style="padding: 2px 40px 2px 40px;">${ otherInfo.sysDept.sdName }</td>
				 <td style="padding: 2px 40px 2px 40px;"><a href="getShareCardById.do?userId=${otherInfo.suId }">查看共享名片</a></td>
				</tr>
			</logic:iterate>
			</table>
			${ requestScope.pageString }
	</logic:present>
  <logic:present name="search">
	  <logic:notPresent name="searchShareCardUser">
			    	<div class="notFound" align="center">
			    		<strong class="notFoundText">
			    			找不到符合条件的共享名片信息
			    		</strong>
			    	</div>
	  </logic:notPresent>
  </logic:present>
  
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>










