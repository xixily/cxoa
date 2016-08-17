var employee = {
	initEmployee : function(data, user) {
//		console.log("employee.initEmployee!");
		$('#employee_datas')
				.datagrid(
						{
							title : '职工信息列表',
							iconCls : 'icon-edit',// 图标
							height : 'auto',
//							fitColumns : true,
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
							columns : [ [ 
//							              {
//								field : 'id',
//								title : '员工编号',
//								sortable : true,
//								width : 60
//							}, 
							{
								field : 'username',
								title : '姓名',
								sortable : true,
								width : 60
							}, {
								field : 'firstLevel',
								title : '公司',
								sortable : true,
								width : 40
							}, {
								field : 'secondLevel',
								title : '部门',
								sortable : true,
								width : 80
							}, {
								field : 'thirdLevel',
								title : '岗位',
								sortable : true,
								width : 80
							}, {
								field : 'fourthLevel',
								title : '小组',
								sortable : true,
								width : 100
							}, {
								field : 'position',
								title : '职位',
								sortable : true,
								width : 80
							}, {
								field : 'sex',
								title : '性别',
								sortable : true,
								width : 40
							}, {
								field : 'identityCard',
								title : '身份证号',
								sortable : true,
								width : 125
							}, {
								field : 'borthDay',
								title : '出生年月',
								sortable : true,
								width : 80
							}, {
								field : 'nation',
								title : '民族',
								sortable : true,
								width : 40
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
								width : 80
							}, {
								field : 'hiredate',
								title : '入职时间',
								sortable : true,
								width : 80
							}, {
								field : 'zhuanzhengTime',
								title : '转正时间',
								sortable : true,
								width : 80
							}, {
								field : 'leaveTime',
								title : '离职时间',
								sortable : true,
								width : 80
							}, {
								field : 'phoneNumber',
								title : '联系方式',
								sortable : true,
								width : 80
							}, {
								field : 'graduatedSchool',
								title : '毕业学校',
								sortable : true,
								width : 100
							}, {
								field : 'major',
								title : '专业',
								sortable : true,
								width : 100
							}, {
								field : 'homeAddress',
								title : '家庭住址',
								sortable : true,
								width : 100
							}, {
								field : 'homeNumber',
								title : '家庭电话',
								sortable : true,
								width : 80
							}, {
								field : 'pastLeaveTime',
								title : '过去离职时间',
								sortable : true,
								width : 80
							}, {
								field : 'householdType',
								title : '户口性质',
								sortable : true,
								width : 60
							}, {
								field : 'insurance',
								title : '保险',
								sortable : true,
								width : 60
							}, {
								field : 'maritalStatus',
								title : '婚姻状况',
								sortable : true,
								width : 60
							}, {
								field : 'cellCore',
								title : '细胞核',
								sortable : true,
								width : 60
							}, {
								field : 'cellCoreEmail',
								title : '细胞核邮箱',
								sortable : true,
								width : 100
							}, {
								field : 'guidance',
								title : '指导',
								sortable : true,
								width : 60
							}, {
								field : 'guidanceEmail',
								title : '指导邮箱',
								sortable : true,
								width : 100
							}, {
								field : 'managementSystem',
								title : '管理制度',
								sortable : true,
								width : 100
							}, {
								field : 'signedTime',
								title : '签定时间',
								sortable : true,
								width : 100
							}, {
								field : 'terminationTime',
								title : '终止时间',
								sortable : true,
								width : 100
							}, {
								field : 'registeredAddress',
								title : '户口地址',
								sortable : true,
								width : 100
							}, {
								field : 'Postcode',
								title : '邮编',
								sortable : true,
								width : 80
							}, {
								field : 'socialSecurityHospital',
								title : '社保医院',
								sortable : true,
								width : 100
							}, {
								field : 'level',
								title : '级别',
								sortable : true,
								width : 80
							}, {
								field : 'email',
								title : '邮箱',
								sortable : true,
								width : 100
							} ] ],
							toolbar : '#renshi_toolbar',
							// toolbar: [{
							// text:'查看',
							// iconCls: 'icon-search',
							// handler: function(){
							// employee.view();
							// }
							// },'-',{
							// text:'编辑',
							// iconCls: 'icon-edit',
							// handler: function(){
							// employee.editEmployee();
							// }
							// },'-',{
							// text:'新增',
							// iconCls: 'icon-add',
							// handler: function(){
							// employee.addEmployee();
							// }
							// },'-',{
							// text:'删除',
							// iconCls: 'icon-cancel',
							// handler: function(){
							// employee.deleteEmployee();
							// }
							// },'-',{
							// text:'导出报表',
							// iconCls: 'icon-excel',
							// handler: function(){
							// employee.exportExcel();
							// }
							// }],
							onDblClickCell : function(index, field, value) {
								employee.view();
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
				$('#employee_datas').datagrid('reload');
				if (result.success) {
					$.messager.alert("添加提示", result.msg);
				} else {
					$.messager.alert("添加提示", result.msg);
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
	deleteEmployee : function(confirmId) {
//		console.log('deleteEmployee');
		if (session.user.roleId != 0 && session.user.roleId != 1) {
			alert('您没有删除权限！~');
			return;
		}
		var userInfo = $('#employee_datas').datagrid('getSelected');
		var url = "user/deleteUserName.action";
		$.getJSON(url, userInfo, function(result) {
			confirmDialog.destoryDialog(confirmId);
			$('#employee_datas').datagrid('reload');
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
//				dataGrid.total = result.length >= 6? result.length:6;
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
							onClickCell : employee.wages.onClickCell,
							onEndEdit : employee.wages.onEndEdit,
							onDblClickCell : function(index, field, value) {
								employee.wages.endEditing();
								session.append = false;
								$('#wages_add').css('display', 'none');
								$('#wages_save').css('display', '');
								$('#wages_edit').css('display', '');
								employee.wages.eidtWages();
							},
							toolbar : [
									{
										text : '新增',
										iconCls : 'icon-add',
										handler : function() {
											employee.wages.append();
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
													employee.wages.removeit);
											// employee.wages.removeit();
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
				if (employee.wages.endEditing()) {
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
			if (employee.wages.endEditing()) {
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
			if (employee.wages.endEditing()) {
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
					employee.wages.openWages();
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
					employee.wages.openWages();
					$.messager.alert('消息', result.msg, 'info');
				}
			});
			disabledForm('updatewages_form', true);
		}
	},
	shebao : {
		onDblClickRow : function(index,row) {
			if(!shebaoEdit){
				session.editRow = $.extend({},row);
			}
			if (shebaoEdit != index) {
				if (employee.shebao.endEditing()) {
					$('#datagrid_shebao').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					shebaoEdit = index;
				} else {
					setTimeout(function() {$('#datagrid_shebao').datagrid('selectRow',shebaoEdit);
							}, 0);
				}
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
									employee.shebao.removeit(1);
									shebaoAdd = undefined;
									return;
								}
								$('#datagrid_shebao').datagrid('getData').rows[index] = session.editRow;
								$('#datagrid_shebao').datagrid('refreshRow',index);
							}else{
								if(shebaoAdd){
									$('#datagrid_shebao').datagrid('reload');
									shebaoAdd = undefined;
									return;
								}
//								else{
//									$('#datagrid_shebao').datagrid('getData').rows[index] = session.editRow;
//									$('#datagrid_shebao').datagrid('refreshRow',index);
//								}
							}
							$.messager.alert('消息', result.msg, 'info');
						});
					},function(){
						if(shebaoAdd){
							employee.shebao.removeit(1);
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
			if (employee.shebao.endEditing()) {
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
			if (employee.shebao.endEditing()) {
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
	},
shebaoSummary : {
	queryShebaoSummary : function(data, src) {
		$('#datagrid_shebaoSummary').datagrid({
			queryParams : data
		})
	},
	view : function(index, row){
		
		$('#shebaoCompany_detail').dialog({
			title : '查看公司五险详情'
		});
		$('#shebaoCompany_detail').window('open').window(
				'resize',
				{
					top : $(document).scrollTop()
							+ ($(window).height() - 480) * 0.5
				});
		
		var company = row.company;
		$('#shebaoCompany_company').html(company);
		employee.shebaoSummary.updateDom(row);
		session.viewCompanyRow = row;
		session.viewCompanyIndex = index;
		var queryParam = {};
		queryParam.company = company;
		var url = "employee/getShebaoCompany.action";
		$('#datagrid_shebaoCompany').datagrid(
				{
					height : 'auto',
					fitColumns : true,
					url : url,
					queryParams : queryParam,
					singleSelect : true,// 是否单选
					rownumbers : true,// 行号
					pagination : true,// 分页控件
					pageSize : 15,
					loadFilter : function(data){
						if (typeof(data.d)=='number'){
							return data.d.toFixed(2);
						} else {
							return data;
						}
					},
					pageList : [ 10, 15, 20, 30, 50, 100 ],
					columns : [ [ {
						field : 'ck',
						checkbox : true,
						width : 30
					}, {
						field : 'username',
						title : '职员姓名',
						sortable : true,
						width : 80
					}, {
						field : 'identityCard',
						title : '身份证号',
						sortable : true,
						width : 100
					}, {
						field : 'rubaoTime',
						title : '入保时间',
						sortable : true,
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
						sortable : true,
						width : 100,
//						editor : {
//							type : 'numberbox',
//							options : {
//								precision : 2
//							}
//						}
					}, {
						field : 'subEndowmentIinsurance',
						title : '代扣养老保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					}, {
						field : 'subMedicare',
						title : '代扣医疗保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					}, {
						field : 'subUnemployedInsurance',
						title : '代扣失业保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					}, {
						field : 'subHouseIinsurance',
						title : '代扣住房保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					}, {
						field : 'cEndowmentIinsurance',
						title : '公司养老保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					}, {
						field : 'cMedicare',
						title : '公司医疗保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					} , {
						field : 'cUnemployedInsurance',
						title : '公司失业保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					} , {
						field : 'cHouseIinsurance',
						title : '公司住房保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					} , {
						field : 'cInjuryInsurance',
						title : '公司工伤保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					} , {
						field : 'cBirthIinsurance',
						title : '公司生育保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					}] ],
					toolbar : "#shebaoCompany_toolbar",
					footer : "#shebaoComany_footerbar",
//					toolbar: [{
//						iconCls: 'icon-edit',
//						text:'批量修改基数',
//						handler: function(){alert('edit')}
//					},'-',{
//						iconCls: 'icon-help',
//						handler: function(){alert('help')}
//					}],
					onDblClickRow : employee.shebaoSummary.onDblClickRow,
					onClickCell : employee.shebaoSummary.endEditing,
					onEndEdit: employee.shebaoSummary.onEndEdit}
					)
//					$('#datagrid_shebaoCompany').datagrid('enableFilter', [{}]); 不能用
	},
	onDblClickRow : function(index,row) {
		if(!shebaoCompanyEdit){
			session.editRow = $.extend({},row);
		}
		if (shebaoCompanyEdit != index) {
			if (employee.shebaoSummary.endEditing()) {
				$('#datagrid_shebaoCompany').datagrid('selectRow', index)
						.datagrid('beginEdit', index);
				shebaoCompanyEdit = index;
			} else {
				setTimeout(function() {$('#datagrid_shebaoCompany').datagrid('selectRow',shebaoCompanyEdit);
						}, 0);
			}
		}
		
	},
	endEditing : function() {
		if (shebaoCompanyEdit == undefined) {
			return true
		}
		if ($('#datagrid_shebaoCompany').datagrid('validateRow', shebaoCompanyEdit)) {
			$('#datagrid_shebaoCompany').datagrid('endEdit', shebaoCompanyEdit);
			shebaoCompanyEdit = undefined;
			return true;
		} else {
			return false;
		}
	},
	onEndEdit : function(index,row,changes) {
		var message = "您确定要更新吗？";
		var url = "employee/updateWages.action";
		confirmDialog.createDialog(
				message,
				function(confirmId){
					var data = row;
					$.post(url, data, function(result){
						var result =  eval("(" + result + ")");
						confirmDialog.destoryDialog(confirmId);
						if (!result.success) {
							$('#datagrid_shebaoCompany').datagrid('getData').rows[index] = session.editRow;
							$('#datagrid_shebaoCompany').datagrid('refreshRow',index);
						}else{
							session.viewCompanyRow.subEndowmentIinsurance += (row.subEndowmentIinsurance -session.editRow.subEndowmentIinsurance);
							session.viewCompanyRow.subMedicare += ( row.subMedicare - session.editRow.subMedicare);
							session.viewCompanyRow.subUnemployedInsurance += (row.subUnemployedInsurance - session.editRow.subUnemployedInsurance );
							session.viewCompanyRow.subHouseIinsurance += (row.subHouseIinsurance - session.editRow.subHouseIinsurance);
							session.viewCompanyRow.cEndowmentIinsurance += (row.cEndowmentIinsurance - session.editRow.cEndowmentIinsurance);
							session.viewCompanyRow.cMedicare += ( row.cMedicare - session.editRow.cMedicare);
							session.viewCompanyRow.cUnemployedInsurance += (row.cUnemployedInsurance - session.editRow.cUnemployedInsurance);
							session.viewCompanyRow.cHouseIinsurance += (row.cHouseIinsurance - session.editRow.cHouseIinsurance);
							session.viewCompanyRow.cBirthIinsurance += (row.cBirthIinsurance - session.editRow.cBirthIinsurance);
							employee.shebaoSummary.updateDom(session.viewCompanyRow);
							$('#datagrid_shebaoSummary').datagrid('refreshRow',session.viewCompanyIndex);
						}
						session.editRow = {};
						$.messager.alert('消息', result.msg, 'info');
					});
				},function(){
					$('#datagrid_shebaoCompany').datagrid('getData').rows[index] = session.editRow;
					$('#datagrid_shebaoCompany').datagrid('refreshRow',index);
				});
	},
	append : function() {
	//TODO
	},
	removeit : function(confirmId) {
		//TODO
	},
	remove : function() {
		//TODO
	},
	accept : function() {
		if (employee.shebaoSummaryendEditing()) {
			$('#datagrid_shebaoCompany').datagrid('acceptChanges');
		}
	},
	reject : function() {
		$('#datagrid_shebaoCompany').datagrid('rejectChanges');
		shebaoCompanyEdit = undefined;
	},
	getChanges : function() {
		var rows = $('#datagrid_shebaoCompany').datagrid('getChanges');
		alert(rows.length + ' rows are changed!');
	},
	queryShebao : function(data, src) {
		$('#datagrid_shebaoCompany').datagrid({
			queryParams : data
		})
	},
	updateDom : function(row){
		$('#shebaoCompany_dyangl').html(row.subEndowmentIinsurance);
		$('#shebaoCompany_dyil').html(row.subMedicare);
		$('#shebaoCompany_dshiy').html(row.subUnemployedInsurance);
		$('#shebaoCompany_dzhuf').html(row.subHouseIinsurance);
		$('#shebaoCompany_cyangl').html(row.cEndowmentIinsurance);
		$('#shebaoCompany_cyil').html(row.cMedicare);
		$('#shebaoCompany_cshiy').html(row.cUnemployedInsurance);
		$('#shebaoCompany_czhuf').html(row.cHouseIinsurance);
		$('#shebaoCompany_cgongs').html(row.cInjuryInsurance);
		$('#shebaoCompany_cshengy').html(row.cBirthIinsurance);
	},
	updateRadix : function(){
		var checkeds = $('#datagrid_shebaoCompany').datagrid('getChecked');
		var newRadix = Number($('#shebaoCompany_updateRadix').val());
		if(checkeds.length === 0){
			$.messager.alert('提示', '您没有选择任何人');
			return;
		}
		var usernames = "";
		$.each(checkeds, function(n,obj){
			if(n<7){
				if(n === (checkeds.length -1)){
					usernames += obj.username;
				}else{
					usernames += (obj.username + ",");
				}
			}else if(n === 7){
				usernames += "..."
			}
		})
		var message = "您确定要更新：[" + checkeds[0].company + "]:<br/>" + usernames + "<br/>的基数为: " + newRadix + " 吗？";
		confirmDialog.createDialog(
				message,
				function(confirmId){
					$.each(checkeds,function(n,obj) {
						obj.radix = newRadix;
						wagesCalculate.calculateShebao(obj, function(result) {
							if (result) {
								$.post('employee/updateWagesRadix.action', obj, function(result){
									confirmDialog.destoryDialog(confirmId);
									var result =  eval("(" + result + ")");
									if(result.success){
										var index = $('#datagrid_shebaoCompany').datagrid('getRowIndex',checkeds[n]);
										$('#datagrid_shebaoCompany').datagrid('refreshRow',index);
									}else{
										$.messager.alert("更新结果", "[" + obj.username + "]" + result.msg + ",请您手动刷新页面。");
									}
								})
							} else {
								$.messager.alert('计算结果' , "[" + obj.username + ']计算失败');
							}
						})
					})
				}
		);
		$.each(checkeds,function(n,obj) {
			obj.radix = newRadix;
			wagesCalculate.calculateShebao(obj, function(result) {
				if (result) {
					var index = $('#datagrid_shebaoCompany').datagrid('getRowIndex',checkeds[n]);
					$('#datagrid_shebaoCompany').datagrid('refreshRow',index);
				} else {
					alert('计算失败');
				}
			})
		})

	}
	},
	kaoqin : {
		queryKaoqin : function(data, src) {
			$('#datagrid_kaoqin').datagrid({
				queryParams : data
			})
		},
		view : function(){
			//TODO  to do something useful
			return;
		},
		onDblClickRow : function(index,row) {
			if(!kaoqinEdit){
				session.editKaoqinRow = $.extend({},row);
			}
			if (kaoqinEdit != index) {
				if (employee.shebaoSummary.endEditing()) {
					$('#datagrid_kaoqin').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					kaoqinEdit = index;
				} else {
					setTimeout(function() {$('#datagrid_kaoqin').datagrid('selectRow',kaoqinEdit);
							}, 0);
				}
			}
			
		},
		endEditing : function() {
			if (kaoqinEdit == undefined) {
				return true
			}
			if ($('#datagrid_kaoqin').datagrid('validateRow', kaoqinEdit)) {
				$('#datagrid_kaoqin').datagrid('endEdit', kaoqinEdit);
				kaoqinEdit = undefined;
				return true;
			} else {
				return false;
			}
		},
		onEndEdit : function(index,row,changes) {
			var message = "您确定要更新吗？";
			var url = "employee/updateKaoqin.action";
			confirmDialog.createDialog(
					message,
					function(confirmId){
						var data = row;
						$.post(url, data, function(result){
							var result =  eval("(" + result + ")");
							confirmDialog.destoryDialog(confirmId);
							if (!result.success) {
								$('#datagrid_kaoqin').datagrid('getData').rows[index] = session.editKaoqinRow;
								$('#datagrid_kaoqin').datagrid('refreshRow',index);
							}
							session.editKaoqinRow = {};
							$.messager.alert('消息', result.msg, 'info');
						});
					},function(){
						$('#datagrid_kaoqin').datagrid('getData').rows[index] = session.editKaoqinRow;
						$('#datagrid_kaoqin').datagrid('refreshRow',index);
						session.editKaoqinRow = {};
					});
		},
		openWagesDate : function(){
			$('#dialog_wagesDate').window('open').window(
					'resize',
					{
						top : $(document).scrollTop()
								+ ($(window).height() - 480) * 0.5
					});
		},
		selectMonth : function(dom){
			var data = {};
			data.wagesMonth = dom.val();
			$('#datagrid_wagesDate').datagrid({queryParams : data});
		},
		generateWagesDate : function(){
			  var calendar = $('#calendar_wagesDate').calendar('options');
			    var data = {};
			    data.year = calendar.year;
			    data.month = calendar.month;
			    $.post('employee/generateWagesDates.action', data, function(result){
					var result =  eval("(" + result + ")");
					var saveNums = 0;
					if(result.success){
						var query = "";
						query = data.year + "." + (data.month<10?"0"+data.month:data.month);
						$('#datagrid_wagesDate').datagrid({queryParams : {wagesMonth:query}});
						saveNums = Number(result.msg);
					}
					$.messager.alert('消息', "成功更新/插入数据库" + saveNums + "条数据！");
				});
		},
		wagesDateDblCickRow : function(index,row){
			onDblClickRow(index,row,'datagrid_wagesDate');
		},
		wagesDateEndEditing : function(){
			endEditing('datagrid_wagesDate');
		},
		wagesDateEndEdit : function(index,row,changes){
			var message = "你确定要更新吗?";
			var url = "employee/updateWagesDate.action";
			if(wagesDateAdd){
				message = "您确定要增加吗？";
			}
			confirmDialog.createDialog(
					message,
					function(confirmId){
						var data = row;
						$.post(url, data, function(result){
							var result =  eval("(" + result + ")");
							confirmDialog.destoryDialog(confirmId);
							if (!result.success) {
								if(wagesDateAdd){
									removeIt('datagrid_wagesDate');
									wagesDateAdd = undefined;
									return;
								}
								$('#datagrid_wagesDate').datagrid('getData').rows[index] = session.dEditRow;
								$('#datagrid_wagesDate').datagrid('refreshRow',index);
							}else{
								if(wagesDateAdd){
									$('#datagrid_wagesDate').datagrid('reload');
									shebaoAdd = undefined;
									return;
								}
							}
							$.messager.alert('消息', result.msg, 'info');
						});
					},function(){
						if(wagesDateAdd){
							removeIt('datagrid_wagesDate');
							wagesDateAdd = undefined;
							return;
						}
						$('#datagrid_wagesDate').datagrid('getData').rows[index] = session.dEditRow;
						var selectedData = $('#datagrid_wagesDate').datagrid('getSelected');
						session.dEditRow = $.extend({},selectedData);
						$('#datagrid_wagesDate').datagrid('refreshRow',index);
					});
		},
		wagesDateDelete : function(){
			var selectedRow = $('#datagrid_wagesDate').datagrid('getSelected');
			var message = "您确定要删除？";
			confirmDialog.createDialog(
					message,function(confirmId){
						$.post('employee/deleteWagesDate.action',selectedRow,function(result){
							var result =  eval("(" + result + ")");
							confirmDialog.destoryDialog(confirmId);
							if(result.success){
								$('#datagrid_wagesDate').datagrid('reload');
							}
							$.messager.alert('消息', result.msg, 'info');
						});
					})
		},
		openGenerateWages : function(){
			var date = new Date();
			var month = date.getMonth();
			var message = "在批量生成考勤表前，是否检查一下(" + month + "月、" + (month - 1) + "月)工作日表？";
			confirmDialog.createDialog(
					message,function(confirmId){
							confirmDialog.destoryDialog(confirmId);
							employee.kaoqin.openWagesDate();
						},function(confirmId){
							confirmDialog.destoryDialog(confirmId);
							employee.kaoqin.generateWages();
							});
		},
		generateWages : function(){
			var message = "现有的考勤表将被清除，您确定要重新生成当月考勤表吗？";
			confirmDialog.createDialog(
					message,function(confirmId){
							confirmDialog.destoryDialog(confirmId);
							$.post('employee/generateKaoqin.action',{},function(result){
								var result =  eval("(" + result + ")");
								if(result.success){
									$('#datagrid_kaoqin').datagrid('reload');
								}
								$.messager.alert('消息', result.msg, 'info');
							})
						},undefined,'confirmId2');
		}
	},
	monthWages : {
		queryMonthWages : function(data, src) {
			$('#datagrid_monthWages').datagrid({
				queryParams : data
			})
		},
		onDblClickRow : function(index,row){
			onDblClickRow(index,row,'datagrid_monthWages');
		},
		endEditing : function(){
			endEditing('datagrid_monthWages');
		},
		onEndEdit : function(index,row,changes){
			var message = "你确定要更新吗?";
			var url = "employee/updateMonthWages.action";
			if(monthWagesAdd){
				message = "您确定要增加吗？";
			}
			confirmDialog.createDialog(
					message,
					function(confirmId){
						var data = row;
						$.post(url, data, function(result){
							var result =  eval("(" + result + ")");
							confirmDialog.destoryDialog(confirmId);
							if (!result.success) {
								if(wagesDateAdd){
									removeIt('datagrid_monthWages');
									wagesDateAdd = undefined;
									return;
								}
								$('#datagrid_monthWages').datagrid('getData').rows[index] = session.dEditRow;
								$('#datagrid_monthWages').datagrid('refreshRow',index);
							}else{
								if(wagesDateAdd){
									$('#datagrid_monthWages').datagrid('reload');
									shebaoAdd = undefined;
									return;
								}
							}
							$.messager.alert('消息', result.msg, 'info');
						});
					},function(){
						if(wagesDateAdd){
							removeIt('datagrid_monthWages');
							wagesDateAdd = undefined;
							return;
						}
						$('#datagrid_monthWages').datagrid('getData').rows[index] = session.dEditRow;
						var selectedData = $('#datagrid_monthWages').datagrid('getSelected');
						session.dEditRow = $.extend({},selectedData);
						$('#datagrid_monthWages').datagrid('refreshRow',index);
					});
		},
		generateMothWages : function(){
			var message = "现有的当月工资表将被清除，您确定要重新生成当月工资表吗？";
			confirmDialog.createDialog(
					message,function(confirmId){
							confirmDialog.destoryDialog(confirmId);
							$.post('employee/generateMonthWages.action',{},function(result){
								var result =  eval("(" + result + ")");
								if(result.success){
									$('#datagrid_monthWages').datagrid('reload');
								}
								$.messager.alert('消息', result.msg, 'info');
							})
						});
		}
	},
	
}
var wagesDateAdd = undefined;
var monthWagesAdd = undefined;
var shebaoAdd = undefined;
var shebaoEdit = undefined;
var shebaoCompanyEdit = undefined;
var kaoqinEdit = undefined;