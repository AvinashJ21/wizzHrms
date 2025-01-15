$(document).ready(function() {

	$("#hireDate").jqxDateTimeInput({ width: '200px', height: '30px', value: new Date() });
	$("#dobDate").jqxDateTimeInput({ width: '200px', height: '30px', value: new Date() });
	

	var url = "../assets/CountryCodes.json";
	var source =
	{
		datatype: "json",
		datafields: [
			{ name: 'name' },
			{ name: 'code' }
		],
		url: url,
		async: false
	};
	var dataAdapter = new $.jqx.dataAdapter(source);
	// Create a jqxComboBox
	$("#countryCode").jqxComboBox({ width: '100%', height: '35', selectedIndex: -1, source: dataAdapter, displayMember: "name", valueMember: "code", width: 200, height: 30, });


	$("#addEmployee").click(function() {

		$("#addUpdateEmployeesModal").modal('show');
		$("#employeesForm").trigger('reset');
		//		$("#projectsActiveChkBox").css('display', 'none');


	});

});