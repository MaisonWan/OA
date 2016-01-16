<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="true">
 <head>
    
    <title>OA办公系统-日历查询</title>
    
<script language="javaScript">
<!--
/*****************************************************************************
                                   日期资料
*****************************************************************************/

var lunarInfo=new Array(
0x04bd8,0x04ae0,0x0a570,0x054d5,0x0d260,0x0d950,0x16554,0x056a0,0x09ad0,0x055d2,
0x04ae0,0x0a5b6,0x0a4d0,0x0d250,0x1d255,0x0b540,0x0d6a0,0x0ada2,0x095b0,0x14977,
0x04970,0x0a4b0,0x0b4b5,0x06a50,0x06d40,0x1ab54,0x02b60,0x09570,0x052f2,0x04970,
0x06566,0x0d4a0,0x0ea50,0x06e95,0x05ad0,0x02b60,0x186e3,0x092e0,0x1c8d7,0x0c950,
0x0d4a0,0x1d8a6,0x0b550,0x056a0,0x1a5b4,0x025d0,0x092d0,0x0d2b2,0x0a950,0x0b557,
0x06ca0,0x0b550,0x15355,0x04da0,0x0a5d0,0x14573,0x052d0,0x0a9a8,0x0e950,0x06aa0,
0x0aea6,0x0ab50,0x04b60,0x0aae4,0x0a570,0x05260,0x0f263,0x0d950,0x05b57,0x056a0,
0x096d0,0x04dd5,0x04ad0,0x0a4d0,0x0d4d4,0x0d250,0x0d558,0x0b540,0x0b5a0,0x195a6,
0x095b0,0x049b0,0x0a974,0x0a4b0,0x0b27a,0x06a50,0x06d40,0x0af46,0x0ab60,0x09570,
0x04af5,0x04970,0x064b0,0x074a3,0x0ea50,0x06b58,0x055c0,0x0ab60,0x096d5,0x092e0,
0x0c960,0x0d954,0x0d4a0,0x0da50,0x07552,0x056a0,0x0abb7,0x025d0,0x092d0,0x0cab5,
0x0a950,0x0b4a0,0x0baa4,0x0ad50,0x055d9,0x04ba0,0x0a5b0,0x15176,0x052b0,0x0a930,
0x07954,0x06aa0,0x0ad50,0x05b52,0x04b60,0x0a6e6,0x0a4e0,0x0d260,0x0ea65,0x0d530,
0x05aa0,0x076a3,0x096d0,0x04bd7,0x04ad0,0x0a4d0,0x1d0b6,0x0d250,0x0d520,0x0dd45,
0x0b5a0,0x056d0,0x055b2,0x049b0,0x0a577,0x0a4b0,0x0aa50,0x1b255,0x06d20,0x0ada0)

var solarMonth=new Array(31,28,31,30,31,30,31,31,30,31,30,31);
var Gan=new Array("甲","乙","丙","丁","戊","己","庚","辛","壬","癸");
var Zhi=new Array("子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥");
var Animals=new Array("鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪");
var solarTerm = new Array("小寒","大寒","立春","雨水","惊蛰","春分","清明","谷雨","立夏","小满","芒种","夏至","小暑","大暑","立秋","处暑","白露","秋分","寒露","霜降","立冬","小雪","大雪","冬至")
var sTermInfo = new Array(0,21208,42467,63836,85337,107014,128867,150921,173149,195551,218072,240693,263343,285989,308563,331033,353350,375494,397447,419210,440795,462224,483532,504758)
var nStr1 = new Array('日','一','二','三','四','五','六','七','八','九','十')
var nStr2 = new Array('初','十','廿','卅','　')
var monthName = new Array("JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC");

