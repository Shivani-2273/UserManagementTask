$(document).ready(function() {
	$("#sDateError").hide();
	$("#eDateError").hide();

	var sDateError = false;
	var eDateError = false;
	const downloadCSV = document.querySelector("#download");

	function check_startDate() {
		var sDate = $("#startDate").val();
		if (sDate.length == 0) {
			$("#sDateError").html("Please select start date!!").css("color", "red");;
			$("#sDateError").show();
			sDateError = true;
		} else {
			$("#sDateError").hide();
		}
	}


	function check_endDate() {
		var eDate = $("#endDate").val();
		if (eDate.length == 0) {
			$("#eDateError").html("Please select end date!!").css("color", "red");;
			$("#eDateError").show();
			eDateError = true;
		} else {
			$("#eDateError").hide();
		}
	}


	/*function downloadCSV() {

		downloadCSV.addEventListener("click", () => {
			

		});
		var element = document.createElement("a");

		element.href = "/Login.csv";
		element.download = "Login.csv";

		element.setAttribute(element.href, "Login.csv");
		element.style.display = 'none';


		document.body.appendChild(element);
		element.click();

		//remove element from body
		document.body.removeChild(element);


	}*/


	$("#csvDownload").submit(function() {
		sDateError = false;
		eDateError = false;
		check_startDate();
		check_endDate();

		if (sDateError === false && eDateError === false) {
			//return true;
		} else {
			return false;
		}
	});
});