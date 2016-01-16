<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-会议室维护</title>

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
			//alert(value);
			document.forms[0].action = "manageAllRooms.do?roomID="+value;
    		document.forms[0].type.value = name;
			document.forms[0].submit();
		}
	</script>
  </head>
  
  <body>
  
  	<div id="head">
  		<a href="<%=request.getContextPath()%>/Home.do" style="width:140px;">
  			r<img id="headPicture" alt="转到首页" style="top: 0px; left: 20px; width: 140px; height: 120px; position:relative; border: 0px navy none;" src="./images/headHover2.jpg" onmouseover="changePicture();" onmouseout="backPicture();"></img>
  		</a>
  	</div>
	<div id="nav">
		<jsp:include page="../menu.jsp"></jsp:include>
	</div>
	<div id="content" style="font-size:13px;">
		<div style="color: #FFFFFF; padding-top: 6px;">
			<img alt="箭头" src="./images/arrow.gif" style=" position:relative; top: 3px; left: 6px;"/>
			<strong style="margin-left: 9px;">当前位置->会议室维护</strong>
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
		
		<logic:present name="roomList">
  				<!-- 表单提交的地方！！！！ -->
  				<form method="post" name="msgform"> 
  				<input id="type" name="type" type="hidden" value=""/>
    			<table class="shorterTable" cellspacing="0" width="100%">
   
	<thead class="tableHead" style="text-align:center; font-size: large;color: black; background-color: #dbdbda; font-family: 方正姚体;">
		会议室管理
	</thead>
		
		<tr >
				<td class="tableColumeHead">编号</td>
				<td class="tableColumeHead" >名称</td>
				<td class="tableColumeHead" >可容纳人数</td>
				<td class="tableColumeHead" >备注</td>
				<td class="tableColumeHead" colspan="2" >操 作</td>
		 </tr>
		 <logic:iterate id="room" name="roomList" type="com.icss.hit.hibernate.vo.Room">
		 <tr onmouseover="this.style.backgroundColor = '#ffffff';" onmouseout="this.style.backgroundColor = '#fefccc';">
		 		<td>
		 			<bean:write name="room" property="RId" />                                  <!-- 会议室ID 的传递 -->
		 			<input id="roomID" name="roomID" type="hidden" value="${ room.RId }" />
				</td>              
				<td>
					${ room.RName }
				</td>   <!-- 会议室名称的传递 -->
				<td>
					${ room.RContain }       <!-- 房间容纳人数 -->
				</td>
				<td>
					<c:out value="${ room.RInfo }" default="无"></c:out>         <!-- 备注 -->
				</td>
				<td>
					<a href="#" onclick="chooseType('alter',${ room.RId })">修改</a>
				</td>
				<td>
					<a href="#" onclick="chooseType('del',${ room.RId })">删除</a>				
				</td>
		  </tr>
		  
		  </logic:iterate>
		  <tr>
		  	<td colspan="6" class="generalError">
		  		<html:errors property="roomError"/>
		  	</td>
		  </tr>
		</table>
		</form>
    	${ requestScope.pageString }
    </logic:present>
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>