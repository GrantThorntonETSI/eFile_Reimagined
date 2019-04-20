// JavaScript Document
//set min max date filing basis a,b + filing basis a,b,d,e
function datelimit(divID, messageID, buttonID) {
	var b = document.getElementById(divID);
	var bb = document.getElementById(buttonID);
	b.style.visibility = "hidden";
	bb.style.display = "none";
	b.style.top = "-10000px";
	b.style.float = "none";
	b.style.marginBottom = "0";
	b.style.padding = "0";
	document.getElementById(messageID).style.display = "none";
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
