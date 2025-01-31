$(document).ready(function() {


	getTasks();
	function initializeTasksGrid(data) {
		var source =
		{
			localdata: data,
			datatype: "array",
			datafields: [
				{ name: 'id', type: 'int' },
				{ name: 'taskName', type: 'string' },
				{ name: 'taskDesc', type: 'string' },
				{ name: 'createdOn', type: 'date' },
				{ name: 'modifiedDate', type: 'date' },
				{ name: 'modifiedBy', type: 'string' },
				{ name: 'active', type: 'boolean' }
			]
		};
		var dataAdapter = new $.jqx.dataAdapter(source);
		var gridHeight = $(window).height() * 0.90;


		// initialize jqxGrid
		$("#tasks_grid").jqxGrid(
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
					{ text: 'Task Name', datafield: 'taskName', width: '20%' },
					{ text: 'Task Description', datafield: 'taskDesc', width: '20%' },
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

	$("#addTask").click(function() {

		$("#tasksModal").modal('show');
		$("#tasksForm").trigger('reset');
		$("#addUpdTasks").val('Add Task');
		$("#taskHeader").html('Add Task');
		$("#tasksActiveChkBox").css('display', 'none');

	});

	$("#addUpdTask").click(function() {

		var param = {};
		param['taskName'] = $("#taskName").val();
		param['taskDesc'] = $("#taskDesc").val();
		param['id'] = $("#taskid").val();
		param['active'] = $('#taskActive').is(":checked");
		var arr = ['taskName', 'taskDesc'];
		if (validateFields(arr)) {

			saveUpdTask(param);
		} else {

			alert("Please enter all the fields");
		}


	});

	function saveUpdTask(param) {

		console.log(param);
		$.ajax({
			url: "/admin/saveUpdTasks", type: 'POST', dataType: 'json',
			contentType: "application/json",
			data: JSON.stringify(param),
			error: function(xhr, status, error) {
				console.log(error);
				$('#tasksModal').modal('hide');
			},
			success: function(data) {
				$('#tasksModal').modal('hide');
				getTasks();
			}
		});


	}

	function getTasks() {


		$.ajax({
			url: "/admin/getTasks", type: 'GET', dataType: 'json',
			contentType: "application/json",
			error: function(xhr, status, error) {
				console.log(error);
				$('#tasksModal').modal('hide');
			},
			success: function(data) {
				console.log(data);
				$('#tasksModal').modal('hide');
				initializeTasksGrid(data);

			}
		});
	}
	
	$('#tasks_grid').on('rowdoubleclick', function(event) {
		
		var rowIndex = event.args.rowindex;
		var dataRow = $('#tasks_grid').jqxGrid('getrowdata', rowIndex);
		console.log(dataRow);
		$('#tasksModal').modal('show');
		$("#taskName").val(dataRow.taskName);
		$("#taskDesc").val(dataRow.taskDesc);
		$("#taskid").val(dataRow.id);
		$("#addUpdTask").val('Update Task');
		$("#taskHeader").html('Update Task');
		if (dataRow.active) {
			$("#taskActive").prop('checked', true);
		} else {
			$("#taskActive").prop('checked', false);
		}
		$("#tasksActiveChkBox").css('display', 'block');
	});

});