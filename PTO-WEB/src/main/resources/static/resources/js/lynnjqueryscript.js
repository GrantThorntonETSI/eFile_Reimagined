$(document).ready(function(){
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

	//END expand / collapse glyphicon

	//START set initial checkboxes
	$('input[type=checkbox]').attr('checked',false);
	//END set initial checkboxes

	//START enable / disable checkboxes
	//When 'Certification' is checked, 'Trademark / Servicemark', 'Collective' and 'Collective Membership' are disabled
	$('input#certcheck').change(function() {
		if(this.checked == true){
			$("input#tradeserv").prop({
				disabled: true,
			});
			$("input#collectcheck").prop({
				disabled: true,
			});
			$("input#collectmember").prop({
				disabled: true,
			});
			$('.form-check#tradeserv2 span.radio').addClass('special');
			$('.form-check#collect2 span.radio').addClass('special');
			$('.form-check#collectmemb span.radio').addClass('special');
			$('.form-check#tradeserv2 span.radio').attr('aria-disabled', 'true');
			$('.form-check#collect2 span.radio').attr('aria-disabled', 'true');
			$('.form-check#collectmemb span.radio').attr('aria-disabled', 'true');
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
			$('.form-check#collect2 span.radio').removeClass('special');
			$('.form-check#collectmemb span.radio').removeClass('special');
			$('.form-check#tradeserv2 span.radio').attr('aria-disabled', 'false');
			$('.form-check#collect2 span.radio').attr('aria-disabled', 'false');
			$('.form-check#collectmemb span.radio').attr('aria-disabled', 'false');
		}
		$("input#certcheck").on( "click", function(){
			$(".form-check#tradeserv2 span.radio").addClass("special");
			$(".form-check#collect2 span.radio").addClass("special");
			$(".form-check#collectmemb span.radio").addClass("special");
			$(".form-check#tradeserv2 span.radio").attr('aria-disabled', 'true');
			$(".form-check#collect2 span.radio").attr('aria-disabled', 'true');
			$(".form-check#collectmemb span.radio").attr('aria-disabled', 'true');
		});
	});
	//When 'Collective' or 'Collective Membership' are checked, 'Trademark / Servicemark' and 'Certification' are disabled
	$('input#collectcheck,input#collectmember').change(function() {
		if(this.checked == true){
			//$('input#tradeserv').prop({
			//disabled: true,
			//});
			$('input#certcheck').prop({
				disabled: true,
			});
			//$('.form-check#tradeserv2 span.radio').toggleClass('special');
			$('.form-check#cert span.radio').addClass('special');
			//$('.form-check#tradeserv2 span.radio').attr('aria-disabled', 'true');
			$('.form-check#cert span.radio').attr('aria-disabled', 'true');
		}
		else{
			//$('input#tradeserv').prop({
			//	disabled: false
			//})
			$('input#certcheck').prop({
				disabled: false,
			});
			$('.form-check#cert span.radio').removeClass('special');
			$('.form-check#cert span.radio').attr('aria-disabled', 'false');
		}
	});
	//When 'Trademark / Servicemark' is checked, 'Certification' is disabled
	$('input#tradeserv').change(function(e) {
		if(this.checked == true){
			$('input#certcheck').prop({
				disabled: true,
			});
			$('.form-check#cert span.radio').addClass('special');
			$('.form-check#cert span.radio').attr('aria-disabled', 'true');
		}
		else{
			$('input#certcheck').prop({
				disabled: false,
			});
			$('.form-check#cert span.radio').removeClass('special');
		}
	});//END enable / disable checkboxes

	//START toggle panel color
	$( '.nocontent' ).click(function() {
		$( document ).find('div').removeClass( 'highlight focus' );
		$( this ).addClass( 'highlight focus' );
	});
	//END toggle panel color

	//START panel height match
	var a = $( 'div.match' );
	//$('#panels .panel-body').css( 'min-height', (a.innerHeight() + 8) );
	$('#panels .panel-body').css( 'height', (a.innerHeight() + 8) );
	//END

	//START examples panel link hover / active
	$('#examples div.panel a').hover(function() {
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
		container: "body"
	});

	//Initialize popover
	//$(function () {
	//$('[data-toggle="popover"]').popover()
	//})

	//$( '#dismisspop' ).click(function() {
	//$( this ).toggleClass( 'focus' );
	//$("[data-toggle='popover']").popover('hide');
	//});


	//+ Translation Item
	//

	//- Translation Item
	//

	//START toggle Mark w/ Text radio buttons content
	//'Yes' is initially checked, with associated content visible, when 'No' is togged, the content is replaced with 'No' content
	//start mark color options
	$("input#inlineRadio1").prop({
		checked: false,
	});
	$('div#nocolorclaim').css('display','none');
	$('div#yescolorclaim').css('display','none');
	$('input#inlineRadio1').change(function() {
		if(this.checked == true){
			$('div#nocolorclaim').hide( 'fast' );
			$('div#yescolorclaim').show( 'slow' );

			$("input#inlineRadio2").prop({
				checked: false,
			});
		}
		else {
			$('div#nocolorclaim').show( 'fast' );
			$('div#yescolorclaim').hide( 'slow' );

			$("input#inlineRadio2").prop({
				checked: true,
			});
		}
	});
	$('input#inlineRadio2').change(function() {
		if(this.checked == true){
			$('div#nocolorclaim').show( 'fast' );
			$('div#yescolorclaim').hide( 'slow' );

			$("input#inlineRadio1").prop({
				checked: false,
			});
		}
		else {
			$('div#nocolorclaim').hide( 'fast' );
			$('div#yescolorclaim').show( 'slow' );

			$("input#inlineRadio1").prop({
				checked: true,
			});
		}
	});
	//end mark color options

	//start translations options
	$("input#inlineRadio3").prop({
		checked: false,
	});
	$('div#yestranslation').css('display','none');
	$('div#notranslation').css('display','none');
	$('input#inlineRadio3').change(function() {
		if(this.checked == true){
			$('div#notranslation').hide( 'fast' );
			$('div#yestranslation').show( 'slow' );

			$("input#inlineRadio4").prop({
				checked: false,
			});
		}
		else {
			$('div#notranslation').show( 'fast' );
			$('div#yestranslation').hide( 'slow' );

			$("input#inlineRadio4").prop({
				checked: true,
			});
		}
	});
	$('input#inlineRadio4').change(function() {
		if(this.checked == true){
			$('div#notranslation').show( 'fast' );
			$('div#yestranslation').hide( 'slow' );

			$("input#inlineRadio3").prop({
				checked: false,
			});
		}
		else {
			$('div#notranslation').hide( 'fast' );
			$('div#yestranslation').show( 'slow' );

			$("input#inlineRadio3").prop({
				checked: true,
			});
		}
	});
	//end translations options

	//start transliterations options
	$("input#inlineRadio5").prop({
		checked: false,
	});
	$('div#yestransliteration').css('display','none');
	$('input#inlineRadio5').change(function() {
		if(this.checked == true){
			$('div#yestransliteration').show( 'fast' );

			$("input#inlineRadio6").prop({
				checked: false,
			});
		}
		else {
			$('div#yestransliteration').hide( 'fast' );

			$("input#inlineRadio6").prop({
				checked: true,
			});
		}
	});
	$('input#inlineRadio6').change(function() {
		if(this.checked == true){
			$('div#yestransliteration').hide( 'fast' );

			$("input#inlineRadio5").prop({
				checked: false,
			});
		}
		else {
			$('div#yestransliteration').show( 'fast' );

			$("input#inlineRadio5").prop({
				checked: true,
			});
		}
	});
	//end translations options

	//start nps options
	$("input#inlineRadio7").prop({
		checked: false,
	});
	$('div#yesnps').css('display','none');
	$('input#inlineRadio7').change(function() {
		if(this.checked == true){
			$('div#yesnps').show( 'fast' );

			$("input#inlineRadio8").prop({
				checked: false,
			});
		}
		else {
			$('div#yesnps').hide( 'fast' );

			$("input#inlineRadio8").prop({
				checked: true,
			});
		}
	});
	$('input#inlineRadio8').change(function() {
		if(this.checked == true){
			$('div#yesnps').hide( 'fast' );

			$("input#inlineRadio7").prop({
				checked: false,
			});
		}
		else {
			$('div#yesnps').show( 'fast' );

			$("input#inlineRadio7").prop({
				checked: true,
			});
		}
	});
	//end nps options

	//start disclaimer options
	$("input#inlineRadio09").prop({
		checked: false,
	});
	$('div#yesdisclaimer').css('display','none');
	$('input#inlineRadio09').change(function() {
		if(this.checked == true){
			$('div#yesdisclaimer').show( 'fast' );

			$("input#inlineRadio010").prop({
				checked: false,
			});
		}
		else {
			$('div#yesdisclaimer').hide( 'fast' );

			$("input#inlineRadio010").prop({
				checked: true,
			});
		}
	});
	$('input#inlineRadio010').change(function() {
		if(this.checked == true){
			$('div#yesdisclaimer').hide( 'fast' );

			$("input#inlineRadio09").prop({
				checked: false,
			});
		}
		else {
			$('div#yesdisclaimer').show( 'fast' );

			$("input#inlineRadio09").prop({
				checked: true,
			});
		}
	});
	//end disclaimer options

	//start prior options
	$("input#inlineRadio011").prop({
		checked: false,
	});
	$('div#yesprior').css('display','none');
	$('input#inlineRadio011').change(function() {
		if(this.checked == true){
			$('div#yesprior').show( 'fast' );

			$("input#inlineRadio012").prop({
				checked: false,
			});
		}
		else {
			$('div#yesprior').hide( 'fast' );

			$("input#inlineRadio012").prop({
				checked: true,
			});
		}
	});
	$('input#inlineRadio012').change(function() {
		if(this.checked == true){
			$('div#yesprior').hide( 'fast' );

			$("input#inlineRadio011").prop({
				checked: false,
			});
		}
		else {
			$('div#yesprior').show( 'fast' );

			$("input#inlineRadio011").prop({
				checked: true,
			});
		}
	});
	//end prior options

	//start prior options
	$("input#inlineRadio013").prop({
		checked: false,
	});
	$('div#yesmeaning').css('display','none');
	$('input#inlineRadio013').change(function() {
		if(this.checked == true){
			$('div#yesmeaning').show( 'fast' );

			$("input#inlineRadio014").prop({
				checked: false,
			});
		}
		else {
			$('div#yesmeaning').hide( 'fast' );

			$("input#inlineRadio014").prop({
				checked: true,
			});
		}
	});
	$('input#inlineRadio014').change(function() {
		if(this.checked == true){
			$('div#yesmeaning').hide( 'fast' );

			$("input#inlineRadio013").prop({
				checked: false,
			});
		}
		else {
			$('div#yesmeaning').show( 'fast' );

			$("input#inlineRadio013").prop({
				checked: true,
			});
		}
	});
	//end prior options

	//start attorney options
	$("input#inlineRadio015").prop({
		checked: false,
	});
	$('div#yesattorneyfiling').css('display','none');
	$('input#inlineRadio015').change(function() {
		if(this.checked == true){
			$('div#yesattorneyfiling').show( 'fast' );

			$("input#inlineRadio016").prop({
				checked: false,
			});
		}
		else {
			$('div#yesattorneyfiling').hide( 'fast' );

			$("input#inlineRadio016").prop({
				checked: true,
			});
		}
	});
	$('input#inlineRadio016').change(function() {
		if(this.checked == true){
			$('div#yesattorneyfiling').hide( 'fast' );

			$("input#inlineRadio015").prop({
				checked: false,
			});
		}
		else {
			$('div#yesattorneyfiling').show( 'fast' );

			$("input#inlineRadio015").prop({
				checked: true,
			});
		}
	});
	//end attorney options

	//END toggle Mark w/ Text radio buttons content

	//START set text area
	//var text = $( this ).text();
	$( "a#clearform" ).click(function() {
		//var text = $( this ).text();
		//$( "input" ).val( text );
		$( 'input#attorney-first-name' ).val( '' );
		$( 'input#title' ).val( '' );
		$( 'input#attorney-last-name' ).val( '' );
		$( '#suffix' ).val( );
		$( 'input#attorney-lawfirm-name' ).val( '' );
		$( '#attorney-country' ).val( );
		$( 'input#attorney-address1' ).val( '' );
		$( 'input#attorney-city' ).val( '' );
		$( '#attorney-state' ).val( );
		$( 'input#attorney-zipcode' ).val( '' );
		$( 'input#attorney-email' ).val( '' );
		$( 'input#attorney-phonenumbertype' ).val( );
		$( 'input#attorney-phone' ).val( '' );
	});
	$( "a#avo" ).click(function() {
		//var text = $( this ).text();
		//$( "input" ).val( text );
		$( 'input#attorney-first-name' ).val( 'Avo' );
		$( 'input#title' ).val( 'Director, Enterprise Technology Strategy and Innovation' );
		$( 'input#attorney-last-name' ).val( 'Reid' );
		$( '#suffix' ).val( 1 );
		$( 'input#attorney-lawfirm-name' ).val( 'Grant Thornton' );
		$( '#attorney-country' ).val( 1 );
		$( 'input#attorney-address1' ).val( '333 John Carlyle St # 500' );
		$( 'input#attorney-city' ).val( 'Alexandria' );
		$( '#attorney-state' ).val( 4 );
		$( 'input#attorney-zipcode' ).val( '22314' );
		$( 'input#attorney-email' ).val( 'avo.reid@us.gt.com' );
		$( 'input#attorney-phonenumbertype' ).val( 4 );
		$( 'input#attorney-phone' ).val( '703-637-4097' );
	});
	//alert($('#attorney-country').val());
	$( "a#jacob" ).click(function() {
		//var text = $( this ).text();
		//$( "input" ).val( text );
		$( 'input#attorney-first-name' ).val( 'Jacob' );
		$( 'input#title' ).val( 'ETS-GPS Senior Associate PS Advisory Practice' );
		$( 'input#attorney-last-name' ).val( 'Goldstein' );
		$( '#suffix' ).val( 1 );
		$( 'input#attorney-lawfirm-name' ).val( 'Grant Thornton' );
		$( '#attorney-country' ).val( 1 );
		$( 'input#attorney-address1' ).val( '333 John Carlyle St # 500' );
		$( 'input#attorney-city' ).val( 'Alexandria' );
		$( '#attorney-state' ).val( 4 );
		$( 'input#attorney-zipcode' ).val( '22314' );
		$( 'input#attorney-email' ).val( 'jacob.goldstein@us.gt.com' );
		$( 'input#attorney-phonenumbertype' ).val( 4 );
		$( 'input#attorney-phone' ).val( '571-444-1983' );
	});
	$( "a#jackie" ).click(function() {
		//var text = $( this ).text();
		//$( "input" ).val( text );
		$( 'input#attorney-first-name' ).val( 'Jackie' );
		$( 'input#title' ).val( 'ADTA-GPS Associate PS Advisory Practice' );
		$( 'input#attorney-last-name' ).val( 'Babos' );
		$( '#suffix' ).val( 3 );
		$( 'input#attorney-lawfirm-name' ).val( 'Grant Thornton' );
		$( '#attorney-country' ).val( 1 );
		$( 'input#attorney-address1' ).val( '333 John Carlyle St # 500' );
		$( 'input#attorney-city' ).val( 'Alexandria' );
		$( '#attorney-state' ).val( 4 );
		$( 'input#attorney-zipcode' ).val( '22314' );
		$( 'input#attorney-email' ).val( 'jackie.babos@us.gt.com' );
		$( '#attorney-phonenumbertype' ).val( 4 );
		$( 'input#attorney-phone' ).val( '703-562-6675' );
	});
	//END set text area


	$("button#feebtn").click(function() {
		$( '#mydata' ).css('visibility','visible');
		$( '#mydata .collapse' ).collapse('show').fadeIn( 'slow','swing');
		$( 'button#feebtn span' ).toggleClass('visuallyremoved');
		$( 'button#feebtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	$("button#contactsbtn").click(function() {
		$( '#mydata2' ).css('visibility','visible');
		$( '#mydata2 .collapse' ).collapse('show').fadeIn( 'slow','swing');
		$( 'button#contactsbtn span' ).toggleClass('visuallyremoved');
		$( 'button#contactsbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});

	//START contacts, fees, my mark components
	//start toggle glyphicon add from contact
	$('a.fromcontact').click(function() {
		$( document ).find('span.glyphicon-ok-sign.visuallyadded').addClass( 'visuallyremoved' ).removeClass( 'visuallyadded' ).parent().parent().css('background-color','#9BB8D3');;
		$( document ).find('span.glyphicon-plus-sign.visuallyremoved').addClass( 'visuallyadded' ).removeClass( 'visuallyremoved' );
		$( this ).find('span.glyphicon-plus-sign').addClass('visuallyremoved').removeClass( 'visuallyadded' )
		$( this ).find('span.glyphicon-ok-sign').addClass('visuallyadded').removeClass('visuallyremoved').parent().parent().css('background-color','#D4EB8E');
	});
	//end toggle glyphicon add from contact

	//start hide fees
	$('button#closefee').click(function() {
		$( '#mydata' ).css('visibility','hidden');
		//$('#showfees').html('<span class="glyphicon glyphicon-usd kerned" aria-hidden="true"></span> Show My Fees');
	});
	//end hide fees

	//start show fees
	$('a#showfees').click(function() {
		$( '#mydata' ).css('visibility','visible');
		$( '#mydata .collapse' ).collapse('show').fadeIn( 'slow','swing');
		$( '#mydata button#feebtn span' ).toggleClass('visuallyremoved');
		$( '#mydata button#feebtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	//end show fees

	//start toggle show / hide fees from icon link
	$('a#showfees').click(function() {
		$( '#mydata' ).css('visibility','visible');
		$( '#mydata .collapse' ).collapse('hide').fadeOut( 'slow','swing');
		//$( '#mydata button#feebtn span' ).toggleClass('visuallyremoved');
		//$( '#mydata button#feebtn span#toggleglyph' ).toggleClass('visuallyadded');

		//$( this ).toggleClass( 'focus' );
//		$(this).html() === '<span class="glyphicon glyphicon-usd" aria-hidden="true"></span> Hide My Fees'
//			? $(this).html('<span class="glyphicon glyphicon-usd" aria-hidden="true"></span> Show My Fees')
//			: $(this).html('<span class="glyphicon glyphicon-usd" aria-hidden="true"></span> Hide My Fees');

		//$( "a#showfees" ).focus(function() {
//			$('a#showfees').click(function() {
//				$( '#mydata' ).css('visibility','visible');
//				$( '#mydata .collapse' ).collapse('hide').fadeOut( 'slow','swing');
//				$( '#mydata button#feebtn span' ).toggleClass('visuallyremoved');
//				$( '#mydata button#feebtn span#toggleglyph' ).toggleClass('visuallyadded');
//
//			});
//		});
	});
	//end toggle show / hide fees from icon link

	//start hide managed contacts
	$('button#closecontacts').click(function() {
		$( '#mydata2' ).css('visibility','hidden');
	});
	//end hide managed contacts

	//start show managed contacts
	$('a#showcontacts,#autofill').click(function() {
		$( '#mydata2' ).css('visibility','visible');
		$( '#mydata2 .collapse' ).collapse('show').fadeIn( 'slow','swing');
		$( '#mydata2 button#contactsbtn span' ).toggleClass('visuallyremoved');
		$( '#mydata2 button#contactsbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	//end show managed contacts

	//start toggle show / hide contacts from icon link
	$('a#showcontacts').click(function() {
		$( '#mydata2' ).css('visibility','visible');
		$( '#mydata2 .collapse' ).collapse('hide').fadeOut( 'slow','swing');
		//$( '#mydata2 button#feebtn span' ).toggleClass('visuallyremoved');
		//$( '#mydata2 button#feebtn span#toggleglyph' ).toggleClass('visuallyadded');

		//$( this ).toggleClass( 'focus' );
		//$(this).html() === '<span class="glyphicon glyphicon-list" aria-hidden="true"></span> Hide Managed Contacts'
		//? $(this).html('<span class="glyphicon glyphicon-list" aria-hidden="true"></span> Show Managed Contacts')
		//: $(this).html('<span class="glyphicon glyphicon-list" aria-hidden="true"></span> Hide Managed Contacts');

		//$( "a#showcontacts" ).focus(function() {
//			$('a#showcontacts').click(function() {
//				$( '#mydata2' ).css('visibility','visible');
//				$( '#mydata2 .collapse' ).collapse('hide').fadeOut( 'slow','swing');
//				$( '#mydata2 button#contactsbtn span' ).toggleClass('visuallyremoved');
//				$( '#mydata2 button#contactsbtn span#toggleglyph' ).toggleClass('visuallyadded');
//
//				});
//			});
	});
	//end toggle show / hide contacts from icon link
	//END contacts, fees, my mark components



});
