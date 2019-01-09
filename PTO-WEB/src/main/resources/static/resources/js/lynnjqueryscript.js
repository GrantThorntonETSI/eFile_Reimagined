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
	$("button#entbtn").click(function() {
		$( 'button#entbtn span' ).toggleClass('visuallyremoved');
		$( 'button#entbtn span#toggleglyph' ).toggleClass('visuallyadded');
	});
	//END expand / collapse glyphicon

	//END initialize datable
	//start close (x) gs panels
	$('.closegspanels').click(function() {
		$( this ).parent().parent().parent().fadeOut( 'fast','swing');
	});
	//end close (x) gs panels

	//START set initial checkboxes
	$('input[type=checkbox]').attr('checked',false);
	//END set initial checkboxes

	//START enable / disable checkboxes
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
	$('#panels .panel-body').css( 'height', (a.innerHeight() + 8) );
	//END

	//START close button height match
	var d = $( '.closepans' ).prev('div');
	$(d).css('display','flex').css('flex-direction','column');
	$('#gsselected .closepans').css( 'height', (d.innerHeight()) );
	$('#gsselected .closegspanels').css('line-height',(d.innerHeight() + 'px'));
	$( window ).resize(function() {
		$('#gsselected .closepans').css( 'height', (d.innerHeight()) );
		$('#gsselected .closegspanels').css('line-height',(d.innerHeight() + 'px'));
	});
	//END close button height match

	//START input label height match
	var d = $( '.matchlabelheight' ).parent().prev('div').children('label');
	var e = $( '.matchlabelheighttwo' ).parent().prev('div').children('label');
	$('.matchlabelheight').css( 'height', (d.innerHeight() + 'px') );
	$('.matchlabelheighttwo').css( 'height', (e.innerHeight() + 'px') );
	$( window ).resize(function() {
		$('.matchlabelheight').css( 'height', (d.innerHeight())  + 'px' );
		$('.matchlabelheighttwo').css( 'height', (e.innerHeight())  + 'px' );
	});
	//END input label height match

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
		container: "body"
	});
	//Initialize popover

	//Display uploaded filename
	$('input[type="file"]').change(function(e){
		var fileName = e.target.files[0].name;
		$('a.list-group-item').html('<span class="filename">' + fileName + '</span>' + '<span class="badge alert-success pull-right">Success</span>')
	});

	//+ Translation Item
	//

	//- Translation Item
	//

	//START toggle radio buttons content
	//start mark color options
	$('div#nocolorclaim').css('display','none');
	$('div#yescolorclaim').css('display','none');
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
	$("input#inlineRadio3").prop({
		checked: false,
	});
	$('div#yestranslation').css('display','none');
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
	$("input#inlineRadio5").prop({
		checked: false,
	});
	$('div#yestransliteration').css('display','none');
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
	$("input#inlineRadio7").prop({
		checked: false,
	});
	$('div#yesnps').css('display','none');
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
	$('div#yescontainsname').hide();
	$('#namechecked').change(function() {
		if(this.checked == true){
			$('div#yescontainsname').show( 'fast' );
		}
		else {
			$('div#yescontainsname').hide( 'fast' );
		}
	});
	//yes, contains portrait is checked
	$('div#yescontainsportrait').hide();
	$('#portraitchecked').change(function() {
		if(this.checked == true){
			$('div#yescontainsportrait').show( 'fast' );
		}
		else {
			$('div#yescontainsportrait').hide( 'fast' );
		}
	});
	//yes, contains signature is checked
	$("#signaturechecked").prop({
		checked: false,
	});
	$('div#yescontainssignature').hide();
	$('#signaturechecked').change(function() {
		if(this.checked == true){
			$('div#yescontainssignature').show( 'fast' );
		}
		else {
			$('div#yescontainssignature').hide( 'fast' );
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

	//start prior options
	$("input#inlineRadio011").prop({
		checked: false,
	});
	$('div#yesprior').css('display','none');
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
	$("input#inlineRadio013").prop({
		checked: false,
	});
	$('div#yesmeaning').css('display','none');
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
	$("input#inlineRadio015").prop({
		checked: false,
	});
	$('div#yesattorneyfiling').css('display','none');
	$('input#inlineRadio015').change(function() {
		if(this.checked == true){
			$('div#yesattorneyfiling').show( 'fast' );
		}
		else {
			$('div#yesattorneyfiling').hide( 'fast' );
		}
	});
	$('input#inlineRadio016').change(function() {
		if(this.checked == true){
			$('div#yesattorneyfiling').hide( 'fast' );
		}
		else {
			$('div#yesattorneyfiling').show( 'fast' );
		}
	});
	//end attorney options

	//start us foreign options
	$("input#inlineRadio019").prop({
		checked: false,
	});
	$('div#yesusentity').css('display','none');
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
	//end us foreign options

	//start basis commerce options
	$("input#inlineRadio25").prop({
		checked: false,
	});

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
	$("input#inlineRadio29").prop({
		checked: false,
	});
	$('div#yesconnection').css('display','none');
	$('input#inlineRadio29').change(function() {
		if(this.checked == true){
			$('div#yesconnection').show( 'fast' );
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

	//END toggle radio buttons content

	//start affiliation options
	$('div#usaffiliation').css('display','none');
	$('div#canadianaffiliation').css('display','none');
	$('#attorney-bar-standing').change(function(){
		$('.hidethis').hide( 'fast' );
		$('#' + $(this).val()).show( 'fast' );
	});
	//end affiliation options
	//start nameoftypeofbusiness options
	$( 'div#nametype' ).css('display','none');
	$( '#type' ).on('change',function(){
		$( 'div#nametype' ).show( 'fast' );
	});
	//start entity options (import concept)
	$('#autofill').css('display','none');
	$('#entype').on('change',function(){
		$('#container').empty();
		$('footer').css('display','none');
		var include = ('js/' + $(this).val() + '.js');
		$.getScript( include );
		$('#autofill').show( 'fast' );
		$('footer').css('display','block');
	});
	//end entity options (import concept)

	//START fill from contacts values -- attorney
	$( "a#clearform" ).click(function() {
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
		$( 'input#firstname' ).val( 'Avo' );
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
	$( "a#jacob" ).click(function() {
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
	//END fill from contacts values -- attorney

	//START contacts, fees, my mark components
	//start toggle glyphicon contacts widget
	$('a.fromcontact').click(function() {
		$( document ).find('span.glyphicon-ok-sign.visuallyadded').addClass( 'visuallyremoved' ).removeClass( 'visuallyadded' ).parent().parent().css('background-color','#9BB8D3').siblings().css('background-color','#9BB8D3');
		$( document ).find('span.glyphicon-plus-sign.visuallyremoved').addClass( 'visuallyadded' ).removeClass( 'visuallyremoved' );
		$( this ).find('span.glyphicon-plus-sign').addClass('visuallyremoved').removeClass( 'visuallyadded' );
		$( this ).find('span.glyphicon-ok-sign').addClass('visuallyadded').removeClass('visuallyremoved').parent().parent().css('background-color','#D4EB8E').siblings().css('background-color','#D4EB8E');
	});
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
	$('button#autofill').on('click',function() {
		$( '#mydata2' ).css('visibility','visible');
		$( '#mydata2 .collapse' ).collapse('show').fadeIn( 'slow','swing');
		$( 'button#contactsbtn span#toggleglyphone' ).addClass('visuallyremoved');
		$( 'button#contactsbtn span#toggleglyph' ).addClass('visuallyadded');
	});
	//end show managed contacts from nav

	//START additional phone
	$( 'button#addphone' ).on('click',function(){
		$( '.phones:eq(0)' ).clone().appendTo( '.appendphone' );
	});
	$( '#resetphone' ).click(function () {
		$( '.appendphone .phones' ).remove( '.phones:eq(0)' );
	});
	//END additional phone

	//START additional docket
	$( 'button#addocket' ).click(function(){
		$( '#copy:eq(0)' ).clone().appendTo( '.appenddocket' );
	});
	$("#resetdockets").click(function () {
		$( '.appenddocket #copy' ).remove('#copy:eq(0)');
	});
	//END additional docket

	//START additional containsname
	$( 'button#addaname' ).on('click',function(){
		$( '.containsaname:eq(0)' ).clone().appendTo( '.appendaname' );
	});
	$( '#resetaname' ).on('click',function () {
		$( '.appendaname .containsaname' ).remove( '.containsaname:eq(0)' );
	});
	//END additional containsname

	//START additional pending + foreign
	$('div .holdsapending').css('display','none');
	$('div #pluspending').css('display','none');
	$('input#pendingtwo').change(function() {
		if(this.val == 'pendingtwo'){
			$('div .holdsapending').hide( 'fast' );
			$('div #pluspending').hide( 'fast' );
		}
		else {
			$('div .holdsapending').show( 'fast' );
			$('div #pluspending').show( 'fast' );
		}
	});
	$('div .holdsaforeign').css('display','none');
	$('div #plusforeign').css('display','none');
	$('input#foreigntwo').change(function() {
		if(this.val == 'foreigntwo'){
			$('div .holdsaforeign').hide( 'fast' );
			$('div #plusforeign').hide('fast');
		}
		else {
			$('div .holdsaforeign').show( 'fast' );
			$('div #plusforeign').show('fast');
		}
	});
	//END additional pending + foreign

	$( 'button#addaname' ).on('click',function(){
		$( '.containsaname:eq(0)' ).clone().appendTo( '.appendaname' );
	});
	$( '#resetaname' ).on('click',function () {
		$( '.appendaname .containsaname' ).remove( '.containsaname:eq(0)' );
	});
	//END additional pending

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
	$( 'button#addforeign' ).on('click',function(){
		$( '.holdsaforeign:eq(0)' ).clone().appendTo( '.appendaforeign' );
	});
	$( '#resetforeign' ).on('click',function () {
		$( '.appendaforeign .holdsaforeign' ).remove( '.holdsaforeign:eq(0)' );
	});
	//END additional foreignreg

	//START additional foreignpending
	$( 'button#addpending' ).on('click',function(){
		$( '.holdsapending:eq(0)' ).clone().appendTo( '.appendapending' );
	});
	$( '#resetpending' ).on('click',function () {
		$( '.appendapending .holdsapending' ).remove( '.holdsapending:eq(0)' );
	});
	//END additional foreignpending
});
