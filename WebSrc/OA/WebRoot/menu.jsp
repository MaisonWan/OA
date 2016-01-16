<%@ page language="java" import="java.util.*,com.icss.hit.bean.*" pageEncoding="utf-8"%>
<link type="text/css" rel="stylesheet" rev="stylesheet" href="./css/general_style.css"/>
<ul id="navmenu"> 
		<li><a href="Home.do" style="background-image:none; padding-left:15px;">首页</a></li> 
		<%
			String path = request.getContextPath();
		%>
		<li><a href="#">个人信息</a> 
			<ul> 
				<li><a href="<%=path %>/UserInfo.do">个人信息</a></li> 
				<li><a href="<%=path %>/OtherInfo.do">他人信息</a></li> 
			</ul> 
		</li>
		
		<li><a href="#">工作安排</a> 
			<ul> 
				<li><a href="<%=path %>/configWork.do">代办设置</a></li> 
				<li><a href="<%=path %>/WorkPlan.do">日程安排</a></li>
				<li><a href="<%=path %>/canlenderSearch.do">日历查询</a></li> 
				<li><a href="<%=path %>/SearchPlan.do">综合查询</a></li> 
			</ul> 
		</li>
		
		<li><a href="#">名片夹</a> 
			<ul> 
				<li><a href="<%=path %>/newCard.do">添加新名片</a></li> 
				<li><a href="<%=path %>/CardType.do">我的名片夹</a></li> 
				<li><a href="<%=path %>/selfCard.do">我的名片</a></li>
				<li><a href="<%=path %>/shareCard.do">共享名片夹</a></li> 
			</ul> 
		</li>

		<li><a href="#">待办事项</a> 
			<ul> 
				<li><a href="<%=path %>/planList.do">待办事项</a></li> 
				<li><a href="<%=path %>/workList.do">工作清单</a></li>
			</ul> 
		</li>
		
		<li><a href="#">在线信息</a> 
			<ul> 
				<li><a href="<%=path %>/NewMessage.do">新建留言</a></li> 
				<li><a href="<%=path %>/inBox.do">收件箱</a></li>
				<li><a href="<%=path %>/draftBox.do">草稿箱</a></li>
				<li><a href="<%=path %>/outbox.do">发件箱</a></li>
			</ul>
		</li>

		<li><a href="#">个人文件夹</a> 
			<ul> 
				<li><a href="<%=path %>/MyFile.do">个人文件夹</a></li> 
				<li><a href="<%=path %>/FileUpload.do">上传文件</a></li> 
				<li><a href="<%=path %>/ShareFile.do">公共文件夹</a></li>
			</ul> 
		</li>

		 
		<li><a href="<%=path %>/addressInfo.do" style="background-image:none; padding-left:15px;">公司通讯录</a></li> 
		
		<li><a href="#">会议管理</a> 
			<ul> 
				<li><a href="<%=path %>/roomRedirect.do">会议室申请</a></li> 
				<li><a href="<%=path %>/meetingOrder.do">预约会议室</a></li>
				<li><a href="<%=path %>/registerMeetingRoomLog.do">会议室申请管理</a></li>
				<li><a href="<%=path %>/meetingLaunch.do">发起会议</a></li>
				<li><a href="<%=path %>/meetingAttend.do">会议记录</a></li>
				
			</ul> 
		</li>
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
		<li><a href="#">系统管理</a> 
			<ul> 
			<%
				if(check.check(CheckPower.ADMIN_ROOM)){
			%>
				<li><a href="<%=path %>/NewMeetingRoom.do">添加会议室</a></li>
				<li><a href="<%=path %>/manageRoom.do">会议室管理</a></li>
			<%
				}
				if(check.check(CheckPower.ADMIN_ROOM_REG)){
			%>
				<li><a href="<%=path %>/manageMeetingRoom.do">会议室申请审批</a></li>
				<li><a href="<%=path %>/outputGraph.do">会议室使用报表</a></li>
			<%
				}
				if(check.check(CheckPower.ADMIN_DEPT)){
			%>
				<li><a href="<%=path %>/newDept.do">添加新部门</a></li>
				<li><a href="<%=path %>/deptList.do">部门列表</a></li> 
			<%
				}
				if(check.check(CheckPower.ADMIN_POWER)){
			%>
				<li><a href="<%=path %>/newSysPowerType.do">添加权限</a></li>
				<li><a href="<%=path %>/sysPowerList.do">权限列表</a></li>
			<%
				}
				if(check.check(CheckPower.ADMIN_ROLE)){
			%>
				<li><a href="<%=path %>/newSysRole.do">添加系统角色</a></li>
				<li><a href="<%=path %>/showRoles.do">系统角色列表</a></li>
				<li><a href="<%=path %>/roleArrange.do">角色分配</a></li>
				<li><a href="<%=path %>/roleArrange.do">员工管理</a></li>
				<li><a href="<%=path %>/addWorker.do">添加员工</a></li>
			<%
				}
			%>
			</ul> 
		</li>
		<%
			}
		%>
		<li><a href="<%=path %>/Logout.do" style="background-image:none; padding-left:15px;">退出</a></li> 
	</ul>