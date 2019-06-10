// JavaScript Document
//alert messages //alert if not using Chrome
	function isCHROME() {
		var b = document.getElementById("alertmin");
		var bb = document.getElementById("alertbtn");
		b.style.visibility = "hidden";
		bb.style.display = "none";
		b.style.top = "-10000px";
		b.style.float = "none";
		b.style.marginBottom = "1.2em";
		b.style.padding = "0";
		document.getElementById("mintext").style.display = "none";
		var ua = window.navigator.userAgent; //Check the userAgent property of the window.navigator object
		var chrome = ua.indexOf('Chrome'); // Chrome browser
		return (chrome > -1);
		}		
		//show alert if it's not Chrome
	function ShowChromeAlert(){
		if(isCHROME()){
		   	//alert("User is using Chrome");
			var b = document.getElementById("alertmin");
			b.style.visibility = "hidden";
			b.style.top = "-10000px";
			b.style.float = "none";
			b.padding = "0";
			b.marginBottom = "0";
			document.getElementById("mintext").style.display = "none";
			document.getElementById("alertbtn").style.display = "none";
			document.getElementById("alertmin").style.display = "none";
			document.getElementById("alertbtn").blur();
		}
		else {
			//alert("User is NOT using Chrome");
			var b = document.getElementById("alertmin");
			b.style.visibility = "visible";
			b.style.height = "auto";
			b.style.float = "none";
			b.style.top = ".25em";
			b.style.padding = "1em";
			b.marginBottom = "1em";
			document.getElementById("mintext").style.display = "table-cell";
			document.getElementById("alertbtn").style.display = "table-cell";
			document.getElementById("alertbtn").focus();
			window.scrollTo(0, '90%');
			document.getElementById("mintext").innerHTML = ("You appear to be using a browser other than Chrome. For best experience, please use this application in Chrome.");
			}
	  }