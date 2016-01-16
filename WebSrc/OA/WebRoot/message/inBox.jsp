<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-我的收件箱</title>
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
			<strong style="margin-left: 9px;">当前位置->收件箱</strong>
		</div>
		<hr class="line"/>
			
		<div class="left" id="leftPanel">
			<jsp:include page="../menu/messageMenu.jsp"></jsp:include>
		</div>

		<div id="middleButton">
			<a id="middleLink" style="height: 40px; width: 10px; cursor: pointer;" onclick="change();">
				<img style=" background-image:url('./images/middleButtonTiny1.jpg');  margin-top: 140px; border-style:none; width: 10px; height: 40px;" src="../images/middleButtonTiny1.jpg"/>
				<span class="popBox">单击这里打开/折叠左侧树状视图</span>
			</a>
		</div>

		<div class="right" id="rightPanel">
  		<logic:present name="inBoxList">				
  		<form action="searchInBox.do" method="post">
	    <table width="100%">
	    	<thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">收件箱</thead>
	    	<tr>
	    		<td>
		    		<select name="searchType" id="searchType">
						<option value="name" 
							<c:if test="${ requestScope.searchType == 'name' }">selected</c:if>>
								发件人姓名
						</option>
						<option value="title" 
							<c:if test="${ requestScope.searchType == 'title' }">selected</c:if>>
								标题
						</option>
					</select>
					<input type="text" name="contents" id="contents" value="${ requestScope.content }" />
					<input type="submit" value="查询" class="shortButton" />
				</td>
	    	</tr>
	    
	    </table>
		    
			
			
		
	</form>
    
			<table class="shorterTable" cellspacing="0" width="100%">
			<tr>
				<td class="tableColumeHead">标题</td>
				<td class="tableColumeHead">发件人</td>
				<td class="tableColumeHead">接收时间</td>
				<td class="tableColumeHead">状态</td>
				<td class="tableColumeHead">回复</td>
				<td class="tableColumeHead">操作</td>
				
			</tr>
			<logic:iterate id="inBox" name="inBoxList" type="com.icss.hit.hibernate.vo.ReceiverInfo">
				<tr onmouseover="this.style.backgroundColor = '#ffffff';" onmouseout="this.style.backgroundColor = '#fefccc';">
				 <td><a href="inBoxDetails.do?riId=${ inBox.riId }${ requestScope.url }" title="详细内容：<c:out value="${ inBox.message.msContent }" default="无"/>">${ inBox.message.msTitle } </a></td>
				 <td>${ inBox.message.sysUser.suUsername }</td>
				 <td>${ inBox.message.msSendtime }</td>
				 <td><c:if test="${ inBox.riRead == '0' }"><a href="inBoxDetails.do?riId=${ inBox.riId }${ requestScope.url }">未读</a></c:if>
				  	 <c:if test="${ inBox.riRead == '1' }">已读</c:if></td>
				 <td><a href="NewMessage.do?receiverId=${ inBox.message.sysUser.suId }&riId=${ inBox.riId }${ requestScope.url }">回复</a></td>
				 <td><a href="deleteInBoxMessage.do?riId=${ inBox.riId }${ requestScope.url }">删除</a></td>
			 </tr>
			</logic:iterate>
			</table>
			${ requestScope.pageString }
	</logic:present>
	<logic:notPresent name="inBoxList">
		<div class="notFound" align="center">
	   		<strong class="notFoundText">
	   			找不到符合条件的消息
	   		</strong>
	   	</div>
	</logic:notPresent>
  						
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>










