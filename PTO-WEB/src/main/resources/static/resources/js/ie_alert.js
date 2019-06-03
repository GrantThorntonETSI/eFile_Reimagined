// JavaScript Document
//alert messages //alert if using IE
	function isIE() {
		var b = document.getElementById("alertmin");
		var bb = document.getElementById("alertbtn");
		b.style.visibility = "hidden";
		bb.style.display = "none";
		b.style.top = "-10000px";
		b.style.float = "none";
		b.style.marginBottom = "0";
		b.style.padding = "0";
		document.getElementById("mintext").style.display = "none";
		var ua = window.navigator.userAgent; //Check the userAgent property of the window.navigator object
		var msie = ua.indexOf('MSIE '); // IE 10 or older
		var trident = ua.indexOf('Trident/'); //IE 11
		return (msie > 0 || trident > 0);
		}		
		//show alert if it's IE
	function ShowIEAlert(){
		if(isIE()){
		   //alert("User is using IE");
		   	var b = document.getElementById("alertmin");
			b.style.visibility = "visible";
			b.style.float = "none";
			b.style.top = ".25em";
			b.style.padding = "1em";
			b.marginBottom = "1em";
			document.getElementById("mintext").style.display = "block";
			document.getElementById("alertbtn").style.display = "block";
			document.getElementById("alertbtn").focus();
			window.scrollTo(0, '90%');
			document.getElementById("mintext").innerHTML = ("You appear to be using Internet Explorer. For best experience, please use this application in Chrome.")
		}
		else {
			//alert("User is NOT using IE");
			var b = document.getElementById("alertmin");
			b.style.visibility = "hidden";
			b.style.top = "-10000px";
			b.style.float = "none";
			b.padding = "0";
			b.marginBottom = "0";
			document.getElementById("mintext").style.display = "none";
			document.getElementById("alertbtn").style.display = "none";
			document.getElementById("alertbtn").blur();			
			}
	  }