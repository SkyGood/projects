var http = "http://localhost:8080/sh/";

$(document).ready(function(){
	var classListTable = $("#classlist");
	var addStudentA = classListTable.find("a[class=addstudent]");
	var addAdminA = classListTable.find("a[class=addadmin]");
	addStudentA.bind("click",function(event){
		event.preventDefault();
		classId = $(this).attr("href");
		$("#addstudent").slideDown(1500,submitData($("#addstudent"),classId));
	});
	addAdminA.bind("click",function(event){
		event.preventDefault();
		classId = $(this).attr("href");
		$("#addadmin").slideDown(1500,submitData($("#addadmin"),classId));
	});
	
	var DelA = classListTable.find("a[class=del]");
	DelA.bind("click",function(event){
		event.preventDefault();
		var thisA = $(this);
		$.get(http+"system/class/delete/"+$(this).attr("href"),function(flag){
			if(flag == true){
				thisA.parents("tr").remove();
			}else{
				alert(flag);
			}
		});
	});
});
	function submitData(ObjDiv,classId){
		var closeP = ObjDiv.find("p");
		closeP.bind("click",function(){ObjDiv.slideUp();});
		
		var hiddenInput = ObjDiv.find("input[type=hidden]");
		hiddenInput.val(classId);
		var thisUrl;
		if(ObjDiv.attr("id")=="addadmin"){
			thisUrl = http + "system/administrator/add"; 
		} else {
			thisUrl = http + "system/student/add";
		}
		
		var submit = ObjDiv.find("input[type=submit]");
		submit.unbind("click").bind("click",function(event){
			event.preventDefault();
			$.ajax({
				url  : thisUrl,
				type : 'post',
				data : ObjDiv.children().serialize(),
				success : function(flag){
					ObjDiv.slideUp(1500,function(){
						if(flag == true){
							errorTip("添加成功");
							if(ObjDiv.attr("id")=="addadmin"){
								$("#classlist").find("a[href="+classId+"]").eq(0).parent().html(ObjDiv.find("input[name=adminName]").val());
							}
						}else{
							errorTip("添加失败");
						}
					});
				}
			});
		});
	}
	
	function errorTip(content){
		$("#error").html(content).slideDown(2000,function(){
			$(this).slideUp(2000);
		});
	}
	