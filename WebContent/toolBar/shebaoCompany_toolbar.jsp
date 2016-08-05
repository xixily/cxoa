<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id = "shebaoCompany_toolbar" style="padding-left: 16px;">
基数：<input id="shebaoCompany_updateRadix" class="easyui-numberbox" value=0 style="width:110px" data-options="min:0,precision:2">
<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick="employee.shebaoSummary.updateRadix()">修改基数</a>
<span class="btn-separator" style="margin:0 6px;"></span>
<span>选择模式: </span>
		<select onchange="$('#datagrid_shebaoCompany').datagrid({singleSelect:(this.value==0)})">
			<option value="0">单选模式</option>
			<option value="1">多选模式</option>
</select><br/>
</div>


<div id ="shebaoComany_footerbar">
<span>选择模式: </span>
		<select onchange="$('#datagrid_shebaoCompany').datagrid({singleSelect:(this.value==0)})">
			<option value="0">单选模式</option>
			<option value="1">多选模式</option>
		</select><br/>
</div>