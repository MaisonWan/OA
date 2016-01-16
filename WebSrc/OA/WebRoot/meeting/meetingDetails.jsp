<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-会议详情</title>
    	<link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>
    	<meta name="verify-v1" content="S+Y0imOx/nWw7HfjS4Iknhar/4IZh+fuZuQn8Z3fdyc=" />
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
		window.uploadForm.action="UploadMeetingRecord.do?mtid=" + document.forms[0].mtId.value;
		uploadBegin();
		window.uploadForm.submit();
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
			<strong style="margin-left: 9px;">当前位置->会议详情</strong>
		</div>
		<hr class="line"/>
			
		<div class="left" id="leftPanel">
			<jsp:include page="../menu/meetingMenu.jsp"></jsp:include>
		</div>

		<div id="middleButton">
			<a id="middleLink" style="height: 40px; width: 10px; cursor: pointer;" onclick="change();">
				<img style=" background-image:url('./images/middleButtonTiny1.jpg');  margin-top: 140px; border-style:none; width: 10px; height: 40px;" src="../images/middleButtonTiny1.jpg"/>
				<span class="popBox">单击这里打开/折叠左侧树状视图</span>
			</a>
		</div>

		<div class="right" id="rightPanel">
  				
  				<logic:present name="meetingDetails">
		    <table class="table" width="100%" cellspacing="0">
		    <thead style="text-align:center; font-size: large; color: black; background-color: #dbdbda; font-family: 方正姚体;">会议详情</thead>
		    	<tr>
			    	<td class="tableColumeHead">会议标题</td>
			    	<td>${ meetingDetails.mtTitle }
			    	</td>
			    </tr>
			    <tr>
			    	<td class="tableColumeHead">发起人</td>
			    	<td>${ meetingDetails.sysUser.suUsername }</td>
			    </tr>
			    <tr>
			    	<td class="tableColumeHead">会议地点</td>
			    	<td>${ meetingDetails.room.RName }</td>
			    </tr>
			    <tr>
			    	<td class="tableColumeHead">会议时间</td>
			    	 <td><bean:write name="meetingDetails" property="mtBegintime" format="yyyy-MM-dd HH:mm"/>----
			    	 <bean:write name="meetingDetails" property="mtEndtime" format="yyyy-MM-dd HH:mm"/></td>
			    </tr>
			    <tr>
			    	<td class="tableColumeHead">参与人</td>
			    	 <td style="height: 20px;">
			    	 	<logic:present name="attendeeList">
			    	 		<logic:iterate id="attendee" name="attendeeList" type="com.icss.hit.hibernate.vo.SysUser">
								${ attendee.suUsername } 
							</logic:iterate>
			    	 	</logic:present>
			    	 </td>
			    </tr>
			    <tr>
			    	<td class="tableColumeHead">会议内容</td>
			    	<td style="height: 90px;vertical-align:top;">
			    		${ meetingDetails.mtContent }
			    	</td>
			    </tr>
			    
			    <tr>
			    	<td class="tableColumeHead" style="width: 30%;">会议记录:</td>
    				<td>
    				<c:if test="${ requestScope.record != null }"><a href="./meetingRecord/${ requestScope.record}">查看会议详细记录</a>
    				</c:if>
    				<c:if test="${ requestScope.record == null }">
    				<c:if test="${ requestScope.userId == meetingDetails.sysUser.suId }">
    					<form  enctype="multipart/form-data" method="post" name="uploadForm" id="uploadForm" onsubmit="uploadBegin();">
    					<input type="file" name="file0" id="file0" size=55 />
    					<input type="hidden" name="mtId" value="${ meetingDetails.mtId }"/><br/>
    					会议记录最大上传为2MB，文件类型为*.htm或者*.html
      					<input type="button" name="button" value="上 传" onclick="upload_onclick()" style="cursor:hand" class="shortButton"/>
      					${ requestScope.MeetingRecordError }
      					</form>
			    	</c:if>
			    	<c:if test="${ requestScope.userId != meetingDetails.sysUser.suId }">
			    		会议记录暂时未上传
			    	</c:if>
			    	</c:if> 				
      				</td>
				</tr>

			    <tr>
			    	<td colspan="2">
			    		<input type="button" value="返回" class="shortButton" onclick="window.location.href='${ requestScope.forwardString }'"/>
			    	</td>
			    </tr>
		    </table>
	    
    </logic:present>
    <logic:notPresent name="meetingDetails">
		<div class="notFound" align="center">
		    		<strong class="notFoundText">
		    			无会议记录
		    		</strong>
		    	</div>
	</logic:notPresent>		
  				
  				
  				
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>






