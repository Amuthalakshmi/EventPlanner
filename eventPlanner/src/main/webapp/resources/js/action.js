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

function showDiv(element) {
	var numberOfChild = document.getElementsByClassName("child").length;
	if (numberOfChild > element.value) {
		var numberOfChildToBeDeleted = numberOfChild - element.value;
		console.log('To delete: ' + numberOfChildToBeDeleted);
		for (var i = numberOfChildToBeDeleted; i >= 1; i--) {
			del();
		}
	} else if (numberOfChild < element.value) {
		var numberOfChildToBeAdded = element.value - numberOfChild;
		console.log('To add: ' + numberOfChildToBeAdded);
		for (var i = 1; i <= numberOfChildToBeAdded; i++) {
			add();
		}
	}
}

function add() {
	var original = document.getElementById('foreachkid');
	var newChild = $(document.getElementsByClassName('child')[0]).clone(
			true);
	var numberOfChild = document.getElementsByClassName("child").length;
	var inputTypes = [ "input", "select", "textarea" ];

	newChild = replaceNameAndId(newChild, numberOfChild, inputTypes);
	newChild = replaceHeading(newChild, numberOfChild);
	newChild.attr('id', 'child' + (numberOfChild + 1));

	$(original).append(newChild);
}

function del() {
	var numberOfChild = document.getElementsByClassName("child").length;
	var childToBeRemoved = document.getElementById('child' + numberOfChild);
	childToBeRemoved.parentNode.removeChild(childToBeRemoved);
}

function replaceNameAndId(newChild, numberOfChild, inputTypes) {

	newChild.attr('id', 'child' + (numberOfChild + 1));

	for (i = 0; i < inputTypes.length; i++) {
		newChild.find(inputTypes[i]).each(
				function() {
					this.name = this.name.replace('List[0', 'List['
							+ numberOfChild);
					this.id = this.id.replace('List0', 'List'
							+ numberOfChild);
				});
	}
	return newChild;
}

function replaceHeading(newChild, numberOfChild) {
	newChild.find('h4').each(function() {
		this.innerHTML = this.innerHTML.replace(1, (numberOfChild + 1));
	});
	return newChild;
}