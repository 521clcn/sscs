/**
 * 
 */
$().ready(function(){		
	$.post("footer/list",{},function(res){
		var data = res.data;
		if(data!=null){
			$(".address_sscs").html(data.address);
			$(".phone_sscs").html(data.contract);
			$(".email_sscs").html(data.email);
			$(".copyright_sscs").html(data.copyright);
			$(".beian_sscs").html(data.record);
		}
	});
	

});



