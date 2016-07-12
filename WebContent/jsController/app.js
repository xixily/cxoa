$.ajaxSetup({
    cache: true
});
var session = {
    logined : false,
    user : {},
    testText : '我在这里存放全局缓存数据'
};
$(window).on('beforeunload', function (e) {
    if (session.logined){
//    	north.logoutFun();
//    	return '警告：请使用系统的退出按钮正常退出系统!';
    }
});
/**
 * 此login方法暂时弃用。
 */
function login(){
	$('#login_form').form('submit', {
		onSubmit: function(){
			var isValid = $(this).form('validate');
			if (!isValid){
				console.log('登陆校验失败！');
				$.messager.progress('close');	// hide progress bar while the form is invalid
			}
			return isValid;	// return false will stop the form submission
		},
		success: function(data_string){
			var data = $.parseJSON(data_string);
			console.log('o%', data);
			$.messager.progress('close');	// hide progress bar while submit successfully
			if(data.success){
				session.logined = data.success;
				session.user = data.obj;
				$('#main_body').css('display','');
				$('#login_dlg').dialog('close');
				initClickHandler();
				west.initWestTree();
				center.initEmployee();
			}
		}
	});
}
/**
 * @author dengxuefeng
 * @requires jquery.js easyui.js
 * 初始化
 */
function initClickHandler() {
    $(document).on("click", ".do_action", function(event) {
        console.log('do_action!');
        event.preventDefault();//关闭默认事件
        event.stopImmediatePropagation();//停止冒泡
        var src = $(event.currentTarget);
        var action = src.attr("appaction");
        var frm = src.closest("form");
        if(!frm.form('validate')){
            console.log('form 表单校验失败！');
            return false;
        }
        var data = getDataOfForm(frm);
        console.log('o%', data);
        if (data.succeed) {
            var actionHandler = eval(action);//jQuery.gloabEval()全局方法
            if (actionHandler) {
                actionHandler(data.data, src);
            } else {
                console.log('action handler [' + action + '] is not support!');
            }
        }
    });
}

/**
 * @author dengxuefeng
 * @requires jquery.js
 * 遍历form表单，取出input,textarea,select里面的值，返回data
 */
function getDataOfForm(form) {
    var succeed = true;
    var data = {};
    var inputs = form.find("input,textarea,select");
    if (inputs) {
        for (var i = 0; i < inputs.length; i++) {
            var ele = $(inputs[i]);
            var name = ele.attr("name");
            var value = $.trim(ele.val());
            if (value.indexOf("****") != -1 && ele.attr("shieldedTel")) {
                value = ele.attr("shieldedTel") || '';
            }
            if (ele[0].type == 'checkbox') {
                var isGroup = ele.attr("fieldgroup");
                if (isGroup) {
                    if (!ele[0].checked) {
                        continue;
                    }
                } else {
                    value = ele[0].checked;
                }
            } else if (ele[0].type == 'radio') {
                if (ele[0].checked)
                    data[name] = value; else continue;
            }
            if (!name)
                continue;

            if (name.match(/\w\[\]/g)) {
                name = name.substring(0, name.length - 2);
                if (!data[name]) {
                    data[name] = new Array();
                }
                data[name].push(value);
            } else if ((ele[0].tagName).toLowerCase() == "select" && ele[0].name == 'role') {
                data[name] = new Array();
                var options = ele[0].options;
                for (var j = 0, len = options.length; j < len; j++) {
                    data[name].push(options[j].value);
                }
            }else {
                data[name] = value;

            }
        }
    }
    return {
        succeed: succeed,
        data: data
    };
}

/**
 * @author dengxuef
 * 
 * @requires jQuery
 * 
 * @param action : {url:'',data:data}
 * @param callback 回调函数
 * @param method 默认get 
 * @param async 是否异步 
 * @param timeout 超时时间
 * 
 * @description 发送do_action 请求 
 */
function sendAction(action, callback, method, async, timeout) {
	async = async ? true : false;
	method = (method && method == "post") ? 'post' : 'get';
	timeout = timeout ? timeout : 20000;
	if (!session.logined) {
		alert('即将退出,请您先登陆！');
		north.logoutFun();
	}
	if (!action.url) {
		alert('您的请求不存在！');
		return false;
	}
	$.ajax({
		type : method,
		url : action.url,
		async : async,
		data : action.data,
		timeout : timeout,
		dataType : "json",
		success : function(data) {
			if (data.succeed) {
				callback(data.obj);
			} else {
				alert('请求失败！');
			}
		}
	})
}

