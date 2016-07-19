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
				toolbar : '#renshi_toolbar',
//				toolbar: [{
//		            text:'查看',
//		            iconCls: 'icon-search',
//		            handler: function(){
//		            	center.view();
//		            }
//		        },'-',{
//		            text:'编辑',
//		            iconCls: 'icon-edit',
//		            handler: function(){
//		            	center.editEmployee();
//		            }
//		        },'-',{
//		            text:'新增',
//		            iconCls: 'icon-add',
//		            handler: function(){
//		            	center.addEmployee();
//		            }
//		        },'-',{
//		            text:'删除',
//		            iconCls: 'icon-cancel',
//		            handler: function(){
//		            	center.deleteEmployee();
//		            }
//		        },'-',{
//		            text:'导出报表',
//		            iconCls: 'icon-excel',
//		            handler: function(){
//		            	center.exportExcel();
//		            }
//		        }],
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
			var rights = session.user.roleId;
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
			if(session.user.roleId != 0 && session.user.roleId != 1){
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
			if(session.user.roleId != 0 && session.user.roleId != 1){
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
			if(session.user.roleId != 0 && session.user.roleId != 1){
				alert('您没有增加权限！~');
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
			if(session.user.roleId != 0 && session.user.roleId != 1){
				alert('您没有删除权限！~');
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
		exportExcel : function(month){
			console.log('exportExcel:' + month);
			var exportParam = {};
			var form = $('#renshiquery_form');
			var data = getDataOfForm(form);
			var frm = $("<form>");
			frm.attr("style","display:none");
			frm.attr("target","");
			frm.attr("id","export_query");
			frm.attr("method","post");
			$("body").append(frm);//将表单放置在web中
			var url = "file/exportExcel.action";
			var mydate = new Date();
			var year = mydate.getFullYear(); //获取完整的年份(4位,1970-????)
			var month = mydate.getMonth()+1; //获取当前月份(0-11,0代表1月)
			var monthb = month-1;
			var montha = month+1;
			var date = year + (month<10?('0'+month):month);
			var dateBefore = year + (monthb<10?('0'+monthb):monthb);
			var dateAfter = year + (montha<10?('0'+montha):montha);
			exportParam.configurable = 'reportForm';
			if(!month || month == 0){
				delete exportParam.configurable;
				if (data.succeed) {
					exportParam = data.data;
		        }
			}else if(month==111 || month == 112 || month == 113|| month == 121|| month == 122|| month == 123 ){
				if(month == 111){
					exportParam.configurable_value = dateBefore + "入职";
				}else if(month == 112){
					exportParam.configurable_value = date + "入职";
				}else if(month == 113){
					exportParam.configurable_value = dateAfter + "入职";
				}else if(month == 121){
					exportParam.configurable_value = dateBefore + "入职";
				}else if(month == 122){
					exportParam.configurable_value = date + "入职";
				}else{
					exportParam.configurable_value = dateAfter + "入职";
				}
			}else if(month==211 || month == 212 || month == 213|| month == 221|| month == 222|| month == 223 ){
				if(month == 211){
					exportParam.configurable_value = dateBefore + "转正";
				}else if(month == 212){
					exportParam.configurable_value = date + "转正";
				}else if(month == 213){
					exportParam.configurable_value = dateAfter + "转正";
				}else if(month == 221){
					exportParam.configurable_value = dateBefore + "转正";
				}else if(month == 222){
					exportParam.configurable_value = date + "转正";
				}else{
					exportParam.configurable_value = dateAfter + "转正";
				}
			}else if(month==311 || month == 312 || month == 313|| month == 321|| month == 322|| month == 323 ){
				if(month == 311){
					exportParam.configurable_value = dateBefore + "离职";
				}else if(month == 312){
					exportParam.configurable_value = date + "离职";
				}else if(month == 313){
					exportParam.configurable_value = dateAfter + "离职";
				}else if(month == 321){
					exportParam.configurable_value = dateBefore + "离职";
				}else if(month == 322){
					exportParam.configurable_value = date + "离职";
				}else{
					exportParam.configurable_value = dateAfter + "离职";
				}
			}else if(month==411 || month == 412 || month == 413|| month == 421|| month == 422|| month == 423 ){
				if(month == 411){
					exportParam.configurable_value = dateBefore + "异动";
				}else if(month == 412){
					exportParam.configurable_value = date + "异动";
				}else if(month == 413){
					exportParam.configurable_value = dateAfter + "异动";
				}else if(month == 421){
					exportParam.configurable_value = dateBefore + "异动";
				}else if(month == 422){
					exportParam.configurable_value = date + "异动";
				}else{
					exportParam.configurable_value = dateAfter + "异动";
				}
			}
			$("#export_query").form('submit',{
				url : url,
				queryParams : exportParam,
				onSubmit : function(){
					console.log("正在导出,请稍后");
				}
			});
		},
		helpEmployee : function(){
			console.log('helpEmployee');
		},
		reloadQueryForm : function(dom){
			alert(dom);
		}
}
