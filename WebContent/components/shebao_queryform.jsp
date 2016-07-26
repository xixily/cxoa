<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<form id="renshiquery_form" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>公司名称:</td>
			<td>
                     <input class="easyui-combobox" name="company" data-options="
                    url: 'employee/getCompany.action',
                    valueField:'cmopany',
                    textField:'cmopany',
                    " />
            </td>
			<td>社保类型:</td>
			<td>
                     <input class="easyui-combobox" name="householdType" data-options="
                    url: 'employee/getShebaoType.action',
                    valueField:'name',
                    textField:'name',
                    " />
            </td>
            <td></td>
            <td>
            <input class="easyui-linkbutton"  type="button" value="查找"
				onclick="" style="width:46px;height:26px;" />
            </td>
            <td></td>
            <td>
            <input class="easyui-linkbutton"  type="button" value="重置"
				onclick="clearForm($(this))" style="width:46px;height:26px;" />
			</td>
		</tr>
	</table>
</form>