/**
 * 
 */
var loadIndex = 0;
$().ready(function(){	
//	var id = GetQueryString("id");
//	if(id){
//		$("#ultree li").removeClass("act");
//		$("#"+id).addClass("act");
//	}else{
//		id = "dt";
//	}
	
	loadNews(loadIndex);
	
	
	$(".page #next").click(function(){		    
	    loadIndex++;
		var isload = loadNews(loadIndex);
		if(isload){
			$(".page .current").html(loadIndex);
		}else{
			loadIndex--;
		}
		
	});
	
	$(".page #previous").click(function(){		    
	    loadIndex--;
		var isload = loadNews(loadIndex);
		if(isload){
			$(".page .current").html(loadIndex);
		}else{
			loadIndex++;
		}
		
	});

});

function loadNews(loadIndex){
	$.post("news/list",{accessid:"3",pageIndex:loadIndex},function(res){
	    var dataList = res.data;
	    if(dataList && dataList.length>0){
	    	var newsCenterContent = "";
		    for(var i=0;i<dataList.length;i++){
		    	var data = dataList[i];
		    	newsCenterContent += "<li class='clearfix'>";
			    newsCenterContent +="<a href='activity.html?id="+data.id+"'>";
			    newsCenterContent +="<img src='"+data.picPath+"'>";
			    newsCenterContent +="<div class='list_text'><em></em>";
			    newsCenterContent +="<h3>"+data.title+"</h3>";
			    newsCenterContent +="<span>发布时间："+convertToDate(data.releaseTime)+"</span>";
			    newsCenterContent +="</div></a></li>";
		    }
		    $("#newsCenterDiv ul").html(newsCenterContent);	
		    
		    return true;
	    }  
	});
	return false;
}

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
