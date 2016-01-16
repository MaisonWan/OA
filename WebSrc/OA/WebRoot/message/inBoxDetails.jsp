<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-收件箱</title>
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
			<strong style="margin-left: 9px;">当前位置->消息详情</strong>
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
  					
  		<logic:present name="inBoxDetails">
		    <table class="table" width="100%" cellspacing="0">
		    <thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">消息详情</thead>
			    <tr>
			    	<td class="tableColumeHead" style="width:50px;">发件人</td>
			    	<td>${ inBoxDetails.message.sysUser.suUsername }</td>
			    </tr>
			    <tr>
			    	<td class="tableColumeHead">时间</td>
			    	<td>${ inBoxDetails.message.msSendtime }</td>
			    </tr>
			    <tr>
			    	<td class="tableColumeHead">标题</td>
			    	<td>${ inBoxDetails.message.msTitle }</td>
			    </tr>
			    <tr>
			    	<td class="tableColumeHead">内容</td>
			    	<td style="height:210px;vertical-align:top;">
			    		<div style="width: 100%; height: 210px; overflow: scroll;">
			    			${ inBoxDetails.message.msContent }
			    		</div>
			    		
			    	</td>
			    </tr>
			    <tr>
			    	<td colspan="2">
			    		<a href="${ requestScope.forwardString }">返回</a>
			    	</td>
			    </tr>
		    </table>
	    
    </logic:present>
  	<logic:notPresent name="inBoxDetails">
		<div class="notFound" align="center">
		    		<strong class="notFoundText">
		    			没有详细信息
		    		</strong>
		    	</div>
	
	
	</logic:notPresent>			
  					
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>











