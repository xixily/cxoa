<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 舍弃  -->
<div id = "monthWages_toolbar">
<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="employee.editEmployee()">编辑</a> -->
<span class="btn-separator"></span>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="employee.addEmployee()">新增</a>
<span class="btn-separator"></span>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"
onclick="confirmDialog.createDialog('您确定要删除吗？',function(){$.messager.alert('删除功能未完成！~')});">删除</a>
<span class="btn-separator"></span>
<a href="#" class="easyui-menubutton" data-options="menu:'#menu_monthWages_export',iconCls:'icon-excel'">导出报表</a>
<span class="btn-separator"></span>
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-databse_refresh',plain:true" onclick="employee.monthWages.generateMothWages()">批量生成工资表</a>
<span class="btn-separator"></span>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-coins',plain:true" onclick="">发放完毕</a>
</div>
<div id="menu_monthWages_export" >
	 <div data-options="iconCls:'icon-excel'" onclick="">导出</div>
     <div data-options="iconCls:'icon-excel'" onclick="">导出2</div>
     <div data-options="iconCls:'icon-excel'" onclick="">导出3</div>
     <div class="menu-sep"></div>
     <div data-options="iconCls:'icon-excel'" onclick="">导出4</div>
     <div data-options="iconCls:'icon-excel'" onclick="">导出5</div>
     <div data-options="iconCls:'icon-excel'" onclick="">导出6</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-excel'" onclick="employee.monthWages.exportMonthWagesExcel()">根据当前查询条件导出</div>
</div>