/**
 * 
 */
$().ready(function(){	
	var menu = [
		{"id":"1","text":"板块一"},
		{"id":"2","text":"板块二"},
		{"id":"3","text":"板块三"},
		{"id":"4","text":"板块四"}
		];
	
	$("#accessid").combobox("loadData", menu);

});


