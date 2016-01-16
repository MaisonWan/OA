<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>OA办公系统-选择图片上传</title>
<link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>
	<link href="favicon.ico" rel="icon" type="image/x-icon"/>
	<meta name="verify-v1" content="S+Y0imOx/nWw7HfjS4Iknhar/4IZh+fuZuQn8Z3fdyc=" />
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

<script language="JavaScript">
function uploadBegin(){
	theFeats = "height=220,width=320,location=no,menubar=no,resizable=no,scrollbars=no,status=no,toolbar=no";
	strAppVersion = navigator.appVersion;
	if (document.uploadForm.file0.value != ""||document.uploadForm.file1.value != ""||document.uploadForm.file2.value != ""||document.uploadForm.file3.value != ""||document.uploadForm.file4.value != "")
	{
	    if (strAppVersion.indexOf('MSIE') != -1 && strAppVersion.substr(strAppVersion.indexOf('MSIE')+5,1) > 4)
	    {	
			winstyle = "dialogWidth=320px; dialogHeight:200px; center:yes";
			window.showModelessDialog(<%="\""+response.encodeURL(request.getContextPath() + "/filepage/progressbar.jsp")+"\""%>,window,winstyle);
	    }
	}
}
function upload_onclick()
{
	window.uploadForm.action="FileUploadProcess.do?ffid=" + window.uploadForm.foldername.options(window.uploadForm.foldername.selectedIndex).value;
	uploadBegin();
	window.uploadForm.submit();
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
			<strong style="margin-left: 9px;">当前位置-></strong>
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
  			
  				<form  enctype="multipart/form-data" method="post" name="uploadForm" id="uploadForm" onSubmit="uploadBegin();">
	<logic:present name="fileFolder">
		<table width="100%"	border="0" align="center" cellpadding="0" cellspacing="0" class="table">
       <thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">请选择要上传的文件</thead>
       
       <tr>
       	<td class="tableColumeHead" colspan="2">选择要保存的文件夹:
       		
       		<select name="foldername">
       			<logic:iterate id="folder" name="fileFolder" type="com.icss.hit.hibernate.vo.FileFolder">
       				<option value="${ folder.ffId }">${ folder.ffName }</option>
       			</logic:iterate>
       		</select>
       		
       	</td>
       </tr>
       <tr>
         <td align="center">
           <input type="file" name="file0" id="file0" size=55 />
           <input type="file" name="file1" id="file1" size=55 />
           <input type="file" name="file2" id="file2" size=55 />
           <input type="file" name="file3" id="file2" size=55 />
         </td>
         <td>
         	<p class="passage">单个文件大小不得超过5M</p>
         </td>
       </tr>
       <tr>
       <td colspan="2">
       		<input type="button" name="button" value="上 传" onClick="upload_onclick()" style="cursor:hand" class="shortButton"/>
       		${ requestScope.uploaderror }
       </td>
     </tr>
</table>
</logic:present>
 
</form>
  		<logic:notPresent name="fileFolder">
  			<div class="notFound" align="center">
	    		<strong class="notFoundText">
	    			没有文件夹分类，请新建之后再上传文件
	    		</strong>
	    	</div>
  		</logic:notPresent>	
  			
  			
    	</div>
    	</div>
    	<div id="bottom"></div>





</body>
</html>

