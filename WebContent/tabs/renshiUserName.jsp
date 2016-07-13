<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
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
	<!-- 组合查询form表单 -->
	<div id="renshi_form" title="组合查询" class="easyui-panel"
		data-options="iconCls:'icon-search',href:'employee/getQueryForm.action',tools:[
				{iconCls:'icon-reload',handler:function(){$('#renshi_form').panel('open').panel('refresh')}}]"
		style="width: 98%; padding: 10px; margin-bottom: 10px;"></div>
	<%-- <jsp:include page="../components/toolbar.jsp"></jsp:include> --%>
	<div id="employee_datas" style="width: 98%"></div><!-- 780  -->
	<div id="userName_info" class="easyui-dialog" title="查看修改职员信息"
		style="width: 980px;overflow: hidden"
		data-options="
                iconCls : 'icon-edit',
              <!--   href : 'components/usernameForm.jsp', -->
                modal : false,
                closed : true
            ">
           	<jsp:include page="../components/usernameForm.jsp"></jsp:include>
	</div>
</div>
