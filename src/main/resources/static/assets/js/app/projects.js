$(document).ready(function() {

	var statusArr = [{ 'label': 'Client Owned', 'key': 1 }, { 'label': 'Self Owned', 'key': 2 }];
	$("#startDate").jqxDateTimeInput({ width: '200px', height: '30px',value:new Date() });
	$("#endDate").jqxDateTimeInput({ width: '200px', height: '30px',value:new Date()  });

	init();

	function init() {
		initComponents();
		getProjects();

	}

	function initComponents() {

		$("#projectType").jqxDropDownList({ width: '100%', height: '35', displayMember: "label", valueMember: "label", dropDownHeight: 100, autoDropDownHeight: "auto", placeHolder: "Select", source: statusArr, selectedIndex: 0 });
		$("#projectManager").jqxDropDownList({ width: '100%', height: '35', displayMember: "label", valueMember: "key", dropDownHeight: 100, autoDropDownHeight: "auto", placeHolder: "Select" });
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
		$("#addUpdproject").val('Update Project');
		$("#projectHeader").html('Update Project');
		if (dataRow.active) {
			$("#projectActive").prop('checked', true);
		} else {
			$("#projectActive").prop('checked', false);
		}
		$("#projectsActiveChkBox").css('display', 'block');
	});

});