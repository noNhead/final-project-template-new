$(document).ready(function() {
	var form = $('#registration-form');
	var input = $('input#login', form)

	input.keyup(function(event) {
		var value = input.val();
		if (value.length > 3) {
			$.getJSON('/checkloginexist', {
				login : value
			}, function(response) {
				if (response == true) {
					$('#registration-form input#login').css({
						"color" : "red"
					});
				} else {
					$('#registration-form input#login').css({
						"color" : "black"
					});
				}
			});
		}
	});
});