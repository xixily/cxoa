<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<form id="kaoqin_form" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>姓名:</td>
			<td><input name="username" class="easyui-textbox" /></td>
			<td>公司名称:</td>
			<td>
                     <input class="easyui-combobox" name="company" data-options="
                    url: 'employee/getCompany.action',
                    valueField:'cmopany',
                    textField:'cmopany',
                    " />
            </td>
            <td>入职时间:</td>
			<td><input name="hiredate" class="easyui-datebox" style="width:160px" />
			</td>

			<td>离职时间:</td>
			<td><input name="leaveTime" class="easyui-datebox" style="width:160px" /></td>

			<td>转正时间:</td>
			<td><input name="zhuanzhengTime" class="easyui-datebox" style="width:160px" /></td>
            <td></td>
            <td>
            <input class="easyui-linkbutton do_action" appaction="employee.monthWages.queryMonthWages"  type="button" value="查找"
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