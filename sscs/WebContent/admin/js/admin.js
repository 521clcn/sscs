/**
 * 
 */
$().ready(function(){
	$("#introduce").parent().addClass("active");
	$("#introduce").parent().parent().parent().addClass("active");
	
	$("#contentFrame").attr("src","../admin/introduce.html?type=1&accessid=jj");  
	
//	$.post("../user/user",{},function(res){
//	    var data = res.data;
//	    $("#loginId").html(data.username);
//	    if(data.type!="0"){
//	    	$("#userLi").css("display","none");
//	    	$("#contentFrame").attr("src","../admin/newsList.html");  
//	    }else{
//	    	$("#contentFrame").attr("src","../admin/user.html");  
//	    }
//	});

});

$("#admin_menu a").click(function() {
	var id = this.id;
	var action = $(this).attr("action");
	$("#admin_menu .active").removeAttr('class');
	$(this).parent().addClass("active");
	$(this).parent().parent().parent().addClass("active");
	if(action!="no"){
		var type = $(this).attr("type");
		var accessid = $(this).attr("accessid");
		$("#contentFrame").attr("src","../admin/"+id+".html?type="+type+"&accessid="+accessid);  
	}
	
});

$("#logout").click(function(){
	$.post("../user/logout");
});