//国历节日 *表示放假日
var sFtv = new Array(
"0101*元旦",
"0214 情人节",
"0303 我生日",
"0308 妇女节",
"0312 植树节",
"0315 消费者权益日",
"0317 St. Patrick's",
"0401 愚人节",
"0501 劳动节",
"0504 青年节",
"0512 护士节",
"0512 茵生日",
"0601 儿童节",
"0614 Flag Day",
"0701 建党节 香港回归纪念",
"0703 炎黄在线诞辰",
"0718 托普诞辰",
"0801 建军节",
"0808 父亲节",
"0909 毛泽东逝世纪念",
"0910 教师节",
"0928 孔子诞辰",
"1001*国庆节",
"1006 老人节",
"1024 联合国日",
"1111 Veteran's / Remembrance Day",
"1112 孙中山诞辰纪念",
"1220 澳门回归纪念",
"1225 Christmas Day",
"1226 毛泽东诞辰纪念")

//农历节日 *表示放假日
var lFtv = new Array(
"0101*春节",
"0115 元宵节",
"0505 端午节",
"0707 七夕情人节",
"0715 中元节",
"0815 中秋节",
"0909 重阳节",
"1208 腊八节",
"1224 小年",
"0100*除夕")

//某月的第几个星期几
var wFtv = new Array(
"0131 Martin Luther King Day",
"0231 President's Day",
"0520 母亲节",
"0530 Armed Forces Day",
"0531 Victoria Day",
"0716 合作节",
"0730 被奴役国家周",
"0811 Civic Holiday",
"0911 Labor Holiday",
"1021 Columbus Day",
"1144 Thanksgiving")


/*****************************************************************************
                                      日期计算
*****************************************************************************/

//====================================== 传回农历 y年的总天数
function lYearDays(y) {
   var i, sum = 348
   for(i=0x8000; i>0x8; i>>=1) sum += (lunarInfo[y-1900] & i)? 1: 0
   return(sum+leapDays(y))
}

//====================================== 传回农历 y年闰月的天数
function leapDays(y) {
   if(leapMonth(y))  return((lunarInfo[y-1900] & 0x10000)? 30: 29)
   else return(0)
}

//====================================== 传回农历 y年闰哪个月 1-12 , 没闰传回 0
function leapMonth(y) {
   return(lunarInfo[y-1900] & 0xf)
}

//====================================== 传回农历 y年m月的总天数
function monthDays(y,m) {
   return( (lunarInfo[y-1900] & (0x10000>>m))? 30: 29 )
}

//====================================== 算出农历, 传入日期物件, 传回农历日期物件
//                                       该物件属性有 .year .month .day .isLeap .yearCyl .dayCyl .monCyl
function Lunar(objDate) {

   var i, leap=0, temp=0
   var baseDate = new Date(1900,0,31)
   var offset   = (objDate - baseDate)/86400000

   this.dayCyl = offset + 40
   this.monCyl = 14

   for(i=1900; i<2050 && offset>0; i++) {
      temp = lYearDays(i)
      offset -= temp
      this.monCyl += 12
   }

   if(offset<0) {
      offset += temp;
      i--;
      this.monCyl -= 12
   }

   this.year = i
   this.yearCyl = i-1864

   leap = leapMonth(i) //闰哪个月
   this.isLeap = false

   for(i=1; i<13 && offset>0; i++) {
      //闰月
      if(leap>0 && i==(leap+1) && this.isLeap==false)
         { --i; this.isLeap = true; temp = leapDays(this.year); }
      else
         { temp = monthDays(this.year, i); }

      //解除闰月
      if(this.isLeap==true && i==(leap+1)) this.isLeap = false

      offset -= temp
      if(this.isLeap == false) this.monCyl ++
   }

   if(offset==0 && leap>0 && i==leap+1)
      if(this.isLeap)
         { this.isLeap = false; }
      else
         { this.isLeap = true; --i; --this.monCyl;}

   if(offset<0){ offset += temp; --i; --this.monCyl; }

   this.month = i
   this.day = offset + 1
}

//==============================传回国历 y年某m+1月的天数
function solarDays(y,m) {
   if(m==1)
      return(((y%4 == 0) && (y%100 != 0) || (y%400 == 0))? 29: 28)
   else
      return(solarMonth[m])
}
//============================== 传入 offset 传回干支, 0=甲子
function cyclical(num) {
   return(Gan[num%10]+Zhi[num%12])
}

