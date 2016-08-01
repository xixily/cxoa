var center = {
	initEmployee : function(data, user) {
//		console.log("center.initEmployee!");
		$('#employee_datas')
				.datagrid(
						{
							title : '职工信息列表',
							iconCls : 'icon-edit',// 图标
							height : 'auto',
							fitColumns : true,
							singleSelect : true,
							url : 'employee/renshiUser.action',
							singleSelect : true,// 是否单选
							pagination : true,// 分页控件
							pageSize : 10,
							pageList : [ 10, 15, 20, 30, 50, 100, 200, 500],
							rownumbers : true,// 行号
							rowStyler : function(index, row) {
								if (row.leaveTime && row.leaveTime != '') {
									return 'background-color:#E88282;color:#fff;font-weight:bold;';
								}
								if(row.zhuanzhengTime =='实习生'){
									return 'background-color:yellow;';
								}	
							},
							columns : [ [ {
								field : 'id',
								title : '员工编号',
								sortable : true,
								width : 60
							}, {
								field : 'username',
								title : '姓名',
								sortable : true,
								width : 80
							}, {
								field : 'firstLevel',
								title : '公司',
								sortable : true,
								width : 100
							}, {
								field : 'secondLevel',
								title : '部门',
								sortable : true,
								width : 100
							}, {
								field : 'thirdLevel',
								title : '岗位',
								sortable : true,
								width : 100
							}, {
								field : 'fourthLevel',
								title : '小组',
								sortable : true,
								width : 100
							}, {
								field : 'position',
								title : '职位',
								sortable : true,
								width : 100
							}, {
								field : 'sex',
								title : '性别',
								sortable : true,
								width : 60
							}, {
								field : 'company',
								title : '公司名称',
								sortable : true,
								width : 80
							}, {
								field : 'insuranceCompany',
								title : '保险公司',
								sortable : true,
								width : 80
							}, {
								field : 'degree',
								title : '学历',
								sortable : true,
								width : 80
							}, {
								field : 'workPlace',
								title : '工作地点',
								sortable : true,
								width : 100
							}, {
								field : 'earlyEntryDate',
								title : '早期入职时间',
								sortable : true,
								width : 100
							}, {
								field : 'hiredate',
								title : '入职时间',
								sortable : true,
								width : 100
							}, {
								field : 'zhuanzhengTime',
								title : '转正时间',
								sortable : true,
								width : 100
							}, {
								field : 'leaveTime',
								title : '离职时间',
								sortable : true,
								width : 100
							}, {
								field : 'phoneNumber',
								title : '联系方式',
								sortable : true,
								width : 120
							}, {
								field : 'maritalStatus',
								title : '婚姻状况',
								sortable : true,
								width : 60
							} ] ],
							toolbar : '#renshi_toolbar',
							// toolbar: [{
							// text:'查看',
							// iconCls: 'icon-search',
							// handler: function(){
							// center.view();
							// }
							// },'-',{
							// text:'编辑',
							// iconCls: 'icon-edit',
							// handler: function(){
							// center.editEmployee();
							// }
							// },'-',{
							// text:'新增',
							// iconCls: 'icon-add',
							// handler: function(){
							// center.addEmployee();
							// }
							// },'-',{
							// text:'删除',
							// iconCls: 'icon-cancel',
							// handler: function(){
							// center.deleteEmployee();
							// }
							// },'-',{
							// text:'导出报表',
							// iconCls: 'icon-excel',
							// handler: function(){
							// center.exportExcel();
							// }
							// }],
							onDblClickCell : function(index, field, value) {
								center.view();
							}
						});
	},
	queryEmployee : function(data, src) {
		$('#employee_datas').datagrid({
			queryParams : data
		})
	},
	view : function() {
		var rights = session.user.roleId;
		$('#userName_info').dialog({
			title : '查看职员信息'
		});
		$("#textbox_id").textbox({
			onChange : function() {
				return
			}
		});
		$("#textbox_addrss").textbox({
			onChange : function() {
				return
			}
		});
		var userInfo = $('#employee_datas').datagrid('getSelected');
		var url = "user/getUserName.action";
		$.getJSON(url, userInfo, function(result) {
			if (result.success) {
				$('#updateUsesrname_form').form('load', result.obj);
				$('#btn_employeeMailto').attr('href', 'mailto:' + result.obj.email);
				disabledForm('updateUsesrname_form', true);
				$('#userName_info').window('open').window(
						'resize',
						{
							top : $(document).scrollTop()
									+ ($(window).height() - 480) * 0.5
						});
				$('#btn_employeeSave').linkbutton("disable");
				$('#btn_employeeRest').linkbutton("disable");
				$('#btn_employeeEdit').linkbutton("enable");
			} else {
				alert(result.msg);
			}
		})
	},
	editEmployee : function() {
		if (session.user.roleId != 0 && session.user.roleId != 1) {
			alert('您没有编辑权限！~');
			return;
		}
		$('#userName_info').dialog({
			title : '编辑职员信息'
		});
		$("#textbox_id").textbox({
			onChange : function() {
				return
			}
		});
		$("#textbox_addrss").textbox({
			onChange : function() {
				return
			}
		});
		var userInfo = $('#employee_datas').datagrid('getSelected');
		var url = "user/getUserName.action";
		var form_url = "user/updateUserName.action"
		$('#updateUsesrname_form').form({
			url : form_url,
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					alert("更新成功！");
				} else {
					alert("更新失败！")
				}
			}
		});
		$.getJSON(url, userInfo, function(result) {
			if (result.success) {
				$('#updateUsesrname_form').form('load', result.obj);
				$('#btn_employeeMailto').attr('href', 'mailto:' + result.obj.email);
				$('#userName_info').window('open').window(
						'resize',
						{
							top : $(document).scrollTop()
									+ ($(window).height() - 480) * 0.5
						});
				disabledForm('updateUsesrname_form', false);
				$('#btn_employeeSave').linkbutton("enable");
				$('#btn_employeeRest').linkbutton("enable");
				$('#btn_employeeEdit').linkbutton("disable");
			} else {
				alert(result.msg);
			}
		})
	},
	openAddEEmployee : function() {
		if (session.user.roleId != 0 && session.user.roleId != 1) {
			alert('您没有编辑权限！~');
			return;
		}
		$('#updateUsesrname_form').form('clear');
		$('#userName_info').window('open').window('resize', {
			top : $(document).scrollTop() + ($(window).height() - 480) * 0.5
		});
		disabledForm('updateUsesrname_form', false);
		$('#btn_employeeSave').linkbutton("enable");
		$('#btn_employeeRest').linkbutton("enable");
		$('#btn_employeeEdit').linkbutton("disable");
	},
	addEmployee : function() {
		if (session.user.roleId != 0 && session.user.roleId != 1) {
			alert('您没有增加权限！~');
			return;
		}
		$('#userName_info').dialog({
			title : '增加职员'
		});
		$('#updateUsesrname_form').form('clear');
		$('#userName_info').window('open').window('resize', {
			top : $(document).scrollTop() + ($(window).height() - 480) * 0.5
		});
		disabledForm('updateUsesrname_form', false);
		$('#btn_employeeSave').linkbutton("enable");
		$('#btn_employeeRest').linkbutton("enable");
		$('#btn_employeeEdit').linkbutton("disable");
		$('#updateUsesrname_form').form('load', {
			id : -1
		});

		var url = "user/addUserName.action";
		$('#updateUsesrname_form').form({
			url : url,
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					alert("添加成功！");
				} else {
					alert("添加失败！")
				}
			}
		});
		$('#text_nation').textbox('setValue', '汉');
		$('#combox_sex').combobox('setValue', '男');
		$("#textbox_id").textbox(
				{
					onChange : function(newValue, oldValue) {
						if (oldValue && oldValue.length > 0) {
							return;
						}
						borthday = newValue.substr(6, 4) + '.'
								+ newValue.substr(10, 2) + '.'
								+ newValue.substr(12, 2);
						$('#textbox_borth').textbox('setValue', borthday);
					}
				})
		$("#textbox_addrss").textbox({
			onChange : function(newValue, oldValue) {
				if (oldValue && oldValue.length > 0) {
					return;
				}
				$('#textbox_hukou').textbox('setValue', newValue);
			}
		})

	},
	deleteEmployee : function() {
//		console.log('deleteEmployee');
		if (session.user.roleId != 0 && session.user.roleId != 1) {
			alert('您没有删除权限！~');
			return;
		}
		var userInfo = $('#employee_datas').datagrid('getSelected');
		var url = "user/deleteUserName.action";
		$.getJSON(url, userInfo, function(result) {
			if (result.success) {
				$.messager.alert(result.msg,result.msg);
			} else {
				$.messager.alert(result.msg,result.msg);
			}
		})
	},
	exportExcel : function(type) {
		console.log('exportExcel:' + type);
		var exportParam = {};
		var form = $('#renshiquery_form');
		var data = getDataOfForm(form);
		downloadForm.createForm();
		var url = "file/exportExcel.action";
		var mydate = new Date();
		var year = mydate.getFullYear(); // 获取完整的年份(4位,1970-????)
		var month = mydate.getMonth() + 1; // 获取当前月份(0-11,0代表1月)
		var monthb = month - 1;
		var montha = month + 1;
		var date = year + (month < 10 ? ('0' + month) : month);
		var dateBefore = year + (monthb < 10 ? ('0' + monthb) : monthb);
		var dateAfter = year + (montha < 10 ? ('0' + montha) : montha);
		exportParam.configurable = 'reportForm';
		if (!type || type == 0) {
			delete exportParam.configurable;
			if (data.succeed) {
				exportParam = data.data;
			}
		} else if (type == 111 || type == 112 || type == 113 || type == 121
				|| type == 122 || type == 123) {
			if (type == 111) {
				exportParam.configurable_value = dateBefore + "入职";
				exportParam.levelc = "非实习生";
			} else if (type == 112) {
				exportParam.configurable_value = date + "入职";
				exportParam.levelc = "非实习生";
			} else if (type == 113) {
				exportParam.configurable_value = dateAfter + "入职";
				exportParam.levelc = "非实习生";
			} else if (type == 121) {
				exportParam.configurable_value = dateBefore + "入职";
				exportParam.levelc = "实习生";
			} else if (type == 122) {
				exportParam.configurable_value = date + "入职";
				exportParam.levelc = "实习生";
			} else {
				exportParam.configurable_value = dateAfter + "入职";
				exportParam.levelc = "实习生";
			}
		} else if (type == 211 || type == 212 || type == 213 || type == 221
				|| type == 222 || type == 223) {
			if (type == 211) {
				exportParam.configurable_value = dateBefore + "转正";
				exportParam.levelc = "非实习生";
			} else if (type == 212) {
				exportParam.configurable_value = date + "转正";
				exportParam.levelc = "非实习生";
			} else if (type == 213) {
				exportParam.configurable_value = dateAfter + "转正";
				exportParam.levelc = "非实习生";
			} else if (type == 221) {
				exportParam.configurable_value = dateBefore + "转正";
				exportParam.levelc = "实习生";
			} else if (type == 222) {
				exportParam.configurable_value = date + "转正";
				exportParam.levelc = "实习生";
			} else {
				exportParam.configurable_value = dateAfter + "转正";
				exportParam.levelc = "实习生";
			}
		} else if (type == 311 || type == 312 || type == 313 || type == 321
				|| type == 322 || type == 323) {
			if (type == 311) {
				exportParam.configurable_value = dateBefore + "离职";
				exportParam.levelc = "非实习生";
			} else if (type == 312) {
				exportParam.configurable_value = date + "离职";
				exportParam.levelc = "非实习生";
			} else if (type == 313) {
				exportParam.configurable_value = dateAfter + "离职";
				exportParam.levelc = "非实习生";
			} else if (type == 321) {
				exportParam.configurable_value = dateBefore + "离职";
				exportParam.levelc = "实习生";
			} else if (type == 322) {
				exportParam.configurable_value = date + "离职";
				exportParam.levelc = "实习生";
			} else {
				exportParam.configurable_value = dateAfter + "离职";
				exportParam.levelc = "实习生";
			}
		} else if (type == 411 || type == 412 || type == 413 || type == 421
				|| type == 422 || type == 423) {
			if (month == 411) {
				exportParam.configurable_value = dateBefore + "异动";
			} else if (type == 412) {
				exportParam.configurable_value = date + "异动";
			} else if (type == 413) {
				exportParam.configurable_value = dateAfter + "异动";
			} else if (type == 421) {
				exportParam.configurable_value = dateBefore + "异动";
				exportParam.levelc = "实习生";
			} else if (type == 422) {
				exportParam.configurable_value = date + "异动";
				exportParam.levelc = "实习生";
			} else {
				exportParam.configurable_value = dateAfter + "异动";
				exportParam.levelc = "实习生";
			}
		}
		$("#export_query").form('submit', {
			url : url,
			queryParams : exportParam,
			onSubmit : function() {
				console.log("正在导出,请稍后");
			},
			onLoadSuccess : function() {
				downloadForm.destoryForm();
			}
		});
	},
	helpEmployee : function() {
	},
	reloadQueryForm : function(dom) {
		alert(dom);
	},
	wages : {
		openWages : function(src) {
			disabledForm('updatewages_form', true);
			session.formData = getDataOfForm($('#updateUsesrname_form')).data;
			$('#wages_username').html(session.formData.username);
			$('#wages_identity').html(session.formData.identityCard);
			$('#wages_company').html(session.formData.company);
			var queryParam = {};
			queryParam.id = session.formData.id;
			// queryParam.id = $('#form_hidden_ID')[0].value;
			// $('#wages_hidden_employeeid').val(queryParam.id);
			session.wadgeData = {};
			$.getJSON("employee/getWagesList.action", queryParam, function(
					result) {
				session.wadgeData = result;
				var dataGrid = {};
				dataGrid.total = result.length;
				dataGrid.rows = result;
				if (result && result.length > 0) {
					var salary = 0;
					$.each(result, function(n, obj) {
						salary += obj.salary;
					})
					$('#wages_totalSalary').html(salary);
				} else {
					$('#wages_totalSalary').html(0);
				}
				$('#datagrid_wages').datagrid(
						{
							height : 'auto',
							fitColumns : true,
							data : dataGrid,
							singleSelect : true,// 是否单选
							rownumbers : true,// 行号
							pagination : true,// 分页控件
							pageSize : 5,
							pageList : [ 5, 15, 20, 30, 50, 100 ],
							columns : [ [ {
								field : 'id',
								title : '工资编号',
								width : 80
							}, {
								field : 'salary',
								title : '工资总额',
								width : 100,
								editor : {
									type : 'numberbox',
									options : {
										precision : 2
									}
								}
							}, {
								field : 'radix',
								title : '基数',
								width : 100,
								editor : {
									type : 'numberbox',
									options : {
										precision : 2
									}
								}
							}, {
								field : 'identityCard',
								title : '身份证号码',
								width : 120,
								editor : 'textbox'
							}, {
								field : 'company',
								title : '公司名称',
								width : 100,
								editor : 'textbox'
							}, {
								field : 'accountBank',
								title : '开户行',
								width : 100,
								editor : 'textbox'
							}, {
								field : 'account',
								title : '职工帐号',
								width : 180,
								editor : 'textbox'
							}, {
								field : 'householdType',
								title : '户口性质',
								width : 100,
								editor : 'textbox'
							} ] ],
							onClickCell : center.wages.onClickCell,
							onEndEdit : center.wages.onEndEdit,
							onDblClickCell : function(index, field, value) {
								center.wages.endEditing();
								session.append = false;
								$('#wages_add').css('display', 'none');
								$('#wages_save').css('display', '');
								$('#wages_edit').css('display', '');
								center.wages.eidtWages();
							},
							toolbar : [
									{
										text : '新增',
										iconCls : 'icon-add',
										handler : function() {
											center.wages.append();
											// alert('新增');
										}
									},
									'-',
									{
										text : '删除',
										iconCls : 'icon-remove',
										handler : function() {
											confirmDialog.createDialog(
													"您确定要删除吗？",
													center.wages.removeit);
											// center.wages.removeit();
										}
									} ]
						})
				$('#userName_info').dialog('close');
				$('#updatewages_form').form('clear');
				$('#updatewages_form').form({
					url : 'employee/updateWages.action'
				});
				$('#dialog_wagesInfo').dialog("open");
				// $('#dialog_wagesInfo').window('open').window('resize',{top:$(document).scrollTop()
				// + ($(window).height()-480) * 0.5});
			});
		},
		eidtWages : function(index, field, value) {
			disabledForm('updatewages_form', true);
			var userInfo = $('#datagrid_wages').datagrid('getSelected');
			// $.getJSON("employee/getWages.action",userInfo,function(result){
			$('#updatewages_form').form('load', userInfo);
			// })
		},
		onClickCell : function(index, field) {
			session.append = false;
			$('#wages_add').css('display', 'none');
			$('#wages_save').css('display', '');
			$('#wages_edit').css('display', '');
			if (editIndex != index) {
				if (center.wages.endEditing()) {
					$('#datagrid_wages').datagrid('selectRow', index).datagrid(
							'beginEdit', index);
					var ed = $('#datagrid_wages').datagrid('getEditor', {
						index : index,
						field : field
					});
					if (ed) {
						($(ed.target).data('textbox') ? $(ed.target).textbox(
								'textbox') : $(ed.target)).focus();
					}
					editIndex = index;
				} else {
					setTimeout(function() {
						$('#datagrid_wages').datagrid('selectRow', editIndex);
					}, 0);
				}
			}
		},
		endEditing : function() {
			if (editIndex == undefined) {
				return true
			}
			if ($('#datagrid_wages').datagrid('validateRow', editIndex)) {
				$('#datagrid_wages').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		},
		onEndEdit : function(index, row) {
			if (session.append) {
				return false
			}
			;
			var rowsData = $('#datagrid_wages').datagrid('getData');
			var salary = 0;
			$.each(rowsData.rows, function(n, obj) {
				if (n == 0) {
					$('#wages_identity').html(obj.identityCard);
					$('#wages_company').html(obj.company);
					$('#wages_username').html(obj.username);
				}
				salary += Number(obj.salary);
			})
			$('#wages_totalSalary').html(salary);

			var data = rowsData.rows[index];
			// data.company = data.companyName;
			wagesCalculate.calculateShebao(data, function(result) {
				if (result) {
					$('#updatewages_form').form('load', data);
				} else {
					alert('计算失败');
				}
			})
		},
		append : function() {
			session.append = true;
			$('#wages_add').css('display', '');
			$('#wages_save').css('display', 'none');
			$('#wages_edit').css('display', 'none');
			if (center.wages.endEditing()) {
				$('#updatewages_form').form('clear');
				$('#updatewages_form').form('load', session.formData);
				$(
						'#updatewages_form input[class="easyui-numberbox numberbox-f textbox-f"]')
						.each(function() {
							$(this).numberbox({
								value : 0
							});
						});
				disabledForm('updatewages_form', false);
				$('#wages_id').textbox({
					disabled : true,
					value : ''
				});
				$('#wages_hidden_employeeid').val(session.formData.id);
				$("#wages_radix").textbox(
						{
							onChange : function(newValue, oldValue) {
								var data = {};
								data.radix = newValue;
								data.company = $(
										'#wages_company input[name="company"]')
										.val();
								wagesCalculate.calculateShebao(data, function(
										result) {
									if (result) {
										$('#updatewages_form').form('load',
												data);
									}
								})
							}
						})
			}
		},
		removeit : function(confirmId) {
			if (editIndex == undefined) {
				return
			}
			var selected = $('#datagrid_wages').datagrid('getSelected');
			if (selected && selected.id != '0') {
				$.getJSON("employee/deleteWages.action", selected,
						function(result) {
							confirmDialog.destoryDialog(confirmId);
							if (result.success) {
								$('#datagrid_wages').datagrid('cancelEdit',
										editIndex).datagrid('deleteRow',
										editIndex);
								editIndex = undefined;
								var rowsData = $('#datagrid_wages').datagrid(
										'getData');
								var salary = 0;
								$.each(rowsData.rows,
										function(n, obj) {
											if (n == 0) {
												$('#wages_identity').html(
														obj.identityCard);
												$('#wages_company').html(
														obj.company);
												$('#wages_username').html(
														obj.username);
											}
											salary += Number(obj.salary);
										})
								$('#wages_totalSalary').html(salary);
							}
							$.messager.alert('消息', result.msg, 'info');
						})
			}
		},
		accept : function() {
			if (center.wages.endEditing()) {
				$('#datagrid_wages').datagrid('acceptChanges');
			}
		},
		reject : function() {
			$('#datagrid_wages').datagrid('rejectChanges');
			editIndex = undefined;
		},
		getChanges : function() {
			var rows = $('#datagrid_wages').datagrid('getChanges');
			alert(rows.length + ' rows are changed!');
		},
		editWages : function() {
			$('#wages_add').css('display', 'none');
			$('#wages_save').css('display', '');
			$('#wages_edit').css('display', '');
			disabledForm('updatewages_form', false);
		},
		updateWages : function(dom) {
			$('#wages_hidden_employeeid').val(session.formData.id);
			$('#updatewages_form').form({
				url : 'employee/updateWages.action'
			});
			submitForm(dom, function(result) {
				if (result.success) {
					center.wages.openWages();
					$.messager.alert('消息', result.msg, 'info');
				}
			});
			disabledForm('updatewages_form', true);
		},
		addWages : function(dom) {
			$('#wages_hidden_employeeid').val(session.formData.id);
			$('#updatewages_form').form({
				url : 'employee/addWages.action'
			});
			submitForm(dom, function(result) {
				if (result.success) {
					center.wages.openWages();
					$.messager.alert('消息', result.msg, 'info');
				}
			});
			disabledForm('updatewages_form', true);
		}
	},
	shebao : {
		onDblClickCell : function(index, field, value) {
			if (shebaoEdit != index) {
				if (center.shebao.endEditing()) {
					$('#datagrid_shebao').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					var ed = $('#datagrid_shebao').datagrid('getEditor', {
						index : index,
						field : field
					});
					if (ed) {
						($(ed.target).data('textbox') ? $(ed.target).textbox(
								'textbox') : $(ed.target)).focus();
					}
					shebaoEdit = index;
				} else {
					setTimeout(function() {$('#datagrid_shebao').datagrid('selectRow',shebaoEdit);
							}, 0);
				}
			}
			if(!shebaoEdit){
				var selectedData = $('#datagrid_shebao').datagrid('getSelected');
				session.editRow = $.extend({},selectedData);
			}
		},
		endEditing : function() {
			if (shebaoEdit == undefined) {
				return true
			}
			if ($('#datagrid_shebao').datagrid('validateRow', shebaoEdit)) {
				$('#datagrid_shebao').datagrid('endEdit', shebaoEdit);
				shebaoEdit = undefined;
				return true;
			} else {
				return false;
			}
		},
		onEndEdit : function(index,row,changes) {
			var message = "您确定要更新吗？";
			var url = "employee/updateShebao.action";
			if(shebaoAdd){
				message = "您确定要增加吗？";
				url = "employee/addShebao.action";
			}
			confirmDialog.createDialog(
					message,
					function(confirmId){
						var data = row;
						$.post(url, data, function(result){
							var result =  eval("(" + result + ")");
							confirmDialog.destoryDialog(confirmId);
							if (!result.success) {
								if(shebaoAdd){
									center.shebao.removeit(1);
									shebaoAdd = undefined;
									return;
								}
								$('#datagrid_shebao').datagrid('getData').rows[index] = session.editRow;
								$('#datagrid_shebao').datagrid('refreshRow',index);
							}
							if(shebaoAdd){
								$('#datagrid_shebao').datagrid('reload');
								shebaoAdd = undefined;
								return;
							}
							var selectedData = $('#datagrid_shebao').datagrid('getSelected');
							session.editRow = $.extend({},selectedData);
							$.messager.alert('消息', result.msg, 'info');
						});
					},function(){
						if(shebaoAdd){
							center.shebao.removeit(1);
							shebaoAdd = undefined;
							return;
						}
						$('#datagrid_shebao').datagrid('getData').rows[index] = session.editRow;
						var selectedData = $('#datagrid_shebao').datagrid('getSelected');
						session.editRow = $.extend({},selectedData);
						$('#datagrid_shebao').datagrid('refreshRow',index);
					});
		},
		append : function() {
			if (center.shebao.endEditing()) {
				shebaoAdd = 1;
				var selectedData = $('#datagrid_shebao').datagrid('getSelected');
				if(!selectedData){
					selectedData = {};
					selectedData.company = "世纪超星";
					selectedData.shebaoType = "养老保险";
					selectedData.typeCode = 1;
					selectedData.radixMin = 0;
					selectedData.radixMax = 0;
					selectedData.cRadio = 0;
					selectedData.radio = 0;
					selectedData.cFixedValue = 0;
					selectedData.fixedValue = 0;
				}
				$('#datagrid_shebao').datagrid('appendRow',selectedData);
				shebaoEdit = $('#datagrid_shebao').datagrid('getRows').length - 1;
				$('#datagrid_shebao').datagrid('selectRow', shebaoEdit)
						.datagrid('beginEdit', shebaoEdit);
			}
		},
		removeit : function(confirmId) {
			if(confirmId){
				shebaoEdit = $('#datagrid_shebao').datagrid('getRows').length - 1;
			}
			if (shebaoEdit == undefined) {
				return
			}
			$('#datagrid_shebao').datagrid('cancelEdit', shebaoEdit).datagrid(
					'deleteRow', shebaoEdit);
			shebaoEdit = undefined;
		},
		remove : function() {
			var selectedData = $('#datagrid_shebao').datagrid('getSelected');
			var message = "您确定要删除<strong>【" + selectedData.company + "-" + selectedData.shebaoType + "】</strong>吗？";
			confirmDialog.createDialog(
					message,
					function(confirmId){
						$.post('employee/deleteShebao.action', selectedData, function(result){
							confirmDialog.destoryDialog(confirmId);
							var result =  eval("(" + result + ")");
							$.messager.alert("删除结果", result.msg);
							$('#datagrid_shebao').datagrid('reload');
						})
					});
		},
		accept : function() {
			if (center.shebao.endEditing()) {
				$('#datagrid_shebao').datagrid('acceptChanges');
			}
		},
		reject : function() {
			$('#datagrid_shebao').datagrid('rejectChanges');
			shebaoEdit = undefined;
		},
		getChanges : function() {
			var rows = $('#datagrid_shebao').datagrid('getChanges');
			alert(rows.length + ' rows are changed!');
		},
		queryShebao : function(data, src) {
			$('#datagrid_shebao').datagrid({
				queryParams : data
			})
		}
	}
}
var shebaoAdd = undefined;
var shebaoEdit = undefined;