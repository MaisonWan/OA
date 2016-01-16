<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="ahxu.commons.upload.*"%>
<%!
HttpSession ses;
ReportItem aReportItem;
%>
<%
	ses=request.getSession(false);
	aReportItem=DefaultReportItemManage.getItem(ses);
	if(aReportItem!=null){
		out.println("<detail-start>");
		out.println(aReportItem.getFileName()+"||"
					+aReportItem.getCompletePercent()+"||"
					+aReportItem.getUploadSpeedKB()+"||"
					+aReportItem.getUploadSizeMKB()+"||"
					+aReportItem.getTotalSizeMKB()+"||"
					+aReportItem.getRemainTimeHMS()+"||"
					+aReportItem.getTotalTimeHMS());
		out.println("</detail-start>");
	}

%>
