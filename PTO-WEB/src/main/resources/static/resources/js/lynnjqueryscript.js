$(document).ready(function(){
	//START footer and login positioning
	$( window ).on('load', function() {
		var e = $( window ).height();
		var enav = $('.navbar-fixed-top').height();
		var efoot = $('footer').height();
		var winwidth = $(window).width();
		//if (winwidth > 768) {
		$('footer').css('position','relative').css('top',((e - efoot) - enav)).css('margin-top','0');
		$('main#loginform').css('position','relative').css('top',((e - efoot) - enav) / 6);
		//}
//		else if (winwidth < 767) {
//			$('footer').css('position','relative').css('top',((e - efoot) - enav)).css('margin-top','0');
//			$('main#loginform').css('position','relative').css('top', '0');
//			}
	});
	$( window ).resize(function() {
		var e = $( window ).height();
		var enav = $('.navbar-fixed-top').height();
		var efoot = $('footer').height();
		var winwidth = $(window).width();
		//if (winwidth > 768) {
		$('footer').css('position','relative').css('top',((e - efoot) - enav)).css('margin-top','0');
		$('main#loginform').css('position','relative').css('top',((e - efoot) - enav) / 6);
        $('.datepicker').datepicker( 'hide' );
		//}
//		else if (winwidth < 767) {
//			$('footer').css('position','relative').css('top',((e - efoot) - enav)).css('margin-top','0');
//			$('main#loginform').css('position','relative').css('top', '0');
//			}
	});
	//END footer and login positioning
	//START expand / collapse glyphicon
	$("button#optionsbtn").click(function() {
		$( 'button#optionsbtn span' ).toggleClass('visuallyremoved');
		$( 'button#optionsbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#translatesbtn").click(function() {
		$( 'button#translatesbtn span' ).toggleClass('visuallyremoved');
		$( 'button#translatesbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#colorbtn").click(function() {
		$( 'button#colorbtn span' ).toggleClass('visuallyremoved');
		$( 'button#colorbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#marktypebtn").click(function() {
		$( 'button#marktypebtn span' ).toggleClass('visuallyremoved');
		$( 'button#marktypebtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#translitbtn").click(function() {
		$( 'button#translitbtn span' ).toggleClass('visuallyremoved');
		$( 'button#translitbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#npsbtn").click(function() {
		$( 'button#npsbtn span' ).toggleClass('visuallyremoved');
		$( 'button#npsbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#disclaimerbtn").click(function() {
		$( 'button#disclaimerbtn span' ).toggleClass('visuallyremoved');
		$( 'button#disclaimerbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#priorbtn").click(function() {
		$( 'button#priorbtn span' ).toggleClass('visuallyremoved');
		$( 'button#priorbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#meanbtn").click(function() {
		$( 'button#meanbtn span' ).toggleClass('visuallyremoved');
		$( 'button#meanbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#entbtn").click(function() {
		$( 'button#entbtn span' ).toggleClass('visuallyremoved');
		$( 'button#entbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#registerbtn").click(function() {
		$( 'button#registerbtn span' ).toggleClass('visuallyremoved');
		$( 'button#registerbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#distinctivenessbtn").click(function() {
		$( 'button#distinctivenessbtn span' ).toggleClass('visuallyremoved');
		$( 'button#distinctivenessbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	//END expand / collapse glyphicon




	// intialize application review data table
	var tablethree = $('#responseamendtabletwo').DataTable({
		"fnDrawCallback": function( oSettings ) {
		},
		'sDom': 't',
		//"language": {
		//"search": "<span class='glyphicon glyphicon-search' aria-hidden='true'></span><span class='sr-only'>search</span>",
//			"lengthMenu": "<span class='glyphicon glyphicon-filter' aria-hidden='true'></span><span class='sr-only'>select number of entries to display</span> <select>"+
//			  '<option value="10">10</option>'+
//			  '<option value="25">25</option>'+
//			  '<option value="50">50</option>'+
//			  '<option value="100">100</option>'+
//			  '<option value="-1">All</option>'+
//			  '</select>'
		//},

		'autoWidth': false,
		responsive: {
			breakpoints: [
				{ name: 'desktop', width: Infinity },
				{ name: 'tablet',  width: 1024 },
				{ name: 'fablet',  width: 768 },
				{ name: 'phone',   width: 480 }
			]
		},
		'columns': [
			{ 'width': '4%' },
			{ 'width': '13%' },
			{ 'width': '13%' },
			{ 'width': '10%' },
			{ 'width': '10%' },
			{ 'width': '10%' },
			{ 'width': '10%' },
			{ 'width': '10%' },
			{ 'width': '10%' },
			{ 'width': '10%' },
		],
		'columnDefs': [
			{ className: 'centertxt', 'targets': [ 0,1,2,3,4,5,6,7,8,9 ] },
			{ orderable: false},
			{ className: 'select-checkbox'},
			{ targets:   0}
		],
		select: {
			style:    'multi',
			selector: 'tr td:nth-child(1) span'
		},
		order: [[ 1, 'asc' ]]
	});




	//Dashboard datatables ellipsis menu tableone
	$("div.toolbar").html('<div class="dropdown"><button class="btn btn-xs dropdown-toggle" type="button" id="dropdownMenucolvis" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"><span class="glyphicon glyphicon-option-vertical" role="img" aria-label="toggle columns visibility"></span></button><ul class="dropdown-menu" aria-labelledby="dropdownMenucolvis"><li class="dropdown-header">Toggle Columns</li><li><a class="toggle-vis" data-column="0"><span class="glyphicon glyphicon-eye-open" role="img" aria-label="hide this column"></span>Serial#</a></li><li><a class="toggle-vis" data-column="1"><span class="glyphicon glyphicon-eye-open" role="img" aria-label="hide this column"></span>Registration#</a></li><li><a class="toggle-vis" data-column="2"><span class="glyphicon glyphicon-eye-open" role="img" aria-label="hide this column"></span>Owner</a></li><li><a class="toggle-vis" data-column="3"><span class="glyphicon glyphicon-eye-open" role="img" aria-label="hide this column"></span>Status</a></li><li><a class="toggle-vis" data-column="4"><span class="glyphicon glyphicon-eye-open" role="img" aria-label="hide this column"></span>Mark</a></li></ul></div>');
	$('a.toggle-vis').on( 'click', function (e) {
		e.preventDefault();
		var column = tableone.column( $(this).attr('data-column') );
		column.visible( ! column.visible() );
		//console.log($(this).attr('data-column'));
	});
	//END dashboard datatables ellipsis menu tableone

	//Dashboard datatables ellipsis menu tabletwo
	$("div.toolbartwo").html('<div class="dropdown"><button class="btn btn-xs dropdown-toggle" type="button" id="dropdownMenucolvis" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"><span class="glyphicon glyphicon-option-vertical role="img" aria-label="toggle column visibility""></span></button><ul class="dropdown-menu" aria-labelledby="dropdownMenucolvis"><li class="dropdown-header">Toggle Columns</li><li><a class="toggle-vistwo" data-column="0"><span class="glyphicon glyphicon-eye-open" role="img" aria-label="hide this column"></span>Serial#</a></li><li><a class="toggle-vistwo" data-column="1"><span class="glyphicon glyphicon-eye-open" role="img" aria-label="hide this column"></span>Registration#</a></li><li><a class="toggle-vistwo" data-column="2"><span class="glyphicon glyphicon-eye-open" role="img" aria-label="hide this column"></span>Mark</a></li><li><a class="toggle-vistwo" data-column="3"><span class="glyphicon glyphicon-eye-open" role="img" aria-label="hide this column"></span>Owner</a></li><li><a class="toggle-vistwo" data-column="4"><span class="glyphicon glyphicon-eye-open" role="img" aria-label="hide this column"></span>Due Date</a></li><li><a class="toggle-vistwo" data-column="5"><span class="glyphicon glyphicon-eye-open" role="img" aria-label="hide this column"></span>Status</a></li><li><a class="toggle-vistwo" data-column="6"><span class="glyphicon glyphicon-eye-open" role="img" aria-label="hide this column"></span>Action</a></li></ul></div>');
	$('a.toggle-vistwo').on( 'click', function (e) {
		e.preventDefault();
		var column = tabletwo.column( $(this).attr('data-column') );
		column.visible( ! column.visible() );
		//console.log($(this).attr('data-column'));
	});
	//END dashboard datatables ellipsis menu tabletwo

	//.dashsection height = #announcedashsection height
	var h = $( 'div#announcedashsection' );
	var y = $( 'div#announce' );
	$('.dashsection:eq(0), .dashsection:eq(1)').css( 'min-height', (h.innerHeight() + y.innerHeight()) );
	$( window ).resize(function() {
		$('.dashsection:eq(0), .dashsection:eq(1)').css( 'min-height', (h.innerHeight() + y.innerHeight()) );
	});
	//END

	//start close (x) dashboard panels
	$('#dashsectionscontainer #announcements .closegspanels').click(function() {
		$( this ).parent().parent().parent().parent().parent().fadeOut( 'fast','swing');
	});
    //START close (x) survey alert panel
	$('#alertminwelcome .closegspanels').click(function() {
		$( this ).parent().parent().fadeOut( 'fast','swing');
	});
	//end close (x) dashboard panels

	//start close (x) gs + editowner + reviewattorney panels
	$('#gsselected .closegspanels, #editowner .closegspanels, #reviewattorney .closegspanels').click(function() {
		$( this ).parent().parent().parent().fadeOut( 'fast','swing');
	});
	//end close (x) gs + editowner + reviewattorney panels

	//START set initial checkboxes

	$('input[type=checkbox]#authemail, input[type=radio]#inlineRadio044').not(this).prop('checked', true);
	$('input[type=radio]#inlineRadio031').prop('checked', true);
	//if ($('input[type=checkbox]#authemail').prop('checked')) {
	//	  console.log('checked');
	//  }
	//  else {
	//	  console.log('unchecked');
	//	  }
	//END set initial checkboxes

	//START additional info page supplemental register checked
	$('input[type=radio]#inlineRadio032').change(function() {
		var firstfees = $('li#firstli span').text();
		var classfee = $('li#classli span').text();
		var highestfeeperclass = '80';//additional fee
		var lastfees = (parseFloat(firstfees) + parseFloat( highestfeeperclass ));
		var lastfeesprincipal = (parseFloat(firstfees) + parseFloat( originalclassfees ));
		var originalclassfees = (parseFloat(classfee) - parseFloat( highestfeeperclass ));
		if(this.checked == true){
			$('li#classli span').html('<span>' + highestfeeperclass + '</span>');
			$('li#lastli span').html('<span>' + lastfees + '</span>');
			$('span#displayfees').html('<span>' + lastfees + '</span>');
			$('#tr div.alert').addClass('visuallyadded');
			$('#tr div.alert').removeClass('visuallyremoved');
		}
	});
	$('input[type=radio]#inlineRadio031').change(function() {
		var firstfees = $('li#firstli span').text();
		var classfee = $('li#classli span').text();
		var highestfeeperclass = '0';//additional fee
		var lastfees = (parseFloat(firstfees) + parseFloat( highestfeeperclass ));
		if(this.checked == true){
			$('li#classli span').html('<span>' + highestfeeperclass + '</span>');
			$('li#lastli span').html('<span>' + lastfees + '</span>');
			$('span#displayfees').html('<span>' + lastfees + '</span>');
			$('#tr div.alert').addClass('visuallyremoved');
			$('#tr div.alert').removeClass('visuallyadded');
		}
	});
	//END additional info page supplemental register checked

	//START enable / disable checkboxes
	//When 'Certification' is checked, 'Trademark / Servicemark', 'Collective' and 'Collective Membership' are disabled
	$('input#certcheck').change(function() {
		if(this.checked == true){
			$("input#tradeserv").prop({
				disabled: true,
				checked: false,
			});
			$("input#collectcheck").prop({
				disabled: true,
				checked: false,
			});
			$("input#collectmember").prop({
				disabled: true,
				checked: false,
			});
			$('.form-check#tradeserv2 span.radio').addClass('special');
			$('.form-check#collectcheck2 span.radio').addClass('special');
			$('.form-check#collectm2 span.radio').addClass('special');
			$('input#tradeserv').attr('aria-disabled', 'true');
			$('input#collectcheck').attr('aria-disabled', 'true');
			$('input#collectmember').attr('aria-disabled', 'true');
		}
		else{
			$("input#tradeserv").prop({
				disabled: false,
			});
			$("input#collectcheck").prop({
				disabled: false,
			});
			$("input#collectmember").prop({
				disabled: false,
			});
			$('.form-check#tradeserv2 span.radio').removeClass('special');
			$('.form-check#collectcheck2 span.radio').removeClass('special');
			$('.form-check#collectm2 span.radio').removeClass('special');
			$('input#tradeserv').attr('aria-disabled', 'false');
			$('input#collectcheck').attr('aria-disabled', 'false');
			$('input#collectmember').attr('aria-disabled', 'false');
		}
	});
	//When 'Collective' is checked, 'Certification' is disabled
	$('input#collectcheck').change(function() {
		if(this.checked == true){
			$('input#certcheck').prop({
				disabled: true,
				checked: false,
			});
			$('.form-check#certcheck2 span.radio').addClass('special');
			$('input#certcheck').attr('aria-disabled', 'true');
		}
		else if ($('input#collectmember').prop('checked')){
			$('input#certcheck').prop({
				disabled: true,
				checked: false,
			});
			$('.form-check#certcheck2 span.radio').addClass('special');
			$('input#certcheck').attr('aria-disabled', 'true');
		}
		else if ($('input#tradeserv').prop('checked')){
			$('input#certcheck').prop({
				disabled: true,
				checked: false,
			});
			$('.form-check#certcheck2 span.radio').addClass('special');
			$('input#certcheck').attr('aria-disabled', 'true');
		}
		else{
			$('input#certcheck').prop({
				disabled: false,
			});
			$('.form-check#certcheck2 span.radio').removeClass('special');
			$('input#certcheck').attr('aria-disabled', 'false');
		}
	});
	//When 'Collective Membership' is checked, 'Certification' is disabled
	$('input#collectmember').change(function() {
		if(this.checked == true){
			$('input#certcheck').prop({
				disabled: true,
				checked: false,
			});
			$('.form-check#certcheck2 span.radio').addClass('special');
			$('input#certcheck').attr('aria-disabled', 'true');
		}
		else if ($('input#collectcheck').prop('checked')){
			$('input#certcheck').prop({
				disabled: true,
				checked: false,
			});
			$('.form-check#certcheck2 span.radio').addClass('special');
			$('input#certcheck').attr('aria-disabled', 'true');
		}
		else if ($('input#tradeserv').prop('checked')){
			$('input#certcheck').prop({
				disabled: true,
				checked: false,
			});
			$('.form-check#certcheck2 span.radio').addClass('special');
			$('input#certcheck').attr('aria-disabled', 'true');
		}
		else{
			$('input#certcheck').prop({
				disabled: false,
			});
			$('.form-check#certcheck2 span.radio').removeClass('special');
			$('input#certcheck').attr('aria-disabled', 'false');
		}
	});
	//When 'Trademark / Servicemark' is checked, 'Certification' is disabled
	$('input#tradeserv').change(function(e) {
		if(this.checked == true){
			$('input#certcheck').prop({
				disabled: true,
				checked: false,
			});
			$('.form-check#certcheck2 span.radio').addClass('special');
			$('input#certcheck').attr('aria-disabled', 'true');
		}
		else{
			$('input#certcheck').prop({
				disabled: false,
			});
			$('.form-check#certcheck2 span.radio').removeClass('special');
			$('input#certcheck').attr('aria-disabled', 'false');
		}
	});//END enable / disable checkboxes

	//START additional info checkboxes

	$('input#typeregi').change(function() {
		if(this.checked == true){
			$('#tr').show( 'fast' );
		}
		else {
			$('#tr').hide( 'fast' );
		}
	});

	$('input#distinct').change(function() {
		if(this.checked == true){
			$('#cad').show( 'fast' );
		}
		else {
			$('#cad').hide( 'fast' );
		}
	});

	$('input#useanotherform').change(function() {
		if(this.checked == true){
			$('#uaf').show( 'fast' );
		}
		else {
			$('#uaf').hide( 'fast' );
		}
	});

	$('input#concurruse').change(function() {
		if(this.checked == true){
			$('#cc').show( 'fast' );
		}
		else {
			$('#cc').hide( 'fast' );
		}
	});
	//END additional info checkboxes

	//start distinctive options

	$('input#inlineRadio033').change(function() {
		if(this.checked == true){
			$('div#yesdistict').show( 'fast' );
		}
		else {
			$('div#yesdistict').hide( 'fast' );
		}
	});
	$('input#inlineRadio034').change(function() {
		if(this.checked == true){
			$('div#yesdistict').hide( 'fast' );
		}
		else {
			$('div#yesdistict').show( 'fast' );
		}
	});
	//other form

	$('input#inlineRadio040').change(function() {
		if(this.checked == true){
			$('div#yesotherform').show( 'fast' );
		}
		else {
			$('div#yesotherform').hide( 'fast' );
		}
	});
	$('input#inlineRadio041').change(function() {
		if(this.checked == true){
			$('div#yesotherform').hide( 'fast' );
		}
		else {
			$('div#yesotherform').show( 'fast' );
		}
	});
	//whole or in part

	$('input#inlineRadio035').change(function() {
		if(this.checked == true){
			$('div#yeswhole').show( 'fast' );
			$('div#yespart').hide( 'fast' );
		}
		else {
			$('div#yeswhole').hide( 'fast' );
		}
	});
	$('input#inlineRadio036').change(function() {
		if(this.checked == true){
			$('div#yeswhole').show( 'fast' );
			$('div#yespart').show( 'fast' );
		}
		else {
			$('div#yespart').hide( 'fast' );
		}
	});
	//whole or in part (other form)

	$('input#inlineRadio042').change(function() {
		if(this.checked == true){
			$('div#yeswholeother').show( 'fast' );
			//$(heightmatchbackwards);
			$('div#yespartother').hide( 'fast' );
		}
		else {
			$('div#yeswholeother').hide( 'fast' );
		}
	});
	$('input#inlineRadio043').change(function() {
		if(this.checked == true){
			$('div#yeswholeother').show( 'fast' );
			$('div#yespartother').show( 'fast' );
		}
		else {
			$('div#yespartother').hide( 'fast' );
		}
	});
	//evidence

	$('input#inlineRadio037').change(function() {
		if(this.checked == true){
			$('div#yesevidence').show( 'fast' );
			$('div#yespriors').hide( 'fast' );
		}
		else {
			$('div#yesevidence').hide( 'fast' );
		}
	});
	$('input#inlineRadio038').change(function() {
		if(this.checked == true){
			$('div#yesevidence').hide( 'fast' );
			$('div#yespriors').show( 'fast' );
		}
		else {
			$('div#yespriors').hide( 'fast' );
		}
	});
	$('input#inlineRadio039').change(function() {
		if(this.checked == true){
			$('div#yesevidence').hide( 'fast' );
			$('div#yespriors').hide( 'fast' );
		}
	});
	//hide / show concurrent uses


	//START toggle panel color
	$( '.nocontent' ).click(function() {
		$( document ).find('div').removeClass( 'highlight focus' );
		$( this ).addClass( 'highlight focus' );
	});
	//END toggle panel color

	//START panel height match
	var a = $( 'div.match' );
	$('#panels .panel-body').css( 'height', (a.innerHeight() + 8) );
	//END

	//START input label height match
	var t = $( '.matchlabelheight' ).parent().prev('div').children('label');
	var u = $( '.matchlabelheighttwo' ).parent().prev('div').children('label');
	$('.matchlabelheight').css( 'height', (t.outerHeight() + 'px') );
	$('.matchlabelheighttwo').css( 'height', (u.outerHeight() + 'px') );
	//console.log(u.outerHeight());
	$( window ).resize(function() {
		$('.matchlabelheight').css( 'height', (t.outerHeight())  + 'px' );
		$('.matchlabelheighttwo').css( 'height', (u.outerHeight())  + 'px' );
	});
	//END input label height match

	//START basis input label height match upload label
	//var r = $( '#specdescript' );
//		var s = $( '#upimg' );
//		var z = $( '#specdescriptwo' );
//		var n = $( '#upimgtwo' );
//		var c = $( '#upimgfour' );
//		$( window ).load(function() {
//			$('#specdescript').css( 'height', ((s.outerHeight() + 2) + 'px') );
//			$('#specdescriptwo').css( 'height', ((n.outerHeight() + 2) + 'px') );
//			$('#specdescriptfour').css( 'height', ((c.outerHeight() + 2) + 'px') );
//		});
//		//console.log(s.outerHeight());
//		$( window ).resize(function() {
//			$('#specdescript').css( 'height', ((s.outerHeight() + 2)  + 'px') );
//			$('#specdescriptwo').css( 'height', ((n.outerHeight() + 2) + 'px') );
//			$('#specdescriptfour').css( 'height', ((c.outerHeight() + 2) + 'px') );
//		});
	//END basis input label height match upload label
    
    //generate unique IDs + matching labels for filing basis foreign registration datepickers
		var datepickerList = $('#basisabde div.holdsaforeign input.datepicker');
		for (var i = 0; i <= datepickerList.length; i++) {
			$(datepickerList[i]).attr('id', 'datepicker' + i);
		}
		var datepickerlabelList = $('#basisabde div.holdsaforeign input.datepicker').prev('label');
		for (var i = 0; i <= datepickerlabelList.length; i++) {
			$(datepickerlabelList[i]).attr('for', 'datepicker' + i);
		}
	//

	//Placeholder as editable text
	//$('textarea#ta2, textarea#ta5').val('This mark consists of');
	//Placeholder as editable text

	//Placeholder as editable text edit owner

	//START examples panel link hover / active
	$('#examples div.panel a').hover(function() {
		$(this).css('display','block');
		$( this ).parent().parent().parent().addClass( 'examplehover' );
	}, function() {
		$( this ).parent().parent().parent().removeClass( 'examplehover' );
	});
	//END examples panel link hover / active

	//START examples panel button toggle text
	$( 'button#moreoptionsbtn' ).click(function() {
		$( this ).toggleClass( 'focus' );
		$(this).text() === 'See More Types'
			? $(this).text('See Less Types')
			: $(this).text('See More Types');
	});
	//END examples panel button toggle text

	//Initialize tooltip
	$("body").tooltip({
		selector: "[data-toggle='tooltip']",
		container: "body",
		placement : 'bottom auto',
	});
	//Initialize popover
	$('[data-toggle="popover"]').popover({
		title : false,
		html : true,
		placement : 'bottom auto',
		content : '<div class="media"><span class="glyphicon glyphicon-picture" aria-hidden="true" style="min-width:77px;width:80px;margin:0 auto;"></span></div>'
	});
	$( '#togglepop' ).click(function() {
		$( this ).toggleClass( 'focus' );
		$(this).html() === '<span class="glyphicon glyphicon-picture" aria-hidden="true"></span> Hide My Mark'
			? $(this).html('<span class="glyphicon glyphicon-picture" aria-hidden="true"></span> Show My Mark')
			: $(this).html('<span class="glyphicon glyphicon-picture" aria-hidden="true"></span> Hide My Mark');
	});
	//toggle acceptance
	$( '#acceptreview' ).click(function() {
		$( this ).toggleClass( 'focus' );
		$(this).html() === 'Accept'
			? $(this).html('Accepted')
			: $(this).html('Accept');
	});

	//- Translation Item
	//

	//START toggle radio buttons content
	//start mark color options

	$('input#inlineRadio1').change(function() {
		if(this.checked == true){
			$('div#nocolorclaim').hide( 'fast' );
			$('div#yescolorclaim').show( 'slow' );
		}
		else {
			$('div#nocolorclaim').show( 'fast' );
			$('div#yescolorclaim').hide( 'slow' );
		}
	});
	$('input#inlineRadio2').change(function() {
		if(this.checked == true){
			$('div#nocolorclaim').show( 'fast' );
			$('div#yescolorclaim').hide( 'slow' );
		}
		else {
			$('div#nocolorclaim').hide( 'fast' );
			$('div#yescolorclaim').show( 'slow' );
		}
	});
	//end mark color options

	//start translations options

	$('input#inlineRadio3').change(function() {
		if(this.checked == true){
			$('div#yestranslation').show( 'slow' );
		}
		else {
			$('div#yestranslation').hide( 'slow' );
		}
	});
	$('input#inlineRadio4').change(function() {
		if(this.checked == true){
			$('div#yestranslation').hide( 'slow' );
		}
		else {
			$('div#yestranslation').show( 'slow' );
		}
	});
	//end translations options

	//start transliterations options

	$('input#inlineRadio5').change(function() {
		if(this.checked == true){
			$('div#yestransliteration').show( 'fast' );
		}
		else {
			$('div#yestransliteration').hide( 'fast' );
		}
	});
	$('input#inlineRadio6').change(function() {
		if(this.checked == true){
			$('div#yestransliteration').hide( 'fast' );
		}
		else {
			$('div#yestransliteration').show( 'fast' );
		}
	});
	//end translations options

	//start nps options

	$('input#inlineRadio7').change(function() {
		if(this.checked == true){
			$('div#yesnps').show( 'fast' );
		}
		else {
			$('div#yesnps').hide( 'fast' );
		}
	});
	$('input#inlineRadio8').change(function() {
		if(this.checked == true){
			$('div#yesnps').hide( 'fast' );
		}
		else {
			$('div#yesnps').show( 'fast' );
		}
	});
	//yes, contains name is checked

	$('#namechecked').change(function() {
		if(this.checked == true){
			$('div#yescontainsname').show( 'fast' );
		}
		else {
			$('div#yescontainsname').hide( 'fast' );
		}
	});
	//yes, contains portrait is checked

	$('#portraitchecked').change(function() {
		if(this.checked == true){
			$('div#yescontainsportrait').show( 'fast' );
		}
		else {
			$('div#yescontainsportrait').hide( 'fast' );
		}
	});
	//yes, contains signature is checked

	$('#signaturechecked').change(function() {
		if(this.checked == true){
			$('div#yescontainssignature').show( 'fast' );
		}
		else {
			$('div#yescontainssignature').hide( 'fast' );
		}
	});
	//yes, consent required is checked

	$('#inlineRadio17').change(function() {
		if(this.checked == true){
			$('div#yesliving').show( 'fast' );
		}
		else {
			$('div#yesliving').hide( 'fast' );
		}
	});
	$('#inlineRadio18').change(function() {
		if(this.checked == true){
			$('div#yesliving').hide( 'fast' );
		}
		else {
			$('div#yesliving').show( 'fast' );
		}
	});
	//end nps options

	//start disclaimer options

	$('input#inlineRadio09').change(function() {
		if(this.checked == true){
			$('div#yesdisclaimer').show( 'fast' );
		}
		else {
			$('div#yesdisclaimer').hide( 'fast' );
		}
	});
	$('input#inlineRadio010').change(function() {
		if(this.checked == true){
			$('div#yesdisclaimer').hide( 'fast' );
		}
		else {
			$('div#yesdisclaimer').show( 'fast' );
		}
	});
	//end disclaimer options

	//start uncommoninfo options

	$('input#inlineRadio045').change(function() {
		if(this.checked == true){
			$('div#yesnotpreviously').show( 'fast' );
		}
		else {
			$('div#yesnotpreviously').hide( 'fast' );
		}
	});
	$('input#inlineRadio046').change(function() {
		if(this.checked == true){
			$('div#yesnotpreviously').hide( 'fast' );
		}
		else {
			$('div#yesnotpreviously').show( 'fast' );
		}
	});
	//end uncommoninfo options

	//start prior options

	$('input#inlineRadio011').change(function() {
		if(this.checked == true){
			$('div#yesprior').show( 'fast' );
		}
		else {
			$('div#yesprior').hide( 'fast' );
		}
	});
	$('input#inlineRadio012').change(function() {
		if(this.checked == true){
			$('div#yesprior').hide( 'fast' );
		}
		else {
			$('div#yesprior').show( 'fast' );
		}
	});
	//end prior options

	//start prior options

	$('input#inlineRadio013').change(function() {
		if(this.checked == true){
			$('div#yesmeaning').show( 'fast' );
		}
		else {
			$('div#yesmeaning').hide( 'fast' );
		}
	});
	$('input#inlineRadio014').change(function() {
		if(this.checked == true){
			$('div#yesmeaning').hide( 'fast' );
		}
		else {
			$('div#yesmeaning').show( 'fast' );
		}
	});
	//end prior options

	//start attorney options

	$('input#inlineRadio015').change(function() {
		if(this.checked == true){
			$('div#yesattorneyfiling').show( 'fast' );
			$( document ).find('p.required').toggleClass('visuallyshown');
		}
		else {
			$('div#yesattorneyfiling').hide( 'fast' );
		}
	});
	$('input#inlineRadio016').change(function() {
		if(this.checked == true){
			$('div#yesattorneyfiling').hide( 'fast' );
			$( document ).find('p.required').removeClass('visuallyshown');
		}
		else {
			$('div#yesattorneyfiling').show( 'fast' );
		}
	});
	//end attorney options

	//start us foreign options
	//us entity show

	$('input#inlineRadio019').change(function() {
		if(this.checked == true){
			$('div#yesusentity').show( 'fast' );

			$("input#inlineRadio020").prop({
				checked: false,
			});
		}
		else {
			$('div#yesusentity').hide( 'fast' );

			$("input#inlineRadio020").prop({
				checked: true,
			});
		}
	});
	$('input#inlineRadio020').change(function() {
		if(this.checked == true){
			$('div#yesusentity').hide( 'fast' );

			$("input#inlineRadio019").prop({
				checked: false,
			});
		}
		else {
			$('div#yesusentity').show( 'fast' );

			$("input#inlineRadio019").prop({
				checked: true,
			});
		}
	});

	//foreign entity show

	$('input#inlineRadio020').change(function() {
		if(this.checked == true){
			$('div#yesforeignentity').show( 'fast' );

			$("input#inlineRadio019").prop({
				checked: false,
			});
		}
		else {
			$('div#yesforeignentity').hide( 'fast' );

			$("input#inlineRadio019").prop({
				checked: true,
			});
		}
	});
	$('input#inlineRadio019').change(function() {
		if(this.checked == true){
			$('div#yesforeignentity').hide( 'fast' );

			$("input#inlineRadio020").prop({
				checked: false,
			});
		}
		else {
			$('div#yesforeignentity').show( 'fast' );

			$("input#inlineRadio020").prop({
				checked: true,
			});
		}
	});
	//end us foreign options

	//start basis commerce options

	$('input#inlineRadio25').change(function() {
		if(this.checked == true){
			$('div#yescommerce').show( 'fast' );
		}
		else {
			$('div#yescommerce').hide( 'fast' );
		}
	});
	$('input#inlineRadio26').change(function() {
		if(this.checked == true){
			$('div#yescommerce').hide( 'fast' );
		}
		else {
			$('div#yescommerce').show( 'fast' );
		}
	});
	//end basis commerce options

	//start basistwo connection options

	$('input#inlineRadio29').change(function() {
		if(this.checked == true){
			$('div#yesconnection').show( 'fast' );
			//$(heightmatch);
		}
		else {
			$('div#yesconnection').hide( 'fast' );
		}
	});
	$('input#inlineRadio30').change(function() {
		if(this.checked == true){
			$('div#yesconnection').hide( 'fast' );
		}
		else {
			$('div#yesconnection').show( 'fast' );
		}
	});
	//end basistwo connection options

	//start provide specimen options

	$('input#specimen').change(function() {
		if(this.checked == true){
			$('div#yesspecimenone').show( 'fast' );
		}
		else {
			$('div#yesspecimenone').hide( 'fast' );
		}
	});

	$('input#specimentwo').change(function() {
		if(this.checked == true){
			$('div#yesspecimentwo').show( 'fast' );
		}
		else {
			$('div#yesspecimentwo').hide( 'fast' );
		}
	});
	//end provide specimen options
	//END toggle radio buttons content

	//start affiliation options

	$('#attorney-bar-standing').change(function(){
		//$('.hidethisdiv').hide( 'fast' );
		$('#' + $(this).val()).show( 'fast' );

		if($(this).val() == "usaffiliation"){
			$('#user-accept-checkbox').attr("required", true);

			$('#canadianaffiliation').hide( 'fast' );
		}
		else{
			$('#user-accept-checkbox').attr("required", false);
			$('#usaffiliation').hide( 'fast' );
		}

	});
	//end affiliation options
	//start nameoftypeofbusiness options

	$( '#type' ).on('change',function(){
		$( 'div#nametype' ).show( 'fast' );
	});
	//end nameoftypeofbusiness options

	//START entity options (import concept)
	//us form



	$('#entypeforeignbahamas').on('change',function(){
		$('#containerforeign').empty();//maybe use detach
		$('footer').css('display','none');
		var includeforeign = ('js/' + $(this).val() + $( '#entitycountry' ).val() + '.js');
		$.getScript( includeforeign );
		$('#owner #autofillforeign').show( 'fast' );
		$('footer').css('display','block');
		//console.log(includeforeign);
	});
	//foreign select by country
	$('.albaniaform, .algeriaform, .angolaform, .bahamasform').css('display','none');
	$('#entitycountry').on('change',function(){
		var loadselectmenu = ('.' + $(this).val() + 'form');
		$('.hidethis').hide('fast');
		$( loadselectmenu ).show('slow').addClass('hidethis');
		$( loadselectmenu ).css('display','block');
	});
	//END entity options (import concept)

	//select signature method

	$('#signmethod').on('change',function(){
		var loadsign = ('#' + $(this).val());
		$('.hidethis').hide('fast');
		$( loadsign ).show('fast').addClass('hidethis');
		$( loadsign ).css('display','block');
	});
	//END signature method

	//END fill from contacts values -- attorney

	//START auto-detect pre-fill
	//var arrlanguages = [ 'Spanish', 'German', 'French' ];
//	var arrlanguage = jQuery.makeArray( arrlanguages );
//	var arrlangtranslits = [ 'German', 'Korean', 'French', 'Spanish' ];
//	var arrlangtranslit = jQuery.makeArray( arrlangtranslits );
//	  $( '#detected textarea#ta2' ).val( 'Scrubby Butts Soap Co. Squeaky Clean Naturally!' );
//	  $( '#detected textarea#ta3' ).val( 'Red' );
//	  $( '#detected #language' ).val( arrlanguage[0] );
//	  $( '#detected #engtranslation' ).val('Jabón' );
//	  $( '#detected input#inthemark' ).val( 'Soap' );
//	  $( '#detected #languagetranslit' ).val( arrlangtranslit[1] );
//	  $( '#detected input#nonlatranslation' ).val( '비누' );
//	  $( '#detected input#inenglish' ).val( 'Soap' );
	//END auto-detect pre-fill

	//START contacts, fees, my mark components
	//start toggle glyphicon contacts widget
	function togglecontacts() {
		$( '#mydata2' ).find('span.glyphicon-ok-sign').parent().parent().css('background-color','#9BB8D3').css('color','#fff').siblings().css('background-color','#9BB8D3').css('color','#fff');
		$( this ).find('span.glyphicon-plus-sign').toggleClass( 'glyphicon-ok-sign' ).attr('aria-label','select this contact');
		$( this ).find('span.glyphicon-ok-sign').parent().parent().css('background-color','#D4EB8E').css('color','#333').siblings().css('background-color','#D4EB8E').css('color','#333');
		$( this ).find('span.glyphicon-ok-sign').parent().parent().parent().siblings().children().children().children('.glyphicon-ok-sign').removeClass( 'glyphicon-ok-sign' );
		$( this ).find('span.glyphicon-ok-sign').attr('aria-label','deselect this contact');
	}
	$('a.fromcontact').click(togglecontacts);
	//start toggle glyphicon contacts widget
	//start toggle glyphicon dashboard toggle menu widget
	function togglecolumnselection() {
		$( this ).find('span.glyphicon-eye-open').toggleClass( 'visuallyhidden' ).attr('aria-label','show this column');
	}
	$('a.toggle-vis').click(togglecolumnselection);
	//end toggle glyphicon contacts widget

	//start close (x) fees
	$('button#closefee').click(function() {
		$( '#mydata' ).css('visibility','hidden');
		$( '#mydata .collapse' ).collapse('hide').fadeOut( 'slow','swing');
		$( '#mydata button#feebtn span' ).toggleClass('visuallyremoved');
		$( '#mydata button#feebtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	//end close (x) fees

	//start show fees from nav
	$('a#showfees').click(function() {
		$( '#mydata' ).css('visibility','visible');
		$( '#mydata .collapse' ).collapse('show').fadeIn( 'slow','swing');
		$( '#mydata button#feebtn span' ).toggleClass('visuallyremoved');
		$( '#mydata button#feebtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	//end show fees from nav

	//start show fees from widget
	$("button#feebtn").click(function() {
		$( '#mydata' ).css('visibility','visible');
		$( '#mydata .collapse' ).collapse('show').fadeIn( 'slow','swing');
		$( 'button#feebtn span' ).toggleClass('visuallyremoved');
		$( 'button#feebtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	//end show fees from widget

	//start close (x) managed contacts
	$('button#closecontacts').click(function() {
		$( 'button#contactsbtn span#toggleglyphone' ).toggleClass('visuallyremoved','visuallyadded');
		$( 'button#contactsbtn span#toggleglyph' ).toggleClass('visuallyadded','visuallyremoved');
		$( '#mydata2 .collapse' ).collapse('hide').fadeOut( 'slow','swing');
		$( '#mydata2' ).css('visibility','hidden');
	});
	//end close (x) managed contacts

	//start show managed contacts from widget
	$("button#contactsbtn").click(function() {
		$( '#mydata2' ).css('visibility','visible');
		$( '#mydata2 .collapse' ).collapse('show').fadeIn( 'slow','swing');
		$( 'button#contactsbtn span#toggleglyphone' ).toggleClass('visuallyremoved','visuallyadded');
		$( 'button#contactsbtn span#toggleglyph' ).toggleClass('visuallyadded','visuallyremoved');
	});
	//end show managed contacts from widget

	//start show managed contacts from nav
	$('a#showcontacts').click(function() {
		$( '#mydata2' ).css('visibility','visible');
		$( '#mydata2 .collapse' ).collapse('show').fadeIn( 'slow','swing');
	});
	//populate form from managed contacts
	$('button#autofill, button#autofillforeign').on('click',function() {
		$( '#mydata2' ).css('visibility','visible');
		$( '#mydata2 .collapse' ).collapse('show').fadeIn( 'slow','swing');
		$( 'button#contactsbtn span#toggleglyphone' ).addClass('visuallyremoved');
		$( 'button#contactsbtn span#toggleglyph' ).addClass('visuallyadded');
	});
	//end show managed contacts from nav


	//search managed contacts
	$("#contactsearch").on("keyup", function() {


		var value = $(this).val().toLowerCase();


		$("#contactssearch tr").filter(function() {

			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	//search managed contacts

	//edit owner accordion button toggle
	$('#editowner button.Accordion-trigger, #reviewattorney button.Accordion-trigger').click(function() {
		//$( this ).children('span.glyphicon-chevron-right').toggleClass('visuallyremoved');
		//$( this ).children('span.glyphicon-chevron-down').toggleClass('visuallyadded');
		$( this ).children('span.glyphicon-triangle-right').toggleClass('visuallyremoved');
		$( this ).children('span.glyphicon-triangle-bottom').toggleClass('visuallyadded');
	});	//edit owner accordion button toggle rev
	$( document ).on('click','#editowner button.Accordion-trigger, #reviewattorney button.Accordion-trigger', function() {
		$( this ).siblings('span.glyphicon-triangle-right').removeClass('visuallyadded').toggleClass('visuallyremoved');
		$( this ).siblings('span.glyphicon-triangle-bottom').removeClass('visuallyremoved').toggleClass('visuallyadded');
	});
	$( document ).on('click','#editowner .panel-title span.glyphicon-triangle-right, #reviewattorney .panel-title span.glyphicon-triangle-right', function() {
		$( this ).addClass('visuallyremoved').removeClass('visuallyadded');
		$( this ).siblings( 'span.glyphicon-triangle-bottom' ).addClass('visuallyadded').removeClass('visuallyremoved');
	});
	$( document ).on('click','#editowner .panel-title span.glyphicon-triangle-bottom, #reviewattorney .panel-title span.glyphicon-triangle-bottom', function() {
		$( this ).addClass('visuallyremoved').removeClass('visuallyadded');
		$( this ).siblings( 'span.glyphicon-triangle-right' ).addClass('visuallyadded').removeClass('visuallyremoved');
	});
	//edit owner accordion button toggle rev
	//edit owner accordion button toggle

	//END additional phone

	//START concurrent use, 2(f) Claim
	$( document ).on('click','.resetregbtn',function(){
		$( this ).parent().parent().remove();
	});
	//start additional registration 2(f)
	$( document ).on('click','button#addreg2f',function(){
		$( 'div.reg:eq(0)' ).clone().appendTo( '#yespriors .appendreg' );
		$( 'div.reg' ).last().find('input').val('');
		$( 'div.reg' ).last().find('textarea').val('');
		$( '#yespriors .appendreg .resetreg' ).removeClass( 'visuallyremoved' );
		$( this ).removeClass( '.addinitial' );
	});
	//end additional registration 2(f) add/remove

	//start additional registration no courtd
	$( document ).on('click','button#addreg',function(){
		$( 'div.holdsaconcregapp_courtd:eq(0)' ).clone().appendTo( '.appendaconcregapp_courtd' );
		$( 'div.holdsaconcregapp_courtd' ).last().find('input').val('');
		$( 'div.holdsaconcregapp_courtd' ).last().find('textarea').val('');
		$( 'div.holdsaconcregapp_courtd').last().find('input.checkmark').prop('checked', false);
		$( '.appendaconcregapp_courtd .resetreg' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em');
		$( this ).removeClass( '.addinitial' );
	});
	//end additional registration no courtd

	//start additional registration no ttab
	$( document ).on('click','button#addttab',function(){
		$( 'div.holdsaconcregapp_ttab:eq(0)' ).clone().appendTo( '.appendaconcregapp_ttab' );
		$( 'div.holdsaconcregapp_ttab' ).last().find('input').val('');
		$( 'div.holdsaconcregapp_ttab' ).last().find('textarea').val('');
		$( 'div.holdsaconcregapp_ttab').last().find('input.checkmark').prop('checked', false);
		$( '.appendaconcregapp_ttab .resetreg' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em');
		$( this ).removeClass( '.addinitial' );
	});
	//end additional registration no ttab

	//start additional registration no conflicting
	$( document ).on('click','button#addconflicting',function(){
		$( 'div.holdsaconcregapp_conflictingreg:eq(0)' ).clone().appendTo( '.appendaconcregapp_conflictingreg' );
		$( 'div.holdsaconcregapp_conflictingreg' ).last().find('input').val('');
		$( 'div.holdsaconcregapp_conflictingreg' ).last().find('textarea').val('');
		$( 'div.holdsaconcregapp_conflictingreg').last().find('input.checkmark').prop('checked', false);
		$( '.appendaconcregapp_conflictingreg .resetreg' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em');
		$( this ).removeClass( '.addinitial' );
	});
	//end additional registration no conflicting

	//start additional registration no earlieruse
	$( document ).on('click','button#addearlieruse',function(){
		$( 'div.holdsaconcregapp_earlieruse:eq(0)' ).clone().appendTo( '.appendaconcregapp_earlieruse' );
		$( 'div.holdsaconcregapp_earlieruse' ).last().find('input').val('');
		$( 'div.holdsaconcregapp_earlieruse' ).last().find('textarea').val('');
		$( 'div.holdsaconcregapp_earlieruse').last().find('input.checkmark').prop('checked', false);
		$( '.appendaconcregapp_earlieruse .resetreg' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em');
		$( this ).removeClass( '.addinitial' );
	});
	//end additional registration no earlieruse
	//END concurrent use, 2(f) Claim add/remove

	//END additional docket

	//START additional containsname
	$( 'button#addaname' ).on('click',function(){
		$( '.containsaname:eq(0)' ).clone().appendTo( '.appendaname' );
	});
	$( '#resetaname' ).on('click',function () {
		$( '.appendaname .containsaname' ).remove( '.containsaname:eq(0)' );
	});
	//END additional containsname

	//START pending + foreign
	//Basis one

	$('div#yescommerce input#pendingtwo_basisone').change(function() {
		if(this.checked == true){
			$(this).parent().parent().parent().find('div.holdsapending').show( 'fast' );
			$('button#addpending_basisone').show( 'fast' );
			$('input#natwo_basisone').prop({
				checked: false,
			});
		}
		else {
			$(this).parent().parent().parent().find('div.holdsapending').hide( 'fast' );
			$('button#addpending_basisone').hide( 'fast' );
		}
	});

	$('div#yescommerce input#foreigntwo_basisone').change(function() {
		if(this.checked == true){
			$(this).parent().parent().parent().find('div.holdsaforeign').show( 'fast' );
			$('button#addforeignreg_basisone').show( 'fast' );
			$('input#natwo_basisone').prop({
				checked: false,
			});
		}
		else {
			$(this).parent().parent().parent().find('div.holdsaforeign').hide( 'fast' );
			$('button#addforeignreg_basisone').hide( 'fast' );
		}
	});
	//Basis two

	$('div#yesconnection input#pendingtwo_basistwo').change(function() {
		if(this.checked == true){
			$(this).parent().parent().parent().find('div.holdsapending').show( 'fast' );
			$('button#addpending_basistwo').show( 'fast' );
			$('input#natwo_basistwo').prop({
				checked: false,
			});
		}
		else {
			$(this).parent().parent().parent().find('div.holdsapending').hide( 'fast' );
			$('button#addpending_basistwo').hide( 'fast' );
		}
	});

	$('div#yesconnection input#foreigntwo_basistwo').change(function() {
		if(this.checked == true){
			$(this).parent().parent().parent().find('div.holdsaforeign').show( 'fast' );
			$('button#addforeignreg_basistwo').show( 'fast' );
			$('input#natwo_basistwo').prop({
				checked: false,
			});
		}
		else {
			$(this).parent().parent().parent().find('div.holdsaforeign').hide( 'fast' );
			$('button#addforeignreg_basistwo').hide( 'fast' );
		}
	});

	$('input#natwo_basisone').change(function() {
		if(this.checked == true){
			$('div#yescommerce div.holdsaforeign').hide( 'fast' );
			$('div#yescommerce div.holdsapending').hide( 'fast' );
			$('button#addforeignreg_basisone').hide( 'fast' );
			$('button#addpending_basisone').hide( 'fast' );
			$('div#yescommerce input#pendingtwo_basisone').prop({
				checked: false,
			});
			$('div#yescommerce input#foreigntwo_basisone').prop({
				checked: false,
			});
			var resetselect = [ 'Select','mm/dd/yy' ];
			var resetselect = jQuery.makeArray( resetselect );
			$('div#yescommerce div.holdsaforeign input[type=date], div#yescommerce div.holdsapending input[type=date]').val(resetselect[1]);
			$('div#yescommerce div.holdsaforeign textarea, div#yescommerce div.holdsapending textarea').val('');
			$('div#yescommerce div.holdsaforeign input.checkmark, div#yescommerce div.holdsapending input.checkmark').prop('checked', false);
			$('div#yescommerce div.holdsaforeign input[type=text], div#yescommerce div.holdsapending input[type=text]').val('');
			$('div#yescommerce div.holdsaforeign select, div#yescommerce div.holdsapending select').val( resetselect[0] );
			$('div#yescommerce div.holdsaforeign .hidethis, div#yescommerce div.holdsapending .hidethis').contents().css('display','block');
			$('div#yescommerce div.holdsaforeign .upload-drop-zone, div#yescommerce div.holdsapending .upload-drop-zone').css('height','115px');
			$('div#yescommerce div.holdsaforeign div.upload-drop-zone, div#yescommerce div.holdsapending div.upload-drop-zone').css('height','175px');
			$('div#yescommerce div.holdsaforeign .js-upload-finished button.close, div#yescommerce div.holdsapending .js-upload-finished button.close' ).parent().parent().remove();
			$('div#yescommerce div.holdsaforeign div.fileDisplayArea img, div#yescommerce div.holdsapending div.fileDisplayArea img' ).remove();
			$('div#yescommerce div.holdsaforeign div.fileDisplayArea, div#yescommerce div.holdsapending div.fileDisplayArea' ).html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Select File</div>');
			$('div#yescommerce div.appendaforeign div.holdsaforeign, div#yescommerce div.appendaforeign div.holdsapending').remove();
		}
	});
	$('input#natwo_basistwo').change(function() {
		if(this.checked == true){
			$('div#yesconnection div.holdsaforeign').hide( 'fast' );
			$('div#yesconnection div.holdsapending').hide( 'fast' );
			$('button#addforeignreg_basistwo').hide( 'fast' );
			$('button#addpending_basistwo').hide( 'fast' );
			$('div#yesconnection input#pendingtwo_basistwo').prop({
				checked: false,
			});
			$('div#yesconnection input#foreigntwo_basistwo').prop({
				checked: false,
			});
			var resetselect = [ 'Select','mm/dd/yy' ];
			var resetselect = jQuery.makeArray( resetselect );
			$('div#yesconnection div.holdsaforeign input[type=date], div#yesconnection div.holdsapending input[type=date]').val(resetselect[1]);
			$('div#yesconnection div.holdsaforeign textarea, div#yesconnection div.holdsapending textarea').val('');
			$('div#yesconnection div.holdsaforeign input.checkmark, div#yesconnection div.holdsapending input.checkmark').prop('checked', false);
			$('div#yesconnection div.holdsaforeign input[type=text], div#yesconnection div.holdsapending input[type=text]').val('');
			$('div#yesconnection div.holdsaforeign select, div#yesconnection div.holdsapending select').val( resetselect[0] );
			$('div#yesconnection div.holdsaforeign .hidethis, div#yesconnection div.holdsapending .hidethis').contents().css('display','block');
			$('div#yesconnection div.holdsaforeign .upload-drop-zone, div#yesconnection div.holdsapending .upload-drop-zone').css('height','115px');
			$('div#yesconnection div.holdsaforeign div.upload-drop-zone, div#yesconnection div.holdsapending div.upload-drop-zone').css('height','175px');
			$('div#yesconnection div.holdsaforeign .js-upload-finished button.close, div#yesconnection div.holdsapending .js-upload-finished button.close' ).parent().parent().remove();
			$('div#yesconnection div.holdsaforeign div.fileDisplayArea img, div#yesconnection div.holdsapending div.fileDisplayArea img' ).remove();
			$('div#yesconnection div.holdsaforeign div.fileDisplayArea, div#yesconnection div.holdsapending div.fileDisplayArea' ).html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Select File</div>');
			$('div#yesconnection div.appendaforeign div.holdsaforeign, div#yesconnection div.appendaforeign div.holdsapending').remove();
		}
	});
	//END pending + foreign

	//START additional containsportrait
	$( 'button#addaportrait' ).on('click',function(){
		$( '.containsaportrait:eq(0)' ).clone().appendTo( '.appendaportrait' );
	});
	$( '#resetaportrait' ).on('click',function () {
		$( '.appendaportrait .containsaportrait' ).remove( '.containsaportrait:eq(0)' );
	});
	//END additional containsportrait

	//START additional containssignature
	$( 'button#addasignature' ).on('click',function(){
		$( '.containsasignature:eq(0)' ).clone().appendTo( '.appendasignature' );
	});
	$( '#resetasignature' ).on('click',function () {
		$( '.appendasignature .containsasignature' ).remove( '.containsasignature:eq(0)' );
	});
	//END additional containssignaturet

	//START additional foreignreg
	$( document ).on('click','button#addforeignreg_basisone',function(){
		$( 'div#yescommerce div.holdsaforeign' ).last().clone().appendTo( 'div#yescommerce .appendaforeign' );
		$( 'div#yescommerce div.holdsaforeign' ).last().find('input').val('');
		$( 'div#yescommerce div.holdsaforeign' ).last().find('div.hidethis input').attr( 'id', function() {
			return (parseFloat(this.id) + 1);
		});
		var clonelabel = $( 'div.holdsaforeign div.hidethis input' ).last().attr('id');
		$( 'div#yescommerce div.holdsaforeign' ).last().find('textarea').val('');
		$( 'div#yescommerce div.holdsaforeign' ).last().find('.js-upload-finished button.close').parent().parent().remove();
		$( 'div#yescommerce div.holdsaforeign .hidethis' ).last().contents().css('display','block');
		$( 'div#yescommerce div.holdsaforeign .upload-drop-zone' ).last().css('height','115px');
		$( 'div#yescommerce div.holdsaforeign .upload-drop-zone' ).last().attr('for', clonelabel);
		$( 'div#yescommerce div.holdsaforeign #usaffiliation .upload-drop-zone' ).last().css('height','175px');
		$( 'div#yescommerce div.holdsaforeign div.fileDisplayArea img' ).last().remove();
		$( 'div#yescommerce div.holdsaforeign div.fileDisplayArea' ).last().html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Select File</div>');
		$( 'div#yescommerce .appendaforeign .resetreg' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em');
		$( this ).removeClass( '.addinitial' );
	});
	$( document ).on('click','button#addforeignreg_basistwo',function(){
		$( 'div#yesconnection div.holdsaforeign' ).last().clone().appendTo( 'div#yesconnection .appendaforeign' );
		$( 'div#yesconnection div.holdsaforeign' ).last().find('input').val('');
		$( 'div#yesconnection div.holdsaforeign' ).last().find('div.hidethis input').attr( 'id', function() {
			return (parseFloat(this.id) + 1);
		});
		var clonelabel = $( 'div.holdsaforeign div.hidethis input' ).last().attr('id');
		$( 'div#yesconnection div.holdsaforeign' ).last().find('textarea').val('');
		$( 'div#yesconnection div.holdsaforeign' ).last().find('.js-upload-finished button.close').parent().parent().remove();
		$( 'div#yesconnection div.holdsaforeign .hidethis' ).last().contents().css('display','block');
		$( 'div#yesconnection div.holdsaforeign .upload-drop-zone' ).last().css('height','115px');
		$( 'div#yesconnection div.holdsaforeign .upload-drop-zone' ).last().attr('for', clonelabel);
		$( 'div#yesconnection div.holdsaforeign #usaffiliation .upload-drop-zone' ).last().css('height','175px');
		$( 'div#yesconnection div.holdsaforeign div.fileDisplayArea img' ).last().remove();
		$( 'div#yesconnection div.holdsaforeign div.fileDisplayArea' ).last().html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Select File</div>');
		$( 'div#yesconnection .appendaforeign .resetreg' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em');
		$( this ).removeClass( '.addinitial' );
	});
	//END additional foreignreg

	//START additional foreignpending
	$( document ).on('click','button#addpending_basisone',function(){
		$( 'div#yescommerce div.holdsapending' ).last().clone().appendTo( 'div#yescommerce .appendapending' );
		$( 'div#yescommerce div.holdsapending' ).last();
		$( 'div#yescommerce div.holdsapending' ).last().find('input').val('');
		$( 'div#yescommerce div.holdsapending' ).last().find('div.hidethis input').attr( 'id', function() {
			return (parseFloat(this.id) + 1);
		});
		var clonelabel = $( 'div#yescommerce div.holdsapending div.hidethis input' ).last().attr('id');
		$( 'div#yescommerce div.holdsapending' ).last().find('textarea').val('');
		$( 'div#yescommerce div.holdsapending' ).last().find('.js-upload-finished button.close').parent().parent().remove();
		$( 'div#yescommerce div.holdsapending .hidethis' ).last().contents().css('display','block');
		$( 'div#yescommerce div.holdsapending .upload-drop-zone' ).last().css('height','115px');
		$( 'div#yescommerce div.holdsapending .upload-drop-zone' ).last().attr('for', clonelabel);
		$( 'div#yescommerce div.holdsapending #usaffiliation .upload-drop-zone' ).last().css('height','175px');
		$( 'div#yescommerce div.holdsapending div.fileDisplayArea img' ).last().remove();
		$( 'div#yescommerce div.holdsapending div.fileDisplayArea' ).last().html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Select File</div>');
		$( 'div#yescommerce .appendapending .resetreg' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em').css('margin-top','1.5em');
		$( this ).removeClass( '.addinitial' );
	});
	$( document ).on('click','button#addpending_basistwo',function(){
		$( 'div#yesconnection div.holdsapending' ).last().clone().appendTo( 'div#yesconnection .appendapending' );
		$( 'div#yesconnection div.holdsapending' ).last();
		$( 'div#yesconnection div.holdsapending' ).last().find('input').val('');
		$( 'div#yesconnection div.holdsapending' ).last().find('div.hidethis input').attr( 'id', function() {
			return (parseFloat(this.id) + 1);
		});
		var clonelabel = $( 'div#yescommerce div.holdsapending div.hidethis input' ).last().attr('id');
		$( 'div#yesconnection div.holdsapending' ).last().find('textarea').val('');
		$( 'div#yesconnection div.holdsapending' ).last().find('.js-upload-finished button.close').parent().parent().remove();
		$( 'div#yesconnection div.holdsapending .hidethis' ).last().contents().css('display','block');
		$( 'div#yesconnection div.holdsapending .upload-drop-zone' ).last().css('height','115px');
		$( 'div#yesconnection div.holdsapending .upload-drop-zone' ).last().attr('for', clonelabel);
		$( 'div#yesconnection div.holdsapending #usaffiliation .upload-drop-zone' ).last().css('height','175px');
		$( 'div#yesconnection div.holdsapending div.fileDisplayArea img' ).last().remove();
		$( 'div#yesconnection div.holdsapending div.fileDisplayArea' ).last().html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Select File</div>');
		$( 'div#yesconnection .appendapending .resetreg' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em').css('margin-top','1.5em');
		$( this ).removeClass( '.addinitial' );
	});
	//END additional foreignpending

	//START additional translate
	$( 'button#addtranslate' ).on('click',function(){
		$( '.holdsatranslate:eq(0)' ).clone().appendTo( '.appendatranslate' );
	});
	$( '#resettranslate' ).on('click',function () {
		$( '.appendatranslate .holdsatranslate' ).remove( '.holdsatranslate:eq(0)' );
	});
	//END additional translate

	//START additional transliteration
	$( 'button#addtransliterate' ).on('click',function(){
		$( '.holdsatransliterate:eq(0)' ).clone().appendTo( '.appendatransliterate' );
	});
	$( '#resettransliterate' ).on('click',function () {
		$( '.appendatransliterate .holdsatransliterate' ).remove( '.holdsatransliterate:eq(0)' );
	});
	//END additional transliteration

	//START additional disclaimer
	$( document ).on('click','button#addisclaimer',function(){
		$( 'div.disclaim:eq(0)' ).clone().appendTo( '.appenddisclaim' );
		$( 'div.disclaim' ).last().find('input').val('');

		$( 'div.disclaim' ).last().find('[disclaimer-index]').removeAttr("disclaimer-index");


		$( '.appenddisclaim .resetdisclaim' ).removeClass( 'visuallyremoved' );
		$( this ).removeClass( '.addinitial' );
	});




	//START modals
	$('#tradeservmodal','#collectivemodal','#collectivemembmodal','#loginmodal','#emailmodal','#securitymodal','#passwordmodal','#soucontent .modal').on('shown.bs.modal', function () {
		$('.btn-success').focus();
	})
	//END modals

	//START show standard character preview
	$( '#ta2' ).keyup(function(){
		var d = $( '#entertext' ).next('div');
		var currentText = $(this).val();
		$( 'p#showmarktxt' ).text(currentText);
		$('#entertext, #entertext textarea').css( 'height', (d.innerHeight()) );
	});
	//var str = $( '#ta2' ).val();
//	$( 'button#displaytxt' ).on('click',function(){
//		$( 'p#displaytext' ).html( str );
//	});
	//END show standard character preview

	//START displaymark height match
	$( window ).load(function () {
		var d = $( '#entertext' ).next('div');
		$('#displaytext').css('display','flex').css('flex-direction','column');
		$('#entertext').css( 'min-height', (d.innerHeight()) );
		$( window ).resize(function() {
			$('#entertext').css( 'min-height', (d.innerHeight()) );
		});
	});
	//END displaymark height match

	//START close button height match SOU labels
	$('div.gsmodal').on('show.bs.modal', function() {
		var d = $('div.gsmodal button.closegspanels').parent().find('span.closepans');
		var c = $('div.gsmodal button.closegspanels span.label');
		var f = $('div.gsmodal button.closegspanels');
		var e = $('div.gsmodal span.closepans').eq(0).parent();
		$(d).css('display','table-cell').css('vertical-align','middle');
		$(f).css('display','table-cell').css('vertical-align','middle');
		$(c).css('min-height','24%');
		$('div.gsmodal button.closegspanels').css('line-height',(c.innerHeight() + 'px')).css( 'height',(c.innerHeight()));
	});
	//
	//START SOU modal button add/remove GSs
	$('div.inusegs span.label button').on('click',function() {
		var colorClass = this.className;
		var count = $(this).parent().parent('div.inusegs').find('span.glyphicon-remove-circle');
		if ((colorClass) == ('close closegspanels')) {
			$(this).parent().css('background','#ecf1f3').css('color','#999');
			$(this).children('span').addClass('glyphicon-refresh').removeClass('glyphicon-remove-circle');
			$(this).toggleClass('refresh');
			$(this).attr('aria-label','undo delete this good / service');
		}
		if ((colorClass) == ('close closegspanels refresh')) {
			$(this).parent().css('background','#cbd6da').css('color','#333');
			$(this).children('span').removeClass('glyphicon-refresh').addClass('glyphicon-remove-circle');
			$(this).toggleClass('refresh');
			$(this).attr('aria-label','delete');
		}
		if ((count).length == 1) {
			$(this).closest( 'div.gsmodal' ).parent().find('div.statementou').css('opacity','0.4');
			$(this).closest( 'div.gsmodal' ).parent().find('div.statementou select option').prop('disabled', true);
			$(this).closest( 'div.gsmodal' ).parent().find('div.formodal').css('display','block');
		}
	});
	//
	//START SOU modal strike/unstrike GSs from blue bar
	$('div.inusegs button').on('click',function() {
		var colorClass = this.className;
		label = $(this).parent().parent('div.inusegs').find('button.closegspanels');
		var labelindex = $(this).index('div.inusegs button.closegspanels');
		if ((colorClass) === ('close closegspanels')) {
			var buttonparent = $(this).parents('div.modal');
			var buttongrandparent = $( buttonparent ).parent();
			$('.statementou h2.displaycell span.listed').eq( labelindex ).removeClass('strike');
			$('.statementou h2.displaycell span.listed span.sr-only').eq( labelindex ).text('added');
		}
		if ((colorClass) === ('close closegspanels refresh')) {
			var buttonparent = $(this).parents('div.modal');
			var buttongrandparent = $( buttonparent ).parent();
			$('.statementou h2.displaycell span.listed').eq( labelindex ).addClass('strike');
			$('.statementou h2.displaycell span.listed span.sr-only').eq( labelindex ).text('removed');
		}
	});
	//
	//START SOU modal add/remove class
	if ( $( 'div#soucontent' ).length ) {
		$('div.formodal').css('display','none');
	}
	$('div.classmodal button.deleteclass').on('click',function() {
		var modaldiv = $('div.formodal');
		var modaldivparent = $(this).parents('div.modal');
		var modaldivgrandparent = $(modaldivparent).parent();
		var modalgs = $(modaldivparent).closest('div.gsmodal');
		$( modaldivparent ).find('span.sr-only').text('class removed');
		$( modaldivgrandparent ).find('div.inusegs span.subtle').css('display','none');
		$( modaldivparent ).parent().find('div.statementou').css('opacity','0.4');
		$( modaldivparent ).parent().find('div.statementou select option').prop('disabled', true);
		$( modaldivparent ).modal('hide');
		$( modaldivparent ).parent().find('div.formodal').css('display','block');
		$(function() {
			var gsbuttons = $(modalgs).find('div.inusegs span.label button');
			$( gsbuttons ).parent().css('background','#ecf1f3').css('color','#999');
			$( gsbuttons ).children('span').addClass('glyphicon-refresh').removeClass('glyphicon-remove-circle');
			$( gsbuttons ).toggleClass('refresh');
			$( gsbuttons ).attr('aria-label','undo delete this good / service');
		});
		$(this).closest('div.classmodal').parent().find('.statementou h2.displaycell span.listed').addClass('strike');
		$(this).closest('div.classmodal').parent().find('.statementou h2.displaycell span.listed span.sr-only').text('removed');
	});
	$('button.resetclassbtn').on('click',function() {
		var modaldiv = $(this).parents('div.formodal');
		var modaldivparent = $(this).parents('div.modal');
		var modaldivgrandparent = $(modaldiv).parent();
		var modalgs = $(modaldiv).siblings('div.gsmodal');
		$( modaldivgrandparent ).find('div.inusegs span.subtle').css('display','table');
		$( modaldiv).parent().find('div.statementou').css('opacity','1.0');
		$( modaldiv ).parent().find('div.statementou select option').eq(0).prop('disabled', false);
		$( modaldiv ).parent().find('div.statementou select option').eq(1).prop('disabled', false);
		$( modaldiv ).parent().find('div.statementou select option').eq(2).prop('disabled', false);
		$( modaldivparent ).find('span.sr-only').text('class added');
		$( modaldiv ).css('display','none');
		$(function() {
			var gsbuttons = $(modalgs).find('div.inusegs span.label button');
			$( gsbuttons ).parent().css('background','#cbd6da').css('color','#333');
			$( gsbuttons ).children('span').removeClass('glyphicon-refresh').addClass('glyphicon-remove-circle');
			$( gsbuttons ).toggleClass('refresh');
			$( gsbuttons ).attr('aria-label','delete');
		});
		$(this).closest('div.formodal').parent().find('.statementou h2.displaycell span.listed').removeClass('strike');
		$(this).closest('div.formodal').parent().find('.statementou h2.displaycell span.listed span.sr-only').text('added');
	});
	//

	//row header p height match
	var p = $('.rowheader p').height();
	$('.rowheader p').height(p);
	$( window ).resize(function() {
		$('.rowheader p').height(p);
	});
	//END row header p height match

	//row header h3 height match
	var q = $('.rowheader h3').height();
	$('.rowheader h3').height(q);
	$( window ).resize(function() {
		$('.rowheader h3').height(q);
	});
	//END row header p height match

	//tabpanels
	$('.tabwidget').tabs();
	$('ul[role="tablist"] a').on('click',function (){
		$(this).parent('li').toggleClass('notice');
		$(this).parent('li').siblings('li').removeClass('notice');
	});
	//tabpanels

	//toggle save more labels
	$('.togglesavemore').change(function() {
		if(this.checked == true){
			$( document ).find('span.subtle').removeClass('visuallyremoved');
			$( document ).find('#on').removeClass('visuallyhidden');
		}
		else if(this.checked == false) {
			$( document ).find('span.subtle').addClass( 'visuallyremoved' );
			$( document ).find('#on').addClass('visuallyhidden');
		}
	});
	//toggle save more labels

	//toggle save more labels
	$('.togglesavemore').change(function() {
		if(this.checked == true){
			$( document ).find('span.subtle').removeClass('visuallyremoved');
			$( document ).find('#on').removeClass('visuallyhidden');
			setTEASValidatioOption("yes");
		}
		else if(this.checked == false) {
			$( document ).find('span.subtle').addClass( 'visuallyremoved' );
			setTEASValidatioOption("no");
		}
	});


	//start uncommoninfo options

	$('input#inlineRadio045').change(function() {
		if(this.checked == true){
			$('div#yesnotpreviously').show( 'fast' );
		}
		else {
			$('div#yesnotpreviously').hide( 'fast' );
		}
	});
	$('input#inlineRadio046').change(function() {
		if(this.checked == true){
			$('div#yesnotpreviously').hide( 'fast' );
		}
		else {
			$('div#yesnotpreviously').show( 'fast' );
		}
	});
	//end uncommoninfo options



	//START additional info page supplemental register checked
	$('input[type=radio]#inlineRadio032').change(function() {

		if(this.checked == true){

			$('#tr div.alert').addClass('visuallyadded');
			$('#tr div.alert').removeClass('visuallyremoved');
		}
	});
	$('input[type=radio]#inlineRadio031').change(function() {

		if(this.checked == true){

			$('#tr div.alert').addClass('visuallyremoved');
			$('#tr div.alert').removeClass('visuallyadded');
		}
	});


//start revive petition s1 options

	//$('input#inlineRadio072').change(function() {
		//if(this.checked == true){
			//$('div#reviveappform').css('display','block');
			//$('div#reviveappformradios').show('fast','swing');
			//$('fieldset#responsefile').hide('fast');
			//$('fieldset#edit').show('fast','swing');
			//$('fieldset#resp').show('fast','swing');
			//$('.hidethis').hide('fast','swing');
			//$('select.signmethod').val('Select');
			//$('input#inlineRadio074, input#inlineRadio075').prop({
				checked:false,
			//});
			//$('div.ql-editor').empty();
		//}
	//});
	//$('input#inlineRadio073').change(function() {
		//if(this.checked == true){
			//$('div#reviveappform').hide('fast','swing');
			//$('fieldset#responsefile').css('display','block');
			//$('div#reviveappformradios').show('fast');
		//}
	//});
	//$('input#inlineRadio074').change(function() {
		//if(this.checked == true){
			//$('div#reviveappform').show('fast','swing');
			//$('fieldset#edit').show('fast','swing');
			//$('fieldset#resp').show('fast','swing');
			//$('.hidethis').hide('fast','swing');
			//$('select.signmethod').val('Select');
			//$('div.ql-editor').empty();
		//}
	//});
	//$('input#inlineRadio075').change(function() {
		//if(this.checked == true){
			//$('div#reviveappform').show('fast','swing');
			//$('div#reviveappform').find('fieldset#edit').hide('fast');
			//$('div#reviveappform').find('fieldset#resp').hide('fast');
		//}
	//});
    
    //START revive petition options			
	//$('div#reviveappformradios').css('display','none');
	//$('div#reviveappformdisplay').css('display','block');
	//$('div#reviveappform').css('display','none');
	//$('div#reconsideration').css('display','none');
	//$('div#noreconsideration').css('display','none');
	//$('div#yesreconsideration').css('display','none');
	//$('div#yesttabreconsideration').css('display','none');
	//$('div#nottabreconsideration').css('display','none');
	//$('div#newpetradios').css('display','none');
	//$('div#twomonths').css('display','none');
	//$('div#petdiradios').css('display','none');
	//$('div#receipt').css('display','none');
	//$('div#received').css('display','none');
	//$('div#expedite').css('display','none');
	//$('div#reconsidersig').css('display','none');
	//$('div#petsignature').css('display','none');
	//$('div#xreq').css('display','none');
	//$('div#sou').css('display','none');
	//$('div#requestnoa').css('display','none');
	//$('div#fees').css('display','none');
	//$('div#souquestion').css('display','none');
	
    //clear signature inputs when user selects alternate radio option
	function clear() { 
		$('div#reviveappform textarea.datesigned').val('');
		$('div#reviveappform .hidethis input').val('');
		$('div#reviveappform select.signmethod').val('Select');
		$('div#reviveappform .hidethis').hide('fast');
		}
	function clearttab() { 
		$('div#yesttabreconsideration textarea.datesigned').val('');
		$('div#yesttabreconsideration .hidethis input').val('');
		$('div#yesttabreconsideration select.signmethod').val('Select');
		$('div#yesttabreconsideration .hidethis').hide('fast');
		}
	function clearreconsidersig() { 
		$('div#reconsidersig textarea.datesigned').val('');
		$('div#reconsidersig .hidethis input').val('');
		$('div#reconsidersig select.signmethod').val('Select');
		$('div#reconsidersig .hidethis').hide('fast');
		}
	function clearexpedite() { 
		$('div#expedite textarea.datesigned').val('');
		$('div#expedite .hidethis input').val('');
		$('div#expedite select.signmethod').val('Select');
		$('div#expedite .hidethis').hide('fast');
		}
	function clearreconsider() { 
		$('div#reconsideration textarea.datesigned, div#noreconsideration textarea.datesigned, div#expedite textarea.datesigned, div#received textarea.datesigned').val('');
		$('div#reconsideration .hidethis input, div#noreconsideration .hidethis input, div#expedite .hidethis input, div#received .hidethis input').val('');
		$('div#reconsideration select.signmethod, div#noreconsideration select.signmethod, div#expedite select.signmethod, div#received select.signmethod').val('Select');
		$('div#reconsideration .hidethis, div#noreconsideration .hidethis, div#expedite .hidethis, div#received .hidethis').hide('fast');
		}
		//

	$('input#inlineRadio074').change(function() {
		if(this.checked == true){
			$(clear);
			$('fieldset#edit, fieldset#resp, div#reviveappform').show('fast','swing');
			}
	});
	$('input#inlineRadio075').change(function() {
		if(this.checked == true){
			$(clear);
			$('div#reviveappform fieldset#resp, div#reviveappform fieldset#edit').hide('fast');
			$('div#reviveappform').show('fast','swing');
			}
	});
	$('input#inlineRadio078,input#inlineRadio079').change(function() {
		$(clear);
		$('div#reviveappform div.ql-editor').empty();
		$('div#reviveappform input').prop({
			checked:false,
		});
		$('div#reviveappform input').val('');
		$('div#reconsideration, div#noreconsideration, div#yesttabreconsideration, div#nottabreconsideration, div#reviveappform').hide('fast');
		$('div#reviveappform').show('fast','swing');
	});
	$('input#inlineRadio080').change(function() {
		$(clearreconsider);
		$('div#reconsideration input,div#noreconsideration input,div#yesttabreconsideration input,div#nottabreconsideration input').prop({
			checked:false,
		});
		$('div#nottabreconsideration, div#noreconsideration').hide('fast');
		$('div#reconsideration').show('fast','swing');
	});
	$('input#inlineRadio081').change(function() {
		$(clearreconsider);
		$('div.ql-editor').empty();
		$('div#reconsideration input,div#noreconsideration input,div#yesttabreconsideration input,div#nottabreconsideration input').prop({
			checked:false,
		});
		$('div#nottabreconsideration, fieldset#tabappeal, div#reconsideration').hide('fast');
		$('div#noreconsideration').show('fast','swing');
	});
	//
	$('input#inlineRadio076,input#inlineRadio082').change(function() {
		$('div#nottabreconsideration input').prop({
			checked:false,
		});
		$('div#nottabreconsideration').hide('fast');
		$('div#yesttabreconsideration').show('fast');
	});
	$('input#inlineRadio077,input#inlineRadio083').change(function() {
		$('div#yesttabreconsideration').hide('fast','swing');
		$('div#nottabreconsideration').show('fast','swing');
	});
	$('input#inlineRadio084').change(function() {
		$('div#newpetradios input, div#twomonths input, div#petdiradios input, div#reviveappformradios input, div#expedite input, div#received input, div#receipt input').val('');
		$('div#newpetradios input, div#twomonths input, div#petdiradios input, div#reviveappformradios input, div#expedite input, div#received input, div#receipt input').prop({
			checked:false,
		});
		$('div.ql-editor').empty();
		$('textarea.datesigned, .hidethis input').val('');
		$('select.signmethod').val('Select');
		$('div#twomonths, div#newpetradios, div#twomonths, div#petdiradios, div#reviveappformradios, div#expedite, div#received, div#receipt, .hidethis').hide('fast');
		$('div#newpetradios').show('fast','swing');
	});
	$('input#inlineRadio085').change(function() {
		$('div#newpetradios input, div#twomonths input, div#petdiradios input, div#reviveappformradios input, div#expedite input, div#received input, div#receipt input').val('');
		$('div#newpetradios input, div#twomonths input, div#petdiradios input, div#reviveappformradios input, div#expedite input, div#received input, div#receipt input').prop({
			checked:false,
		});
		$('div.ql-editor').empty();
		$('textarea.datesigned, .hidethis input').val('');
		$('select.signmethod').val('Select');
		$('.hidethis, div#newpetradios, div#twomonths, div#petdiradios, div#reviveappformradios, div#expedite, div#received, div#receipt').hide('fast');
		$('div#twomonths').show('fast','swing');
	});
	$('input#inlineRadio088').change(function() {
		$(clearreconsider);
		$('div#petdiradios input, div#souquestion input').prop({
			checked:false,
		});
		$('div#petdiradios, div#xreq, #souquestion, #reconsideration').hide('fast');
		$('div#receipt').show('fast','swing');
	});
	$('input#inlineRadio089').change(function() {
		$(clearreconsider);
		$('textarea.datesigned, .hidethis input, div#newpetradios input, div#reviveappformradios input, div#expedite input, div#received input, div#receipt input, div#reconsidersig input, div#souquestion input, div#requestnoa input, div#fees input').val('');
		$('div#newpetradios input, div#reviveappformradios input, div#expedite input, div#received input, div#receipt input, div#reconsidersig input').prop({
			checked:false,
		});
		$('div.ql-editor').empty();
		$('select.signmethod').val('Select');
		$('div#newpetradios, div#reviveappformradios, div#expedite, div#received, div#receipt, div#reconsidersig, div#yesttabreconsideration, div#nottabreconsideration, div#xreq, #souquestion, #reconsideration, #requestnoa, #fees').hide('fast');
		$('div#petdiradios').show('fast','swing');
	});
	$('input#inlineRadio092').change(function() {
		$(clearreconsider);
		$('input#inlineRadio094, input#inlineRadio095').prop({
			checked:false,
		});
		$('div#reviveappformradios, div#expedite').hide('fast');
		$('div#received').show('fast','swing');
	});
	$('input#inlineRadio093').change(function() {
		$('div#received div.ql-editor').empty();
		$('div#received input').val('');
		$(clearreconsider);
		$('div#received').hide('fast');
		$('div#reviveappformradios').show('fast','swing');
	});
	$('input#inlineRadio094, input#inlineRadio095').change(function() {
		$('div#expedite').show('fast','swing');
		$(clearreconsider);
	});
	$('input#inlineRadio096').change(function() {
		$('textarea.datesigned, .hidethis input, div#newpetradios input, div#twomonths input, div#petdiradios input, div#reviveappformradios input, div#expedite input, div#received input, div#receipt input, div#reconsidersig input').val('');
		$('div#newpetradios input, div#twomonths input, div#petdiradios input, div#reviveappformradios input, div#expedite input, div#received input, div#receipt input, div#reconsidersig input, #requestnoa input, #fees input').prop({
			checked:false,
		});
		$('div.ql-editor').empty();
		$('select.signmethod').val('Select');
		$('.hidethis, div#newpetradios, div#twomonths, div#petdiradios, div#reviveappformradios, div#expedite, div#received, div#receipt, div#reconsidersig, div#yesttabreconsideration, div#nottabreconsideration, div#xreq, #reconsideration, #requestnoa, #fees').hide('fast');
		$('div#newpetradios').show('fast','swing');
	});
	$('input#inlineRadio097').change(function() {
		$('textarea.datesigned, .hidethis input, div#newpetradios input, div#twomonths input, div#petdiradios input, div#reviveappformradios input, div#expedite input, div#received input, div#receipt input, div#reconsidersig input').val('');
		$('div#newpetradios input, div#twomonths input, div#petdiradios input, div#reviveappformradios input, div#expedite input, div#received input, div#receipt input, div#reconsidersig input').prop({
			checked:false,
		});
		$('div.ql-editor').empty();
		$('select.signmethod').val('Select');
		$('.hidethis, div#newpetradios, div#twomonths, div#petdiradios, div#reviveappformradios, div#expedite, div#received, div#receipt, div#reconsidersig, div#yesttabreconsideration, div#nottabreconsideration').hide('fast');
		$('div#twomonths').show('fast','swing');
	});
	$('input#inlineRadio098').change(function() {
		$(clearreconsider);
		$('div#reviveappformradios input, div#reconsidersig input, div#nottabreconsideration input').prop({
			checked:false,
		});
		$('div#reviveappformradios, div#expedite, div#received, div#reconsidersig, div#yesttabreconsideration, div#nottabreconsideration').hide('fast');
		$('div#reviveappformradios').show('fast','swing');
		$('div#expedite').show('fast','swing');
	});
	$('input#inlineRadio099').change(function() {
		$(clearreconsider);
		$(clearttab);
		$(clearreconsidersig);
		$('div.ql-editor').empty();
		$('div#reviveappformradios input, div#reconsidersig input, div#nottabreconsideration input').prop({
			checked:false,
		});
		$('div#reviveappformradios, div#expedite, div#received, div#reconsidersig, div#yesttabreconsideration, div#nottabreconsideration').hide('fast');
		$('div#reviveappformradios').show('fast','swing');
		$('div#expedite').show('fast','swing');
	});
	$('input#inlineRadio0100').change(function() {
		$(clearttab);
		$('div#yesttabreconsideration, div#nottabreconsideration').hide('fast');
		$('div#received').show('fast','swing');
		$('div#reconsidersig').show('fast','swing');
	});
	$('input#inlineRadio0101').change(function() {
		$(clearttab);
		$(clearreconsidersig);
		$('div.ql-editor').empty();
		$('div#received, div#reconsidersig, div#yesttabreconsideration, div#nottabreconsideration').hide('fast');
		$('div#reconsidersig input, div#nottabreconsideration').prop({
			checked:false,
		});
		$('div#yesttabreconsideration').show('fast','swing');
	});
	$('input#inlineRadio0102').change(function() {
		$(clear);
		$(clearexpedite);
		$('div#reviveappform input').prop({
			checked:false,
		});
		$('div#reviveappform input').val('');
		$('div#fees, div#requestnoa, div#reconsideration, div#expedite, div#reviveappform').hide('fast');
		$('div#expedite, div#xreq, div#souquestion, div#sou').css('display','block');
		$('div#reviveappform, div#expedite, div#xreq, div#souquestion, div#sou').show('fast','swing');
		
	});
	$('input#inlineRadio0103').change(function() {
		$(clear);
		$(clearexpedite);
		$('div#reviveappform input').prop({
			checked:false,
		});
		$('div#reviveappform input').val('');
		$('div#expedite, div#xreq, div#souquestion, div#sou, div#reconsideration, div#reviveappform').hide('fast');
		$('#requestnoa, div#expedite').css('display','block');
		$('div#reviveappform, #requestnoa, div#expedite').show('fast','swing');
	});

	$('input#inlineRadio0104').change(function() {
		$(clearreconsider);
		$('div#fees input').prop({
			checked:false,
		});
		$('div#fees, div#xreq, div#reconsideration').hide('fast');
		//$('div#expedite').show('fast','swing');
	});
	$('input#inlineRadio0105').change(function() {
		//$(clearexpedite);
		//$('div#expedite').hide('fast');
		$('div#fees').show('fast','swing');
	});
	$('input#inlineRadio0108').change(function() {
		$('div#xreq').show('fast','swing');
		$('div#souquestion').hide('fast');
		$('div#reconsideration').show('fast','swing');
	});
	$('input#inlineRadio0109').change(function() {
		$(clearreconsider);
		$('div#reconsideration, div#souquestion, div#xreq').hide('fast');
		$('div#xreq').show('fast','swing');
	});
    //


	$('select.signmethod').on('change',function(){
		var loadsign = ('.' + $(this).val());
		$(this).closest('fieldset').find('.hidethis').show('fast');
		$( loadsign ).css('display','block');
		if( loadsign == '.Select') {
			$(this).closest('fieldset').find('.hidethis').hide('fast');
		}
		//console.log(loadsign);
	});


	//START initialize Dashboard datable one
		var tableone = $('#dashboardtableone').DataTable({
		"fnDrawCallback": function( oSettings ) {
		},
        "fnInitComplete": function(oSettings) {
           if (oSettings.aiDisplayMaster.length <= 0) {
               $('#dashboardtableone_wrapper').hide();
               $('#dashboardtableone_wrapper').parent().parent().parent().parent().parent().parent().parent().hide();
               $('section#announcements').parent().addClass('col-lg-6').removeClass('col-lg-3').css('margin','0 auto').css('float', 'none');
           }
           else {
                $('#dashboardtableone_wrapper').show();
                $('#dashboardtableone_wrapper').parent().parent().parent().parent().parent().parent().parent().show();
                $('section#announcements').parent().removeClass('col-lg-6').addClass('col-lg-3').css('margin','auto').css('float', 'left');
            }
        },
			'sDom': '<"toolbar">lfrtip',
			"language": {
			"search": "<span class='glyphicon glyphicon-search' aria-hidden='true'></span><span class='sr-only'>search</span>",
			"lengthMenu": "<span class='glyphicon glyphicon-filter' aria-hidden='true'></span><span class='sr-only'>select number of entries to display</span> <select>"+
			  '<option value="10">10</option>'+
			  '<option value="25">25</option>'+
			  '<option value="50">50</option>'+
			  '<option value="100">100</option>'+
			  '<option value="-1">All</option>'+
			  '</select>'
  			},
			'autoWidth': false,
			responsive: {
				details: {
                type: 'column',
				target: 1,
            	},
				breakpoints: [
					{ name: 'desktop', width: Infinity },
					{ name: 'tablet',  width: 1180 },
					{ name: 'fablet',  width: 768 },
					{ name: 'phone',   width: 480 }
				],
			},
			'columns': [
				{ 'width': '10%' },
				{ 'width': '10%' },
				{ 'width': '16%' },
				{ 'width': '20%' },
				{ 'width': '16%' },
				{ 'width': '16%' },
				{ 'width': '16%' },
			  ],
			  'columnDefs': [ 
			  	{ responsivePriority: 1, targets: 0 },//buttons 
				{ responsivePriority: 0, targets: 1 },//(control)
				{ responsivePriority: 2, targets: 2 },//serial
				{ responsivePriority: 4, targets: 3 },//reg
				{ responsivePriority: 3, targets: 4 },//mark
				{ responsivePriority: 6, targets: 5 },//owner
				{ responsivePriority: 5, targets: 6 },//status
				{ className: 'centertxt', 'targets': [ 0,2,3,4,5,6 ] },
				{ className: 'control', 'orderable': true, 'targets': [ 1 ] },
			   ],
		});
	//
    //START a11y datatable hide/show rows
	$( '#dashboardtableone tbody' ).on('click', 'td.control', function(e){
		e.preventDefault();
		$( this ).attr('aria-expanded', function (i, attr) {
			return attr == 'true' ? 'false' : 'true'
		});
		var dtcontent = $( this ).parent().next('tr.child');
		$( dtcontent ).attr('role','alert');
	});
	$( '#dashboardtabletwo tbody' ).on('click', 'td.control', function(e){
		e.preventDefault();
		$( this ).attr('aria-expanded', function (i, attr) {
			return attr == 'true' ? 'false' : 'true'
		});
		var dtcontent = $( this ).parent().next('tr.child');
		$( dtcontent ).attr('role','alert');
	});
	$( '#filepetitiontable tbody' ).on('click', 'td.control', function(e){
		e.preventDefault();
		$( this ).attr('aria-expanded', function (i, attr) {
			return attr == 'true' ? 'false' : 'true'
		});
		var dtcontent = $( this ).parent().next('tr.child');
		$( dtcontent ).attr('role','alert');
	});
	$( '#responseamendtable tbody' ).on('click', 'td.control', function(e){
		e.preventDefault();
		$( this ).attr('aria-expanded', function (i, attr) {
			return attr == 'true' ? 'false' : 'true'
		});
		var dtcontent = $( this ).parent().next('tr.child');
		$( dtcontent ).attr('role','alert');
	});
	$( '#responseamendtabletwo tbody' ).on('click', 'td.control', function(e){
		e.preventDefault();
		$( this ).attr('aria-expanded', function (i, attr) {
			return attr == 'true' ? 'false' : 'true'
		});
		var dtcontent = $( this ).parent().next('tr.child');
		$( dtcontent ).attr('role','alert');
	});
	//
	//START delete datatable table row
	$( '#dashboardtableone tbody .deleterow' ).each(function() {
		$(this).on('click', function(e){
			e.preventDefault();
			var numrows = $( '#dashboardtableone tbody tr' ).length -1;
			var rows = $( '#dashboardtableone tbody tr' );
			var hasdraft = $( this ).closest('tr:contains(Draft)');
			var hasdraftchild = $( this ).closest('tr').next('tr.child:contains(Draft)');
			var draft = 'draft application';
			var focussed = $('span#filingsheader button');
			var dt = $('#dashboardtableone').dataTable();
			var b = $('#alertmin');
			var deletebtn = event.target;
			if ((hasdraft.length > 0) || (hasdraftchild.length > 0)) {
				if ($(b).css('visibility','hidden')) {
					$(b).css('visibility','visible');
					$(b).css('height','auto');
					$(b).addClass('form-group');
					$(b).addClass('form-group-md');
					$(b).css('float','left');
					$(b).css('top','.25em');
					$(b).css('padding','1em');
					$(b).css('padding-top','.5em');
					$(b).css('marginBottom','1em');
					$('#mintextdraft').css('display','table-cell');
					$('#alertbtndash').css('display','block');
					$('#cancelbtndash').css('display','block');
					$('#alertbtndash').focus();
					window.scrollBy(0, '90%');
					$('#mintextdraft').html('Are you sure you want to delete this ' + draft + '?');
					$('#alertbtndash').on('click', function() {
						$(focussed).focus();
						$(hasdraft).remove();
						$(hasdraftchild).remove();
						dt.fnDeleteRow(hasdraft);
						dt.fnDeleteRow(hasdraftchild);
					});
				}
			} else if ((hasdraft.length < 1) || (hasdraftchild.length < 1)) {
				var deletethis = $( this ).closest('tr').next('tr.child');
				var deletethistoo = $( this ).closest('tr');
				$(b).css('visibility','hidden');
				$(b).css('height','1px');
				$(b).removeClass('form-group');
				$(b).removeClass('form-group-md');
				$(b).css('top','-10000px');
				$(b).css('float','none');
				$(b).css('padding','0');
				$(b).css('marginBottom','0');
                $(b).css('margin','0!important');
				$('#mintextdraft').css('display','none');
				$('#alertbtndash').css('display','none');
				$('#cancelbtndash').css('display','none');
				$('#alertbtndash').blur();
				$(deletethis).remove();
				$(deletethistoo).remove();
				dt.fnDeleteRow(deletethis);
				dt.fnDeleteRow(deletethistoo);
			}
		});
	});
	//
	//START initialize Dashboard datable two
		var tabletwo = $('#dashboardtabletwo').DataTable({
		"fnDrawCallback": function( oSettings ) {
		},
        "fnInitComplete": function(oSettings) {
           if (oSettings.aiDisplayMaster.length <= 0) {
               $("#dashboardtabletwo_wrapper").hide();
               $("#dashboardtabletwo_wrapper").parent().parent().hide();
           }
        },
			'sDom': '<"toolbartwo">lfrtip',
			"language": {
			"search": "<span class='glyphicon glyphicon-search' aria-hidden='true'></span><span class='sr-only'>search</span>",
			"lengthMenu": "<span class='glyphicon glyphicon-filter' aria-hidden='true'></span><span class='sr-only'>select number of entries to display</span> <select>"+
			  '<option value="10">10</option>'+
			  '<option value="25">25</option>'+
			  '<option value="50">50</option>'+
			  '<option value="100">100</option>'+
			  '<option value="-1">All</option>'+
			  '</select>'
  			},
			'autoWidth': false,
			responsive: {
				breakpoints: [
					{ name: 'desktop', width: Infinity },
					{ name: 'tablet',  width: 1180 },
					{ name: 'fablet',  width: 768 },
					{ name: 'phone',   width: 380 }
				]
			},
			'columns': [
				{ 'width': '8%' },
				{ 'width': '12%' },
				{ 'width': '20%' },
				{ 'width': '12%' },
				{ 'width': '12%' },
				{ 'width': '12%' },
				{ 'width': '12%' },
				{ 'width': '12%' },
			  ],
			  'columnDefs': [
			  	{ responsivePriority: 1, targets: 0 },//toggle show child rows
				{ responsivePriority: 2, targets: 1 },//serial
				{ responsivePriority: 4, targets: 2 },//reg
				{ responsivePriority: 5, targets: 3 },//mark
				{ responsivePriority: 3, targets: 4 },//owner
				{ responsivePriority: 6, targets: 5 },//due date
				{ responsivePriority: 7, targets: 6 },//status
				{ responsivePriority: 8, targets: 7 },//action
				{ className: 'centertxt', 'targets': [ 1,2,3,4,5,6,7 ] },
				{ className: 'control', 'orderable': false, 'targets': [ 0 ] },
			  ],
		});
	//


	$(function() {
		var progresslength = $('#editattorney .breadcrumb-steps .col-xs-2, #amendmark .breadcrumb-steps .col-xs-2, #editowneroptional .breadcrumb-steps .col-xs-2, #gsinuseoptional .breadcrumb-steps .col-xs-2, #gsselectedoptional .breadcrumb-steps .col-xs-2, div.amend .breadcrumb-steps .col-xs-2').length;
		var progress = $('#editattorney .breadcrumb-steps .col-xs-2, #amendmark .breadcrumb-steps .col-xs-2, #editowneroptional .breadcrumb-steps .col-xs-2, #gsinuseoptional .breadcrumb-steps .col-xs-2, #gsselectedoptional .breadcrumb-steps .col-xs-2, div.amend .breadcrumb-steps .col-xs-2');
		if ((progresslength) === 5)  {
			$(progress).css('width','20%');
		}
		if ((progresslength) === 4)  {
			$(progress).css('width','25%');
		}
		if ((progresslength) === 3)  {
			$(progress).css('width','33.33%');
		}
		if ((progresslength) === 2)  {
			$(progress).css('width','50%');
		}
		if ((progresslength) === 1)  {
			$('#editattorney .displaycell, #amendmark .displaycell, #editowneroptional .displaycell, #gsinuseoptional .displaycell, #gsselectedoptional .displaycell, div.amend .displaycell').css('display','none');
			//console.log(progresslength);
		}
	});




	//START SOU select modals
	$('.statementou select').on('change',function(){
		var loadmodal = ($(this).val());
		//$( loadmodal ).css('display','block');
		if( loadmodal == 'edit') {
			$(this).parents('#soucontent div.modal').modal('hide');
		}
		else if ( loadmodal == 'delete') {
			$(this).parents('.statementou').parent().siblings('div.classmodal').modal('show');
		}
		else if ( loadmodal == 'limit') {
			$(this).parents('.statementou').parent().siblings('div.gsmodal').modal('show');
		}
	});
	$('#soucontent').on('hide.bs.modal', function() {
		var resetselect = [ 'edit' ];
		var resetselect = jQuery.makeArray( resetselect );
		var loadmodalreset = $('.statementou select');
		$(loadmodalreset).val( resetselect[0] );
	});
    
    
    
    
    
    //jQuery Ui Datepicker
	$('input.foreignfiling').each(function(){
		var thatz = new Date();
		function addDays(theDate, days) {
			return new Date(theDate.getTime() + days*24*60*60*1000);
		}
		var newDate = addDays(new Date(), -182.5);
		var strmin = newDate;
		$(this).datepicker({
			showOn: 'button',
			buttonImage: '',
			buttonText: '<span class="sr-only">launch the datepicker</span>',
			prevText: '',
			nextText: '',
			altFormat: '@',
			maxDate: thatz,
			minDate: strmin,
			constrainInput: true,
			changeMonth:true,
			changeYear:true,
			beforeShow: function() {
				makeAccessible();
			},
		});
		function makeAccessible() {
			clearTimeout(makeAccessible.timer);
			$('.ui-datepicker-month').focus();
			if ($('.ui-datepicker.ui-widget .ui-datepicker-calendar').is(':visible')) {
				var cal = $('.ui-datepicker.ui-widget');
				var cal_grid = cal.find('table');
				var grid_thead = cal_grid.find('thead');
				var grid_thead_tr = grid_thead.find('tr');
				var grid_thead_th = grid_thead.find('th');
				var grid_tbody = cal_grid.find('tbody');
				var grid_tbody_tr = grid_tbody.find('tr');
				var grid_tbody_td = grid_tbody.find('td');
				var grid_selectoption = $('.ui-datepicker-header').find('option');
					
				$( cal ).attr('role','region');
				$( grid_selectoption ).each(function () {
					var span_months = 'name of month';
					$( this ).attr('abbr', span_months);
				});
				$( cal_grid ).attr('role', 'grid').attr('tabindex', 0);
				$( grid_thead ).attr('role', 'presentation');
				$( grid_thead_tr ).attr('role', 'row');
				$( grid_thead_th ).each(function () {
					var span_title = $(this).find('span').attr('title');
					$( this ).attr('role', 'columnheader').attr('arial-label', span_title).attr('abbr', span_title);
				});
				grid_tbody.attr('role', 'presentation');
				grid_tbody_tr.each(function () {
					$( this ).attr('role', 'row');
				});
				grid_tbody_td.each(function () {
					var self = $( this );
					var self_link = self.find('a');
					$( self ).attr('role', 'gridcell')
					$( self_link ).attr('tabindex', '0');
				});
			}
			else {
				makeAccessible.timer = setTimeout(makeAccessible, 10);
			}
		}
	});
	$('.datepicker').each(function(){
		$(this).datepicker({
			showOn: 'button',
			buttonImage: '',
			buttonText: '<span class="sr-only">open the datepicker</span>',
			prevText: '',
			nextText: '',
			constrainInput: true,
			monthNamesShort: [ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" ],
			changeMonth:true,
			changeYear:true,
			beforeShow: function() {
				makeAccessible();
			},
		});
		function makeAccessible () {
			clearTimeout(makeAccessible.timer);
			$('.ui-datepicker-month').focus();
			if ($('.ui-datepicker.ui-widget .ui-datepicker-calendar').is(':visible')) {
				var cal = $('.ui-datepicker.ui-widget');
				var cal_grid = cal.find('table');
				var grid_thead = cal_grid.find('thead');
				var grid_thead_tr = grid_thead.find('tr');
				var grid_thead_th = grid_thead.find('th');
				var grid_tbody = cal_grid.find('tbody');
				var grid_tbody_tr = grid_tbody.find('tr');
				var grid_tbody_td = grid_tbody.find('td');
				var grid_selectoption = $('.ui-datepicker-header').find('option');
				$( cal ).attr('role','region').attr('role','alert');
				$( grid_selectoption ).each(function () {
					var span_months = 'name of month';
					$( this ).attr('abbr', span_months);
				});
				$( cal_grid ).attr('role', 'grid').attr('tabindex', 0).attr('aria-label','select a date');
				$( grid_thead_th ).each(function () {
					var span_title = $(this).find('span').attr('title');
					$( this ).attr('role', 'columnheader').attr('arial-label', span_title).attr('abbr', span_title).attr('scope', 'col');
				});
				$( grid_tbody_td ).each(function () {
					var self = $( this );
					var self_link = self.find('a');
					$( self_link ).attr('tabindex', '0');
					$( this ).attr('tabindex', '0')
				});
				$( grid_tbody_td ).on('focusin',function () {
					$(this).children('a').css('color','#333');
					$(this).css('background-color','#F3D54E');
				});
				$( grid_tbody_td ).on('focusout',function () {
					$(this).children('a').css('color','#222');
					$(this).css('background-color','#9BB8D3');
				});
			}
			else {
				makeAccessible.timer = setTimeout(makeAccessible, 10);
			}
		}
	});
	$('input.datepicker').on('click', function() {
		$('#ui-datepicker-div').datepicker().css('display','none');
		$(this).focus();
		if ($(this).is(':focus')) {
			$('#ui-datepicker-div').datepicker().css('display','none');
			}
		});
	$('input.foreignfiling').on('change', function() {
		var focussed = $( this ).datepicker();
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
		
		var strmax = thatz.toLocaleTimeString('en-US', options); 
		var sliceyearmx = strmax.slice(6, 10);
		var slicemonthmx = strmax.slice(0, 2);
		var slicedaymx = strmax.slice(3, 5);
		
		var f = $('input.foreignfiling');
		for (var i = 0; i < f.length; i++)
		f[i].setAttribute("min", sliceyearmin + "-" + slicemonthmin + "-" + slicedaymin);	
		var t = $('input.foreignfiling');
		for (var i = 0; i < t.length; i++)
		t[i].setAttribute("max", sliceyearmx + "-" + slicemonthmx + "-" + slicedaymx);
				
		var validateinputbox = $(this).val();
		var validateinput = $(this).val();
		var validate = $(this).val(); 	
		var dateval = Date.parse(validate);
		var requireddate = Date.parse(newDate);
		var todaydate = Date.parse(thatz);
		
		if ((requireddate - 182.5) > dateval) {
			var b = $('#alertmin');
			if ($(b).css('visibility','hidden')) {
				$(b).css('visibility','visible');
				$(b).css('height','auto');
				$(b).addClass('form-group');
				$(b).addClass('form-group-md');
				$(b).css('float','left');
				$(b).css('top','.25em');
				$(b).css('padding','1em');
				$(b).css('marginBottom','1em');
				$('#mintext').css('display', 'block');
				$('#alertbtn').css('display','block');
				$('#alertbtn').focus();
				window.scrollTo(0, '90%');
				$('#mintext').html('You cannot claim Section 44(d) priority, because your attempted U.S. filing is outside the 6-month window. (i.e., from when the foreign application was filed). The date must be a number after ' + strmin)
		  } else {
				$(b).css('visibility','hidden');
				$(b).css('height','1px');
				$(b).removeClass('form-group');
				$(b).removeClass('form-group-md');
				$(b).css('top','-10000px');
				$(b).css('float','none');
				$(b).css('padding','0');
				$(b).css('marginBottom','0');
				$('#mintext').css('display','none');
				$('#alertbtn').css('display','none');
				$('#alertbtn').blur();
		  }
		  $('#alertbtn').on('click', function() {
			$(focussed).focus();
			$(focussed).val('');
			$('.datepicker').datepicker( 'hide' );
		  });
		}
		if ((todaydate) < (dateval)) {
			var b = $('#alertmin');
			if ($(b).css('visibility','hidden')) {
				$(b).css('visibility','visible');
				$(b).css('height','auto');
				$(b).addClass('form-group');
				$(b).addClass('form-group-md');
				$(b).css('float','left');
				$(b).css('top','.25em');
				$(b).css('padding','1em');
				$(b).css('marginBottom','1em');
				$('#mintext').css('display','block');
				$('#alertbtn').css('display','block');
				$('#alertbtn').focus();
				window.scrollBy(0, '90%');
				$('#mintext').html('You cannot claim Section 44(d) priority, because your attempted U.S. filing is after the current date. The date must be a number before ' + thatz);
			} else {
				$(b).css('visibility','hidden');
				$(b).css('height','1px');
				$(b).removeClass('form-group');
				$(b).removeClass('form-group-md');
				$(b).css('top','-10000px');
				$(b).css('float','none');
				$(b).css('padding','0');
				$(b).css('marginBottom','0');
				$('#mintext').css('display','none');
				$('#alertbtn').css('display','none');
				$('#alertbtn').blur();
		  }
		}
		$('#alertbtn').on('click', function() {
			$(focussed).focus();
			$(focussed).val('');
			$( '.datepicker' ).datepicker( 'hide' );
		  });	
	});
    //



    $('#sectioneight h2 button.close').on('click',function() {
        var gsdiv = $(this).closest('section');
        $(this).parent().find('span.sr-only').text() === 'good / service removed'
            ? $(this).parent().find('span.sr-only').text('good / service added')
            : $(this).parent().find('span.sr-only').text('good / service removed');
        $( this ).find('span.glyphicon').toggleClass('glyphicon-refresh');
        $( this ).find('span.glyphicon').toggleClass('glyphicon-remove');
        $( this ).toggleClass('resetgsbtn');
        $( gsdiv ).toggleClass('removegs');





    });
    $('#sectioneight h2 button.resetgsbtn').on('click',function() {
        var gsdiv = $(this).closest('section');
        $( gsdiv ).css('opacity','1.0');
        $( gsdiv ).toggleClass('removegs');



    });


	$.datepicker.setDefaults({dateFormat: 'yy-mm-dd'});


});

function setTEASValidatioOption(option) {





	//alert(field_name_prefix);
	// send state, and token to server
	// token will be generated in the controller and added to the view
	// similar to how access token works ...a token is tied to a user
	// access token will be passed in through model
	//
	// var tokenValue = '${token}';  // token is the model attribute name
	// we are not using secured tokens for the prototype
	// we will secure it by adding ssl and https support

	//var field_value = $(this).val();
	//alert(field_value);

	$.ajax({
		url: "../../REST/apiGateway/application/TEASOpt/"+option+"/"+appInternalID,
		type: 'GET',
		success: function(data){

			// parse out status msg:
			var s_code = data.substring(data.indexOf("status:")+7, data.indexOf("status:")+4+7);

			var s_msg = data.substring(data.indexOf("msg:")+4, data.length-2);

			if(s_code.trim() == "200"){
				//if(data.contains("status: 200")){
				// show confirmation check mark on form
				//$('#toggle').delay(1000).click();
				$(".autoSaveMessageArea span").fadeOut(500);
				setTimeout(function() {
					// after 1000 ms we add the class animated to the login/register card
					$(".autoSaveMessageArea span").text(s_msg);
					$(".autoSaveMessageArea span").fadeIn(1000);
				}, 500);

			}


		},
		error: function(data) {

			console.log(data);

		}

	});



	////////////////////////////////////////////////////////////////////////////////
	// end of contact information 'State' auto save ////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////


}
