<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.icss.hit.hibernate.vo.*" %>
<%@ page import="java.util.*" %>
    <div class="dtree">
	<p><a href="javascript: d.openAll();" style="font-size: 15px; color: navy; margin-top: 5px; margin-left: 5px;">展开</a> | <a href="javascript: d.closeAll();" style="font-size: 15px; color: navy; margin-top: 5px; margin-left: 5px;">合并</a></p>
 	<jsp:useBean id="fileBean" class="com.icss.hit.bean.FileBean"/>
	<jsp:useBean id="folderBean" class="com.icss.hit.bean.FileFolderBean"/>
	<script type="text/javascript">
		<!--
		
		d = new dTree('d');
		d.add(0,-1,'个人文件夹',"MyFile.do",'浏览个人文件夹信息信息');
		<%
			long uid = 1L;
			if( request.getSession().getAttribute("UserId") != null ){
				uid = Long.parseLong(request.getSession().getAttribute("UserId").toString());
			}
			List<FileFolder> folderList = folderBean.getAllFolders(uid);
			//List<Files> fileList = fileBean.getFiles();
			
			// 开始的编号
			int num = 1;
			
			//out.println("结果集大小"+deptSize+" " + userSize );
			for( int i = 0; folderList != null && i < folderList.size(); i++ ){
				FileFolder folder = folderList.get(i);
				int current = num;
				// 得到该文件夹下的所有文件信息
				List<Files> fileList = fileBean.getFiles(folder.getFfId());
				%>
					d.add(<%=num++%>,0,'<%=folder.getFfName()%>','FolderDetail.do?id=<%=folder.getFfId() %>','<%=folder.getFfName()%>');
				<%
				for( int j = 0;fileList != null && j < fileList.size(); j++ ){
					Files file = fileList.get(j);
					// 判断文件类型
					String filetype = file.getFName().substring(file.getFName().lastIndexOf(".") + 1);
					String iconame = null;
					filetype = filetype.toLowerCase();
					if( filetype.equals("jpg") || filetype.equals("gif") || filetype.equals("bmp") || filetype.equals("jpeg")){
						iconame = "smallPicIcon.png";
					}
					else if(filetype.equals("doc") || filetype.equals("docx")){
						iconame = "smallWordIcon.png";
					}
					else if(filetype.equals("rar") || filetype.equals("zip")){
						iconame = "smallRarIcon.png";
					}
					else if( filetype.equals("xls") || filetype.equals("xlsx")){
						iconame = "smallExcelIcon.png";
					}
					else{
						iconame = "smallUnknownIcon.png";
					}
		%>
					d.add(<%=num++%>,<%=current%>,'<%=file.getFName()%>','DownLoad?id=<%=file.getFId() %>','<%=file.getFName()%>的详细信息','','./images/<%=iconame %>');
 		<%
 				}// end for userList
 			}// end for deptList
 		%>
 		d.add(<%=num++%>,-1,'上传文件',"FileUpload.do",'上传新的文件');
 		d.add(<%=num++%>,-1,'共享文件夹',"ShareFile.do",'浏览他人的共享文件夹');
		document.write(d);
		//-->
	</script>
	
	
</div>
