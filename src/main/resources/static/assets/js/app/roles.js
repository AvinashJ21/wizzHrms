$(document).ready(function() {


	getRoles();

	function initializeRolesGrid(data) {
		var source =
		{
			localdata: data,
			datatype: "array",
			datafields: [
				{ name: 'id', type: 'int' },
				{ name: 'roleName', type: 'string' },
				{ name: 'roleDesc', type: 'string' },
				{ name: 'createdOn', type: 'date' },
				{ name: 'modifiedDate', type: 'date' },
				{ name: 'modifiedBy', type: 'string' },
				{ name: 'active', type: 'boolean' }
			]
		};
		var dataAdapter = new $.jqx.dataAdapter(source);
		var gridHeight = $(window).height() * 0.90;


		// initialize jqxGrid
		$("#roles_grid").jqxGrid(
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
					{ text: 'Role Name', datafield: 'roleName', width: '20%' },
					{ text: 'Role Description', datafield: 'roleDesc', width: '20%' },
					{ text: 'Created Date', datafield: 'createdOn', width: '20%' },
					{ text: 'Modified Date', datafield: 'modifiedDate', width: '20%' },
					{ text: 'Modified By', datafield: 'modifiedBy' },
					{
						text: 'Active',pinned:true, datafield: 'active', width: '5%', cellsrenderer: function(row, dataField, value, html, column, data) {

							if (value)
								return '<div style="align-items: center;justify-content: center; height: 100%; display: flex;"><img style="width: 35px; height: 20px;" src="../assets//images/active.png"/></div>';
							else
								return '<div style="align-items: center;justify-content: center; height: 100%; display: flex;"><img style="width: 20px; height: 20px;" src="../assets//images/inactive.png"/></div>';
						}

					}
					

				]
			});
	}
	$("#addRole").click(function() {

		$("#rolesModal").modal('show');
		$("#rolesForm").trigger('reset');
		$("#addUpdRole").val('Add Role');
		$("#roleHeader").html('Add Role');
		$("#rolesActiveChkBox").css('display', 'none');

	});

	$("#addUpdRole").click(function() {

		var param = {};
		param['roleName'] = $("#roleName").val();
		param['roleDesc'] = $("#roleDesc").val();
		param['id'] = $("#id").val();
		param['active'] = $('#roleActive').is(":checked");
		var arr = ['roleName', 'roleDesc'];
		if (validateFields(arr)) {

			saveUpdRole(param);
		} else {

			alert("Please enter all the fields");
		}


	});

	function saveUpdRole(param) {

		console.log(param);
		$.ajax({
			url: "/admin/addUpdRole", type: 'POST', dataType: 'json',
			contentType: "application/json",
			data: JSON.stringify(param),
			error: function(xhr, status, error) {
				console.log(error);
				$('#rolesModal').modal('hide');
			},
			success: function(data) {
				$('#rolesModal').modal('hide');
				getRoles();

			}
		});


	}

	function getRoles() {


		$.ajax({
			url: "/admin/getRoles", type: 'GET', dataType: 'json',
			contentType: "application/json",
			error: function(xhr, status, error) {
				console.log(error);
				$('#rolesModal').modal('hide');
			},
			success: function(data) {
				$('#rolesModal').modal('hide');
				initializeRolesGrid(data);

			}
		});


	}
	$('#roles_grid').on('rowdoubleclick', function(event) {
		
		var rowIndex = event.args.rowindex;
		var dataRow = $('#roles_grid').jqxGrid('getrowdata', rowIndex);
		console.log(dataRow);
		$('#rolesModal').modal('show');
		$("#roleName").val(dataRow.roleName);
		$("#roleDesc").val(dataRow.roleDesc);
		$("#id").val(dataRow.id);
		$("#addUpdRole").val('Update Role');
		$("#roleHeader").html('Update Role');
		if (dataRow.active) {
			$("#roleActive").prop('checked', true);
		} else {
			$("#roleActive").prop('checked', false);
		}
		$("#rolesActiveChkBox").css('display', 'block');
	});



});
