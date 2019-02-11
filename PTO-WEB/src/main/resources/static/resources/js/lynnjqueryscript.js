$(document).ready(function(){
	var e = $( window ).height();
	$('footer').css('position','relative').css('top',e).css('margin-top','0');
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
		container: "body",
		placement : 'bottom auto'
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

	$('input#inlineRadio1').change(function() {
		if(this.checked == true){
			$('div#nocolorclaim').hide( 500 );
			$('div#yescolorclaim').show( 500 );
		}
		else {
			$('div#nocolorclaim').show( 500 );
			$('div#yescolorclaim').hide( 500 );
		}
	});
	$('input#inlineRadio2').change(function() {
		if(this.checked == true){
			$('div#nocolorclaim').show( 500 );
			$('div#yescolorclaim').hide( 500 );
		}
		else {
			$('div#nocolorclaim').hide( 500 );
			$('div#yescolorclaim').show( 500 );
		}
	});
	//end mark color options


	$('input#inlineRadio3').change(function() {
		if(this.checked == true){
			$('div#yestranslation').show( 500 );
		}
		else {
			$('div#yestranslation').hide( 500 );
		}
	});
	$('input#inlineRadio4').change(function() {
		if(this.checked == true){
			$('div#yestranslation').hide( 500 );
		}
		else {
			$('div#yestranslation').show( 500 );
		}
	});
	//end translations options


	$('input#inlineRadio5').change(function() {
		if(this.checked == true){
			$('div#yestransliteration').show( 500 );
		}
		else {
			$('div#yestransliteration').hide( 500 );
		}
	});
	$('input#inlineRadio6').change(function() {
		if(this.checked == true){
			$('div#yestransliteration').hide(500 );
		}
		else {
			$('div#yestransliteration').show( 500 );
		}
	});
	//end translations options


	$('input#inlineRadio7').change(function() {
		if(this.checked == true){
			$('div#yesnps').show( 500 );
		}
		else {
			$('div#yesnps').hide( 500 );
		}
	});
	$('input#inlineRadio8').change(function() {
		if(this.checked == true){
			$('div#yesnps').hide( 500 );
		}
		else {
			$('div#yesnps').show( 500 );
		}
	});
	//yes, contains name is checked
	$('div#yescontainsname').hide();
	$('#namechecked').change(function() {
		if(this.checked == true){
			$('div#yescontainsname').show( 500 );
		}
		else {
			$('div#yescontainsname').hide( 500 );
		}
	});
	//yes, contains portrait is checked
	$('div#yescontainsportrait').hide();
	$('#portraitchecked').change(function() {
		if(this.checked == true){
			$('div#yescontainsportrait').show( 500 );
		}
		else {
			$('div#yescontainsportrait').hide( 500 );
		}
	});
	//yes, contains signature is checked
	$("#signaturechecked").prop({
		checked: false,
	});
	$('div#yescontainssignature').hide();
	$('#signaturechecked').change(function() {
		if(this.checked == true){
			$('div#yescontainssignature').show( 500 );
		}
		else {
			$('div#yescontainssignature').hide( 500 );
		}
	});
	//end nps options


	$('input#inlineRadio09').change(function() {
		if(this.checked == true){
			$('div#yesdisclaimer').show( 500 );
		}
		else {
			$('div#yesdisclaimer').hide( 500 );
		}
	});
	$('input#inlineRadio010').change(function() {
		if(this.checked == true){
			$('div#yesdisclaimer').hide( 500 );
		}
		else {
			$('div#yesdisclaimer').show( 500 );
		}
	});
	//end disclaimer options


	$('div#yesprior').css('display','none');
	$('input#inlineRadio011').change(function() {
		if(this.checked == true){
			$('div#yesprior').show( 500 );
		}
		else {
			$('div#yesprior').hide( 500 );
		}
	});
	$('input#inlineRadio012').change(function() {
		if(this.checked == true){
			$('div#yesprior').hide( 500 );
		}
		else {
			$('div#yesprior').show( 500 );
		}
	});
	//end prior options

	$('input#inlineRadio013').change(function() {
		if(this.checked == true){
			$('div#yesmeaning').show( 500 );
		}
		else {
			$('div#yesmeaning').hide( 500 );
		}
	});
	$('input#inlineRadio014').change(function() {
		if(this.checked == true){
			$('div#yesmeaning').hide( 500 );
		}
		else {
			$('div#yesmeaning').show( 500 );
		}
	});
	//end prior options


	$('input#inlineRadio015').change(function() {
		if(this.checked == true){
			$('div#yesattorneyfiling').show( 500 );
		}
		else {
			$('div#yesattorneyfiling').hide( 500 );
		}
	});
	$('input#inlineRadio016').change(function() {
		if(this.checked == true){
			$('div#yesattorneyfiling').hide( 500 );
		}
		else {
			$('div#yesattorneyfiling').show( 500 );
		}
	});
	//end attorney options


	$('div#yesusentity').css('display','none');
	$('input#inlineRadio019').change(function() {
		if(this.checked == true){
			$('div#yesusentity').show( 500 );

			$("input#inlineRadio020").prop({
				checked: false,
			});
		}
		else {
			$('div#yesusentity').hide( 500 );

			$("input#inlineRadio020").prop({
				checked: true,
			});
		}
	});
	$('input#inlineRadio020').change(function() {
		if(this.checked == true){
			$('div#yesusentity').hide(500 );

			$("input#inlineRadio019").prop({
				checked: false,
			});
		}
		else {
			$('div#yesusentity').show( 500 );

			$("input#inlineRadio019").prop({
				checked: true,
			});
		}
	});
	//end us foreign options



	$('input#inlineRadio25').change(function() {
		if(this.checked == true){
			$('div#yescommerce').show( 500 );
		}
		else {
			$('div#yescommerce').hide( 500 );
		}
	});
	$('input#inlineRadio26').change(function() {
		if(this.checked == true){
			$('div#yescommerce').hide( 500 );
		}
		else {
			$('div#yescommerce').show( 500 );
		}
	});
	//end basis commerce options


	$('div#yesconnection').css('display','none');
	$('input#inlineRadio29').change(function() {
		if(this.checked == true){
			$('div#yesconnection').show( 500);
		}
		else {
			$('div#yesconnection').hide( 500 );
		}
	});
	$('input#inlineRadio30').change(function() {
		if(this.checked == true){
			$('div#yesconnection').hide( 500 );
		}
		else {
			$('div#yesconnection').show( 500 );
		}
	});
	//end basistwo connection options

	//END toggle radio buttons content

	//start affiliation options
	$('div#usaffiliation').css('display','none');
	$('div#canadianaffiliation').css('display','none');
	$('#attorney-bar-standing').change(function(){
		$('.hidethis').hide( 500 );
		$('#' + $(this).val()).show( 500 );
	});
	//end affiliation options
	//start nameoftypeofbusiness options
	$( 'div#nametype' ).css('display','none');
	$( '#type' ).on('change',function(){
		$( 'div#nametype' ).show( 500 );
	});
	//start entity options (import concept)

	$('#autofillForeign').css('display','none');
	$('#entype').on('change',function(){
		$('#container').empty();
		$('footer').css('display','none');
		//var include = ('js/' + $(this).val() + '.js');
		//$.getScript( include );
		$('#autofill').show( 500 );
		$('footer').css('display','block');
	});
	//end entity options (import concept)


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

	$('button#autofillForeign').on('click',function() {
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


	//START show standard character preview
	$( '#ta2' ).keyup(function(){
		var currentText = $(this).val();
		$( 'p#showmarktxt' ).text(currentText);
	});


	//foreign entity show
	$('div#yesforeignentity').css('display','none');
	$('input#inlineRadio020').change(function() {
		if(this.checked == true){
			$('div#yesforeignentity').show( 500);

			$("input#inlineRadio019").prop({
				checked: false,
			});
		}
		else {
			$('div#yesforeignentity').hide( 500 );

			$("input#inlineRadio019").prop({
				checked: true,
			});
		}
	});
	$('input#inlineRadio019').change(function() {
		if(this.checked == true){
			$('div#yesforeignentity').hide( 500 );

			$("input#inlineRadio020").prop({
				checked: false,
			});
		}
		else {
			$('div#yesforeignentity').show( 500 );

			$("input#inlineRadio020").prop({
				checked: true,
			});
		}
	});
	//foreign select by country
	$('.albaniaform, .algeriaform, .angolaform, .bahamasform').css('display','none');
	$('#entitycountry').on('change',function(){
		var loadselectmenu = ('.' + $(this).val() + 'form');
		$('.hidethis').hide('fast');
		$( loadselectmenu ).show('slow').addClass('hidethis');
		$( loadselectmenu ).css('display','block');
	});

	//START additional info checkboxes
	$('div#tr').css('display','none');
	$('input#typeregi').change(function() {
		if(this.checked == true){
			$('#tr').show( 'fast' );
		}
		else {
			$('#tr').hide( 'fast' );
		}
	});
	$('div#cad').css('display','none');
	$('input#distinct').change(function() {
		if(this.checked == true){
			$('#cad').show( 'fast' );
		}
		else {
			$('#cad').hide( 'fast' );
		}
	});
	$('div#uaf').css('display','none');
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
	$('div#yesdistict').css('display','none');
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
	$('div#yesotherform').css('display','none');
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
	$('div#yeswhole').css('display','none');
	$('div#yespart').css('display','none');
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
	$('div#yeswholeother').css('display','none');
	$('div#yespartother').css('display','none');
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
	$('div#yesevidence').css('display','none');
	$('div#yespriors').css('display','none');
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
			$('div#yesevidence').show( 'fast' );
			$('div#yespriors').hide( 'fast' );
		}
	});
	$('input#inlineRadio039').change(function() {
		if(this.checked == true){
			$('div#yesevidence').hide( 'fast' );
			$('div#yespriors').hide( 'fast' );
		}
		else {
			$('div#yesevidence').show( 'fast' );
			$('div#yespriors').show( 'fast' );
		}
	});
	//hide / show concurrent uses

	$('input#courtd, input#ttabmade, input#conflict, input#earlieruse').change(function() {
		if(this.checked == true){
			$('div#hideshowconcurrentuses').show( 'fast' );
		}
		else if ($('input#courtd').prop('checked')){
			$('div#hideshowconcurrentuses').show('fast');
		}
		else if ($('input#ttabmade').prop('checked')){
			$('div#hideshowconcurrentuses').show('fast');
		}
		else if ($('input#conflict').prop('checked')){
			$('div#hideshowconcurrentuses').show('fast');
		}
		else if ($('input#earlieruse').prop('checked')){
			$('div#hideshowconcurrentuses').show('fast');
		}
		else {
			$('div#hideshowconcurrentuses').hide( 'fast' );
		}
	});

	//select signature method

	$('#signmethod').on('change',function(){
		var loadsign = ('#' + $(this).val());
		$('.hidethis').hide('fast');
		$( loadsign ).show('fast').addClass('hidethis');
		$( loadsign ).css('display','block');
		//console.log(loadsign);
	});
	//END signature method


	//grid view checkboxes In-Use 1(a)
	//Gridview In-Use 1(a) Form Reveal
	$(document).on('change', 'input#otherformfirst, input#otherformcommerce', function() {
		$(this).parent().siblings('div#yesinuse1atwo').css('display','block' );
	});
	//END Gridview In-Use 1(a) Form Reveal

	//Toggle 1(a) + 1(b)
	$('#gridview table tbody tr td:nth-child(2) input').change(function() {
		$(this).parent().next('td').children('label').children('span').addClass('special');
		$(this).parent().next('td').children('input').prop({
			disabled: true,
			checked: false,
		});
		$(this).parent().siblings('td').children('input').attr('aria-disabled', 'true');
	});
	$('#gridview table tbody tr th:nth-child(2) input').change(function() {
		$(this).parent().next('th').children('label').children('span').addClass('special');
		$(this).parent().next('th').children('input').prop({
			disabled: true,
			checked: false,
		});
		$(this).parent().next('th').children('input').attr('aria-disabled', 'true');
	});
	$('#gridview table tbody tr td:nth-child(3) input').change(function() {
		$(this).parent().prev('td').children('label').children('span').addClass('special');
		$(this).parent().prev('td').children('input').prop({
			disabled: true,
			checked: false,
		});
		$(this).parent().siblings('td').children('input').attr('aria-disabled', 'true');
	});
	$('#gridview table tbody tr th:nth-child(3) input').change(function() {
		$(this).parent().prev('th').children('label').children('span').addClass('special');
		$(this).parent().prev('th').children('input').prop({
			disabled: true,
			checked: false,
		});
		$(this).parent().prev('th').children('input').attr('aria-disabled', 'true');
	});
	//END Toggle 1(a) + 1(b)

	//user selects In-Use 1(a) classname and adjacent checkbox
	$(document).on('change', '#gridview table tbody tr th:nth-child(2) input', function() { //Classnames 2nd checkbox
		var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
		var p 	= 	$( '#gridview table tbody tr th:nth-child(2) input:checked' ); //Classnames 2nd checkbox, checked
		var q	=	$( '#gridview table thead tr:nth-child(2) th:nth-child(2) input' ); //In-Use 1(a) checkbox
		//var newrow = $( "<tr class='inuse1aone'><td colspan='5'><div id='yesinuse1aone' class='form-group'><!--toggle 1(a) one--><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>In-Use 1(a) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>In-Use 1(a) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='yesinuse1atwo' class='col-xs-12 form-group'><!--toggle 1(a) two--><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div><!-- Drop Zone --></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='specdescripthree'>Provide a description of your specimen:</label><textarea id='specdescripthree' class='form-control'></textarea></div></div></div></div></td></tr>" );
		console.log('#gridview table tbody tr th:nth-child(2) input');
		if ($(this).prop('checked')) {
			$(q).prop({
				checked: true,
			});
			$(this).parent().prev('th').children('input').prop({
				checked: true,
			});
			//$(this).parent().parent().after(newrow);
			$('div#yesinuse1atwo').css('display','none');
		}
		if (this.checked == false) {
			$(q).prop({
				checked: false,
			});
			$(this).parent().parent().next('tr').remove();
		}
	});
	//user selects Intent-to-Use 1(b) classname and adjacent checkbox
	$(document).on('change', '#gridview table tbody tr th:nth-child(3) input', function() { //Classnames 3rd checkbox
		var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
		var p 	= 	$( '#gridview table tbody tr th:nth-child(3) input:checked' ); //Classnames 3rd checkbox, checked
		var q	=	$( '#gridview table thead tr:nth-child(2) th:nth-child(3) input' ); //Intent-to-Use 1(b) checkbox
		if ($(this).prop('checked')) {
			$(q).prop({
				checked: true,
			});
			$(this).parent().prev('th').prev('th').children('input').prop({
				checked: true,
			});
		}
		if (this.checked == false) {
			$(q).prop({
				checked: false,
			});
		}
	});
	//user selects Foreign Application 44(d) classname and adjacent checkbox
	$(document).on('change', '#gridview table tbody tr th:nth-child(4) input', function() { //Classnames 3rd checkbox
		var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
		var p 	= 	$( '#gridview table tbody tr th:nth-child(3) input:checked' ); //Classnames 3rd checkbox, checked
		var q	=	$( '#gridview table thead tr:nth-child(2) th:nth-child(4) input' ); //Foreign Application 44(d) checkbox
		//var newrow = $( "<tr class='inuse1aone'><td colspan='5'><div id='yesinuse1aone' class='form-group'><!--toggle 1(a) one--><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>Foreign Application 44(d) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>Foreign Application 44(d) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='yesinuse1atwo' class='col-xs-12 form-group'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div><!-- Drop Zone --></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='specdescripthree'>Provide a description of your specimen:</label><textarea id='specdescripthree' class='form-control'></textarea></div></div></div></div></td></tr>" );
		if ($(this).prop('checked')) {
			$(q).prop({
				checked: true,
			});
			$(this).parent().prev('th').prev('th').prev('th').children('input').prop({
				checked: true,
			});
			//$(this).parent().parent().after(newrow);
			$('div#yesinuse1atwo').css('display','none');
		}
		if (this.checked == false) {
			$(q).prop({
				checked: false,
			});
			$(this).parent().parent().next('tr').remove();
		}
	});
	//user selects Foreign Registration 44(e) classname and adjacent checkbox
	$(document).on('change', '#gridview table tbody tr th:nth-child(5) input', function() { //Classnames 3rd checkbox
		var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
		var p 	= 	$( '#gridview table tbody tr th:nth-child(3) input:checked' ); //Classnames 3rd checkbox, checked
		var q	=	$( '#gridview table thead tr:nth-child(2) th:nth-child(5) input' ); //Foreign Registration 44(e) checkbox
		//var newrow = $( "<tr class='inuse1aone'><td colspan='5'><div id='yesinuse1aone' class='form-group'><!--toggle 1(a) one--><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>Foreign Registration 44(e) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>Foreign Registration 44(e) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='yesinuse1atwo' class='col-xs-12 form-group'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div><!-- Drop Zone --></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='specdescripthree'>Provide a description of your specimen:</label><textarea id='specdescripthree' class='form-control'></textarea></div></div></div></div></td></tr>" );
		if ($(this).prop('checked')) {
			$(q).prop({
				checked: true,
			});
			$(this).parent().prev('th').prev('th').prev('th').prev('th').children('input').prop({
				checked: true,
			});
			//$(this).parent().parent().after(newrow);
			$('div#yesinuse1atwo').css('display','none');
		}
		if (this.checked == false) {
			$(q).prop({
				checked: false,
			});
			$(this).parent().parent().next('tr').remove();
		}
	});
	//END user selects classname and adjacent checkbox

	$( '#gridview table thead tr:nth-child(2) th:nth-child(2) input' ).change(function() { //In-Use 1(a) checkbox
		var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
		var p 	= 	$( '#gridview table tbody tr th:nth-child(2) input' ); //Classnames 2nd checkbox
		var pp	=	$( '#gridview table tbody tr th:nth-child(3) input' );
		var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classnames 1st checkbox
		var y	=	$( '#gridview table thead tr:nth-child(2) th:nth-child(3) input' ); //Intent-To-Use 1(b) checkbox
		var d 	= 	$( '#gridview table tbody tr td:nth-child(3) input' ); //GS 2nd checkbox
		var dd 	= 	$( '#gridview table tbody tr td:nth-child(2) input' ); //GS 1st checkbox
		//var newrow = $( "<tr class='inuse1aone'><td colspan='5'><div id='yesinuse1aone' class='form-group'><!--toggle 1(a) one--><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>In-Use 1(a) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>In-Use 1(a) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='yesinuse1atwo' class='col-xs-12 form-group'><!--toggle 1(a) two--><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div><!-- Drop Zone --></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='specdescripthree'>Provide a description of your specimen:</label><textarea id='specdescripthree' class='form-control'></textarea></div></div></div></div></td></tr>" );
		//var newrowgs1a = $( "<tr class='optionalupload'><td colspan='5'><div id='yesinuse1aone' class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>In-Use 1(a) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>In-Use 1(a) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='optionuploadone' class='col-xs-12'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><input class='form-check-input checkmark' type='checkbox' value='' tabindex='0' id='optionupload'><label class='form-check-label form-check' for='optionupload'><span class='radio notspecial'>Only one specimen is required per class. Click here to add a specimen to this good / service.</span></label></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md' id='optionuploadtwo'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></div></div></td></tr>" );
		//user selects classnames then selects In-Use 1(a)
		if ($(b).prop('checked')) {
			$(b).parent().next('th').children('input').prop({
				checked: true,
			});
			//$( document ).find($(b).prop('checked',true)).parent().parent().after(newrow);
			$('div#yesinuse1atwo').css('display','none');
			console.log(dd);
		}
		//user selects In-Use 1(a) and all Classnames + GSs are selected
		else if ($(b).prop('checked',false)) {
			$(p).prop({
				checked: true,
			});
			$(b).prop({
				checked: true,
			});
			$(c).prop({
				checked: true,
			});
			$(dd).prop({
				checked: true,
			});
			//$(dd).parent().parent().after(newrow);
			//$( document ).find('tr:contains(Class)').after(newrowgs1a);
			$('div#yesinuse1atwo').css('display','none');
			$('div#optionuploadtwo').css('display','block');
		}
		//disable
		if ($(this).prop('checked')) {
			$(y).prop({
				disabled: true,
				checked: false,
			});
			$(y).attr('aria-disabled', 'true');
			$( '#gridview table thead tr:nth-child(2) th:nth-child(3) label span' ).addClass('special');
			$(pp).prop({
				disabled: true,
				checked: false,
			});
			$(pp).attr('aria-disabled', 'true');
			$( '#gridview table tbody tr th:nth-child(3) label span' ).addClass('special');
			$(d).prop({
				disabled: true,
				checked: false,
			});
			$(d).attr('aria-disabled', 'true');
			$( '#gridview table tbody tr td:nth-child(3) label span' ).addClass('special');
		}

		if (this.checked == false) {
			$(b).parent().next('th').children('input').prop({
				checked: false,
			});
			$(p).prop({
				checked: false,
			});
			$(dd).prop({
				checked: false,
			});
			$(y).prop({
				disabled: false,
			});
			$( '#gridview table thead tr:nth-child(2) th:nth-child(3) label span' ).removeClass('special');
			$(pp).prop({
				disabled: false,
			});
			$(pp).attr('aria-disabled', 'false');
			$( '#gridview table tbody tr th:nth-child(3) label span' ).removeClass('special');
			$(d).prop({
				disabled: false,
			});
			$(d).attr('aria-disabled', 'false');
			$( '#gridview table tbody tr td:nth-child(3) label span' ).removeClass('special');
			$(c).prop({
				checked: false,
			});
			$( document ).find('.inuse1aone').remove('.inuse1aone');
			$('div#optionuploadtwo').css('display','none');
			$( document ).find('.optionalupload').remove('.optionalupload');
		}
	});

	//grid view checkboxes Intent-to-Use 1(b)
	$( '#gridview table thead tr:nth-child(2) th:nth-child(3) input' ).change(function() { //Intent-to-Use 1(b) checkbox
		var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
		var p 	= 	$( '#gridview table tbody tr th:nth-child(3) input' ); //Classnames 3rd checkbox
		var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classnames 1st checkbox
		var d 	= 	$( '#gridview table tbody tr td:nth-child(2) input' ); //GS 1st checkbox
		var dd 	= 	$( '#gridview table tbody tr td:nth-child(3) input' ); //GS 2nd checkbox
		var ee	=	$( '#gridview table thead tr:nth-child(2) th:nth-child(2) input' )
		var ff	=	$( '#gridview table tbody tr th:nth-child(2) input' ); //Classnames 2nd checkbox
		//user selects classnames then selects Intent-to-Use 1(b)
		if ($(b).prop('checked')) {
			$(b).parent().next('th').next('th').children('input').prop({
				checked: true,
			});
			$(b).parent().next('th').children('input').prop({  //disable
				disabled: true,
				checked: false,
			});
			$(d).parent().parent().next('.optionalupload').hide('.optionalupload');
			//disable
			$(d).prop({
				disabled: true,
				checked: false,
			});
			$(d).attr('aria-disabled', 'true');
			$( '#gridview table tbody tr td:nth-child(2) label span' ).addClass('special');
			$(ff).prop({
				disabled: true,
				checked: false,
			});
			$(ff).attr('aria-disabled', 'true');
			$( '#gridview table tbody tr th:nth-child(2) label span' ).addClass('special');
			$(b).parent().next('th').children('input').attr('aria-disabled', 'true');
			$( '#gridview table tbody tr th:nth-child(2) label span' ).addClass('special');
			$( '#gridview table thead tr:nth-child(2) th:nth-child(2) label span' ).addClass('special');
		}
		//user selects Intent-to-Use 1(b) and all Classnames + GSs are selected
		else if($(b).prop('checked',false)) {
			$(p).prop({
				checked: true,
			});
			$(b).prop({
				checked: true,
			});
			$(c).prop({
				checked: true,
			});
			$(dd).prop({
				checked: true,
			});
			//disable
			$(b).parent().next('th').children('input').prop({
				disabled: true,
				checked: false,
			});
			$(d).parent().parent().next('.optionalupload').hide('.optionalupload');
			$(d).prop({
				disabled: true,
				checked: false,
			});
			$(d).attr('aria-disabled', 'true');
			$( '#gridview table tbody tr td:nth-child(2) label span' ).addClass('special');

			$(ee).prop({
				disabled: true,
				checked: false,
			});
			$(ee).attr('aria-disabled', 'true');
			$( '#gridview table thead tr:nth-child(2) th:nth-child(2) label span' ).addClass('special');

			$(ff).prop({
				disabled: true,
				checked: false,
			});
			$(ff).attr('aria-disabled', 'true');
			$( '#gridview table tbody tr th:nth-child(2) label span' ).addClass('special');

			$(b).parent().next('th').children('input').attr('aria-disabled', 'true');
			$( '#gridview table tbody tr th:nth-child(2) label span' ).addClass('special');
			//disable
			$( '#gridview table thead tr:nth-child(2) th:nth-child(2) label span' ).addClass('special');
		}
		if (this.checked == false) {
			$(p).prop({
				checked: false,
			});
			$(b).prop({
				checked: false,
			});
			$(c).prop({
				checked: false,
			});
			$(dd).prop({
				checked: false,
			});
			//enable
			$(d).prop({
				disabled: false,
			});
			$(d).attr('aria-disabled', 'false');
			$( '#gridview table tbody tr td:nth-child(2) label span' ).removeClass('special');
			$(ee).prop({
				disabled: false,
			});
			$(ee).attr('aria-disabled', 'false');
			$( '#gridview table thead tr:nth-child(2) th:nth-child(2) label span' ).removeClass('special');
			$(ff).prop({
				disabled: false,
			});
			$(ff).attr('aria-disabled', 'false');
			$( '#gridview table tbody tr th:nth-child(2) label span' ).removeClass('special');

			$(b).parent().next('th').children('input').attr('aria-disabled', 'false');
			$( '#gridview table tbody tr th:nth-child(2) label span' ).removeClass('special');
			$( '#gridview table thead tr:nth-child(2) th:nth-child(2) label span' ).removeClass('special');
		}
	});

	//grid view checkboxes Foreign Application 44(d)
	$( '#gridview table thead tr:nth-child(2) th:nth-child(4) input' ).change(function() { //Foreign Application 44(d) checkbox
		var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
		var p 	= 	$( '#gridview table tbody tr th:nth-child(4) input' ); //Classnames 4th checkbox
		var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classnames 1st checkbox
		var dd 	= 	$( '#gridview table tbody tr td:nth-child(4) input' ); //GS 3rd checkbox
		//var newrow = $( "<tr class='foreignapp'><td colspan='5'><div class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='country'>Foreign Application Country</label><select class='form-control' id='country'><option value='Select'>Select</option><option value='Mexico'>Mexico</option><option value='Canada'>Canada</option><option value='United Kingdom'>United Kingdom</option></select></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='foreignappno'>Foreign Application Number</label><input type='textarea' class='form-control' id='foreignappno' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='foreignappdate'>Foreign Application Filing Date</label><input type='date' class='form-control' id='foreignappdate' value=''></div></div></td></tr>" );
		//var newrowgs44d = $( "<tr class='optionalupload'><td colspan='5'><div id='yesinuse1aone' class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>Foreign Application 44(d) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>Foreign Application 44(d) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='optionuploadone' class='col-xs-12'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><input class='form-check-input checkmark' type='checkbox' value='' tabindex='0' id='optionupload'><label class='form-check-label form-check' for='optionupload'><span class='radio notspecial'>Only one specimen is required per class. Click here to add a specimen to this good / service.</span></label></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md' id='optionuploadtwo'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></div></div></td></tr>" );
		//user selects classnames then selects Foreign Application 44(d)
		if ($(b).prop('checked')) {
			$(b).parent().next('th').next('th').next('th').children('input').prop({
				checked: true,
			});
			//$( document ).find($(b).prop('checked',true)).parent().parent().after(newrow);
		}
		//user selects Foreign Application 44(d) and all Classnames  + GSs are selected
		else if ($(b).prop('checked',false)) {
			$(p).prop({
				checked: true,
			});
			$(b).prop({
				checked: true,
			});
			$(c).prop({
				checked: true,
			});
			$(dd).prop({
				checked: true,
			});
			//$(dd).parent().parent().after(newrowgs44d);
			//$( document ).find('tr:contains(Class)').after(newrow);
			$('div#yesinuse1atwo').css('display','none');
			$('div#optionuploadtwo').css('display','block');
		}
		if (this.checked == false) {
			$(p).prop({
				checked: false,
			});
			$(dd).prop({
				checked: false,
			});
			$(c).prop({
				checked: false,
			});
			$(b).parent().parent().siblings('.foreignapp').remove('.foreignapp');
			$( document ).find('.inuse1aone').remove('.inuse1aone');
			$('div#optionuploadtwo').css('display','none');
			$( document ).find('.optionalupload').remove('.optionalupload')
		}
	});

	//grid view checkboxes Foreign Registration 44(e)
	$( '#gridview table thead tr:nth-child(2) th:nth-child(5) input' ).change(function() { //Foreign Registration 44(e) checkbox
		var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
		var p 	= 	$( '#gridview table tbody tr th:nth-child(5) input' ); //Classnames 5th checkbox
		var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classnames 1st checkbox
		var dd 	= 	$( '#gridview table tbody tr td:nth-child(5) input' ); //GS 4th checkbox
		//var newrow = $( "<tr class='foreignregi'><td colspan='5'><div class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='country'>Foreign Registration Country</label><select class='form-control' id='country'><option value='Select'>Select</option><option value='Mexico'>Mexico</option><option value='Canada'>Canada</option><option value='United Kingdom'>United Kingdom</option></select></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='foreignappno'>Foreign Registration Number</label><input type='textarea' class='form-control' id='foreignappno' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='foreignappdate'>Foreign Registration Exp. Date</label><input type='date' class='form-control' id='foreignappdate' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></td></tr>" );
		//var newrowgs44e = $( "<tr class='optionalupload'><td colspan='5'><div id='yesinuse1aone' class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>Foreign Registration 44(e) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>Foreign Registration 44(e) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='optionuploadone' class='col-xs-12'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><input class='form-check-input checkmark' type='checkbox' value='' tabindex='0' id='optionupload'><label class='form-check-label form-check' for='optionupload'><span class='radio notspecial'>Only one specimen is required per class. Click here to add a specimen to this good / service.</span></label></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md' id='optionuploadtwo'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></div></div></td></tr>" );
		//user selects classnames then selects Foreign Registration 44(e)
		if ($(b).prop('checked')) {
			$(b).parent().next('th').next('th').next('th').next('th').children('input').prop({
				checked: true,
			});
			//$( document ).find($(b).prop('checked',true)).parent().parent().after(newrow);
		}
		//user selects Foreign Registration 44(e) and all Classnames + GSs are selected
		else if ($(b).prop('checked',false)) {
			$(p).prop({
				checked: true,
			});
			$(b).prop({
				checked: true,
			});
			$(c).prop({
				checked: true,
			});
			$(dd).prop({
				checked: true,
			});
			//$(dd).parent().parent().after(newrowgs44e);
			//$( document ).find('tr:contains(Class)').after(newrow);
			$('div#yesinuse1atwo').css('display','none');
			$('div#optionuploadtwo').css('display','block');
		}
		if (this.checked == false) {
			$(p).prop({
				checked: false,
			});
			$(c).prop({
				checked: false,
			});
			$(dd).prop({
				checked: false,
			});
			$(b).parent().parent().siblings('.foreignregi').remove('.foreignregi');
			$( document ).find('.inuse1aone').remove('.inuse1aone');
			$('div#optionuploadtwo').css('display','none');
			$( document ).find('.optionalupload').remove('.optionalupload')
		}
	});

	//grid view GS checkboxes In-Use 1(a)
	$(document).on('change', 'input#optionupload', function() {
		if (this.checked == true) {
			$('div#optionuploadtwo').css('display','block');
		}
		else if (this.checked == false) {
			$('div#optionuploadtwo').css('display','none');
		}
	});
	$( '#gridview table tbody tr td:nth-child(2) input' ).change(function() { //In-Use 1(a) GS checkbox
		var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classname 1st checkbox
		//var newrow = $( "<tr class='optionalupload'><td colspan='5'><div id='yesinuse1aone' class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>In-Use 1(a) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>In-Use 1(a) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='optionuploadone' class='col-xs-12'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><input class='form-check-input checkmark' type='checkbox' value='' tabindex='0' id='optionupload'><label class='form-check-label form-check' for='optionupload'><span class='radio notspecial'>Only one specimen is required per class. Click here to add a specimen to this good / service.</span></label></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md' id='optionuploadtwo'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></div></div></td></tr>" );
		if (this.checked == true) {
			//$(this).parent().parent().after(newrow);
			$('div#optionuploadtwo').css('display','none');
			$( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').children('input').prop({
				checked: true,
			});
			$( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').next('th').children('input').prop({
				checked: true,
			});
		}
		else if (this.checked == false) {
			$(this).parent().parent().siblings('.optionalupload').remove('.optionalupload');
			$( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').children('input').prop({
				checked: false,
			});
			$( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').next('th').children('input').prop({
				checked: false,
			});
			$('div#optionuploadtwo').css('display','none');
		}
	});

	//grid view GS checkboxes Intent-to-Use 1(b)
	$( '#gridview table tbody tr td:nth-child(3) input' ).change(function() { //Intent-to-Use 1(b) GS checkbox
		var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classname 1st checkbox
		if (this.checked == true) {
			$( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').children('input').prop({
				checked: true,
			});
			$( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').next('th').next('th').children('input').prop({
				checked: true,
			});
		}
		else if (this.checked == false) {
			$( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').children('input').prop({
				checked: false,
			});
			$( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').next('th').next('th').children('input').prop({
				checked: false,
			});
		}
	});

	//grid view GS checkboxes Foreign Application 44(d)
	$( '#gridview table tbody tr td:nth-child(4) input' ).change(function() { //Foreign Application 44(d) GS checkbox
		var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classname 1st checkbox
		//var newrow = $( "<tr class='optionalupload'><td colspan='5'><div id='yesinuse1aone' class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>Foreign Application 44(d) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>Foreign Application 44(d) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='optionuploadone' class='col-xs-12'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><input class='form-check-input checkmark' type='checkbox' value='' tabindex='0' id='optionupload'><label class='form-check-label form-check' for='optionupload'><span class='radio notspecial'>Only one specimen is required per class. Click here to add a specimen to this good / service.</span></label></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md' id='optionuploadtwo'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></div></div></td></tr>" );
		if (this.checked == true) {
			//$(this).parent().parent().after(newrow);
			$('div#optionuploadtwo').css('display','none');
			$( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').children('input').prop({
				checked: true,
			});
			$( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').next('th').next('th').next('th').children('input').prop({
				checked: true,
			});
		}
		else if (this.checked == false) {
			$(this).parent().parent().siblings('.optionalupload').remove('.optionalupload');
			$( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').children('input').prop({
				checked: false,
			});
			$( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').next('th').next('th').next('th').children('input').prop({
				checked: false,
			});
			$('div#optionuploadtwo').css('display','none');
		}
	});

	//grid view GS checkboxes Foreign Registration 44(e)
	$( '#gridview table tbody tr td:nth-child(5) input' ).change(function() { //Foreign Registration 44(e) GS checkbox
		var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classname 1st checkbox
		//var newrow = $( "<tr class='optionalupload'><td colspan='5'><div id='yesinuse1aone' class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>Foreign Registration 44(e) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>Foreign Registration 44(e) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='optionuploadone' class='col-xs-12'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><input class='form-check-input checkmark' type='checkbox' value='' tabindex='0' id='optionupload'><label class='form-check-label form-check' for='optionupload'><span class='radio notspecial'>Only one specimen is required per class. Click here to add a specimen to this good / service.</span></label></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md' id='optionuploadtwo'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></div></div></td></tr>" );
		if (this.checked == true) {
			//$(this).parent().parent().after(newrow);
			$('div#optionuploadtwo').css('display','none');
			$( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').children('input').prop({
				checked: true,
			});
			$( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').next('th').next('th').next('th').next('th').children('input').prop({
				checked: true,
			});
		}
		else if (this.checked == false) {
			$(this).parent().parent().siblings('.optionalupload').remove('.optionalupload');
			$( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').children('input').prop({
				checked: false,
			});
			$( this ).parent().parent().prev('tr:contains(Class)').children('th:contains(Class)').next('th').next('th').next('th').next('th').children('input').prop({
				checked: false,
			});
			$('div#optionuploadtwo').css('display','none');
		}
	});




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
	$('div#yesnotpreviously').css('display','none');
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
