/**
 * 
 */
$().ready(function() {
	var id = GetQueryString("id");
	if(id){
		$.post("../tkd/findById",{id:id},function(res){
		    var data = res.data;
		    
		    $("#id").val(data.id);
		    $("#modelname").val(data.modelname);
		    $("#modelurl").val(data.modelurl);
		    $("#title").val(data.title);
		    $("#keywords").val(data.keywords);
		    $("#description").val(data.description);		    
		},'json');
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