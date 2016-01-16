<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
    <div class="dtree">
	<script type="text/javascript">
		<!--
		d = new dTree('d');
		d.add(0,-1,'在线信息',"#",'浏览在线信息');
		d.add(1,0,'新建信息',"<%=path%>/NewMessage.do",'新建立一条信息','','./images/mail.png');
		d.add(2,0,'收件箱',"<%=path%>/inBox.do",'查看您收到的所有信息','','./images/mail.png');
		d.add(3,0,'草稿箱',"<%=path%>/draftBox.do",'保存您所有的草稿','','./images/mail.png');
		d.add(4,0,'发件箱',"<%=path%>/outbox.do",'您发出去的所有邮件','','./images/mail.png');
		document.write(d);
		//-->
	</script>
</div>
