// Cross-Browser Rich Text Editor
// http://www.kevinroth.com/rte/demo.htm
// Written by Kevin Roth (kevin@NOSPAMkevinroth.com - remove NOSPAM)
// Visit the support forums at http://www.kevinroth.com/forums/index.php?c=2
// This code is public domain. Redistribution and use of this code, with or without modification, is permitted.

//init variables
var isRichText = false;
var rng;
var currentRTE;
var allRTEs = "";

var isIE;
var isGecko;
var isSafari;
var isKonqueror;

var imagesPath;
var includesPath;
var cssFile;
var generateXHTML;

var lang = "zh_cn";
var encoding = "UTF-8";


function initRTE(imgPath, incPath, css, genXHTML) {
	//set browser vars
	var ua = navigator.userAgent.toLowerCase();
	isIE = ((ua.indexOf("msie") != -1) && (ua.indexOf("opera") == -1) && (ua.indexOf("webtv") == -1)); 
	isGecko = (ua.indexOf("gecko") != -1);
	isSafari = (ua.indexOf("safari") != -1);
	isKonqueror = (ua.indexOf("konqueror") != -1);
	
	generateXHTML = genXHTML;
	
	//check to see if designMode mode is available
	//Safari/Konqueror think they are designMode capable even though they are not
	if (document.getElementById && document.designMode && !isSafari && !isKonqueror) {
		isRichText = true;
	}
	
	if (isIE) {
		document.onmouseover = raiseButton;
		document.onmouseout  = normalButton;
		document.onmousedown = lowerButton;
		document.onmouseup   = raiseButton;
	}
	
	//set paths vars
	imagesPath = imgPath;
	includesPath = incPath;
	cssFile = css;
	
	if (isRichText) document.writeln('<style type="text/css">@import "' + includesPath + 'rte.css";</style>');
	
	//for testing standard textarea, uncomment the following line
	//isRichText = false;
}

