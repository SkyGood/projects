var http = "http://localhost:8080/sh/";

$(document).ready(function(){
	var userListTable = $("#userlist");
	var aliveA = userListTable.find("a[tostatus]");
	
	aliveA.bind("click",function(event){
		event.preventDefault();
		var thisA = $(this);
		var toStatus = $(this).attr("tostatus");
		var userId = $(this).attr("href");
		$.ajax({
			url  : http + "system/user/alive/"+userId+"/"+toStatus,
			type : 'get',
			success : function(flag){
				if(flag == true){
					if(toStatus == "A"){
						thisA.attr("tostatus","C").html("禁用");
					}else{
						thisA.attr("tostatus","A").html("开启");
					}
				}else{
					alert(flag);
				}
			}
		});
	});
	
	var DelA = userListTable.find("a[class=del]");
	DelA.bind("click",function(event){
		event.preventDefault();
		var thisA = $(this);
		$.get(http + "system/delete/user/" + $(this).attr("href"),
			function(flag){
				if(flag == true){
					thisA.parents("tr").remove();
				}else{
					alert(flag);
			}
		});
	});
});