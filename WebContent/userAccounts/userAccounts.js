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
	});
	
	function setMessageModalContent(title, message){
		$('#messageModal div div .modal-header .modal-title').text(title);
		$('#messageModal div div .modal-body p').text(message);
	}
	
	function setMessageModalVisible(visible){
		if(visible){
			$('#messageModal').fadeIn();
		}else{
			$('#messageModal').fadeOut();
		}
	}
	
	function showMessageModalFast(){
		$('#messageModal').fadeIn().delay(500).fadeOut();
	}
	
	function setUserAccountFormContent(userAccount){
		$('#userAccountsForm div div .modal-header h4').text((userAccount == null ? "New" : "Update") + " User Account");
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
		var elementIds = ['#username', '#lastname', '#firstname'];
		$.each(elementIds, function(index, item){
			if(validateField(item)){
				hasError = true;
			}
		});
		
		if(!hasError){
			
			var userAccount = {
				username: $('#username').val(),
				password: CryptoJS.MD5('d3f_p@ss').toString(),
				lastname: $('#lastname').val(),
				firstname: $('#firstname').val(),
				middlename: $('#middlename').val()
			};
			
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
								showMessageModalFast();
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
			
		}else{
			setUserAccountFormMessage('Fill-up all required fields');
		}
		
	}

	$('#btnSearch').on('click', function(){
		loadUserAccounts(1);
	});
	
	$('#txtSearchValue').on('keypress', function(event){
		var keyCode = (event.keyCode ? event.keyCode : event.which);
		if(keyCode == '13'){
			$('#btnSearch').click();
		}
	});
	
	function loadUserAccounts(startingNumber){
		
		$.ajax({
			type: 'GET',
			url: '/ThesesManagement/rest/userAccounts/count?searchValue=' + $('#txtSearchValue').val(),
			success: function(data){
	
				setMessageModalContent('User Accounts', 'Found ' + data.count);
				showMessageModalFast();
				
			},
			error: function(jqXHR, textStatus, errorThrown){
				setUserAccountFormMessage('Server error occurred.');
			}
		});
		
	}
	
});