function writeRichText(rte, html, width, height, buttons, readOnly) {
	if (isRichText) {
		if (allRTEs.length > 0) allRTEs += ";";
		allRTEs += rte;
		
		if (readOnly) buttons = false;
		
		//adjust minimum table widths
		if (isIE) {
			if (buttons && (width < 440)) width = 440;
			var tablewidth = width;
		} else {
			if (buttons && (width < 440)) width = 440;
			var tablewidth = width + 4;
		}
		
		document.writeln('<div class="rteDiv">');
		if (buttons == true) {
			document.writeln('<table class="rteBack" cellpadding=0 cellspacing=0 id="Buttons1_' + rte + '" width="' + tablewidth + '">');
			document.writeln('	<tr>');
			document.writeln('		<td>');
			document.writeln('			<select id="formatblock_' + rte + '" onchange="selectFont(\'' + rte + '\', this.id);">');
			document.writeln('				<option value="">-- \u6837\u5f0f --</option>');
			document.writeln('				<option value="<p>">\u6bb5\u843d &lt;p&gt;</option>');
			document.writeln('				<option value="<h1>">\u6807\u9898 1 &lt;h1&gt;</option>');
			document.writeln('				<option value="<h2>">\u6807\u9898 2 &lt;h2&gt;</option>');
			document.writeln('				<option value="<h3>">\u6807\u9898 3 &lt;h3&gt;</option>');
			document.writeln('				<option value="<h4>">\u6807\u9898 4 &lt;h4&gt;</option>');
			document.writeln('				<option value="<h5>">\u6807\u9898 5 &lt;h5&gt;</option>');
			document.writeln('				<option value="<h6>">\u6807\u9898 6 &lt;h6&gt;</option>');
			document.writeln('				<option value="<address>">\u5730\u5740 &lt;ADDR&gt;</option>');
			document.writeln('				<option value="<pre>">\u683c\u5f0f\u5316 &lt;pre&gt;</option>');
			document.writeln('			</select>');
			document.writeln('		</td>');
			document.writeln('		<td>');
			document.writeln('			<select id="fontname_' + rte + '" onchange="selectFont(\'' + rte + '\', this.id)">');
			document.writeln('				<option value="Font" selected>-- \u5b57\u4f53 --</option>');
			document.writeln('				<option value="\u5b8b\u4f53">\u5b8b\u4f53</option>');
			document.writeln('				<option value="Arial, Helvetica, sans-serif">Arial</option>');
			document.writeln('				<option value="Courier New, Courier, mono">Courier New</option>');
			document.writeln('				<option value="Times New Roman, Times, serif">Times New Roman</option>');
			document.writeln('				<option value="Verdana, Arial, Helvetica, sans-serif">Verdana</option>');
			document.writeln('			</select>');
			document.writeln('		</td>');
			document.writeln('		<td>');
			document.writeln('			<select unselectable="on" id="fontsize_' + rte + '" onchange="selectFont(\'' + rte + '\', this.id);">');
			document.writeln('				<option value="Size">-- \u5b57\u4f53\u5927\u5c0f --</option>');
			document.writeln('				<option value="1">1</option>');
			document.writeln('				<option value="2">2</option>');
			document.writeln('				<option value="3">3</option>');
			document.writeln('				<option value="4">4</option>');
			document.writeln('				<option value="5">5</option>');
			document.writeln('				<option value="6">6</option>');
			document.writeln('				<option value="7">7</option>');
			document.writeln('			</select>');
			document.writeln('		</td>');
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'cut.gif" width="20" height="20" alt="\u526a\u5207" title="\u526a\u5207" onClick="rteCommand(\'' + rte + '\', \'cut\')"></td>');
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'copy.gif" width="20" height="20" alt="\u590d\u5236" title="\u590d\u5236" onClick="rteCommand(\'' + rte + '\', \'copy\')"></td>');
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'paste.gif" width="20" height="20" alt="\u7c98\u8d34" title="\u7c98\u8d34" onClick="rteCommand(\'' + rte + '\', \'paste\')"></td>');
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'undo.gif" width="20" height="20" alt="\u64a4\u6d88" title="\u64a4\u6d88" onClick="rteCommand(\'' + rte + '\', \'undo\')"></td>');
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'redo.gif" width="20" height="20" alt="\u91cd\u505a" title="\u91cd\u505a" onClick="rteCommand(\'' + rte + '\', \'redo\')"></td>');
			//document.writeln('		<td width="100%">');
			//document.writeln('		</td>');
			document.writeln('	</tr>');
			document.writeln('</table>');
			document.writeln('<table class="rteBack" cellpadding="0" cellspacing="0" id="Buttons2_' + rte + '" width="' + tablewidth + '">');
			document.writeln('	<tr>');
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'bold.gif" width="20" height="20" alt="\u52a0\u7c97" title="\u52a0\u7c97" onClick="rteCommand(\'' + rte + '\', \'bold\', \'\')"></td>');
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'italic.gif" width="20" height="20" alt="\u659c\u4f53" title="\u659c\u4f53" onClick="rteCommand(\'' + rte + '\', \'italic\', \'\')"></td>');
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'underline.gif" width="20" height="20" alt="\u4e0b\u5212\u7ebf" title="\u4e0b\u5212\u7ebf" onClick="rteCommand(\'' + rte + '\', \'underline\', \'\')"></td>');
			document.writeln('		<td><img class="rteVertSep" src="' + imagesPath + 'blackdot.gif" width="1" height="20" border="0" alt=""></td>');
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'left_just.gif" width="20" height="20" alt="\u5de6\u5bf9\u9f50" title="\u5de6\u5bf9\u9f50" onClick="rteCommand(\'' + rte + '\', \'justifyleft\', \'\')"></td>');
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'centre.gif" width="20" height="20" alt="\u5c45\u4e2d" title="\u5c45\u4e2d" onClick="rteCommand(\'' + rte + '\', \'justifycenter\', \'\')"></td>');
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'right_just.gif" width="20" height="20" alt="\u53f3\u5bf9\u9f50" title="\u53f3\u5bf9\u9f50" onClick="rteCommand(\'' + rte + '\', \'justifyright\', \'\')"></td>');
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'justifyfull.gif" width="20" height="20" alt="\u4e24\u7aef\u5bf9\u9f50" title="\u4e24\u7aef\u5bf9\u9f50" onclick="rteCommand(\'' + rte + '\', \'justifyfull\', \'\')"></td>');
			document.writeln('		<td><img class="rteVertSep" src="' + imagesPath + 'blackdot.gif" width="1" height="20" border="0" alt=""></td>');
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'hr.gif" width="20" height="20" alt="\u6c34\u5e73\u7ebf" title="\u6c34\u5e73\u7ebf" onClick="rteCommand(\'' + rte + '\', \'inserthorizontalrule\', \'\')"></td>');
			document.writeln('		<td><img class="rteVertSep" src="' + imagesPath + 'blackdot.gif" width="1" height="20" border="0" alt=""></td>');
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'numbered_list.gif" width="20" height="20" alt="\u7f16\u53f7" title="\u7f16\u53f7" onClick="rteCommand(\'' + rte + '\', \'insertorderedlist\', \'\')"></td>');
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'list.gif" width="20" height="20" alt="\u9879\u76ee\u7b26\u53f7" title="\u9879\u76ee\u7b26\u53f7" onClick="rteCommand(\'' + rte + '\', \'insertunorderedlist\', \'\')"></td>');
			document.writeln('		<td><img class="rteVertSep" src="' + imagesPath + 'blackdot.gif" width="1" height="20" border="0" alt=""></td>');
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'outdent.gif" width="20" height="20" alt="\u51cf\u5c11\u7f29\u8fdb\u91cf" title="\u51cf\u5c11\u7f29\u8fdb\u91cf" onClick="rteCommand(\'' + rte + '\', \'outdent\', \'\')"></td>');
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'indent.gif" width="20" height="20" alt="\u589e\u52a0\u7f29\u8fdb\u91cf" title="\u589e\u52a0\u7f29\u8fdb\u91cf" onClick="rteCommand(\'' + rte + '\', \'indent\', \'\')"></td>');
			document.writeln('		<td><div id="forecolor_' + rte + '"><img class="rteImage" src="' + imagesPath + 'textcolor.gif" width="25" height="24" alt="\u5b57\u4f53\u989c\u8272" title="\u5b57\u4f53\u989c\u8272" onClick="dlgColorPalette(\'' + rte + '\', \'forecolor\', \'\')"></div></td>');
			document.writeln('		<td><img class="rteVertSep" src="' + imagesPath + 'blackdot.gif" width="1" height="20" border="0" alt=""></td>');
			document.writeln('		<td><img class="rteImage" src="' + imagesPath + 'image.gif" width="20" height="20" alt="\u63d2\u5165\u56fe\u50cf" title="\u63d2\u5165\u56fe\u50cf" onClick="addImage(\'' + rte + '\')"></td>');
			//document.writeln('		<td width="100%"></td>');
			document.writeln('	</tr>');
			document.writeln('</table>');
		}
		document.writeln('<iframe id="' + rte + '" name="' + rte + '" width="' + width + 'px" height="' + height + 'px" src="' + includesPath + 'blank.htm"></iframe>');
		if (!readOnly) document.writeln('<br /><input type="checkbox" id="chkSrc' + rte + '" onclick="toggleHTMLSrc(\'' + rte + '\',' + buttons + ');" />&nbsp;<label style="font-size:12px;" for="chkSrc' + rte + '">\u67e5\u770b\u6e90\u4ee3\u7801</label>');
		document.writeln('<iframe width="154" height="104" id="cp' + rte + '" src="' + includesPath + 'palette.htm" marginwidth="0" marginheight="0" scrolling="no" style="visibility:hidden; position: absolute;"></iframe>');
		document.writeln('<input type="hidden" id="hdn' + rte + '" name="' + rte + '" value="">');
		document.writeln('</div>');
		
		document.getElementById('hdn' + rte).value = html;
		enableDesignMode(rte, html, readOnly);
	} else {
		if (!readOnly) {
			document.writeln('<textarea name="' + rte + '" id="' + rte + '" style="width: ' + width + 'px; height: ' + height + 'px;">' + html + '</textarea>');
		} else {
			document.writeln('<textarea name="' + rte + '" id="' + rte + '" style="width: ' + width + 'px; height: ' + height + 'px;" readonly>' + html + '</textarea>');
		}
	}
}

