<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-个人名片夹</title>
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
			<strong style="margin-left: 9px;">当前位置->个人名片夹</strong>
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
  
  			<form action="searchCard.do" method="post">
  			<table class="table" cellpadding="4" cellspacing="0" width="100%">
  			<thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">
  				查找个人名片
  			</thead>
  				<tr>
  					<td class="tableColumeHead">姓名</td>
  					<td>
  						<input name="userName" type="text" size="20" value="${ requestScope.searchName }" maxLength="25" class="inputBox"/>
  					</td>
  					<td class="tableColumeHead">公司</td>
  					<td>
  						<input name="company" type="text" size="20" value="${ requestScope.searchCompany }" maxLength="25" class="inputBox"/>
  					</td>
  					<td class="tableColumeHead">性别</td>
  					<td>
  						
						<select name="userSex">
						<option value="0">-请选择-</option>
						<option value="1" 
							<c:if test="${ requestScope.searchSex == 1 }"> selected</c:if>
							>男</option>
							<option value="2" 
							<c:if test="${ requestScope.searchSex == 2 }"> selected</c:if>
							>女</option>
						</select>
  					</td>
  					<td class="tableColumeHead">名片夹</td>
  					<td>
  						<select name="cardType">
		    			<option value="0">-请选择-</option>
		    				<logic:present name="cardTypeList">
							<logic:iterate id="type" name="cardTypeList" type="com.icss.hit.hibernate.vo.CardType">
							<option value="<bean:write name="type" property="ctId"/>" 
							<c:if test="${ requestScope.searchCardType == type.ctId }"> selected</c:if>>
								<bean:write name="type" property="ctName"/></option>
							</logic:iterate>
							</logic:present>
						</select>
  					</td>
  					<td>
  						<input type="submit" value="搜索" class="shortButton" /><html:errors property="nameCompError"/>
  					</td>
  				</tr>
  				<tr>
  					<td colspan="9">
  						<a class="link" href="<%=request.getContextPath() %>/newCard.do" style="background-color: #fefccc;">添加新名片</a>
  					</td>
  				</tr>
  			</table>
  			
		</form>
		
		
			
		
		
		
		<logic:present name="searchCardInfo">
			<table class="shorterTable" cellspacing="0" width="100%">
			<thead></thead>
			<tr>
				<td class="tableColumeHead">姓名</td>
				<td class="tableColumeHead">性别</td>
				<td class="tableColumeHead">工作单位</td>
				<td class="tableColumeHead">手机</td>
				<td class="tableColumeHead">所属名片夹</td>
				<td class="tableColumeHead">操作</td>
			</tr>
			<logic:iterate id="CardInfo" name="searchCardInfo" type="com.icss.hit.hibernate.vo.Card">
				<tr onmouseover="this.style.backgroundColor = '#ffffff';" onmouseout="this.style.backgroundColor = '#fefccc';">
				 <td style="padding: 1px 30px 1px 30px;"><c:out value="${ CardInfo.cdName}" default="无"></c:out> </td>
				 <td style="padding: 1px 30px 1px 30px;"><c:out value="${ CardInfo.cdSex }" default="无"></c:out></td>
				 <td style="padding: 1px 30px 1px 30px;"><c:out value="${ CardInfo.cdCompany }" default="无"></c:out></td>
				 <td style="padding: 1px 30px 1px 30px;"><c:out value="${ CardInfo.cdCellphone }" default="无"></c:out></td>
				 <td style="padding: 1px 30px 1px 30px;"><c:out value="${ CardInfo.cardType.ctName }" default="无"></c:out></td>
				 	<td> 
				 	<a href="<%=request.getContextPath() %>/toModifyCard.do?cdId=${CardInfo.cdId }">修改</a>
				 	</td>
				</tr>
			</logic:iterate>
			</table>
			
			${ requestScope.pageString }
		</logic:present>
  <logic:present name="search">
  
  <logic:notPresent name="searchCardInfo">
		    	<div class="notFound" align="center">
		    		<strong class="notFoundText">
		    			找不到符合条件的名片
		    		</strong>
		    	</div>
		    </logic:notPresent>
  </logic:present>
  
  
    	</div>
    	</div>
    	<div id="bottom"></div>
    	
  </body>
</html>









