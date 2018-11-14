/**
 * 
 */

$().ready(function(){	
	loadPages();
	loadNews();
	loadActivities();
});

function loadPages(){
	$.post("news/list",{accessid:"home"},function(res){
	    var dataList = res.data;
	    if(dataList && dataList.length>0){
	    	var newsCenterContent = "";
		    for(var i=0;i<dataList.length;i++){
		    	var data = dataList[i];
		    	
		    	newsCenterContent += "<div class='swiper-slide ";
		    	if(i==0 || i==dataList.length-1){
		    		newsCenterContent += "swiper-slide-duplicate";
		    	}
		    	newsCenterContent += "' style='background: url(" +data.picPath+") 0% 0%/cover no-repeat; width: 1343px; height: 377.719px;'>";
		    	newsCenterContent += "<a href='news.html?id="+data.id+"'><div class='swiper-conntent'>";
		    	newsCenterContent += "<p class='title'>"+data.title+"</p></div></a></div>";
		    }
		    $(".swiper-wrapper").html(newsCenterContent);	
		    
		    var mySwiper = new Swiper('.swiper-container',{
				pagination: '.pagination',
				loop:true,
				autoplay: 5000,
				followFinger : false,
				autoplayDisableOnInteraction : true,
				autoplayDisableOnInteraction : false,
				/*grabCursor: true,*/
				paginationClickable: true
			});
		    
			  $('.arrow-left').on('click', function(e){
				e.preventDefault()
				mySwiper.swipePrev()
			  });
			  
			  $('.arrow-right').on('click', function(e){
				e.preventDefault()
				mySwiper.swipeNext()
			  });
	    }  
	});
}

function loadNews(loadIndex){
	$.post("news/list",{accessid:"2",pageIndex:"0"},function(res){
	    var dataList = res.data;
	    if(dataList && dataList.length>0){
	    	var newsCenterContent = "";
	    	var length = dataList.length;
	    	if(length>3){
	    		length=3;
	    	}
		    for(var i=0;i<length;i++){
		    	var data = dataList[i];
		    	newsCenterContent += "<li class='clearfix'>";
			    newsCenterContent +="<a href='news.html?id="+data.id+"'>";
			    newsCenterContent +="<img src='"+data.picPath+"'>";
			    newsCenterContent +="<div class='list_text'>";
			    newsCenterContent +="<h3>"+data.title+"</h3>";
			    newsCenterContent +="<span>发布时间："+convertToDate(data.releaseTime)+"</span>";
			    newsCenterContent +="</div></a></li>";
		    }
		    $("#newsCenterDiv ul").html(newsCenterContent);		
	    }  
	});
}

function loadActivities(loadIndex){
	$.post("news/list",{accessid:"3",pageIndex:"0"},function(res){
	    var dataList = res.data;
	    if(dataList && dataList.length>0){
	    	var length = dataList.length;
	    	if(length>2){
	    		length=2;
	    	}
	    	var newsCenterContent = "";
		    for(var i=0;i<length;i++){
		    	var data = dataList[i];
		    	newsCenterContent += "<li class='clearfix'>";
			    newsCenterContent +="<a href='activity.html?id="+data.id+"'>";
			    newsCenterContent +="<img style='float:none;' src='"+data.picPath+"'>";
			    newsCenterContent +="<div class='list_text'>";
			    newsCenterContent +="<h3 style='display:inline-block;margin-top:12px;margin-left:2px'>"+data.title+"</h3>";
			    newsCenterContent +="<span>"+convertToDate(data.releaseTime)+"</span>";
			    newsCenterContent +="</div></a></li>";
		    }
		    $("#activitiesDiv ul").html(newsCenterContent);		
	    }  
	});
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