function enableDesignMode(rte, html, readOnly) {
	var frameHtml = "<html id=\"" + rte + "\">\n";
	frameHtml += "<head>\n";
	//to reference your stylesheet, set href property below to your stylesheet path and uncomment
	if (cssFile.length > 0) {
		frameHtml += "<link media=\"all\" type=\"text/css\" href=\"" + cssFile + "\" rel=\"stylesheet\">\n";
	} else {
		frameHtml += "<style>\n";
		frameHtml += "body {\n";
		frameHtml += "	background: #FFFFFF;\n";
		frameHtml += "	margin: 0px;\n";
		frameHtml += "	padding: 0px;\n";
		frameHtml += "}\n";
		frameHtml += "</style>\n";
	}
	frameHtml += "</head>\n";
	frameHtml += "<body>\n";
	frameHtml += html + "\n";
	frameHtml += "</body>\n";
	frameHtml += "</html>";
	
	if (document.all) {
		var oRTE = frames[rte].document;
		oRTE.open();
		oRTE.write(frameHtml);
		oRTE.close();
		if (!readOnly) {
			oRTE.designMode = "On";
			frames[rte].document.attachEvent("onkeypress", function evt_ie_keypress(event) {ieKeyPress(event, rte);});
		}
	} else {
		try {
			if (!readOnly) document.getElementById(rte).contentDocument.designMode = "on";
			try {
				var oRTE = document.getElementById(rte).contentWindow.document;
				oRTE.open();
				oRTE.write(frameHtml);
				oRTE.close();
				if (isGecko && !readOnly) {
					//attach a keyboard handler for gecko browsers to make keyboard shortcuts work
					oRTE.addEventListener("keypress", geckoKeyPress, true);
				}
			} catch (e) {
				alert("\u8f7d\u5165\u6587\u4ef6\u51fa\u9519");
			}
		} catch (e) {
			//gecko may take some time to enable design mode.
			//Keep looping until able to set.
			if (isGecko) {
				setTimeout("enableDesignMode('" + rte + "', '" + html + "', " + readOnly + ");", 10);
			} else {
				return false;
			}
		}
	}
}