//============================== 月历属性
function calElement(sYear,sMonth,sDay,week,lYear,lMonth,lDay,isLeap,cYear,cMonth,cDay) {

      this.isToday    = false;
      //国历
      this.sYear      = sYear;
      this.sMonth     = sMonth;
      this.sDay       = sDay;
      this.week       = week;
      //农历
      this.lYear      = lYear;
      this.lMonth     = lMonth;
      this.lDay       = lDay;
      this.isLeap     = isLeap;
      //干支
      this.cYear      = cYear;
      this.cMonth     = cMonth;
      this.cDay       = cDay;

      this.color      = '';

      this.lunarFestival = ''; //农历节日
      this.solarFestival = ''; //国历节日
      this.solarTerms    = ''; //节气

}

//===== 某年的第n个节气为几日(从0小寒起算)
function sTerm(y,n) {
   var offDate = new Date( ( 31556925974.7*(y-1900) + sTermInfo[n]*60000  ) + Date.UTC(1900,0,6,2,5) )
   return(offDate.getUTCDate())
}

//============================== 传回月历物件 (y年,m+1月)
function calendar(y,m) {

   var sDObj, lDObj, lY, lM, lD=1, lL, lX=0, tmp1, tmp2
   var lDPOS = new Array(3)
   var n = 0
   var firstLM = 0

   sDObj = new Date(y,m,1)            //当月一日日期

   this.length    = solarDays(y,m)    //国历当月天数
   this.firstWeek = sDObj.getDay()    //国历当月1日星期几


   for(var i=0;i<this.length;i++) {

      if(lD>lX) {
         sDObj = new Date(y,m,i+1)    //当月一日日期
         lDObj = new Lunar(sDObj)     //农历
         lY    = lDObj.year           //农历年
         lM    = lDObj.month          //农历月
         lD    = lDObj.day            //农历日
         lL    = lDObj.isLeap         //农历是否闰月
         lX    = lL? leapDays(lY): monthDays(lY,lM) //农历当月最後一天

         if(n==0) firstLM = lM
         lDPOS[n++] = i-lD+1
      }

      //sYear,sMonth,sDay,week,
      //lYear,lMonth,lDay,isLeap,
      //cYear,cMonth,cDay
      this[i] = new calElement(y, m+1, i+1, nStr1[(i+this.firstWeek)%7],
                               lY, lM, lD++, lL,
                               cyclical(lDObj.yearCyl) ,cyclical(lDObj.monCyl), cyclical(lDObj.dayCyl++) )


      if((i+this.firstWeek)%7==0)   this[i].color = 'red'  //周日颜色
      if((i+this.firstWeek)%14==13) this[i].color = 'red'  //周休二日颜色
   }

   //节气
   tmp1=sTerm(y,m*2  )-1
   tmp2=sTerm(y,m*2+1)-1
   this[tmp1].solarTerms = solarTerm[m*2]
   this[tmp2].solarTerms = solarTerm[m*2+1]
   if(m==3) this[tmp1].color = 'red' //清明颜色

   //国历节日
   for(i in sFtv)
      if(sFtv[i].match(/^(\d{2})(\d{2})([\s\*])(.+)$/))
         if(Number(RegExp.$1)==(m+1)) {
            this[Number(RegExp.$2)-1].solarFestival += RegExp.$4 + ' '
            if(RegExp.$3=='*') this[Number(RegExp.$2)-1].color = 'red'
         }

   //月周节日
   for(i in wFtv)
      if(wFtv[i].match(/^(\d{2})(\d)(\d)([\s\*])(.+)$/))
         if(Number(RegExp.$1)==(m+1)) {
            tmp1=Number(RegExp.$2)
            tmp2=Number(RegExp.$3)
            this[((this.firstWeek>tmp2)?7:0) + 7*(tmp1-1) + tmp2 - this.firstWeek].solarFestival += RegExp.$5 + ' '
         }

   //农历节日
   for(i in lFtv)
      if(lFtv[i].match(/^(\d{2})(.{2})([\s\*])(.+)$/)) {
         tmp1=Number(RegExp.$1)-firstLM
         if(tmp1==-11) tmp1=1
         if(tmp1 >=0 && tmp1<n) {
            tmp2 = lDPOS[tmp1] + Number(RegExp.$2) -1
            if( tmp2 >= 0 && tmp2<this.length) {
               this[tmp2].lunarFestival += RegExp.$4 + ' '
               if(RegExp.$3=='*') this[tmp2].color = 'red'
            }
         }
      }

   //黑色星期五
   if((this.firstWeek+12)%7==5)
      this[12].solarFestival += '黑色星期五 '

   //今日
   if(y==tY && m==tM) this[tD-1].isToday = true;

}

