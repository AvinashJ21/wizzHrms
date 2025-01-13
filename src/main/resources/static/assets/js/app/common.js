function validateFields(arrIds){
	
	 for (var i = 0; i < arrIds.length; i++)
        {
            if ($("#"+arrIds[i]+"").val().trim().length == 0)
            {
                return false;
            }
        }
	return true;
}