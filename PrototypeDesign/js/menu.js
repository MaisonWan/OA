// JavaScript Document
	var mmenus    = new Array();
	var misShow   = new Boolean(); 
	misShow=false;
	var misdown   = new Boolean();
	misdown=false;
	var musestatus=false;
	var mpopTimer = 0;
	
	
	
	mmenucolor='#bec0c1';
	mfontcolor='MenuText';
	mmenuoutcolor='#edfc4b';
	mmenuincolor='#edfc4b';
	mmenuoutbordercolor='#FFFFFF';
	mmenuinbordercolor='#000000';
	mmidoutcolor='#E2DFDA';
	mmidincolor='#8D8A85';
	mmenuovercolor='MenuText';
	mitemedge='1';
	msubedge='1';
	mmenuunitwidth=90;
	mmenuitemwidth=100;
	mmenuheight=22;
	mmenuwidth='975';
	mmenuadjust=0;
	mmenuadjustV=0;
	mfonts='font-family: 宋体; font-size: 9pt; color: MenuText; ';
	mcursor='default';
	
	
	
	
	
	var fadeSteps = 15;
	var fademsec = 50;//修改显示速度
	var fadeArray = new Array();
	function fade(el, fadeIn, steps, msec) {
		if (steps == null) steps = fadeSteps;
		if (msec == null) msec = fademsec;
		if (el.fadeIndex == null)
			el.fadeIndex = fadeArray.length;
		fadeArray[el.fadeIndex] = el;
		if (el.fadeStepNumber == null) {
			if (el.style.visibility == "hidden")
				el.fadeStepNumber = 0;
			else
				el.fadeStepNumber = steps;
			if (fadeIn)
				el.style.filter = "Alpha(Opacity=0)";
			else
				el.style.filter = "Alpha(Opacity=100)";
		}
		window.setTimeout("repeatFade(" + fadeIn + "," + el.fadeIndex + "," + steps + "," + msec + ")", msec);
	}
	function repeatFade(fadeIn, index, steps, msec) {	
		el = fadeArray[index];
		
		c = el.fadeStepNumber;
		if (el.fadeTimer != null)
			window.clearTimeout(el.fadeTimer);
		if ((c == 0) && (!fadeIn)) {
			el.style.visibility = "hidden";
			return;
		}
		else if ((c==steps) && (fadeIn)) {
			el.style.filter = "";
			el.style.visibility = "visible";
			return;
		}
		else {
			(fadeIn) ? 	c++ : c--;
			el.style.visibility = "visible";
			el.style.filter = "Alpha(Opacity=" + 100*c/steps + ")";
			el.fadeStepNumber = c;
			el.fadeTimer = window.setTimeout("repeatFade(" + fadeIn + "," + index + "," + steps + "," + msec + ")", msec);
		}
	}
	
	function stoperror(){
	return true;
	}
	window.onerror=stoperror;
	function mpopOut() {
	mpopTimer = setTimeout('mallhide()', 100);
	}
	function getReal(el, type, value) {
		temp = el;
		while ((temp != null) && (temp.tagName != "BODY")) {
			if (eval("temp." + type) == value) {
				el = temp;
				return el;
			}
			temp = temp.parentElement;
		}
		return el;
	}
	
	
	function mMenuRegister(menu) 
	{
	  mmenus[mmenus.length] = menu
	  return (mmenus.length - 1)
	}
	function mMenuItem(caption,command,target,isline,statustxt,img,sizex,sizey,pos){
		this.caption=caption;
		this.command=command;
		this.target=target;
		this.isline=isline;
		this.statustxt=statustxt;
		this.img=img;
		this.sizex=sizex;
		this.sizey=sizey;
		this.pos=pos;
	}
	function mMenu(caption,command,target,img,sizex,sizey,pos){
		this.items = new Array();
		this.caption=caption;
		this.command=command;
		this.target=target;
		this.img=img;
		this.sizex=sizex;
		this.sizey=sizey;
		this.pos=pos;
		this.id=mMenuRegister(this);
	}
	function mMenuAddItem(item)
	{
	  this.items[this.items.length] = item
	  item.parent = this.id;
	  this.children=true;
	}
	
	mMenu.prototype.addItem = mMenuAddItem;
	function mtoout(src){
	
	src.style.borderLeftColor=mmenuoutbordercolor;
	src.style.borderRightColor=mmenuinbordercolor;
	src.style.borderTopColor=mmenuoutbordercolor;
	src.style.borderBottomColor=mmenuinbordercolor;
	src.style.backgroundColor=mmenuoutcolor;
	src.style.color=mmenuovercolor;
	}
	function mtoin(src){
	
	src.style.borderLeftColor=mmenuinbordercolor;
	src.style.borderRightColor=mmenuoutbordercolor;
	src.style.borderTopColor=mmenuinbordercolor;
	src.style.borderBottomColor=mmenuoutbordercolor;
	src.style.backgroundColor=mmenuincolor;
	src.style.color=mmenuovercolor;
	}
	function mnochange(src){
	src.style.borderLeftColor=mmenucolor;
	src.style.borderRightColor=mmenucolor;
	src.style.borderTopColor=mmenucolor;
	src.style.borderBottomColor=mmenucolor;
	src.style.backgroundColor='';
	src.style.color=mfontcolor;
	
	}
	function mallhide(){
		for(var nummenu=0;nummenu<mmenus.length;nummenu++){
			var themenu=document.all['mMenu'+nummenu]
			var themenudiv=document.all['mmenudiv'+nummenu]
					mnochange(themenu);
					mmenuhide(themenudiv);
					}
	}
	function mmenuhide(menuid){
	
	fade(menuid,false,0);
	misShow=false;
	}
	function mmenushow(menuid,pid){
	
	menuid.style.left=mposflag.offsetLeft+pid.offsetLeft+mmenuadjust;menuid.style.top=mposflag.offsetTop+mmenutable.offsetHeight+mmenuadjustV;
	if(mmenuitemwidth+parseInt(menuid.style.left)>document.body.clientWidth+document.body.scrollLeft)
	menuid.style.left=document.body.clientWidth+document.body.scrollLeft-mmenuitemwidth;
	fade(menuid,true,0);
	misShow=true;
	}
	function mmenu_over(menuid,x){
	toel = getReal(window.event.toElement, "className", "coolButton");
	fromel = getReal(window.event.fromElement, "className", "coolButton");
	if (toel == fromel) return;
	if(x==0||x==7||x==9){
	  misShow = false;
	  mallhide();
	  mtoout(eval("mMenu"+x));
	}else{
	
	  mallhide();
	  mtoin(eval("mMenu"+x));
	  mmenushow(menuid,eval("mMenu"+x));
	
	}
	clearTimeout(mpopTimer);
	}
	function mmenu_out(x){
	toel = getReal(window.event.toElement, "className", "coolButton");
	fromel = getReal(window.event.fromElement, "className", "coolButton");
	if (toel == fromel) return;
	if (misShow){
	mtoin(eval("mMenu"+x));
	}else{
	mnochange(eval("mMenu"+x));
	}
	mpopOut()
	}
	function mmenu_down(menuid,x){
	  if(misShow){
	  mmenuhide(menuid);
	  mtoout(eval("mMenu"+x));
	  }
	  else{
	  mtoin(eval("mMenu"+x));
	  mmenushow(menuid,eval("mMenu"+x));
	  misdown=true;
	  }
	}
	function mmenu_up(){
	  misdown=false;
	}
	function mmenuitem_over(x,i){
	srcel = getReal(window.event.srcElement, "className", "coolButton");
	if(misdown){
		mtoin(srcel);
	}
	else{
	mtoout(srcel);
	}
	mthestatus = mmenus[x].items[i].statustxt;
	if(mthestatus!=""){
		musestatus=true;
		window.status=mthestatus;
	}
	clearTimeout(mpopTimer);
	}
	function mmenuitem_out(){
	srcel = getReal(window.event.srcElement, "className", "coolButton");
	mnochange(srcel);
	if(musestatus)window.status="";
	mpopOut()
	}
	function mmenuitem_down(){
	srcel = getReal(window.event.srcElement, "className", "coolButton");
	mtoin(srcel)
	misdown=true;
	}
	function mmenuitem_up(){
	srcel = getReal(window.event.srcElement, "className", "coolButton");
	mtoout(srcel)
	misdown=false;
	}
	function mexec2(x){
	var cmd;
	if(mmenus[x].target=="blank"){
	  cmd = "window.open('"+mmenus[x].command+"')";
	}else{
	  cmd = mmenus[x].target+".location=\""+mmenus[x].command+"\"";
	}
	eval(cmd);
	}
	function mexec(x,i){
	var cmd;
	if(mmenus[x].items[i].target=="blank"){
	  cmd = "window.open('"+mmenus[x].items[i].command+"')";
	}else{
	  cmd = mmenus[x].items[i].target+".location=\""+mmenus[x].items[i].command+"\"";
	}
	eval(cmd);
	}
	function mbody_click(){
	
	if (misShow){
		srcel = getReal(window.event.srcElement, "className", "coolButton");
		for(var x=0;x<=mmenus.length;x++){
			if(srcel.id=="mMenu"+x)
			return;
		}
		mallhide();
	}
	}
	document.onclick=mbody_click;
	function mwritetodocument(){
		  var mwb=1;
		 var stringx='<div id="mposflag" style="position:absolute;"></div><table  id=mmenutable border=0 cellpadding=3 cellspacing=2 width='+mmenuwidth+' height='+mmenuheight+' bgcolor='+mmenucolor+
		 ' onselectstart="event.returnValue=false"'+
		 ' style="cursor:'+mcursor+';'+mfonts+
		 ' border-left: '+mwb+'px solid '+mmenuoutbordercolor+';'+
		 ' border-right: '+mwb+'px solid '+mmenuinbordercolor+'; '+
		 'border-top: '+mwb+'px solid '+mmenuoutbordercolor+'; '+
		 'border-bottom: '+mwb+'px solid '+mmenuinbordercolor+'; padding:0px"><tr>'
		 for(var x=0;x<mmenus.length;x++){
			var thismenu=mmenus[x];
			var imgsize="";
			if(thismenu.sizex!="0"||thismenu.sizey!="0")imgsize=" width="+thismenu.sizex+" height="+thismenu.sizey;
			var ifspace="";
			if(thismenu.caption!="")ifspace=" ";
			stringx += "<td nowrap class=coolButton id=mMenu"+x+" style='border: "+mitemedge+"px solid "+mmenucolor+
			"' width="+mmenuunitwidth+"px onmouseover=mmenu_over(mmenudiv"+x+
			","+x+") onmouseout=mmenu_out("+x+
			") onmousedown=mmenu_down(mmenudiv"+x+","+x+")";
				  if(thismenu.command!=""){
					  stringx += " onmouseup=mmenu_up();mexec2("+x+");";
				  }else{
					  stringx += " onmouseup=mmenu_up()";
				  }
				  if(thismenu.pos=="0"){
					  stringx += " align=center><img align=absmiddle src='"+thismenu.img+"'"+imgsize+">"+ifspace+thismenu.caption+"</td>";	
				  }else if(thismenu.pos=="1"){
					  stringx += " align=center>"+thismenu.caption+ifspace+"<img align=absmiddle src='"+thismenu.img+"'"+imgsize+"></td>";	
				  }else if(thismenu.pos=="2"){
					  stringx += " align=center background='"+thismenu.img+"'> "+thismenu.caption+" </td>";	
				  }else{
					  stringx += " align=center> "+thismenu.caption+" </td>";
				  }
			stringx += "";
		 }
		 stringx+="<td width=*> </td></tr></table>";
		 
		 
		 for(var x=0;x<mmenus.length;x++){
			thismenu=mmenus[x];
			if(x==0||x==9||x==7){
			stringx+='<div id=mmenudiv'+x+' style="visiable:none"></div>';
			}else{
			stringx+='<div id=mmenudiv'+x+
			' style="cursor:'+mcursor+';position:absolute;'+
			'width:'+mmenuitemwidth+'px; z-index:'+(x+100);
			if(mmenuinbordercolor!=mmenuoutbordercolor&&msubedge=="0"){
			stringx+=';border-left: 1px solid '+mmidoutcolor+
			';border-top: 1px solid '+mmidoutcolor;}
			stringx+=';border-right: 1px solid '+mmenuinbordercolor+
			';border-bottom: 1px solid '+mmenuinbordercolor+';visibility:hidden" onselectstart="event.returnValue=false">\n'+
			'<table  width="100%" border="0" height="100%" align="center" cellpadding="0" cellspacing="2" '+
			'style="'+mfonts+' border-left: 1px solid '+mmenuoutbordercolor;
			if(mmenuinbordercolor!=mmenuoutbordercolor&&msubedge=="0"){
			stringx+=';border-right: 1px solid '+mmidincolor+
			';border-bottom: 1px solid '+mmidincolor;}
			stringx+=';border-top: 1px solid '+mmenuoutbordercolor+
			';padding: 4px" bgcolor='+mmenucolor+'>\n'
			for(var i=0;i<thismenu.items.length;i++){
				var thismenuitem=thismenu.items[i];
				var imgsize="";
					if(thismenuitem.sizex!="0"||thismenuitem.sizey!="0")imgsize=" width="+thismenuitem.sizex+" height="+thismenuitem.sizey;
					var ifspace="";
					if(thismenu.caption!="")ifspace=" ";
				if(!thismenuitem.isline){
				stringx += "<tr><td class=coolButton style='border: "+mitemedge+"px solid "+mmenucolor+
				"' width=100% height=15px onmouseover=\"mmenuitem_over("+x+","+i+
				");\" onmouseout=mmenuitem_out() onmousedown=mmenuitem_down() onmouseup=";
	stringx += "mmenuitem_up();mexec("+x+","+i+"); ";
	if(thismenuitem.pos=="0"){
						stringx += "><img align=absmiddle src='"+thismenuitem.img+"'"+imgsize+">"+ifspace+thismenuitem.caption+"</td></tr>";	
					  }else if(thismenuitem.pos=="1"){
						stringx += ">"+thismenuitem.caption+ifspace+"<img align=absmiddle src='"+thismenuitem.img+"'"+imgsize+"></td></tr>";	
					  }else if(thismenuitem.pos=="2"){
						stringx += "background='"+thismenuitem.img+"'>"+thismenuitem.caption+"</td></tr>";	
					  }else{
						stringx += ">"+thismenuitem.caption+"</td></tr>";
					  }
	}else{
				stringx+='<tr><td height="1" background="hr.gif" onmousemove="clearTimeout(mpopTimer);"><img height="1" width="1" src="none.gif" border="0"></td></tr>\n';
				}
			}stringx+='</table>\n</div>'
			}
			
	}
	
		 document.write("<div align='left'>"+stringx+"</div>");
	}
	
		mpmenu1=new mMenu('首页','../home/home.html','self','','','','');
		
		mpmenu2=new mMenu('个人信息','#','self','','','','');
		mpmenu2.addItem(new mMenuItem('个人信息','../info/selfInfo.html','self',false,'','','','',''));
		mpmenu2.addItem(new mMenuItem('他人信息','../info/otherInfo.html','self',false,'','','','',''));
		
		mpmenu3=new mMenu('工作安排','#','self','','','','');
		mpmenu3.addItem(new mMenuItem('代办设置','../work/config.html','self',false,'','','','',''));
		mpmenu3.addItem(new mMenuItem('日程安排','../work/Plan.html','self',false,'','','','',''));
		mpmenu3.addItem(new mMenuItem('日历查询','../work/calenderSearch.html','self',false,'','','','',''));
		mpmenu3.addItem(new mMenuItem('综合查询','../work/allSearch.html','self',false,'','','','',''));
		
		mpmenu4=new mMenu('名片夹','#','self','','','','');
		mpmenu4.addItem(new mMenuItem('名片夹分类','../card/cardType.html','self',false,'','','','',''));
		mpmenu4.addItem(new mMenuItem('个人名片夹','../card/selfCard.html','self',false,'','','','',''));
		mpmenu4.addItem(new mMenuItem('共享名片夹','../card/shareCard.html','self',false,'','','','',''));
		
		mpmenu5=new mMenu('待办事项','#','self','','','','');
		mpmenu5.addItem(new mMenuItem('待办事项','../workPlan/planList.html','self',false,'','','','',''));
		mpmenu5.addItem(new mMenuItem('工作清单','../workPlan/workList.html','self',false,'','','','',''));
		
		mpmenu6=new mMenu('在线信息','#','self','','','','');
		mpmenu6.addItem(new mMenuItem('新建留言','../message/new.html','self',false,'','','','',''));
		mpmenu6.addItem(new mMenuItem('收件箱','../message/inbox.html','self',false,'','','','',''));
		mpmenu6.addItem(new mMenuItem('草稿箱','../message/draftbox.html','self',false,'','','','',''));
		mpmenu6.addItem(new mMenuItem('发件箱','../message/outbox.html','self',false,'','','','',''));
		
		
		
		
		mpmenu9=new mMenu('个人文件夹','#','self','','','','');
		mpmenu9.addItem(new mMenuItem('个人文件夹','../file/selfDefault.html','self',false,'','','','',''));
		mpmenu9.addItem(new mMenuItem('公共文件夹','../file/share.html','self',false,'','','','',''));
		
		mpmenu7=new mMenu('公司通讯录','../address/adress.html','self','','','','');
		
		
		mpmenu8=new mMenu('会议管理','#','self','','','','');
		
		mpmenu8.addItem(new mMenuItem('会议室申请','../meeting/meetingapply.html','self',false,'','','','',''));
		mpmenu8.addItem(new mMenuItem('预约会议室','../meeting/meetingorder.html','self',false,'','','','',''));
		mpmenu8.addItem(new mMenuItem('会议室申请管理','../meeting/managermeeting.html','self',false,'','','','',''));
		mpmenu8.addItem(new mMenuItem('会议记录','../meeting/meetingrecord.html','self',false,'','','','',''));
		
		mpmenu10=new mMenu('退出','../login.html','self','','','','');
