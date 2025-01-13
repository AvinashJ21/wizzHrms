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
				{ name: 'modifiedBy', type: 'string' }
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
					{ text: 'Modified By', datafield: 'modifiedBy' }

				]
			});
	}
	$("#addRole").click(function() {

		$("#rolesModal").modal('show');
		$("#rolesForm").trigger('reset');

	});

	$("#addUpdRole").click(function() {

		var param = {};
		param['roleName'] = $("#roleName").val();
		param['roleDesc'] = $("#roleDesc").val();
		param['id'] = $("#id").val();
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
	});



});
