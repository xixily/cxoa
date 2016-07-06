<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="renshiquery_form" action="renshiUserName.action" method="post"
				enctype="multipart/form-data">
				<table>
					<tr>
						<td>职员姓名:</td>
						<td><input name="username" class="f1 easyui-textbox" /></td>
						
						<td>身份证号:</td>
						<td><input name="identityCard" class="f1 easyui-textbox" /></td>
						
						<td>公司名称:</td>
						<td><input name="company" class="f1 easyui-textbox" /></td>
						
						<td>级别:</td>
						<td><select class="easyui-combobox" name="position"
							style="width: 100%; height: 26px;">
								<option value=""></option>
								<option value="指导">指导</option>
								<option value="细胞核">细胞核</option>
								<option value="职员">职员</option>
						</select></td>
						<!-- <td rowspan="2" colspan="2">
							<div>
								<table>
									<tr></tr>
								</table>
							</div>
						</td> -->
					</tr>
					<tr>
						<td>入职时间:</td>
						<td>
						<input name="hiredate" class="easyui-datetimebox"/>
						</td>

						<td>离职时间:</td>
						<td>
						<input name="leaveTime" class="easyui-datetimebox"/>
						</td>

						<td>转正时间:</td>
						<td>
						<input name="zhuanzhengTime" class="easyui-datetimebox"/>
						</td>
						
						<td>计划入保时间:</td>
						<td>
						<input name="dueSocialSecurity" class="easyui-datetimebox"/>
						</td>
						
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><input class="do_action" appaction="center.queryEmployee" type="button" value="查询" onclick="" /></td>
						<td></td>
						<td><input id="btn_reset" type="button" value="重置" onclick="$('#renshiquery_form').form('clear')" /></td>
					</tr>
				</table>
			</form>