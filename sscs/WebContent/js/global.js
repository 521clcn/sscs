/*!
 * Javascript for global.js, based on jquery.
 * @author zeyi ren
 * @date 2016-6-14
 * @update 2016-6-14
 *
 * @目录
 *    
 *    1. 站内搜索效果
 *    2. 一级导航下拉动态效果 
 *    3. 返回页面顶部
 */
$(function(){
	
	
	
	//站内搜索
	SearchClick();
	function SearchClick(){ 
	  /*$(".search button").focus(function(){
		$(".search input").css({"width":"200px","border":"1px solid #ddd"});
	  })
	  
	  $(".search input").blur(function(){
		var this_val=$(this).val();
		if(this_val.length>0 ){	
		}else{	
			$(this).css({"width":"0px"}).attr("value","");
		}
	  })	*/
	  
	  $(".search button").mouseenter(function(){	
	    $(".search input").css({"width":"225px","border":"1px solid #ddd"});
	  })
	  
	  
	  $(".search input").blur(function(){
		var this_val=$(this).val();
		if(this_val.length>0 ){	
		}else{	
			$(this).css({"width":"0px"}).attr("value","");
			$(this).css({"border":"none"});
		}
	  })
		
    }
	
    //导航下拉
	NavMenuHover(); 
	function NavMenuHover(){ 
	
	  $('.nav ul> li').hover(function(){
		  $(this).stop(true).find('.subnav').show();	
	  },function(){
		  $(this).stop(true).find('.subnav').hide();		
	  });
	
	}


   //左右对齐框架
   JustifyBlock();
   function JustifyBlock(){
	     var left=$(".col_sidebar").height();
		 var right=$(".col_con").height();
		 if(left>right)
		 {
	       //$(".col_con").height(left-40);
		 }
		 else if(left<right){
			  $(".col_sidebar").height(right+40); 
		 }   
   }
   
   //返回页面顶部
   returnTop();
   function returnTop(){
		
	    $(".return_top").click(function(){
			$("html, body").animate({
				"scroll-top":0
			},"fast");
		});
		
		$(window).scroll(function(){//bug
		var _top=$(window).scrollTop();
		var _screenHeight=$(window).height();
		  if(_top>100){
			  
			  $(".return_top").show();
		  }else{
			  $(".return_top").hide();
		  }
	    });	
		
	}
})
//站长统计
var _hmt = _hmt || [];
(function () {
    var hm = document.createElement("script");
    hm.src = "//hm.baidu.com/hm.js?e022ea3cf38d5f49001cae64b8054676";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();