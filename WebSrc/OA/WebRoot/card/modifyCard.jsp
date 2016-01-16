
<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-修改名片</title>
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
			<strong style="margin-left: 9px;">当前位置->修改名片</strong>
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
  				
  		<form action="modifyCard.do" method="post"> 
  		<table class="table" cellpadding="6" cellspacing="0" width="100%">
  		<thead class="tableHead" style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">修改${requestScope.ct.cdName}的名片信息</thead>
  			<tr>
  				<td class="tableColumeHead">姓名</td>
  				<td class="generalError"><input type="text" name="cd_name" value="${requestScope.ct.cdName}"/>*<html:errors property="cd_name"/></td>
  				<td class="tableColumeHead">性别</td>
  				<td class="generalError">
  					<select name="cd_sex" style="width:155px;">
						<option value="0" selected>-请选择-</option>
						<option value="男" 
						<c:if test="${ requestScope.ct.cdSex == '男'}"> selected</c:if>
						>男</option>
						<option value="女"
						<c:if test="${ requestScope.ct.cdSex == '女'}"> selected</c:if>
						>女</option>
					</select><html:errors property="cd_sex"/>
  				</td>
  			</tr>
  			<tr>
  				<td class="tableColumeHead">所在公司</td>
  				<td class="generalError"><input type="text" name="cd_company" value="${requestScope.ct.cdCompany}"/> <html:errors property="cd_company"/></td>
  				<td class="tableColumeHead">职位</td>
  				<td class="generalError"><input type="text" name="cd_position" value="${requestScope.ct.cdPosition}"/>	<html:errors property="cd_position"/></td>
  			</tr>
  			<tr>
  				<td class="tableColumeHead">手机号码</td>
  				<td class="generalError"><input type="text" name="cd_cellphone" value="${requestScope.ct.cdCellphone}"/><html:errors property="cd_cellphone"/></td>
  				<td class="tableColumeHead">电子邮件</td>
  				<td class="generalError"><input type="text" name="cd_email" value="${requestScope.ct.cdEmail}"/> <html:errors property="cd_email"/></td>
  			</tr>
  			<tr>
  				<td class="tableColumeHead">传真</td>
  				<td class="generalError"><input type="text" name="cd_fax" value="${requestScope.ct.cdFax}"/>	<html:errors property="cd_fax"/></td>
  				<td class="tableColumeHead">是否共享</td>
  				<td class="generalError">
  				<select name="cd_share" style="width:155px;">
					<option value="1" 
					<c:if test="${ requestScope.ct.cdShare == '1'}"> selected</c:if>
					>共享</option>
					<option value="0"
					<c:if test="${ requestScope.ct.cdShare == '0'}"> selected</c:if>
					>私有</option>
				</select>*<html:errors property="cd_share"/></td>
  			</tr>
  			<tr>
  				<td class="tableColumeHead">办公电话</td>
  				<td class="generalError"><input type="text" name="cd_tel" value="${requestScope.ct.cdTel}" />	<html:errors property="cd_tel"/></td>
  				<td class="tableColumeHead">所属名片夹</td>
  				<td class="generalError">
  					<select name="ct_name" style="width:155px;">
					    <logic:present name="ctnameList">
							<logic:iterate id="cardtype" name="ctnameList" type="com.icss.hit.hibernate.vo.CardType">
								<option value="<bean:write name="cardtype" property="ctId"/>"
								<c:if test="${ requestScope.ct.cardType.ctId == cardtype.ctId}"> selected</c:if>>
								<bean:write name="cardtype" property="ctName"/></option>
							</logic:iterate>
						</logic:present>
					</select>*<html:errors property="ct_name"/>
  				</td>
  			</tr>
  			<tr>
  				<td class="tableColumeHead">修改</td>
  				<td colspan="4" class="generalError">
  					<textarea name="cd_info" style="width: 84%;">${requestScope.ct.cdInfos} </textarea>  <html:errors property="cd_info"/>
  				</td>
  				
  			</tr>
  			<tr>
  				<td colspan="4"> 
  					<input type="submit" value="提交" class="shortButton" />
  					<input type="hidden" value="${ requestScope.ct.cdId }" name="cd_id"/>
  				</td>
  				
  			</tr>

  		</table>
  		
				

		</form>
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>








