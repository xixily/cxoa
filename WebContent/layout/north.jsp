<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="sessionInfoDiv" style="position: absolute;right: 5px;top:10px;">
	<c:if test="${sessionInfo != null}">[<strong>${sessionInfo.username}</strong>]，欢迎您！您使用[<strong>${sessionInfo.ip}</strong>]IP登录！</c:if>
</div>
<div style="position: absolute; right: 0px; bottom: 0px; ">
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'icon-ok'">更换皮肤</a>
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_zxMenu',iconCls:'icon-back'">注销</a>
</div>
<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	<div onclick="changeTheme('default');">default</div>
	<div onclick="changeTheme('gray');">gray</div>
	<div onclick="changeTheme('black');">black</div>
	<div onclick="changeTheme('bootstrap');">bootstrap</div>
	<div onclick="changeTheme('metro');">metro</div>
	<div onclick="changeTheme('material');">material</div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div class="menu-sep"></div>
	<div onclick="north.lock(true);">锁定系统</div>
	<div onclick="north.logoutFun(true);">退出系统</div>
</div>
<div id="lock_sys_dialog" class="easyui-dialog" title="解锁" style="width:400px;padding:50px 60px" data-options = "modal:true,closed:true">
	<form id="unlock_login_form">
	<div style="margin-bottom:20px">
	<input id="pass" name="password" class="easyui-passwordbox" prompt="密码..." style="width:100%" data-options="label:'请输入密码解锁:',required:true">
	</div>
	</form>
	<div style="text-align:center">
	<span class="easyui-linkbutton" onclick="north.lock(false)" style="width:80px;">解锁</span>
	<span class="easyui-linkbutton" onclick="$('#unlock_login_form').form('clear')" style="width:80px;">重置</span>
	</div>
</div>