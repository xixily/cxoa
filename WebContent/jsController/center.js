var center = {
		initEmployee : function(data, user){
			console.log("center.initEmployee!");
			$('#employee_datas').datagrid({
				title : '职工信息列表',
				iconCls : 'icon-edit',// 图标
				height : 'auto',
				fitColumns : true,
				singleSelect : true,
				url : 'employee/renshiUser.action',
//				queryParams : {
//					userName : '郭玉',
//					sex : '女',
//					position : '细胞核',
//					department : '人事'
//				},
				singleSelect : true,// 是否单选
				pagination : true,// 分页控件
				pageSize : 10,
				pageList : [ 10, 15, 20, 30, 50, 100 ],
				rownumbers : true,// 行号
				columns : [ [ {
					field : 'id',
					title : '员工编号',
					width : 60
				}, {
					field : 'username',
					title : '姓名',
					width : 80
				}, {
					field : 'firstLevel',
					title : '一级',
					width : 80
				}, {
					field : 'secondLevel',
					title : '二级',
					width : 100
				}, {
					field : 'thirdLevel',
					title : '三级',
					width : 100
				}, {
					field : 'fourthLevel',
					title : '四级',
					width : 100
				}, {
					field : 'position',
					title : '职位',
					width : 80
				}, {
					field : 'sex',
					title : '性别',
					width : 70
				}, {
					field : 'hiredate',
					title : '入职时间',
					width : 80
				}, {
					field : 'leaveTime',
					title : '离职时间',
					width : 80
				}, {
					field : 'workPlace',
					title : '工作地点',
					width : 96
				}, {
					field : 'phoneNumber',
					title : '联系方式',
					width : 80
				}, {
					field : 'degree',
					title : '学历',
					width : 80
				} ] ],
				toolbar : '#renshi_toolbar'
			});
		},
		queryEmployee: function(data, src){
			console.log("center.initEmployee!");
			$('#employee_datas').datagrid({
				queryParams : data
			})
		},
		editEmployee : function(){
			console.log('editEmployee');
		},
		addEmployee : function(){
			console.log('addEmployee');
		},
		deleteEmployee : function(){
			console.log('deleteEmployee');
		},
		helpEmployee : function(){
			console.log('helpEmployee');
		}
}
/**
 * 打开dialog的参考
 */
/*var url;
function newUser() {
	$('#dlg').dialog('open').dialog('center').dialog('setTitle', 'New User');
	$('#fm').form('clear');
	url = 'save_user.php';
}
function editUser() {
	var row = $('#dg').datagrid('getSelected');
	if (row) {
		$('#dlg').dialog('open').dialog('center').dialog('setTitle',
				'Edit User');
		$('#fm').form('load', row);
		url = 'update_user.php?id=' + row.id;
	}
}
function saveUser() {
	$('#fm').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.errorMsg) {
				$.messager.show({
					title : 'Error',
					msg : result.errorMsg
				});
			} else {
				$('#dlg').dialog('close'); // close the dialog
				$('#dg').datagrid('reload'); // reload the user data
			}
		}
	});
}
function destroyUser() {
	var row = $('#dg').datagrid('getSelected');
	if (row) {
		$.messager.confirm('Confirm',
				'Are you sure you want to destroy this user?', function(r) {
					if (r) {
						$.post('destroy_user.php', {
							id : row.id
						}, function(result) {
							if (result.success) {
								$('#dg').datagrid('reload'); // reload the
																// user data
							} else {
								$.messager.show({ // show error message
									title : 'Error',
									msg : result.errorMsg
								});
							}
						}, 'json');
					}
				});
	}

}*/