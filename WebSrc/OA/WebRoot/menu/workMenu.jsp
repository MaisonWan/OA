<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
    <div class="dtree">
	<script type="text/javascript">
		<!--
		d = new dTree('d');
		d.add(0,-1,'工作安排',"#",'浏览日程安排等信息');
		d.add(1,0,'待办设置',"<%=path%>/configWork.do",'授权给他人代办的权限','','./images/mail.png');
		d.add(2,0,'授权待办',"<%=path%>/authorizedUser.do",'查看您已经授权的人员信息','','./images/mail.png');
		d.add(3,0,'新建日程',"<%=path%>/newWorkConfig.do",'新建一个日程安排','','./images/mail.png');
		d.add(4,0,'日程安排',"<%=path%>/WorkPlan.do",'查看您安排的所有日程安排','','./images/mail.png');
		d.add(5,0,'日历查询',"<%=path%>/canlenderSearch.do",'按月日查询详细的日程安排','','./images/mail.png');
		d.add(6,0,'综合查询',"<%=path%>/SearchPlan.do",'对日程安排的高级搜索','','./images/mail.png');
		document.write(d);
		//-->
	</script>
</div>
