<%@ page language="java" pageEncoding="UTF-8" import="com.icss.hit.bean.*"%>
<%
	String path = request.getContextPath();
%>
    <div class="dtree">
	<script type="text/javascript">
		<!--
		<%
			long uid = -1;
			if( request.getSession().getAttribute("UserId") != null ){
				uid = Long.parseLong(request.getSession().getAttribute("UserId").toString());
			}
			CheckPower check = new CheckPower();
			// 设置用户ID
			check.setUserId(uid);
			// 得到用户权限
			check.getPower();
			if( !check.isEmpty()){
		%>
		d = new dTree('d');
		d.add(0,-1,'系统管理',"",'浏览系统管理');
		<%
				if(check.check(CheckPower.ADMIN_ROOM)){
		%>
		d.add(1,0,'添加会议室',"<%=path%>/NewMeetingRoom.do",'添加一个会议室','','./images/sys.gif');
		d.add(2,0,'会议室管理',"<%=path%>/manageRoom.do",'修改或者删除会议室','','./images/sys.gif');
		<%
			}
			if(check.check(CheckPower.ADMIN_ROOM_REG)){
		%>
		d.add(3,0,'会议室申请审批',"<%=path%>/manageMeetingRoom.do",'审批会议室预约','','./images/sys.gif');
		d.add(4,0,'会议室使用报表',"<%=path%>/outputGraph.do",'查看会议室使用情况或者导出报表','','./images/sys.gif');
		<%
			}
			if(check.check(CheckPower.ADMIN_DEPT)){
		%>
		d.add(5,0,'添加新部门',"<%=path%>/newDept.do",'添加新的部门','','./images/sys.gif');
		d.add(6,0,'部门列表',"<%=path%>/deptList.do",'查看部门列表','','./images/sys.gif');
		<%
			}
			if(check.check(CheckPower.ADMIN_POWER)){
		%>
		d.add(7,0,'添加权限',"<%=path%>/newSysPowerType.do",'添加新的系统权限','','./images/sys.gif');
		d.add(8,0,'权限列表',"<%=path%>/sysPowerList.do",'查看权限列表','','./images/sys.gif');
		<%
			}
			if(check.check(CheckPower.ADMIN_ROLE)){
		%>
		d.add(9,0,'添加系统角色',"<%=path%>/newSysRole.do",'添加新的系统角色','','./images/sys.gif');
		d.add(10,0,'系统角色列表',"<%=path%>/showRoles.do",'系统角色的列表','','./images/sys.gif');
		d.add(11,0,'角色分配',"<%=path%>/roleArrange.do",'分配角色','','./images/sys.gif');
		d.add(12,0,'员工管理',"<%=path%>/roleArrange.do",'修改员工信息','','./images/sys.gif');
		d.add(13,0,'添加员工',"<%=path%>/addWorker.do",'添加一个新的员工','','./images/sys.gif');
		<%
			}
		%>
		document.write(d);
		<%
			}
		%>
		//-->
	</script>
</div>
