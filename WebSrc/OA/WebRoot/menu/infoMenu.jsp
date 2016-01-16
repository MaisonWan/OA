<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.icss.hit.hibernate.vo.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link rel="stylesheet" type="text/css" href="../css/dtree.css">
	<script type="text/javascript" src="../js/tree.js"></script>
  </head>
  <body>
    <div class="dtree">
	<p><a href="javascript: d.openAll();" style="font-size: 15px; color: navy; margin-top: 5px; margin-left: 5px;">展开</a> | <a href="javascript: d.closeAll();" style="font-size: 15px; color: navy; margin-top: 5px; margin-left: 5px;">合并</a></p>
 	<jsp:useBean id="infoBean" class="com.icss.hit.bean.UserInfoBean"/>
	<jsp:useBean id="deptBean" class="com.icss.hit.bean.DepartmentBean"/>
	<script type="text/javascript">
		<!--
		d = new dTree('d');
		d.add(0,-1,'个人信息',"UserInfo.do",'浏览个人信息');
		d.add(1,0,'修改信息','userInfoUpdateAction.do','','','./images/info.gif','./images/info.gif');
		d.add(2,0,'修改密码','Password.do','','','./images/info.gif','./images/info.gif');
		d.add(3,-1,'他人信息','OtherInfo.do','','','./images/info.gif');
		<%
			List<SysDept> deptList = deptBean.getAllDept();
			List<SysUser> userList = infoBean.getAllUsers();
			
			// 将员工与部门进行关联
			int num = 4;
			int deptSize = deptList.size();
			int userSize = userList.size();
			int deptCurrentNum = num;
			//out.println("结果集大小"+deptSize+" " + userSize );
			for( int i = 0; i < deptSize; i++ ){
				SysDept dept = deptList.get(i);
				
				// 当前部门的树状编号
				deptCurrentNum = num;
				%>
					d.add(<%=num++%>,3,'<%=dept.getSdName()%>','#','<%=dept.getSdName()%>','','./images/dept.gif','./images/dept.gif');
				<%
				for( int j = 0; j < userList.size(); j++ ){
					SysUser user = userList.get(j);
					// 用户属于哪一个部门
					if( user.getSysDept().getSdId().equals(dept.getSdId()) ){
		%>
						d.add(<%=num++%>,<%=deptCurrentNum%>,'<%=user.getSuUsername()%>','otherUserDetail.do?userId=<%=user.getSuId()%>','<%=user.getSuUsername()%>的详细信息','','./images/stuff.gif');
 		<%			}// end if
 				}// end for userList
 			}// end for deptList
 		%>
		document.write(d);
		//-->
	</script>
</div>
  </body>
</html>
