
<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

  <head>
    
    <title>OA办公系统-会议室使用情况</title>
    	<link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>
	<link href="favicon.ico" rel="icon" type="image/x-icon"/>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link type="text/css" rel="stylesheet" rev="stylesheet" href="../css/general_style.css"/>
	<link type="text/css" rel="stylesheet" rev="stylesheet" href="../css/dtree.css"/>
	<script  type="text/javascript" src="./js/menu.js"></script>
	<script type="text/javascript" src="./js/tree.js"></script>
	<script type="text/javascript" src="./js/head.js"></script>
	<script type="text/javascript">
		function changeSubmit()
		{
			document.forms[0].action="roomExcelExport.do";
			document.forms[0].submit();
		}
	</script>
	<%
			String path = request.getContextPath();
	%>
  </head>
  
  <body>
  
  	<div id="head">
  		<a href="../home/home.html" style="width:140px;">
  			<img id="headPicture" alt="转到首页" style="top: 0px; left: 20px; width: 140px; height: 120px; position:relative; border: 0px navy none;" src="./images/headHover2.jpg" onmouseover="changePicture();" onmouseout="backPicture();"></img>
  		</a>
  	</div>
	<div id="nav">
		<jsp:include page="../menu.jsp"></jsp:include>
	</div>
	<div id="content" style="font-size:13px;">
		<div style="color: #FFFFFF; padding-top: 6px;">
			<img alt="箭头" src="./images/arrow.gif" style=" position:relative; top: 3px; left: 6px;"/>
			<strong style="margin-left: 9px;">当前位置->会议室使用情况</strong>
		</div>
		<hr class="line"/>
			
		<div class="left" id="leftPanel">
			<jsp:include page="../menu/sysMenu.jsp"></jsp:include>
		</div>

		<div id="middleButton">
			<a id="middleLink" style="height: 40px; width: 10px; cursor: pointer;" onclick="change();">
				<img style=" background-image:url('./images/middleButtonTiny1.jpg');  margin-top: 140px; border-style:none; width: 10px; height: 40px;" src="../images/middleButtonTiny1.jpg"/>
				<span class="popBox">单击这里打开/折叠左侧树状视图</span>
			</a>
		</div>

		<div class="right" id="rightPanel">
  					
  		<form action="/OA/graphOutput.do" method="post">
  		<table class="table" width="100%" cellspacing="0">
  			<tr>
  				<td>
  					年份：
			    	<select name="year" style="width: 150px;">
			    		<option value="2007" <c:if test="${ requestScope.year == 2007 }">selected</c:if>>2007</option>
			    		<option value="2008" <c:if test="${ requestScope.year == 2008 }">selected</c:if>>2008</option>
			    		<option value="2009" <c:if test="${ requestScope.year == 2009 }">selected</c:if>>2009</option>
			    		<option value="2010" <c:if test="${ requestScope.year == 2010 }">selected</c:if>>2010</option>
			    		<option value="2011" <c:if test="${ requestScope.year == 2011 }">selected</c:if>>2011</option>
			    		<option value="2012" <c:if test="${ requestScope.year == 2012 }">selected</c:if>>2012</option>
			    	</select>
  				</td>
  				<td>
  					月份：
			    	<select name="month" style="width: 150px;">
			    		<option value="1" <c:if test="${ requestScope.month == 1 }">selected</c:if>>1</option>
			    		<option value="2" <c:if test="${ requestScope.month == 2 }">selected</c:if>>2</option>
			    		<option value="3" <c:if test="${ requestScope.month == 3 }">selected</c:if>>3</option>
			    		<option value="4" <c:if test="${ requestScope.month == 4 }">selected</c:if>>4</option>
			    		<option value="5" <c:if test="${ requestScope.month == 5 }">selected</c:if>>5</option>
			    		<option value="6" <c:if test="${ requestScope.month == 6 }">selected</c:if>>6</option>
			    		<option value="7" <c:if test="${ requestScope.month == 7 }">selected</c:if>>7</option>
			    		<option value="8" <c:if test="${ requestScope.month == 8 }">selected</c:if>>8</option>
			    		<option value="9" <c:if test="${ requestScope.month == 9 }">selected</c:if>>9</option>
			    		<option value="10" <c:if test="${ requestScope.month == 10 }">selected</c:if>>10</option>
			    		<option value="11" <c:if test="${ requestScope.month == 11 }">selected</c:if>>11</option>
			    		<option value="12" <c:if test="${ requestScope.month == 12 }">selected</c:if>>12</option>
			    	</select>
  				</td>
  				<td>
  					<input name="submit" type="submit" value="查看会议室使用情况图" class="longerButton" />
  				</td>
  			</tr>
  			<tr>
  				<td colspan="3" class="generalError">
  					
  					<input name="submit" type="submit" value="导出查询月份的Excel文件" onclick="changeSubmit()" class="longerButton"/>
			    	<logic:present name="conflict">
			    		${ requestScope.conflict }
					</logic:present>
					
					<logic:present name="roomRegsErrors">
			    		${ requestScope.roomRegsErrors }
					</logic:present>
					
					<logic:present name="ExcelName">
			    		<a class="link" href="<%=request.getContextPath() %>/excel/${ requestScope.ExcelName }">Excel文件导出成功点击下载</a>
					</logic:present>
  					
  				</td>
  			</tr>
  		</table>
    	
    	
    	
    	
    	<div align="center">
		<logic:present name="pic">
    		<img src="<%=path %>/file/pie/${ requestScope.year }_${ requestScope.month }_fileline.jpg" style="width: 746px; height: 280px;" />
    		
		</logic:present>
		</div>
		
    	
    	
    	
    </form>
  					
  					
    	</div>
    	</div>
    	<div id="bottom"></div>
  </body>
</html>