function updateRTE(rte) {
	if (!isRichText) return;
	
	//check for readOnly mode
	var readOnly = false;
	if (document.all) {
		if (frames[rte].document.designMode != "On") readOnly = true;
	} else {
		if (document.getElementById(rte).contentDocument.designMode != "on") readOnly = true;
	}
	
	if (isRichText && !readOnly) {
		//if viewing source, switch back to design view
		if (document.getElementById("chkSrc" + rte).checked) document.getElementById("chkSrc" + rte).click();
		setHiddenVal(rte);
	}
}

function setHiddenVal(rte) {
	//set hidden form field value for current rte
	var oHdnField = document.getElementById('hdn' + rte);
	
	//convert html output to xhtml (thanks Timothy Bell and Vyacheslav Smolin!)
	if (oHdnField.value == null) oHdnField.value = "";
	if (document.all) {
		if (generateXHTML) {
			oHdnField.value = get_xhtml(frames[rte].document.body, lang, encoding);
		} else {
			oHdnField.value = frames[rte].document.body.innerHTML;
		}
	} else {
		if (generateXHTML) {
			oHdnField.value = get_xhtml(document.getElementById(rte).contentWindow.document.body, lang, encoding);
		} else {
			oHdnField.value = document.getElementById(rte).contentWindow.document.body.innerHTML;
		}
	}
	
	//if there is no content (other than formatting) set value to nothing
	if (stripHTML(oHdnField.value.replace("&nbsp;", " ")) == "" &&
		oHdnField.value.toLowerCase().search("<hr") == -1 &&
		oHdnField.value.toLowerCase().search("<img") == -1) oHdnField.value = "";
}

