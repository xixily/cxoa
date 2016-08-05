<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!-- 考勤管理 -->
<div id="tab_shebaoSummary" title="社保汇总" style="padding: 10px;overflow: hidden"	data-options="iconCls:'icon-edit',closable:true">
<!-- 组合查询form表单 -->
	<div id="form_kaoqin" title="组合查询" class="easyui-panel"
		data-options="iconCls:'icon-search',href:'${pageContext.request.contextPath}/queryForm/shebaoSummary_queryform.jsp',tools:[
				{iconCls:'icon-reload',handler:function(){$('#form_shebaoSummary').panel('open').panel('refresh')}}]"
		style="width: 98%; padding: 10px; margin-bottom: 10px;">
	</div>

	<div style="width: 98%">
		<table id="datagrid_shebaoSummary" class="easyui-datagrid" style="width: 100%;"
			data-options="
			url:'employee/queryShebaoSummary.action',
			title:'考勤表',
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
					<th data-options="field:'username',width:80">姓名</th><br>
					<th data-options="field:'firstLevel',width:100">公司</th>
					<th data-options="field:'secondLevel',width:100">部门</th>
					<th data-options="field:'thirdLevel'">岗位</th>
					<th data-options="field:'fourthLevel'">小组</th>
					<th data-options="field:'identityCard'">身份证号</th>
					<th data-options="field:'chuqinDay'">出勤天数</th>
					<th data-options="field:'zhuanzhengChaeDay'">转正差额天数</th>
					<th data-options="field:'chidaoYingkouDay'">迟到应扣天数</th>
					<th data-options="field:'shiJiaHour'">事假时数</th>
					<th data-options="field:'bingJiaHour'">病假时数</th>
					<th data-options="field:'kuangGongHour'">旷工时数</th>
					<th data-options="field:'hunJiaDay'">婚假天数</th>
					<th data-options="field:'chanJiaDay'">产假天数</th>
					<th data-options="field:'sangJiaDay'">丧家天数</th>
					<th data-options="field:'nianJiaDay'">年假天数</th>
					<th data-options="field:'hiredate',width:100">入职时间</th>
					<th data-options="field:'leaveTime',width:100">离职时间</th>
					<th data-options="field:'zhuanzhengTime',width:100">转正时间</th>
					<th data-options="field:'ruzhiReport',width:100">入职报表</th>
					<th data-options="field:'lizhiReport',width:100">离职报表</th>
					<th data-options="field:'zhuanzhengReport',width:100">转正报表</th>
					<th data-options="field:'kaoQinremarks',width:100">考勤备注</th>
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
