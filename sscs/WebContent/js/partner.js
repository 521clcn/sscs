/**
 * 
 */
$().ready(function(){	
	var id = GetQueryString("id");
	if(id){
		$("#ultree li").removeClass("act");
		$("#"+id).addClass("act");
	}else{
		id = "fc";
	}
	
	$.post("introduce/list",{type:"4",accessid:id},function(res){
	    var data = res.data;
//	    $("title").html(data.title);
	    var paddingDiv = "<div style='padding-top:30px;'></div>"
	    var content = paddingDiv+data.content;
	    $("#contentDiv").html(content);
	});
	

});

function GetQueryString(param){
	var reg = new RegExp("(^|&)"+ param +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null){
		return unescape(r[2]);
	}else{
		return null;
	}
}


