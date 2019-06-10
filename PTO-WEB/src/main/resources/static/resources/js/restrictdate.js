// JavaScript Document
//set min max date filing basis a,b + filing basis a,b,d,e
function datelimit() {
	var b = document.getElementById("alertmin");
	var bb = document.getElementById("alertbtn");
	b.style.visibility = "hidden";
	b.style.height = "1px";
	bb.style.display = "none";
	b.style.top = "-10000px";
	b.style.float = "none";
	b.style.marginBottom = "0";
	b.style.padding = "0";
	document.getElementById("mintext").style.display = "none";
	var thatz = new Date();
	
	function addDays(theDate, days) {
		return new Date(theDate.getTime() + days*24*60*60*1000);
	}	
	
	var options = { hour12: false, timeZone: 'America/New_York', year: 'numeric', month: '2-digit', day: '2-digit', timeZoneName: 'short' };
	var newDate = addDays(new Date(), -182.5).toLocaleTimeString('en-US', options);
	var strmin = newDate; 
	var sliceyearmin = strmin.slice(6, 10);
	var slicemonthmin = strmin.slice(0, 2);
	var slicedaymin = strmin.slice(3, 5);
	var f = document.getElementsByClassName("foreignfiling");
	
	var strmax = thatz.toLocaleTimeString('en-US', options); 
	var sliceyearmx = strmax.slice(6, 10);
	var slicemonthmx = strmax.slice(0, 2);
	var slicedaymx = strmax.slice(3, 5);
	
	for (var i = 0; i < f.length; i++)
    f[i].setAttribute("min", sliceyearmin + "-" + slicemonthmin + "-" + slicedaymin);
	
	var t = document.getElementsByClassName("foreignfiling");
	for (var i = 0; i < t.length; i++)
	t[i].setAttribute("max", sliceyearmx + "-" + slicemonthmx + "-" + slicedaymx);
}
//alert messages //alert if invalid dates are entered manually incorrectly
function datealert() {
	
	function addDays(theDate, days) {
		return new Date(theDate.getTime() + days*24*60*60*1000);
	}	
	
	var options = { hour12: false, timeZone: 'America/New_York', year: 'numeric', month: '2-digit', day: '2-digit', timeZoneName: 'short' };
	var thatz = new Date().toLocaleTimeString('en-US', options);
	var newDate = addDays(new Date(), -182.5).toLocaleTimeString('en-US', options);
	var strmin = newDate; 
		
	var validateinput = event.currentTarget;
	var validate = event.currentTarget.value; 	
	var dateval = Date.parse(validate);
  	var requireddate = Date.parse(newDate);
	var todaydate = Date.parse(thatz);	
	
	if ((requireddate - 182.5) > dateval) {
		var b = document.getElementById("alertmin");
		if (b.style.visibility === "hidden") {
			b.style.visibility = "visible";
			b.style.height = "auto";
			b.classList.add("form-group");
			b.classList.add("form-group-md");
			b.style.float = "left";
			b.style.top = ".25em";
			b.style.padding = "1em";
			b.marginBottom = "1em";
			document.getElementById("mintext").style.display = "block";
			document.getElementById("alertbtn").style.display = "block";
			document.getElementById("alertbtn").focus();
			window.scrollTo(0, '90%');
			document.getElementById("mintext").innerHTML = ("You cannot claim Section 44(d) priority, because your attempted U.S. filing is outside the 6-month window. (i.e., from when the foreign application was filed). The date must be a number after " + strmin)
	  } else {
			b.style.visibility = "hidden";
			b.style.height = "1px";
			b.classList.remove("form-group");
			b.classList.remove("form-group-md");
			b.style.top = "-10000px";
			b.style.float = "none";
			b.padding = "0";
			b.marginBottom = "0";
			document.getElementById("mintext").style.display = "none";
			document.getElementById("alertbtn").style.display = "none";
			document.getElementById("alertbtn").blur();
			//validateinput.focus();
	  }
	  //validateinput.value = "yyyy-MM-dd";
	  document.getElementById("alertbtn").onclick = function() {
	  	validateinput.focus();
	  }
	}
	if ((todaydate) < dateval) {
		var b = document.getElementById("alertmin");
		if (b.style.visibility === "hidden") {
			b.style.visibility = "visible";
			b.style.height = "auto";
			b.classList.add("form-group");
			b.classList.add("form-group-md");
			b.style.float = "left";
			b.style.top = ".25em";
			b.style.padding = "1em";
			b.marginBottom = "1em";
			document.getElementById("mintext").style.display = "block";
			document.getElementById("alertbtn").style.display = "block";
			document.getElementById("alertbtn").focus();
			window.scrollBy(0, '90%');
			document.getElementById("mintext").innerHTML = ("You cannot claim Section 44(d) priority, because your attempted U.S. filing is after the current date. The date must be a number before " + thatz);
	  		//validateinput.value = "yyyy-MM-dd";
		} else {
			b.style.visibility = "hidden";
			b.style.height = "1px";
			b.classList.remove("form-group");
			b.classList.remove("form-group-md");
			b.style.top = "-10000px";
			b.style.float = "none";
			b.padding = "0";
			b.marginBottom = "0";
			document.getElementById("mintext").style.display = "none";
			document.getElementById("alertbtn").style.display = "none";
			document.getElementById("alertbtn").blur();
	  }
	  document.getElementById("alertbtn").onclick = function() {
	  	validateinput.focus();
	  }
	}	
}