<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-草稿箱</title>

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
	
	<script language="javascript">
		function chooseType(name,value)
		{
			alert(value);
			document.forms[0].action = "draftMessage.do?mesID="+value;
    		document.forms[0].type.value = name;
			document.forms[0].submit();
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
			<strong style="margin-left: 9px;">当前位置->草稿箱</strong>
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
		
  				<logic:present name="draftMessage">
  				<!-- 表单提交的地方！！！！ -->
  				<form method="post" name="msgform"> 
  				<input id="type" name="type" type="hidden" value=""/>
    			<table class="shorterTable" cellspacing="0" width="100%">
   
	<thead class="tableHead" style="text-align:center; font-size: large;color: black; background-color: #dbdbda; font-family: 方正姚体;">
	草稿箱
	</thead>
		<tr align="right">
		</tr>
		<tr align="center"  >
				<td class="tableColumeHead">&nbsp;</td>
				<td class="tableColumeHead" >标题</td>
				<td class="tableColumeHead" >收件人</td>
				<td class="tableColumeHead" >时间</td>
				<td class="tableColumeHead" >操 作</td>
		 </tr>
		 <logic:iterate id="message" name="draftMessage" type="com.icss.hit.hibernate.vo.MessageReceivers">
		 <tr align="center" onmouseover="this.style.backgroundColor = '#ffffff';" onmouseout="this.style.backgroundColor = '#fefccc';">
		 		<td><input id="mesId" name="mesId" type="checkbox" value="${ message.msId }"/></td>              <!-- 信息ID 的传递 -->
				<td>
					<a href="#" onclick="chooseType('alert',${ message.msId })" title="详细内容：<c:out value="${message.msContent }" default="无"/>">
					<c:out value="${message.msTitle }" default="无"/></a></td>   <!-- 信息头部的传递 -->
				<td>
					<c:out value="${ message.receiverInfos[0] }" default="无"/>
				</td>
				<td>
					<bean:write name="message" property="msSendtime" format="yyyy/MM/dd  HH:mm:ss"/>          <!-- 信息时间 的传递 -->
				</td>
				<td>
						<a href="#" onclick="chooseType('del',${ message.msId })">删除</a>				
				</td>
		  </tr>
		  
		  </logic:iterate>
		</table>
		</form>
    	${ requestScope.pageString }
	    </logic:present>
	    <logic:notPresent name="draftMessage">
		   	<div class="notFound" align="center">
		   		<strong class="notFoundText">
		   			没有符合条件的信息
		   		</strong>
		   	</div>
		</logic:notPresent>
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>