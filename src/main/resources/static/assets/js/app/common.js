function validateFields(arrIds){
	
	 for (var i = 0; i < arrIds.length; i++)
        {
			console.log(arrIds[i]);
			console.log($("#"+arrIds[i]+"").val());
            if ($("#"+arrIds[i]+"").val().trim().length == 0)
            {
                return false;
            }
        }
	return true;
}