//====================== 中文日期 =========================
function cDay(d){
   var s;

   switch (d) {
      case 10:
         s = '初十'; break;
      case 20:
         s = '二十'; break;
         break;
      case 30:
         s = '三十'; break;
         break;
      default :
         s = nStr2[Math.floor(d/10)];
         s += nStr1[d%10];
   }
   return(s);
}

///////////////////////////////////////////////////////////////////////////////
//！！！！Ajax的执行查询！！！！
//查询用的

///////////////////////////////////////////////////////////////////////////////
var cld;

function drawCld(SY,SM) {
   var i,sD,s,size;
   cld = new calendar(SY,SM);

   if(SY>1874 && SY<1909) yDisplay = '光绪' + (((SY-1874)==1)?'元':SY-1874)
   if(SY>1908 && SY<1912) yDisplay = '宣统' + (((SY-1908)==1)?'元':SY-1908)
   if(SY>1911 && SY<1950) yDisplay = '民国' + (((SY-1911)==1)?'元':SY-1911)
//   if(SY>1949) yDisplay = '共和国' + (((SY-1949)==1)?'元':SY-1949)

  // GZ.innerHTML = yDisplay +'年 农历' + cyclical(SY-1900+36) + '年 &nbsp;&nbsp;【'+Animals[(SY-4)%12]+'】';

   if(SY>1949) yDisplay = ''

   GZ.innerHTML = yDisplay +' 农历' + cyclical(SY-1900+36) + '年 &nbsp;&nbsp;【'+Animals[(SY-4)%12]+'】';

   YMBG.innerHTML = "&nbsp;" + SY + "&nbsp;&nbsp;&nbsp;" + monthName[SM];


   for(i=0;i<42;i++) {

      sObj=eval('SD'+ i);
      lObj=eval('LD'+ i);

      sObj.className = '';
	  lObj.style.cursor = 'pointer';
      sD = i - cld.firstWeek;

      if(sD>-1 && sD<cld.length) { //日期内
         sObj.innerHTML = sD+1;

         if(cld[sD].isToday) sObj.className = 'todyaColor'; //今日颜色

         sObj.style.color = cld[sD].color; //国定假日颜色

         if(cld[sD].lDay==1) //显示农历月
            lObj.innerHTML = '<b>'+(cld[sD].isLeap?'闰':'') + cld[sD].lMonth + '月' + (monthDays(cld[sD].lYear,cld[sD].lMonth)==29?'小':'大')+'</b>';
         else //显示农历日
            lObj.innerHTML = cDay(cld[sD].lDay);

         s=cld[sD].lunarFestival;
         if(s.length>0) { //农历节日
            if(s.length>6) s = s.substr(0, 4)+'…';
            s = s.fontcolor('red');
         }
         else { //国历节日
            s=cld[sD].solarFestival;
            if(s.length>0) {
               size = (s.charCodeAt(0)>0 && s.charCodeAt(0)<128)?8:4;
               if(s.length>size+2) s = s.substr(0, size)+'…';
               s = s.fontcolor('blue');
            }
            else { //廿四节气
               s=cld[sD].solarTerms;
               if(s.length>0) s = s.fontcolor('limegreen');
            }
         }
         if(s.length>0) lObj.innerHTML = s;

      }
      else { //非日期
         sObj.innerHTML = '';
         lObj.innerHTML = '';
      }
   }
}

