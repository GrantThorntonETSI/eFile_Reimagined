$(document).ready(function(){
  //START expand / collapse link button
  $("#markoptions").off("hide.bs.collapse", function(){
    $(".btn .Accordion-trigger").html('<span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span> Uncommon Mark Options');
  });
  $("#markoptions").on("show.bs.collapse", function(){
    $(".btn .Accordion-trigger").html('<span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span> Uncommon Mark Options');
  });
  //END expand / collapse link button
  
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
	});//END toggle panel color
	
	//START vertical center panel examples links
	var a = $( 'div.panel-body' ); 
	$( 'div.panel-body a.vctr' ).css( 'margin-top', ((a.innerHeight()/9) + 24) + 'px' );
	$( 'div.panel-body a.vctr' ).css( 'margin-bottom', ((a.innerHeight()/9) + 24) + 'px' );
	//END vertical center panel examples links
	
	//START panel height match
      var a = $( 'div.match' );
	  $('#panels .panel-body').css( 'min-height', a.innerHeight() );
	//END 
	
	
	//.hover(function() {
  //$( this ).fadeOut( 100 );
  //$( this ).fadeIn( 500 );
	//START exmples panel link hover / active
	  $('#examples div.panel-body a').hover(function() {
		  $( this ).parent().parent().parent().addClass( 'examplehover' );
	  }, function() {
		  $( this ).parent().parent().parent().removeClass( 'examplehover' );
	  });
	//END exmples panel link hover / active
});
