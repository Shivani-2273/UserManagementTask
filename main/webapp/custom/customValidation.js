
$(document).ready(function() {

	$("#email_error").hide();

	$("#password_error").hide();
	$("#Cpassword_error").hide();




	var email_error = false;

	var password_error = false;
	var Cpassword_error = false;
	


	//email validation
	$("#email").keyup(function() {
		check_email();
	});
	$("#email").focusout(function() {
		$("#email_error").hide();
	});
	$("#email").focus(function() {
		check_email();
	});

	//password validation
	$("#password").keyup(function() {
		check_pass();
	});
	$("#password").focusout(function() {
		$("#password_error").hide();

	});

	$("#password").focus(function() {
		check_pass();
	});

	//confirm password validation
	$("#Cpassword").keyup(function() {
		check_Cpass();
	});
	$("#Cpassword").focusout(function() {
		$("#Cpassword_error").hide();
	});

	$("#Cpassword").focus(function() {
		check_Cpass();
	});


	


	function check_email() {
		var emailPattern = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
		var email = $("#email").val();
		if (email.length == 0) {
			$("#email_error").html("Please enter email!!").css("color", "red");;
			$("#email_error").show();

			email_error = true;
		} else if (!emailPattern.test(email)) {
			$("#email_error").html("Please enter valid email!!").css("color", "red");;
			$("#email_error").show();
			email_error = true;
		} else {
			$("#email_error").hide();

		}

	}


	function check_pass() {
		var pass_length = $("#password").val().length;
		if (pass_length == 0) {
			$("#password_error").html("Please enter password!!").css("color", "red");;
			$("#password_error").show();
			password_error = true;
		}
		else {
			$("#password_error").hide();
		}
	}
	function check_Cpass() {
		var pass = $("#password").val();
		var cpass = $("#Cpassword").val();

		if (cpass.length == 0) {
			$("#Cpassword_error").html("Please enter confirm passwordl!!").css("color", "red");
			$("#Cpassword_error").show();
			Cpassword_error = true;
		}

		else if (pass !== cpass) {
			$("#Cpassword_error").html("Password and confirm password should be same").css("color", "red");
			$("#Cpassword_error").show();

			Cpassword_error = true;
		} else {
			$("#Cpassword_error").hide();

		}
	}


	$("#resetPassword").submit(function() {

		email_error = false;
		password_error = false;
		Cpassword_error = false;


		check_email();
		check_pass();
		check_Cpass();

		if (email_error === false && password_error === false && Cpassword_error === false) {
			return true;
		} else {

			return false;
		}

	});


	$("#login").submit(function() {

		email_error = false;
		password_error = false;


		check_email();
		check_pass();
		if (email_error === false && password_error === false) {
			return true;
		} else {
			return false;
		}
	});




});