function changeLong()
{
var y,m,ly,lm,id,im,iy,yangy,yangm,deltm,miny,tt;
CLD.SY.selectedIndex=CLD.D1.selectedIndex;
CLD.SM.selectedIndex=CLD.D2.selectedIndex;
yangm=0;yangy=0;
tt=true;
while (tt)
 {
 yangm=0;yangy=0;
   changeCld();
     for(i=0;i<42;i++)
     {
        sD = i - cld.firstWeek;
        if(sD>-1 && sD<cld.length)
        { //日期内
         if ((cld[sD].lMonth==CLD.D2.selectedIndex+1)&&(cld[sD].lYear==CLD.D1.selectedIndex+1900))
         {
     yangy=CLD.SY.selectedIndex+1900;  yangm=CLD.SM.selectedIndex ;
     tt=false;
     break;
     }
         }
     }
     if (!tt) break;
  
   pushBtm('MD');
   changeCld();

//   alert(CLD.SY.selectedIndex+" "+CLD.SM.selectedIndex);

     for(i=0;i<42;i++)
     {
        sD = i - cld.firstWeek;
        if(sD>-1 && sD<cld.length)
        { //日期内
         if ((cld[sD].lMonth==CLD.D2.selectedIndex+1)&&(cld[sD].lYear==CLD.D1.selectedIndex+1900))
         {
     yangy=CLD.SY.selectedIndex+1900;  yangm=CLD.SM.selectedIndex ;
     tt=false;
     break;
     }
         }
     }
   break;  
 }
//  alert(yangy+" "+yangm);
 

//CLD.SY.selectedIndex=yangy;//-1900;
//pushBtm('YU');
//pushBtm('YD');
CLD.SM.selectedIndex=yangm;
pushBtm('MD');
pushBtm('MU');
  
   
  
}
//changeLong end

function changeCld() {
   var y,m;
   y=CLD.SY.selectedIndex+1900;
   m=CLD.SM.selectedIndex;
   drawCld(y,m);
}

function pushBtm(K) {
   switch (K){
      case 'YU' :
         if(CLD.SY.selectedIndex>0) CLD.SY.selectedIndex--;
         break;
      case 'YD' :
         if(CLD.SY.selectedIndex<149) CLD.SY.selectedIndex++;
         break;
      case 'MU' :
         if(CLD.SM.selectedIndex>0) {
            CLD.SM.selectedIndex--;
         }
         else {
            CLD.SM.selectedIndex=11;
            if(CLD.SY.selectedIndex>0) CLD.SY.selectedIndex--;
         }
         break;
      case 'MD' :
         if(CLD.SM.selectedIndex<11) {
            CLD.SM.selectedIndex++;
         }
         else {
            CLD.SM.selectedIndex=0;
            if(CLD.SY.selectedIndex<149) CLD.SY.selectedIndex++;
         }
         break;
      default :
         CLD.SY.selectedIndex=tY-1900;
         CLD.SM.selectedIndex=tM;
   }
   changeCld();
}



var Today = new Date();
var tY = Today.getFullYear();
var tM = Today.getMonth();
var tD = Today.getDate();
//////////////////////////////////////////////////////////////////////////////

var width = "130";
var offsetx = 2;
var offsety = 16;

var x = 0;
var y = 0;
var snow = 0;
var sw = 0;
var cnt = 0;

var dStyle;
document.onmousemove = mEvn;