function formartDate(id){
	 $("#" + id + " input[class='easyui-datebox datebox-f combo-f textbox-f']").each(function () {  
	        $(this).combobox({
	        	formatter:function(date){
	        		var y = date.getFullYear();
	        		var m = date.getMonth()+1;
	        		var d = date.getDate();
	        		return m+'.'+d+'.'+y;
	        	}
	        });
	    });  
//	easyui-datebox datebox-f combo-f textbox-f
}

/**
 * @author dengxf
 * @param id form表单的id
 * @param isDisabled boolean型，默认true
 * @description 对指定id下的form表单设置 disabled
 */
function disabledForm(formId, isDisabled){
	var attr="disable";  
    if(!isDisabled){  
       attr="enable"; 
       isDisabled = false;
    }else{
    	attr="disable"; 
    	isDisabled = true;
    }
    $("form[id='"+formId+"'] :text").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] textarea").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] select").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] :radio").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] :checkbox").attr("disabled",isDisabled);  
      
    //禁用jquery easyui中的下拉选（使用input生成的combox）  
  
//    $("#" + formId + " input[class='combobox']").each(function () {  
    $("#" + formId + " input[class='easyui-combobox combobox-f combo-f']").each(function () {  
        /*if (this.id) {
//        	alert("input"+this.id);  
            $("#" + this.id).combobox(attr);  
        }  */
        $(this).combobox(attr);
    });  
    
    $("#" + formId + ' input[class="easyui-combobox combobox-f combo-f textbox-f"]').each(function () {  
    	if (this.id) {
//        	alert("input"+this.id);  
    		$("#" + this.id).combobox(attr);  
    	}  
    });  
      
    //禁用jquery easyui中的下拉选（使用select生成的combox）  
    $("#" + formId + " select[class='combobox-f combo-f']").each(function () {  
        if (this.id) {  
//        alert(this.id);  
            $("#" + this.id).combobox(attr);  
        }  
    });  
    //禁用jquery easyui中的日期组件dataBox  
    $("#" + formId + " input[class='datebox-f combo-f']").each(function () {  
        if (this.id) {  
//        alert(this.id)  
            $("#" + this.id).datebox(attr);  
        }  
    });  
}

function closeDialog(dom){
	var dialog = dom.closest(".easyui-dialog");
	dialog.dialog('close');
}

function clearForm(dom){
	var form = dom.closest("form");
	form.form('clear');
}

function submitForm(dom){
	var form = dom.closest("form");
	console.log(form);
	form.form('submit');
}
/**
 * @author dengxf
 * 
 * @requires jQuery,EasyUI
 * 
 * panel关闭时回收内存，主要用于layout使用iframe嵌入网页时的内存泄漏问题
 */
$.fn.panel.defaults.onBeforeDestroy = function() {
	var frame = $('iframe', this);
	try {
		if (frame.length > 0) {
			for ( var i = 0; i < frame.length; i++) {
				frame[i].contentWindow.document.write('');
				frame[i].contentWindow.close();
			}
			frame.remove();
			if ($.browser.msie) {
				CollectGarbage();
			}
		}
	} catch (e) {
	}
};

/**
 *时间格式化 
 */
$.fn.datebox.defaults.formatter = function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y + '.' + m + '.' + d;
}

/**
 * 使panel和datagrid在加载时提示
 * 
 * @author dengxf
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.fn.panel.defaults.loadingMessage = '加载中....';
$.fn.datagrid.defaults.loadMsg = '加载中....';

/**
 * @author dengxf
 * 
 * @requires jQuery,EasyUI
 * 
 * 通用错误提示
 * 
 * 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
 */
var easyuiErrorFunction = function(XMLHttpRequest) {
	$.messager.progress('close');
	$.messager.alert('错误', XMLHttpRequest.responseText);
};
$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.tree.defaults.onLoadError = easyuiErrorFunction;
$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
$.fn.form.defaults.onLoadError = easyuiErrorFunction;

/**
 * @author dengxf
 * 
 * @requires jQuery,EasyUI
 * 
 * 为datagrid、treegrid增加表头菜单，用于显示或隐藏列，注意：冻结列不在此菜单中
 */
