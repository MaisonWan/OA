<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>目录页</title>
	
	<link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>
	<link href="favicon.ico" rel="icon" type="image/x-icon"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
  
<body>
	<div style="color:blue;">
		注意：本页码为了调试方便，免得每次都自己输入网址。需要常用的调试网址，自己添加到此页面，之后开发范围内不更改主页。<br>
		<h1 style="color:red;">成功已经不远，大家继续努力！！</h1>
	</div>
	<table width="400">
		<tr>
			<a href="UserInfo.do">个人信息</a>
		</tr>
		<tr>
			<a href="otherUserDetail.do?userId=10">他人信息(默认为10号)</a>
		</tr>
		<tr>

		<a href="OtherInfo.do">他人信息(搜索页)</a>
	</tr>
		<tr>
			<a href="./login.jsp">登陆界面</a>
		</tr>
		<tr>
			<a href="CardType.do?userId=1">名片夹分类测试</a>
		</tr>

		<tr>
		    <a href="selfCard.do">个人名片夹查询</a>
		</tr>

			<a href="addressInfo.do">公司通讯录</a>
		<tr>
			<a href="newCard.do">添加名片夹</a>
		</tr>
		<tr>
			<a href="Password.do">修改密码</a>
		</tr>
		<tr>
			<a href="shareCard.do">获取他人信息(共享名片夹---搜索页)</a>
		</tr>
		<tr>
			<a href="toModifyCard.do">修改名片</a>
		</tr>
		<tr>
			<a href="WorkPlan.do">日程安排</a>
		</tr>
		<tr>
			<a href="WorkPlanDetail.do?id=2">日程安排详细信息</a>
		</tr>
		<tr>
			<a href="SearchPlan.do">日程安排高级搜索</a>
		</tr>
		<tr>
			
		</tr>
		<tr>
		<a href="canlenderSearch.do">日历查询</a>
		</tr>
		<tr>
		<a href="configWork.do?dept=0&page=1">代办设置</a>
		</tr>
		<tr>
		<a href="authorizedUser.do">本人授权代办组</a>
		</tr>
		<tr>
			<a href="NewMessage.do">在线信息-新建信息</a>
		</tr>
		<tr>
			<a href="draftBox.do">在线信息-草稿箱</a>
		</tr>
		<tr>
			<a href="outbox.do">在线信息-发件箱</a>
		</tr>
		<tr>
			<a href="workList.do">工作清单</a>
		</tr>
		<tr>
			<a href="planList.do">待办事项列表 </a>
		</tr>
		<tr>
			<a href="planListMore.do">待办事项列表(更多) </a>
		</tr>
		<tr>
			<a href="MyFile.do">个人文件夹 </a>
		</tr>

		<tr>
		<a href="inBox.do">收件箱</a>
		</tr>

		<tr>
			<a href="FileUpload.do">上传个人文件夹 </a>
		</tr>
		<tr>
			<a href="FolderDetail.do?id=1">个人文件夹详细信息</a>
		</tr>

		<tr>
			<a href="roomRedirect.do">会议申请</a>
		</tr>
		


		<tr>
			<a href="registerMeetingRoomLog.do">会议室申请记录</a>
		</tr>

		
		<tr>
			<a href="NewMeetingRoom.do">添加会议室</a>
		</tr>
		
		<tr>
			<a href="meetingOrder.do">····会议室预约····</a>
		</tr>
		
		<tr>
			<a href="deptList.do">部门列表</a>
		</tr>
		<tr>
			<a href="newDept.do">添加新部门</a>
		</tr>

		<tr>
			<a href="meetingOrder.do">会议室预约</a>
		</tr>
		<tr>
			<a href="meetingAttend.do">会议记录</a>
		</tr>

		
		<tr>
			<a href="ShareFile.do">共享文件夹</a>
		</tr>

		<tr>
			<a href="meetingOrder.do">查看可用会议室</a>
		</tr>

		<tr>
			<a href="meetingLaunch.do">发起会议</a>
		</tr>
		<tr>
			<a href="manageRoom.do">会议室维护</a>
		</tr>
		<tr>
			<a href="addSysPowerType.do">添加系统权限</a>
		</tr>
		<tr>
			<a href="sysPowerList.do">权限列表</a>
		</tr>

		<tr>
			<a href="outputGraph.do">每月会议室报表导出</a>
		</tr>


		<tr>
			<a href="newSysRole.do">添加角色</a>
		</tr>
		
		<tr>
			<a href="showRoles.do">角色权限</a>
		</tr>

	</table>
	
</body>
</html>