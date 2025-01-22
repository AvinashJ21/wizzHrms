$(document).ready(function() {

	var url = "../assets/AllDesignations.json";
	var source =
	{
		datatype: "json",
		datafields: [
			{ name: 'lbl' },
			{ name: 'lblDesc' }
		],
		url: url,
		async: false
	};

	var dataAdapter = new $.jqx.dataAdapter(source);

	// Create a jqxComboBox
	$("#designationName").jqxComboBox({
		width: '100%', height: '35', selectedIndex: 0, source: dataAdapter, displayMember: "lblDesc", valueMember: "lbl", width: 200, height: 30,
		renderer: function(index, label, value) {
			var table = '<table style="min-width: 150px;"><tr><td>' + value + '</td></tr><tr><td>' + label + '</td></tr></table>';
			return table;
		}
	});

	getDesignations();
	function initializeDesignationGrid(data) {
		var source =
		{
			localdata: data,
			datatype: "array",
			datafields: [
				{ name: 'id', type: 'int' },
				{ name: 'designationName', type: 'string' },
				{ name: 'designationShortName', type: 'string' },
				{ name: 'designationDesc', type: 'string' },
				{ name: 'createdOn', type: 'date' },
				{ name: 'modifiedDate', type: 'date' },
				{ name: 'modifiedBy', type: 'string' },
				{ name: 'active', type: 'boolean' }
			]
		};
		var dataAdapter = new $.jqx.dataAdapter(source);
		var gridHeight = $(window).height() * 0.90;


		// initialize jqxGrid
		$("#designation_grid").jqxGrid(
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
					{ text: 'Designation Name', datafield: 'designationName', width: '20%' },
					{ text: 'Designation Short Name', datafield: 'designationShortName', width: '20%' },
					{ text: 'Designation Description', datafield: 'designationDesc', width: '20%' },
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

	$("#addDesignation").click(function() {

		$("#designationModal").modal('show');
		$("#designationForm").trigger('reset');
		$("#addUpdDesignation").val('Add Designation');
		$("#designationHeader").html('Add Designation');
		$("#designationActiveChkBox").css('display', 'none');

	});


	$("#addUpdDesignation").click(function() {

		var param = {};
		var desgItem = $("#designationName").jqxComboBox('getSelectedItem');
		param['designationName'] = desgItem.originalItem.lblDesc;
		param['designationShortName'] = desgItem.originalItem.lbl;
		param['designationDesc'] = $("#designationDesc").val();
		param['id'] = $("#desgid").val();
		param['active'] = $('#designationActive').is(":checked");
		var arr = ['designationName', 'designationDesc'];
		if (validateFields(arr)) {
			saveUpdDesignation(param);
		} else {

			alert("Please enter all the fields");
		}

	});

	function saveUpdDesignation(param) {

		console.log(param);
		$.ajax({
			url: "/admin/addUpdDesignation", type: 'POST', dataType: 'json',
			contentType: "application/json",
			data: JSON.stringify(param),
			error: function(xhr, status, error) {
				console.log(error);
				$('#designationModal').modal('hide');
			},
			success: function(data) {
				$('#designationModal').modal('hide');
				getDesignations();

			}
		});
	}

	function getDesignations() {

		$.ajax({
			url: "/admin/getDesignations", type: 'GET', dataType: 'json',
			contentType: "application/json",
			data: JSON.stringify(),
			error: function(xhr, status, error) {
				console.log(error);

			},
			success: function(data) {

				initializeDesignationGrid(data);

			}
		});
	}

	$('#designation_grid').on('rowdoubleclick', function(event) {

		var rowIndex = event.args.rowindex;
		var dataRow = $('#designation_grid').jqxGrid('getrowdata', rowIndex);
		console.log(dataRow);
		$('#designationModal').modal('show');
		$("#designationName").jqxComboBox('selectItem', dataRow.designationShortName);
		$("#designationDesc").val(dataRow.designationDesc);
		$("#desgid").val(dataRow.id);
		$("#addUpdDesignation").val('Update Deignation');
		$("#roleHeader").html('Update Deignation');
		if (dataRow.active) {
			$("#designationActive").prop('checked', true);
		} else {
			$("#designationActive").prop('checked', false);
		}
		$("#designationActiveChkBox").css('display', 'block');
	});

});	