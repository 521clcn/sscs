/*!
 * Javascript for index.js, based on jquery.
 * @author zeyi ren
 * @date 2016-6-14
 * @update 2016-6-16
 *
 * @目录
 *    1. 页面浏览器尺寸改变，banner 高度适应  图片轮播swiper插件
 *    2. 相关平台 滚动效果
 *    3. 公益项目 滚动效果
 *    4. 友情链接 滚动效果 
 *    
 */

$(function () {
    function isPC() {
        var userAgentInfo = navigator.userAgent;
        var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");
        var flag = true;
        for (var v = 0; v < Agents.length; v++) {
            if (userAgentInfo.indexOf(Agents[v]) > 0) {
                flag = false;
                break
            }
        }
        return flag
    }
    if (!isPC())
    {
        location.href = "http://m.cfpa.org.cn/index.html";
    }
	   GetwindowHeight();
		function GetwindowHeight(){
		    //var  bannerHeight=$(window).height()-$(".top_wapp").height()-$("nav").height();
		    var  bannerHeight=$(window).width() * 540 / 1920 ;
		    if ($(window).width() >= 1200) {
		        $(".banner").height(bannerHeight);
		        $(".swiper-container").height(bannerHeight);
		    }
		}

		$(window).resize(function(){
			 GetwindowHeight();
	 
		})
		  var mySwiper = new Swiper('.swiper-container',{
				pagination: '.pagination',
				loop:true,
				autoplay: 5000,
				followFinger : false,
				autoplayDisableOnInteraction : true,
				autoplayDisableOnInteraction : false,
				/*grabCursor: true,*/
				paginationClickable: true
			  })
			  $('.arrow-left').on('click', function(e){
				e.preventDefault()
				mySwiper.swipePrev()
			  })
			  $('.arrow-right').on('click', function(e){
				e.preventDefault()
				mySwiper.swipeNext()
			  })
	
	
	
	
	   //相关平台
	   platformAjaxSlide();
	   function platformAjaxSlide(id){ 
	   
	                jQuery(".platform .bd li").each(function(i){ jQuery(".platform .bd li").slice(i*6,i*6+6).wrapAll("<ul></ul>");});
	                $(".platform .bd ul").each(function(index, element) {
                      $(this).find("li").eq(2).css("margin","0px");
		              $(this).find("li").eq(5).css("margin","0px");
                    });
                    jQuery(".platform").slide({titCell:".hd ul",mainCell:".bd .picList",autoPage:true,effect:"leftLoop",autoPlay:false,trigger:"click"}); 
            /*$.ajax({
                url: 'dataDemoNoUl.html',
                type: 'get',
				async:false,
                data: { typeid: id},
                success: function (data) {
					$(".platform .bd .picList").empty();
					$(".platform .bd .picList").append(data); 
					jQuery(".platform .bd li").each(function(i){ jQuery(".platform .bd li").slice(i*6,i*6+6).wrapAll("<ul></ul>");});
	                $(".platform .bd ul").each(function(index, element) {
                      $(this).find("li").eq(2).css("margin","0px");
		              $(this).find("li").eq(5).css("margin","0px");
                    });
                    jQuery(".platform").slide({titCell:".hd ul",mainCell:".bd .picList",autoPage:true,effect:"leftLoop",autoPlay:true}); 	
                }
            });*/
	   }
	   
	   $(".platform .bd li").click(function () {
	       var index = $(".platform .bd li").index(this);
	       if ($(this).find("span").css("display") != "none") {
	           $(this).find("span").css("display", "none");
	       }
	       else {
	           $(".platform .bd li").eq(index).find("span").show().end().siblings().find("span").hide();
	       }
	   })

	    
	   
	  
       //公益项目 实现滚动播放
	   itemAjaxSlide("1");   //默认第一个tab的id
	   $(".item .item_tab ul li").click(function(){             //tab切换 
		   var index=$(".item .item_tab ul li").index(this);
		   var selectTabId= $(this).attr("id");
		   $(".item .item_tab ul li").eq(index).addClass("act").siblings().removeClass("act");
		  itemAjaxSlide(selectTabId);
	   })
	   function itemAjaxSlide(id){
            $.ajax({
                url: 'getProjectlist.ashx',
                type: 'get',
				async:false,
				data: { tid: id },
                success: function (data) {
					$(".item .itemlist .hd ul").empty();
					$(".item .itemlist .bd").empty();
					$(".item .itemlist .bd").append(data); 
					jQuery(".itemlist").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"leftLoop",autoPlay:true,vis:4,scroll:4}); 
                }
            });
		   
	   
	   }
	   
	   //友情链接  实现滚动播放
	   friendAjaxSlide("1");
	   $(".friend .friend_tab ul li").click(function(){
		   var index=$(".friend .friend_tab ul li").index(this);
		   var selectTabId= $(this).attr("id");
		   $(".friend .friend_tab ul li").eq(index).addClass("act").siblings().removeClass("act");
		   friendAjaxSlide(selectTabId);
	   })
	   function friendAjaxSlide(id){
		       $.ajax({
		        url: 'getfriendlinklist.ashx',
                type: 'get',
				async:false,
                data: { tid: id},
                success: function (data) {
					$(".friend .friend_list").remove();
					$(".friend").append("<div class='friend_list'><div class='bd clearfix'></div><div class='hd'><ul></ul></div></div>");
					jQuery(".friend .friend_list .bd").append(data);
					jQuery(".friend_list").slide({titCell:".hd ul",mainCell:".bd",autoPage:true,effect:"leftLoop",autoPlay:true,vis:8,scroll:8}); 
					/*if(id=="friend_4") //多行判定
					{	
					  jQuery(".friend .friend_list .bd li").each(function(i){ jQuery(".friend .friend_list .bd li").slice(i*24,i*24+24).wrapAll("<ul></ul><");});				
					  jQuery(".friend_list").slide({titCell:".hd ul",mainCell:".bd",autoPage:true,effect:"leftLoop",autoPlay:true,vis:1,scroll:1}); 
				    }
					else
					{
					   jQuery(".friend_list").slide({titCell:".hd ul",mainCell:".bd",autoPage:true,effect:"leftLoop",autoPlay:true,vis:8,scroll:8}); 
				    }*/
                 }
              });
		   
	    }
      
	    
})