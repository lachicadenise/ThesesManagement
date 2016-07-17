/**
 * 
 */

$(document).ready(function(){
	
	function setUserAccountFormContent(userAccount){
		
		$('#userAccountsForm div div .modal-header h4').text((userAccount == null ? "Create" : "Update") + " New User Account");
		
		$('#userAccountsForm div div .modal-body').children('div').each(function(){
			$(this).removeClass('has-error').removeClass('has-success');
			$('input', this).val('');
		});
		
		setFormMessage('');
		
		if(userAccount != null){
			$('#username').val(userAccount.username);
			$('#lastname').val(userAccount.lastname);
			$('#firstname').val(userAccount.firstname);
			$('#middlename').val(userAccount.midlename);
		}
		
	}
	
	function setFormMessage(message){
		$('#userAccountsForm div div .modal-footer span').text(message);
	}
	
	function create(){
		
		//check if form contains valid field values
		function validateLength(elementId){
			var hasError = false;
			if($(elementId).val().length < 1){
				$(elementId).parent().removeClass('has-success').addClass('has-error');
				hasError = true;
			}else{
				$(elementId).parent().removeClass('has-error').addClass('has-success');
			}
			return hasError;
		}
		var hasError = false;
		var elementIds = ['#username', '#password', '#confirmPassword', '#lastname', '#firstname'];
		$.each(elementIds, function(index, item){
			if(validateLength(item)){
				hasError = true;
			}
		});
		if(!hasError){
		
			var userAccount = {
				username: $('#username').val(),
				password: $('#password').val(),
				lastname: $('#lastname').val(),
				firstname: $('#firstname').val(),
				middlename: $('#middlename').val()
			};
			
			//check if passwords match
			if(userAccount.password == $('#confirmPassword').val()){
				
				//check if username exists
				$.ajax({
					type: 'GET',
					url: '/ThesesManagement/rest/userAccounts/usernameExists?username=' + userAccount.username,
					success: function(data){
						if(data.usernameExists != true){
							
							//send form content
							$.ajax({
								type: 'POST',
								url: '/ThesesManagement/rest/userAccounts/create',
								data: JSON.stringify(userAccount),
								success: function(data){
									
								},
								error: function(jqXHR, textStatus, errorThrown){
									$('#userAccountsForm div div .modal-footer span').text('Server error occured: ' + textStatus);
								}
							});
							
						} else {
							$('#userAccountsForm div div .modal-footer span').text('Username already exists');
							$('#username').parent().removeClass('has-success').addClass('has-error');
						}
					},
					error: function(jqXHR, textStatus, errorThrown){
						setFormMessage('Server error occured: ' + textStatus);
					}
				});
				
			} else {
				$('#password').parent().removeClass('has-success').addClass('has-error');
				$('#confirmPassword').parent().removeClass('has-success').addClass('has-error');
				setFormMessage('Passwords don\'t match');
			}
			
		}else{
			setFormMessage('Fill-up all required fields');
		}
		
	}
	
	function setUserAccountFormVisible(visible){
		if(visible){
			$('#userAccountsForm').fadeIn();
		} else {
			$('#userAccountsForm').fadeOut();	
		}
	}
	
	function usernameExists(username){
		var usernameExists = false;
		$.ajax({
			type: 'POST',
			url: '',
			data: null,
			async: false,
			sucess: function(data){
				usernameExists = data.usernameExists;
			},
			error: function(jqXHR, textStatus, errorThrown){
				
			}
		});
		return usernameExists;
	}
	
	$('#userAccountsForm div div .modal-footer .btn-danger').on('click', function(){
		setUserAccountFormVisible(false);
	});
	
	$('#userAccountsForm div div .modal-footer .btn-primary').on('click', function(){
		create();
//		function validateLength(elementId){
//			var hasError = false;
//			if($(elementId).val().length < 1){
//				$(elementId).parent().removeClass('has-success').addClass('has-error');
//				hasError = true;
//			}else{
//				$(elementId).parent().removeClass('has-error').addClass('has-success');
//			}
//			return hasError;
//		}
//		
//		var hasError = false;
//		var elementIds = ['#username', '#password', '#confirmPassword', '#lastname', '#firstname'];
//		$.each(elementIds, function(index, item){
//			if(validateLength(item)){
//				hasError = true;
//			}
//		});
//		
//		$('#txtMiddlename').parent().removeClass('has-error').addClass('has-success');
//		
//		if(hasError){
//			$('#userAccountsForm div div .modal-footer span').text('Fill-up all the required fields');
//		}else{
//			
//			var usernameExists = false;
//			$.ajax({
//				type: 'GET',
//				url: '/ThesesManagement/rest/userAccounts/usernameExists?username=' + $('#username').val(),
//				success: function(data){
//					usernameExists = data.usernameExists;
//				},
//				error: function(jqXHR, textStatus, errorThrown){
//					alert('Checking if username already exists... ' + textStatus);
//				}
//			});
//			alert(usernameExists);
//			if(!usernameExists){
//				
//				var userAccount = {
//						username: $('#username').val(),
//						password: CryptoJS.MD5($('#password').val()).toString(),
//						lastname: $('#lastname').val(),
//						firstname: $('#firstname').val(),
//						middlename: $('#middlename').val()
//				};
//				
//				$.ajax({
//					url: '/ThesesManagement/rest/userAccounts/create',
//					type: 'POST',
//					contentType: 'application/json',
//					data: JSON.stringify(userAccount),
//					dataType: 'json',
//					success: function(data){
//						alert('Done: ' + data.id);
//					},
//					error: function(jqXHR, textStatus, errorThrown){
//						alert(textStatus);
//					}
//				});
//				
//			}else{
//				$('#userAccountsForm div div .modal-footer span').text('Username already exists');
//				$('#username').parent().removeClass('has-success').addClass(has-error);
//			}
//			
//		}
		
	});
	
	$('#btnCreateUserAccount').on('click', function(event){
		setUserAccountFormContent(null);
		setUserAccountFormVisible(true);
	});
	
});

