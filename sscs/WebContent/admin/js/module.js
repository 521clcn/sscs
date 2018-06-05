/**
 * 
 */
$().ready(function() {

	var menu = [ {
		"id" : "newscenter.html",
		"text" : "新闻"
	}, {
		"id" : "notice.html",
		"text" : "公告"
	}, {
		"id" : "research.html",
		"text" : "研究"
	}, {
		"id" : "join.html",
		"text" : "人才招聘"
	} ];
	$("#accessid").combobox("loadData", menu);
	
	var series = [ {
		"id" : "1",
		"text" : "1"
	}, {
		"id" : "2",
		"text" : "2"
	}, {
		"id" : "3",
		"text" : "3"
	}, {
		"id" : "4",
		"text" : "4"
	} ];
	$("#series").combobox("loadData", series);
	
//	$("#accessid").combobox("setValue", "newscenter.html");
	
	$.post("../module/findById",{accessid:$("#accessid").val()},function(res){
	    var data = res.data;
	    if(data){
		    $("#id").val(data.id);
		    $("#accessid").combobox("setValue", data.accessid);
		    $("#title").val(data.title);
		    $("#content").val(data.content);
//		    $("#imageFile").val(data.picPath);
		    $("#series").combobox("setValue", data.series);
	    }
	});
		
	
	$("#accessid").combobox({
		onChange: function (n,o) {
			$.post("../module/findById",{accessid:$("#accessid").val()},function(res){
			    var data = res.data;
			    if(data){
				    $("#id").val(data.id);
				    $("#accessid").combobox("setValue", data.accessid);
				    $("#title").val(data.title);
				    $("#content").val(data.content);
//				    $("#imageFile").val(data.picPath);
				    $("#series").combobox("setValue", data.series);
			    }else{
			    	$("#id").val("");
				    $("#title").val("");
				    $("#content").val("");
//				    $("#imageFile").val(data.picPath);
				    $("#series").combobox("setValue", "");
			    }
			});
		}
	});

});
