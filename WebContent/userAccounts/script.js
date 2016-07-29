$(document).ready(function(){
	
	/**
	 * Begin: Initializations
	 */
	
	$("#navBtnUserAccounts").addClass("active");
	
	/**
	 * End: Initializations
	 */
	
	/**
	 * Begin: Functions
	 */
	
	function search(value, pageNumber){
		$.ajax({
			type: "GET",
			url: "/ThesesManagement/rest/userAccounts/count?value=" + value,
			success: function(responseData){
				var count = responseData.count;
				var buttonCount = Math.floor(count / 15);
				if(count % 15 > 0){
					buttonCount++;
				}
				alert("Items: " + count + "\r\nButton Count: " + buttonCount);
				$(".pagination").empty();
				for(var currentNumber = pageNumber; currentNumber < buttonCount; currentNumber++){
					$(".pagination").append("<li><a href='#'>" + (currentNumber + 1) + "</a></li>");
				}
			},
			error: function(jqXHR, errorThrown, textStatus){
				alert("Text Status: " + textStatus);
			}
		});
	}
	
	/**
	 * End: Functions
	 */
	
	/**
	 * Begin: Event Handlers
	 */
	
	$("#searchBox button").on("click", function(){
		search($("#searchBox input").val(), 0);
	});
	
	$("#searchBox input").on("keypress", function(event){
		var keyCode = event.which || event.keyCode;
		if(keyCode == 13){
			search($(this).val(), 0);
		}
	});
	
	/**
	 * End: Event Handlers
	 */
	
});