//显示详细日期资料
function mOvr(v) {
   var s,festival;
   
   var sObj=eval('SD'+ v);
   
   var d=sObj.innerHTML-1;

      //sYear,sMonth,sDay,week,
      //lYear,lMonth,lDay,isLeap,
      //cYear,cMonth,cDay

   if(sObj.innerHTML!='') {

      sObj.style.cursor = 'pointer';
	  lObj.style.cursor = 'pointer';
      if(cld[d].solarTerms == '' && cld[d].solarFestival == '' && cld[d].lunarFestival == '')
         festival = '';
      else
         festival = '<TABLE WIDTH=100% BORDER=0 CELLPADDING=2 CELLSPACING=0 BGCOLOR="#ccfffd"><TR><TD>'+
         '<FONT COLOR="#000000" STYLE="font-size:9pt;">'+cld[d].solarTerms + ' ' + cld[d].solarFestival + ' ' + cld[d].lunarFestival+'</FONT></TD>'+
         '</TR></TABLE>';

      s= '<TABLE WIDTH="130" BORDER=0 CELLPADDING="2" CELLSPACING=0 BGCOLOR="#0606fc"><TR><TD>' +
         '<TABLE WIDTH=100% BORDER=0 CELLPADDING=0 CELLSPACING=0><TR><TD ALIGN="right"><FONT COLOR="#ffffff" STYLE="font-size:9pt;">'+
         cld[d].sYear+' 年 '+cld[d].sMonth+' 月 '+cld[d].sDay+' 日<br>星期'+cld[d].week+'<br>'+
         '<font color="violet">农历'+(cld[d].isLeap?'闰 ':' ')+cld[d].lMonth+' 月 '+cld[d].lDay+' 日</font><br>'+
         '<font color="yellow">'+cld[d].cYear+'年 '+cld[d].cMonth+'月 '+cld[d].cDay + '日</font>'+
         '</FONT></TD></TR></TABLE>'+ festival +'</TD></TR></TABLE>';


      document.all["detail"].innerHTML = s;

    if (snow == 0) {
         dStyle.left = x+offsetx-(width/2) ;
         dStyle.top = y+offsety ;
     dStyle.visibility = "visible";
     snow = 1;
    }
 }
}
//产生日期
function createDate(gNum)
{
 	var sObj=eval('SD'+ gNum);
   	var d=sObj.innerHTML-1;
	var myDate=" ";
	myDate = cld[d].sYear +"n"+ cld[d].sMonth+"y"+cld[d].sDay;
	//alert(myDate);
	window.location.href("/OA/canlenderSearch.do?date="+myDate);
	return myDate
}

//产生月份
function createMonth()
{
	var myDate=" ";
	var yearSelected = CLD.SY.selectedIndex + 1900;
	var monthSelected = CLD.SM.selectedIndex + 1;
	myDate = yearSelected+"n"+monthSelected+"y";
	//alert(myDate);
	window.location.href("/OA/monthSearch.do?date="+myDate);
	return myDate
}

//清除详细日期资料
function mOut() {
 if ( cnt >= 1 ) { sw = 0 }
 if ( sw == 0 ) { snow = 0; dStyle.visibility = "hidden";}
 else cnt++;
}

//取得位置
function mEvn() {
   x=event.x;
   y=event.y;
 if (document.body.scrollLeft)
    {x=event.x+document.body.scrollLeft; y=event.y+document.body.scrollTop;}
 if (snow){
      dStyle.left = x+offsetx-(width/2);
      dStyle.top = y+offsety;
 }
}

///////////////////////////////////////////////////////////////////////////


function tick() {
   var today
   today = new Date()
   Clock.innerHTML = today.toLocaleString().replace(/(年|月)/g, "/").replace(/日/, "");
//   Clock.innerHTML = TimeAdd(today.toGMTString(), CLD.TZ.value)
   window.setTimeout("tick()", 1000);
}

function setCookie(name, value) {
 var today = new Date()
 var expires = new Date()
 expires.setTime(today.getTime() + 1000*60*60*24*365)
 document.cookie = name + "=" + escape(value) + "; expires=" + expires.toGMTString()
}

function getCookie(Name) {
   var search = Name + "="
   if(document.cookie.length > 0) {
      offset = document.cookie.indexOf(search)
      if(offset != -1) {
         offset += search.length
         end = document.cookie.indexOf(";", offset)
         if(end == -1) end = document.cookie.length
         return unescape(document.cookie.substring(offset, end))
      }
      else return ""
   }
}

/////////////////////////////////////////////////////////

function initial() {
   dStyle = detail.style;
   CLD.SY.selectedIndex=tY-1900;
   CLD.SM.selectedIndex=tM;
   drawCld(tY,tM);

//   CLD.TZ.selectedIndex=getCookie("TZ");
//   changeTZ();
   tick();
}
 
 

//-->
</script>
	
<meta content="Microsoft FrontPage 6.0" name=GENERATOR/>
<link type="text/css" rel="stylesheet" rev="stylesheet" href="../css/general_style.css"/>
	<link type="text/css" rel="stylesheet" rev="stylesheet" href="../css/dtree.css"/>
	<link href="favicon.ico" rel="shortcut icon" />
	<script  type="text/javascript" src="./js/menu.js"></script>
	<script type="text/javascript" src="./js/tree.js"></script>
	<script type="text/javascript" src="./js/head.js"></script>
