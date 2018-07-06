/**
 * 
 */
$().ready(function(){	
		$.post("../user/user",{},function(res){
			var menu = [
				{"id":"2","text":"新闻"},
				{"id":"3","text":"公告"},
				{"id":"4","text":"研究"},
				{"id":"5","text":"人才招聘"}
				];
		    var data = res.data;
		    if(data.type!="0"){
			    var accessid = data.accessid;
			    if(accessid!=null){
			    	var ids = accessid.split(",");
			    	var userMenu = [];
			    	for(var i=0; i<ids.length; i++){
			    		if(ids[i]=="2"){
			    			userMenu.push(menu[0]);
			    		}else if(ids[i]=="3"){
			    			userMenu.push(menu[1]);
			    		}else if(ids[i]=="4"){
			    			userMenu.push(menu[2]);
			    		}else if(ids[i]=="5"){
			    			userMenu.push(menu[3]);
			    		}		    		
			    	}
			    	$("#accessid").combobox("loadData", userMenu);
			    }
		    }else{
		    	$("#accessid").combobox("loadData", menu);
		    }

		});
		
		var id = GetQueryString("id");
		if(id!=null){
			$.post("../news/findById",{id:id},function(res){
			    var data = res.data;
			    
			    $("#id").val(data.id);
			    $("#accessid").combobox("setValue", data.accessid);
			    $("#title").val(data.title);
			    
//			    editor.setContent(data.content);
			    editor.ready(function() {
			    	editor.setContent(data.content);
			    },2);
			});
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

