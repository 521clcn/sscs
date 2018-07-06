/**
 * 
 */
$().ready(function() {
	initUserTable();
	initUserData();
	$("#admin_menu .active", parent.document).removeAttr('class');
	$("#newsList1", parent.document).parent().addClass("active");
	$("#newsList1", parent.document).parent().parent().parent().addClass("active");
	$("#newsList1", parent.document).parent().parent().parent().parent().parent().addClass("active");
	
	$("#edit").click(function(){
		var row = $('#newsTable').datagrid('getSelected');
		if (row){
			$("#contentFrame", parent.document).attr("src","../admin/news.html?id="+row.id);  
		}else{
			$('#messageContent').html("请选择修改的新闻");
			$('#message').dialog('open').dialog('setTitle','提示');
		}
	});
	
	$("#view").click(function(){
		var row = $('#newsTable').datagrid('getSelected');
		if (row){
			var url = window.location.href;
			var urls = url.split("/admin");
			
			window.open(urls[0]+"/news.html?id="+row.id);

		}else{
			$('#messageContent').html("请选择预览新闻");
			$('#message').dialog('open').dialog('setTitle','提示');
		}
	});
	
	$("#release").click(function(){
		var row = $('#newsTable').datagrid('getSelected');
		if (row){
			$.messager.confirm('确认','确定发布吗?',function(r){
				if (r){
					$.post('../news/release',{id:row.id},function(result){
						if (result.suc=="yes"){
							$('#messageContent').html("发布成功");
							$('#message').dialog('open').dialog('setTitle','提示');
							
							row.status = "1";
							var index = $("#newsTable").datagrid("getRowIndex", row);
							$("#newsTable").datagrid("refreshRow", index);
						} else {
							$.messager.show({	// show error message
								title: 'Error',
								msg: result.msg
							});
						}
					},'json');
				}
			});
		}else{
			$('#messageContent').html("请选择发布的新闻");
			$('#message').dialog('open').dialog('setTitle','提示');
		}
	});
	
	$("#delete").click(function(){
		var row = $('#newsTable').datagrid('getSelected');
		if (row){
			$.messager.confirm('确认','确定删除新闻吗?',function(r){
				if (r){
					$.post('../news/delete',{id:row.id},function(result){
						if (result.suc=="yes"){
							$('#messageContent').html("删除成功");
							$('#message').dialog('open').dialog('setTitle','提示');
							
							var index = $("#newsTable").datagrid("getRowIndex", row);
							$("#newsTable").datagrid("deleteRow", index);
						} else {
							$.messager.show({	// show error message
								title: 'Error',
								msg: result.msg
							});
						}
					},'json');
				}
			});
		}else{
			$('#messageContent').html("请选择删除的新闻");
			$('#message').dialog('open').dialog('setTitle','提示');
		}
	});

});

function initUserData(){
	var accessid= "1";
	
	var options = $("#newsTable" ).datagrid("getPager" ).data("pagination" ).options;
    var curr = options.pageNumber;
    if(curr == 0) {
    	curr = 1;
    }
    var pageSize = options.pageSize;
	$.post("../news/admin/list", {startPage:curr,pageSize:pageSize,accessid:accessid}, function(res) {
		var list = res.data;
		var total = res.total;
		var datagridData = {
			total: total,
			rows: list
		};
		$('#newsTable').datagrid({
			loadFilter: pagerFilter
		}).datagrid('loadData', datagridData);
		
	});
}

function pagerFilter(data) {
	if (typeof data.length == 'number' && typeof data.splice == 'function') { // 判断数据是否是数组
		data = {
			total: data.length,
			rows: data
		};
	}
	var dg = $(this);
	var opts = dg.datagrid('options');
	var pager = dg.datagrid('getPager');
	pager.pagination({
		showPageList: true,
		onSelectPage: function(pageNum, pageSize) {
			opts.pageNumber = pageNum;
			opts.pageSize = pageSize;
			pager.pagination('refresh', {
				pageNumber: pageNum,
				pageSize: pageSize
			});
			initUserData();
		}
	});
	return data;
}

function initUserTable(){
	$("#newsTable").datagrid({
		maxHeight: 500,
		height: 500,
		striped:true,
		fitColumns: true,
		rownumbers: true,
		singleSelect:true,
		remoteSort:false,
		pagination: true,
		pageSize: 10,
		pageList: [10, 20, 50],
		loadMsg: '正在加载，请稍候...',
		rowStyler: function(index, row) {},
		columns:[[
			{field:"id",title:"id",hidden:true},
			{field:"title",title:"标题",width:"20%"},
			{field:"accessid",title:"栏目",width:"20%",
				formatter:function(value,row,index){
					if(value!=null&&value!=""){
						value = value.replace("1","首页新闻");
						value = value.replace("2","新闻");
						value = value.replace("3","公告");
						value = value.replace("4","研究");
						value = value.replace("5","人才招聘");
					}
					return value;
				}
			},
			{field:"creater",title:"创建人",width:"20%"},
			{field:"createdTime",title:"更新时间",width:"20%",
				formatter:function(value,row,index){
					if(value!=null&&value!=""){
						value = convertToDate(value);
					}
					return value;
				}
			},
			{field:"releaseTime",title:"发布时间",width:"20%",
				formatter:function(value,row,index){
					if(value!=null&&value!=""){
						value = convertToDate(value);
					}
					return value;
				}
			},
			{field:"status",title:"是否发布",width:"20%",
				formatter:function(value,row,index){
					if(value!="1"){
						return "否";
					}
					return "是";
				}
			}
		]]
	});
}

function convertToDate(millis){
	var date = new Date(millis);
	var year = date.getFullYear();
	var month= date.getMonth()+1;
	var day = date.getDate();
	var hour = date.getHours();
	var min = date.getMinutes();
	var second = date.getSeconds();
	
	if(month<10){
		month = "0"+month;
	}
	if(day<10){
		day ="0" + day;
	}
	if(hour<10){
		hour ="0"+hour;
	}
	if(min<10){
		min ="0"+min;
	}
	if(second<10){
		second="0"+second;
	}
	return year+"-"+month+"-"+day+"\n"+hour+":"+min+":"+second;
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
