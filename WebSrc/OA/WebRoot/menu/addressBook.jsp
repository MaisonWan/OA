<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.icss.hit.hibernate.vo.*" %>
<%@ page import="java.util.*" %>
	<link rel="stylesheet" type="text/css" href="./css/dtree.css">
	<script type="text/javascript" src="./js/addressBookTree.js"></script>
	<script type="text/javascript">
		function addAddress(nameid){
			var nameID = new Array();
			nameID = nameid.split(",");
			// 显示的名字和员工号
			var name = nameID[0];
			// 后台提交的ID
			var id = nameID[1];
			var sinal = ",";// 分割的符号
			var address = document.getElementById("address").innerText;

			var addressid = document.getElementById("sum").value;
			// 显示的姓名和员工号
			var separate = new Array();
			var separateid = new Array();
			separate = address.split(sinal);
			separateid = addressid.split(sinal);
			if( separate.length == 0 ){
				document.getElementById("address").innerText = name;
				document.getElementById("sum").value = id;
			}
			else{
			var flag = false;
			var result = "";
			var resultid = "";
			//document.getElementById("sum").innerText = separate.length;
				for(var i=0; i<separate.length; i++){
					//alert(separate[i]);
					//alert(name);
					if( separate[i] == name ){
						
						flag = true;
						continue;
					}else{
						if( i != 0 && result != "" ){
							result += sinal;
							resultid += sinal;
						}
						result += separate[i];
						resultid += separateid[i];
					}
				}
				if( !flag ){
					//alert("I am here!!!");
					if( result != "" ){
						result += sinal;
						resultid += sinal;
					}
					result += name;
					resultid += id;
				}
				document.getElementById("address").innerText = result;
				document.getElementById("sum").value = resultid;
			}
		}
	</script>
    <div class="dtree">
	<p><a href="javascript: d.openAll();" style="font-size: 15px; color: navy; margin-top: 5px; margin-left: 5px;">展开</a> | <a href="javascript: d.closeAll();" style="font-size: 15px; color: navy; margin-top: 5px; margin-left: 5px;">合并</a></p>
 	<jsp:useBean id="infoBean" class="com.icss.hit.bean.UserInfoBean"/>
	<jsp:useBean id="deptBean" class="com.icss.hit.bean.DepartmentBean"/>
	<script type="text/javascript">
		<!--
		d = new dTree('d');
		d.add(0,-1,'组织机构',"",'浏览个人信息');
		<%
			List<SysDept> deptList = deptBean.getAllDept();
			List<SysUser> userList = infoBean.getAllUsers();
			
			// 将员工与部门进行关联
			int num = 1;
			int deptSize = deptList.size();
			int userSize = userList.size();
			int deptCurrentNum = num;
			//out.println("结果集大小"+deptSize+" " + userSize );
			for( int i = 0; i < deptSize; i++ ){
				SysDept dept = deptList.get(i);
				
				// 当前部门的树状编号
				deptCurrentNum = num;
				%>
					d.add(<%=num++%>,0,'<%=dept.getSdName()%>','','<%=dept.getSdName()%>','','./images/dept.gif','./images/dept.gif');
				<%
				for( int j = 0; j < userList.size(); j++ ){
					SysUser user = userList.get(j);
					// 用户属于哪一个部门
					if( user.getSysDept().getSdId().equals(dept.getSdId()) ){
		%>
						d.add(<%=num++%>,<%=deptCurrentNum%>,'<%=user.getSuUsername()%>','<%=user.getSuUsername()%><<%=user.getSuUid()%>>,<%=user.getSuId()%>','<%=user.getSuUsername()%>的详细信息','','./images/stuff.gif');
 		<%			}// end if
 				}// end for userList
 			}// end for deptList
 		%>
		document.write(d);
		//-->
	</script>
</div>