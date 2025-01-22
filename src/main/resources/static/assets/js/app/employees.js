$(document).ready(function() {

	$("#hireDate").jqxDateTimeInput({ width: '200px', height: '30px', value: new Date() });
	$("#dobDate").jqxDateTimeInput({ width: '200px', height: '30px', value: new Date() });
	var param;
	var desigantions;

	//getEmployees();
	getDesignations();
	getEmpRoles();
	initializeEmployeesGrid(null);
	var url = "../assets/CountryCodes.json";
	var source =
	{
		datatype: "json",
		datafields: [
			{ name: 'name' },
			{ name: 'dial_code' }
		],
		url: url,
		async: false
	};

	var dataAdapter = new $.jqx.dataAdapter(source);

	// Create a jqxComboBox
	$("#countryCode").jqxComboBox({ width: '100%', height: '35', selectedIndex: 0, source: dataAdapter, displayMember: "name", valueMember: "dial_code", width: 200, height: 30, });


	$("#addEmployee").click(function() {

		$("#addUpdateEmployeesModal").modal('show');
		$("#employeesForm").trigger('reset');
		//$("#projectsActiveChkBox").css('display', 'none');


	});

	function initializeEmployeesGrid(data) {

		var source =
		{
			localdata: data,
			datatype: "array",
			datafields: [
				{ name: 'employeeId', type: 'int' },
				{ name: 'countryCode', type: 'string' },
				{ name: 'designationId', type: 'int' },
				{ name: 'designation', type: 'string' },
				{ name: 'emailId', type: 'string' },
				{ name: 'employeeFullName', type: 'string' },
				{ name: 'employeeOrgId', type: 'string' },
				{ name: 'hireDate', type: 'string' },
				{ name: 'mobNumber', type: 'string' },
				{ name: 'empPersonalDetails', type: 'array' },
				{ name: 'roles', type: 'array' },
				{ name: 'createdOn', type: 'date' },
				{ name: 'modifiedDate', type: 'date' },
				{ name: 'modifiedBy', type: 'string' },
				{ name: 'active', type: 'boolean' }
			]
		};
		var dataAdapter = new $.jqx.dataAdapter(source);
		$("#employee_grid").jqxGrid(
			{
				width: '100%',
				height: '400px',
				source: dataAdapter,
				sortable: true,
				enabletooltips: true,
				editable: false,
				selectionmode: 'singlerow',
				columnsresize: true,
				enabletooltips: true,
				enablehover: true,
				columns: [
					{ text: 'Employee Name', datafield: 'employeeFullName', width: '20%' },
					{ text: 'Employee Id', datafield: 'employeeOrgId', width: '20%' },
					{ text: 'Designation', datafield: 'designation', width: '20%' },
					{ text: 'Email Id', datafield: 'emailId', width: '20%' },
					{ text: 'Country Dial Code', datafield: 'countryCode', width: '20%' },
					{ text: 'Mobile Number', datafield: 'mobNumber', width: '20%' },
					{ text: 'Hire Date', datafield: 'hireDate', width: '20%' },
					{ text: 'Created Date', datafield: 'createdOn', width: '20%' },
					{ text: 'Modified Date', datafield: 'modifiedDate', width: '20%' },
					{ text: 'Modified By', datafield: 'modifiedBy' },
					{
						text: 'Active', pinned: true, datafield: 'active', width: '5%', cellsrenderer: function(row, dataField, value, html, column, data) {

							if (value)
								return '<div style="align-items: center;justify-content: center; height: 100%; display: flex;"><img style="width: 35px; height: 20px;" src="../assets//images/active.png"/></div>';
							else
								return '<div style="align-items: center;justify-content: center; height: 100%; display: flex;"><img style="width: 20px; height: 20px;" src="../assets//images/inactive.png"/></div>';
						}

					}


				]
			});

	}

	$("#addUpdEmployee").click(function() {


		var arr = ['employeeId', 'employeeFullName', 'emailId', 'personalEmailId', 'adharCardNo', 'panCardNo', 'permanentAddress', 'altMobNumber'];
		var mobNoArr = ['mobNumber', 'altMobNumber'];
		if (validateFields(arr)) {

			if (valiateMobileNumber(mobNoArr)) {

				buildPayload();

			} else {
				alert('Please enter valid mobile number');
			}

		} else {

			alert("Please enter all the fields");
		}



	});

	function buildPayload() {

		var param = {};
		param['employeeId'] = $("#employeeId").val();
		param['employeeOrgId'] = $("#employeeOrgId").val();
		param['employeeFullName'] = $("#employeeFullName").val();
		param['emailId'] = $("#emailId").val();
		var item = $("#countryCode").jqxComboBox('getSelectedItem');
		console.log(item);
		var desgItem = $("#designation").jqxComboBox('getSelectedItem');
		param['designation'] = desgItem.originalItem.designationName;
		param['designationId'] = desgItem.originalItem.id;

		param['countryCode'] = item.originalItem.dial_code;
		param['mobNumber'] = $("#mobNumber").val();
		param['hireDate'] = $("#hireDate").val();


		var personalDtls = {};
		personalDtls['empPersonalId'] = $("#empPersonalId").val();
		personalDtls['personalEmailId'] = $("#personalEmailId").val();
		personalDtls['birthDate'] = $("#dobDate").val();
		personalDtls['adharNo'] = $("#adharCardNo").val();
		personalDtls['panNo'] = $("#panCardNo").val();
		personalDtls['permanentAddress'] = $("#permanentAddress").val();
		personalDtls['altMobNumber'] = $("#altMobNumber").val();
		param['empPersonalDetails'] = personalDtls;
		var roles = [];
		var roleItems = $("#roles").jqxComboBox('getCheckedItems');
		if (roleItems.length > 0) {

			console.log(roleItems);
			for (var i = 0; i < roleItems.length; i++) {
				var itemObj = { id: roleItems[i].originalItem.id, roleName: roleItems[i].originalItem.roleName, roleDesc: roleItems[i].originalItem.roleDesc, active: roleItems[i].originalItem.active };
				roles.push(itemObj);
			}


		} else {
			alert("Please select roles");
			return;
		}
		param['roles'] = roles;
		param['active'] = $('#empActive').is(":checked");
		saveUpdEmployees(param);

	}

	function saveUpdEmployees(param) {

		console.log(param);
		$.ajax({
			url: "/admin/addUpdEmployee", type: 'POST', dataType: 'json',
			contentType: "application/json",
			data: JSON.stringify(param),
			error: function(xhr, status, error) {
				console.log(error);
				$('#addUpdateEmployeesModal').modal('hide');
			},
			success: function(data) {
				$('#addUpdateEmployeesModal').modal('hide');
				findEmpById(param['employeeOrgId']);

			}
		});

	}



	function getDesignations() {

		$.ajax({
			url: "/admin/getActiveDesignations", type: 'GET', dataType: 'json',
			contentType: "application/json",
			data: JSON.stringify(),
			error: function(xhr, status, error) {
				console.log(error);

			},
			success: function(data) {

				initDesgCombobox(data);

			}
		});
	}

	function initDesgCombobox(designations) {

		var desgsource =
		{

			datafields: [
				{ name: 'designationName' },
				{ name: 'id' }
			],
			localdata: designations,
			datatype: "array"
		};
		var dataAdapterDesg = new $.jqx.dataAdapter(desgsource);
		$("#designation").jqxComboBox({ width: '100%', height: '35', selectedIndex: 0, source: dataAdapterDesg, displayMember: "designationName", valueMember: "id", width: 200, height: 30, });


	}

	function getEmpRoles() {

		$.ajax({
			url: "/admin/getActiveRoles", type: 'GET', dataType: 'json',
			contentType: "application/json",
			error: function(xhr, status, error) {
				console.log(error);

			},
			success: function(data) {

				initializeRolesCombobox(data);

			}
		});
	}

	function initializeRolesCombobox(roles) {

		var desgsource =
		{

			datafields: [
				{ name: 'roleName' },
				{ name: 'roleDesc' },
				{ name: 'id' },
				{ name: 'active' },
				{ name: 'createdOn' },
				{ name: 'modifiedBy' },
				{ name: 'modifiedDate' }
			],
			localdata: roles,
			datatype: "array"
		};
		var dataAdapterDesg = new $.jqx.dataAdapter(desgsource);
		$("#roles").jqxComboBox({ checkboxes: true, width: '100%', height: '35', selectedIndex: 0, source: dataAdapterDesg, displayMember: "roleName", valueMember: "id", width: 200, height: 30, });


	}

	$("#findEmp").click(function() {

		var arr = ['filterEmpId'];
		if (validateFields(arr)) {

			var empId = $("#filterEmpId").val();
			findEmpById(empId);

		} else {

			alert("Please enter filter data");
		}



	});


	function findEmpById(empId) {


		$.ajax({
			url: "/admin/getEmployee/" + empId, type: 'GET', dataType: 'json',
			contentType: "application/json",
			error: function(xhr, status, error) {
				console.log(error);

			},
			success: function(data) {

				initializeEmployeesGrid(data);

			}
		});
	}

	$('#employee_grid').on('rowdoubleclick', function(event) {

		var rowIndex = event.args.rowindex;
		var dataRow = $('#employee_grid').jqxGrid('getrowdata', rowIndex);
		console.log(dataRow);
		console.log(dataRow.empPersonalDetails.birthDate);
		$('#addUpdateEmployeesModal').modal('show');
		$("#employeeOrgId").val(dataRow.employeeOrgId);
		$("#employeeFullName").val(dataRow.employeeFullName);
		$("#emailId").val(dataRow.emailId);
		$("#mobNumber").val(dataRow.mobNumber);
		$("#hireDate").val(dataRow.hireDate);
		$("#personalEmailId").val(dataRow.empPersonalDetails.personalEmailId);
		$("#dobDate").val(dataRow.empPersonalDetails.birthDate);
		$("#adharCardNo").val(dataRow.empPersonalDetails.adharNo);
		$("#panCardNo").val(dataRow.empPersonalDetails.panNo);
		$("#permanentAddress").val(dataRow.empPersonalDetails.permanentAddress);
		$("#altMobNumber").val(dataRow.empPersonalDetails.altMobNumber);
		$("#designation").jqxComboBox('selectItem', dataRow.designationId);
		$("#countryCode").jqxComboBox('selectItem', dataRow.countryCode);
		var roles = dataRow.roles;
		for (var i = 0; i < roles.length; i++) {
			$("#roles").jqxComboBox('checkItem', roles[i].id);

		}
		if (dataRow.active) {
			$("#empActive").prop('checked', true);
		} else {
			$("#empActive").prop('checked', false);
		}
		$("#empactiveCheckbox").css('display', 'block');
		$("#employeeId").val(dataRow.employeeId);
		$("#empPersonalId").val(dataRow.empPersonalDetails.empPersonalId);

	});



});