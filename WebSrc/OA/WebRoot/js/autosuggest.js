function AutoSuggestControl(oTextbox,oProvider)
{
	this.cur = -1;
	this.div = null;
	this.provider = oProvider;
	this.textbox = oTextbox;
	this.timeoutId = null;
	this.userText = oTextbox.value;
	this.init();
}


AutoSuggestControl.prototype.init=function()
{
	var oThis = this;
	
	this.textbox.onkeyup = function (oEvent)
	{
		if(!oEvent)
		{
			oEvent = window.event;
		}
		oThis.handleKeyUp(oEvent);
	};
	this.textbox.onkeydown = function (oEvent)
	{
		if(!oEvent)
		{
			oEvent = window.event;
		}
		oThis.handleKeyDown(oEvent);
	};
	this.textbox.onblur = function()
	{
		oThis.hideSuggestions();
	};
	this.createDropDown();
};

AutoSuggestControl.prototype.selectRange=function(iStart,iEnd)
{
	if(this.textbox.createTextRange)
	{
		var oRange=this.textbox.createTextRange();
		oRange.moveStart("character",iStart);
		oRange.moveEnd("character",iEnd-this.textbox.value.length);
		oRange.select();
	}
	else if(this.textbox.setSelectionRange)
	{
		this.textbox.setSelectionRange(iStart,iEnd);
	}
};

AutoSuggestControl.prototype.typeAhead = function(sSuggestion)
{
	if(this.textbox.createTextRange || this.textbox.setSelectionRange)
	{
		var iLen = this.textbox.value.length;
		this.textbox.value= sSuggestion;
		this.selectRange(iLen,sSuggestion.length);
	}
};

AutoSuggestControl.prototype.autosuggest = function(aSuggestions,bTypeAhead)
{
	this.cur= -1;
	if(aSuggestions.length > 0)
	{
		/*if(bTypeAhead)
		{
			var suggest = aSuggestions[0].split("&");
			this.typeAhead(suggest[0]);
		}*/
		
		this.showSuggestions(this.getDetails(aSuggestions));
	}
	else
	{
		this.hideSuggestions();
	}
};

AutoSuggestControl.prototype.hideSuggestions = function ()
{
	this.div.style.visibility = "hidden";
};

AutoSuggestControl.prototype.highlightSuggestion = function (oSuggestionNode)
{
	for(var i = 0; i < this.div.childNodes.length;i++)
	{
		var oNode = this.div.childNodes[i];
		if(oNode == oSuggestionNode)
		{
			oNode.className = "current";
		}
		else if(oNode.className =="current")
		{
			oNode.className = "";
		}
	}
};

AutoSuggestControl.prototype.handleKeyUp = function(oEvent)
{
	var iKeyCode = oEvent.keyCode;
	var oThis = this;
	
	this.userText = this.textbox.value;
	
	clearTimeout(this.timeoutId);
	
	if(iKeyCode == 8 || iKeyCode == 46)
	{
		this.timeoutId = setTimeout(function(){
			oThis.provider.requestSuggestions(oThis,false);
		},250);
	}
	else if((iKeyCode !=16 && iKeyCode < 32) || (iKeyCode >= 33 && iKeyCode < 46) || (iKeyCode >=112 && iKeyCode <= 123))
	{
		//do nothing
	}
	else
	{
		this.timeoutId = setTimeout(function(){
			oThis.provider.requestSuggestions(oThis,true);
		},250);
	}
};

AutoSuggestControl.prototype.handleKeyDown = function(oEvent)
{
	switch(oEvent.keyCode)
	{
		case 38:
			this.goToSuggestion(-1);
			break;
		case 40:
			this.goToSuggestion(1);
			break;
		case 27:
			this.textbox.value= this.userText;
			this.selectRange(this.userText.length,0);
			break;
		case 13:
			this.hideSuggestions();
			oEvent.returnValue = false;
			if(oEvent.preventDefault)
			{
				oEvent.preventDefault();
			}
			break;
	}
};

AutoSuggestControl.prototype.goToSuggestion = function(iDiff)
{
	var cSuggestionNodes = this.div.childNodes;
	
	if(cSuggestionNodes.length > 0)
	{
		var oNode = null;
		if(iDiff > 0)
		{
			if(this.cur < cSuggestionNodes.length-1)
			{
				oNode = cSuggestionNodes[++this.cur];
			}
		}
		else
		{
			if(this.cur > 0)
			{
				oNode = cSuggestionNodes[--this.cur];
			}
		}
		if(oNode)
		{
			this.highlightSuggestion(oNode);
			this.textbox.value = oNode.firstChild.nodeValue.split("--")[0];
		}
	}
};		

