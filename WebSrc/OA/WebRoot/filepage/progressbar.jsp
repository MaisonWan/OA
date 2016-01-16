<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>文件上传进度</title>
<script language="javascript">
function GetProgressDetail()
{
	var funid=(new Date()).getTime();
    var detail = new ActiveXObject("Microsoft.XMLHTTP");
    detail.open("GET",<%="\""+response.encodeURL("progressdetail.jsp?funid=")+"\""%>+funid,false);
    detail.send();
	var detail_info=detail.responseText;
	var start=detail_info.indexOf("<detail-start>");
	var end=detail_info.indexOf("</detail-start>");
	if(start!=-1 && end!=-1){
		detail_info=detail_info.substring(start+14,end);
		var detail_info_array=detail_info.split("||");
		var fileName=detail_info_array[0];
		
		if(fileName.length>15) fileName="..."+fileName.slice(fileName.length-16);
		setMessageContent("上传文件 "+fileName);
		progressBar.UpdateToPersent(detail_info_array[1]);
		progressBar.SetProgressTextValue(detail_info_array[1]+"%");
		setUploadDetail(detail_info_array[2],
						detail_info_array[3],
						detail_info_array[4],
						detail_info_array[5],
						detail_info_array[6]);
	}

}
 function closeWindow(){
 	if(document.all.isStop.checked==true) window.dialogArguments.document.execCommand("Stop");
	window.close();
 }
 function setMessageContent(mes){
 	document.all.messageContent.innerHTML=mes;
 }
 function setUploadDetail(speed,readTotalSize,totalSize,remainTime,totalTime){
 	document.all.speed.innerText=speed;
	document.all.readTotalSize.innerText=readTotalSize;
	document.all.totalSize.innerText=totalSize;
	document.all.remainTime.innerText=remainTime;
	document.all.totalTime.innerText=totalTime;
 }
 //以下为进度条对象代码
 function xyProgressLG(xyID){

  this.xyProgressID = 'oProgress' + Math.random().toString().substr(2, 10) + xyID;
  this.max = 0;
  this.min = 0;
 
  this.width = 100;
  this.height = 20;
  this.currPos = 0;
  this.outerBorderColor = "black";
  this.outerBackColor = "white";
  this.innerBorderColor = "";
  this.innerBackColor = "blue";
  this.TextColor = "red";

  this.SetProgressTop = xySetProgressTop;
  this.SetProgressLeft = xySetProgressLeft;
  this.SetProgressWidth = xySetProgressWidth;
  this.SetProgressHeight = xySetProgressHeight;
  this.SetProgressBorderColor = xySetProgressBorderColor;
  this.SetProgressForeBorderColor = xySetProgressForeBorderColor;
  this.SetProgressBackColor = xySetProgressBackColor;
  this.SetProgressForeColor = xySetProgressForeColor;
  this.EnableProgressText = xyEnableProgressText;
  this.SetProgressTextFontSize = xySetProgressTextFontSize;
  this.SetProgressTextFontColor = xySetProgressTextFontColor;
  this.SetProgressTextFontFamily = xySetProgressTextFontFamily;
  this.SetProgressTextValue=xySetProgressTextValue;

  this.SetProgressMax = xySetProgressMax;
  this.SetProgressMin = xySetProgressMin;
  this.UpdatePosition = xyUpdatePosition;
  this.UpdateToPosition = xyUpdateToPosition;
  this.UpdatePersent = xyUpdatePersent;
  this.UpdateToPersent = xyUpdateToPersent;

  xyProgressInit(this.xyProgressID);
  return this.xyProgressID;
 }

 function xyProgressInit(xyID){
  var div = document.createElement("div");
  div.id = xyID;
  div.style.position = "absolute";
  div.style.left = "100px";
  div.style.top = "100px";
  document.body.appendChild(div);
  
  var pro = document.createElement("div");
  pro.style.position = "absolute";
  pro.style.left = "0px";
  pro.style.top = "0px";
  pro.style.fontSize= "0px";
  pro.style.width = "100px";
  pro.style.height = "20px";
  pro.style.border = "1px solid black";
  pro.style.background = "white";
  pro.style.zIndex = "100";
  div.appendChild(pro);

  var proInner = document.createElement("div");
  proInner.id = xyID+"_Inner";
  proInner.style.position = "absolute";
  proInner.style.left = "1px";
  proInner.style.top = "1px";
  proInner.style.fontSize= "0px";
  proInner.style.width = "0px";
  proInner.style.height = "16px";
  proInner.style.border = "1px solid black";
  proInner.style.background = "blue";
  pro.style.zIndex = "102";
  pro.appendChild(proInner);

  var text = document.createElement("div");
  text.style.position = "absolute";
  text.style.left = "47%";
  text.style.top = "2px";
  text.style.fontSize= "14px";
  text.style.width = "40px";
  text.style.height = "16px";
  text.style.zIndex = "103";
  text.style.color="red";
  text.style.fontWeight="bolder";
  div.appendChild(text);
 }
 function xySetProgressTop(pStyleTop){ //设置top值
  try{
   var e = document.all(this.xyProgressID);
   e.style.top = pStyleTop;
  }catch(e){
   alert(e.description);
  }
 }
 function xySetProgressLeft(pStyleLeft){ //设置left值
  try{
   var e = document.all(this.xyProgressID);
   e.style.left = pStyleLeft;
  }catch(e){
   alert(e.description);
  }
 }
 function xySetProgressWidth(pWidth){ //设置width值
  try{
   var e = document.all(this.xyProgressID);
   e.style.width = pWidth;
   eOuter = e.children(0);
   eOuter.style.width = pWidth;
   this.width = pWidth;
  }catch(e){
   alert(e.description);
  }
 }
 function xySetProgressHeight(pHeight){ //设置height值
  try{
   var e = document.all(this.xyProgressID);
   e.style.height = pHeight;
   eOuter = e.children(0);
   eInner = e.children(0).children(0);
   eOuter.style.height = pHeight;
   eInner.style.height = pHeight-4;
   this.height = pHeight;
  }catch(e){
   alert(e.description);
  }
 }
 function xySetProgressBorderColor(pColor){ //设置外边框border的color值
  try{
   var e = document.all(this.xyProgressID);
   e = e.children(0);
   e.style.border = "1px solid "+pColor;
  }catch(e){
   alert(e.description);
  }
 }
 function xySetProgressForeBorderColor(pColor){  //设置内边框border的color值
  try{
   var e = document.all(this.xyProgressID);
   e = e.children(0).children(0);
   e.style.border = "1px solid "+pColor;
  }catch(e){
   alert(e.description);
  }
 }
 function xySetProgressBackColor(pColor){  //设置外边框背影色
  try{
   var e = document.all(this.xyProgressID);
   e = e.children(0);
   e.style.background = pColor;
  }catch(e){
   alert(e.description);
  }
 }
 function xySetProgressForeColor(pColor){ //设置内边框行进条色
  try{
   var e = document.all(this.xyProgressID);
   e = e.children(0).children(0);
   e.style.background = pColor;
  }catch(e){
   alert(e.description);
  }
 }
 function xyEnableProgressText(bEnable){ //是否显示文字的开关，默认是true，显示
  try{
   var e = document.all(this.xyProgressID);
   eText = e.children(1);
   if(bEnable){
    eText.style.display = "none";
   }else{
    eText.style.display = "";
   }
   var pos = e.style.width;
   var sel = eText.style.width;
   pos = (pos-sel)/2;
   eText.style.left = pos;
  }catch(e){
   alert(e.description);
  }
 }
 function xySetProgressTextFontSize(pSize){ //设置显示文字的大小
  try{
   var e = document.all(this.xyProgressID);
   e = e.children(1);
   e.style.fontSize = pSize;
  }catch(e){
   alert(e.description);
  }
 }
 function xySetProgressTextFontColor(pColor){ //设置显示文字的颜色
  try{
   var e = document.all(this.xyProgressID);
   e = e.children(1);
   e.style.color = pColor;
  }catch(e){
   alert(e.description);
  }
 }
 function xySetProgressTextFontFamily(pFamily){ //设置显示文字的字体
  try{
   var e = document.all(this.xyProgressID);
   e = e.children(1);
   e.style.fontFamily = pFamily;
  }catch(e){
   alert(e.description);
  }
 }
 function xySetProgressMax(pMax){ //进度条表示的最大长度
  this.max = pMax;
 }
 function xySetProgressMin(pMin){ //进度条表示的最小长度
  this.min = pMin;
 }
 function xySetProgressTextValue(pText){ //设置显示文字的内容
   try{
   var e = document.all(this.xyProgressID);
   e = e.children(1);
   e.innerHTML=pText;
   }
   catch(e){
   alert(e.description);
  }
 }
 function xyUpdatePosition(pPosition){   /*将progress增长或减小pPosition(表示长度),其中,0<pPosition<max*/
  try{
   var max = this.max;
   var min = this.min;
   var e = document.all(this.xyProgressID);
   if(pPosition>max){
    return -1;
   }
   var len = this.width;
   var pos = this.currPos+(pPosition/(max-min))*len;
   var eProgress = e.children(0).children(0);
   if(pos>=this.width){
    eProgress.style.width = this.width-4;
    this.currPos = pos;
   }else if(pos<=0){
    eProgress.style.width = 0;
    this.currPos = pos;
   }else{
    eProgress.style.width = pos;
    this.currPos = pos;
   }
   return this.currPos;
  }catch(e){
   alert(e.description);
   return -1;
  }
 }
 function xyUpdateToPosition(pPosition){   /*将progress更新到pPosition位置(表示长度),其中,min<pPosition<max*/
  try{
   var max = this.max;
   var min = this.min;
   var e = document.all(this.xyProgressID);
   if(pPosition<min||pPosition>max){
    return -1;
   }
   var len = this.width;
   var pos = ((pPosition-min)/(max-min))*len;
   eProgress = e.children(0).children(0);
   if(pos>=this.width){
    eProgress.style.width = this.width-4;
    this.currPos = pos;
   }else if(pos<=0){
    eProgress.style.width = 0;
    this.currPos = pos;
   }else{
    eProgress.style.width = pos;
    this.currPos = pos;
   }
   return this.currPos;
  }catch(e){
   alert(e.description);
   return -1;
  }
 }
 function xyUpdatePersent(pPersent){   /*从当前位置增长或减小(+\-)pPersent%(html进度条长度),pPersent大于零:增长,否则可以小于零,减小*/
  try{
   var e = document.all(this.xyProgressID);
   e = e.children(0).children(0);
   var len = this.width;
   var pos = this.currPos;
   len = len*pPersent/100;
   pos += len;
   if(pos>this.width||pos<0){
    return -1;
   }
   e.style.width = pos;
   this.currPos = pos;
   return this.currPos;
  }catch(e){
   alert(e.description);
   return -1;
  }
 }
 function xyUpdateToPersent(pPersent){   /*从当前位置增长或减小到pPersent%(html进度条长度)*/
  try{
   var e = document.all(this.xyProgressID);
   e = e.children(0).children(0);
   var len = this.width;
   var pos = len*pPersent/100;
   if(pos>this.width||pos<0){
    return -1;
   }
   e.style.width = pos;
   this.currPos = pos;
   return this.currPos;
  }catch(e){
   alert(e.description);
   return -1;
  }
 }

 function xyNewID(){
  var d = new Date();
  var t = "oDraw_"+d.getTime().toString();
  return t;
 }
 //-->
