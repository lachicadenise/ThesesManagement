<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="plugins/jquery-3.0.0.min.js"></script>
<script type="text/javascript" src="plugins/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
<script type='text/javascript' src='plugins/CryptoJS-master/rollups/md5.js'></script>
<link rel="stylesheet" href="plugins/bootstrap-3.3.6-dist/css/bootstrap.min.css">

<title>Theses Management</title>
</head>
<body>
	
	<div class="container-fluid">
		
		<div class="row">
			
			<!-- side bar -->
			<div class="col-md-2 well">
				<h3 class="text-info">
					Theses <br />
					Management
				</h3>
				<br />
				<p>
					<small>
						Logged in as:
					</small>
				</p>
				<p>
					<strong>Zabala, Joshua Perez</strong>
				</p>
				<br />
				<div class="form-group">
					<div class="list-group">
						<a href="#" class="list-group-item">Home<i class="glyphicon glyphicon-home pull-right"></i></a>
						<a href="#theses" class="list-group-item">Theses<i class="glyphicon glyphicon-book pull-right"></i></a>
						<a href="#authors" class="list-group-item">Authors<i class="glyphicon glyphicon-pencil pull-right"></i></a>
						<a href="#advisers" class="list-group-item">Advisers<i class="glyphicon glyphicon-user pull-right"></i></a>
						<a href="#tags" class="list-group-item">Tags<i class="glyphicon glyphicon-tags pull-right"></i></a>
						<a href="#userAccounts" class="list-group-item">User Accounts<i class="glyphicon glyphicon-lock pull-right"></i></a>
					</div>
				</div>
				<div class="form-group">
					<button class="btn btn-default form-control">Log Out&nbsp;<span class="glyphicon glyphicon-log-out"></span></button>
				</div>
			</div>
			
			<!-- contents -->
			<div class="col-md-10 well">
				<p>Contents</p>
			</div>
			
		</div>
			
	</div>
	
</body>
</html>