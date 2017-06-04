$(document)
		.ready(
				function() {
					$("#dietReq :input")
							.change(
									function() {
										divId = this.parentNode.parentNode.parentNode.parentNode.parentNode.id;
										dietReq = $(this).val();
										if (dietReq == 'yes') {
											console.log(divId
													+ 'foodpreference');
											document.getElementById(divId
													+ 'foodpreference').style.display = 'block';
										} else if (dietReq == 'no') {
											document.getElementById(divId
													+ 'foodpreference').style.display = 'none';
										}
									});

				});

function editEventToUpdate() {
	document.getElementById("updateBtn").style.display = 'block';
	document.getElementById("editBtn").style.display = 'none';

	jQuery(function($) {
		$('#eventSetUpForm').find("*").removeAttr("disabled");
	});

};

function editParticipantToUpdate() {
	document.getElementById("participantUpdateBtn").style.display = 'block';
	document.getElementById("participantEditBtn").style.display = 'none';

	jQuery(function($) {
		$('#ParticipantForm').find("*").removeAttr("disabled");
	});

};

function editTaskToUpdate() {
	document.getElementById("taskUpdateBtn").style.display = 'block';
	document.getElementById("taskEditBtn").style.display = 'none';

	jQuery(function($) {
		$('#TaskForm').find("*").removeAttr("disabled");
	});
}

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
	var newChild = $(document.getElementsByClassName('child')[0]).clone(true);
	var numberOfChild = document.getElementsByClassName("child").length;
	var inputTypes = [ "input", "select", "textarea" ];

	newChild = replaceNameAndId(newChild, numberOfChild, inputTypes);
	newChild = replaceIdForDiv(newChild, numberOfChild, "div");
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
					this.name = this.name.replace('children[0', 'children['
							+ numberOfChild);
					this.id = this.id.replace('children0', 'children'
							+ numberOfChild);
				});
	}
	return newChild;
}

function replaceIdForDiv(newChild, numberOfChild, inputType) {

	newChild.find(inputType).each(function() {
		this.id = this.id.replace('child1', 'child' + (numberOfChild + 1));
	});

	return newChild;
}
function replaceHeading(newChild, numberOfChild) {
	newChild.find('legend').each(function() {
		this.innerHTML = this.innerHTML.replace(1, (numberOfChild + 1));
	});
	return newChild;
}