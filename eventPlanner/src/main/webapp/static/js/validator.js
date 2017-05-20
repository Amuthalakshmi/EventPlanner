$(document).ready(function() {
    $('#event').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	eventName: {
                validators: {
                    notEmpty: {
                        message: 'The event Name is required'
                    },
                    stringLength: {
                        min: 5,
                        max: 50,
                        message: 'The username must be more than 5 and less than 50 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z _\.]+$/,
                        message: 'The event Name can only consist of alphabetical, dot and underscore'
                    }
                }
            }
        }
    });
});