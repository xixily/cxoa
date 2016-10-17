<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#gongzihuizong_queryform').keydown(function(e){
		if(e.keyCode==13){
		   $('#gongzihuizhong_search').trigger('click');
		}
		});
})
</script>
<form id="gongzihuizong_queryform" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>姓名:</td>
		
			<td><input name="username" class="easyui-textbox" style="width:100px;" /></td>
		
			<td>小组:</td>
			<td><input name="fourthLevel" class="easyui-textbox" style="width:100px;" /></td>
		
            </tr>
         <tr>
         
            <td>
            <input id="gongzihuizhong_search" class="easyui-linkbutton do_action" appaction="employee.gongzihuizong.querygongzihuizong"  type="button" value="查找"
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