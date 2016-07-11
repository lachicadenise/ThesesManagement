<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/css/bootstrap.min.css">
<script type="text/javascript" src="scripts/jquery-3.0.0.min.js"></script>
<script type="text/javascript" src="scripts/bootstrap.min.js"></script>
<style type="text/css">
	.table > tbody > tr > td {
		vertical-align: middle;
	}
</style>
<title>Theses Management</title>
</head>
<body>
	<div class="col-lg-12">
		<h2>User Accounts</h2>
		<br />
	</div>
	<div class="col-md-4">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="Search...">
			<span class="input-group-btn">
				<button type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-search"></span>
					Go
			    </button>
			</span>
		</div>
	</div>
	<div class="col-md-8">
		<button type="button" id="btnCreate" class="btn btn-primary pull-right">
			New User Account
		</button>
	</div>
	<div class='col-md-12 container'>
		<br />
		<table class='table table-bordered' id='tblUserAccounts'>
			<thead>
				<tr>
					<th class='col-md-3'>Username</th>
					<th class='col-md-6'>Name</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
	<div class='container'>
		<ul id="pageControl" class="pagination-sm"></ul>
	</div>
</body>
<script type="text/javascript">

	$(document).ready(function(){
		
		$('#tblUserAccounts').on('click', '.actionEdit', function(event) {
			var employeeId = $(this).attr('data-employeeId');
			alert('Edit ' + employeeId);
		});
		
		$('#tblUserAccounts').on('click', '.actionDelete', function(event) {
			var employeeId = $(this).attr('data-employeeId');
			alert('Delete ' + employeeId);
		});
		
		$.ajax({
			type: 'GET',
			url: '/ThesesManagement/rest/userAccounts/get?searchValue=&from=1&limit=10',
			success: function(data){
				$.each(data, function(index, item){
					var id = item.id;
					var btnEdit = '';
					btnEdit += '<button type="button" class="btn btn-default actionEdit" data-employeeId="' + id + '">';
					btnEdit += 'Edit';
					btnEdit += '</button>';
					var btnDelete = '';
					btnEdit += '<button type="button" class="btn btn-danger actionDelete" data-employeeId="' + id + '">';
					btnEdit += 'Delete';
					btnEdit += '</button>';
					var name = 
						item.lastname + ', ' + 
						item.firstname + ' ' + 
						item.middlename;
					$('#tblUserAccounts tbody').append(
					'<tr>' + 
						'<td>' + 
							item.username +
						'</td>' +
						'<td>' + 
							name + 
						'</td>' + 
						'<td>' + 
							'<div class="btn-group">' + 
								btnEdit + 
								btnDelete + 
							'</div>' + 
						'</td>' + 
					'</tr>');
				});
				
			},
			error: function(jqXHR, textStatus, errorThrown){
				
			}
		});
		
		$('.actionButton').click(function(event){
			alert('joshua');
		});
		
	});
	
</script>
</html>