$().ready(function(){	
	$.post("../footer/list",{},function(res){
		var data = res.data;
		if(data!=null){
			$("#id").val(data.id);
			$("#address").val(data.address);
			$("#contract").val(data.contract);
			$("#email").val(data.email);
			$("#copyright").val(data.copyright);
		}
	});

});
