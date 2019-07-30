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
		var f = $('ul.dropdown-menu').eq(0).height();
		var enav = $('.navbar-fixed-top').height();
		var efoot = $('footer').height();
		var winwidth = $(window).width();
		//if (winwidth > 768) {
		$('footer').css('position','relative').css('top',((e - efoot) - enav)).css('margin-top','0');
		$('main#loginform').css('position','relative').css('top',((e - efoot) - enav) / 6);
		if (f > e) {
			$('ul.dropdown-menu').eq(0).css('height', (e - 400)).css('max-height', '340px').css('overflow-y','scroll');
		}
		else {
			$('ul.dropdown-menu').eq(0).css('height', (e - 400)).css('max-height', '340px').css('overflow-y','auto');
		}
		//}
//		else if (winwidth < 767) {
//			$('footer').css('position','relative').css('top',((e - efoot) - enav)).css('margin-top','0');
//			$('main#loginform').css('position','relative').css('top', '0');
//			}
	});
	//
	//START main nav nested dropdown menu
	$('ul.nav .dropdown-menu a.dropdown-toggle').on('click', function() {
		var subMenu = $(this).next('.dropdown-menu');
		$(subMenu).toggleClass('show');
		if ($(subMenu).hasClass( 'show' )) {
			$(this).attr( 'aria-expanded','true' );
		}
		else {
			$(this).attr( 'aria-expanded','false' );
		}
		var e = $( window ).height();
		var f = $('ul.dropdown-menu').eq(0).height();
		if (f > e) {
			$('ul.dropdown-menu').eq(0).css('max-height', (e - 280)).css('overflow-y','scroll');
		}
		else {
			$('ul.dropdown-menu').eq(0).css('max-height', (e - 280)).css('overflow-y','auto');
		}
		return false;
	});
	$('a.dropdown-toggle').eq(0).on('click', function() {
		var subMenushown = $('.dropdown-submenu');
		var submenuwide = $('ul.dropdown-menu').eq(0);
		$(subMenushown).removeClass('show');
		$(subMenushown).prev('a.dropdown-toggle').attr('aria-expanded','false');
		$(submenuwide).css('min-height', '200px').css('overflow-y','auto');
		var winwidth = $(window).width();
		if (winwidth > 766) {
			$(submenuwide).css('width', '389px');
		}
		$( window ).resize(function() {
			var winwidth = $(window).width();
			if (winwidth < 767) {
				$(submenuwide).css('width', 'auto');
			}
		});
	});
	//
	$("button.Accordion-trigger").click(function() {
		var triangles = $('.Accordion-trigger span');
		$(this).find(triangles).toggleClass('visuallyremoved');
	});
	//START accordion (panel) scroll
	$('#responseamend #accordion').on('shown.bs.collapse', function () {
		var panel = $(this).find('.in');
		$('html, body').animate({
			scrollTop: panel.offset().top - 144
		}, 500);
	});
	$('#responseamend div.panel-collapse').on('hide.bs.collapse', function () {
		$(this).parent().find('.panel-heading button span').toggleClass('visuallyremoved');
	});
	$('#responseamend div.panel-collapse').on('show.bs.collapse', function () {
		$(this).parent().find('.panel-heading button span').toggleClass('visuallyremoved');
	});
	$('.Accordion-panel').on('show.bs.collapse shown.bs.collapse hide.bs.collapse hidden.bs.collapse', function (e) {
		e.stopPropagation();
	});
	//
	//START initialize Goods + Services datable
	var table = $('#goodsandservices').DataTable({
		"fnDrawCallback": function( oSettings ) {
			$( '#goodsandservices input[type=checkbox]' ).each(function() {
				var x = $( 'input[type=checkbox]' ).index( this );
				$( this ).attr('id', 'a' + x);
			});
			$( '#goodsandservices label' ).each(function() {
				var y = $( this ).siblings( 'input[type=checkbox]' ).attr('id');
				$( this ).attr('for', y);
			});
		},
		"autoWidth": false,
		"responsive": true,
		"columns": [
			{ "width": "25%" },
			{ "width": "25%" },
			{ "width": "25%" },
			{ "width": "25%" },
		],
		"columnDefs": [
			{ className: "centertxt", "targets": [ 0 ] }
		]
	});
	//
	//START initialize Dashboard datable one
	var tableone = $('#dashboardtableone').DataTable({
		"fnDrawCallback": function( oSettings ) {
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
			{ 'width': '25%' },
			{ 'width': '16%' },
			{ 'width': '12%' },
			{ 'width': '11%' },
		],
		'columnDefs': [
			{ responsivePriority: 1, targets: 0 },//buttons
			{ responsivePriority: 0, targets: 1 },//(control)
			{ responsivePriority: 2, targets: 2 },//serial
			{ responsivePriority: 4, targets: 3 },//reg
			{ responsivePriority: 3, targets: 4 },//owner
			{ responsivePriority: 6, targets: 5 },//status
			{ responsivePriority: 5, targets: 6 },//mark
			{ className: 'centertxt', 'targets': [ 0,2,3,4,5,6 ] },
			{ className: 'control', 'orderable': true, 'targets': [ 1 ] },
		],
	});
	//
	//START initialize Dashboard datable two
	var tabletwo = $('#dashboardtabletwo').DataTable({
		"fnDrawCallback": function( oSettings ) {
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
			{ 'width': '12%' },//toggle show child rows
			{ 'width': '16%' },//serial
			{ 'width': '25%' },//reg
			{ 'width': '11%' },//mark
			{ 'width': '9%' },//owner
			{ 'width': '9%' },
			{ 'width': '9%' },
			{ 'width': '9%' },
		],
		'columnDefs': [
			{ responsivePriority: 1, targets: 0 },//toggle show child rows
			{ responsivePriority: 2, targets: 1 },//serial
			{ responsivePriority: 4, targets: 2 },//reg
			{ responsivePriority: 5, targets: 3 },//mark
			{ responsivePriority: 3, targets: 4 },//owner
			{ className: 'centertxt', 'targets': [ 1,2,3,4,5 ] },
			{ className: 'control', 'orderable': false, 'targets': [ 0 ] },
		],
	});
	//
	//START initialize File Petition datable
	var table = $('#filepetitiontable').DataTable({
		"fnDrawCallback": function( oSettings ) {
		},
		'sDom': '<"toolbarpetitions">lfrtip',
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
				{ name: 'tablet',  width: 1024 },
				{ name: 'fablet',  width: 768 },
				{ name: 'phone',   width: 480 }
			]
		},
		'columns': [
			{ 'width': '8%' },//control 0
			{ 'width': '12%' },//mark 2
			{ 'width': '13%' },//serial 3
			{ 'width': '18%' },//filing 4
			{ 'width': '23%' },//status 5
			{ 'width': '13%' },//owner 6
			{ 'width': '13%' },//attorney 7
		],
		'columnDefs': [
			{ responsivePriority: 1, targets: 0 },
			{ responsivePriority: 2, targets: 5 },
			{ responsivePriority: 3, targets: 1 },
			{ responsivePriority: 4, targets: 6 },
			{ responsivePriority: 5, targets: 2 },
			{ responsivePriority: 6, targets: 4 },
			{ responsivePriority: 7, targets: 3 },
			{ className: 'centertxt', 'targets': [ 1,2,3,4,5 ] },
			{ className: 'control', 'orderable': false, 'targets': [ 0 ] },
		],
	});
	//
	//START initialize Response Amendment datable
	var tablethree = $('#responseamendtable').DataTable({
		"fnDrawCallback": function( oSettings ) {
		},
		'sDom': 't',
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
			{ 'width': '8%' },
			{ 'width': '14%' },
			{ 'width': '24%' },
			{ 'width': '24%' },
			{ 'width': '24%' },
		],
		'columnDefs': [
			{ className: 'centertxt', 'targets': [ 1,2,3,4 ] },
			{ className: 'select-checkbox', 'orderable': false, targets: [ 1 ]},
			{ className: 'control', 'orderable': false, targets: [ 0 ]},
		],
		select: {
			style:    'multi',
			selector: 'tr td:nth-child(1) span'
		},
		order: [[ 1, 'asc' ]]
	});
	//
	//START initialize Response Amendment datable two
	var tablethree = $('#responseamendtabletwo').DataTable({
		"fnDrawCallback": function( oSettings ) {
		},
		'sDom': 't',
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
			{ 'width': '9%' },
			{ 'width': '9%' },
			{ 'width': '13%' },
			{ 'width': '13%' },
			{ 'width': '8%' },
			{ 'width': '8%' },
			{ 'width': '8%' },
			{ 'width': '8%' },
			{ 'width': '8%' },
			{ 'width': '8%' },
			{ 'width': '8%' },
		],
		'columnDefs': [
			{ className: 'centertxt', 'targets': [ 1,2,3,4,5,6,7,8,9 ] },
			{ className: 'select-checkbox', 'orderable': false, targets: [ 1 ]},
			{ className: 'control', 'orderable': false, targets: [ 0 ]},
		],
		select: {
			style:    'multi',
			selector: 'tr td:nth-child(1) span'
		},
		order: [[ 1, 'asc' ]]
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
			if ((hasdraft.length > 0) || (hasdraftchild.length > 0)) {
				if ($(b).css('visibility','hidden')) {
					$(b).css('visibility','visible');
					$(b).css('height','auto');
					$(b).addClass('form-group');
					$(b).addClass('form-group-md');
					$(b).css('float','left');
					$(b).css('top','.25em');
					$(b).css('padding','1em');
					$(b).css('marginBottom','1em');
					$('#mintext').css('display','table-cell');
					$('#alertbtndash').css('display','block');
					$('#alertbtndash').focus();
					window.scrollBy(0, '90%');
					$('#mintext').html('Are you sure you want to delete this ' + draft + '?');
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
				$('#mintext').css('display','none');
				$('#alertbtndash').css('display','none');
				$('#alertbtndash').blur();
				$( deletethis ).remove();
				$( deletethistoo ).remove();
				dt.fnDeleteRow(deletethis);
				dt.fnDeleteRow(deletethistoo);
			}

		});
	});
	//
	//generate unique IDs + matching labels for response / amend pages, checkmarks
	var checkboxList = $('#responseamendtable tr td:nth-child(2) input, #responseamendtabletwo tr td:nth-child(2) input');
	for (var i = 0; i <= checkboxList.length; i++) {
		$(checkboxList[i]).attr('id', 'checkboxone' + i);
	}
	var labelList = $('#responseamendtable tr td:nth-child(2) input, #responseamendtabletwo tr td:nth-child(2) input').next('label');
	for (var i = 0; i <= labelList.length; i++) {
		$(labelList[i]).attr('for', 'checkboxone' + i);
	}
	var labelidList = $('#responseamendtable tr td:nth-child(2) input, #responseamendtabletwo tr td:nth-child(2) input').next('label');
	for (var i = 0; i <= labelidList.length; i++) {
		$(labelidList[i]).attr('id', 'selectrow_' + i);
	}
	var checkboxarialabelList = $('#responseamendtable tr td:nth-child(2) input, #responseamendtabletwo tr td:nth-child(2) input');
	for (var i = 0; i <= checkboxarialabelList.length; i++) {
		$(checkboxarialabelList[i]).attr('aria-labelledby', 'selectrow_' + i);
	}
	//
	//start select all response / amend
	$(document).on('change','#responseamendtable input#selectall', function() {
		var checkboxes  = $('#responseamendtable tr td:nth-child(2) span');
		var rows  = $('#responseamendtable tr');
		var input  = $('#responseamendtable tr td:nth-child(2) input.form-check-input');
		var selectall = $(this);
		if ($(this).is( ":checked" )) {
			$(rows).addClass('selected');
			$(input).prop('checked', true);
		} else {
			$(rows).removeClass('selected');
			$(input).prop('checked', false);
		}
	});
	$('#responseamendtable tr td:nth-child(2) input').on('change', function(e){
		$('#responseamendtable input#selectall').prop('checked', false);
		if ($(this).is( ":checked" )) {
			$(this).parent().parent().addClass('selected');
		} else {
			$(this).parent().parent().removeClass('selected');
		}
	});
	$(document).on('change','#responseamendtabletwo input#selectalltwo', function() {
		var checkboxes  = $('#responseamendtabletwo tr td:nth-child(2) span');
		var rows  = $('#responseamendtabletwo tr');
		var input  = $('#responseamendtabletwo tr td:nth-child(2) input');
		var selectall = $(this);
		if ($(this).is( ":checked" )) {
			$(rows).addClass('selected');
			$(input).prop('checked', true);
		} else {
			$(rows).removeClass('selected');
			$(input).prop('checked', false);
		}
	});
	$('#responseamendtabletwo tr td:nth-child(2) input').on('change', function(e){
		$('#responseamendtabletwo input#selectalltwo').prop('checked', false);
		if ($(this).is( ":checked" )) {
			$(this).parent().parent().addClass('selected');
		} else {
			$(this).parent().parent().removeClass('selected');
		}
	});
	//
	//Dashboard datatables ellipsis menu tableone
	$("div.toolbar").html('<div class="dropdown" aria-live="assertive"><button class="btn btn-xs dropdown-toggle" id="dropdownMenucolvis" data-toggle="dropdown" aria-controls="elipsisdrop" aria-haspopup="true" aria-expanded="false" aria-label="toggle columns visibility"><span class="glyphicon glyphicon-option-vertical" aria-hidden="true"></span></button><ul class="dropdown-menu" aria-labelledby="dropdownMenucolvis" id="elipsisdrop" role="menu"><li class="dropdown-header">Toggle Columns</li><li role="menuitem" aria-label="hide this column"><a class="toggle-vis" data-column="0" tabindex="0"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Serial#</a></li><li role="menuitem" aria-label="hide this column"><a class="toggle-vis" data-column="1" tabindex="0"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Registration#</a></li><li role="menuitem" aria-label="hide this column"><a class="toggle-vis" data-column="2" tabindex="0"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Owner</a></li><li role="menuitem" aria-label="hide this column"><a class="toggle-vis" data-column="3" tabindex="0"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Status</a></li><li role="menuitem" aria-label="hide this column"><a class="toggle-vis" data-column="4" tabindex="0"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Mark</a></li></ul></div>');
	$('a.toggle-vis').on( 'click', function () {
		//e.preventDefault();
		var column = tableone.column( $(this).attr('data-column') );
		column.visible( ! column.visible() );
		$(column).attr('aria-hidden','true')
		$(this).attr('aria-label','show this column');
	});
	//
	//Dashboard datatables ellipsis menu tabletwo
	$("div.toolbartwo").html('<div class="dropdown" aria-live="assertive"><button class="btn btn-xs dropdown-toggle" id="dropdownMenucolvis" data-toggle="dropdown" aria-controls="elipsisdroptwo" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-option-vertical aria-hidden="true"></span></button><ul class="dropdown-menu" aria-labelledby="dropdownMenucolvis" id="elipsisdroptwo" role="menu"><li class="dropdown-header">Toggle Columns</li><li role="menuitem" aria-label="hide this column"><a class="toggle-vistwo" data-column="0" tabindex="0"><span class="glyphicon glyphicon-ok" aria-label="hide this column"></span>Serial#</a></li><li role="menuitem" aria-label="hide this column"><a class="toggle-vistwo" data-column="1" tabindex="0"><span class="glyphicon glyphicon-ok" aria-label="hide this column"></span>Registration#</a></li><li role="menuitem" aria-label="hide this column"><a class="toggle-vistwo" data-column="2" tabindex="0"><span class="glyphicon glyphicon-ok" aria-label="hide this column"></span>Mark</a></li><li role="menuitem" aria-label="hide this column"><a class="toggle-vistwo" data-column="3" tabindex="0"><span class="glyphicon glyphicon-ok" aria-label="hide this column"></span>Owner</a></li><li role="menuitem" aria-label="hide this column"><a class="toggle-vistwo" data-column="4" tabindex="0"><span class="glyphicon glyphicon-ok" aria-label="hide this column"></span>Due Date</a></li><li role="menuitem" aria-label="hide this column"><a class="toggle-vistwo" data-column="5" tabindex="0"><span class="glyphicon glyphicon-ok" aria-label="hide this column"></span>Status</a></li><li role="menuitem" aria-label="hide this column"><a class="toggle-vistwo" data-column="6" tabindex="0"><span class="glyphicon glyphicon-ok" aria-label="hide this column"></span>Action</a></li></ul></div>');
	$('a.toggle-vistwo').on( 'click', function () {
		//e.preventDefault();
		var column = tabletwo.column( $(this).attr('data-column') );
		column.visible( ! column.visible() );
		$(column).attr('aria-hidden','true');
		$(this).attr('aria-label','show this column');
		console.log(column);
	});
	//
	//Datatable filter menu petitions
	$("div.toolbarpetitions").html('<div class="dropdown" aria-live="assertive"><button class="btn btn-xs dropdown-toggle" id="dropdownMenucolvis" data-toggle="dropdown" aria-controls="elipsisdrop" aria-haspopup="true" aria-expanded="false" aria-label="filter row visibility"><span class="">Filter</span></button><ul class="dropdown-menu" aria-labelledby="dropdownMenucolvis" id="elipsisdrop" role="menu"><li role="menuitem" aria-label="filter these rows"><a class="toggle-vis" data-column="0" tabindex="0" id="revive"><span class="glyphicon glyphicon-ok visuallyhidden" aria-hidden="true"></span>Petition to Revive</a></li><li role="menuitem" aria-label="filter these rows"><a class="toggle-vis disabled" data-column="1" tabindex="0"><span class="glyphicon glyphicon-ok visuallyhidden" aria-hidden="true"></span>Petition to Director</a></li><li role="menuitem" aria-label="filter these rows"><a class="toggle-vis disabled" data-column="2" tabindex="0"><span class="glyphicon glyphicon-ok visuallyhidden" aria-hidden="true"></span>Petition to Make Special</a></li><li role="menuitem" aria-label="filter these rows"><a class="toggle-vis disabled" data-column="3" tabindex="0"><span class="glyphicon glyphicon-ok visuallyhidden" aria-hidden="true"></span>Request for Reinstatement</a></li></ul></div>');
	$('#petitions a.toggle-vis').on( 'click', function () {
		//e.preventDefault();
		$(this).attr('aria-label','unfilter these rows');
	});
	//
	//start toggle glyphicon dashboard toggle menu widget
	function togglecolumnselection() {
		$( this ).find('span.glyphicon-ok').toggleClass( 'visuallyhidden' ).attr('aria-label','show this column');
	}
	$('#dashboard a.toggle-vis').click(togglecolumnselection);
	$('#dashboard a.toggle-vistwo').click(togglecolumnselection);
	//
	//START toggle glyphicon petitions filter menu widget
	function togglecolumnselectionpetitions() {
		$( this ).find('span.glyphicon-ok').toggleClass( 'visuallyshown' ).attr('aria-label','unfilter these rows');
	}
	$('#petitions a.toggle-vis').click(togglecolumnselectionpetitions);
	//
	//START toggle glyphicon petitions filter menu widget
	$('button.dropdown-toggle').on( 'click', function () {
		$(this).attr('aria-expanded','true');
	});
	//
	//START Update ARIA label when sorting
	$(document).on('click', 'table#dashboardtableone th, table#dashboardtabletwo th, table#filepetitiontable th, table#responseamendtable th', function() {
		$( this ).toggleClass( 'focus' );
		$( this ).find('span.glyphicon-triangle-bottom').toggleClass( 'glyphicon-triangle-top' ).attr('aria-hidden','true');
	});
	//
	//START .dashsection height = #announcedashsection height
	var h = $( 'div#announcedashsection' );
	var y = $( 'div#announce' );
	$('.dashsection:eq(0), .dashsection:eq(1)').css( 'min-height', (h.innerHeight() + y.innerHeight()) );
	$( window ).resize(function() {
		$('.dashsection:eq(0), .dashsection:eq(1)').css( 'min-height', (h.innerHeight() + y.innerHeight()) );
	});
	//
	//START close (x) dashboard panels
	$('#dashsectionscontainer #announcements .closegspanels').click(function() {
		$( this ).parent().parent().parent().parent().parent().fadeOut( 'fast','swing');
	});
	//
	//START filter petition types
	$(document).on('click','#petitions a#revive', function() {
		var a = $('#filepetitiontable td:contains("abandoned")');
		//var aa = $('#filepetitiontable tr[data-petition="revive"]');
		var aaa = $(a).parent().siblings();
		//$(document).find(a).parent().attr('data-petition','revive');
		$( aaa ).toggleClass('visuallyremoved');
	});
	//
	//START close (x) gs + editowner + reviewattorney panels
	$('#gsselected .closegspanels, #editowner .closegspanels, #reviewattorney .closegspanels').click(function() {
		$( this ).parent().parent().parent().fadeOut( 'fast','swing');
	});
	//
	//START set initial checkboxes
	$('input[type=checkbox]').attr('checked',false);
	$('input[type=radio]').attr('checked',false);
	$('input#tradeserv').prop('checked',true);
	$('input[type=radio]#inlineRadio044').prop('checked', true);
	$('input[type=radio]#inlineRadio031').prop('checked',true);
	$('input[type=radio]#inlineRadio059').prop('checked',true);
	$('input[type=radio]#inlineRadio061').prop('checked',true);
	//if ($('input[type=radio]#inlineRadio059').prop('checked')) {
