<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!-- 社保维护 -->
<div id="tab_shebaoSummary" title="社保汇总" style="padding: 10px;overflow: hidden"	data-options="iconCls:'icon-edit',closable:true">
<!-- 组合查询form表单 -->
	<div id="form_shebaoSummary" title="组合查询" class="easyui-panel"
		data-options="iconCls:'icon-search',href:'${pageContext.request.contextPath}/queryForm/shebaoSummary_queryform.jsp',tools:[
				{iconCls:'icon-reload',handler:function(){$('#form_shebaoSummary').panel('open').panel('refresh')}}]"
		style="width: 98%; padding: 10px; margin-bottom: 10px;">
	</div>

	<div style="width: 98%">
		<table id="datagrid_shebaoSummary" class="easyui-datagrid" style="width: 100%;"
			data-options="
			url:'employee/queryShebaoSummary.action',
			title:'社保公司汇总表',
			fitColumns:true,
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			pageSize:15,
			pageList:[10,15,20,30,50],
			loadFilter : function(data){
				if (typeof(data.d)=='number'){
					return data.d.toFixed(2);
				} else {
					return data;
				}
			},
			onDblClickRow : function(index, row) {
								employee.shebaoSummary.view(index, row);
							}">
			
			<thead>
				<tr>
					<th data-options="field:'company',width:120">公司名称</th><br>
					<th data-options="field:'subEndowmentIinsurance',width:100">代扣养老金总额</th>
					<th data-options="field:'subMedicare',width:100">代扣医疗保险总额</th>
					<th data-options="field:'subUnemployedInsurance'">代扣失业保险总额</th>
					<th data-options="field:'subHouseIinsurance'">代扣住房保险总额</th>
					<th data-options="field:'cEndowmentIinsurance'">公司养老保险总额</th>
					<th data-options="field:'cMedicare',width:100">公司医疗保险总额</th>
					<th data-options="field:'cUnemployedInsurance',width:100">公司失业保险总额</th>
					<th data-options="field:'cHouseIinsurance',width:100">公司住房保险总额</th>
					<th data-options="field:'cInjuryInsurance',width:100">公司工伤保险总额</th>
					<th data-options="field:'cBirthIinsurance',width:100">公司生育保险总额</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="shebaoCompany_detail" class="easyui-dialog" title="查看公司五险一金详情"
		style="width: 1180px;overflow: hidden"
		data-options="
                iconCls : 'icon-edit',
                modal : false,
                closed : true
            ">
           	<jsp:include page="../components/shebaoCompany.jsp"></jsp:include>
	</div>
</div>
