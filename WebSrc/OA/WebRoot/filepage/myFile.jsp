<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-个人文件夹</title>
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
	<script type="text/javascript">
		function openWindow(){
			var name = window.showModalDialog( "./filepage/newFolder.jsp","","dialogWidth=300px; dialogHeight=100px;");
			if( name != null ){
				document.myform.filename.value = name;
				document.myform.action = "AddNewFolder.do";
				document.myform.submit();
			}
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
			<strong style="margin-left: 9px;">当前位置->我的文件夹</strong>
		</div>
		<hr class="line"/>
			
		<div class="left" id="leftPanel">
			<jsp:include page="../menu/fileMenu.jsp"/>
		</div>

		<div id="middleButton">
			<a id="middleLink" style="height: 40px; width: 10px; cursor: pointer;" onclick="change();">
				<img style=" background-image:url('./images/middleButtonTiny1.jpg');  margin-top: 140px; border-style:none; width: 10px; height: 40px;" src="../images/middleButtonTiny1.jpg"/>
				<span class="popBox">单击这里打开/折叠左侧树状视图</span>
			</a>
		</div>

		<div class="right" id="rightPanel">
		<table width="100%">
			<tr>
				<td align="left" style="width: 506px;">
					<div style="border-style: none;">
						<img width="500px" src="<%=request.getContextPath() %>/file/pie/${ requestScope.pieName }"/>
					</div>
				</td>
				
				<td rowspan="2" style="vertical-align: top;">
					<div style="background-color: white; height: 30px; padding-top: 10px; text-align: center;">
						<a href="<%=request.getContextPath() %>/FileUpload.do" class="link" title="上传新文件">上传新文件</a>
					</div>
					<div style="background-color: #fefbc9; height: margin-top: 10px;">  		
						<br/>
								
								<p class="passage">
									单击上面的链接来转到文件上传页面 
								</p>
								<p class="passage" style="line-height: 20px;">
									服务器端为您开辟了50M的个人空间
								</p>
								<br/>
					</div>
					<div style="background-color: white; height: 30px; padding-top: 10px; text-align: center;">
						<a href="#" class="link" style="margin-top: 10px;" onclick="javascript:openWindow();" title="在服务器建立文件夹">新建文件夹</a>
					</div>
					<div style="background-color: #fefbc9; height: margin-top: 10px;">  		
						<br/>
								
								<p class="passage">
									单击上面的链接来新建您的文件夹
								</p>
								<br/>
					</div>
				</td>
				<form method="post" name="myform" action="#">
					<input type="hidden" name="filename" value=""/>
				</form>
				
  			</tr>
  			<tr>
  				<td align="center">
  					<strong style="font-family: 方正姚体; font-size: 20px; color: navy;">
  						剩余空间<fmt:formatNumber type="number" value="${ requestScope.spaceScale }" pattern="##.#%"></fmt:formatNumber>
  						&nbsp;共50M
  					</strong>
  							
					
  				</td>
  			</tr>
  	
		</table>
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>














