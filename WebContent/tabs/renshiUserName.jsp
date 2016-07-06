<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
$(document).ready(function() {
	if(session.logined){
		center.initEmployee();
	}
})
</script>
	<!-- 人事userName  -->
	<div id="tab_renshiUserName" title="职员信息管理" style="padding: 10px;"
		data-options="iconCls:'icon-man',closable:true">
		<div title = "组合查询" class="easyui-panel"
			style="width: 98%; padding: 10px; margin-bottom: 10px;">
			<jsp:include page="../components/queryform.jsp"></jsp:include>
		</div>
		<jsp:include page="../components/toolbar.jsp"></jsp:include>
		<div id = "employee_datas" style="width: 98%"></div>
	</div>
