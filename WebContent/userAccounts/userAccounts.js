$(document).ready(function(){
	
	$('#btnCreateUserAccount').on('click', function(){
		setUserAccountFormContent(null);
		setUserAccountFormMessage('');
		setUserAccountFormVisible(true);
	});
	
	$('#btnCloseUserAccountForm').on('click', function(){
		setUserAccountFormVisible(false);
	});
	
	$('#btnSubmitUserAccount').on('click', function(){
		createUserAccount();
	});
	
	$('#btnCloseMessageModal').on('click', function(){
		setMessageModalVisible(false);
		setMessageModalContent('', '');
	});
	
	function setMessageModalContent(title, message){
		$('#simpleMessageModal div div .modal-header .modal-title').text(title);
		$('#simpleMessageModal div div .modal-body p').text(message);
	}
	
	function setMessageModalVisible(visible){
		if(visible){
			$('#simpleMessageModal').fadeIn();
		}else{
			$('#simpleMessageModal').fadeOut();
		}
	}
	
	function setUserAccountFormContent(userAccount){
		$('#userAccountsForm div div .modal-header h4').text((userAccount == null ? "Create" : "Update") + " New User Account");
		$('#userAccountsForm div div .modal-body').children('div').each(function(){
			$(this).removeClass('has-error').removeClass('has-success');
			$('input', this).val('');
		});
		setUserAccountFormMessage('');
		if(userAccount != null){
			$('#username').val(userAccount.username);
			$('#lastname').val(userAccount.lastname);
			$('#firstname').val(userAccount.firstname);
			$('#middlename').val(userAccount.midlename);
		}
	}
	
	function setUserAccountFormVisible(visible){
		if(visible){
			$('#userAccountsForm').fadeIn();
		}else{
			$('#userAccountsForm').fadeOut();
		}
	}
	
	function setUserAccountFormMessage(message){
		$('#userAccountsForm div div .modal-footer span').text(message);
	}
	
	function createUserAccount(){
		
		function validateField(elementId){
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
			if(validateField(item)){
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
			
			if(userAccount.password == $('#confirmPassword').val()){
				
				userAccount.password = CryptoJS.MD5(userAccount.password).toString();
				
				$.ajax({
					type: 'GET',
					url: '/ThesesManagement/rest/userAccounts/usernameExists?username=' + userAccount.username,
					success: function(data){
						
						if(!data.usernameExists){
							
							$.ajax({
								type: 'POST',
								contentType: 'application/json',
								url: '/ThesesManagement/rest/userAccounts/create',
								data: JSON.stringify(userAccount),
								success: function(data){
									setUserAccountFormContent(null);
									setUserAccountFormVisible(false);
									setMessageModalContent('User Accounts', 'User Account successfully created.');
									setMessageModalVisible(true);
								},
								error: function(jqXHR, textStatus, errorThrown){
									setUserAccountFormMessage('Server error occurred.');
								}
							});
							
						} else {
							setUserAccountFormMessage('Username already exists');
							$('#username').parent().removeClass('has-success').addClass('has-error');
						}
					},
					error: function(jqXHR, textStatus, errorThrown){
						setUserAccountFormMessage('Server error occurred.');
					}
				});
				
			} else {
				$('#password').parent().removeClass('has-success').addClass('has-error');
				$('#confirmPassword').parent().removeClass('has-success').addClass('has-error');
				setUserAccountFormMessage('Passwords don\'t match');
			}
			
		}else{
			setUserAccountFormMessage('Fill-up all required fields');
		}
		
	}

	$('#btnSearch').on('click', function(){
		loadUserAccounts(1);
	});
	
	function loadUserAccounts(startingNumber){
		
		$.ajax({
			type: 'GET',
			url: '/ThesesManagement/rest/userAccounts/count?searchValue=' + $('#txtSearchValue').val(),
			success: function(data){
				
				
				
			},
			error: function(jqXHR, textStatus, errorThrown){
				setUserAccountFormMessage('Server error occurred.');
			}
		});
		
	}
	
});

