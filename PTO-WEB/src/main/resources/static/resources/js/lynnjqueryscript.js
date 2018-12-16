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
	
	//Tooltip
	 $("body").tooltip({   
		selector: "[data-toggle='tooltip']",
		container: "body"
		});
	
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
			
	//END toggle Mark w/ Text radio buttons content
	
	
		
});
