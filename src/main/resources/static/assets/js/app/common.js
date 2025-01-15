function validateFields(arrIds) {

	for (var i = 0; i < arrIds.length; i++) {
		console.log(arrIds[i]);
		console.log($("#" + arrIds[i] + "").val());
		if ($("#" + arrIds[i] + "").val().trim().length == 0) {
			return false;
		}
	}
	return true;
}

function valiateMobileNumber(phone) {

	phone = phone.replace(/[^0-9]/g, '');
	if (phone.length != 10) {
		return false;
	} else {
		return true;
	}

}