function updateRTEs() {
	var vRTEs = allRTEs.split(";");
	for (var i = 0; i < vRTEs.length; i++) {
		updateRTE(vRTEs[i]);
	}
}

function rteCommand(rte, command, option) {
	//function to perform command
	var oRTE;
	if (document.all) {
		oRTE = frames[rte];
	} else {
		oRTE = document.getElementById(rte).contentWindow;
	}
	
	try {
		oRTE.focus();
	  	oRTE.document.execCommand(command, false, option);
		oRTE.focus();
	} catch (e) {
//		alert(e);
//		setTimeout("rteCommand('" + rte + "', '" + command + "', '" + option + "');", 10);
	}
}

function toggleHTMLSrc(rte, buttons) {
	//contributed by Bob Hutzel (thanks Bob!)
	var oHdnField = document.getElementById('hdn' + rte);
	
	if (document.getElementById("chkSrc" + rte).checked) {
		//we are checking the box
		if (buttons) {
			showHideElement("Buttons1_" + rte, "hide");
			showHideElement("Buttons2_" + rte, "hide");
		}
		setHiddenVal(rte);
		if (document.all) {
			frames[rte].document.body.innerText = oHdnField.value;
		} else {
			var oRTE = document.getElementById(rte).contentWindow.document;
			var htmlSrc = oRTE.createTextNode(oHdnField.value);
			oRTE.body.innerHTML = "";
			oRTE.body.appendChild(htmlSrc);
		}
	} else {
		//we are unchecking the box
		if (buttons) {
			showHideElement("Buttons1_" + rte, "show");
			showHideElement("Buttons2_" + rte, "show");
		}
		if (document.all) {
			//fix for IE
			var output = escape(frames[rte].document.body.innerText);
			output = output.replace("%3CP%3E%0D%0A%3CHR%3E", "%3CHR%3E");
			output = output.replace("%3CHR%3E%0D%0A%3C/P%3E", "%3CHR%3E");
			frames[rte].document.body.innerHTML = unescape(output);
		} else {
			var oRTE = document.getElementById(rte).contentWindow.document;
			var htmlSrc = oRTE.body.ownerDocument.createRange();
			htmlSrc.selectNodeContents(oRTE.body);
			oRTE.body.innerHTML = htmlSrc.toString();
		}
	}
}

function dlgColorPalette(rte, command) {
	//function to display or hide color palettes
	setRange(rte);
	
	//get dialog position
	var oDialog = document.getElementById('cp' + rte);
	var buttonElement = document.getElementById(command + '_' + rte);
	var iLeftPos = getOffsetLeft(buttonElement);
	var iTopPos = getOffsetTop(buttonElement) + (buttonElement.offsetHeight + 4);
	oDialog.style.left = (iLeftPos) + "px";
	oDialog.style.top = (iTopPos) + "px";
	
	if ((command == parent.command) && (rte == currentRTE)) {
		//if current command dialog is currently open, close it
		if (oDialog.style.visibility == "hidden") {
			showHideElement(oDialog, 'show');
		} else {
			showHideElement(oDialog, 'hide');
		}
	} else {
		//if opening a new dialog, close all others
		var vRTEs = allRTEs.split(";");
		for (var i = 0; i < vRTEs.length; i++) {
			showHideElement('cp' + vRTEs[i], 'hide');
		}
		showHideElement(oDialog, 'show');
	}
	
	//save current values
	parent.command = command;
	currentRTE = rte;
}

function dlgInsertTable(rte, command) {
	//function to open/close insert table dialog
	//save current values
	parent.command = command;
	currentRTE = rte;
	InsertTable = popUpWin(includesPath + 'insert_table.htm', 'InsertTable', 360, 180, '');
}