//	console.log('checked');
//	}
	//$('input[type=radio]#inlineRadio060').change(function() {
//		if ($('input[type=radio]#inlineRadio059').prop('checked')) {
//		console.log('checked');
//		}
//		else{
//		console.log('unchecked');
//		}
//	});
	//
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
		else {
			$('li#classli span').html('<span>' + highestfeeperclass + '</span>');
			$('li#lastli span').html('<span>' + lastfees + '</span>');
			$('span#displayfees').html('<span>' + lastfees + '</span>');
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
	//
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
			$('.form-check span.radio.notspecial::before').removeClass('disabled');
			$('.form-check#collectcheck2 span.radio').removeClass('special');
			$('.form-check span.radio.notspecial::before').removeClass('disabled');
			$('.form-check#collectm2 span.radio').removeClass('special');
			$('.form-check span.radio.notspecial::before').removeClass('disabled');
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
			$('.form-check span.radio.notspecial::before').removeClass('disabled');
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
			$('.form-check span.radio.notspecial::before').removeClass('disabled');
			$('input#certcheck').attr('aria-disabled', 'false');
		}
	});
	//When 'Trademark / Servicemark' is checked, 'Certification' is disabled
	if ($('input#tradeserv').prop('checked',true)) {
		$('input#certcheck').prop({
			disabled: true,
			checked: false,
		});
		$('.form-check#certcheck2 span.radio').addClass('special');
		$('input#certcheck').attr('aria-disabled', 'true');
	};
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
			$('.form-check span.radio.notspecial::before').removeClass('disabled');
			$('input#certcheck').attr('aria-disabled', 'false');
		}
	});//
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
			var clearthis = $('div#cad');
			var clearall = {};
			$(function(){
				var clear = function(){
					$(clearthis).find('input[type="file"]').val('');
					$(clearthis).find('.hidethis').contents().css('display','block');
					$(clearthis).find('.upload-drop-zone').css('height','115px');
					$(clearthis).find('.js-upload-finished button.close').parent().parent().remove();
					$(clearthis).find('div.fileDisplayArea img').remove();
					$(clearthis).find('div.js-upload-finished').css('display','none');
					$(clearthis).find('button[type=submit]').css('display','none');
					$(clearthis).find('div.fileDisplayArea').html('<span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .pdf, .doc');
					$(clearthis).find('input[type=radio]').prop('checked', false);
					$(clearthis).find('input[type=checkbox]').prop('checked', false);
					$(clearthis).find('input').val('');
					$(clearthis).find('textarea').val('');
					$(clearthis).find('div.appendreg').contents().remove();
				};
				clearall = clear;
			});
			$(clearall);
			$('#cad').hide( 'fast' );
			$('#yesdistict').hide( 'fast' );
			$('#yeswhole').hide( 'fast' );
			$('#yespart').hide( 'fast' );
			$('#yesevidence').hide( 'fast' );
		}
	});
	$('div#uaf').css('display','none');
	$('input#useanotherform').change(function() {
		if(this.checked == true){
			$('#uaf').show( 'fast' );
		}
		else {
			var clearthis = $('div#uaf');
			var resetselect = [ 'mm/dd/yy' ];
			var resetselect = jQuery.makeArray( resetselect );
			var clearall = {};
			$(function(){
				var clear = function(){
					$(clearthis).find('select[type=date]').val( resetselect[0] );
					$(clearthis).find('input[type=radio]').prop('checked', false);
					$(clearthis).find('input').val('');
					$(clearthis).find('textarea').val('');
				};
				clearall = clear;
			});
			$(clearall);
			$('#yesotherform').hide( 'fast' );
			$('#uaf').hide( 'fast' );
		}
	});
	$('div#cc').css('display','none');
	$('input#concurruse').change(function() {
		if(this.checked == true){
			$('#cc').show( 'fast' );
		}
		else {
			$('#cc').find('input[type=radio]').prop('checked', false);
			var clearthis = $('#cc');
			var clearall = {};
			$(function(){
				var clear = function(){
					$(clearthis).find('input[type="file"]').val('');
					$(clearthis).find('.hidethis').contents().css('display','block');
					$(clearthis).find('.upload-drop-zone').css('height','115px');
					$(clearthis).find('.js-upload-finished button.close').parent().parent().remove();
					$(clearthis).find('div.fileDisplayArea img').remove();
					$(clearthis).find('div.js-upload-finished').css('display','none');
					$(clearthis).find('button[type=submit]').css('display','none');
					$(clearthis).find('div.fileDisplayArea').html('<span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .pdf, .doc');
					$(clearthis).find('input[type=radio]').prop('checked', false);
					$(clearthis).find('input[type=checkbox]').prop('checked', false);
					$(clearthis).find('select').val('Select');
					$(clearthis).find('input').val('');
					$(clearthis).find('textarea').val('');
					$(clearthis).find('div.upload').hide('fast');
					$(clearthis).find('div.appendaconcregapp_courtd').contents().remove();
				};
				clearall = clear;
			});
			$(clearall);
			$('#hideshowconcurrentuses_courtd').hide( 'fast' );
			$('#hideshowconcurrentuses_ttab').hide( 'fast' );
			$('#hideshowconcurrentuses_conflictingreg').hide( 'fast' );
			$('#hideshowconcurrentuses_earlieruse').hide( 'fast' );
			$('#cc').hide( 'fast' );
		}
	});
	//
	//START distinctive options
	$('div#yesdistict').css('display','none');
	$('input#inlineRadio033').change(function() {
		if(this.checked == true){
			var clearthis = $(this).closest('fieldset');
			var clearall = {};
			$(function(){
				var clear = function(){
					$(clearthis).find('input[type="file"]').val('');
					$(clearthis).find('.hidethis').contents().css('display','block');
					$(clearthis).find('.upload-drop-zone').css('height','115px');
					$(clearthis).find('.js-upload-finished button.close').parent().parent().remove();
					$(clearthis).find('div.fileDisplayArea img').remove();
					$(clearthis).find('div.js-upload-finished').css('display','none');
					$(clearthis).find('button[type=submit]').css('display','none');
					$(clearthis).find('div.fileDisplayArea').html('<span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .pdf, .doc');
					$(clearthis).find('div.upload').hide('fast');
					$(clearthis).find('input[type=radio]').prop('checked', false);
					$(this).prop('checked', true);
				};
				clearall = clear;
			});
			$(clearall);
			$(this).prop('checked', true);
			$('div#yesdistict').show( 'fast' );
		}
		else {
			$('div#yesdistict').hide( 'fast' );
		}
	});
	$('input#inlineRadio034').change(function() {
		if(this.checked == true){
			var clearthis = $(this).closest('fieldset');
			var clearall = {};
			$(function(){
				var clear = function(){
					$(clearthis).find('input[type="file"]').val('');
					$(clearthis).find('.hidethis').contents().css('display','block');
					$(clearthis).find('.upload-drop-zone').css('height','115px');
					$(clearthis).find('.js-upload-finished button.close').parent().parent().remove();
					$(clearthis).find('div.fileDisplayArea img').remove();
					$(clearthis).find('div.js-upload-finished').css('display','none');
					$(clearthis).find('button[type=submit]').css('display','none');
					$(clearthis).find('div.fileDisplayArea').html('<span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .pdf, .doc');
					$(clearthis).find('div.upload').hide('fast');
					$(clearthis).find('input[type=radio]').prop('checked', false);
				};
				clearall = clear;
			});
			$(clearall);
			$('div#yeswhole').css('display','none');
			$(this).prop('checked', true);
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
			$('div#yeswhole').hide( 'fast' );
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
			$('div#yespriors').find('input').val('');
			$('div.appendreg').contents().remove();
			$('div#yespriors').hide( 'fast' );
		}
		else {
			$('div#yesevidence').hide( 'fast' );
		}
	});
	$('input#inlineRadio038').change(function() {
		if(this.checked == true){
			var clearthis = $(this).closest('div#yeswhole').parent();
			var clearall = {};
			$(function(){
				var clear = function(){
					$(clearthis).find('input[type="file"]').val('');
					$(clearthis).find('.hidethis').contents().css('display','block');
					$(clearthis).find('.upload-drop-zone').css('height','115px');
					$(clearthis).find('.js-upload-finished button.close').parent().parent().remove();
					$(clearthis).find('div.fileDisplayArea img').remove();
					$(clearthis).find('div.js-upload-finished').css('display','none');
					$(clearthis).find('button[type=submit]').css('display','none');
					$(clearthis).find('div.fileDisplayArea').html('<span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .pdf, .doc');
					$(clearthis).find('div.upload').hide('fast');
				};
				clearall = clear;
			});
			$(clearall);
			$('div.resetreg').removeClass( 'visuallyremoved' );
			$('div#yesevidence').hide( 'fast' );
			$('div#yespriors').show( 'fast' );
		}
		else {
			$('div#yespriors').hide( 'fast' );
		}
	});
	$('input#inlineRadio039').change(function() {
		if(this.checked == true){
			var clearthis = $(this).closest('div#yeswhole').parent();
			var clearall = {};
			$(function(){
				var clear = function(){
					$(clearthis).find('input[type="file"]').val('');
					$(clearthis).find('.hidethis').contents().css('display','block');
					$(clearthis).find('.upload-drop-zone').css('height','115px');
					$(clearthis).find('.js-upload-finished button.close').parent().parent().remove();
					$(clearthis).find('div.fileDisplayArea img').remove();
					$(clearthis).find('div.js-upload-finished').css('display','none');
					$(clearthis).find('button[type=submit]').css('display','none');
					$(clearthis).find('div.fileDisplayArea').html('<span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .pdf, .doc');
					$(clearthis).find('div.upload').hide('fast');
				};
				clearall = clear;
			});
			$(clearall);
			$('div#yesevidence').hide( 'fast' );
			$('div#yespriors').find('input').val('');
			$('div.appendreg').contents().remove();
			$('div#yespriors').hide( 'fast' );
		};
	});
	//basis + postreg classes provide specimen options
	$('div.yesonespecimen').css('display','none');
	$('div.individualspecimen').css('display','none');
	$('input.yesone').on('change',function(){
		if(this.checked == true){
			//$(this).closest('fieldset').find( 'div[role="radiogroup"] input[name="use"]:even').prop('checked', false).prop('disabled','true').attr('aria-disabled','true').addClass('disabled');
			//$(this).closest('fieldset').find( 'div[role="radiogroup"] input[name="use"]:even').parent('label').addClass('disabled').addClass('special');
			$(this).closest('fieldset').find( 'section.basis div.individualspecimen input.showspecimen' ).prop('checked', false);
			var clearthis = $(this).closest('fieldset');
			var clearall = {};
			$(function(){
				var clear = function(){
					$(clearthis).find('section.basis input[type="file"]').val('');
					$(clearthis).find('section.basis div.yesspecimen textarea').val('');
					$(clearthis).find('section.basis div.fileholder .hidethis').contents().css('display','block');
					$(clearthis).find('section.basis div.fileholder .row').remove();
					$(clearthis).find('section.basis .upload-drop-zone').css('height','115px');
					$(clearthis).find('section.basis .js-upload-finished button.close').parent().parent().remove();
					$(clearthis).find('section.basis div.fileDisplayArea img').remove();
					$(clearthis).find('section.basis div.js-upload-finished').css('display','none');
					$(clearthis).find('section.basis button[type=submit]').css('display','none');
					$(clearthis).find('section.basis div.fileDisplayArea').html('<span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .tiff, .png');
					$(clearthis).find('section.basis div.upload').hide('fast');
				};
				clearall = clear;
			});
			$(clearall);
			$('section.basis div.yesspecimen').hide('fast');
			$('div.yesonespecimen .upload').show('fast');
			$(this).closest('section').find('div.yesonespecimen').show( 'fast' );
			$(this).closest('section').find('div.yesonespecimen div.hidethis').contents().css('display','block');
			$(this).closest('section').find('div.yesonespecimen .upload-drop-zone').css('height','115px');
			$(this).closest('section').find('div.yesonespecimen div.js-upload-finished').css('display','none');
			$(this).closest('fieldset').find('div.individualspecimen').hide( 'fast' );
		}
		else {
			$('div.upload').find('div.fileholder');
			$(this).closest('section').find('div.yesonespecimen').hide( 'fast' );
			$(this).closest('fieldset').find('div.individualspecimen').show( 'fast' );
		}
	});
	$('input.yesoneeach').on('change',function(){
		if(this.checked == true){
			$(this).closest('fieldset').find('div.individualspecimen').show('fast');
			$(this).closest('section.basis div.upload .hidethis').contents().css('display','block');
			$(this).closest('section.basis div.yesspecimen textarea').css('display','block');
			$(this).closest('section.basis div.yesspecimen label').css('display','block');
			var clearthis = $(this).closest('fieldset');
			var clearall = {};
			$(function(){
				var clear = function(){
					$(clearthis).find('div.yesonespecimen input[type="file"]').val('');
					$(clearthis).find('div.yesonespecimen div.yesspecimen textarea').val('');
					$(clearthis).find('div.yesonespecimen div.fileholder .hidethis').contents().css('display','block');
					$(clearthis).find('div.yesonespecimen div.fileholder .row').remove();
					$(clearthis).find('div.yesonespecimen .upload-drop-zone').css('height','115px');
					$(clearthis).find('div.yesonespecimen .js-upload-finished button.close').parent().parent().remove();
					$(clearthis).find('div.yesonespecimen div.fileDisplayArea img').remove();
					$(clearthis).find('div.yesonespecimen div.js-upload-finished').css('display','none');
					$(clearthis).find('div.yesonespecimen button[type=submit]').css('display','none');
					$(clearthis).find('div.yesonespecimen div.fileDisplayArea').html('<span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .tiff, .png');
					$(clearthis).find('div.yesonespecimen div.upload').hide('fast');
				};
				clearall = clear;
			});
			$(clearall);
			$(this).closest('section').find('div.yesonespecimen').hide('fast');

		}
		else {
			$('div.upload').find('div.fileholder');
			$(this).closest('section').find('div.yesonespecimen').show( 'fast' );
			$(this).closest('fieldset').find('div.individualspecimen').hide( 'fast' );
		}
	});
	$('input.yesoneeachpost').on('change',function(){
		if(this.checked == true){
			//$(this).closest('fieldset').find( 'div[role="radiogroup"] input[name="use"]:even').prop('disabled','false').removeAttr('disabled').attr('aria-disabled','false').removeClass('disabled');
			//$(this).closest('fieldset').find( 'div[role="radiogroup"] input[name="use"]:even').parent('label').removeClass('disabled').removeClass('special');
			$(this).closest('section').find('textarea#specdescript' ).val('');
			$(this).closest('section').find('div.yesonespecimen input[type="file"]').val('');
			$(this).closest('section').find('div.yesonespecimen button.close').parent().parent().remove();
			$(this).closest('section').find('div.yesonespecimen div.fileDisplayArea img').remove();
			$(this).closest('section').find('div.yesonespecimen button[type=submit]').css('display','none');
			$(this).closest('section').find('div.yesonespecimen div.fileDisplayArea').html('<span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .tiff, .png');
			$(this).closest('section').find('div.yesonespecimen div.upload').hide('fast');
			$(this).closest('section').find('div.yesonespecimen').hide('fast');
		}
	});
	if ( $( 'div#sectioneight' ).length ) {
		$('div.nonuse').css('display', 'none');
	}
	//START sectioneight delete gs
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
	//
	$('section.basis div[role="radiogroup"] input:odd').on('change',function(){
		if(this.checked == true){
			var clearthis = $(this).closest('section');
			var clearall = {};
			$(function(){
				var clear = function(){
					$(clearthis).find('div.yesspecimen input[type="file"]').val('');
					$(clearthis).find('div.yesspecimen textarea').val('');
					$(clearthis).find('div.yesspecimen div.fileholder .hidethis').contents().css('display','block');
					$(clearthis).find('div.yesspecimen div.fileholder .row').remove();
					$(clearthis).find('div.yesspecimen .upload-drop-zone').css('height','115px');
					$(clearthis).find('div.yesspecimen .js-upload-finished button.close').parent().parent().remove();
					$(clearthis).find('div.yesspecimen div.fileDisplayArea img').remove();
					$(clearthis).find('div.yesspecimen div.js-upload-finished').css('display','none');
					$(clearthis).find('div.yesspecimen button[type=submit]').css('display','none');
					$(clearthis).find('div.yesspecimen div.fileDisplayArea').html('<span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .tiff, .png');
					$(clearthis).find('div.yesspecimen div.upload').hide('fast');
				};
				clearall = clear;
			});
			$(clearall);
			$(this).closest('section').find('div.yesspecimen').hide( 'fast' );
			$(this).closest('section').find('div.nonuse').show( 'fast' );
		}
		else {
			$(this).closest('section').find('div.nonuse').hide( 'fast' );
		}
	});
	$('section.basis div[role="radiogroup"] input:even').on('change',function(){
		if(this.checked == true){
			var selectedyesoneach = $(this).closest('fieldset').find('input.yesoneeachpost');
			$(selectedyesoneach);
			if($(selectedyesoneach).prop('checked')) {
				$(this).closest('section').find('div.nonuse').hide( 'fast' );
				$(this).closest('section').find('div.yesspecimen').show('fast');
				$(this).closest('section').find('div.upload').css('display','block');
				$(this).closest('section.basis').find('div.upload .hidethis').contents().css('display','block');
				$(this).closest('section.basis').find( '.upload-drop-zone' ).css('height','115px');
				$(this).closest('section.basis').find('div.yesspecimen textarea').css('display','block');
				$(this).closest('section.basis').find('div.yesspecimen label').css('display','block');
				$(this).closest('section').find('textarea#specdescript' ).val('');
			}
		}
	});
	//hide / show concurrent uses
	$('div#hideshowconcurrentuses_courtd').css('display','none');
	$('div#hideshowconcurrentuses_ttab').css('display','none');
	$('div#hideshowconcurrentuses_conflictingreg').css('display','none');
	$('div#hideshowconcurrentuses_earlieruse').css('display','none');
	$('input#inlineRadio052').change(function() {
		if(this.checked == true){
			$('div#hideshowconcurrentuses_courtd').show( 'fast' );
			$('div#hideshowconcurrentuses_ttab').hide( 'fast' );
			$('div#hideshowconcurrentuses_conflictingreg').hide( 'fast' );
			$('div#hideshowconcurrentuses_earlieruse').hide( 'fast' );
		}
		var resetselect = [ 'Select','mm/dd/yy' ];
		var resetselect = jQuery.makeArray( resetselect );
		$('div#hideshowconcurrentuses_ttab input[type=date], div#hideshowconcurrentuses_conflictingreg input[type=date], div#hideshowconcurrentuses_earlieruse input[type=date]').val(resetselect[1]);
		$('div#hideshowconcurrentuses_ttab textarea, div#hideshowconcurrentuses_conflictingreg textarea, div#hideshowconcurrentuses_earlieruse textarea').val('');
		$('div#hideshowconcurrentuses_ttab input.checkmark, div#hideshowconcurrentuses_conflictingreg input.checkmark, div#hideshowconcurrentuses_earlieruse input.checkmark').prop('checked', false);
		$('div#hideshowconcurrentuses_ttab input[type=text], div#hideshowconcurrentuses_conflictingreg input[type=text], div#hideshowconcurrentuses_earlieruse input[type=text]').val('');
		$('div#hideshowconcurrentuses_ttab select, div#hideshowconcurrentuses_conflictingreg select, div#hideshowconcurrentuses_earlieruse select').val( resetselect[0] );
		$('div.appendaconcregapp_ttab div.holdsaconcregapp_ttab, div.appendaconcregapp_conflictingreg div.holdsaconcregapp_conflictingreg, div.appendaconcregapp_earlieruse div.holdsaconcregapp_earlieruse').remove();
		$('div#hideshowconcurrentuses_ttab .hidethis, div#hideshowconcurrentuses_conflictingreg .hidethis, div#hideshowconcurrentuses_earlieruse .hidethis').contents().css('display','block');
		$('div#hideshowconcurrentuses_ttab .js-upload-finished, div#hideshowconcurrentuses_conflictingreg .js-upload-finished, div#hideshowconcurrentuses_earlieruse .js-upload-finished').css('display','none');
		$('div#hideshowconcurrentuses_ttab .upload-drop-zone, div#hideshowconcurrentuses_conflictingreg .upload-drop-zone, div#hideshowconcurrentuses_earlieruse .upload-drop-zone').css('height','115px');
		$('div#hideshowconcurrentuses_ttab #usaffiliation .upload-drop-zone, div#hideshowconcurrentuses_conflictingreg #usaffiliation .upload-drop-zone, div#hideshowconcurrentuses_earlieruse #usaffiliation .upload-drop-zone').css('height','175px');
		$('div#hideshowconcurrentuses_ttab .js-upload-finished button.close, div#hideshowconcurrentuses_conflictingreg .js-upload-finished button.close, div#hideshowconcurrentuses_earlieruse .js-upload-finished button.close' ).parent().parent().remove();
		$('div#hideshowconcurrentuses_ttab div.fileDisplayArea img, div#hideshowconcurrentuses_conflictingreg div.fileDisplayArea img, div#hideshowconcurrentuses_earlieruse div.fileDisplayArea img' ).remove();
		$('div#hideshowconcurrentuses_ttab div.fileDisplayArea, div#hideshowconcurrentuses_conflictingreg div.fileDisplayArea, div#hideshowconcurrentuses_earlieruse div.fileDisplayArea' ).html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .pdf, .doc</div>');
	});
	$('input#inlineRadio053').change(function() {
		if(this.checked == true){
			$('div#hideshowconcurrentuses_ttab').show( 'fast' );
			$('div#hideshowconcurrentuses_courtd').hide( 'fast' );
			$('div#hideshowconcurrentuses_conflictingreg').hide( 'fast' );
			$('div#hideshowconcurrentuses_earlieruse').hide( 'fast' );
		}
		var resetselect = [ 'Select','mm/dd/yy' ];
		var resetselect = jQuery.makeArray( resetselect );
		$('div#hideshowconcurrentuses_courtd input[type=date], div#hideshowconcurrentuses_conflictingreg input[type=date], div#hideshowconcurrentuses_earlieruse input[type=date]').val(resetselect[1]);
		$('div#hideshowconcurrentuses_courtd textarea, div#hideshowconcurrentuses_conflictingreg textarea, div#hideshowconcurrentuses_earlieruse textarea').val('');
		$('div#hideshowconcurrentuses_courtd input.checkmark, div#hideshowconcurrentuses_conflictingreg input.checkmark, div#hideshowconcurrentuses_earlieruse input.checkmark').prop('checked', false);
		$('div#hideshowconcurrentuses_courtd input[type=text], div#hideshowconcurrentuses_conflictingreg input[type=text], div#hideshowconcurrentuses_earlieruse input[type=text]').val('');
		$('div#hideshowconcurrentuses_courtd select, div#hideshowconcurrentuses_conflictingreg select, div#hideshowconcurrentuses_earlieruse select').val( resetselect[0] );
		$('div.appendaconcregapp_courtd div.holdsaconcregapp_courtd, div.appendaconcregapp_conflictingreg div.holdsaconcregapp_conflictingreg, div.appendaconcregapp_earlieruse div.holdsaconcregapp_earlieruse').remove();
		$('div#hideshowconcurrentuses_courtd .hidethis, div#hideshowconcurrentuses_conflictingreg .hidethis, div#hideshowconcurrentuses_earlieruse .hidethis').contents().css('display','block');
		$('div#hideshowconcurrentuses_courtd .js-upload-finished, div#hideshowconcurrentuses_conflictingreg .js-upload-finished, div#hideshowconcurrentuses_earlieruse .js-upload-finished').css('display','none');
		$('div#hideshowconcurrentuses_courtd .upload-drop-zone, div#hideshowconcurrentuses_conflictingreg .upload-drop-zone, div#hideshowconcurrentuses_earlieruse .upload-drop-zone').css('height','115px');
		$('div#hideshowconcurrentuses_courtd #usaffiliation .upload-drop-zone, div#hideshowconcurrentuses_conflictingreg #usaffiliation .upload-drop-zone, div#hideshowconcurrentuses_earlieruse #usaffiliation .upload-drop-zone').css('height','175px');
		$('div#hideshowconcurrentuses_courtd .js-upload-finished button.close, div#hideshowconcurrentuses_conflictingreg .js-upload-finished button.close, div#hideshowconcurrentuses_earlieruse .js-upload-finished button.close' ).parent().parent().remove();
		$('div#hideshowconcurrentuses_courtd div.fileDisplayArea img, div#hideshowconcurrentuses_conflictingreg div.fileDisplayArea img, div#hideshowconcurrentuses_earlieruse div.fileDisplayArea img' ).remove();
		$('div#hideshowconcurrentuses_courtd div.fileDisplayArea, div#hideshowconcurrentuses_conflictingreg div.fileDisplayArea, div#hideshowconcurrentuses_earlieruse div.fileDisplayArea' ).html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .pdf, .doc</div>');
	});
	$('input#inlineRadio054').change(function() {
		if(this.checked == true){
			$('div#hideshowconcurrentuses_conflictingreg').show( 'fast' );
			$('div#hideshowconcurrentuses_ttab').hide( 'fast' );
			$('div#hideshowconcurrentuses_courtd').hide( 'fast' );
			$('div#hideshowconcurrentuses_earlieruse').hide( 'fast' );
		}
		var resetselect = [ 'Select','mm/dd/yy' ];
		var resetselect = jQuery.makeArray( resetselect );
		$('div#hideshowconcurrentuses_courtd input[type=date], div#hideshowconcurrentuses_ttab input[type=date], div#hideshowconcurrentuses_earlieruse input[type=date]').val(resetselect[1]);
		$('div#hideshowconcurrentuses_courtd textarea, div#hideshowconcurrentuses_ttab textarea, div#hideshowconcurrentuses_earlieruse textarea').val('');
		$('div#hideshowconcurrentuses_courtd input.checkmark, div#hideshowconcurrentuses_ttab input.checkmark, div#hideshowconcurrentuses_earlieruse input.checkmark').prop('checked', false);
		$('div#hideshowconcurrentuses_courtd input[type=text], div#hideshowconcurrentuses_ttab input[type=text], div#hideshowconcurrentuses_earlieruse input[type=text]').val('');
		$('div#hideshowconcurrentuses_courtd select, div#hideshowconcurrentuses_ttab select, div#hideshowconcurrentuses_earlieruse select').val( resetselect[0] );
		$('div.appendaconcregapp_courtd div.holdsaconcregapp_courtd, div.appendaconcregapp_ttab div.holdsaconcregapp_ttab, div.appendaconcregapp_earlieruse div.holdsaconcregapp_earlieruse').remove();
		$('div#hideshowconcurrentuses_courtd .hidethis, div#hideshowconcurrentuses_ttab .hidethis, div#hideshowconcurrentuses_earlieruse .hidethis').contents().css('display','block');
		$('div#hideshowconcurrentuses_courtd .js-upload-finished, div#hideshowconcurrentuses_ttab .js-upload-finished, div#hideshowconcurrentuses_earlieruse .js-upload-finished').css('display','none');
		$('div#hideshowconcurrentuses_courtd .upload-drop-zone, div#hideshowconcurrentuses_ttab .upload-drop-zone, div#hideshowconcurrentuses_earlieruse .upload-drop-zone').css('height','115px');
		$('div#hideshowconcurrentuses_courtd #usaffiliation .upload-drop-zone, div#hideshowconcurrentuses_ttab #usaffiliation .upload-drop-zone, div#hideshowconcurrentuses_earlieruse #usaffiliation .upload-drop-zone').css('height','175px');
		$('div#hideshowconcurrentuses_courtd .js-upload-finished button.close, div#hideshowconcurrentuses_ttab .js-upload-finished button.close, div#hideshowconcurrentuses_earlieruse .js-upload-finished button.close' ).parent().parent().remove();
		$('div#hideshowconcurrentuses_courtd div.fileDisplayArea img, div#hideshowconcurrentuses_ttab div.fileDisplayArea img, div#hideshowconcurrentuses_earlieruse div.fileDisplayArea img' ).remove();
		$('div#hideshowconcurrentuses_courtd div.fileDisplayArea, div#hideshowconcurrentuses_ttab div.fileDisplayArea, div#hideshowconcurrentuses_earlieruse div.fileDisplayArea' ).html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .pdf, .doc</div>');
	});
	$('input#inlineRadio055').change(function() {
		if(this.checked == true){
			$('div#hideshowconcurrentuses_earlieruse').show( 'fast' );
			$('div#hideshowconcurrentuses_conflictingreg').hide( 'fast' );
			$('div#hideshowconcurrentuses_ttab').hide( 'fast' );
			$('div#hideshowconcurrentuses_courtd').hide( 'fast' );
		}
		var resetselect = [ 'Select','mm/dd/yy' ];
		var resetselect = jQuery.makeArray( resetselect );
		$('div#hideshowconcurrentuses_courtd input[type=date], div#hideshowconcurrentuses_ttab input[type=date], div#hideshowconcurrentuses_conflictingreg input[type=date]').val(resetselect[1]);
		$('div#hideshowconcurrentuses_courtd textarea, div#hideshowconcurrentuses_ttab textarea, div#hideshowconcurrentuses_conflictingreg textarea').val('');
		$('div#hideshowconcurrentuses_courtd input.checkmark, div#hideshowconcurrentuses_ttab input.checkmark, div#hideshowconcurrentuses_conflictingreg input.checkmark').prop('checked', false);
		$('div#hideshowconcurrentuses_courtd input[type=text], div#hideshowconcurrentuses_ttab input[type=text], div#hideshowconcurrentuses_conflictingreg input[type=text]').val('');
		$('div#hideshowconcurrentuses_courtd select, div#hideshowconcurrentuses_ttab select, div#hideshowconcurrentuses_conflictingreg select').val( resetselect[0] );
		$('div.appendaconcregapp_courtd div.holdsaconcregapp_courtd, div.appendaconcregapp_ttab div.holdsaconcregapp_ttab, div.appendaconcregapp_conflictingreg div.holdsaconcregapp_conflictingreg').remove();
		$('div#hideshowconcurrentuses_courtd .hidethis, div#hideshowconcurrentuses_ttab .hidethis, div#hideshowconcurrentuses_conflictingreg .hidethis').contents().css('display','block');
		$('div#hideshowconcurrentuses_courtd .js-upload-finished, div#hideshowconcurrentuses_ttab .js-upload-finished, div#hideshowconcurrentuses_conflictingreg .js-upload-finished').css('display','none');
		$('div#hideshowconcurrentuses_courtd .upload-drop-zone, div#hideshowconcurrentuses_ttab .upload-drop-zone, div#hideshowconcurrentuses_conflictingreg .upload-drop-zone').css('height','115px');
		$('div#hideshowconcurrentuses_courtd #usaffiliation .upload-drop-zone, div#hideshowconcurrentuses_ttab #usaffiliation .upload-drop-zone, div#hideshowconcurrentuses_conflictingreg #usaffiliation .upload-drop-zone').css('height','175px');
		$('div#hideshowconcurrentuses_courtd .js-upload-finished button.close, div#hideshowconcurrentuses_ttab .js-upload-finished button.close, div#hideshowconcurrentuses_conflictingreg .js-upload-finished button.close' ).parent().parent().remove();
		$('div#hideshowconcurrentuses_courtd div.fileDisplayArea img, div#hideshowconcurrentuses_ttab div.fileDisplayArea img, div#hideshowconcurrentuses_conflictingreg div.fileDisplayArea img' ).remove();
		$('div#hideshowconcurrentuses_courtd div.fileDisplayArea, div#hideshowconcurrentuses_ttab div.fileDisplayArea, div#hideshowconcurrentuses_conflictingreg div.fileDisplayArea' ).html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .pdf, .doc</div>');
	});
	//START toggle panel color
	$( '.nocontent' ).click(function() {
		$( document ).find('div').removeClass( 'highlight focus' );
		$( this ).addClass( 'highlight focus' );
	});
	//
	//START panel height match
	var a = $( 'div.match' );
	$('#panels .panel-body').css( 'height', (a.innerHeight() + 8) );
	//END
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
	//
	//START Placeholder as editable text
	$('textarea#ta2, textarea#ta5').val('This mark consists of');
	//
	//START Placeholder as editable text edit owner
	$('input#ta12').val('Jackie').css('position','relative');
	$('input#ta13').val('Babos').css('position','relative');
	$('input#ta18').val('jackie.babos@us.gt.com').css('position','relative');
	$('input#ta14').val('333 John Carlyle St ').css('position','relative');
	$('input#ta15').val('# 500').css('position','relative');
	$('input#ta16').val('').css('position','relative');
	$('input#ta19').val('Alexandria').css('position','relative');
	$('input#ta020').val('Virginia').css('position','relative');
	$('input#ta021').val('22314').css('position','relative');
	$('input#ta17').val('703-562-6675').css('position','relative');
	$('input#ta022').val('Doing Business As (DBA)').css('position','relative');
	$('input#ta023').val('Jackie Z. Babos-Smith').css('position','relative');
	$('input#ta024').val('United States').css('position','relative');
	$('input#ta025').val('United States').css('position','relative');
	$('input#ta026').val('www.us.gt.com').css('position','relative');
	$('input#ta027').val('Cell').css('position','relative');
	$('input#ta028').val('').css('position','relative');
	$('input#ta212').val('Jacob').css('position','relative');
	$('input#ta213').val('Goldstein').css('position','relative');
	$('input#ta218').val('jacob.goldstein@us.gt.com').css('position','relative');
	$('input#ta214').val('333 John Carlyle St ').css('position','relative');
	$('input#ta215').val('# 500').css('position','relative');
	$('input#ta216').val('').css('position','relative');
	$('input#ta219').val('Alexandria').css('position','relative');
	$('input#ta2020').val('Virginia').css('position','relative');
	$('input#ta2021').val('22314').css('position','relative');
	$('input#ta217').val('703-562-6675').css('position','relative');
	$('input#ta2022').val('Also Known As (AKA)').css('position','relative');
	$('input#ta2023').val('Jacob T. Goldstein').css('position','relative');
	$('input#ta2024').val('Canada').css('position','relative');
	$('input#ta2025').val('Denmark').css('position','relative');
	$('input#ta2026').val('www.us.gt.com').css('position','relative');
	$('input#ta2027').val('Home').css('position','relative');
	$('input#ta2028').val('12345').css('position','relative');
	$('input#ta12, input#ta13, input#ta14, input#ta15, input#ta16, input#ta17, input#ta18, input#ta19, input#ta020, input#ta021, input#ta212, input#ta213, input#ta214, input#ta215, input#ta216, input#ta217, input#ta218, input#ta219, input#ta2020, input#ta2021, input#ta022, input#ta023, input#ta2022, input#ta2023, input#ta024, input#ta2024, input#ta025, input#ta2025, input#ta026, input#ta2026, input#ta027, input#ta028, input#ta2027, input#ta2028').prop('disabled', true);
	//
	//START examples panel link hover / active
	$('#examples div.panel a').hover(function() {
		$(this).css('display','block');
		$( this ).parent().parent().parent().addClass( 'examplehover' );
	}, function() {
		$( this ).parent().parent().parent().removeClass( 'examplehover' );
	});
	//
	//START examples panel button toggle text
	$( 'button#moreoptionsbtn' ).click(function() {
		$( this ).toggleClass( 'focus' );
		$(this).text() === 'See More Types'
			? $(this).text('See Less Types')
			: $(this).text('See More Types');
	});
	//
	//START Initialize tooltip
	$("body").tooltip({
		selector: "[data-toggle='tooltip']",
		container: "body",
		placement : 'bottom auto',
	});
	$("[data-toggle='tooltip']").click(function (event) {
		event.preventDefault();
	});
	//START Initialize popover
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
	//
	//START toggle acceptance
	$( '#acceptreview' ).click(function() {
		$( this ).toggleClass( 'focus' );
		$(this).html() === 'Accept'
			? $(this).html('Accepted')
			: $(this).html('Accept');
	});
	//
	//START file upload inputs
	$('div.upload button[type=submit]').css('display','none');
	$(document).on('change', 'input[type="file"]', function(e) {
		var fileDisplayArea = $(this).siblings('label').children( 'div.fileDisplayArea' );
		//var fileDisplayArea = ('.fileDisplayArea');
		var file = e.target.files[0];
		var name = e.target.files[0].name;
		var type = e.target.files[0].type;
		var icon = [ '<i class="upload-icon demo-icon icon-file-word" aria-hidden="true">\&\#xf1c2\;</i>','<i class="upload-icon demo-icon icon-file-pdf" aria-hidden="true">\&\#xf1c1\;</i>','<i class="upload-icon demo-icon icon-file-powerpoint" aria-hidden="true">\&\#xf1c4\;</i>','<i class="upload-icon demo-icon icon-file-excel" aria-hidden="true">\&\#xf1c3\;</i>','<i class="upload-icon demo-icon icon-file-code" aria-hidden="true">\&\#xf1c9\;</i>' ];
		var icon = jQuery.makeArray( icon );
		if((type) == 'application\/vnd.openxmlformats\-officedocument\.wordprocessingml\.document'){
			var icon = (icon[0]);
		}
		if((type) == 'application/msword')
		{
			var icon = (icon[0]);
		}
		else if((type) == 'application\/pdf'){
			var icon = (icon[1]);
		}
		else if((type) == 'application\/vnd.openxmlformats\-officedocument\.presentationml\.presentation'){
			var icon = (icon[2]);
		}
		else if((type) == 'application\/vnd\.openxmlformats\-officedocument\.spreadsheetml\.sheet'){
			var icon = (icon[3]);
		}
		else if((type) == 'text\/csv'){
			var icon = (icon[3]);
		}
		else if((type) == 'application\/vnd\.ms\-excel'){
			var icon = (icon[3]);
		}
		else {
			var icon = ('');
		}
		var listlength = $(this).parent().next().children('js-upload-finished').children('list-group').children('div.fileholder div.row').length + 1;
		var holdata = $(this).closest('div.upload').find('div.fileholder');
		var displaydata = $(this).closest('div.upload').find('div.js-upload-finished');
		var hidethis = $(this).parent().contents();
		//var fileName = e.target.files[0].name;
		var imageType = /image.*/;
		if (file.type.match(imageType)) {
			if ((listlength) < 2)  {
				window.filetypes = $(this).closest('div.upload').find('div.fileDisplayArea').text();
				//console.log(listlength);
				var reader = new FileReader();
				reader.onload = function(e) {
					$(fileDisplayArea).addClass('loader').html("");
					// Create an image
					var img = new Image();
					// Set the img src
					img.src = reader.result;
					var imgSrc = img.src;
					// display the image on the page
					$(fileDisplayArea).removeClass('loader').append(img);
					$(displaydata).css('display','block');
					$( '.upload-drop-zone' ).css('height','115px');
					$( '.upload-drop-zone img' ).css('height','64px');
					$( '.thumbnail img' ).css('height','20px');
					$(holdata).append('<div class="row"><div class="col-xs-2"><button type="button" class="btn btn-sm close" aria-label="remove file selection"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></button></div><div class="col-xs-2 linkholder"><a href="'+ imgSrc + '" aria-labelledby="uploadedfile" download>' + name + '</a></div><div class="col-xs-5"><img src="' + imgSrc + '" class="img-thumbnail"></div><div class="col-xs-3"><span class="badge alert-success pull-right">Selected File</span></div></div>');
					if ( $( 'div#amendmark' ).length ) {
						$('div#currentmark img').attr('src', imgSrc);
					}
					//console.log(holdata);
				}
				if ((listlength) == 1)  {
					$( hidethis ).hide();
				}
				//if ((listlength) == 0)  {
				//$( '.upload-drop-zone' ).show();
				//}
				if (file) {
					reader.readAsDataURL(file);
				}
				console.log(filetypes);
			}
		} else {
			var reader = new FileReader();
			reader.onload = function() {
				if (( listlength ) < 2)  {
					$( fileDisplayArea ).addClass( 'loader' ).html("");
					// Create a file
					var dataURL = reader.result;
					$( fileDisplayArea ).removeClass( 'loader' );
					$( displaydata ).css('display','block');
					$( fileDisplayArea ).html( '<span class="glyphicon glyphicon-check"></span>' );
					$( holdata ).append('<div class="row"><div class="col-xs-2"><button type="button" class="btn btn-sm close" aria-label="remove file selection"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></button></div><div class="col-xs-1 fileholder iconholder">' + icon + '</div><div class="col-xs-4 linkholder"><a href="' + dataURL + '"aria-labelledby="uploadedfile" download>' + name + '</a></div><div class="col-xs-1 visuallyhidden">' + type + '</div><div class="col-xs-4"><span class="badge alert-success pull-right">Selected File</span></div></div>');
					if ( $( 'div#amendmark' ).length ) {
						$('div#currentmark img').css('display','none');
						$('div#currentmark').prepend('<a href="' + dataURL + '"aria-labelledby="uploadedfile" download>' + name + '</a>');
						$('div#currentmark').prepend(icon);
					}
					//console.log(icon);
					//console.log(type);
				}
				if (( listlength ) == 1)  {
					$( hidethis ).hide();
				}
//					if ((listlength) == 0)  {
//						$( '.upload-drop-zone' ).show();
//					}
			}
			if (file) {
				reader.readAsDataURL(file);
			}
		}
		$(this).closest('div.upload').find('button[type=submit]').css('display','block');
		$( 'div.upload' ).submit(
			function(e) {
				var file = ($( 'div.upload .linkholder a' ).attr('href'));
				//at this point, the variable, file, needs to be converted to a blob and appended to FormData
				$.ajax({
					url: 'upload',
					type: 'POST',
					data: new FormData( file ),
					processData: false,
					contentType: false,
					success: function(result){
						//$("#div").html(str);
						//alert('At this point, the variable, file, needs to be converted to a blob and appended to FormData -- ' + file);
						//$( this ).find( 'button[type=submit]' ).css('display','none');
						//$( this ).find( 'span.alert-success' ).html('Uploaded File');
					}
				});
				$( this ).find( 'button[type=submit]' ).css('display','none');
				$( this ).find( 'span.alert-success' ).html('Uploaded File');
				$( this ).find( 'input[type="file"]' ).val('');
				//return false;
				e.preventDefault();
			}
		);
	});
	$(document).on('click', 'div.upload button[type=submit]', function() {
		$( this ).closest( 'button[type=submit]' ).css('display','none');
		$( this ).parent().parent().parent().parent().find( 'span.alert-success' ).html('Uploaded File');
		$( this ).parent().parent().parent().parent().find( 'input[type="file"]' ).val('');
		return false;
	});
	//
	//START remove selected file
	var initialmark = $('div#currentmark img').attr('src');
	var resetmark = {};
	$(document).on('click', '.js-upload-finished button.close', function() {
		var filetypes2 = window.filetypes;
		var displaydata = $(this).closest('div.upload').find('div.js-upload-finished');
		var showinput = $(this).closest('div.upload').find('div.hidethis');
		$(this).closest('div.upload').find( 'input[type="file"]' ).val('');
		$( showinput ).contents().css('display','block');
		$( displaydata ).css('display','none');
		$(this).closest('div.upload').find( 'button[type=submit]' ).css('display','none');
		$(this).closest('div.upload').find( 'label.upload-drop-zone' ).css('height','115px');
		$(this).closest('div#usaffiliation').find( 'label.upload-drop-zone' ).css('height','2.65em').css('display','table');
		$(this).closest('div.upload').find( 'div.fileDisplayArea img' ).remove();
		$(this).closest('div.upload').find( 'div.fileDisplayArea' ).html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>' + filetypes + '</div>');
		$(this).parent().parent().remove();
		var removemarkselection = function(){
			$('div#currentmark').find('i').remove();
			$('div#currentmark').find('a').remove();
			$('div#currentmark img').attr('src',initialmark).css('display','block');
		}
		resetmark = removemarkselection;
		$(resetmark);
	});

	//
	//START prevent File Upload submit
	//$('div.upload button[type=submit]').click(function(event){
	//event.preventDefault();
	//});
	//
	//START toggle radio buttons content
	//start mark color options
	$('div#nocolorclaim').css('display','none');
	$('div#yescolorclaim').css('display','none');
	$('input#inlineRadio1').change(function() {
		if(this.checked == true){
			var clearthis = $('div#nocolorclaim');
			var clearall = {};
			$(function(){
				var clear = function(){
					$(clearthis).find('input[type=checkbox]').prop({
						checked: false,
					});
				};
				clearall = clear;
			});
			$(clearall);
			$('div#nocolorclaim').hide( 'fast' );
			$('div#yescolorclaim').show( 'fast' );
		}
		else {
			$('div#nocolorclaim').show( 'fast' );
			$('div#yescolorclaim').hide( 'fast' );
		}
	});
	$('input#inlineRadio2').change(function() {
		if(this.checked == true){
			var clearthis = $('div#yescolorclaim');
			var clearall = {};
			$(function(){
				var clear = function(){
					$(clearthis).find('textarea').val('');
				};
				clearall = clear;
			});
			$(clearall);
			$('div#nocolorclaim').show( 'fast' );
			$('div#yescolorclaim').hide( 'fast' );
		}
		else {
			$('div#nocolorclaim').hide( 'fast' );
			$('div#yescolorclaim').show( 'fast' );
		}
	});
	//end mark color options
	//start translations options
	$('div#yestranslation').css('display','none');
	$('input#inlineRadio3').change(function() {
		if(this.checked == true){
			$('div#yestranslation').show( 'fast' );
		}
		else {
			$('div#yestranslation').hide( 'fast' );
		}
	});
	$('input#inlineRadio4').change(function() {
		if(this.checked == true){
			var clearthis = $('div#yestranslation');
			$(function(){
				var clear = function(){
					$(clearthis).find('select').val('Select');
					$(clearthis).find('input').val('');
					$(clearthis).find('div.appendatranslate').contents().remove();
				};
				clearall = clear;
			});
			$(clearall);
			$('div#yestranslation').hide( 'fast' );
		}
		else {
			$('div#yestranslation').show( 'fast' );
		}
	});
	//end translations options
	//start transliterations options
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
			var clearthis = $('div#yestransliteration');
			$(function(){
				var clear = function(){
					$(clearthis).find('div.appendatransliterate').contents().remove();
					$(clearthis).find('select').val('Select');
					$(clearthis).find('input').val('');
				};
				clearall = clear;
			});
			$(clearall);
			$('div#yestransliteration').hide( 'fast' );
		}
		else {
			$('div#yestransliteration').show( 'fast' );
		}
	});
	//end translations options
	//start nps options
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
			var clearthis = $('div#yesnps');
			$(function(){
				var clear = function(){
					$(clearthis).find('div.appendaname').contents().remove();
					$(clearthis).find('div.appendaportrait').contents().remove();
					$(clearthis).find('div.appendasignature').contents().remove();
					$(clearthis).find('input[type="file"]').val('');
					$(clearthis).find('select').val('Select');
					$(clearthis).find('.hidethis').contents().css('display','block');
					$(clearthis).find('.upload-drop-zone').css('height','2.65em').css('display','table');
					$(clearthis).find('.js-upload-finished button.close').parent().parent().remove();
					$(clearthis).find('div.fileDisplayArea img').remove();
					$(clearthis).find('div.js-upload-finished').css('display','none');
					$(clearthis).find('button[type=submit]').css('display','none');
					$(clearthis).find('div.fileDisplayArea').html('<span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .doc');
					$(clearthis).find('input[type=checkbox]').prop('checked', false);
					$(clearthis).find('input[type=radio]').prop('checked', false);
					$(clearthis).find('input').val('');
					$(clearthis).find('textarea').val('');
				};
				clearall = clear;
			});
			$(clearall);
			$('div#yescontainsname').hide( 'fast' );
			$('div#yescontainsportrait').hide( 'fast' );
			$('div#yescontainssignature').hide( 'fast' );
			$('div#yesliving').hide( 'fast' );
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
	$('div#yescontainssignature').hide();
	$('#signaturechecked').change(function() {
		if(this.checked == true){
			$('div#yescontainssignature').show( 'fast' );
		}
		else {
			$('div#yescontainssignature').hide( 'fast' );
		}
	});
	//yes, consent required is checked
	$('div#yesliving').hide();
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
	//
	//START disclaimer options
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
			var clearthis = $('div#yesdisclaimer');
			$(function(){
				var clear = function(){
					$(clearthis).find('input').val('');
					$(clearthis).find('div.appenddisclaim').contents().remove();
				};
				clearall = clear;
			});
			$(clearall);
			$('div#yesdisclaimer').hide( 'fast' );
		}
		else {
			$('div#yesdisclaimer').show( 'fast' );
		}
	});
	//
	//START uncommoninfo options
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
			var clearthis = $('div#yesnotpreviously');
			$(function(){
				var clear = function(){
					$(clearthis).find('div.appendaname').contents().remove();
					$(clearthis).find('div.appendaportrait').contents().remove();
					$(clearthis).find('div.appendasignature').contents().remove();
					$(clearthis).find('input[type="file"]').val('');
					$(clearthis).find('select').val('Select');
					$(clearthis).find('.hidethis').contents().css('display','block');
					$(clearthis).find('.upload-drop-zone').css('height','2.65em').css('display','table');
					$(clearthis).find('.js-upload-finished button.close').parent().parent().remove();
					$(clearthis).find('div.fileDisplayArea img').remove();
					$(clearthis).find('div.js-upload-finished').css('display','none');
					$(clearthis).find('button[type=submit]').css('display','none');
					$(clearthis).find('div.fileDisplayArea').html('<span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .pdf, .doc');
					$(clearthis).find('input[type=checkbox]').prop('checked', false);
					$(clearthis).find('input[type=radio]').prop('checked', false);
					$(clearthis).find('input').val('');
					$(clearthis).find('textarea').val('');
				};
				clearall = clear;
			});
			$(clearall);
			$('div#yesnotpreviously').hide( 'fast' );
		}
		else {
			$('div#yesnotpreviously').show( 'fast' );
		}
	});
	//
	//START prior options
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
			var clearthis = $('div#yesprior');
			$(function(){
				var clear = function(){
					$(clearthis).find('input').val('');
					$(clearthis).find('div.appenddocket').contents().remove();
				};
				clearall = clear;
			});
			$(clearall);
			$('div#yesprior').hide( 'fast' );
		}
		else {
			$('div#yesprior').show( 'fast' );
		}
	});
	//
	//START prior options
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
			var clearthis = $('div#yesmeaning');
			$(function(){
				var clear = function(){
					$(clearthis).find('input').val('');
				};
				clearall = clear;
			});
			$(clearall);
			$('div#yesmeaning').hide( 'fast' );
		}
		else {
			$('div#yesmeaning').show( 'fast' );
		}
	});
	//
	//START attorney options
	$('div#yesattorneyfiling').css('display','none');
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
	//
	//START us foreign options
	//us entity show
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
	//foreign entity show
	$('div#yesforeignentity').css('display','none');
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
	//
	//START basis commerce options
	$('div.yescommerce').css('display','none');
	$('input#inlineRadio25, input.countyes').on('change',function(){
		if(this.checked == true){
			$(this).closest('section').find('div.yescommerce').show( 'fast' );
		}
		else {
			$(this).closest('section').find('div.yescommerce').hide( 'fast' );
		}
	});
	$('input#inlineRadio26, input.count').change(function() {
		if(this.checked == true){
			$(this).closest('section').find('div.yescommerce').hide( 'fast' );
			var resetselect = [ 'Select','mm/dd/yy' ];
			var resetselect = jQuery.makeArray( resetselect );
			$(this).closest('section').find('div.yescommerce input[type=date]').val(resetselect[1]);
			$(this).closest('section').find('div.yescommerce textarea').val('');
			$(this).closest('section').find('div.yescommerce input.checkmark').prop('checked', false);
			$(this).closest('section').find('div.yescommerce input[type=text]').val('');
			$(this).closest('section').find('div.yescommerce select').val( resetselect[0] );
			$(this).closest('section').find('div.yescommerce .hidethis').contents().css('display','block');
			$(this).closest('section').find('div.yescommerce .upload-drop-zone').css('height','115px');
			$(this).closest('section').find('div.yescommerce .js-upload-finished button.close' ).parent().parent().remove();
			$(this).closest('section').find('div.yescommerce div.fileDisplayArea img' ).remove();
			$(this).closest('section').find('div.yescommerce div.holdsaforeign div.fileDisplayArea' ).html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .tiff, .png</div>');
		}
		else {
			$(this).closest('section').find('div.yescommerce').show( 'fast' );
		}
	});
	//
	//START generate unique IDs + matching labels for Basis  + SOU pages, checkmarks
	var checkboxList = $('#basisab input.showspecimen, #basisabde input.showspecimen');
	for (var i = 0; i <= checkboxList.length; i++) {
		$(checkboxList[i]).attr('id', 'individualspecimencheck_' + i);
	}
	var labelList = $('#basisab input.showspecimen, #basisabde input.showspecimen').next('label');
	for (var i = 0; i <= labelList.length; i++) {
		$(labelList[i]).attr('for', 'individualspecimencheck_' + i);
	}
	var labelidList = $('#basisab input.showspecimen, #basisabde input.showspecimen').next('label');
	for (var i = 0; i <= labelidList.length; i++) {
		$(labelidList[i]).attr('id', 'individualspecimen_' + i);
	}
	var checkboxarialabelList = $('#basisab input.showspecimen, #basisabde input.showspecimen');
	for (var i = 0; i <= checkboxarialabelList.length; i++) {
		$(checkboxarialabelList[i]).attr('aria-labelledby', 'individualspecimen_' + i);
	}
	//
	//START generate unique IDs + matching labels for Basis, Amend Mark, section8 + SOU pages, uploaded files
	var fileList = $('#basisab input[type=file], #basisabde input[type=file], #detected input[type=file], #amendmark input[type=file], #soucontent input[type=file], #sectioneight input[type=file]');
	for (var i = 0; i <= fileList.length; i++) {
		$(fileList[i]).attr('id', 'uploadedfile' + i);
	}
	var nameList = $('#basisab input[type=file], #basisabde input[type=file], #detected input[type=file], #amendmark input[type=file], #soucontent input[type=file], #sectioneight input[type=file]');
	for (var i = 0; i <= nameList.length; i++) {
		$(nameList[i]).attr('name', 'uploadedspecimen' + i + 'file');
	}
	var labelList = $('#basisab input[type=file], #basisabde input[type=file], #detected input[type=file], #amendmark input[type=file], #soucontent input[type=file], #sectioneight input[type=file]').prev('label');
	for (var i = 0; i <= labelList.length; i++) {
		$(labelList[i]).attr('for', 'uploadedfile' + i);
	}
	var labelidList = $('#basisab input[type=file], #basisabde input[type=file], #detected input[type=file], #amendmark input[type=file], #soucontent input[type=file], #sectioneight input[type=file]').prev('label');
	for (var i = 0; i <= labelidList.length; i++) {
		$(labelidList[i]).attr('id', 'specimen' + i);
	}
	var filesarialabelList = $('#basisab input[type=file], #basisabde input[type=file], #detected input[type=file], #amendmark input[type=file], #soucontent input[type=file], #sectioneight input[type=file]');
	for (var i = 0; i <= filesarialabelList.length; i++) {
		$(filesarialabelList[i]).attr('aria-labelledby', 'specimen' + i);
	}
	//
	//START basistwo connection options
	$('div.yesconnection').css('display','none');
	$('input#inlineRadio29, input.countyes').on('change',function(){
		if(this.checked == true){
			$(this).closest('section').find('div.yesconnection').show( 'fast' );
		}
		else {
			$(this).closest('section').find('div.yesconnection').hide( 'fast' );
		}
	});
	$('input#inlineRadio30, input.count').change(function() {
		if(this.checked == true){
			$(this).closest('section').find('div.yesconnection').hide( 'fast' );
			var resetselect = [ 'Select','mm/dd/yy' ];
			var resetselect = jQuery.makeArray( resetselect );
			$(this).closest('section').find('div.yesconnection input[type=date]').val(resetselect[1]);
			$(this).closest('section').find('div.yesconnection textarea').val('');
			$(this).closest('section').find('div.yesconnection input.checkmark').prop('checked', false);
			$(this).closest('section').find('div.yesconnection input[type=text]').val('');
			$(this).closest('section').find('div.yesconnection select').val( resetselect[0] );
			$(this).closest('section').find('div.yesconnection .hidethis').contents().css('display','block');
			$(this).closest('section').find('div.yesconnection .upload-drop-zone').css('height','115px').css('display','block');
			$(this).closest('section').find('div.yesconnection .js-upload-finished button.close' ).parent().parent().remove();
			$(this).closest('section').find('div.yesconnection div.fileDisplayArea img' ).remove();
			$(this).closest('section').find('div.yesconnection div.holdsaforeign div.fileDisplayArea' ).html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .tiff, .png</div>');
		}
		else {
			$(this).closest('section').find('div.yesconnection').show( 'fast' );
		}
	});
	//
	//START provide specimen options
	$('div.yesspecimen').css('display','none');
	$('input.showspecimen').change(function() {
		if(this.checked == true){
			$(this).closest('section').find('div.yesspecimen').show( 'fast' );
			$(this).closest('section').find('textarea').css('display','block');
			$(this).closest('section').find('textarea').siblings('label').css('display','block');
			$(this).closest('section').find('div.upload').css('display','block');
			$(this).closest('section').find( '.upload-drop-zone' ).css('height','115px').css('display','block');
			$(this).closest('section').find( '#usaffiliation .upload-drop-zone' ).css('height','175px').css('display','block');
			$(this).closest('section').find( 'div.fileDisplayArea' ).html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .tiff, .png</div>');
		}
		else {
			$(this).closest('section').find('div.yesspecimen').hide( 'fast' );
			$(this).closest('section').find('.hidethis').contents().css('display','block');
			$(this).closest('section').find('.upload-drop-zone').css('height','115px');
			$(this).closest('section').find('.js-upload-finished button.close' ).parent().parent().remove();
			$(this).closest('section').find('div.fileDisplayArea img' ).remove();
			$(this).closest('section').find('div.holdsaforeign div.fileDisplayArea' ).html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .tiff, .png</div>');
		}
	});
	//end provide specimen options
	//
	//START affiliation options
	$('div#usaffiliation').css('display','none');
	$('div#canadianaffiliation').css('display','none');
	$('#attorney-bar-standing').change(function(){
		var resetselect = [ 'mm/dd/yy' ];
		var resetselect = jQuery.makeArray( resetselect );
		var clearall = {};
		if($(this).val() == 'canadianaffiliation'){ // or this.value == 'canadianaffiliation'
			var clearthis = $('div#usaffiliation');
			$(function(){
				var clear = function(){
					$(clearthis).find('input[type="file"]').val('');
					$(clearthis).find('select').val('Select');
					$(clearthis).find('select[type=date]').val( resetselect[0] );
					$(clearthis).find('.hidethis').contents().css('display','block');
					$(clearthis).find('.upload-drop-zone').css('height','2.65em').css('display','table');
					$(clearthis).find('.js-upload-finished button.close').parent().parent().remove();
					$(clearthis).find('div.fileDisplayArea img').remove();
					$(clearthis).find('div.js-upload-finished').css('display','none');
					$(clearthis).find('button[type=submit]').css('display','none');
					$(clearthis).find('div.fileDisplayArea').html('<span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .pdf, .doc');
					$(clearthis).find('input[type=checkbox]').prop('checked', false);
					$(clearthis).find('input[type=radio]').prop('checked', false);
					$(clearthis).find('input').val('');
					$(clearthis).find('textarea').val('');
				};
				clearall = clear;
			});
			$(clearall);
		}
		else if ($(this).val() == 'usaffiliation') {
			var clearthis = $('div#canadianaffiliation');
			$(function(){
				var clear = function(){
					$(clearthis).find('select').val('Select');
					$(clearthis).find('input[type=checkbox]').prop('checked', false);
					$(clearthis).find('input[type=radio]').prop('checked', false);
					$(clearthis).find('input').val('');
				};
				clearall = clear;
			});
			$(clearall);
		}
		$('.hidethisdiv').hide( 'fast' );
		$('#' + $(this).val()).show( 'fast' );
	});
	//
	//START nameoftypeofbusiness options
	$( 'div#nametype' ).css('display','none');
	$( '#type' ).on('change',function(){
		$( 'div#nametype' ).show( 'fast' );
	});
	//
	//START entity options (import concept)
	//us form
	$('#owner #autofill').css('display','none');
	$('#entype').on('change',function(){
		$('#container').empty();//maybe use detach
		$('footer').css('display','none');
		var include = ('js/' + $(this).val() + '.js');
		$.getScript( include );
		$('#autofill').show( 'fast' );
		$('footer').css('display','block');
		//var includevalidate = ('js/validate_ownerindividual.js');
//		$.getScript( includevalidate );
//		$.getScript( includevalidate, function() {
//			  console.log('script loaded');
//			});
	});
	//foreign form
	$('#owner #autofillforeign').css('display','none');
	$('#entypeforeignbahamas').on('change',function(){
		$('#containerforeign').empty();//maybe use detach
		$('footer').css('display','none');
		var includeforeign = ('js/' + $(this).val() + $( '#entitycountry' ).val() + '.js');
		$.getScript( includeforeign );
		$('#owner #autofillforeign').show( 'fast' );
		$('#owner #autofillforeign').parent().attr('role','alert');
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
	//START select signature method
	$('#declarationsignature .hidethis, #petitions1 .hidethis, #petitions2 .hidethis').css('display','none');
	$('select.signmethod').on('change',function(){
		var loadsign = ($(this).val());
		$(this).closest('fieldset').find('.hidethis').show('fast');
		$( loadsign ).css('display','block');
		if( loadsign == 'Select') {
			$(this).closest('fieldset').find('.hidethis').hide('fast');
			$(this).closest('fieldset').find('textarea.datesigned').val('');
			$(this).closest('fieldset').find('.hidethis input').val('');
		}
	});
	$('select.countinputs').on('change',function(){
		var count = $('div.indentctr input[type=radio]:checked').length;
		var boxes = $('div.indentctr');
		var b = $('#alertmin');
		var c = $('#mintext');
		var d = $('#alertbtn');
		$(this).closest('section').prev('section').find('div.indentctr input[type=radio]:checked');
		if( count < 1) {
			$('html, body').animate({
				scrollTop: boxes.offset().top - 300
			}, 500);
			$(b).css('visibility','visible');
			$(b).css('height','auto');
			$(b).addClass('form-group').addClass('form-group-md');
			$(b).css('float','left');
			$(b).css('top','.25em');
			$(b).css('padding','1em');
			$(b).css('marginBottom','1em');
			$(c).css('display','block');
			$(d).css('display','block');
			$(d).toggleClass( 'focus' );
			$(c).html('You must click one of the three buttons, below, to confirm that you are authorized pursuant to the rules governing representation of others before the USPTO to sign this form.');
			$(this).val('Select');
			$(this).closest('fieldset').find('.hidethis').css('display','none');
			$(this).closest('fieldset').find('textarea.datesigned').val('');
		}
	});
	//
	//START select security questions
	$('#createaccount .hidethis').css('display','none');
	$('select.questions').on('change',function(){
		var loadsign = ($(this).val());
		$(this).closest('fieldset').find('.hidethis').show('fast');
		$( loadsign ).css('display','block');
		if( loadsign == 'Select') {
			$(this).closest('fieldset').find('.hidethis').hide('fast');
			$(this).closest('fieldset').find('input.answer').val('');
			$(this).closest('fieldset').find('.hidethis input').val('');
		}
	});
	//
	//START select all declarations
	$(document).on('change','#declarationsignature input#select', function() {
		var checkboxes  = $('#declarationsignature input#select').parent('div').siblings('div').children('input.checkmark');
		var selectall = $(this);
		if ($(this).is( ":checked" )) {
			$.each(checkboxes, function(){
				$(this).prop('checked', true);
			});
		} else {
			$.each(checkboxes, function(){
				$(this).prop('checked', false);
			});
		}
		$(checkboxes).on('click', function(e){
			$(selectall).prop('checked', false);
		});
	});
	//
	//START select all incontestable
	$(document).on('change','#incontestable input#select', function() {
		var checkboxes  = $('#incontestable input#select').parent().parent().parent().children('fieldset').find('input.checkmark');
		var selectall = $(this);
		if ($(this).is( ":checked" )) {
			$.each(checkboxes, function(){
				$(this).prop('checked', true);
			});
		} else {
			$.each(checkboxes, function(){
				$(this).prop('checked', false);
			});
		}
		$(checkboxes).on('click', function(e){
			$(selectall).prop('checked', false);
		});
	});
	//
	//START fill from contacts values -- attorney
	function clearform() {
		$( 'input#attorney-first-name' ).val( '' );
		$( 'input#title' ).val( '' );
		$( 'input#attorney-last-name' ).val( '' );
		$( '#suffix' ).val( '');
		$( 'input#attorney-lawfirm-name' ).val( '' );
		$( '#attorney-country' ).val( '' );
		$( '#attorney-country' ).val( );
		$( 'input#attorney-address1' ).val( '' );
		$( 'input#attorney-city' ).val( '' );
		$( '#attorney-state' ).val('' );
		$( 'input#attorney-zipcode' ).val( '' );
		$( 'input#attorney-email' ).val( '' );
		$( 'input#attorney-phonenumbertype' ).val( '');
		$( 'input#attorney-phone' ).val( '' );
		$( '#mydata2' ).find('span.glyphicon-ok-sign').removeClass( 'glyphicon-ok-sign' ).parent().parent().css('background-color','#9BB8D3').siblings().css('background-color','#9BB8D3');
	}
	$( "a#clearform" ).click(clearform);
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
	//
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
	//
	//START contacts, fees, my mark components
	//start toggle glyphicon contacts widget
	function togglecontacts() {
		$( '#mydata2' ).find('span.glyphicon-ok-sign').parent().parent().css('background-color','#9BB8D3').css('color','#fff').siblings().css('background-color','#9BB8D3').css('color','#fff');
		$( this ).find('span.glyphicon-plus-sign').toggleClass( 'glyphicon-ok-sign' ).attr('aria-label','select this contact');
		$( this ).find('span.glyphicon-ok-sign').parent().parent().css('background-color','#D4EB8E').css('color','#333').siblings().css('background-color','#D4EB8E').css('color','#333');
		$( this ).find('span.glyphicon-ok-sign').parent().parent().parent().siblings().children().children().children('.glyphicon-ok-sign').removeClass( 'glyphicon-ok-sign' );
		$( this ).find('span.glyphicon-ok-sign').attr('aria-label','deselect this contact');
		$( '#mydata2 .collapse' ).collapse('hide').fadeOut( 'slow','swing');
	}
	$('a.fromcontact').click(togglecontacts);
	//
	//START close (x) fees
	$('button#closefee').click(function() {
		$( '#mydata' ).css('visibility','hidden');
		$( '#mydata .collapse' ).collapse('hide').fadeOut( 'slow','swing');
	});
	//
	//START show fees from nav
	$('a#showfees').click(function() {
		$( '#mydata' ).css('visibility','visible');
		$( '#mydata .collapse' ).collapse('show').fadeIn( 'slow','swing');
	});
	//
	//START show fees from widget
	$("button#feebtn").click(function() {
		$( '#mydata' ).css('visibility','visible');
		$( '#mydata .collapse' ).collapse('show').fadeIn( 'slow','swing');
	});
	//
	//START close (x) managed contacts
	$('button#closecontacts').click(function() {
		$( '#mydata2 .collapse' ).collapse('hide').fadeOut( 'slow','swing');
		$( '#mydata2' ).css('visibility','hidden');
	});
	//
	//START show managed contacts from widget
	$('button#contactsbtn').click(function() {
		$( '#mydata2' ).css('visibility','visible');
		$( '#mydata2 .collapse' ).collapse('show').fadeIn( 'slow','swing');
	});
	//
	//START show managed contacts from nav
	$('a#showcontacts').click(function() {
		$( '#mydata2' ).css('visibility','visible');
		$( '#mydata2 .collapse' ).collapse('show').fadeIn( 'slow','swing');
	});
	//START populate form from managed contacts
	$('button#autofill, button#autofillforeign').on('click',function() {
		$( '#mydata2' ).css('visibility','visible');
		$( '#mydata2 .collapse' ).collapse('show').fadeIn( 'slow','swing');
	});
	//$( '#mydata2' ).find('.panel-heading button span').toggleClass('visuallyremoved');
	$('#mydata2 div.panel-collapse').on('hide.bs.collapse', function () {
		$('#mydata2').parent().find('.panel-heading button span').toggleClass('visuallyremoved');
	});
	$('#mydata2 div.panel-collapse').on('show.bs.collapse', function () {
		$('#mydata2').parent().find('.panel-heading button span').toggleClass('visuallyremoved');
	});
	//
	//START search managed contacts
	$("#contactsearch").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#contactssearch tr").filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	//
	//START edit owner accordion button toggle rev
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
	//
	//START additional phone
	$( document ).on('click','button#addphone2',function(){
		var resettype = [ 'Type' ];
		var resettype = jQuery.makeArray( resettype );
		$( 'div.phones:eq(0)' ).clone().appendTo( '.appendphones' );
		$( 'div.phones' ).last().find('select').val(resettype[0]);
		$( 'div.phones' ).last().find('input').val('');
		$( '.appendphones .resetphone2' ).removeClass( 'visuallyremoved' );
		$( this ).removeClass( '.addphoneinitial' );
	});
	$( document ).on('click','.resetphonebtn',function(){
		$( this ).parent().parent().parent().remove();
	});
	//
	//START concurrent use, 2(f) Claim
	$( document ).on('click','.resetregbtn',function(){
		$( this ).parent().parent().remove();
	});
	//
	//START additional registration 2(f)
	$( document ).on('click','button#addreg2f',function(){
		$( 'div.reg:eq(0)' ).clone().appendTo( '#yespriors .appendreg' );
		$( 'div.reg' ).last().find('input').val('');
		$( 'div.reg' ).last().find('textarea').val('');
		$( '#yespriors .appendreg .resetregbtn' ).removeClass( 'visuallyremoved' );
		$( this ).removeClass( '.addinitial' );
	});
	//
	//START additional registration no courtd
	$( document ).on('click','button#addreg',function(){
		$( 'div.holdsaconcregapp_courtd:eq(0)' ).clone().appendTo( '.appendaconcregapp_courtd' );
		$( 'div.holdsaconcregapp_courtd' ).last().find('input').val('');
		$( 'div.holdsaconcregapp_courtd' ).last().find('textarea').val('');
		$( 'div.holdsaconcregapp_courtd').last().find('input.checkmark').prop('checked', false);
		$( '.appendaconcregapp_courtd .resetreg' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em');
		$( this ).removeClass( '.addinitial' );
		//generate unique IDs + matching labels for Additional Information page, concurrent use, courtd checkmarks
		var checkboxList_courtd = $('.appendaconcregapp_courtd input.checkmark');
		for (var i = 0; i <= checkboxList_courtd.length; i++) {
			$(checkboxList_courtd[i]).attr('id', 'noregapp_' + i);
			console.log(checkboxList_courtd);
		}
		var labelList_courtd = $('.appendaconcregapp_courtd input.checkmark').next('label');
		for (var i = 0; i <= labelList_courtd.length; i++) {
			$(labelList_courtd[i]).attr('for', 'noregapp_' + i);
		}
		var labelidList_courtd = $('.appendaconcregapp_courtd input.checkmark').next('label');
		for (var i = 0; i <= labelidList_courtd.length; i++) {
			$(labelidList_courtd[i]).attr('id', 'noregapplabel_' + i);
		}
		var checkboxarialabelList_courtd = $('.appendaconcregapp_courtd input.checkmark');
		for (var i = 0; i <= checkboxarialabelList_courtd.length; i++) {
			$(checkboxarialabelList_courtd[i]).attr('aria-labelledby', 'noregapplabel_' + i);
		}
	});
	//
	//START additional registration no ttab
	$( document ).on('click','button#addttab',function(){
		$( 'div.holdsaconcregapp_ttab:eq(0)' ).clone().appendTo( '.appendaconcregapp_ttab' );
		$( 'div.holdsaconcregapp_ttab' ).last().find('input').val('');
		$( 'div.holdsaconcregapp_ttab' ).last().find('textarea').val('');
		$( 'div.holdsaconcregapp_ttab').last().find('input.checkmark').prop('checked', false);
		$( '.appendaconcregapp_ttab .resetreg' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em');
		$( this ).removeClass( '.addinitial' );
		//generate unique IDs + matching labels for Additional Information page, concurrent use, ttab checkmarks
		var checkboxList_ttab = $('.appendaconcregapp_ttab input.checkmark');
		for (var i = 0; i <= checkboxList_ttab.length; i++) {
			$(checkboxList_ttab[i]).attr('id', 'noregapp_' + i);
			console.log(checkboxList_ttab);
		}
		var labelList_ttab = $('.appendaconcregapp_ttab input.checkmark').next('label');
		for (var i = 0; i <= labelList_ttab.length; i++) {
			$(labelList_ttab[i]).attr('for', 'noregapp_' + i);
		}
		var labelidList_ttab = $('.appendaconcregapp_ttab input.checkmark').next('label');
		for (var i = 0; i <= labelidList_ttab.length; i++) {
			$(labelidList_ttab[i]).attr('id', 'noregapplabel_' + i);
		}
		var checkboxarialabelList_ttab = $('.appendaconcregapp_ttab input.checkmark');
		for (var i = 0; i <= checkboxarialabelList_ttab.length; i++) {
			$(checkboxarialabelList_ttab[i]).attr('aria-labelledby', 'noregapplabel_' + i);
		}
	});
	//
	//START additional registration no conflicting
	$( document ).on('click','button#addconflicting',function(){
		$( 'div.holdsaconcregapp_conflictingreg:eq(0)' ).clone().appendTo( '.appendaconcregapp_conflictingreg' );
		$( 'div.holdsaconcregapp_conflictingreg' ).last().find('input').val('');
		$( 'div.holdsaconcregapp_conflictingreg' ).last().find('textarea').val('');
		$( 'div.holdsaconcregapp_conflictingreg').last().find('input.checkmark').prop('checked', false);
		$( '.appendaconcregapp_conflictingreg .resetreg' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em');
		$( this ).removeClass( '.addinitial' );
		//generate unique IDs + matching labels for Additional Information page, concurrent use, conflicting reg checkmarks
		var checkboxList_conflictingreg = $('.appendaconcregapp_conflictingreg input.checkmark');
		for (var i = 0; i <= checkboxList_conflictingreg.length; i++) {
			$(checkboxList_conflictingreg[i]).attr('id', 'noregapp_' + i);
		}
		var labelList_conflictingreg = $('.appendaconcregapp_conflictingreg input.checkmark').next('label');
		for (var i = 0; i <= labelList_conflictingreg.length; i++) {
			$(labelList_conflictingreg[i]).attr('for', 'noregapp_' + i);
		}
		var labelidList_conflictingreg = $('.appendaconcregapp_conflictingreg input.checkmark').next('label');
		for (var i = 0; i <= labelidList_conflictingreg.length; i++) {
			$(labelidList_conflictingreg[i]).attr('id', 'noregapplabel_' + i);
		}
		var checkboxarialabelList_conflictingreg = $('.appendaconcregapp_conflictingreg input.checkmark');
		for (var i = 0; i <= checkboxarialabelList_conflictingreg.length; i++) {
			$(checkboxarialabelList_conflictingreg[i]).attr('aria-labelledby', 'noregapplabel_' + i);
		}
	});
	//
	//START additional registration no earlieruse
	$( document ).on('click','button#addearlieruse',function(){
		$( 'div.holdsaconcregapp_earlieruse:eq(0)' ).clone().appendTo( '.appendaconcregapp_earlieruse' );
		$( 'div.holdsaconcregapp_earlieruse' ).last().find('input').val('');
		$( 'div.holdsaconcregapp_earlieruse' ).last().find('textarea').val('');
		$( 'div.holdsaconcregapp_earlieruse').last().find('input.checkmark').prop('checked', false);
		$( '.appendaconcregapp_earlieruse .resetreg' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em');
		$( this ).removeClass( '.addinitial' );
		//generate unique IDs + matching labels for Additional Information page, concurrent use, earlier use checkmarks
		var checkboxList_earlieruse = $('.appendaconcregapp_earlieruse input.checkmark');
		for (var i = 0; i <= checkboxList_earlieruse.length; i++) {
			$(checkboxList_earlieruse[i]).attr('id', 'noregapp_' + i);
		}
		var labelList_earlieruse = $('.appendaconcregapp_earlieruse input.checkmark').next('label');
		for (var i = 0; i <= labelList_earlieruse.length; i++) {
			$(labelList_earlieruse[i]).attr('for', 'noregapp_' + i);
		}
		var labelidList_earlieruse = $('.appendaconcregapp_earlieruse input.checkmark').next('label');
		for (var i = 0; i <= labelidList_earlieruse.length; i++) {
			$(labelidList_earlieruse[i]).attr('id', 'noregapplabel_' + i);
		}
		var checkboxarialabelList_earlieruse = $('.appendaconcregapp_earlieruse input.checkmark');
		for (var i = 0; i <= checkboxarialabelList_earlieruse.length; i++) {
			$(checkboxarialabelList_earlieruse[i]).attr('aria-labelledby', 'noregapplabel_' + i);
		}
	});
	//end additional registration no earlieruse
	//
	//START additional docket
	$( document ).on('click','button#addocketref',function(){
		$( 'div.docket:eq(0)' ).clone().appendTo( '.appenddocket' );
		$( 'div.docket' ).last().find('input').val('');
		$( '.appenddocket .resetdkt' ).removeClass( 'visuallyremoved' );
		$( this ).removeClass( '.addinitial' );
	});
	$( document ).on('click','.resetdktrefbtn',function(){
		$( this ).parent().parent().remove();
	});
	//
	//START additional name
	$( document ).on('click','button#addaname',function(){
		$( 'div.addname:eq(0)' ).clone().appendTo( '.appendaname' );
		$( 'div.addname' ).last().find('input').val('');
		$( '.appendaname .resetname' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em');
		$( this ).removeClass( '.addinitial' );
	});
	$( document ).on('click','.resetnamebtn',function(){
		$( this ).parent().parent().remove();
	});
	//END additional name

	//START additional portrait
	$( document ).on('click','button#addaportrait',function(){
		$( 'div.addportrait:eq(0)' ).clone().appendTo( '.appendaportrait' );
		$( 'div.addportrait' ).last().find('input').val('');
		$( '.appendaportrait .resetportrait' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em');
		$( this ).removeClass( '.addinitial' );
	});
	$( document ).on('click','.resetportraitbtn',function(){
		$( this ).parent().parent().remove();
	});
	//
	//START additional signature
	$( document ).on('click','button#addasignature',function(){
		$( 'div.addsignature:eq(0)' ).clone().appendTo( '.appendasignature' );
		$( 'div.addsignature' ).last().find('input').val('');
		$( '.appendasignature .resetsignature' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em');
		$( this ).removeClass( '.addinitial' );
	});
	$( document ).on('click','.resetsignaturebtn',function(){
		$( this ).parent().parent().remove();
	});
	//
	//START additional translation
	$( document ).on('click','button#addatranslation',function(){
		$( 'div.addtranslation:eq(0)' ).clone().appendTo( '.appendatranslate' );
		$( 'div.addtranslation' ).last().find('input').val('');
		$( '.appendatranslate .resettranslation' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em');
		$( this ).removeClass( '.addinitial' );
	});
	$( document ).on('click','.resettranslationbtn',function(){
		$( this ).parent().parent().remove();
	});
	//
	//START additional transliteration
	$( document ).on('click','button#addatransliteration',function(){
		$( 'div.addtransliteration:eq(0)' ).clone().appendTo( '.appendatransliterate' );
		$( 'div.addtransliteration' ).last().find('input').val('');
		$( '.appendatransliterate .resettransliteration' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em');
		$( this ).removeClass( '.addinitial' );
	});
	$( document ).on('click','.resettransliterationbtn',function(){
		$( this ).parent().parent().remove();
	});
	//
	//START additional transliteration
	$( document ).on('click','button#addisclaimer',function(){
		$( 'div.disclaim:eq(0)' ).clone().appendTo( '.appenddisclaim' );
		$( 'div.disclaim' ).last().find('input').val('');
		$( '.appenddisclaim .resetdisclaim' ).removeClass( 'visuallyremoved' );
		$( this ).removeClass( '.addinitial' );
	});
	$( document ).on('click','.resetdisclaimbtn',function(){
		$( this ).parent().parent().remove();
	});
	//
	//START pending + foreign
	//Basis one
	$('div.yescommerce div.holdsapending').css('display','none');
	$('div.yescommerce button#addpending_basisone').css('display','none');
	$('div.yescommerce input#pendingtwo_basisone').change(function() {
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
	$('div.yescommerce div.holdsaforeign').css('display','none');
	$('button#addforeignreg_basisone').css('display','none');
	$('div.yescommerce input#foreigntwo_basisone').change(function() {
		if(this.checked == true){
			$(this).parent().parent().parent().find('div.holdsaforeign').show( 'fast' );
			$('button#addforeignreg_basisone').show( 'fast' );
			$('button#addforeignreg_basisone').parent().attr( 'role','alert' );
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
	$('div.yesconnection div.holdsapending').css('display','none');
	$('button#addpending_basistwo').css('display','none');
	$('div.yesconnection input#pendingtwo_basistwo').change(function() {
		if(this.checked == true){
			$(this).parent().parent().parent().find('div.holdsapending').show( 'fast' );
			$('button#addpending_basistwo').show( 'fast' );
			$('button#addpending_basistwo').parent().attr( 'role','alert' );
			$('input#natwo_basistwo').prop({
				checked: false,
			});
		}
		else {
			$(this).parent().parent().parent().find('div.holdsapending').hide( 'fast' );
			$('button#addpending_basistwo').hide( 'fast' );
		}
	});
	$('div.yesconnection div.holdsaforeign').css('display','none');
	$('button#addforeignreg_basistwo').css('display','none');
	$('div.yesconnection input#foreigntwo_basistwo').change(function() {
		if(this.checked == true){
			$(this).parent().parent().parent().find('div.holdsaforeign').show( 'fast' );
			$('button#addforeignreg_basistwo').show( 'fast' );
			$('button#addforeignreg_basistwo').parent().attr( 'role','alert' );
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
			$('div.yescommerce div.holdsaforeign').hide( 'fast' );
			$('div.yescommerce div.holdsapending').hide( 'fast' );
			$('button#addforeignreg_basisone').hide( 'fast' );
			$('button#addpending_basisone').hide( 'fast' );
			$('div.yescommerce input#pendingtwo_basisone').prop({
				checked: false,
			});
			$('div.yescommerce input#foreigntwo_basisone').prop({
				checked: false,
			});
			var resetselect = [ 'Select','mm/dd/yy' ];
			var resetselect = jQuery.makeArray( resetselect );
			$('div.yescommerce div.holdsaforeign input[type=date], div.yescommerce div.holdsapending input[type=date]').val(resetselect[1]);
			$('div.yescommerce div.holdsaforeign textarea, div.yescommerce div.holdsapending textarea').val('');
			$('div.yescommerce div.holdsaforeign input.checkmark, div.yescommerce div.holdsapending input.checkmark').prop('checked', false);
			$('div.yescommerce div.holdsaforeign input[type=text], div.yescommerce div.holdsapending input[type=text]').val('');
			$('div.yescommerce div.holdsaforeign select, div.yescommerce div.holdsapending select').val( resetselect[0] );
			$('div.yescommerce div.holdsaforeign .hidethis, div.yescommerce div.holdsapending .hidethis').contents().css('display','block');
			$('div.yescommerce div.holdsaforeign .upload-drop-zone, div.yescommerce div.holdsapending .upload-drop-zone').css('height','115px');
			$('div.yescommerce div.holdsaforeign .js-upload-finished button.close, div.yescommerce div.holdsapending .js-upload-finished button.close' ).parent().parent().remove();
			$('div.yescommerce div.holdsaforeign div.fileDisplayArea img, div.yescommerce div.holdsapending div.fileDisplayArea img' ).remove();
			$('div.yescommerce div.holdsaforeign div.fileDisplayArea, div.yescommerce div.holdsapending div.fileDisplayArea' ).html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .pdf, .doc</div>');
			$('div.yescommerce div.appendaforeign div.holdsaforeign, div.yescommerce div.appendaforeign div.holdsapending').remove();
		}
	});
	$('input#natwo_basistwo').change(function() {
		if(this.checked == true){
			$('div.yesconnection div.holdsaforeign').hide( 'fast' );
			$('div.yesconnection div.holdsapending').hide( 'fast' );
			$('button#addforeignreg_basistwo').hide( 'fast' );
			$('button#addpending_basistwo').hide( 'fast' );
			$('div.yesconnection input#pendingtwo_basistwo').prop({
				checked: false,
			});
			$('div.yesconnection input#foreigntwo_basistwo').prop({
				checked: false,
			});
			var resetselect = [ 'Select','mm/dd/yy' ];
			var resetselect = jQuery.makeArray( resetselect );
			$('div.yesconnection div.holdsaforeign input[type=date], div.yesconnection div.holdsapending input[type=date]').val(resetselect[1]);
			$('div.yesconnection div.holdsaforeign textarea, div.yesconnection div.holdsapending textarea').val('');
			$('div.yesconnection div.holdsaforeign input.checkmark, div.yesconnection div.holdsapending input.checkmark').prop('checked', false);
			$('div.yesconnection div.holdsaforeign input[type=text], div.yesconnection div.holdsapending input[type=text]').val('');
			$('div.yesconnection div.holdsaforeign select, div.yesconnection div.holdsapending select').val( resetselect[0] );
			$('div.yesconnection div.holdsaforeign .hidethis, div.yesconnection div.holdsapending .hidethis').contents().css('display','block');
			$('div.yesconnection div.holdsaforeign .upload-drop-zone, div.yesconnection div.holdsapending .upload-drop-zone').css('height','115px');
			$('div.yesconnection div.holdsaforeign .js-upload-finished button.close, div.yesconnection div.holdsapending .js-upload-finished button.close' ).parent().parent().remove();
			$('div.yesconnection div.holdsaforeign div.fileDisplayArea img, div.yesconnection div.holdsapending div.fileDisplayArea img' ).remove();
			$('div.yesconnection div.holdsaforeign div.fileDisplayArea, div.yesconnection div.holdsapending div.fileDisplayArea' ).html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .pdf, .doc</div>');
			$('div.yesconnection div.appendaforeign div.holdsaforeign, div#yesconnection div.appendaforeign div.holdsapending').remove();
		}
	});
	//
	//START additional foreignreg
	$( document ).on('click','button#addforeignreg_basisone',function(){
		$( 'div.yescommerce div.holdsaforeign' ).last().clone().appendTo( 'div.yescommerce .appendaforeign' );
		$( 'div.yescommerce div.holdsaforeign' ).last().find('input').val('');
		$( 'div.yescommerce div.holdsaforeign' ).last().find('div.hidethis input').attr( 'id', function() {
			return (parseFloat(this.id) + 1);
		});
		var clonelabel = $( 'div.holdsaforeign div.hidethis input' ).last().attr('id');
		$( 'div.yescommerce div.holdsaforeign' ).last().find('textarea').val('');
		$( 'div.yescommerce div.holdsaforeign' ).last().find('.js-upload-finished button.close').parent().parent().remove();
		$( 'div.yescommerce div.holdsaforeign .hidethis' ).last().contents().css('display','block');
		$( 'div.yescommerce div.holdsaforeign .upload-drop-zone' ).last().css('height','115px');
		$( 'div.yescommerce div.holdsaforeign .upload-drop-zone' ).last().attr('for', clonelabel);
		$( 'div.yescommerce div.holdsaforeign #usaffiliation .upload-drop-zone' ).last().css('height','175px');
		$( 'div.yescommerce div.holdsaforeign div.fileDisplayArea img' ).last().remove();
		$( 'div.yescommerce div.holdsaforeign div.fileDisplayArea' ).last().html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .pdf, .doc</div>');
		$( 'div.yescommerce .appendaforeign .resetreg' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em');
		$( this ).removeClass( '.addinitial' );
	});
	$( document ).on('click','button#addforeignreg_basistwo',function(){
		$( 'div.yesconnection div.holdsaforeign' ).last().clone().appendTo( 'div.yesconnection .appendaforeign' );
		$( 'div.yesconnection div.holdsaforeign' ).last().find('input').val('');
		$( 'div.yesconnection div.holdsaforeign' ).last().find('div.hidethis input').attr( 'id', function() {
			return (parseFloat(this.id) + 1);
		});
		var clonelabel = $( 'div.holdsaforeign div.hidethis input' ).last().attr('id');
		$( 'div.yesconnection div.holdsaforeign' ).last().find('textarea').val('');
		$( 'div.yesconnection div.holdsaforeign' ).last().find('.js-upload-finished button.close').parent().parent().remove();
		$( 'div.yesconnection div.holdsaforeign .hidethis' ).last().contents().css('display','block');
		$( 'div.yesconnection div.holdsaforeign .upload-drop-zone' ).last().css('height','115px');
		$( 'div.yesconnection div.holdsaforeign .upload-drop-zone' ).last().attr('for', clonelabel);
		$( 'div.yesconnection div.holdsaforeign #usaffiliation .upload-drop-zone' ).last().css('height','175px');
		$( 'div.yesconnection div.holdsaforeign div.fileDisplayArea img' ).last().remove();
		$( 'div.yesconnection div.holdsaforeign div.fileDisplayArea' ).last().html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .pdf, .doc</div>');
		$( 'div.yesconnection .appendaforeign .resetreg' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em');
		$( this ).removeClass( '.addinitial' );
	});
	//
	//START additional foreignpending
	$( document ).on('click','button#addpending_basisone',function(){
		$( 'div.yescommerce div.holdsapending' ).last().clone().appendTo( 'div.yescommerce .appendapending' );
		$( 'div.yescommerce div.holdsapending' ).last().find('input').val('');
		$( 'div.yescommerce div.holdsapending' ).last().find('div.hidethis input').attr( 'id', function() {
			return (parseFloat(this.id) + 1);
		});
		var clonelabel = $( 'div.yescommerce div.holdsapending div.hidethis input' ).last().attr('id');
		$( 'div.yescommerce div.holdsapending' ).last().find('#alertmin').css('visibility','hidden').css('top','-10000px').css('float','none').css('margin-bottom','0').css('padding','0');
		$( 'div.yescommerce div.holdsapending' ).last().find('#alertmin button').css('display','none');
		$( 'div.yescommerce div.holdsapending' ).last().find('#alertmin p').css('display','none');
		$( 'div.yescommerce div.holdsapending' ).last().find('textarea').val('');
		$( 'div.yescommerce div.holdsapending' ).last().find('.js-upload-finished button.close').parent().parent().remove();
		$( 'div.yescommerce div.holdsapending .hidethis' ).last().contents().css('display','block');
		$( 'div.yescommerce div.holdsapending .upload-drop-zone' ).last().css('height','115px');
		$( 'div.yescommerce div.holdsapending .upload-drop-zone' ).last().attr('for', clonelabel);
		$( 'div.yescommerce div.holdsapending #usaffiliation .upload-drop-zone' ).last().css('height','175px');
		$( 'div.yescommerce div.holdsapending div.fileDisplayArea img' ).last().remove();
		$( 'div.yescommerce div.holdsapending div.fileDisplayArea' ).last().html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .tiff, .png</div>');
		$( 'div.yescommerce .appendapending .resetreg' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em').css('margin-top','1.5em');
		$( this ).removeClass( '.addinitial' );
	});
	$( document ).on('click','button#addpending_basistwo',function(){
		$( 'div.yesconnection div.holdsapending' ).last().clone().appendTo( 'div.yesconnection .appendapending' );
		$( 'div.yesconnection div.holdsapending' ).last().find('input').val('');
		$( 'div.yesconnection div.holdsapending' ).last().find('div.hidethis input').attr( 'id', function() {
			return (parseFloat(this.id) + 1);
		});
		var clonelabel = $( 'div.yescommerce div.holdsapending div.hidethis input' ).last().attr('id');
		$( 'div.yescommerce div.holdsapending' ).last().find('#alertmin').css('visibility','hidden').css('top','-10000px').css('float','none').css('margin-bottom','0').css('padding','0');
		$( 'div.yescommerce div.holdsapending' ).last().find('#alertmin button').css('display','none');
		$( 'div.yescommerce div.holdsapending' ).last().find('#alertmin p').css('display','none');
		$( 'div.yesconnection div.holdsapending' ).last().find('textarea').val('');
		$( 'div.yesconnection div.holdsapending' ).last().find('.js-upload-finished button.close').parent().parent().remove();
		$( 'div.yesconnection div.holdsapending .hidethis' ).last().contents().css('display','block');
		$( 'div.yesconnection div.holdsapending .upload-drop-zone' ).last().css('height','115px');
		$( 'div.yesconnection div.holdsapending .upload-drop-zone' ).last().attr('for', clonelabel);
		$( 'div.yesconnection div.holdsapending #usaffiliation .upload-drop-zone' ).last().css('height','175px');
		$( 'div.yesconnection div.holdsapending div.fileDisplayArea img' ).last().remove();
		$( 'div.yesconnection div.holdsapending div.fileDisplayArea' ).last().html('<div class="fileDisplayArea"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> <br>Upload file. Allowed file types .jpg, .tiff, .png</div>');
		$( 'div.yesconnection .appendapending .resetreg' ).removeClass( 'visuallyremoved' ).css('border-top','1px solid #ddd').css('margin-bottom','.4em').css('margin-top','1.5em');
		$( this ).removeClass( '.addinitial' );
	});
	//
	//START Basis foreign application / dashboard date alert
	//$('#alertmin').css('visibility','hidden').css('top','-10000px').css('float','none').css('margin-bottom','0').css('padding','0');
	$(document).on('click','#alertmin button.close', function(e) {
		$('#alertmin').css('visibility','hidden').css('top','-10000px').css('float','none').css('margin-bottom','0').css('padding','0');
		$('#alertmin button').css('display','none');
		$('#alertmin').css('height','1px');
		$('#alertmin').removeClass('form-group').removeClass('form-group-md');
		$('#alertmin p').css('display','none');
		e.preventDefault();
	});
	$(document).on('click','#filingbasisab #alertmin button.close', function(e) {
		var t = $('input[type=radio][disabled]');
		$('html, body').animate({
			scrollTop: (t).offset().top - 150
		}, 500);
	});
	//
	//START modals
	$('#tradeservmodal','#collectivemodal','#collectivemembmodal','#loginmodal','#emailmodal','#securitymodal','#passwordmodal','#soucontent .modal').on('shown.bs.modal', function () {
		$('.btn-success').focus();
	})
	//
	//START show standard character preview
	$( '#ta2standard' ).keyup(function(){
		var d = $( '#entertext' ).next('div');
		var currentText = $(this).val();
		$( 'p#showmarktxt' ).text(currentText);
		$('#entertext, #entertext textarea').css( 'height', (d.innerHeight()) );
	});
	//var str = $( '#ta2' ).val();
//	$( 'button#displaytxt' ).on('click',function(){
//		$( 'p#displaytext' ).html( str );
//	});
	//
	//START displaymark height match
	$( window ).load(function () {
		var d = $( '#entertext' ).next('div');
		$('#displaytext').css('display','flex').css('flex-direction','column');
		$('#entertext').css( 'min-height', (d.innerHeight()) );
		$( window ).resize(function() {
			$('#entertext').css( 'min-height', (d.innerHeight()) );
		});
	});
	//
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
	});
	//
	//START SOU modal add/remove GSs from blue bar
	$('div.inusegs button').on('click',function() {
		var colorClass = this.className;
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
		//START SOU blue bar height
		$( window ).on('load', function() {
			var winwidth = $(window).width();
			var modalparent = $('div.modal').parent();
			if (winwidth > 1199) {
				//var souf = $(modalparent).find('.statementou h2.displaycell').outerHeight();
				$.each(modalparent, function(){
					var souf = $(this).find('.statementou h2.displaycell').outerHeight();
					$(this).find('.statementou .noexpand.blue').css('height',(souf));
				});
			}
			else if (winwidth < 1199) {
				$.each(modalparent, function(){
					var souf = ($(this).find('.statementou h2.displaycell').outerHeight() + 70);
					$(this).find('.statementou .noexpand.blue').css('height',(souf));
				});
			}
		});
		$( window ).resize(function() {
			var winwidth = $(window).width();
			var modalparent = $('div.modal').parent();
			if (winwidth > 1199) {
				//var souf = $(modalparent).find('.statementou h2.displaycell').outerHeight();
				$.each(modalparent, function(){
					var souf = $(this).find('.statementou h2.displaycell').outerHeight();
					$(this).find('.statementou .noexpand.blue').css('height',(souf));
				});
			}
			else if (winwidth < 1199) {
				$.each(modalparent, function(){
					var souf = ($(this).find('.statementou h2.displaycell').outerHeight() + 70);
					$(this).find('.statementou .noexpand.blue').css('height',(souf));
				});
			}
		});
		//
	}
	$('div.classmodal button.btn-success').on('click',function() {
		var modaldiv = $('div.formodal');
		var modaldivparent = $(this).parents('div.modal');
		var modaldivgrandparent = $(modaldivparent).parent();
		$( modaldivparent ).find('span.sr-only').text('class removed');
		$( modaldivgrandparent ).find('div.inusegs span.subtle').css('display','none');
		$( modaldivparent ).parent().find('div.statementou').css('opacity','0.4');
		$( modaldivparent ).modal('hide')
		$( modaldivparent ).parent().find('div.formodal').css('display','block');
	});
	$('button.resetclassbtn').on('click',function() {
		var modaldiv = $(this).parents('div.formodal');
		var modaldivparent = $(this).parents('div.modal');
		var modaldivgrandparent = $(modaldiv).parent();
		$( modaldivgrandparent ).find('div.inusegs span.subtle').css('display','table');
		$( modaldiv).parent().find('div.statementou').css('opacity','1.0');
		$( modaldivparent ).find('span.sr-only').text('class added');
		$( modaldiv ).css('display','none');
	});
	//
	//START close button height match dashboard
	$( window ).on('load', function() {
		var d = $( '#announcements .closepans' ).prev('div');
		$(d).css('display','flex').css('flex-direction','column').css('min-height','3em');
		$('#announcements .closepans').css( 'line-height', (d.innerHeight() + 'px') ).css( 'height', (d.innerHeight()) );
		$('#announcements .closegspanels').css('line-height',(d.innerHeight() + 'px'));
		$( window ).resize(function() {
			$('#announcements .closepans').css( 'height', (d.innerHeight()) );
			$('#announcements .closegspanels').css('line-height',(d.innerHeight() + 'px'));
		});
		var e = $( '#dashboardmain .alert-warning .closepans' );
		$(e).css('min-height','3em');
		$('#dashboardmain .alert-warning .closepans').css( 'height', (e.innerHeight()) );
		$('#dashboardmain .alert-warning button.closegspanels').css('line-height',(e.innerHeight() + 'px')).css('display','table-cell');
		$( window ).resize(function() {
			$('#dashboardmain .alert-warning .closepans').css( 'height', (e.innerHeight()) );
			$('#dashboardmain .alert-warning button.closegspanels').css('line-height',(e.innerHeight() + 'px'));
		});
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
	//
	//tabpanels
	$('.tabwidget').tabs();
	$('ul[role="tablist"] a').on('click',function (){
		$(this).parent('li').toggleClass('notice');
		$(this).parent('li').siblings('li').removeClass('notice');
	});
	//
	//START toggle teas save more labels
	$('.togglesavemore').prop('checked', true);
	$('.togglesavemore').change(function() {
		if(this.checked == true){
			$( document ).find('span.subtle').removeClass('visuallyremoved');
			$( document ).find('#on').removeClass('visuallyhidden');
			$( document ).find('#off').addClass('visuallyhidden');
		}
		else if(this.checked == false) {
			$( document ).find('span.subtle').addClass( 'visuallyremoved' );
			$( document ).find('#on').addClass('visuallyhidden');
			$( document ).find('#off').removeClass('visuallyhidden');
		}
	});
	//
	//START grid view checkboxes In-Use 1(a)
	//Gridview In-Use 1(a) Form Reveal
	$(document).on('change', 'input#otherformfirst, input#otherformcommerce', function() {
		$(this).parent().siblings('div#yesinuse1atwo').css('display','block' );
	});
	//
	//START Toggle 1(a) + 1(b)
	$('#gridview table tbody tr td:nth-child(2) input').change(function() {
		if ($(this).prop('checked')) {
			$(this).parent().next('td').children('label').children('span').addClass('special');
			$(this).parent().next('td').children('input').prop({
				disabled: true,
				checked: false,
			}).addClass('disabled');
			$(this).parent().siblings('td').children('input').attr('aria-disabled', 'false');
		}
		else if ($(this).prop('checked',false)) {
			$(this).parent().next('td').children('label').children('span').removeClass('special');
			$(this).parent().next('td').children('input').prop({
				disabled: false,
			}).removeClass('disabled');
			$(this).parent().siblings('td').children('input').attr('aria-disabled', 'false');
		}
	});
	$('#gridview table tbody tr th:nth-child(2) input').change(function() {
		if ($(this).prop('checked')) {
			$(this).parent().next('th').children('label').children('span').addClass('special');
			$(this).parent().next('th').children('input').prop({
				disabled: true,
				checked: false,
			}).addClass('disabled');
			$(this).parent().next('th').children('input').attr('aria-disabled', 'true');
		}
		else if ($(this).prop('checked',false)) {
			$(this).parent().next('th').children('label').children('span').removeClass('special');
			$(this).parent().next('th').children('input').prop({
				disabled: false,
			}).removeClass('disabled');
			$(this).parent().next('th').children('input').attr('aria-disabled', 'false');
		}
	});
	$('#gridview table tbody tr td:nth-child(3) input').change(function() {
		if ($(this).prop('checked')) {
			$(this).parent().prev('td').children('label').children('span').addClass('special');
			$(this).parent().prev('td').children('input').prop({
				disabled: true,
				checked: false,
			}).addClass('disabled');
			$(this).parent().siblings('td').children('input').attr('aria-disabled', 'true');
		}
		else if ($(this).prop('checked',false)) {
			$(this).parent().prev('td').children('label').children('span').removeClass('special');
			$(this).parent().prev('td').children('input').prop({
				disabled: false,
			}).removeClass('disabled');
			$(this).parent().siblings('td').children('input').attr('aria-disabled', 'false');
		}
	});
	$('#gridview table tbody tr th:nth-child(3) input').change(function() {
		if ($(this).prop('checked')) {
			$(this).parent().prev('th').children('label').children('span').addClass('special');
			$(this).parent().prev('th').children('input').prop({
				disabled: true,
				checked: false,
			}).addClass('disabled');
			$(this).parent().prev('th').children('input').attr('aria-disabled', 'true');
		}
		else if ($(this).prop('checked',false)) {
			$(this).parent().prev('th').children('label').children('span').removeClass('special');
			$(this).parent().prev('th').children('input').prop({
				disabled: false,
			}).removeClass('disabled');
			$(this).parent().prev('th').children('input').attr('aria-disabled', 'false');
		}
	});
	//
	//START user selects In-Use 1(a) classname and adjacent checkbox
	$(document).on('change', '#gridview table tbody tr th:nth-child(2) input', function() { //Classnames 2nd checkbox
		var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
		var p 	= 	$( '#gridview table tbody tr th:nth-child(2) input:checked' ); //Classnames 2nd checkbox, checked
		var q	=	$( '#gridview table thead tr:nth-child(2) th:nth-child(2) input' ); //In-Use 1(a) checkbox
		var newrow = $( "<tr class='inuse1aone'><td colspan='5'><div id='yesinuse1aone' class='form-group'><!--toggle 1(a) one--><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>In-Use 1(a) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce' class='matchlabelheight'>In-Use 1(a) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='yesinuse1atwo' class='col-xs-12 form-group'><!--toggle 1(a) two--><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div><!-- Drop Zone --></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='specdescripthree'>Provide a description of your specimen:</label><textarea id='specdescripthree' class='form-control'></textarea></div></div></div></div></td></tr>" );
		//console.log('#gridview table tbody tr th:nth-child(2) input');
		if ($(this).prop('checked')) {
			$(q).prop({
				checked: true,
			});
			$(this).parent().prev('th').children('input').prop({
				checked: true,
			});
			$(this).parent().parent().after(newrow);
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
		var newrow = $( "<tr class='inuse1aone'><td colspan='5'><div id='yesinuse1aone' class='form-group'><!--toggle 1(a) one--><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>Foreign Application 44(d) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>Foreign Application 44(d) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='yesinuse1atwo' class='col-xs-12 form-group'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div><!-- Drop Zone --></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='specdescripthree'>Provide a description of your specimen:</label><textarea id='specdescripthree' class='form-control'></textarea></div></div></div></div></td></tr>" );
		if ($(this).prop('checked')) {
			$(q).prop({
				checked: true,
			});
			$(this).parent().prev('th').prev('th').prev('th').children('input').prop({
				checked: true,
			});
			$(this).parent().parent().after(newrow);
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
		var newrow = $( "<tr class='inuse1aone'><td colspan='5'><div id='yesinuse1aone' class='form-group'><!--toggle 1(a) one--><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>Foreign Registration 44(e) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>Foreign Registration 44(e) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='yesinuse1atwo' class='col-xs-12 form-group'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div><!-- Drop Zone --></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='specdescripthree'>Provide a description of your specimen:</label><textarea id='specdescripthree' class='form-control'></textarea></div></div></div></div></td></tr>" );
		if ($(this).prop('checked')) {
			$(q).prop({
				checked: true,
			});
			$(this).parent().prev('th').prev('th').prev('th').prev('th').children('input').prop({
				checked: true,
			});
			$(this).parent().parent().after(newrow);
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
	$(document).on('change', '#gridview table thead tr:nth-child(2) th:nth-child(2) input', function() { //In-Use 1(a) checkbox
		var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
		var p 	= 	$( '#gridview table tbody tr th:nth-child(2) input' ); //Classnames 2nd checkbox
		var pp	=	$( '#gridview table tbody tr th:nth-child(3) input' );
		var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classnames 1st checkbox
		var y	=	$( '#gridview table thead tr:nth-child(2) th:nth-child(3) input' ); //Intent-To-Use 1(b) checkbox
		var d 	= 	$( '#gridview table tbody tr td:nth-child(3) input' ); //GS 2nd checkbox
		var dd 	= 	$( '#gridview table tbody tr td:nth-child(2) input' ); //GS 1st checkbox
		var newrow = $( "<tr class='inuse1aone'><td colspan='5'><div id='yesinuse1aone' class='form-group'><!--toggle 1(a) one--><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>In-Use 1(a) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce' class='matchlabelheight'>In-Use 1(a) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='yesinuse1atwo' class='col-xs-12 form-group'><!--toggle 1(a) two--><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div><!-- Drop Zone --></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='specdescripthree'>Provide a description of your specimen:</label><textarea id='specdescripthree' class='form-control'></textarea></div></div></div></div></td></tr>" );
		var newrowgs1a = $( "<tr class='optionalupload'><td colspan='5'><div id='yesinuse1aone' class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>In-Use 1(a) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce' class='matchlabelheight'>In-Use 1(a) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='optionuploadone' class='col-xs-12'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><input class='form-check-input checkmark' type='checkbox' value='' tabindex='0' id='optionupload'><label class='form-check-label form-check' for='optionupload'><span class='radio notspecial'>Only one specimen is required per class. Click here to add a specimen to this good / service.</span></label></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md' id='optionuploadtwo'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></div></div></td></tr>" );
		//user selects classnames then selects In-Use 1(a)
		if ($(b).prop('checked')) {
			$(b).parent().next('th').children('input').prop({
				checked: true,
			});
			$( document ).find($(b).prop('checked',true)).parent().parent().after(newrow);
			$('div#yesinuse1atwo').css('display','none');
			//console.log(dd);
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
			$(dd).parent().parent().after(newrow);
			$( document ).find('tr:contains(Class)').after(newrowgs1a);
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
			$( '#gridview table thead tr:nth-child(2) th:nth-child(3) label span' ).addClass('special').addClass('disabled');
			$(pp).prop({
				disabled: true,
				checked: false,
			});
			$(pp).attr('aria-disabled', 'true');
			$( '#gridview table tbody tr th:nth-child(3) label span' ).addClass('special').addClass('disabled');
			$(d).prop({
				disabled: true,
				checked: false,
			});
			$(d).attr('aria-disabled', 'true');
			$( '#gridview table tbody tr td:nth-child(3) label span' ).addClass('special').addClass('disabled');
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
			$( '#gridview table thead tr:nth-child(2) th:nth-child(3) label span' ).removeClass('special').removeClass('disabled');
			$(pp).prop({
				disabled: false,
			});
			$(pp).attr('aria-disabled', 'false');
			$( '#gridview table tbody tr th:nth-child(3) label span' ).removeClass('special').removeClass('disabled');
			$(d).prop({
				disabled: false,
			});
			$(d).attr('aria-disabled', 'false');
			$( '#gridview table tbody tr td:nth-child(3) label span' ).removeClass('special').removeClass('disabled');
			$(c).prop({
				checked: false,
			});
			$( document ).find('.inuse1aone').remove('.inuse1aone');
			$('div#optionuploadtwo').css('display','none');
			$( document ).find('.optionalupload').remove('.optionalupload');
		}
	});

	//grid view checkboxes Intent-to-Use 1(b)
	$(document).on('change', '#gridview table thead tr:nth-child(2) th:nth-child(3) input', function() { //Intent-to-Use 1(b) checkbox
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
			$( '#gridview table tbody tr td:nth-child(2) label span' ).addClass('special').addClass('disabled');
			$(ff).prop({
				disabled: true,
				checked: false,
			});
			$(ff).attr('aria-disabled', 'true');
			$( '#gridview table tbody tr th:nth-child(2) label span' ).addClass('special').addClass('disabled');
			$(b).parent().next('th').children('input').attr('aria-disabled', 'true');
			$( '#gridview table tbody tr th:nth-child(2) label span' ).addClass('special').addClass('disabled');
			$( '#gridview table thead tr:nth-child(2) th:nth-child(2) label span' ).addClass('special').addClass('disabled');
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
			$( '#gridview table tbody tr td:nth-child(2) label span' ).addClass('special').addClass('disabled');

			$(ee).prop({
				disabled: true,
				checked: false,
			});
			$(ee).attr('aria-disabled', 'true');
			$( '#gridview table thead tr:nth-child(2) th:nth-child(2) label span' ).addClass('special').addClass('disabled');

			$(ff).prop({
				disabled: true,
				checked: false,
			});
			$(ff).attr('aria-disabled', 'true');
			$( '#gridview table tbody tr th:nth-child(2) label span' ).addClass('special').addClass('disabled');

			$(b).parent().next('th').children('input').attr('aria-disabled', 'true');
			$( '#gridview table tbody tr th:nth-child(2) label span' ).addClass('special').addClass('disabled');
			//disable
			$( '#gridview table thead tr:nth-child(2) th:nth-child(2) label span' ).addClass('special').addClass('disabled');
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
			$( '#gridview table tbody tr td:nth-child(2) label span' ).removeClass('special').removeClass('disabled');
			$(ee).prop({
				disabled: false,
			});
			$(ee).attr('aria-disabled', 'false');
			$( '#gridview table thead tr:nth-child(2) th:nth-child(2) label span' ).removeClass('special').removeClass('disabled');
			$(ff).prop({
				disabled: false,
			});
			$(ff).attr('aria-disabled', 'false');
			$( '#gridview table tbody tr th:nth-child(2) label span' ).removeClass('special').removeClass('disabled');

			$(b).parent().next('th').children('input').attr('aria-disabled', 'false');
			$( '#gridview table tbody tr th:nth-child(2) label span' ).removeClass('special').removeClass('disabled');
			$( '#gridview table thead tr:nth-child(2) th:nth-child(2) label span' ).removeClass('special').removeClass('disabled');
		}
	});

	//grid view checkboxes Foreign Application 44(d)
	$(document).on('change', '#gridview table thead tr:nth-child(2) th:nth-child(4) input', function() { //Foreign Application 44(d) checkbox
		var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
		var p 	= 	$( '#gridview table tbody tr th:nth-child(4) input' ); //Classnames 4th checkbox
		var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classnames 1st checkbox
		var dd 	= 	$( '#gridview table tbody tr td:nth-child(4) input' ); //GS 3rd checkbox
		var newrow = $( "<tr class='foreignapp'><td colspan='5'><div class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='country'>Foreign Application Country</label><select class='form-control' id='country'><option value='Select'>Select</option><option value='Mexico'>Mexico</option><option value='Canada'>Canada</option><option value='United Kingdom'>United Kingdom</option></select></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='foreignappno'>Foreign Application Number</label><input type='textarea' class='form-control' id='foreignappno' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='foreignappdate'>Foreign Application Filing Date</label><input type='date' class='form-control' id='foreignappdate' value=''></div></div></td></tr>" );
		var newrowgs44d = $( "<tr class='optionalupload'><td colspan='5'><div id='yesinuse1aone' class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>Foreign Application 44(d) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>Foreign Application 44(d) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='optionuploadone' class='col-xs-12'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><input class='form-check-input checkmark' type='checkbox' value='' tabindex='0' id='optionupload'><label class='form-check-label form-check' for='optionupload'><span class='radio notspecial'>Only one specimen is required per class. Click here to add a specimen to this good / service.</span></label></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md' id='optionuploadtwo'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></div></div></td></tr>" );
		//user selects classnames then selects Foreign Application 44(d)
		if ($(b).prop('checked')) {
			$(b).parent().next('th').next('th').next('th').children('input').prop({
				checked: true,
			});
			$( document ).find($(b).prop('checked',true)).parent().parent().after(newrow);
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
			$(dd).parent().parent().after(newrowgs44d);
			$( document ).find('tr:contains(Class)').after(newrow);
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
	$(document).on('change', '#gridview table thead tr:nth-child(2) th:nth-child(5) input', function() { //Foreign Registration 44(e) checkbox
		var b 	= 	$( '#gridview table tbody tr th:nth-child(1) input:checked' ); //Classnames 1st checkbox, checked
		var p 	= 	$( '#gridview table tbody tr th:nth-child(5) input' ); //Classnames 5th checkbox
		var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classnames 1st checkbox
		var dd 	= 	$( '#gridview table tbody tr td:nth-child(5) input' ); //GS 4th checkbox
		var newrow = $( "<tr class='foreignregi'><td colspan='5'><div class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='country'>Foreign Registration Country</label><select class='form-control' id='country'><option value='Select'>Select</option><option value='Mexico'>Mexico</option><option value='Canada'>Canada</option><option value='United Kingdom'>United Kingdom</option></select></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='foreignappno'>Foreign Registration Number</label><input type='textarea' class='form-control' id='foreignappno' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='foreignappdate'>Foreign Registration Exp. Date</label><input type='date' class='form-control' id='foreignappdate' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></td></tr>" );
		var newrowgs44e = $( "<tr class='optionalupload'><td colspan='5'><div id='yesinuse1aone' class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>Foreign Registration 44(e) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>Foreign Registration 44(e) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='optionuploadone' class='col-xs-12'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><input class='form-check-input checkmark' type='checkbox' value='' tabindex='0' id='optionupload'><label class='form-check-label form-check' for='optionupload'><span class='radio notspecial'>Only one specimen is required per class. Click here to add a specimen to this good / service.</span></label></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md' id='optionuploadtwo'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></div></div></td></tr>" );
		//user selects classnames then selects Foreign Registration 44(e)
		if ($(b).prop('checked')) {
			$(b).parent().next('th').next('th').next('th').next('th').children('input').prop({
				checked: true,
			});
			$( document ).find($(b).prop('checked',true)).parent().parent().after(newrow);
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
			$(dd).parent().parent().after(newrowgs44e);
			$( document ).find('tr:contains(Class)').after(newrow);
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

	$(document).on('change', '#gridview table tbody tr td:nth-child(2) input', function() { //In-Use 1(a) GS checkbox
		var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classname 1st checkbox
		var newrow = $( "<tr class='optionalupload'><td colspan='5'><div id='yesinuse1aone' class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>In-Use 1(a) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce' class='matchlabelheight'>In-Use 1(a) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='optionuploadone' class='col-xs-12'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><input class='form-check-input checkmark' type='checkbox' value='' tabindex='0' id='optionupload'><label class='form-check-label form-check' for='optionupload'><span class='radio notspecial'>Only one specimen is required per class. Click here to add a specimen to this good / service.</span></label></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md' id='optionuploadtwo'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></div></div></td></tr>" );
		if (this.checked == true) {
			$(this).parent().parent().after(newrow);
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
	$(document).on('change', '#gridview table tbody tr td:nth-child(3) input', function() { //Intent-to-Use 1(b) GS checkbox
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
	$(document).on('change', '#gridview table tbody tr td:nth-child(4) input', function() { //Foreign Application 44(d) GS checkbox
		var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classname 1st checkbox
		var newrow = $( "<tr class='optionalupload'><td colspan='5'><div id='yesinuse1aone' class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>Foreign Application 44(d) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>Foreign Application 44(d) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='optionuploadone' class='col-xs-12'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><input class='form-check-input checkmark' type='checkbox' value='' tabindex='0' id='optionupload'><label class='form-check-label form-check' for='optionupload'><span class='radio notspecial'>Only one specimen is required per class. Click here to add a specimen to this good / service.</span></label></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md' id='optionuploadtwo'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></div></div></td></tr>" );
		if (this.checked == true) {
			$(this).parent().parent().after(newrow);
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
	$(document).on('change', '#gridview table tbody tr td:nth-child(5) input', function() { //Foreign Registration 44(e) GS checkbox
		var c 	= 	$( '#gridview table tbody tr th:nth-child(1) input' ); //Classname 1st checkbox
		var newrow = $( "<tr class='optionalupload'><td colspan='5'><div id='yesinuse1aone' class='form-group'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md matchlabelheightdiv'><label for='otherformfirst' class='matchlabelheight'>Foreign Registration 44(e) Date of First Use Anywhere</label><input type='date' class='form-control' id='otherformfirst' value=''></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><label for='otherformcommerce'>Foreign Registration 44(e) Date of First Use in Commerce</label><input type='date' class='form-control' id='otherformcommerce' value=''></div><div id='optionuploadone' class='col-xs-12'><div class='row'><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md'><input class='form-check-input checkmark' type='checkbox' value='' tabindex='0' id='optionupload'><label class='form-check-label form-check' for='optionupload'><span class='radio notspecial'>Only one specimen is required per class. Click here to add a specimen to this good / service.</span></label></div><div class='col-xs-12 col-md-6 col-lg-6 form-group form-group-md' id='optionuploadtwo'><p id='upimage'>Provide an image of your specimen:</p><div><label class='small upload-drop-zone' for='specimenfile' id='upimg'><span class='glyphicon glyphicon-upload' aria-hidden='true'></span> <br>Drag and drop files or click here to upload.</label><input type='file' id='specimenfile' class='form-control' aria-labelledby='upimg upimage'></div></div></div></div></div></td></tr>" );
		if (this.checked == true) {
			$(this).parent().parent().after(newrow);
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

//Filing Basis a, b: "The system shall not allow the user to select 'No' for all goods / services within each class (one of them has to be 'Yes')"
	$(document).on('change','#basisab input#inlineRadio69, #basisab input#inlineRadio71, #basisab input#inlineRadio73', function() { //'yes' buttons
		if (this.checked == true) {
			$(this).closest('fieldset').find('input[type=radio].countyes').prop({
				disabled: false,
				checked:false,
			}).attr('aria-disabled', 'false');
			$(this).closest('fieldset').find('input[type=radio].countyes').parent().removeClass('special').removeClass('disabled');
			$(this).closest('fieldset').find('input[type=radio].count').attr('checked',false);
			$(document).on('change','#basisab input[type=radio].count', function() {
				var count = $(this).closest('fieldset').find('input[type=radio].count').length;
				var countall = $(this).closest('fieldset').find('input[type=radio].count:checked').length;
				var b = $('#alertmin');
				var c = $('#mintext');
				var d = $('#alertbtn');
				if (countall == count) {
					$(b).css('visibility','visible');
					$(b).css('height','auto');
					$(b).addClass('form-group').addClass('form-group-md');
					$(b).css('float','left');
					$(b).css('top','.25em');
					$(b).css('padding','1em');
					$(b).css('margin-bottom','1em');
					$(c).css('display','block');
					$(d).css('display','block');
					$(d).toggleClass( 'focus' );
					$('html, body').animate({
						scrollTop: $(b).offset().top - 144
					}, 500);
					$(c).html('You have indicated at least one of the goods / services in this class is in-use. You must choose ' + '<span class="leftquote"></span>Yes<span class="rightquote"></span>' + ' for at least one good / service.');
					$(this).prop({
						disabled: true,
						checked:false,
					}).attr('aria-disabled', 'true');
					$(this).parent().addClass('special').addClass('disabled');
				}
			});
			$(document).on('change','#basisab input.countyes', function() {
				$(this).closest('fieldset').find('input[type=radio].count').prop({
					disabled: false,
				}).attr('aria-disabled', 'false');
				$(this).closest('fieldset').find('label.special').removeClass('special').removeClass('disabled');
			});
		}
	});
	$(document).on('change','#basisab input#inlineRadio70, input#inlineRadio72, input#inlineRadio74', function() { //'no' buttons
		$(this).closest('fieldset').find('input[type=radio].count').prop({
			disabled: false,
			checked:true,
		}).attr('aria-disabled', 'false');
		$(this).closest('fieldset').find('label.special').removeClass('special').removeClass('disabled');
		$(this).closest('fieldset').find('input[type=radio].countyes').prop({
			disabled: true,
			checked:false,
		}).attr('aria-disabled', 'true');
		$(this).closest('fieldset').find('input[type=radio].countyes').parent().addClass('special').addClass('disabled');
	});
	//
	//START review application statement
	var arrcompanies = [ 'Scrubby Butts Soap Co.','Black Diamond Ski Shop' ];
	var arrcompany = jQuery.makeArray( arrcompanies );
	var arrnames = [ 'John Smith','Robert Snow' ];
	var arrname = jQuery.makeArray( arrnames );
	var arrterritories = [ 'The United States of America','Afghanistan' ];
	var arrterritory = jQuery.makeArray( arrterritories );
	var arrstates = [ 'Colorado','Alaska' ];
	var arrstate = jQuery.makeArray( arrstates );
	$( 'span.partner_citizenship' ).html( arrterritory[0] );
	$( 'span.partner_name' ).html( arrnames[0]  + " and " + arrnames[1] );
	$( 'span.partnershipname' ).html( arrcompany[0] );
	$( 'span.state_country' ).html( arrstate[0] );
	//
	//WYSIWYG editor for revive petition application scenarios
	if ( $( 'div#editor' ).length ) {
		var quill = new Quill('#editor', {
			theme: 'snow'
		});
	}
	//
	//START ROA Optional Actions
	$('#roaoptact').on('click', 'button.next', function() {
		var url = 'attorney_info_review_optional.html';
		$(location).prop('href', url);
	});
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
	//
	//START revive petition options
	$('div#reviveappformradios').css('display','none');
	$('div#reviveappformdisplay').css('display','block');
	$('div#reviveappform').css('display','none');
	$('div#reconsideration').css('display','none');
	$('div#noreconsideration').css('display','none');
	$('div#yesreconsideration').css('display','none');
	$('div#yesttabreconsideration').css('display','none');
	$('div#nottabreconsideration').css('display','none');
	$('div#newpetradios').css('display','none');
	$('div#twomonths').css('display','none');
	$('div#petdiradios').css('display','none');
	$('div#receipt').css('display','none');
	$('div#received').css('display','none');
	$('div#expedite').css('display','none');
	$('div#reconsidersig').css('display','none');
	$('div#petsignature').css('display','none');
	$('div#xreq').css('display','none');
	$('div#sou').css('display','none');
	$('div#requestnoa').css('display','none');
	$('div#fees').css('display','none');
	$('div#souquestion').css('display','none');
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
	$('input#inlineRadio072').change(function() {
		if(this.checked == true){
			$(clear);
			$('input#inlineRadio074, input#inlineRadio075').prop({
				checked:false,
			});
			$('div#reviveappformradios').hide('fast');
			$('div#reviveappform').show('fast','swing');
		}
	});
	$('input#inlineRadio073,input#inlineRadio079').change(function() {
		if(this.checked == true){
			$(clear);
			$('div#reviveappform').hide('fast','swing');
			$('div#reviveappformradios').show('fast','swing');
		}
	});
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
	$('input#inlineRadio0106').change(function() {
		$(clearreconsider);
		$('div#reconsideration').show('fast','swing');
	});
	$('input#inlineRadio0107').change(function() {
		$(clearreconsider);
		$('div#reconsideration').hide('fast');
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
	//
	//START amend mark edit descripion + literal
	$('div#amenddescript').css('display','none');
	$('div#amendlit').css('display','none');
	$('input#amendd').change(function() {
		if(this.checked == true){
			$('div#amenddescript').show( 'fast' );
		}
		else {
			$('div#amenddescript').hide( 'fast' );
		}
	});
	$('input#amendliteral').change(function() {
		if(this.checked == true){
			$('div#amendlit').show( 'fast' );
		}
		else {
			$('div#amendlit').hide( 'fast' );
		}
	});
	//
	//START amend mark additional info
	if ( $( 'div#amendmark' ).length ) {
		$('fieldset#addmarkinfo').css('display','none');
	}
	$('input#inlineRadio0111').change(function() {
		if(this.checked == true){
			$('fieldset#addmarkinfo').show( 'fast' );
		}
		else {
			$('fieldset#addmarkinfo').hide( 'fast' );
		}
	});
	$('input#inlineRadio0112').change(function() {
		if(this.checked == true){
			$('fieldset#addmarkinfo').hide( 'fast' );
		}
		else {
			$('fieldset#addmarkinfo').show( 'fast' );
		}
	});
});