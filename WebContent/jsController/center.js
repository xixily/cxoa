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
				singleSelect : true,// 是否单选
				pagination : true,// 分页控件
				pageSize : 10,
				pageList : [ 10, 15, 20, 30, 50, 100 ],
				rownumbers : true,// 行号
				columns : [ [ {
					field : 'id',
					title : '员工编号',
					width : 0
				}, {
					field : 'username',
					title : '姓名',
					width : 4
				}, {
					field : 'firstLevel',
					title : '公司',
					width : 4
				}, {
					field : 'secondLevel',
					title : '部门',
					width : 5
				}, {
					field : 'thirdLevel',
					title : '岗位',
					width : 5
				}, {
					field : 'fourthLevel',
					title : '小组',
					width : 5
				}, {
					field : 'position',
					title : '职位',
					width : 4
				}, {
					field : 'sex',
					title : '性别',
					width : 3
				}, {
					field : 'company',
					title : '公司名称',
					width : 4
				}, {
					field : 'insuranceCompany',
					title : '保险公司',
					width : 4
				}, {
					field : 'degree',
					title : '学历',
					width : 4
				}, {
					field : 'workPlace',
					title : '工作地点',
					width : 5
				}, {
					field : 'earlyEntryDate',
					title : '早期入职时间',
					width : 4
				}, {
					field : 'hiredate',
					title : '入职时间',
					width : 4
				}, {
					field : 'zhuanzhengTime',
					title : '转正时间',
					width : 4
				}, {
					field : 'leaveTime',
					title : '离职时间',
					width : 4
				}, {
					field : 'phoneNumber',
					title : '联系方式',
					width : 4
				}, {
					field : 'maritalStatus',
					title : '婚姻状况',
					width : 4
				} ] ],
				/*toolbar : '#renshi_toolbar'*/
				toolbar: [{
		            text:'查看',
		            iconCls: 'icon-search',
		            handler: function(){
		            	center.view();
		            }
		        },'-',{
		            text:'编辑',
		            iconCls: 'icon-edit',
		            handler: function(){
		            	center.editEmployee();
		            }
		        },'-',{
		            text:'新增',
		            iconCls: 'icon-add',
		            handler: function(){
		            	center.addEmployee();
		            }
		        },'-',{
		            text:'删除',
		            iconCls: 'icon-cancel',
		            handler: function(){
		            	center.deleteEmployee();
		            }
		        },'-',{
		            text:'导出报表',
		            iconCls: 'icon-excel',
		            handler: function(){
		            	center.exportExcel();
		            }
		        }],
		        onDblClickCell: function(index,field,value){
		        	center.view();
		    	}
			});
		},
		queryEmployee: function(data, src){
			console.log("center.initEmployee!");
			$('#employee_datas').datagrid({
				queryParams : data
			})
		},
		view: function(){
			var rights = session.user.rights;
			console.log('editEmployee');
			$('#userName_info').dialog({title:'查看职员信息'});
			$("#textbox_id").textbox({onChange:function(){return}});
			$("#textbox_addrss").textbox({onChange:function(){return}});
			var userInfo = $('#employee_datas').datagrid('getSelected');
			var url = "user/getUserName.action";
			$.getJSON(url, userInfo, function(result){
				if(result.success){
					console.log('o%', result.obj);
					$('#updateUsesrname_form').form('load', result.obj);
					disabledForm('updateUsesrname_form', true);
					$('#userName_info').window('open').window('resize',{top:$(document).scrollTop() + ($(window).height()-480) * 0.5});  
					$('#btn_employeeSave').linkbutton("disable");
					$('#btn_employeeRest').linkbutton("disable");
					$('#btn_employeeEdit').linkbutton("enable");
				}else{
					alert(result.msg);
				}
			})
		},
		editEmployee : function(){
			if(session.user.rights != 0 && session.user.rights != 1){
				alert('您没有编辑权限！~');
				return ;
			}
			$('#userName_info').dialog({title:'编辑职员信息'});
			$("#textbox_id").textbox({onChange:function(){return}});
			$("#textbox_addrss").textbox({onChange:function(){return}});
			console.log('editEmployee');
			var userInfo = $('#employee_datas').datagrid('getSelected');
			var url = "user/getUserName.action";
			var form_url = "user/updateUserName.action"
			$('#updateUsesrname_form').form({
				url : form_url,
				success : function(result){
					var result = eval('(' + result + ')');
					if(result.success){
						alert("更新成功！");
					}else{
						alert("更新失败！")
					}
				}
			});
			$.getJSON(url, userInfo, function(result){
				if(result.success){
					console.log('o%', result.obj);
					$('#updateUsesrname_form').form('load', result.obj);
					$('#userName_info').window('open').window('resize',{top:$(document).scrollTop() + ($(window).height()-480) * 0.5});
					disabledForm('updateUsesrname_form', false);
					$('#btn_employeeSave').linkbutton("enable");
					$('#btn_employeeRest').linkbutton("enable");
					$('#btn_employeeEdit').linkbutton("disable");
				}else{
					alert(result.msg);
				}
			})
		},
		openAddEEmployee : function(){
			if(session.user.rights != 0 && session.user.rights != 1){
				alert('您没有编辑权限！~');
				return ;
			}
			$('#updateUsesrname_form').form('clear');
			$('#userName_info').window('open').window('resize',{top:$(document).scrollTop() + ($(window).height()-480) * 0.5});
			disabledForm('updateUsesrname_form', false);
			$('#btn_employeeSave').linkbutton("enable");
			$('#btn_employeeRest').linkbutton("enable");
			$('#btn_employeeEdit').linkbutton("disable");
		},
		addEmployee : function(){
			if(session.user.rights != 0 && session.user.rights != 1){
				alert('您没有编辑权限！~');
				return ;
			}
			$('#userName_info').dialog({title:'增加职员'});
			$('#updateUsesrname_form').form('clear');
			$('#userName_info').window('open').window('resize',{top:$(document).scrollTop() + ($(window).height()-480) * 0.5});
			disabledForm('updateUsesrname_form', false);
			$('#btn_employeeSave').linkbutton("enable");
			$('#btn_employeeRest').linkbutton("enable");
			$('#btn_employeeEdit').linkbutton("disable");
			$('#updateUsesrname_form').form('load',{id:-1});
			
			var url = "user/addUserName.action";
			$('#updateUsesrname_form').form({
				url : url,
				success : function(result) {
					var result = eval('(' + result + ')');
					if(result.success){
						alert("添加成功！");
					}else{
						alert("添加失败！")
					}
			}
			});
			$('#text_nation').textbox('setValue','汉');
			$('#combox_sex').combobox('setValue','男');
			$("#textbox_id").textbox({
                onChange:function(newValue,oldValue){
                	var borthday = '';
                	if(newValue&&newValue.length != 18){
                		alert('输入长度有问题，您输入了：' + newValue.length + '位');
                		return;
                	}
					if(oldValue&&oldValue.length>0){
						return;
					}
                	console.log(newValue.length);
                	borthday = newValue.substr(6,4) + '.' + newValue.substr(10,2) + '.' + newValue.substr(12,2);
                	$('#textbox_borth').textbox('setValue',borthday);
                }
			})
			$("#textbox_addrss").textbox({
				onChange:function(newValue,oldValue){
					if(oldValue&&oldValue.length>0){
						return;
					}
					$('#textbox_hukou').textbox('setValue',newValue);
				}
			})
			
		},
		deleteEmployee : function(){
			console.log('deleteEmployee');
			if(session.user.rights != 0 && session.user.rights != 1){
				alert('您没有编辑权限！~');
				return ;
			}
			var userInfo = $('#employee_datas').datagrid('getSelected');
			var url = "user/deleteUserName.action";
			$.getJSON(url, userInfo, function(result){
				if(result.success){
					alert(result.msg);
				}else{
					alert(result.msg);
				}
			})
		},
		exportExcel : function(){
			console.log('exportExcel');
		},
		helpEmployee : function(){
			console.log('helpEmployee');
		},
		reloadQueryForm : function(dom){
			alert(dom);
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