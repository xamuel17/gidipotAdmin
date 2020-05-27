$(document).ready(
		function() {

			// SUBMIT FORM
			$("#myForm").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
						firstname : $("#firstname").val(),
						lastname : $("#lastname").val(),
						password : $("#password").val(),
						username : $("#username").val(),
						phoneNumber : $("#phoneNumber").val(),
						role : $("#role").val()
				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url :  "create",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						if (result.responseCode == "00") {
							$("#postResultDiv").html(
									"" + result.data.bookName
											+ "Post Successfully! <br>"
											+ "---> Congrats !!" + "</p>");
						} else {
							$("#postResultDiv").html("<strong>Error</strong>");
						}
						console.log(result);
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});

			}

		})