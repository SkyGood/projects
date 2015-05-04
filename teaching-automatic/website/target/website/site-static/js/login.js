var WebURL = "http://localhost/website/";
$(document).ready(function(){
	var loginForm = $("#loginForm");
	var loginSubmit = loginForm.find("input[type=submit]");
	loginSubmit.bind("click",function(event){
		event.preventDefault();
		var identityCode = loginForm.find("input[name=identityCode]");
		var passWord = loginForm.find("input[name=passWord]");
		if(identityCode.val()==""||passWord.val()==""){
			showError(loginForm);
		}else{
			var identity = loginForm.find("select");
			var checkingUrl = WebURL+'login';
			$.post( checkingUrl,
					loginForm.serialize(),
					function(isLogin,Status){
						if(Status.toString()=="success"){
							if(isLogin.toString()=="true"){
								var AdminUrl = WebURL+"admin/admin";
								var userUrl =WebURL + "system/homepage";
								if(identity.val()=="A")
									window.location.href=AdminUrl;
								else
									window.location.href=userUrl;
							}else{
								showError(loginForm);
							}
						}else{
							loginSubmit.unbind("click");
						}
					});
				}
		});
	function showError(obj){
		obj.find("span")
			.show(function(){
				obj.find("input[type=text]").focus(function(){
					obj.find("span").hide();
				})
			});
	}
});