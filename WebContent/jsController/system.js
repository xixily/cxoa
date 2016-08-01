var system = {
		menus : {
			queryMenus : function(data, src) {
				$('#datagrid_menus').datagrid({
					queryParams : data
				})
			}
		}
}