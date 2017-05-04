$(document).ready(function() {	
	$('#datePicker').datepicker({
		changeMonth : true,
		changeYear : true,
		format : 'yyyy-mm-dd',
		startDate: '-0d',
		todayHighlight: true
	});	
});

function editToUpdate() {
	document.getElementById("updateBtn").style.display='block';
	document.getElementById("editBtn").style.display='none';
	
	jQuery(function($) {
		$('#eventSetUpForm').find("*").removeAttr("disabled");				        
    });
	
};