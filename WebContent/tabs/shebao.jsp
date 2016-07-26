<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!-- 社保维护 -->
<div id="tab_shebao" title="社保维护" style="padding: 10px;overflow: hidden"	data-options="iconCls:'icon-edit',closable:true">
<!-- 组合查询form表单 -->
	<div id="form_shebao" title="组合查询" class="easyui-panel"
		data-options="iconCls:'icon-search',href:'${pageContext.request.contextPath}/components/shebao_queryform.jsp',tools:[
				{iconCls:'icon-reload',handler:function(){$('#form_shebao').panel('open').panel('refresh')}}]"
		style="width: 98%; padding: 10px; margin-bottom: 10px;">
	</div>

	<div id="datagrid_shebao" style="width: 98%">
		<table class="easyui-datagrid" style="width: 100%;"
			data-options="url:'employee/getAllShebaoRadio.action',
			title:'社保比例信息',
			fitColumns:true,
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			pageSize:15,
			pageList:[10,15,20,30,50],
			toolbar: [{
			iconCls: 'icon-add',
			text : '增加',
			handler: function(){alert('add')}
			},'-',{
				iconCls: 'icon-remove',
				text : '删除',
				handler: function(){alert('add')}
			}],
			onClickCell : center.shebao.onClickCell,
			onEndEdit: center.shebao.onEndEdit">
			<thead>
				<tr>
					<!-- <th data-options="field:'sid',width:40">id</th> -->
					<th data-options="field:'companyName',width:120">公司名称</th>
					<th data-options="field:'shebaoType',width:100 ">社保类型</th>
					<th data-options="field:'typeCode',width:100 ">类型编号</th>
					<th data-options="field:'radixMin',width:100,editor:{type:'numberbox',options:{precision:2}}">基数下限</th>
					<th data-options="field:'radixMax',width:100 ,editor:{type:'numberbox',options:{precision:2}}">基数上限</th>
					<th data-options="field:'cRadio',width:100 ,editor:{type:'numberbox',options:{precision:2}}">单位比例</th>
					<th data-options="field:'radio',width:100 ,editor:{type:'numberbox',options:{precision:2}}">个人比例</th>
					<!-- <th data-options="field:'cFixedV,width:100 ,editor:{type:'numberbox',options:{precision:2}}">单位固定值</th> -->
					<th data-options="field:'cFixedV',width:100 ,editor:{type:'numberbox',options:{precision:2}}">个人固定值</th>
					<th data-options="field:'fixedValue',width:100 ,editor:{type:'numberbox',options:{precision:2}}">个人固定值</th>
					<th data-options="field:'householdType',width:100 ,editor:'textbox'">户口性质</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
