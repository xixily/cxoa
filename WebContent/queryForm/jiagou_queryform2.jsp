<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#jiagou_form').keydown(function(e){
		if(e.keyCode==13){
		   $('#jiagou_serach').trigger('click');
		}
		});
})
</script>
<form id="jiagou_form" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>一级:</td>
			<td><input name="firstLevel" class="easyui-textbox" style="width:100px;" /></td>
			<td>二级:</td>
			<td><input name="secondLevel" class="easyui-textbox" style="width:100px;" /></td>
            <td>三级:</td>
			<td><input name="thirdLevel" class="f1 easyui-textbox" style="width:100px"/></td>
            <td>四级:</td>
			<td><input name="fourthLevel" class="easyui-textbox" style="width:100px" />
			</td>
		</tr>
		<tr>
            <td colspan="4"></td>
            <td>
            <input id="jiagou_serach" class="easyui-linkbutton do_action" appaction="employee.jiagou.queryJiagou"  type="button" value="查找"
				style="width:46px;height:26px;" />
            </td>
            <td></td>
            <td>
            <input class="easyui-linkbutton"  type="button" value="重置"
				onclick="clearForm($(this))" style="width:46px;height:26px;" />
			</td>
		</tr>
	</table>
</form>