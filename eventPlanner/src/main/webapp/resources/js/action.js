/*
$(document).ready(function() {
	$('.date-picker').datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : 'yy-mm-dd'
	});
});
 */

$(document).ready(function() {	
	$('#datePicker').datepicker({
		changeMonth : true,
		changeYear : true,
		format : 'yyyy-mm-dd',
		startDate: '-0d',
		todayHighlight: true
	});
});