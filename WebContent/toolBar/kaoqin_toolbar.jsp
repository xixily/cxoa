<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 舍弃  -->
<div id = "kaoqin_toolbar">
<!-- <a href="javascript:void(0)" class="easyui-linkbutton" style="margin-left:18px;" data-options="iconCls:'icon-edit',plain:true" onclick="employee.editEmployee()">编辑</a> -->
<span class="btn-separator"></span>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"
onclick="confirmDialog.createDialog('您确定要删除吗？',employee.deleteEmployee);">删除</a>
<span class="btn-separator"></span>
<a class="easyui-menubutton" data-options="menu:'#menu_kaoqin_export',iconCls:'icon-excel'">导出报表</a>
<span class="btn-separator"></span>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="employee.kaoqin.openWagesDate()">维护工作日表</a>
<span class="btn-separator"></span>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-databse_refresh',plain:true" onclick="employee.kaoqin.openGenerateKaoqin()">批量生成当月考勤</a>
<!-- <div data-options="iconCls:'icon-databse_refresh'" >批量生成当月考勤</div> -->
<span>生成开关</span>
<input id="kaoqin_generate" name="ifGenerate" class="easyui-switchbutton"  data-options="onText:'Yes',offText:'No',
	onChange:function(checked){
		disabledButton('kaoqin_toolbar',checked?checked:false);
		$('#kaoqin_generate').switchbutton('enable');
	}
">
<span id="kaoqin_tips" style="color: red;">您当月已经生成过考勤表了</span>
<!-- <a class="easyui-menubutton" data-options="menu:'#menu_kaoqin_databaseRefact',iconCls:'icon-databse_refresh'">批量生成当月考勤</a>
 --><!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-databse_refresh',plain:true" onclick="">批量生成当月考勤</a>
 --></div>
<div id="menu_kaoqin_export" style="width:160px;">
     <div data-options="iconCls:'icon-excel'" onclick="">导出</div>
     <div data-options="iconCls:'icon-excel'" onclick="">导出2</div>
     <div data-options="iconCls:'icon-excel'" onclick="">导出3</div>
     <div class="menu-sep"></div>
     <div data-options="iconCls:'icon-excel'" onclick="">导出4</div>
     <div data-options="iconCls:'icon-excel'" onclick="">导出5</div>
     <div data-options="iconCls:'icon-excel'" onclick="">导出6</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-excel'" onclick="employee.kaoqin.exportKaoqinExcel()">根据当前查询条件导出</div>
</div>
<!-- <div id="menu_kaoqin_databaseRefact" style="width:160px;">
	<div data-options="iconCls:'icon-edit'" onclick="employee.kaoqin.openWagesDate()">1、维护工作日表</div>
    <div data-options="iconCls:'icon-databse_edit'" onclick="employee.kaoqin.openGenerateKaoqin()">2、批量生成当月考勤</div>
</div> -->