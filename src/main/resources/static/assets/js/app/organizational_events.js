$(document).ready(function() {
	$("#fromDate").jqxDateTimeInput({ width: '200px', height: '30px' });
	$("#toDate").jqxDateTimeInput({ width: '200px', height: '30px' });


	getEvents();

	function initializeEventsGrid(data) {
		var source =
		{
			localdata: data,
			datatype: "array",
			datafields: [
				{ name: 'id', type: 'int' },
				{ name: 'eventName', type: 'string' },
				{ name: 'eventDesc', type: 'string' },
				{ name: 'fromDate', type: 'string' },
				{ name: 'toDate', type: 'string' },
				{ name: 'inviteLink', type: 'string' },
				{ name: 'active', type: 'bool' },
				{ name: 'imageName', type: 'string' },
				{ name: 'createdOn', type: 'date' }
			]
		};
		var dataAdapter = new $.jqx.dataAdapter(source);
		var gridHeight = $(window).height() * 0.90;


		// initialize jqxGrid
		$("#event_grid").jqxGrid(
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
					{ text: 'Event Name', datafield: 'eventName', width: '20%' },
					{ text: 'Event Description', datafield: 'eventDesc', width: '25%' },
					{ text: 'From Date', datafield: 'fromDate', width: '10%' },
					{ text: 'To Date', datafield: 'toDate', width: '10%' },
					{ text: 'Invite Link', datafield: 'inviteLink', width: '15%' },
					{ text: 'Invitation Card', datafield: 'imageName', width: '15%' },
					{ text: 'Created On', datafield: 'createdOn', width: '15%' },
					{
						text: 'Active', datafield: 'active', width: '5%', cellsrenderer: function(row, dataField, value, html, column, data) {

							if (value)
								return '<div style="align-items: center;justify-content: center; height: 100%; display: flex;"><img style="width: 35px; height: 20px;" src="../assets//images/active.png"/></div>';
							else
								return '<div style="align-items: center;justify-content: center; height: 100%; display: flex;"><img style="width: 20px; height: 20px;" src="../assets//images/inactive.png"/></div>';
						}

					}



				]
			});
	}

	function getEvents() {

		$.ajax({
			url: "/admin/organizationalEventsData", type: 'GET', dataType: 'json',
			contentType: "application/json",
			success: function(data, textStatus, jqxHR) {
				if (data) {


					initializeEventsGrid(data);
					$('#event_grid').jqxGrid({ selectedrowindex: -1 });

				}

			},
			error: function(jqXHR, textStatus, errorThrown) {

			}
		});

	}

	$('#fromDate').on('change', function(event) {
		var toDate = event.args.date;
		$('#toDate').jqxDateTimeInput({ min: toDate });

	});

	$("#addEvent").click(function() {
		$("#addUpdateEvents").modal('show');
		$("#orgEventsForm").trigger('reset');
		$("#activeCheckbox").css('visibility', 'hidden');
		$("#addUpdEvent").val('Add Event');
		$("#header").html('Add Event');

	});


	$("#addUpdEvent").click(function() {

		var form = document.getElementById('orgEventsForm');
		var formData = new FormData(form);
		formData.append('fromDate', $('#fromDate').val());
		formData.append('toDate', $('#toDate').val());
		$.ajax({
			url: "/admin/addOrganizationalEvents",
			type: 'POST',
			data: formData,
			async: false,
			cache: false,
			contentType: false,
			enctype: 'multipart/form-data',
			processData: false,
			success: function(respdata, textStatus, jqxHR) {
				$('#addUpdateEvents').modal('hide');

				if (respdata) {

					getEvents();
				}


			},
			error: function(jqXHR, textStatus, errorThrown) {



			}
		});

	});

	$('#event_grid').on('rowdoubleclick', function(event) {

		var rowIndex = event.args.rowindex;
		var dataRow = $('#event_grid').jqxGrid('getrowdata', rowIndex);
		console.log(dataRow);
		$('#addUpdateEvents').modal('show');
		$("#eventName").val(dataRow.eventName);
		$("#eventDesc").val(dataRow.eventDesc);
		$("#fromDate").val(dataRow.fromDate);
		$("#toDate").val(dataRow.toDate);
		$("#inviteLink").val(dataRow.inviteLink);
		$("#imageName").val(dataRow.imageName);
		$("#id").val(dataRow.id);
		$("#addUpdEvent").val('Update Event');
		$("#header").html('Update Event');
		if (dataRow.active) {
			$("#active").prop('checked', true);
		} else {
			$("#active").prop('checked', false);
		}
		$("#activeCheckbox").css('visibility', 'visible');

	});



});