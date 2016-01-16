<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    
    <title>OA办公系统-文件夹详情</title>

	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	
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
		function deleteFolder(){
			if( confirm('删除该文件夹则同时删除该文件夹下的所有文件，您确认继续') ){
				document.forms[0].flag.value = "delete";
				document.forms[0].submit();
			}
		}
		
		function shareFolder(){
			if( confirm('你确认要共享该文件夹吗？') ){
				document.forms[0].flag.value = "share";
				document.forms[0].submit();
			}
		}
		
		function noshareFolder(){
			if( confirm('你确认要私有该文件夹吗？') ){
				document.forms[0].flag.value = "noshare";
				document.forms[0].submit();
			}
		}
		
		function checkDelete(id){
			if( confirm('你确认要删除该文件夹吗？') ){
				location.href="DeleteFile.do?id=" + id;
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
			<strong style="margin-left: 9px;">当前位置->文件夹详情</strong>
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
  				<div style="width: 100%; margin: 2px auto; background-color: #fefbc9;">
  	<form action="FolderFunction.do" method="post">
  	<table width="100%">
  	
  		<tr>
  			<td>
  				<input type="button" class="longButton" name="delete" value="删除此文件夹" onclick="deleteFolder();"/>
			  	<c:if test="${requestScope.share == '0'}">
			  		<input type="button" class="shortButton" name="share" value="设为共享" onclick="shareFolder();" style="margin-left: 20px;"/>
			  	</c:if>
			  	<c:if test="${requestScope.share == '1'}">
			  		<input type="button" class="shortButton" name="noshare" value="设为私有" onclick="noshareFolder();" style="margin-left: 20px;"/>
			  	</c:if>
			  	
			  	<input type="hidden" name="flag" value=""/>
			  	<input type="hidden" name="folderId" value="${ param.id }"/>
			  	<input type="hidden" name="newname" value=""/>
  			</td>
  			<td align="right">
  				<a href="<%=request.getContextPath() %>/FileUpload.do" class="link" style="text-align: right;">上传新文件</a>
  			</td>
  		</tr>
	  	
		  	
  		</table>
  	</form>
  	</div>
  
  <logic:present name="list">
  <table class="table" cellspacing="0" width="100%">
  <thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">文件夹详情</thead>
  		<tr>
    		<td class="tableColumeHead">编号</td>
    		<td class="tableColumeHead">文件名</td>
    		<td class="tableColumeHead">所属文件夹</td>
    		<td class="tableColumeHead">上传时间</td>
    		<td class="tableColumeHead">下载</td>
    		<td class="tableColumeHead">删除</td>
    	</tr>
    <logic:iterate id="flist" name="list" type="com.icss.hit.hibernate.vo.Files">
    	<tr onmouseover="this.style.backgroundColor = '#ffffff';" onmouseout="this.style.backgroundColor = '#fefccc';">
    		<td>${ flist.FId }</td>
    		<td>${ flist.FName }</td>
    		<td>${ flist.fileFolder.ffName }</td>
    		<td>${ flist.FTime }</td>
    		<td><a href="./DownLoad?id=${flist.FId}">下载</a></td>
    		<td><a href="#" onclick="checkDelete(${flist.FId});">删除</a></td>
    	</tr>
    </logic:iterate>
  </table>
  ${ requestScope.pageString }
  </logic:present>
  				
  				
    	</div>
    	</div>
    	<div id="bottom"></div>
  
  
  
  

  </body>
</html>
