function showTheTime(tzOffset) {
	var options = { hour12: false, timeZone: 'America/New_York', hour: 'numeric', minute: 'numeric', second: 'numeric', year: 'numeric', month: 'long', day: 'numeric', timeZoneName: 'short' };
	var thatTZ = new Date().toLocaleTimeString('en-US', options//, 
		//{hour12: false}
		);

	var g = document.getElementsByClassName("datesigned");

	var t = document.getElementById("tz").innerHTML = thatTZ;
	g[0].value += t;
	console.log(thatTZ);
}

function showTheTimeTwo(tzOffset) {
	var options = { hour12: false, timeZone: 'America/New_York', hour: 'numeric', minute: 'numeric', second: 'numeric', year: 'numeric', month: 'long', day: 'numeric', timeZoneName: 'short' };
	var thatTZ = new Date().toLocaleTimeString('en-US', options//, 
		//{hour12: false}
		);

	var g = document.getElementsByClassName("datesigned");

	var t = document.getElementById("tz2").innerHTML = thatTZ;
	g[1].value += t;
	console.log(thatTZ);
}
