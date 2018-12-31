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
	
	//START initialize datable
		var table = $('#goodsandservices').DataTable({
		"fnDrawCallback": function( oSettings ) {
			$( '#goodsandservices_wrapper input[type=checkbox]' ).each(function() {
				var x = $( 'input[type=checkbox]' ).index( this );
				$( this ).attr('id', 'a' + x);
			});
			$( '#goodsandservices_wrapper label' ).each(function() {
				var y = $( this ).siblings( 'input[type=checkbox]' ).attr('id');
				$( this ).attr('for', y);
			});
			if ($('#goodsandservices tr').length < 10) {
				$('#goodsandservices_wrapper').css('min-height','975px');
			}
		},
			responsive: {
            details: true,
			breakpoints: [
			{ name: 'phone',   width: 480 }
			],
			},
			"autoWidth": false,
			"responsive": true,
			"columns": [
				{ "width": "12%" },
				{ "width": "25%" },
				{ "width": "30%" },
				{ "width": "33%" },
			  ],
		});
		
	//END initialize datable
	
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
	$('[data-toggle="popover"]').popover({
        title : false,
		html : true,
		placement : 'bottom auto',
        content : '<div class="media"><img src="images/stylized_alt2.gif" style="min-width:77px;width:80px;margin:0 auto;" class="media-object img-responsive" alt="my trademark"></div>'
    });	
	$( '#togglepop' ).click(function() {
		$( this ).toggleClass( 'focus' );
		$(this).html() === '<span class="glyphicon glyphicon-picture" aria-hidden="true"></span> Hide My Mark'
			? $(this).html('<span class="glyphicon glyphicon-picture" aria-hidden="true"></span> Show My Mark')
			: $(this).html('<span class="glyphicon glyphicon-picture" aria-hidden="true"></span> Hide My Mark');
		});	
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
		//yes, contains name is checked
		$("#namechecked").prop({
				checked: false,
			});
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
		$("#portraitchecked").prop({
				checked: false,
			});
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
	//start affiliation options
	$('div#usaffiliation').css('display','none');
	$('div#canadianaffiliation').css('display','none');
	$('#attorney-bar-standing').change(function(){
		$('.hidethis').hide( 'fast' );
		$('#' + $(this).val()).show( 'fast' );
	});
	//end affiliation options
	//start entity options
	$('#autofill').css('display','none');

	//end entity options
	//start iframe width
	var a = $( "body.insertform" ); 
	$( "div#entities" ).css( "height", a.innerHeight() );//set the width of the div to the height of the div
	//end iframe width
	//END toggle radio buttons content
	
	//START set text area
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
	//END set text area
		
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
});
