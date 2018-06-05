/**
 * 
 */
$().ready(function(){	
	initUserTable();
	
	list();
	
	$("#new").click(function(){
		$('#dlg').dialog('open').dialog('setTitle','新建');
		$('#fm').form('clear');
	});

});

function list(){
	$.post("../user/list",{},function(res){
	    var data = res.data;
	    $("#dg").datagrid("loadData",data);
	});
}

function saveUser(){	
	$('#fm').form('submit',{
		url: '../user/save',
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (result.suc=="yes"){
				$('#dlg').dialog('close');		// close the dialog
				list();
			} else {
				$.messager.show({
					title: 'Error',
					msg: result.msg
				});
			}
		}
	});
}

function editUser(){
	var row = $('#dg').datagrid('getSelected');
	if (row){
		$('#dlg').dialog('open').dialog('setTitle','修改');
		$('#fm').form('load',row);
	}else{		
		$('#messageContent').html("请选择编辑用户");
		$('#message').dialog('open').dialog('setTitle','提示');
	}
}

function removeUser(){
	var row = $('#dg').datagrid('getSelected');
	if (row){
		$.messager.confirm('确认','确定删除该用户吗?',function(r){
			if (r){
				$.post('../user/delete',{id:row.id},function(result){
					if (result.suc=="yes"){
						list();
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
		$('#messageContent').html("请选择删除用户");
		$('#message').dialog('open').dialog('setTitle','提示');
	}
	
}

function initUserTable(){
	$("#dg").datagrid({
		maxHeight: 500,
		striped:true,
		fitColumns: true,
		rownumbers: true,
		singleSelect:true,
		loadMsg: '正在加载，请稍候...',
		rowStyler: function(index, row) {},
		columns:[[
			{field:"id",title:"id",hidden:true},
			{field:"username",title:"用户名",width:"20%"},
			{field:"password",title:"密码",width:"20%"},
			{field:"createdTime",title:"创建时间",width:"20%",
				formatter:function(value,row,index){
					if(value!=null&&value!=""){
						value = convertToDate(value);
					}
					return value;
				}
			},
			{field:"showname",title:"显示名称",width:"20%"},
			{field:"accessid",title:"权限",width:"20%",
				formatter:function(value,row,index){
					if(value!=null&&value!=""){
						value = value.replace("1","管理员");
						value = value.replace("2","新闻");
						value = value.replace("3","公告");
						value = value.replace("4","研究");
						value = value.replace("5","人才招聘");
					}
					return value;
				}}
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



