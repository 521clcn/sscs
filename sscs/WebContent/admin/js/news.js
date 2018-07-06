/**
 * 
 */
$().ready(function(){		
		var id = GetQueryString("id");
		if(id!=null){
			$.post("../news/findById",{id:id},function(res){
			    var data = res.data;
			    
			    $("#id").val(data.id);
			    $("#accessid").val(data.accessid);
			    $("#title").val(data.title);
			    
//			    editor.setContent(data.content);
			    editor.ready(function() {
			    	editor.setContent(data.content);
			    },2);
			});
		}
		
		var type = GetQueryString("type");
		if(type=="home"){
			$("#accessid").val("1");
		}else{
			$("#accessid").val("2");
		}
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

