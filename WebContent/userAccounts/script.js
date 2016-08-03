$(document).ready(function(){
	
	/**
	 * Begin: Initializations
	 */
	
	$("#navBtnUserAccounts").addClass("active");
	$("#btnEdit").addClass("disabled");
	$("#btnDelete").addClass("disabled");
	
	/**
	 * End: Initializations
	 */
	
	/**
	 * Begin: Functions
	 */
	
	function search(value, pageNumber, itemsPerPage){
		$.ajax({
			type: "GET",
			url: "/ThesesManagement/rest/userAccounts/count?value=" + value,
			success: function(responseData){
				var count = responseData.count;
				if(count > 0){
					$.ajax({
						type: "GET",
						url: "/ThesesManagement/rest/userAccounts/search?value=" + value + "&pageNumber=" + pageNumber + "&itemsPerPage=" + itemsPerPage,
						success: function(responseData){
							$("#btnEdit").addClass("disabled");
							$("#btnDelete").addClass("disabled");
							$("#tblSearchResults tbody").empty();
							var plottedRows = 0;
							$.each(responseData, function(index, item){
								
								var usernameCell = $(document.createElement("td"));
								$(usernameCell).text(item.username);
								
								var nameCell = $(document.createElement("td"));
								$(nameCell).text(item.lastname + ", " + item.firstname + (item.middlename.length > 0 ? " " + item.middlename : ""));
								
								var row = $(document.createElement("tr"));
								$(row).append(usernameCell);
								$(row).append(nameCell);
								$(row).attr("data-id", item.id);
								$("#tblSearchResults tbody").append(row);
								plottedRows++;
								
							});
							
							var visibleButtonsCount = 7;
							var totalPages = Math.ceil(count / itemsPerPage);
							var startingPage = 1;
							var endingPage = totalPages;
							if(pageNumber > Math.ceil(visibleButtonsCount / 2)) {
								startingPage = pageNumber - Math.floor(visibleButtonsCount / 2);
							}
							endingPage = startingPage + 6;
							if(endingPage > totalPages) {
								endingPage = totalPages;
								startingPage = endingPage - 6;
							}
							if(startingPage < 1){
								startingPage = 1;
							}
							
							$(".pagination").empty();
							
							var toFirstPage = $(document.createElement("li"));
							$(toFirstPage).attr("data-pagenumber", 1);
							$(toFirstPage).attr("data-searchvalue", value);
							$(toFirstPage).append("<a href='#'>&lt;&lt;</a>");
							if(pageNumber == 1){
								$(toFirstPage).addClass("disabled");
							}
							$(".pagination").append(toFirstPage);

							var toPreviousPage = $(document.createElement("li"));
							$(toPreviousPage).attr("data-pagenumber", pageNumber < 2 ? 1 : pageNumber - 1);
							$(toPreviousPage).attr("data-searchvalue", value);
							$(toPreviousPage).append("<a href='#'>&lt;</a>");
							if(pageNumber == 1){
								$(toPreviousPage).addClass("disabled");
							}
							$(".pagination").append(toPreviousPage);
							
							for(var currentNumber = startingPage; currentNumber <= endingPage; currentNumber++){
								var listItem = $(document.createElement("li"));
								$(listItem).attr("data-pagenumber", currentNumber);
								$(listItem).attr("data-searchvalue", value);
								if(currentNumber == pageNumber){
									$(listItem).addClass("active");
								}
								$(listItem).append("<a href='#'>" + currentNumber + "</a>");
								$(".pagination").append(listItem);
							}
							
							var toNextPage = $(document.createElement("li"));
							$(toNextPage).attr("data-pagenumber", pageNumber < totalPages ? pageNumber + 1 : pageNumber);
							$(toNextPage).attr("data-searchvalue", value);
							$(toNextPage).append("<a href='#'>&gt;</a>");
							if(pageNumber == totalPages){
								$(toNextPage).addClass("disabled");
							}
							$(".pagination").append(toNextPage);
							
							var toLastPage = $(document.createElement("li"));
							$(toLastPage).attr("data-pagenumber", totalPages);
							$(toLastPage).attr("data-searchvalue", value);
							$(toLastPage).append("<a href='#'>&gt;&gt;</a>");
							if(pageNumber == totalPages){
								$(toLastPage).addClass("disabled");
							}
							$(".pagination").append(toLastPage);
							
						},
						error: function(jqXHR, errorThrown, textStatus){
							alert("Server Error: " + errorThrown);
						}
					});
					
				} else {
					alert("Nothing found");
				}
			},
			error: function(jqXHR, errorThrown, textStatus){
				alert("Server Error: " + errorThrown);
			}
		});
	}
	
	/**
	 * End: Functions
	 */
	
	/**
	 * Begin: Event Handlers
	 */
	
	$("#tblSearchResults").on("click", "tr", function(){
		
		$("#btnEdit").removeClass("disabled");
		$("#btnDelete").removeClass("disabled");
		
		var source = this;
		$.each($(this).parent().children(), function(index, item){			
			if(item === source){
				$(item).addClass("active");
			} else {
				$(item).removeClass("active");
			}
			
		});
	});
	
	$(".pagination").on("click", "li", function(){
		if(!$(this).hasClass("disabled")){
			search($(this).data("searchvalue"), $(this).data("pagenumber"), 12);	
		}
	});
	
	$("#searchBox button").on("click", function(){
		search($("#searchBox input").val(), 1, 12);
	});
	
	$("#searchBox input").on("keypress", function(event){
		var keyCode = event.which || event.keyCode;
		if(keyCode == 13){
			search($(this).val(), 1, 12);
		}
	});
	
	$("#btnNew").on("click", function(){
		var $form = $("#userAccountForm");
		$form.find("#title").text("Create User Account");
		$form.find("input").val("");
		$form.find(".btn-warning").parent().addClass("hidden");
		$form.fadeIn();
	});
	
	$("#userAccountForm").find(".btn-danger").on("click", function(){
		$("#userAccountForm").fadeOut();
	});
	
	$("#messageBox").find(".btn-primary").on("click", function(){
		$("#messageBox").fadeOut();
	});
	
	$("#userAccountForm").find(".btn-primary").on("click", function(){
		
		var userAccount = {
				username : $("#username").val(),
				password : CryptoJS.MD5("d3fp@55").toString(),
				lastname : $("#lastname").val(),
				firstname : $("#firstname").val(),
				middlename : $("#middlename").val()
		};
	
		$("#userAccountForm").fadeOut();
		
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "/ThesesManagement/rest/userAccounts/create",
			data: JSON.stringify(userAccount),
			success: function(data){
			
				$("#userAccountForm").find("input").val("");
				
				$("#messageBox").find("#title").text("User Accounts");
				$("#messageBox").find("#message").text("User Account successfully created.");
				$("#messageBox").fadeIn();
				
			},
			error: function(jqXHR, errorThrown, textStatus){
				$("#messageBox").find("#title").text("User Accounts");
				$("#messageBox").find("#message").text(textStatus);
				$("#messageBox").fadeIn();
			}
		});
		
	});
	
	$("#btnEdit").on("click", function(){
		if(!$(this).hasClass("disabled")){
			var selectedId = $("#tblSearchResults > tbody > tr.active").data("id");
			$.ajax({
				type: "GET",
				url: "/ThesesManagement/rest/userAccounts/get?id=" + selectedId,
				success: function(responseData){
					
					$("#userAccountForm").find("#username").val(responseData.username);
					$("#userAccountForm").find("#lastname").val(responseData.lastname);
					$("#userAccountForm").find("#firstname").val(responseData.firstname);
					$("#userAccountForm").find("#middlename").val(responseData.middlename);
					
					$("#userAccountForm").find("#title").text("Edit User Account");
					$("#userAccountForm").find(".btn-warning").parent().removeClass("hidden");
					$("#userAccountForm").fadeIn();
					
				},
				error: function(jqXHR, errorThrown, textStatus){
					$("#messageBox").find("#title").text("User Accounts");
					$("#messageBox").find("#message").text(textStatus);
					$("#messageBox").fadeIn();
				}
			});
		}
	});
	
	$("#userAccountForm").find(".btn-warning").on("click", function(){
		var selectedId = $("#tblSearchResults > tbody > tr.active").data("id");
		$("#userAccountForm").fadeOut();
		$("#form_changePassword").attr("data-userId", selectedId);
		$("#form_changePassword").find("strong").remove();
		$("#form_changePassword").fadeIn();
	});
	
	$("#form_changePassword").find(".btn-primary").on("click", function(){
		var $form = $("#form_changePassword");
		$form.find("strong").remove();
		var hasErrors = false;
		$.each($form.find("input"), function(index, item){
			if($(item).val().trim().length < 1){
				hasErrors = true;
				$(item).after("<strong class='bg-danger'>This field is required</strong>");
			}
		})
		if(!hasErrors){
			if($form.find("#newPasswordConfirmation").val() != $form.find("#newPassword").val()){
				hasErrors = true;
				$form.find("#newPasswordConfirmation").after("<strong class='bg-danger'>Passwords doesn't match</strong>");
				$form.find("#newPassword").after("<strong class='bg-danger'>Passwords doesn't match</strong>");
			}
		}
		if(!hasErrors){
			
		}
	});
	
	$("#form_changePassword").find(".btn-danger").on("click", function(){
		$("#form_changePassword").find("input").val("");
		$("#form_changePassword").fadeOut();
	});
	
	/**
	 * End: Event Handlers
	 */
	
});