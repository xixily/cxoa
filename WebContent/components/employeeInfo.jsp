<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div id="p" class="easyui-panel" style="width:100%;height:60px;padding:10px;background-color: #ffb3b3">
</div>
<table id="employee_datas" class="easyui-datagrid"  style="width:100%;height:auto"
       data-options="singleSelect:true,collapsible:true,url:'employee/renshiUser.action',method:'get',pagination:true">
    <thead>
    <tr>
        <th data-options="field:'id'">员工编号</th>
        <th data-options="field:'username'">姓名</th>
        <th data-options="field:'firstLevel'">一级</th>
        <th data-options="field:'secondLevel'">二级</th>
        <th data-options="field:'thirdLevel'">三级</th>
        <th data-options="field:'fourthLevel'">四级</th>
        <th data-options="field:'position'">职位</th>
        <th data-options="field:'sex'">性别</th>
        <th data-options="field:'hiredate'">入职时间</th>
        <th data-options="field:'leaveTime'">离职时间</th>
        <th data-options="field:'workPlace'">工作地点</th>
        <th data-options="field:'phoneNumber'">联系方式</th>
        <th data-options="field:'degree'">学历</th>
    </tr>
    </thead>
</table>
<script type="text/javascript">
$(document).ready(function () {
    $('#employee_datas').datagrid({
    	title:'职员信息列表', 
        iconCls:'icon-edit',//图标 
        /* width: 700,  */
        height: 'auto', 
        nowrap: false, 
        striped: true, 
        border: true, 
        collapsible:false,//是否可折叠的 
        fit: true,//自动大小 
        url:'employee/renshiUser.action', 
        //sortName: 'code', 
        //sortOrder: 'desc', 
        remoteSort:false,  
        idField:'fldId', 
        singleSelect:false,//是否单选 
        pagination:true,//分页控件 
        rownumbers:true,//行号 
        toolbar: [{
            text:'编辑',
            iconCls: 'icon-edit',
            handler: function(){alert('edit')}
        },'-',{
            text:'增加',
            iconCls: 'icon-add',
            handler: function(){alert('add')}
        },'-',{
            text:'删除',
            iconCls: 'icon-cancel',
            handler: function(){alert('delete')}
        },'-',{
            text:'帮助',
            iconCls: 'icon-help',
            handler: function(){alert('help')}
        }]
    });
})
  </script>