</script>
</head>

<body bgcolor="#D6D3CE" leftmargin="0" topmargin="0">
<div id="message" style="position:absolute; left:5px; top:5px; width:300px; height:20px; z-index:1">
	<font size="2">信息:
	<label id="messageContent"></label>
</font></div>

<div id="Layer1" style="position:absolute; left:5px; top:50px; width:300px; height:42px; z-index:2">
 <p><font size="2">上传速度:<label id="speed"></label> K/S<br>
    已上传:<label id="readTotalSize"></label>M 共:<label id="totalSize"></label>M<br>
估计剩余时间:<label id="remainTime"></label><br>
 估计总时间:<label id="totalTime"></label>
 </font></p>
</div>
<div id="Layer2" style="position:absolute; left:5px; top:109px; width:300px; height:13px; z-index:3">  
  <p align="center">
    <font size="2">    </font>
    <input name="isStop" type="checkbox" id="isStop" value="0"> 
  <font size="2">关闭同时停止上传　　
  <input name="cancle" type="button" id="cancle" value="关闭此窗口" onClick="closeWindow();">
  </font></p>
</div>
<script language="javascript">
var progressBar = new xyProgressLG("upload");
 progressBar.SetProgressLeft(5);
 progressBar.SetProgressTop(25);
 progressBar.SetProgressWidth(300);
 progressBar.SetProgressHeight(20);
 progressBar.SetProgressBackColor("#08246B");
 progressBar.SetProgressForeColor("red");
 progressBar.SetProgressTextFontColor("white");
 progressBar.SetProgressMax(300);
 progressBar.SetProgressMin(0);
 setMessageContent("正在进行连接...");
 window.setInterval("GetProgressDetail()",1000);
</script>
</body>
</html>
