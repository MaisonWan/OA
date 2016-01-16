<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
    <div class="dtree">
	<script type="text/javascript">
		<!--
		d = new dTree('d');
		d.add(0,-1,'会议室管理',"#",'浏览会议室和会议管理');
		d.add(1,0,'会议室申请',"<%=path%>/roomRedirect.do",'申请一个会议室','','./images/meeting.gif');
		d.add(2,0,'预约会议室',"<%=path%>/meetingOrder.do",'查看符合条件的会议室','','./images/meeting.gif');
		d.add(3,0,'会议室申请管理',"<%=path%>/registerMeetingRoomLog.do",'查看您所有的申请会议室的记录','','./images/meeting.gif');
		d.add(4,0,'发起会议',"<%=path%>/meetingLaunch.do",'发起一个新的会议','','./images/meeting.gif');
		d.add(5,0,'会议记录',"<%=path%>/meetingAttend.do",'查看您的会议记录','','./images/meeting.gif');
		document.write(d);
		//-->
	</script>
</div>
