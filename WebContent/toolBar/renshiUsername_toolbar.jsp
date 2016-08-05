<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 舍弃  -->
<div id = "renshi_toolbar">
<!-- <a href="javascript:void(0)" id="mb" class="easyui-menubutton" data-options="menu:'#mm',iconCls:'icon-edit'">编辑</a> -->
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="employee.view()">查看</a>
<span class="btn-separator"></span>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="employee.editEmployee()">编辑</a>
<span class="btn-separator"></span>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="employee.addEmployee()">新增</a>
<span class="btn-separator"></span>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"
onclick="confirmDialog.createDialog('您确定要删除吗？',employee.deleteEmployee);">删除</a>
<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true">变更</a> -->
<span class="btn-separator"></span>
<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-excel',plain:true" onclick="employee.exportExcel()">导出报表</a>
<span class="btn-separator"></span> -->
<a href="#" class="easyui-menubutton" data-options="menu:'#menu_employee_export',iconCls:'icon-excel'">导出报表</a>
<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-copy',plain:true">复制</a> -->
</div>
<% 
Calendar cal = Calendar.getInstance();
int year = cal.get(Calendar.YEAR);
int month = cal.get(Calendar.MONTH) + 1;
%>
<div id="menu_employee_export" style="width:160px;">
	 <div>
        <span>当月入职情况</span>
        <div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(111)">导出<%= "" + year+ "-"+ (month - 1) %>月入职信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(112)">导出<%= "" + year + "-"+ month %>月入职信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(113)">导出<%= "" + year + "-"+ (month + 1) %>月入职信息(正式)</div>
            <div class="menu-sep"></div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(121)">导出<%= "" + year+ "-"+ (month - 1) %>月入职信息(实习)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(122)">导出<%= "" + year + "-"+ month %>月入职信息(实习)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(123)">导出<%= "" + year + "-"+ (month + 1) %>月入职信息(实习)</div>
        </div>
    </div>
	 <div>
        <span>当月转正情况</span>
        <div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(211)">导出<%= "" + year+ "-"+ (month - 1) %>月转正信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(212)">导出<%= "" + year + "-"+ month %>月转正信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(213)">导出<%= "" + year + "-"+ (month + 1) %>月转正信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" class="menu-sep"></div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(221)">导出<%= "" + year+ "-"+ (month - 1) %>月转正信息(实习)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(222)">导出<%= "" + year + "-"+ month %>月转正信息(实习)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(223)">导出<%= "" + year + "-"+ (month + 1) %>月转正信息(实习)</div>
        </div>
    </div>
	 <div>
        <span>当月离职情况</span>
        <div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(311)">导出<%= "" + year+ "-"+ (month - 1) %>月离职信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(312)">导出<%= "" + year + "-"+ month %>月离职信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(313)">导出<%= "" + year + "-"+ (month + 1) %>月离职信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" class="menu-sep"></div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(321)">导出<%= "" + year+ "-"+ (month - 1) %>月离职信息(实习)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(322)">导出<%= "" + year + "-"+ month %>月离职信息(实习)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(323)">导出<%= "" + year + "-"+ (month + 1) %>月离职信息(实习)</div>
        </div>
    </div>
	 <div>
        <span>当月异动情况</span>
        <div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(411)">导出<%= "" + year+ "-"+ (month - 1) %>月异动信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(412)">导出<%= "" + year + "-"+ month %>月异动信息(正式)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(413)">导出<%= "" + year + "-"+ (month + 1) %>月异动信息(正式)</div>
            <div class="menu-sep"></div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(421)">导出<%= "" + year+ "-"+ (month - 1) %>月异动信息(实习)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(422)">导出<%= "" + year + "-"+ month %>月异动信息(实习)</div>
            <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(423)">导出<%= "" + year + "-"+ (month + 1) %>月异动信息(实习)</div>
        </div>
    </div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-excel'" onclick="employee.exportExcel(0)">根据当前查询条件导出</div>
</div>