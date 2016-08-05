<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!-- 菜单维护 -->
<div id="tab_menus" title="社保维护" style="padding: 10px;overflow: hidden"	data-options="iconCls:'icon-edit',closable:true">
<!-- 系统菜单维护 -->
	<div id="form_menus" title="组合查询" class="easyui-panel"
		data-options="iconCls:'icon-search',href:'${pageContext.request.contextPath}/queryForm/menus_queryform.jsp',tools:[
				{iconCls:'icon-reload',handler:function(){$('#form_shebao').panel('open').panel('refresh')}}]"
		style="width: 98%; padding: 10px; margin-bottom: 10px;">
	</div>

	<div style="width: 98%">
	<table id="datagrid_menus" title="Folder Browser" class="easyui-treegrid" 
			data-options="
				url: '${pageContext.request.contextPath}/model/treegrid_data1.json',
				method: 'get',
				checkbox: true,
				rownumbers: true,
				idField: 'id',
				treeField: 'name'
			">
		<thead>
			<tr>
				<th data-options="field:'name'" width="220">菜单名</th>
				<th data-options="field:'name'" width="220">角色</th>
				<th data-options="field:'size'" width="100">排序编号</th>
				<th data-options="field:'url'" width="150">地址(URL)</th>
				<th data-options="field:'date'" width="150">修改时间</th>
			</tr>
		</thead>
	</table>
	</div>
</div>
