var http = "http://localhost:8080/sh/";
$(document).ready(function(){
	$('.system-bar')
		.animate({'height':'250px'}, 1000)
		.animate({'height':'200px'}, 1000);
	_init();
});

function _init() {
	var loginForm = $("#loginForm");
	var submit = loginForm.find("input[type=submit]");
		submit.bind("click", function(event){
					event.preventDefault();
					var identifty = loginForm.find("option:selected");
					var checkUrl = http;
					if(identifty.val() == "system"){
						checkUrl += "system/login";
					} else {
						checkUrl += "admin/login";
					}
					
					$.ajax({
						url  : checkUrl,
						type : 'post',
						data : loginForm.serialize(),
						success : function(flag){
							if(flag == true){
								if(identifty.val() == "system"){
									window.location.href = http + "system/center";
								}else{
									window.location.href = http + "admin/notices";
								}
							}else{
								alert(flag);
							}
						}
					});
				});
}