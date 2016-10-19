var hetong = {
		fahuo : {
			intiFahuo : function(){
				$('#datagrid_fahuo').datagrid({
					url:'tabs/hetong/datagrid.json',
//					url:'hetong/queryHetong.action',
					title:'发货表',
					fitColumns:true,
					singleSelect:true,
					pagination:true,
					rownumbers:true,
					pageSize:15,
					loadFilter: function(data){
						$.each(data.rows,function(n,obj){
							obj.send = '<span class="linked"><a href="javascript:hetong.fahuo.sendKuaidi()">获取顺丰号</a></span>';
							if(obj.mailno){
								obj.send += '/<span class="linked"><a href="javascript:hetong.fahuo.print()"><span style="color:#6c7147">打印</span></a></span>';
							}
						});
						return data;
//						if (data.d){
//							return data.d;
//						} else {
//							return data;
//						}
					},
					pageList:[10,15,20,30,50,200],
					toolbar : '#fahuo_toolbar',
//					onDblClickRow : function(){$.messager.alert('TODO','TODO');},
//					onClickCell : function(){$.messager.alert('TODO','TODO');},
//					onEndEdit: function(){$.messager.alert('TODO','TODO');}
				})
			},
			queryFahuo : function(data, src){
				$('#datagrid_fahuo').datagrid({
					queryParams : data
				})
			},
			sendKuaidi: function(data_id){
				data_id = data_id ? data_id : 'datagrid_fahuo';
				var fahuoInfo = $('#' + data_id).datagrid('getSelected');
				if(fahuoInfo && fahuoInfo.orderid && fahuoInfo.orderid != 0){
					if(fahuoInfo.d_company && fahuoInfo.d_company!=''&& fahuoInfo.d_address && fahuoInfo.d_address!=''){
						$.post('hetong/sendKuaidi.action', fahuoInfo, function(result){
							var result =  eval("(" + result + ")");
							if(result.success){
								var obj = result.obj;
								$.messager.alter('发送成功，邮寄凭证号[' + obj.mailno +']已写入数据库。');
//								confirmDialog.createDialog(
//										'发送成功，邮寄凭证号[' + obj.mailno +']已写入数据库。现在是否需要打印？',
//										function(confirmId){
//											var data = fahuoInfo;
//											var url = 'hetong/print.action';
//											$.post(url, data, function(result){
//												var result =  eval("(" + result + ")");
//												confirmDialog.destoryDialog(confirmId);
//												if (!result.success) {
//													// TODO 根据返回的结果去打印
//												}else{
//													//TODO 获取打印页面失败
//												}
//											});
//										});
							}
							$.messager.alert('消息', result.msg, 'info');
						})
					}else{
						$.messager.alert('操作提示：','缺少邮寄单位或邮寄地址，请确认！~');
					}
				}else{
					$.messager.alert('操作提示：','没有找到序号，或者序号为0，请确认！~');
					return false;
				}
				console.log('%o', data_id);
				
			},
			sendAllKuaidi: function(){
				$.messager.alert('调用成功~！');
			},
			express_type : {
				1 : '标准快递',
				2 : '顺丰特惠',
				3 : '电商特惠',
				5 : '顺丰次晨',
				6 : '即日件',
				7 : '电商速配',
				28 : '电商专配'
			},
			print :function(){
				var dialog = $('#kuaidi_form');
				dialog.dialog('clear');
				dialog.dialog({
					buttons: [{
					text:'重新打印',
					iconCls:'icon-ok',
					handler:function(){
						$('#kuaidi_form').dialog({title:'打印页面'});
					}
				},{
					text:'Cancel',
					handler:function(){
						$('#kuaidi_form').dialog('close');
					}
				}]})
//				dialog.dialog({content:iframe})
				var info = $('#datagrid_fahuo').datagrid('getSelected');
				if(info && info.orderid!=0 && info.mailno && info.mailno!=""){
					info.code_src = "template/sfTemplate/img/code_" + info.mailno + ".jpg";//运单号的路径
					if(info.express_type && info.express_type != 0){//快件产品类别
						if(hetong.fahuo.express_type[info.express_type]){
							info.express_type = hetong.fahuo.express_type[info.express_type];
						}else{
							info.express_type = '电商专配';
						}
					}else{
						info.express_type = '电商专配';
					}
					if (info.mailno && info.mailno.length>0){
						var str = info.mailno;
						var tt = '';
						var i = 1;
						for(i = 1; 3*i<= str.length;i++){
							tt += str.slice((i-1)*3,3*i) +' ';
						}
						tt += str.slice((i-1)*3,str.length);
						info.mailno = tt;
					}
					dialog.dialog('open');
					//把快递单放进iframe，再通过iframe来打印。
					$.get('template/sfTemplate/index.html',null,function(result){
						var html_dom = result;
						var jObj = $(html_dom);
						var _values = jObj.find("[target='_value']");
						$.each(_values, function(n, obj){
							var name = $(obj).attr('name');
							if(name == "code_src"){
								$(obj).attr("src",info.code_src);
							}else{
								if(info[name]){
									$(obj).html(info[name]);
								}
							}
//							console.log(obj);
						});
						var html = $('<html>');
						html.append(jObj);
						var iframe = $('<iframe>')
						iframe.attr("height","1040px");
						iframe.attr("width","485px");
						iframe.attr("frameborder",0);
						iframe.attr('srcdoc',html[0].innerHTML);
						dialog.dialog({content:iframe});
					});
				}else{
					if(!info||info.orderid==0){
						$.messager.alert("打印提示","请选择需要打印的合同！~")
					}
					if(!info.code_src){
						$.messager.alert("打印提示：","请您先获取快递单号！~");
					}
				}
			}
		},
		fapiao : {
			queryFapiao : function(data, src){
				$('#datagrid_fapiao').datagrid({
					queryParams : data
				})
			},
			onDblClickRow : function(index,row) {
				if (fapiaoEdit != index) {
					if (hetong.fapiao.endEditing()) {
						$('#datagrid_fapiao').datagrid('selectRow', index)
								.datagrid('beginEdit', index);
						fapiaoEdit = index;
					} else {
						setTimeout(function() {$('#datagrid_fapiao').datagrid('selectRow',fapiaoEdit);
								}, 0);
					}
				}
				
			},
			endEditing : function() {
				if (fapiaoEdit == undefined) {
					return true
				}
				if ($('#datagrid_fapiao').datagrid('validateRow', fapiaoEdit)) {
					$('#datagrid_fapiao').datagrid('endEdit', fapiaoEdit);
					fapiaoEdit = undefined;
					return true;
				} else {
					return false;
				}
			},
			onEndEdit : function(index,row,changes) {
				var message = "您确定要更新吗？";
				var url = "hetong/updateFapiao.action";
				confirmDialog.createDialog(
						message,
						function(confirmId){
							var data = row;
							$.post(url, data, function(result){
								var result =  eval("(" + result + ")");
								confirmDialog.destoryDialog(confirmId);
								if (!result.success) {
									$('#datagrid_fapiao').datagrid('refreshRow',index);
								}
								$('#datagrid_fapiao').datagrid('reload');
								$.messager.alert('消息', result.msg, 'info');
							});
						},function(){
							$('#datagrid_fapiao').datagrid('refreshRow',index);
						});
			},
			view : function() {
				var rights = session.user.roleId;
				var fapiaoInfo = $('#datagrid_fapiao').datagrid('getSelected');
				if(!fapiaoInfo||!fapiaoInfo.id||fapiaoInfo.id==0){
					$.messager.alert('操作提示：','请先选择发票,您没有选择任何发票！~');
					return false;
				}
				$('#hetong_fapiao_form').form('load',fapiaoInfo);
//				disabledForm('hetong_fapiao_form', true);
				$('#hetong_fapiao_info').window('open').window(
						'resize',
						{
							top : $(document).scrollTop()
									+ ($(window).height() - 480) * 0.5
						});
//				$('#btn_employeeSave').linkbutton("disable");
//				$('#btn_employeeRest').linkbutton("disable");
//				$('#btn_employeeEdit').linkbutton("enable");
			},
			editFapiao : function(){
				hetong.fapiao.view();
			}
		}
}
var fapiaoEdit = undefined;