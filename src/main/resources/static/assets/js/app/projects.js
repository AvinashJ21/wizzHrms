$(document).ready(function() {

	var statusArr = [{ 'label': 'Client Owned', 'key': 1 }, { 'label': 'Self Owned', 'key': 2 }];
	$("#startDate").jqxDateTimeInput({ width: '200px', height: '30px',value:new Date() });
	$("#endDate").jqxDateTimeInput({ width: '200px', height: '30px',value:new Date()  });

	init();

	function init() {
		initComponents();
		getProjects();
		getActiveTasks();
		//getEmployeesByShortName(["TL","MNGR"]);

	}

	function initComponents() {

		$("#projectType").jqxComboBox({ width: '100%', height: '35', displayMember: "label", valueMember: "label", dropDownHeight: 100, autoDropDownHeight: "auto", placeHolder: "Select", source: statusArr, selectedIndex: 0 });
		//$("#projectManager").jqxComboBox({ width: '100%', height: '35', displayMember: "label", valueMember: "key", dropDownHeight: 100, autoDropDownHeight: "auto", placeHolder: "Select" });
	}
	
	function initializeProjectsGrid(data) {
		var source =
		{
			localdata: data,
			datatype: "array",
			datafields: [
				{ name: 'id', type: 'int' },
				{ name: 'projectName', type: 'string' },
				{ name: 'projectCode', type: 'string' },
				{ name: 'projectType', type: 'string' },
				{ name: 'projectOwner', type: 'string' },
				{ name: 'startDate', type: 'string' },
				{ name: 'endDate', type: 'string' },
				{ name: 'tasks', type: 'array' },
				{ name: 'managerName', type: 'string' },
				{ name: 'createdOn', type: 'date' },
				{ name: 'modifiedDate', type: 'date' },
				{ name: 'modifiedBy', type: 'string' },
				{ name: 'active', type: 'boolean' }
			]
		};
		var dataAdapter = new $.jqx.dataAdapter(source);
		var gridHeight = $(window).height() * 0.90;


		// initialize jqxGrid
		$("#projects_grid").jqxGrid(
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
					{ text: 'Project Name', datafield: 'projectName', width: '20%' },
					{ text: 'Project Code', datafield: 'projectCode', width: '20%' },
					{ text: 'Project Type', datafield: 'projectType', width: '20%' },
					{ text: 'Project Owner', datafield: 'projectOwner', width: '20%' },
					{ text: 'Start Date', datafield: 'startDate', width: '20%' },
					{ text: 'End Date', datafield: 'endDate', width: '20%' },
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


	$('#startDate').on('change', function(event) {
		var endDate = event.args.date;
		$('#endDate').jqxDateTimeInput({ min: endDate });

	});

	$("#addProject").click(function() {

		$("#projectsModal").modal('show');
		$("#projectsForm").trigger('reset');
		$("#projectsActiveChkBox").css('display', 'none');
		$("#tasks").jqxComboBox('uncheckAll'); 


	});

	$("#addUpdproject").click(function() {

		var param = {};
		param['projectName'] = $("#projectName").val();
		param['projectCode'] = $("#projectCode").val();
		param['projectType'] = $("#projectType").val();
		param['projectOwner'] = $("#projectOwner").val();
		param['startDate'] = $("#startDate").val();
		param['endDate'] = $("#endDate").val();
		param['id'] = $("#id").val();
		param['active'] = $('#projectActive').is(":checked");
//		var manager = $("#projectManager").jqxComboBox('getSelectedItem');
//		console.log(manager);
//		param['managerId'] = manager.originalItem.employeeId;
//		param['managerOrgId'] = manager.originalItem.employeeOrgId;
//		param['managerName'] = manager.originalItem.employeeFullName;
		var tasks = [];
		var taskItems = $("#tasks").jqxComboBox('getCheckedItems');
		if (taskItems.length > 0) {

			console.log(taskItems);
			for (var i = 0; i < taskItems.length; i++) {
				var itemObj = { id: taskItems[i].originalItem.id, taskName: taskItems[i].originalItem.taskName, taskDesc: taskItems[i].originalItem.taskDesc, active: taskItems[i].originalItem.active };
				tasks.push(itemObj);
			}


		} else {
			alert("Please select tasks");
			return;
		}
		param['tasks'] = tasks;
		
//		console.log(param);
		var arr = ['projectName', 'projectCode', 'projectType', 'projectOwner', 'startDate', 'endDate'];
		if (validateFields(arr)) {

			saveUpdProject(param);
		} else {

			alert("Please enter all the fields");
		}


	});

	function saveUpdProject(param) {

		console.log(param);
		$.ajax({
			url: "/admin/addUpdProject", type: 'POST', dataType: 'json',
			contentType: "application/json",
			data: JSON.stringify(param),
			error: function(xhr, status, error) {
				console.log(error);
				$('#projectsModal').modal('hide');
			},
			success: function(data) {
				$('#projectsModal').modal('hide');
				getProjects();

			}
		});

	}
	
	function getProjects(){
		
		$.ajax({
			url: "/admin/getProjects", type: 'GET', dataType: 'json',
			contentType: "application/json",
			error: function(xhr, status, error) {
				console.log(error);
				$('#projectsModal').modal('hide');
			},
			success: function(data) {
				$('#projectsModal').modal('hide');
				initializeProjectsGrid(data);

			}
		});
		
	}
	
	$('#projects_grid').on('rowdoubleclick', function(event) {
		
		var rowIndex = event.args.rowindex;
		var dataRow = $('#projects_grid').jqxGrid('getrowdata', rowIndex);
		console.log(dataRow);
		$('#projectsModal').modal('show');
		$("#projectName").val(dataRow.projectName);
		$("#projectCode").val(dataRow.projectCode);
		$("#projectOwner").val(dataRow.projectOwner);
		$("#id").val(dataRow.id);
		$("#startDate").val(dataRow.startDate);
		$("#endDate").val(dataRow.endDate);
		$("#projectType").jqxComboBox('selectItem', dataRow.projectType ); 
		//$("#projectManager").jqxComboBox('selectItem', dataRow.managerId);
		var tasks = dataRow.tasks;
		for (var i = 0; i < tasks.length; i++) {
			$("#tasks").jqxComboBox('checkItem', tasks[i].id);

		}
		
		$("#addUpdproject").val('Update Project');
		$("#projectHeader").html('Update Project');
		if (dataRow.active) {
			$("#projectActive").prop('checked', true);
		} else {
			$("#projectActive").prop('checked', false);
		}
		$("#projectsActiveChkBox").css('display', 'block');
	});
	
	function getActiveTasks(){
		
		$.ajax({
			url: "/admin/getActiveTasks", type: 'GET', dataType: 'json',
			contentType: "application/json",
			error: function(xhr, status, error) {
				console.log(error);
				 
			},
			success: function(data) {
				initializeTasksCombo(data);

			}
		});
		
	}
	
	function initializeTasksCombo(data) {

		var desgsource =
		{

			datafields: [
				{ name: 'taskName' },
				{ name: 'taskDesc' },
				{ name: 'id' },
				{ name: 'active' },
				{ name: 'createdOn' },
				{ name: 'modifiedBy' },
				{ name: 'modifiedDate' }
			],
			localdata: data,
			datatype: "array"
		};
		var dataAdapterTasks = new $.jqx.dataAdapter(desgsource);
		$("#tasks").jqxComboBox({ checkboxes: true, width: '100%', height: '35', selectedIndex: 0, source: dataAdapterTasks, displayMember: "taskName", valueMember: "id", width: 200, height: 30, });


	}
	
	/*function getEmployeesByShortName(param){
		
		$.ajax({
			url: "/admin/getEmployeesByShortName", type: 'POST', dataType: 'json',
			contentType: "application/json",
			data: JSON.stringify(param),
			error: function(xhr, status, error) {
				console.log(error);
				 
			},
			success: function(data) {
				console.log("EMPLOYEEEES");
				console.log(data);
				initializeManagerCombo(data);

			}
		});
		
	}
	
	function initializeManagerCombo(data) {

		var desgsource =
		{

			datafields: [
				{ name: 'employeeFullName' },
				{ name: 'employeeOrgId' },
				{ name: 'employeeId' }
			],
			localdata: data,
			datatype: "array"
		};
		var dataAdapterTasks = new $.jqx.dataAdapter(desgsource);
		$("#projectManager").jqxComboBox({ width: '100%', height: '35', selectedIndex: 0, source: dataAdapterTasks, displayMember: "employeeFullName", valueMember: "employeeId", width: 200, height: 30, });


	}*/

});