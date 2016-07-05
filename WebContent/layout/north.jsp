<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="sessionInfoDiv" style="position: absolute;right: 5px;top:10px;">
	<c:if test="${sessionInfo != null}">[<strong>${sessionInfo.username}</strong>]，欢迎你！您使用[<strong>${sessionInfo.ip}</strong>]IP登录！</c:if>
</div>
<div style="position: absolute; right: 0px; bottom: 0px; ">
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'icon-ok'">更换皮肤</a>
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_zxMenu',iconCls:'icon-back'">注销</a>
</div>
<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	<div onclick="changeTheme('default');">default</div>
	<div onclick="changeTheme('gray');">gray</div>
	<div onclick="changeTheme('metro');">metro</div>
	<!-- <div onclick="changeTheme('cupertino');">cupertino</div>
	<div onclick="changeTheme('dark-hive');">dark-hive</div> -->
	<!-- <div onclick="changeTheme('pepper-grinder');">pepper-grinder</div>
	<div onclick="changeTheme('sunny');">sunny</div> -->
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div class="menu-sep"></div>
	<%-- <div onclick="logoutFun(true);"><a href="${pageContext.request.contextPath}/user/logout.action">退出系统</a></div> --%>
	<div onclick="north.logoutFun(true);">退出系统</div>
</div>