var createGridHeaderContextMenu = function(e, field) {
	e.preventDefault();
	var grid = $(this);/* grid本身 */
	var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */
	if (!headerContextMenu) {
		var tmenu = $('<div style="width:100px;"></div>').appendTo('body');
		var fields = grid.datagrid('getColumnFields');
		for ( var i = 0; i < fields.length; i++) {
			var fildOption = grid.datagrid('getColumnOption', fields[i]);
			if (!fildOption.hidden) {
				$('<div iconCls="icon-ok" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
			} else {
				$('<div iconCls="icon-empty" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
			}
		}
		headerContextMenu = this.headerContextMenu = tmenu.menu({
			onClick : function(item) {
				var field = $(item.target).attr('field');
				if (item.iconCls == 'icon-ok') {
					grid.datagrid('hideColumn', field);
					$(this).menu('setIcon', {
						target : item.target,
						iconCls : 'icon-empty'
					});
				} else {
					grid.datagrid('showColumn', field);
					$(this).menu('setIcon', {
						target : item.target,
						iconCls : 'icon-ok'
					});
				}
			}
		});
	}
	headerContextMenu.menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;

/**
 * @author dengxf
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展validatebox，添加验证两次密码功能
 */
$.extend($.fn.validatebox.defaults.rules, {
	eqPwd : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '密码不一致！'
	}
});

/**
 * @author 夏悸
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展tree，使其支持平滑数据格式
 */
$.fn.tree.defaults.loadFilter = function(data, parent) {
	var opt = $(this).data().tree.options;
	var idFiled, textFiled, parentField;
	if (opt.parentField) {
		idFiled = opt.idFiled || 'id';
		textFiled = opt.textFiled || 'text';
		parentField = opt.parentField;
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idFiled]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textFiled];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textFiled];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};

/**
 * @author dengxf
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展treegrid，使其支持平滑数据格式
 */
$.fn.treegrid.defaults.loadFilter = function(data, parentId) {
	var opt = $(this).data().treegrid.options;
	var idFiled, textFiled, parentField;
	if (opt.parentField) {
		idFiled = opt.idFiled || 'id';
		textFiled = opt.textFiled || 'text';
		parentField = opt.parentField;
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idFiled]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textFiled];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textFiled];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};

/**
 * @author dengxf
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展combotree，使其支持平滑数据格式
 */
$.fn.combotree.defaults.loadFilter = $.fn.tree.defaults.loadFilter;

/**
 * @author dengxf
 * 
 * @requires jQuery,EasyUI
 * 
 * 防止panel/window/dialog组件超出浏览器边界
 * @param left
 * @param top
 */
var easyuiPanelOnMove = function(left, top) {
	var l = left;
	var t = top;
	if (l < 1) {
		l = 1;
	}
	if (t < 1) {
		t = 1;
	}
	var width = parseInt($(this).parent().css('width')) + 14;
	var height = parseInt($(this).parent().css('height')) + 14;
	var right = l + width;
	var buttom = t + height;
	var browserWidth = $(window).width();
	var browserHeight = $(window).height();
	if (right > browserWidth) {
		l = browserWidth - width;
	}
	if (buttom > browserHeight) {
		t = browserHeight - height;
	}
	$(this).parent().css({/* 修正面板位置 */
		left : l,
		top : t
	});
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;

/**
 * @author dengxf
 * 
 * @requires jQuery,EasyUI,jQuery cookie plugin
 * 
 * 更换EasyUI主题的方法
 * 
 * @param themeName
 *            主题名称
 */
var changeTheme = function(themeName) {
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
	$easyuiTheme.attr('href', href);

	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for ( var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			$(ifr).contents().find('#easyuiTheme').attr('href', href);
		}
	}

	$.cookie('easyuiThemeName', themeName, {
		expires : 7
	});
};

/**
 * @author dengxf
 * 
 * @requires jQuery
 * 
 * 将form表单元素的值序列化成对象
 * 
 * @returns object
 */
serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

/**
 * @author dengxf
 * 
 * 增加formatString功能
 * 
 * 使用方法：formatString('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 * 
 * @returns 格式化后的字符串
 */
formatString = function(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};

/**
 * @author dengxf
 * 
 * 接收一个以逗号分割的字符串，返回List，list里每一项都是一个字符串
 * 
 * @returns list
 */
stringToList = function(value) {
	if (value != undefined && value != '') {
		var values = [];
		var t = value.split(',');
		for ( var i = 0; i < t.length; i++) {
			values.push('' + t[i]);/* 避免他将ID当成数字 */
		}
		return values;
	} else {
		return [];
	}
};

/**
 * @author dengxf
 * 
 * @requires jQuery
 * 
 * 改变jQuery的AJAX默认属性和方法
 */
$.ajaxSetup({
	type : 'POST',
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		$.messager.progress('close');
		$.messager.alert('错误', XMLHttpRequest.responseText);
	}
});