</head>
<body onload="initial();">

<script language=JavaScript><!--
   if(navigator.appName == "Netscape" || parseInt(navigator.appVersion) < 4)
   document.write("<h1>你的浏览器无法执行此程序。</h1>此程序在 IE4 以后的版本才能执行!!")
--></script>

<div id="head">
  		<a href="<%=request.getContextPath()%>/Home.do" style="width:140px;">
  			<img id="headPicture" alt="转到首页" style="top: 0px; left: 20px; width: 140px; height: 120px; position:relative; border: 0px navy none;" src="./images/headHover2.jpg" onmouseover="changePicture();" onmouseout="backPicture();"></img>
  		</a>
  	</div>
	<div id="nav">
		<jsp:include page="../menu.jsp"></jsp:include>
	</div>
	<div id="content" style="font-size:13px;">
		<div style="color: #FFFFFF; padding-top: 6px;">
			<img alt="箭头" src="./images/arrow.gif" style=" position:relative; top: 3px; left: 6px;"/>
			<strong style="margin-left: 9px;">当前位置->日历查询</strong>
		</div>
		<hr class="line"/>
			
		<div class="left" id="leftPanel">
			<jsp:include page="../menu/workMenu.jsp"></jsp:include>
		</div>

		<div id="middleButton">
			<a id="middleLink" style="height: 40px; width: 10px; cursor: pointer;" onclick="change();">
				<img style=" background-image:url('./images/middleButtonTiny1.jpg');  margin-top: 140px; border-style:none; width: 10px; height: 40px;" src="../images/middleButtonTiny1.jpg"/>
				<span class="popBox">单击这里打开/折叠左侧树状视图</span>
			</a>
		</div>


<div class="right" id="rightPanel">
  
    		
    		
<div id=detail style="position: absolute;"></div>



