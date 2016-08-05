<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<form id="renshiquery_form" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>菜单名称:</td>
			<td>
                     <input class="easyui-combobox" name="company" data-options="
                    url: 'system/getMenus.action',
                    valueField:'menuName',
                    textField:'menuName',
                    " />
            </td>
            <td></td>
            <td>
            <input class="easyui-linkbutton do_action" appaction="system.menus.queryMenus"  type="button" value="查找"
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