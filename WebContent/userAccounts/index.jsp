<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="../plugins/jquery-3.0.0.min.js"></script>
<script type="text/javascript" src="../plugins/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
<script type='text/javascript' src='../plugins/CryptoJS-master/rollups/md5.js'></script>
<link rel="stylesheet" href="../plugins/bootstrap-3.3.6-dist/css/bootstrap.min.css">
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
	<div class="col-md-3">
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
	<div class="col-md-9">
		<button type="button" id="btnCreateUserAccount" class="btn btn-primary">
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

	<div class='modal' id='messageModal' role='dialog'>
		<div class='modal-dialog'>
			<div class='modal-content'>
				<div class='modal-body'>
					<span>User account created</span>
				</div>
				<div class='modal-footer'>
					<button type="button" class="btn btn-default">Create</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal" id="userAccountsForm" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content" id='form_userAccountsContent'>
				<div class="modal-header ">
					<h4></h4>
				</div>
				<div class="modal-body">
					<div class='form-group'>
						<label for='username'>Username</label>
						<input type='text' class='form-control' id='username' />
					</div>
					<div class='form-group'>
						<label for='password'>Password</label>
						<input type='password' class='form-control' id='password' />
					</div>
					<div class='form-group'>
						<label for='confirmPassword'>Confirm Password</label>
						<input type='password' class='form-control' id='confirmPassword' />
					</div>
					<div class='form-group'>
						<label for='lastname'>Lastname</label>
						<input type='text' class='form-control' id='lastname' />
					</div>
					<div class='form-group'>
						<label for='firstname'>Firstname</label>
						<input type='text' class='form-control' id='firstname' required='true'/>
					</div>
					<div class='form-group'>
						<label for='middlename'>Middlename</label>
						<input type='text' class='form-control' id='middlename' />
					</div>
				</div>
				<div class="modal-footer">
					<span class='pull-left'></span>
					<button type="button" class="btn btn-primary">Create</button>
					<button type="button" class="btn btn-danger">Cancel</button>
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript" src='script.js'></script>

</html>