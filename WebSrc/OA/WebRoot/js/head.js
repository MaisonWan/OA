function changePicture(){
	headPicture.src = "./images/headHover1.jpg";
		
}
function backPicture(){
	headPicture.src = "./images/headHover2.jpg";	
}
var flag = 0;
function change(){

	flag = flag + 1;
	if(flag%2 == 0){
		show();
		document.getElementById('middleButtonTiny').style.src = './images/middleButtonTiny1.jpg';
	}
	else{
		hide();
		document.getElementById('middleButtonTiny').style.src = './images/middleButtonTiny2.jpg';
	}
	



}		
		
function show(){




	document.getElementById('leftPanel').style.width = '200px';
	document.getElementById('rightPanel').style.width = '750px';
	document.getElementById('leftPanel').style.overflow = 'scroll';
	//if(document.getElementById('leftPanel').offsetWidth >= 200){
		//document.getElementById('middleButton').style.background-image = '../images/middleButtonTiny2.jpg';
		//return;
	//}
	//else{
		//document.getElementById('leftPanel').style.width = (document.getElementById('leftPanel').offsetWidth + 200) + "px";
		//document.getElementById('rightPanel').style.width = (document.getElementById('rightPanel').offsetWidth - 200) + "px";
		
		//if(document.getElementById('leftPanel').offsetWidth < 200){
			//window.setTimeout(show,1);
		//}

	//}
}
		
		
function hide(){


	document.getElementById('leftPanel').style.width = '0px';
	document.getElementById('leftPanel').style.overflow = 'hidden';
	document.getElementById('rightPanel').style.width = '950px';
	


	//if(document.getElementById('leftPanel').offsetWidth <= 0){
		//return;
	//}
	//else{
		//document.getElementById('leftPanel').style.width = (document.getElementById('leftPanel').offsetWidth - 200) + "px";
		//document.getElementById('rightPanel').style.width = (document.getElementById('rightPanel').offsetWidth + 200) + "px";
		////document.getElementById('rightPanel').style.margin-left = '0px';
		//if(document.getElementById('leftPanel').offsetWidth >= 0){
			//window.setTimeout(hide,1);
		//}
	//}
		           
		    
}
