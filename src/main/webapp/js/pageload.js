function reload(id) {
	$(id).datagrid('reload');
	$(id).datagrid('clearChecked');
	$(id).datagrid('clearSelections');
	$(id).datagrid('unselectAll');
};

function reloadParent(id) {
	$(id).bootstrapTable('refresh',{url : '<%=path%>/orderBonded/findAll.do'} );
//	window.opener.$(id).datagrid('reload');
//   	window.opener.$(id).datagrid('clearChecked');
//   	window.opener.$(id).datagrid('clearSelections');
//   	window.opener.$(id).datagrid('unselectAll');
};

function unSelect(id) {
	$(id).datagrid('clearChecked');
	$(id).datagrid('clearSelections');
	$(id).datagrid('unselectAll');
};