function dlgInsertLink(rte, command) {
	//function to open/close insert table dialog
	//save current values
	parent.command = command;
	currentRTE = rte;
	InsertLink = popUpWin(includesPath + 'insert_link.htm', 'InsertLink', 360, 180, '');
	
	//get currently highlighted text and set link text value
	setRange(rte);
	var linkText = '';
	if (isIE) {
		linkText = stripHTML(rng.htmlText);
	} else {
		linkText = stripHTML(rng.toString());
	}
	setLinkText(linkText);
}

function setLinkText(linkText) {
	//set link text value in insert link dialog
	try {
		window.InsertLink.document.linkForm.linkText.value = linkText;
	} catch (e) {
		//may take some time to create dialog window.
		//Keep looping until able to set.
		setTimeout("setLinkText('" + linkText + "');", 10);
	}
}

function popUpWin (url, win, width, height, options) {
	var leftPos = (screen.availWidth - width) / 2;
	var topPos = (screen.availHeight - height) / 2;
	options += 'width=' + width + ',height=' + height + ',left=' + leftPos + ',top=' + topPos;
	return window.open(url, win, options);
}

function setColor(color) {
	//function to set color
	var rte = currentRTE;
	var parentCommand = parent.command;
	
	if (document.all) {
		if (parentCommand == "hilitecolor") parentCommand = "backcolor";
		
		//retrieve selected range
		rng.select();
	}
	
	rteCommand(rte, parentCommand, color);
	showHideElement('cp' + rte, "hide");
}

function addImage(rte) {
	//function to add image
	//window.open("blank.htm");
	imagePath = prompt('\u8bf7\u8f93\u5165\u56fe\u50cf\u7684URL:', '');	
	//alert(imagePath);
	if ((imagePath != null) && (imagePath != "")) {
		rteCommand(rte, 'InsertImage', imagePath);
	}
}

// Ernst de Moor: Fix the amount of digging parents up, in case the RTE editor itself is displayed in a div.
// KJR 11/12/2004 Changed to position palette based on parent div, so palette will always appear in proper location regardless of nested divs
function getOffsetTop(elm) {
	var mOffsetTop = elm.offsetTop;
	var mOffsetParent = elm.offsetParent;
	var parents_up = 2; //the positioning div is 2 elements up the tree
	
	while(parents_up > 0) {
		mOffsetTop += mOffsetParent.offsetTop;
		mOffsetParent = mOffsetParent.offsetParent;
		parents_up--;
	}
	
	return mOffsetTop;
}

// Ernst de Moor: Fix the amount of digging parents up, in case the RTE editor itself is displayed in a div.
// KJR 11/12/2004 Changed to position palette based on parent div, so palette will always appear in proper location regardless of nested divs
function getOffsetLeft(elm) {
	var mOffsetLeft = elm.offsetLeft;
	var mOffsetParent = elm.offsetParent;
	var parents_up = 2;
	
	while(parents_up > 0) {
		mOffsetLeft += mOffsetParent.offsetLeft;
		mOffsetParent = mOffsetParent.offsetParent;
		parents_up--;
	}
	
	return mOffsetLeft;
}

function selectFont(rte, selectname) {
	//function to handle font changes
	var idx = document.getElementById(selectname).selectedIndex;
	// First one is always a label
	if (idx != 0) {
		var selected = document.getElementById(selectname).options[idx].value;
		var cmd = selectname.replace('_' + rte, '');
		rteCommand(rte, cmd, selected);
		document.getElementById(selectname).selectedIndex = 0;
	}
}

function insertHTML(html) {
	//function to add HTML -- thanks dannyuk1982
	var rte = currentRTE;
	
	var oRTE;
	if (document.all) {
		oRTE = frames[rte];
	} else {
		oRTE = document.getElementById(rte).contentWindow;
	}
	
	oRTE.focus();
	if (document.all) {
		var oRng = oRTE.document.selection.createRange();
		oRng.pasteHTML(html);
		oRng.collapse(false);
		oRng.select();
	} else {
		oRTE.document.execCommand('insertHTML', false, html);
	}
}

function showHideElement(element, showHide) {
	//function to show or hide elements
	//element variable can be string or object
	if (document.getElementById(element)) {
		element = document.getElementById(element);
	}
	
	if (showHide == "show") {
		element.style.visibility = "visible";
	} else if (showHide == "hide") {
		element.style.visibility = "hidden";
	}
}