//$(document).ready(function(){
//		
//		$('#tblUserAccounts').on('click', '.actionEdit', function(event) {
//			var employeeId = $(this).attr('data-employeeId');
//			alert('Edit ' + employeeId);
//		});
//		
//		$('#tblUserAccounts').on('click', '.actionDelete', function(event) {
//			var employeeId = $(this).attr('data-employeeId');
//			alert('Delete ' + employeeId);
//		});
//		
//		$.ajax({
//			type: 'GET',
//			url: '/ThesesManagement/rest/userAccounts/get?searchValue=&from=1&limit=10',
//			success: function(data){
//				$.each(data, function(index, item){
//					var id = item.id;
//					var btnEdit = '';
//					btnEdit += '<button type="button" class="btn btn-default actionEdit" data-employeeId="' + id + '">';
//					btnEdit += 'Edit';
//					btnEdit += '</button>';
//					var btnDelete = '';
//					btnEdit += '<button type="button" class="btn btn-danger actionDelete" data-employeeId="' + id + '">';
//					btnEdit += 'Delete';
//					btnEdit += '</button>';
//					var name = 
//						item.lastname + ', ' + 
//						item.firstname + ' ' + 
//						item.middlename;
//					$('#tblUserAccounts tbody').append(
//					'<tr>' + 
//						'<td>' + 
//							item.username +
//						'</td>' +
//						'<td>' + 
//							name + 
//						'</td>' + 
//						'<td>' + 
//							'<div class="btn-group">' + 
//								btnEdit + 
//								btnDelete + 
//							'</div>' + 
//						'</td>' + 
//					'</tr>');
//				});
//				
//			},
//			error: function(jqXHR, textStatus, errorThrown){
//				
//			}
//		});
//		
//		$('.actionButton').click(function(event){
//			alert('joshua');
//		});
//		
//		$('#btnCreate').on('click', function(event){
//			$('#userAccountsForm').fadeIn();
//		});
//		
//		$('#btnCloseModal').on('click', function(event){
//			$('#userAccountsForm').fadeOut();
//		});
//		
//	});