$().ready(function(){	
	$.post("../introduce/list",{type:$("#type").val()},function(res){
		var data = res.data;
		if(data!=null){
			var content = data.content;
			var reg = new RegExp("<br/>", "g");
			content = content.replace(reg, "\n");
			$("#content").html(content);
			$("#id").val(data.id);
		}
	});

});

$("#saveBtn").click(function(){
	var strContent = document.getElementById("content").value;
    strContent = strContent.replace(/\r\n/g, '<br/>'); //IE9、FF、chrome  
    strContent = strContent.replace(/\n/g, '<br/>'); //IE7-8  
    strContent = strContent.replace(/\s/g, ' '); //空格处理  
    
    $.post('../introduce/save',{id:$("#id").val(),content:strContent,type:$("#type").val()},function(result){
		if (result.suc=="yes"){
			alert("保存成功");
		} else {
			alert("保存出错");
		}
	},'json');
});