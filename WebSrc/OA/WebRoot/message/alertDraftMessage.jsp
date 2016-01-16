<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>OA办公系统-修改草稿信息</title>
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
    <script type="text/javascript" src="./js/html2xhtml.js"></script>
	<script type="text/javascript" src="./js/richtext.js"></script>
    
    <script language="javascript">
    	String.prototype.trim   =   function()
		{
         	//   用正则表达式将前后空格
         	//   用空字符串替代。
        	 return   this.replace(/(^\s*)|(\s*$)/g,"");
		}
    	
    	function MessageSendOrSave(name)
    	{
    		document.forms[0].action = "messageAfterAlter.do";
    		document.forms[0].type.value = name;
			document.forms[0].submit();
    	}
    	function inicial()
    	{
    		document.getElementById("sum").value = "";
    	}
    	function submitForm() 
		{
			updateRTEs();
			//alert("您编辑的文本内容为: " + document.forms[0].rte1.value);
			document.forms[0].messagecontent.value = document.forms[0].rte1.value;
			alert(document.forms[0].messagecontent.value);
			return true;
		}
		initRTE("images/", "./css/", "", true);
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
	<div id="content" style="font-size:13px; height: 595px;">
		<div style="color: #FFFFFF; padding-top: 6px;">
			<img alt="箭头" src="./images/arrow.gif" style=" position:relative; top: 3px; left: 6px;"/>
			<strong style="margin-left: 9px;">当前位置->修改草稿信息</strong>
		</div>
		<hr class="line"/>
			
		<div class="left" id="leftPanel" style="height:550px;">
			<jsp:include page="../menu/messageMenu.jsp"></jsp:include>
		</div>

		<div id="middleButton" style="height: 550px;">
			<a id="middleLink" style="height: 40px; width: 10px; cursor: pointer;" onclick="change();">
				<img style=" background-image:url('./images/middleButtonTiny1.jpg');  margin-top: 240px; border-style:none; width: 10px; height: 40px;" src="../images/middleButtonTiny1.jpg"/>
				<span class="popBox">单击这里打开/折叠左侧树状视图</span>
			</a>
		</div>

		<div class="right" id="rightPanel">
  				
  	<form method="post" name="msgform" onsubmit="return submitForm();"> 

  	<input id="sum" name="idArray" type="hidden" value="${ requestScope.userIds }" /><!-- 用于写上去的员工号的真实对应的ID号 -->
  	<input id="mesID" name="mesID" type="hidden" value="${ requestScope.mesID }" />
  	<!-- 用于修改草稿信息用的 -->
  	<table width="100%">
  	
  	<tr>
  	<td style="width: 80%;">
  	
  	<table align="center" cellspacing="0" id="myMessageTable" class="table" style="height: auto;" width="100%">
		  <thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">
		  	修改草稿信息
		  </thead>
		  
		
		  <tr>
		    <td class="tableColumeHead" width="50px">收件人员工号:</td>
		    <td class="generalError" style="padding: 0px 10px 0px 8px;">
		    <div id="address" style="overflow:scroll; width: auto; height: 50px; background-color: white; border:">${ requestScope.userNames}</div>
		    
		    *<html:errors property="sum"/>
		    </td>
		  </tr>
		  <tr>
		    <td class="tableColumeHead" width="50px">标题:</td>
		    <td><input id="messageTitle" name="messageTitle" type="text" style=" width: 500px; " value="${ requestScope.title }"/><html:errors property="mesTitle"/></td>
		  </tr>
		  <tr>
		    
		    <td colspan="2" style="width: auto; border-style: none; padding:2px; height: auto;" align="center">
			    <div id="suibian" style="display:none;">${ requestScope.content }</div>
			    <script language="JavaScript" type="text/javascript">
			    	con = document.getElementById("suibian").innerHTML;
					writeRichText('rte1', con, 550, 280, true, false);
				</script>
			    <input type="hidden" name="messagecontent" value=""/>
			    <input type="hidden" name="type" value=""/>
		    </td>
		  </tr>
		  <tr>
		    
		    <td colspan="2">
			   <input class="shortButton" name="submit" type="submit" value="发送" onclick="MessageSendOrSave('send')"/> 
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   <input type="submit" name="Submit" class="shortButton" value="保存草稿" onclick="MessageSendOrSave('save')"/>
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   <input type="submit" name="Submit" class="shortButton" value="返回" onclick="location.href='draftBox.do'"/>
		    </td>
		    
		  </tr>
	</table>
	
	
	
	</td>
	<td align="left" style="width: 20%; height: 550px;">
	<div style="overflow: scroll; width: auto; height: 550px; background-color: #bfbfbf;">
		<strong style="color: navy;">
			内部通讯录
		</strong>
		<jsp:include page="../menu/addressBook.jsp"></jsp:include>
	</div>
	
	</td>
	</tr>
	</table>
  </form>
    		
    		
    	</div>
    	
    	</div>
    	<div id="bottom"></div>
  </body>
</html>
