<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#renshi_form').keydown(function(e){
		if(e.keyCode==13){
		   $('#employee_search').trigger('click');
		}
		});
})
</script>
<form id="fahuoQuery_form" method="post"
	enctype="multipart/form-data">
	<table>
		<tr>
			<td>合同编号:</td>
			<td><input name="hetongCode" class="easyui-textbox" style="width:160px" /></td>
			<td>收件人:</td>
			<td><input name="receiver" class="easyui-textbox" style="width:160px"/></td>
			<td>邮寄单位:</td>
			<td><input name="jDepart" class="easyui-textbox" style="width:160px"/></td>
		</tr>
		<tr>
			<td>邮寄地址:</td>
			<td><input name="jAddress" class="easyui-textbox" style="width:160px"/></td>
			<td>邮寄时间:</td>
			<td><input name="jDate" class="easyui-textbox" style="width:160px"/></td>
			<td>录库人:</td>
			<td><input name="recordBy" class="easyui-textbox" style="width:160px" /></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td>
			<input class="easyui-linkbutton do_action" appaction="hetong.fahuo.queryFahuo"
				type="button" value="查询" onclick="" style="width:46px;height:26px;" />
			</td>
			<td></td>
			<td>
			<input class="easyui-linkbutton" id="btn_reset" type="button" value="重置"
				onclick="clearForm($(this))" style="width:46px;height:26px;" />
			</td>
		</tr>
	</table>
</form>