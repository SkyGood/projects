var webUrl ="http://localhost/website/";
$(document).ready(function(){

	var NoticeA = $("#studentSubject").find("a[class=notice]");
	NoticeA.bind("click",function(event){
		event.preventDefault();
		var noticeText = $(this).parents("tr").find("input[type=hidden]");
		var noticeDialog =$("#noticeDialog");
		noticeDialog.show(function(){
			var dialogClose = $(this).find("img");
			var PText = noticeDialog.find("p");
			if(noticeText.val()==""){
				showText = "暂无公告";
			}else{
				showText = noticeText.val();
			}
			PText.text(showText);
			dialogClose.bind("click",function(){
				noticeDialog.hide();
				PText.text("");
			});
		});
	});

	$("#hidden").bind("click",function(){
		var flag =$(this).text();
		if(flag=="<<"){
			$("#navbar").children().eq(1).slideUp("slow",function(){
				$(this).parent().animate({"width":-0});
				$("#content").animate({"width":"100%"});
				$("#hidden").html(">>").parent().animate({"left":"0"});
			});
		}else{
			$(this).html("<<").parent().animate({"left":140});
			$("#content").animate({"width":"90%"});
			$("#navbar").animate({"width":140},function(){
					$(this).children().eq(1).slideDown("slow");
			});
		}
	});
	/*
	*一旦input标签有ruquired类,则阻止该父级form提交
	*/
	requiredForm();
	/*
	*the panle of change pwd
	*/
	changPwdDialog();
	/*
	*验证电话号码、邮箱、qq
	*/
	checkUserInfo();

	/*add-class in cotrollpannel page*/
	cotrollpannel();

	/*homeworkdetail page*/
	homeworkdetail();

	/* Checking student */
	checkinglist();

	/* homework alive delete */
	homework();

	/* resources alive delete */
	resources();

	/* import data process bar */
	importPage();

	/*addresources page*/
	addresources();

	/*referenceresources page*/
	reference();
	/* addhomework page*/
	addhomework();
});
	function reference(){
		var referenceTabel = $("#reference");
		var referenceA = referenceTabel.find("a");
		referenceA.bind("click",function(event){
			event.preventDefault();
			var teachingClassId = referenceTabel.prev().find("input[name=classId]");
			var thisUrl = webUrl+"teacher/resource/quote/"+teachingClassId.val()+"/"+$(this).attr("href");
			$.post(thisUrl,function(status){
				var showText = "引用失败";
				if(status.toString()=="true"){
					showText= "引用成功!";
				}
				tipIsSuccess(showText);
			});
		})
	}
	function addresources(){
		var addresourcesForm = $("#addresources");
		var addSubmit = addresourcesForm.find("input[type=submit]");
		var file = addresourcesForm.find("input[type=file]");
		var resName= addresourcesForm.find("input[name=resName]");
		addSubmit.bind("click",function(event){
			if(resName.val()==""){
				event.preventDefault();
				Warn(resName);
			}
			if(file.val()==""){
				event.preventDefault();
				Warn(file);
			}
		});
	}
	function addhomework(){
		var addHomeWorkForm = $("#addHomeWork");
		var workName = addHomeWorkForm.find("input[name=workName]");
		var file = addHomeWorkForm.find("input[type=file]");
		var addHomeWorkSubmit = addHomeWorkForm.find("input[type=submit]");
		addHomeWorkSubmit.bind("click",function(event){
			if(workName.val()==""){
				event.preventDefault();
				Warn(workName);
			}
			if(file.val()==""){
				event.preventDefault();
				Warn(file);
			}
		});
	}
	function Warn(obj){
		obj.css({"border":"1px solid red"})
				.focus(function(){
					$(this).css({"border":"1px solid #ccc"});
				});
	}
	function homework(){
		var homeworkTable = $("#homeworkList");
		var del = homeworkTable.find(".del");
		var homeworkStatus = homeworkTable.find(".homeworkStatus");
		del.bind("click",homeworkOperation);
		homeworkStatus.bind("click",homeworkOperation);
	}
	function homeworkOperation(event){
		event.preventDefault();
		var workA = $(this);
		var isDel = false;
		var ThisUrl = webUrl+"teacher/homework/";
		if($(this).attr("class")=="del"){
			isDel = true;
			ThisUrl += "remove/"+workA.attr('href');
		}else{
			ThisUrl += "alive/"+workA.attr('href')+"/"+workA.attr('status');
		}
		$.ajax({
				type:"GET",
				url:ThisUrl,
				success: function(msg){
					var showText = "";
					if(msg.toString()=="true"){
						showText="操作成功";
						if(isDel){//进行删除功能
							workA.parents("tr").remove();
						}else{//进行开始关闭功能
							if(workA.attr("status")=="Y"){
								workA.html("关闭").attr("status","N");
							}else{
								workA.html("开启").attr("status","Y");
							}
						}
					}else{
						showText ="操作失败";
					}
					tipIsSuccess(showText);
	      		}
		});
	}
	function requiredForm(){
		var required= $(".required");
		required.parents("form").find("input[type=submit]").
			bind("click",function(event){
				for(var i=0;i<required.length;i++){
					if(required.eq(i).val()==""){
						event.preventDefault();
						required.eq(i).css({border:"1px solid red"});
					}else{
						required.eq(i).css({border:"1px solid #ccc"});
					}
				}
			});
	}
	function checkUserInfo(){
		var phoneInput = $("#userInfo input[name=phone]");
		var emailInput = $("#userInfo input[name=email]");
		var qqInput = $("#userInfo input[name=qq]");
		var InfoError =$(".userInfo-error");
		phoneInput.blur(function(){
			if(!isPhone($(this).val())){
				errorPwdTip($(this),InfoError.eq(0),"格式错误","");
			}
		});
		emailInput.blur(function(){
			if(!isEmail($(this).val())){
				errorPwdTip($(this),InfoError.eq(1),"格式错误","");
			}
		});
		qqInput.blur(function(){
			if(!isQQ($(this).val())){
				errorPwdTip($(this),InfoError.eq(2),"格式错误","");
			}
		});
	}
	function changPwdDialog(){
	  $("#changePwd").bind("click",function(){
		$(".changepwd-panel").show(500,function(){
			var submitChangePwd = $("#submitChangePwd");
			var changeform = submitChangePwd.parents("form");
			var oldpwd = changeform.find("input[name=oldPwd]");
			var newpwd = changeform.find("input[name=newPwd]");
			var renewpwd = changeform.find("input[name=reNewPwd]");
			var errorTip = changeform.find("td>span");
			changeform.fadeIn(1000);

			newpwd.blur(function(){
				if(oldpwd.val()==$(this).val()){
					errorPwdTip($(this),//focus obj
								errorTip.eq(0),//error oobj
								"新旧密码不能相同",//error obj new content
								"*6~18位字母和数字"//error obj old content
								);
				}else if(!isNumberOrLetter($(this).val())){
					errorPwdTip($(this),errorTip.eq(0),"格式错误","*6~18位字母和数字");
				}else{
					renewpwd.blur(function(){
						if(newpwd.val()!=$(this).val()){
							errorPwdTip($(this),errorTip.eq(1),"两次输入不符","");
						}else{
							submitChangePwd.unbind("click").bind("click",function(){
								$.ajax({
									type:"POST",
						    		data:{  "oldPwd":oldpwd.val(),
						    				"newPwd":newpwd.val(),
						    				"reNewPwd":renewpwd.val(),
						    				"userId":$("#userId").val()
						    				},
									url:webUrl+"user/repwd",
									success: function(msg){
										var showText = '';
								        if(msg.toString()=="true"){
								           	showText = "修改成功";
								           	oldpwd.val("");newpwd.val("");renewpwd.val("");
								        }else{
								        	showText = "修改失败";
								        }
								       	changeform.fadeOut(500);
								        tipIsSuccess(showText);
								        changeform.parent().fadeOut(500);
						      		}
								});
							});
						}
					});
				}
			});
			closeImgDialog(changeform.parent());
		});
	  });
	}
	function isNumberOrLetter(s){//判断是否是数字或字母
		var regu = "^[0-9a-zA-Z]{6,}$";
		var re = new RegExp(regu);
		if (re.test(s)) {
			return true;
		}else{
			return false;
		}
	}
	function errorPwdTip(objFocus,objError,newcontent,oldcontent){
		objError.html(newcontent);
		objFocus.focus(function(){
			objError.html(oldcontent);
		});
	}
	function tipIsSuccess(content){
		$("#topTip").html(content).slideDown(1500,function(){
			$(this).slideUp(2000);
		});
	}
	function isPhone( s ){
		var reg =  /^0?(13|15|18|14|17)[0-9]{9}$/;
		var re = new RegExp(reg);
		if (re.test(s)) {
			return true;
		}else{
			return false;
		}
	}
	function isEmail( s ){
		var reg =  /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,5}){1,5})$/;
		var re = new RegExp(reg);
		if (re.test(s)) {
			return true;
		}else{
			return false;
		}
	}
	function isQQ( s ){
		var reg = /^[1-9][0-9]{4,}$/;
		var re = new RegExp(reg);
		if (re.test(s)) {
			return true;
		}else{
			return false;
		}
	}
	function cotrollpannel(){
		var addClass = $("#adminAddClass");
		var adminClassForm = $("#adminClass");
		var modifyClass = adminClassForm.find("a[name=modify]");
		var clearClass = adminClassForm.find("a[name=clean]");
		var delClass = adminClassForm.find("a[name=del]");
		//在这里都a标签对象
		var thisUrl = webUrl +"teacher/clazz/admin/";
		addOrModify(modifyClass,thisUrl+"change");
		addOrModify(addClass,thisUrl+"add",1);
		clearOrdel(clearClass,thisUrl+"clean");
		clearOrdel(delClass,thisUrl+"remove",1);
	}
	function addOrModify(obj,Url,isAdd){
		var classDialog = $("#classDialog");
		var DialogDiv = classDialog.parent();
		var DialogInputName = classDialog.find("input[name=adminClassName]");
		var DialogSubmit = classDialog.find("input[type=submit]");
		obj.unbind("click").bind("click",function(event){
			event.preventDefault();
			var className = $(this).parent().children("p");
			DialogInputName.val(className.text());
			TitleC=isAdd?"新增行政班":"修改行政班";
			classDialog.find("span").eq(0).text(TitleC);
			DialogDiv.show();
			var adminClassId = $(this).attr("href");
			DialogSubmit.unbind("click").bind("click",function(event){
				event.preventDefault();
				if(DialogInputName.val()!=""){
					var teachingClassId = classDialog.find("input[name=teachingClassId]").val();
					if(isAdd){//add class
						urlData = { "teachingClassId":teachingClassId,
									"adminClassName":DialogInputName.val()};
					}else{//modify class
						urlData = { "teachingClassId":teachingClassId,
									"adminClassId":adminClassId,
									"adminClassName":DialogInputName.val()};
					}
					$.ajax({
						type:"POST",
						data:urlData,
						url:Url,
						success: function(msg){
							var showText='';
							if(msg.toString()=="true"){//modify class success
								showText="修改成功";
								className.text(DialogInputName.val());
							}else if(msg>0){//add class success
								showText="添加成功";
								$("#adminClass").append("<span><p>"+DialogInputName.val()+"</p>"
							                            +"[<a href='"+msg+"' name='modify'>修改</a>]"
							                            +"[<a href='"+msg+"' name='clean'>清空</a>]"
							                            +"[<a href='"+msg+"' name='del'>删除</a>]</span>");
							}else{
								showText=isAdd?"添加失败":"修改失败";
							}
							tipIsSuccess(showText);
							DialogDiv.hide(function(){
								cotrollpannel();
							});
			      		}
					});
				}
			});
			closeImgDialog(DialogDiv);
		});
	}
	function clearOrdel(obj,Url,isDel){
		obj.unbind("click").bind("click",function(event){
			event.preventDefault();
			var delSpan = $(this).parent();
			if(isDel){//delete class
				//{"adminClassId":$(this).attr("href")};
				Url += "/"+$(this).attr("href")+"/"
							+$("#classDialog").find("input[name=teachingClassId]").val();
			}else{//clean class
				//{"adminClassId":$(this).attr("href")};
				Url +="/"+$(this).attr("href");
			}
			$.ajax({
				type: "GET",
				url:Url,
				success: function(msg){
					var showText = '';
			        if(msg.toString()=="true"){
			         	showText = "清空成功";
			           	if(isDel){
			           		showText = "删除成功";
			           		delSpan.remove();
			           	}
			        }else{
			        	showText=isDel?"删除失败":"清空失败";
			        }
			        tipIsSuccess(showText);
			        cotrollpannel();
	      		}
			});
			Url= null;
		});
	}
	function closeImgDialog(obj){
		$(".Imgclose").unbind("click").bind("click",function(){
			obj.hide();
		});
	}
	function homeworkdetail(){
		var selectHomeWork = $("#homeworkcommit");
		selectHomeWork.change(function(){
			var iscommit =$(this).children('option:selected').val();
			window.location.href= webUrl + "teacher/homework/" + iscommit;
		});
	}
	function checkinglist(){
		var checkingtable = $("#checkingtable");
		var absentA = checkingtable.find("a[class=absent]");
		var afleaveA = checkingtable.find("a[class=note]");
		absentA.bind("click",absentAOrafleaveA);
		afleaveA.bind("click",absentAOrafleaveA);
	}
	function absentAOrafleaveA(event){
		event.preventDefault();
		var absentTd = $(this).parents("tr").children().eq(2);
		var afleaveTd = $(this).parents("tr").children().eq(3);
		var isAbsent = false;
		var Url =webUrl+"teacher/checking/";
		if($(this).attr("class")=="absent"){
			isAbsent = true;
			Url += "absent/" + $(this).parents("table").find("input").val()+"/"+$(this).attr("href");
		}else{
			Url += "note/" + $(this).parents("table").find("input").val()+"/"+$(this).attr("href");
		}
		$.ajax({
			type: "GET",
			url:Url,
			success: function(msg){
				var showText = "";
				if(msg.toString()=="true"){
					if(isAbsent){
						absentTd.text(parseInt(absentTd.text())+1);
					}else{
						afleaveTd.text(parseInt(afleaveTd.text())+1);
					}
					showText="操作成功";
				}else{
					showText ="操作失败";
				}
				tipIsSuccess(showText);
      		}
		});
	}
	function resources(){
		var resourcesTable = $("#resourcesList");
		var del = resourcesTable.find(".Rdel");
		var resourcesStatus = resourcesTable.find(".resourcesStatus");
		del.bind("click",resourcesOperation);
		resourcesStatus.bind("click",resourcesOperation);
	}
	function resourcesOperation(event){
		event.preventDefault();
		var workA = $(this);
		var isDel = false;
		var Url =webUrl+"teacher/resource/";
		if($(this).attr("class")=="Rdel"){
			isDel = true;
			var teachingClassId = $(this).parents("table").find("input[name=teachingClassId]").val();
			Url += "remove/"+teachingClassId+"/"+workA.attr('href');
		}else{
			Url += "alive/"+workA.attr('href')+"/"+workA.attr('status');
		}
		$.ajax({
				type:"GET",
				url:Url,
				success: function(msg){
					var showText = "";
					if(msg.toString()=="true"){
						showText="操作成功";
						if(isDel){//进行删除功能
							workA.parents("tr").remove();
						}else{//进行开始关闭功能
							if(workA.attr("status")=="Y"){
								workA.html("关闭").attr("status","N");
							}else{
								workA.html("开启").attr("status","Y");
							}
						}
					}else{
						showText ="操作失败";
					}
					tipIsSuccess(showText);
	      		}
		});
	}
	function importPage(){
		var importForm = $("#importForm");
		importForm.find("input[type=submit]")
			.bind("click",function(event){
				$("#loading").show();
		});
	}