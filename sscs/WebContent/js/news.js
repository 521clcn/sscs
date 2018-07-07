/**
 * 
 */
$().ready(function(){	
	var id = GetQueryString("id");
	$.post("news/findById",{id:id},function(res){
	    var data = res.data;
	    $("title").html(data.title);
	    $(".content-title").html(data.title);
	    $("#releaseDate").html(convertToDate(data.createdTime));
	    $(".content-detail").html(data.content);
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

function convertToDate(millis){
	var date = new Date(millis);
	var year = date.getFullYear();
	var month= date.getMonth()+1;
	var day = date.getDate();
	
	if(month<10){
		month = "0"+month;
	}
	if(day<10){
		day ="0" + day;
	}

	return year+"-"+month+"-"+day;
}


