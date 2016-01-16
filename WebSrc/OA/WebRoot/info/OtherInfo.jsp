<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    <title>OA办公系统-他人信息</title>
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
		function pdf()
		{
			document.forms[0].action = "OtherInfoPDF.do";
			document.forms[0].submit();
		}
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
			<strong style="margin-left: 9px;">当前位置->他人信息</strong>
		</div>
		<hr class="line"/>
			
		<div class="left" id="leftPanel">
			<jsp:include page="../menu/infoMenu.jsp"></jsp:include>
		</div>

		<div id="middleButton">
			<a id="middleLink" style="height: 40px; width: 10px; cursor: pointer;" onclick="change();">
				<img style=" background-image:url('./images/middleButtonTiny1.jpg');  margin-top: 140px; border-style:none; width: 10px; height: 40px;" src="../images/middleButtonTiny1.jpg"/>
				<span class="popBox">单击这里打开/折叠左侧树状视图</span>
			</a>
		</div>

		<div class="right" id="rightPanel">
  			<form action="SearchOtherInfo.do" method="post">
			
			
			<table align="center" cellspacing="0" class="narrowerTable" width="100%">
			<thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">
				查找其他员工信息
			</thead>
				<tr>
					<td style="width: 60px;">
						<input name="type" id="type" type="radio" value="name"
						<c:if test="${ requestScope.searchType == null }"> checked</c:if>
						<c:if test="${ requestScope.searchType == 'name' }"> checked</c:if>/>姓名
					</td>
					<td style="width: 60px;">
						<input name="type" id="type" type="radio" value="id" 
						<c:if test="${ requestScope.searchType == 'id' }">checked</c:if>/>员工号
					</td>
					<td align="center">
						<input name="suUser" id=suUser type="text" size="20" value="${ requestScope.searchKey }" maxLength="25" style="width: 120px;" autoComplete="off"/>
					</td>
					<td>
						性别
						<select name="suSex">
							<option value="0">-请选择-</option>
							<option value="男" 
								<c:if test="${ requestScope.searchSex == '男' }">selected</c:if> >
							男</option>
							<option value="女" 
								<c:if test="${ requestScope.searchSex == '女' }">selected</c:if>>
							女</option>
						</select>
					</td>
					<td >
						部门
						<select name="suDept">
		    				<option value="0">-请选择-</option>
		    				<logic:present name="deptList">
								<logic:iterate id="dept" name="deptList" type="com.icss.hit.hibernate.vo.SysDept">
									<option value="<bean:write name="dept" property="sdId"/>" 
											<c:if test="${ requestScope.searchDept == dept.sdId }">selected</c:if>>
											<bean:write name="dept" property="sdName"/>
									</option>
								</logic:iterate>
							</logic:present>
						</select>
					</td>
					<td align="left" class="generalError" >
						<input type="submit" value="搜索" class="shortButton"/>
						<input type="button" value="导出PDF" class="shortButton" onclick="pdf()"/>
						*<html:errors property="suUserError"/>
					</td>
				</tr>
			</table>
			<div width="100%" style="height: 20px;">
				<logic:present name="pdfName">
					<a class="link" href="<%=request.getContextPath() %>/pdf/${ requestScope.pdfName }">PDF文件导出成功点击下载</a>
				</logic:present>
			</div>
			
			
		</form>
		
		<logic:present name="searchOtherInfo">
			<table class="otherInfoTable" cellspacing="0" width="100%" >
			<tr>
				<td class="tableColumeHead">员工号</td>
				<td class="tableColumeHead">姓名</td>
				<td class="tableColumeHead">性别</td>
				<td class="tableColumeHead">部门</td>
				<td class="tableColumeHead">职位</td>
				<td class="tableColumeHead">系统角色</td>
				<td class="tableColumeHead">办公电话</td>
				<td class="tableColumeHead">手机</td>
				<td class="tableColumeHead">电子邮件</td>
				<td class="tableColumeHead">&nbsp;</td>
			</tr>
			<logic:iterate id="otherInfo" name="searchOtherInfo" type="com.icss.hit.hibernate.vo.SysUser">
				<tr onmouseover="this.style.backgroundColor = '#ffffff';" onmouseout="this.style.backgroundColor = '#fefccc';">
				 <td>${ otherInfo.suUid } </td>
				 <td>${ otherInfo.suUsername}</td>
				 <td>${ otherInfo.suSex }</td>
				 <td>${ otherInfo.sysDept.sdName }</td>
				 <td>${ otherInfo.sysPosition.spsName }</td>
				 <td>${ otherInfo.sysRole.srName }</td>
				 <td><c:out value="${ otherInfo.suTel}" default="无"></c:out></td>
				 <td><c:out value="${ otherInfo.suCellphone}" default="无"></c:out></td>
				 <td><c:out value="${ otherInfo.suEmail}" default="无"></c:out></td>
				 <td><a href="otherUserDetail.do?userId=${otherInfo.suId }">详细</a></td>

				</tr>
			</logic:iterate>
			</table>
			${ requestScope.pageString }
			</logic:present>
			<logic:present name="search">
			    <logic:notPresent name="searchOtherInfo">
			    	<div class="notFound" align="center">
			    		<strong class="notFoundText">
			    			没有符合条件的员工信息
			    		</strong>
			    	</div>
			    </logic:notPresent>
		    </logic:present>
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>