<form name=CLD>
<div align="center">
<table width="100%" border="0" cellspacing="0"  style="background-color: #d4d4d2; height: auto;" >

  <tr style="width: 100%;">

    <td align="center" width="100%">
  		<table style="height: 250px;" width="100%">
	  	<tr>
	  		<td>
			  <table class="table" width="100%" cellspacing="0" style="margin:0px auto; ">
				    <tr>
					    <td>
						      <p align="center">
							      本地计算机时间:
							      	<br/>
							      <font color=#000080 face=ARIAL id=Clock size=3></font>
						     </p>
					    </td>
				    </tr>
			   </table>
			</td>
	  	</tr>
	  	<tr>
	  		<td>
		      <table class="table" width="100%" cellspacing="0">
		      <thead style="text-align:center;font-size: large;color: black;background-color: #dbdbda;font-family: 方正姚体;">
		      	按日历查看日程安排
		      </thead>
		            <tr>
		            	<td class="tableColumeHead">公历</td>
		            	<td class="tableColumeHead">年</td>
		            	<td>
			            	<select name=SY style="width: 100px;" onchange=changeCld()>
					            <script language=JavaScript><!--
					            for(i=1900;i<2050;i++) document.write('<option>'+i)
					            //--></script>
			            	</select>
		            	</td>
		            	<td class="tableColumeHead">月</td>
		            	<td>
		            		<select name=SM style="width: 100%;" onchange=changeCld()>
					            <script language=JavaScript><!--
					            for(i=1;i<13;i++) document.write('<option>'+i)
					            //--></script>
		            
		        			</select>
		            	</td>
		            </tr>
		            <tr>
		            	<td class="tableColumeHead">阴历</td>
		            	<td class="tableColumeHead" style="width: 50px;">年</td>
		            	<td>
			            	<select name=D1 style="width: 100px;" onchange=changeLong()>
					            <script language=JavaScript><!--
					            for(i=1900;i<2050;i++) document.write('<option>'+i)
					            //--></script>
			      			</select>
		      			</td>
		            	<td class="tableColumeHead" >月</td>
		            	<td>
			            	<select name=D2 style="width: 100%;" onchange=changeLong()>
					            <script language=JavaScript><!--
					            for(i=1;i<13;i++) document.write('<option>'+i)
					            //--></script>
			        		</select>
		        		</td>
		            </tr>
		            <tr>
		            	<td>&nbsp;</td>
		            	
		            	<td colspan="2">
		            		<button onClick="pushBtm('YU')" class="shortButton" >
		            			上一年
		            		</button>
			            		
			      			<button onClick="pushBtm('YD')" class="shortButton">
			      				下一年
			      			</button>
				      			
		            	</td>
		            	
		            	<td colspan="2">
		            		<button onClick="pushBtm('MU')" class="shortButton" >上个月</button>
			      			<button onClick="pushBtm('')" class="shortButton" ><strong style="color: navy;">当月</strong></button>
			      			<button onClick="pushBtm('MD')" class="shortButton" >下个月</button>
			      		</td>
			      		
		            </tr>
		            <tr>
		            	<td colspan="5">
		            		<button onClick="createMonth()" class="longerButton" style="margin-right: 10px;">按公历年月查询日程</button>
		            	</td>
		            	
		            </tr>
		           
		      </table>
      		</td>
    	</tr>
    	</table>
    </td>
    <td style="height: 220px;" style=" width: 320px;" align="right">
 		<div align="center" style="background-color: #faf67c; width: 320px;">
 			<strong>
        		<font id=YMBG >&nbsp;</font>
        	</strong>
     	</div>

     	<div align="center" style=" width: 320px;">

	  
	  <!-- 用于显示右边的一个月的日期的所有天的Table -->
      <table cellspacing="0" width="320px" style="background-color:#fefccc;">
 
        <tr bgcolor="#fefccc">
          <td height="24" colspan="7" align="center">
          	<font color=#000000 face=宋体 id=GZ size=4></font>
          </td>
        </tr>
        <tr align="center" >
          <td  height="1" align="center" bgcolor="#faf67c"><font size="2">日</font></td>
          <td  height="1" align="center" bgcolor="#faf67c"><font size="2">一</font></td>
          <td  height="1" align="center" bgcolor="#faf67c"><font size="2">二</font></td>
          <td  height="1" align="center" bgcolor="#faf67c"><font size="2">三</font></td>
          <td  height="1" align="center" bgcolor="#faf67c"><font size="2">四</font></td>
          <td  height="1" align="center" bgcolor="#faf67c"><font size="2">五</font></td>
          <td  height="1" align="center" bgcolor="#faf67c"><font size="2">六</font></td>
        </tr>
        
        <script language="javaScript"><!--
            var gNum;
			//var mydate;
			<!-- 所有的行为6行 -->
            for(i=0;i<6;i++)
			{
               document.write('<tr align=center>')
			   <!-- 一个星期是七天 -->
               for(j=0;j<7;j++)
			   {
                  gNum = i*7+j;
				  
                  document.write('<td id="GD' + gNum +'" onMouseOver="mOvr(' + gNum +')" onMouseOut="mOut()" onclick="createDate('+gNum+')"><font id="SD' + gNum +'" 				size=2 face="Arial Black"');
				  //mydate = createDate(gNum);
                  if(j == 0) document.write(' color=red')
                  if(j == 6)
                     if(i%2==1) document.write(' color=red')
                        else document.write(' color=green')
				 
				  //alert(myDate);
                  document.write(' TITLE="" > </font><br><font id="LD' + gNum + '" size=2 style="font-size:9pt"> </font></td>');
               }
               document.write('</tr>');
            }
            //--></script>
    
	</table>
		  <!-- 显示右边的一个月的日期的所有天的Table的结束的地方 -->

	      <c:if test="${requestScope.ExistArrange == 0 }">
	      	<script language="javascript">
	      		alert("该日期没有日程安排");
	      	</script>
	      </c:if>
      
	      <c:if test="${requestScope.MonthExistArrange == 0}">
	      	<script language="javascript">
	      		alert("该月没有日程安排");
	      	</script>
	      </c:if>
      </div>
   </td>
</tr>
</table>

</div>
</form>
</div>
</div>
<div id="bottom"></div>


</body>
</html>
