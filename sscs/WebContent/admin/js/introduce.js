$().ready(function(){	
	var type = GetQueryString("type");
	var accessid = GetQueryString("accessid");
	if(!type){
		type ="1";
	}
	if(!accessid){
		accessid="jj";
	}
	$("#type").val(type);
	$("#accessid").val(accessid);
	
	$.post("../introduce/list",{type:type,accessid:accessid},function(res){
		var data = res.data;
		if(data){
			$("#id").val(data.id);
//			editor.setContent(data.content);
			editor.ready(function() {
		    	editor.setContent(data.content);
		    },2);
		}
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