function setRange(rte) {
	//function to store range of current selection
	var oRTE;
	if (document.all) {
		oRTE = frames[rte];
		var selection = oRTE.document.selection; 
		if (selection != null) rng = selection.createRange();
	} else {
		oRTE = document.getElementById(rte).contentWindow;
		var selection = oRTE.getSelection();
		rng = selection.getRangeAt(selection.rangeCount - 1).cloneRange();
	}
	return rng;
}

function stripHTML(oldString) {
	//function to strip all html
	var newString = oldString.replace(/(<([^>]+)>)/ig,"");
	
	//replace carriage returns and line feeds
   newString = newString.replace(/\r\n/g," ");
   newString = newString.replace(/\n/g," ");
   newString = newString.replace(/\r/g," ");
	
	//trim string
	newString = trim(newString);
	
	return newString;
}

function trim(inputString) {
   // Removes leading and trailing spaces from the passed string. Also removes
   // consecutive spaces and replaces it with one space. If something besides
   // a string is passed in (null, custom object, etc.) then return the input.
   if (typeof inputString != "string") return inputString;
   var retValue = inputString;
   var ch = retValue.substring(0, 1);
	
   while (ch == " ") { // Check for spaces at the beginning of the string
      retValue = retValue.substring(1, retValue.length);
      ch = retValue.substring(0, 1);
   }
   ch = retValue.substring(retValue.length - 1, retValue.length);
	
   while (ch == " ") { // Check for spaces at the end of the string
      retValue = retValue.substring(0, retValue.length - 1);
      ch = retValue.substring(retValue.length - 1, retValue.length);
   }
	
	// Note that there are two spaces in the string - look for multiple spaces within the string
   while (retValue.indexOf("  ") != -1) {
		// Again, there are two spaces in each of the strings
      retValue = retValue.substring(0, retValue.indexOf("  ")) + retValue.substring(retValue.indexOf("  ") + 1, retValue.length);
   }
   return retValue; // Return the trimmed string back to the user
}

//********************
//Gecko-Only Functions
//********************
function geckoKeyPress(evt) {
	//function to add bold, italic, and underline shortcut commands to gecko RTEs
	//contributed by Anti Veeranna (thanks Anti!)
	var rte = evt.target.id;
	
	if (evt.ctrlKey) {
		var key = String.fromCharCode(evt.charCode).toLowerCase();
		var cmd = '';
		switch (key) {
			case 'b': cmd = "bold"; break;
			case 'i': cmd = "italic"; break;
			case 'u': cmd = "underline"; break;
		};

		if (cmd) {
			rteCommand(rte, cmd, null);
			
			// stop the event bubble
			evt.preventDefault();
			evt.stopPropagation();
		}
 	}
}

//*****************
//IE-Only Functions
//*****************
function ieKeyPress(evt, rte) {
	var key = (evt.which || evt.charCode || evt.keyCode);
	var stringKey = String.fromCharCode(key).toLowerCase();
	
//the following breaks list and indentation functionality in IE (don't use)
//	switch (key) {
//		case 13:
//			//insert <br> tag instead of <p>
//			//change the key pressed to null
//			evt.keyCode = 0;
//			
//			//insert <br> tag
//			currentRTE = rte;
//			insertHTML('<br>');
//			break;
//	};
}

function raiseButton(e) {
	var el = window.event.srcElement;
	
	className = el.className;
	if (className == 'rteImage' || className == 'rteImageLowered') {
		el.className = 'rteImageRaised';
	}
}

function normalButton(e) {
	var el = window.event.srcElement;
	
	className = el.className;
	if (className == 'rteImageRaised' || className == 'rteImageLowered') {
		el.className = 'rteImage';
	}
}

function lowerButton(e) {
	var el = window.event.srcElement;
	
	className = el.className;
	if (className == 'rteImage' || className == 'rteImageRaised') {
		el.className = 'rteImageLowered';
	}
}
