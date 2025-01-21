function validateFields(arrIds) {

	for (var i = 0; i < arrIds.length; i++) {
		//console.log(arrIds[i]);
		//console.log($("#" + arrIds[i] + "").val());
		if ($("#" + arrIds[i] + "").val().trim().length == 0) {
			return false;
		}
	}
	return true;
}

function valiateMobileNumber(phone) {

	for (var i = 0; i < phone.length; i++) {

		var pattern = /^[1-9][0-9]{9}$/;
        if (pattern.test(phone[i])) {
			return false;
		} else {
			return true;
		}
	}


}