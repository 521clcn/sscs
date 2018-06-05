/**
 * 
 */
$().ready(function() {
	initTKDTable();
	$.post("../tkd/list", {}, function(res) {
		var data = res.data;
		$("#newsTable").datagrid("loadData", data);
	});
	
	$("#new").click(function(){
		$("#contentFrame", parent.document).attr("src","../admin/tkdinfo.html");  
	});
	
	$("#edit").click(function(){
		var row = $('#newsTable').datagrid('getSelected');
		if (row){
			$("#contentFrame", parent.document).attr("src","../admin/tkdinfo.html?id="+row.id);  
		}else{
			$('#messageContent').html("请选择修改的TKD");
			$('#message').dialog('open').dialog('setTitle','提示');
		}
	});
});

function initTKDTable(){
	$("#newsTable").datagrid({
		maxHeight: 500,
		striped:true,
		fitColumns: true,
		rownumbers: true,
		singleSelect:true,
		loadMsg: '正在加载，请稍候...',
		rowStyler: function(index, row) {},
		columns:[[
			{field:"id",title:"id",hidden:true},
			{field:"modelname",title:"页面名称",width:"20%"},
			{field:"modelurl",title:"页面地址",width:"20%"},
			{field:"title",title:"Title",width:"20%"},
			{field:"keywords",title:"Keywords",width:"20%"},
			{field:"description",title:"Description",width:"20%"}
		]]
	});
}

