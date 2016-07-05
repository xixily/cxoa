<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
$(document).ready(function() {
	if(session.logined){
		center.queryEmployee();
	}
})
</script>
	<!-- 人事userName  -->
	<div id="tab_renshiUserName" title="职员信息管理" style="padding: 10px;"
		data-options="iconCls:'icon-man',closable:true">
		<div class="easyui-panel"
			style="width: 98%; padding: 10px; margin-bottom: 10px;">
			<form id="ff" action="form1_proc.php" method="post"
				enctype="multipart/form-data">
				<table>
					<tr>
						<td>职员姓名:</td>
						<td><input name="userName" class="f1 easyui-textbox" /></td>
						<td>性别:</td>
						<td><select class="easyui-combobox" name="sex"
							style="width: 100%; height: 26px;">
								<option value="male">男</option>
								<option value="female">女</option>
						</select></td>
						<td>级别:</td>
						<td><select class="easyui-combobox" name="position"
							style="width: 100%; height: 26px;">
								<option value="guidance">指导</option>
								<option value="cellCore">细胞核</option>
								<option value="employee">职员</option>
						</select></td>
						<td>部门:</td>
						<td><select class="easyui-combobox" name="department"
							style="width: 100%; height: 26px;">
								<option value="qianChang">前场</option>
								<option value="zhongChang">中场</option>
								<option value="houChang">后场</option>
								<option value="jiaGongBu">加工部</option>
						</select></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><input type="submit" value="查询" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div id = "employee_datas" style="width: 98%"></div>
	</div>