AutoSuggestControl.prototype.showSuggestions = function (aSuggestions)
{
	var oDiv = null;
	var textNode;
	this.div.innerHTML = "";
	for(var i = 0;i<aSuggestions.length;i++)
	{
		oDiv = document.createElement("div");
		oDiv.appendChild(document.createTextNode(aSuggestions[i]));
		this.div.appendChild(oDiv);
	}
	this.div.style.left = this.getLeft() + "px";
	this.div.style.top = (this.getTop() + this.textbox.offsetHeight) + "px";
	this.div.style.visibility = "visible";
};

AutoSuggestControl.prototype.getDetails = function(aSuggestions)
{
	var len=aSuggestions.length;
	var details=new Array();
	for(var i=0;i<len;i++)
	{
		var reg=/&/g;
		details[i] = aSuggestions[i].replace(reg,"--");
	}
	return details;	
}

AutoSuggestControl.prototype.getLeft = function()
{
	var oNode = this.textbox;
	var iLeft = 0;
	
	while(oNode != document.body)
	{
		iLeft += oNode.offsetLeft;
		oNode = oNode.offsetParent;
	}
	return iLeft;
};

AutoSuggestControl.prototype.getTop = function()
{
	var oNode = this.textbox;
	var iTop = 0;
	while(oNode != document.body)
	{
		iTop += oNode.offsetTop;
		oNode = oNode.offsetParent;
	}
	return iTop;
};

AutoSuggestControl.prototype.createDropDown = function()
{
	this.div = document.createElement("div");
	this.div.className = "suggestions";
	this.div.style.visibility = "hidden";
	this.div.style.width = this.textbox.offsetWidth;
	document.body.appendChild(this.div);
	
	var oThis = this;
	
	this.div.onmousedown = this.div.onmouseup =this.div.onmouseover = function(oEvent)
	{
		oEvent = oEvent || window.event;
		oTarget = oEvent.target || oEvent.srcElement;
		
		if(oEvent.type=="mousedown")
		{
			oThis.textbox.value = oTarget.firstChild.nodeValue.split("--")[0];
			oThis.hideSuggestions();
		}
		else if(oEvent.type == "mouseover")
		{
			oThis.highlightSuggestion(oTarget);
		}
		else
		{
			oThis.textbox.focus();
		}
	};
};

function SuggestionProvider(textbox)
{
	this.messageDiv = document.getElementById("messageDiv");
	this.oTextbox=textbox;
	this.xhr = this.createRequest();
}

SuggestionProvider.prototype.createRequest = function()
{
	if(typeof XMLHttpRequest != "undefined")
	{
		return new XMLHttpRequest();
	}
	else if(window.ActiveXObject)
	{
		var aVersions = [ "MSXML2.XMLHttp.6.0","MSXML2.XMLHttp.3.0"];
		for(var i=0; i < aVersions.length;i++)
		{
			try{
				var oXHR = new ActiveXObject(aVersions[i]);
				return oXHR;
			}
			catch(oError)
			{
				//do nothing
			}
		}
	}
	throw new Error("XMLHttp object could not be created.");
};

SuggestionProvider.prototype.requestSuggestions = function(oAutoSuggestControl,bTypeAhead)
{
	var oXHR = this.xhr;
	var text=this.oTextbox.value;
	var oMessageDiv=this.messageDiv;
	var oThis=this;
	var typeRadio = document.getElementById("type");
	if(oXHR.readyState != 0)
	{
		oXHR.abort();
	}
	
	var data = oAutoSuggestControl.userText;
	if(data==null ||data=="")
		return;
	if( typeRadio == null ){
		oXHR.open("get","servlet/ajaxServlet?id="+data+"&type=login",true);
	}
	else if(!typeRadio.checked){
		oXHR.open("get","servlet/ajaxServlet?id="+data+"&type=id",true);
	}
	else{
	    oXHR.open("get","servlet/ajaxServlet?id="+data+"&type=name",true);
	}
	
	oXHR.setRequestHeader("Content-type","text/html");
	oXHR.onreadystatechange = function()
	{
		if(oXHR.readyState == 4)
		{
			if(oXHR.status == 200 || oXHR.status == 304)
			{
				var aSuggestions = oXHR.responseText;
				if(aSuggestions==null || aSuggestions=="")
				{
					oAutoSuggestControl.hideSuggestions();
					return;
				}
				suggestions=aSuggestions.split("|");
				oAutoSuggestControl.autosuggest(suggestions,bTypeAhead);
			}
		}
	};
	oXHR.send(null);
};	
		