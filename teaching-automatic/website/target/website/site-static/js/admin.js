var WebURL = "http://localhost/website/";
$(document).ready(function(){
	var UserTable = $("#adminUser");
	var userStatus = UserTable.find("a[status]");
	var userReset = UserTable.find("a[class=reset]");
	var userDel = UserTable.find("a[class=userDel]");
	userStatus.bind("click",function(event){
		event.preventDefault();
		var thisStatus =$(this);
		var Url = WebURL + "admin/user/alive/"+thisStatus.attr("href")+"/"+thisStatus.attr("status");
		$.ajax({
			type:"GET",
			url:Url,
			success: function(msg){
				var showText = "";
				if(msg.toString()=="true"){
					if(thisStatus.attr("status")=="N"){
						showText="禁用成功";
						thisStatus.attr("status","Y");
						thisStatus.html("开启");
					}else{
						showText="开启成功";
						thisStatus.attr("status","N");
						thisStatus.html("禁用");
					}
				}else{
					showText = "禁用失败";
				}
				tipIsSuccess(showText);
      		}
		});
		$(this).attr("href");
	});
	userReset.bind("click",userResetOrDel);
	if(UserTable.find("td[identity]").attr("identity")!="T"){
		userDel.bind("click",userResetOrDel);
	}
//	userDel.bind("click",userResetOrDel);
	var userEdit = UserTable.find("a[class=edit]");
	/*addUser*/
	addUser();
	userEdit.unbind("click").bind("click",userDialog);
	/* manage all admin */
	alladmin();
});
	function alladmin(){
		var addAdmin = $("#addadmin");
		addAdmin.bind("click",function(event){
			event.preventDefault();
			var addAdminPannel = $("#addadminpannel");
			addAdminPannel.show(function(){
				var inputText = $(this).find("input[type=text]");
				var inputSubmit = $(this).find("input[type=submit]");
				$(this).find("img").bind("click",function(){
					addAdminPannel.hide();inputText.val("");
				});
				inputSubmit.unbind("click").bind("click",function(){
					var thisUrl = WebURL + "/admin/add";
					if(inputText.val()!=""){
						$.get(thisUrl,{"adminname":inputText.val()},function(msg){
							var showText ='';
							if(msg.toString()=="true"){
								addAdminPannel.hide();
								showText = "添加成功!";
							}else{
								showText = "添加失败";
							}
							inputText.val("");
							tipIsSuccess(showText);
						});
					}else{
					 	Warn(inputText);
					}
				});
			});
		});
		var alladminTable = $("#alladmin");
		var adminStatus =alladminTable.find("a[status]");
		var adminReset = alladminTable.find("a[class=reset]");
		var adminDel = alladminTable.find("a[class=adminDel]");
		adminStatus.bind("click",adminOperate);
		adminReset.bind("click",adminOperate);
		adminDel.bind('click',adminOperate);
	}
	function adminOperate(evevt){
		evevt.preventDefault();
		var isDel = false;
		var isReset = false;
		var thisStatus ;
		var thisA = $(this);
		var thisUrl= WebURL +"admin/";
		if($(this).attr("status")){
			thisUrl += "alive/"+$(this).attr("href")+"/"+$(this).attr("status");
		}else{
			if($(this).attr("class")=="adminDel"){
				thisUrl +="remove/"+$(this).attr("href");
				isDel = true;
			}else{
				isReset =true;
				thisUrl +="reset/"+$(this).attr("href");
			}
		}
		$.get(thisUrl,function(msg){
			var showText = '';
			if(msg.toString()=="true"){
				if(isDel){
					showText="删除成功";
					thisA.parents("tr").remove();
				}else if(isReset){
					showText = "重置成功";
				}else{
					if(thisStatus=="N"){
						showText = "禁用成功";
						thisA.html("开启").attr("status","Y");
					}else{
						showText = "开启成功";
						thisA.html("禁用").attr("status","N");
					}
				}
			}else{
				showText =isDel ? "删除失败":isReset?"重置失败":thisStatus=="N"?"禁用失败":"开启失败";
			}
			tipIsSuccess(showText);
		});
	}
	function addUser(){
		var addAdminUser = $("#addAdminUser");
		addAdminUser.unbind("click").bind("click",userDialog);
	}
	function userDialog(event){
		event.preventDefault();
		var isAdd = true;
		var dialogText = "";
		if($(this).attr("class")=="edit"){
				dialogText = "编辑用户";
				isAdd = false;
		}else{
			dialogText = "新增用户";
		}
		var allUserDialog = $("#userchangeadmin");
		/*show dialog*/
		allUserDialog.parent().show(function(){
			allUserDialog.find("img").unbind("click").bind("click",function(){
					 $(this).parents("form").parent().hide();
					  $(this).parents("form").find("input").css({"border":"1px solid #ccc"});
			});
			$(this).find("span").eq(0).html(dialogText);
		});
		/*show userAdminInfo*/
		var thisUrl;
		if(isAdd){
			showUserInfo($(this),allUserDialog);
			// Nourl
			thisUrl = WebURL + "/admin/user/add";
		}else{
			showUserInfo($(this).parents("tr"),allUserDialog);
			thisUrl = WebURL+"/admin/user/change";
		}
		/*submit userAdminInfo*/
		var DialogSubmit = allUserDialog.find("input[type=submit]");
		DialogSubmit.unbind("click").bind("click",function(event){
			event.preventDefault();
			var DialogForm = $(this).parents("form");
			var Name = DialogForm.find("input[name=name]");
			var QQ = DialogForm.find("input[name=qq]");
			var Email =DialogForm.find("input[name=email]");
			var Phone = DialogForm.find("input[name=phone]");
			/*check userAdmin infomation*/
			if(checkSuccess(Name,Phone,Email,QQ)){
				var UrlData = $(this).parents("form").serialize();
				$.post(thisUrl,UrlData,function(msg){
					var showText='';
					allUserDialog.parent().hide();
					if(msg.toString()=="true"){
						showText= isAdd?"添加成功":"修改成功";
						allUserDialog.find("input[type!=submit]").val("");
					}else{
						showText = isAdd?"添加失败":"修改失败";
					}
					tipIsSuccess(showText);
				});
			}

		});
	}
	function showUserInfo(tr,Dialog){
		var DialogInput = Dialog.find("input");
		var i = 0;
		while(i<6){
			if(i==2){
				var identity = tr.children().eq(2).attr("identity");
				var option = Dialog.find("option");
				for(var j=0;j<3;j++){
					if(option.eq(j).val()==identity){
						option.eq(j).attr("selected",true);
					}
				}
			}else{
				var j;
				j=i>=3?i-1:i;
				DialogInput.eq(j).val(tr.children().eq(i).text());
			}
			i++;
		}
	}
	function userResetOrDel(event){
		event.preventDefault();
		var isDel = false;
		var thisA = $(this);
		var Url= WebURL +"admin/user/";
		if($(this).attr("class")=="userDel"){
			isDel=true;
			Url +="remove/"+thisA.attr("href");
		}else{
			Url +="reset/"+thisA.attr("href");;
		}
		$.ajax({
			type:"GET",
			url:Url,
			success: function(msg){
				var showText = "";
				if(msg.toString()=="true"){
					if(isDel){
						showText = "删除成功";
						thisA.parents("tr").remove();
					}else{
						showText = "重置成功";
					}
				}else{
					showText =isDel?"删除失败":"重置失败";
				}
				tipIsSuccess(showText);
      		}
		});
	}
	function tipIsSuccess(content){
		$("#topTip").html(content).slideDown(1500,function(){
			$(this).slideUp(2000);
		});
	}
	function checkSuccess(Name,Phone,Email,QQ){
		var phoneOK=false;
		var emailOK=false;
		var qqOK=false;
		var nameOk = false;
		if(Name.val()==""){Warn(Name);}else{ nameOk=true;}

		if(isPhone(Phone.val())||Phone.val()==""){phoneOK=true;}else{Warn(Phone);}
		if(isEmail(Email.val())||Email.val()==""){emailOK=true;}else{Warn(Email);}
		if(isQQ(QQ.val())||Email.val()==""){qqOK=true;}else{Warn(QQ);}
		if(phoneOK&&emailOK&&qqOK&&nameOk){
			return true;
		}else{
			return false;
		}
	}
	function isPhone(s){
		var reg =  /^0?(13|15|18|14|17)[0-9]{9}$/;
		var re = new RegExp(reg);
		if (re.test(s)) {
			return true;
		}else{
			return false;
		}
	}
	function isEmail(s){
		var reg =  /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,5}){1,5})$/;
		var re = new RegExp(reg);
		if (re.test(s)) {
			return true;
		}else{
			return false;
		}
	}
	function isQQ(s){
		var reg =/^[1-9][0-9]{4,}$/;
		var re = new RegExp(reg);
		if (re.test(s)) {
			return true;
		}else{
			return false;
		}
	}
	function Warn(obj){
		obj.css({"border":"1px solid red"})
				.focus(function(){
					$(this).css({"border":"1px solid #ccc"});
				});
	}