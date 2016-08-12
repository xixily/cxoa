<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- 登陆窗口  -->
<div id="login_dlg" class="easyui-dialog" title="登陆窗口"
	data-options="iconCls:'icon-save'," style="width: 400px; padding: 10px">
	<div style="padding: 10px 60px 20px 60px">
		<form id="login_form" method="post" action="user/login.action">
			<table cellpadding="8">
				<tr>
					<td>登陆邮箱:</td>
					<td><input class="easyui-textbox" type="text" name="email"
						data-options="required:true,validType:'email'">
						<span style="color: red">${user_login_error }</span>
					</td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input class="easyui-textbox" type="password">
					<span color="red">${password_login_error }</span>
					</td>
				</tr>
			</table>
			<input style="margin-left: 40px;" type="button" onclick="login(this)"
				value="登陆"> <input style="margin-left: 80px;" type="button"
				value="重置" onclick="$('#login_form').form('clear')"> <br>
		</form>
	</div>
</div>