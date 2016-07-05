<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div id="p" class="easyui-panel" style="width:100%;height:100px;padding:10px;background-color: #ffb3b3">
</div>
<table id="employee_data1" class="easyui-datagrid"  style="width:100%;height:auto"
       data-options="singleSelect:true,collapsible:true,url:'model/datagrid.json',method:'get'">
    <thead>
    <tr>
        <th data-options="field:'id',width:80">员工编号</th>
        <th data-options="field:'username',width:80">姓名</th>
        <th data-options="field:'firstLevel',width:100">一级</th>
        <th data-options="field:'secondLevel',width:100">二级</th>
        <th data-options="field:'thirdLevel',width:100">三级</th>
        <th data-options="field:'fourthLevel',width:100">四级</th>
        <th data-options="field:'position',width:80">职位</th>
        <th data-options="field:'sex',width:80">性别</th>
        <th data-options="field:'hiredate',width:80">入职时间</th>
        <th data-options="field:'leaveTime',width:80">离职时间</th>
        <th data-options="field:'workPlace',width:100">工作地点</th>
        <th data-options="field:'phoneNumber',width:80">联系方式</th>
        <th data-options="field:'degree',width:80">学历</th>
    </tr>
    </thead>
</table>
<script type="text/javascript">
    $('#employee_data1').datagrid({
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
  </script>