<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="renshiquery_form" action="renshiUserName.action" method="post"
	enctype="multipart/form-data">
	<table>
		<tr>
			<td>职员姓名:</td>
			<td><input name="username" class="f1 easyui-textbox" style="width:160px" /></td>

			<td>身份证号:</td>
			<td><input name="identityCard" class="f1 easyui-textbox" style="width:160px"/></td>

			<td>公司名称:</td>
			<td>
			<select name="company" class="easyui-combobox" style="width: 160px; height: 26px;">
			<option value=""></option>
			<c:forEach items="${companys}" var="company">
				<option value="${company.cmopany}">${company.cmopany}</option>
			</c:forEach>
			</select>			

			<td>级别:</td>
			<td><select class="easyui-combobox" name="level" style="width: 160px; height: 26px;">
			<option value=""></option>
			<c:forEach items="${levels}" var="level">
				<option value="${level.name}">${level.name}</option>
			</c:forEach>
			</select></td>
			<td rowspan="2">
				<div class="comboxy">
					<span class="combtitle">可配查询</span> 
					<select class="easyui-combobox"	name="configurable" style="margin-bottom: 2px; height: 26px;width:120px;" >
						<option value=""></option>
						<option value="username">username</option>
						<option value="firstLevel">一级</option>
						<option value="secondLevel">二级</option>
						<option value="thirdLevel">三级</option>
						<option value="fourthLevel">四级</option>
						<option value="cellCore">细胞核</option>
						<option value="cellCoreEmail">细胞核邮箱</option>
						<option value="guidance">指导</option>
						<option value="guidanceEmail">指导邮箱</option>
						<option value="position">职位</option>
						<option value="workPlace">工作地点</option>
						<option value="sex">性别</option>
						<option value="borthDay">出生年月</option>
						<option value="nation">民族</option>
						<option value="degree">学历</option>
						<option value="graduatedSchool">毕业院校</option>
						<option value="major">专业</option>
						<option value="phoneNumber">联系电话</option>
						<option value="homeAddress">家庭地址</option>
						<option value="homeNumber">家庭电话</option>
						<option value="householdType">户口性质</option>
						<option value="insurance">保险</option>
						<option value="insuranceCompany">保险公司</option>
						<option value="resume">简历</option>
						<option value="photo">照片</option>
						<option value="degreeCertificate">学历证件</option>
						<option value="pastLeaveTime">过去离职日期</option>
						<option value="earlyEntryDate">早期入职日期</option>
						<option value="identityCardCopy">身份证复印件</option>
						<option value="familyRegister">户口本</option>
						<option value="leavingCertificate">离职证明</option>
						<option value="contract">合同</option>
						<option value="managementSystem">管理制度</option>
						<option value="signedTime">签定时间</option>
						<option value="terminationTime">终止时间</option>
						<option value="registeredAddress">户口地址</option>
						<option value="Postcode">邮编</option>
						<option value="remarks">备注</option>
						<option value="contractNumber">合同编号</option>
						<option value="dueSocialSecurity">计划入保时间</option>
						<option value="socialSecurityHospital">社保医院</option>
						<option value="originalNumber">原编号</option>
						<option value="recruitmentSources">招聘来源</option>
						<option value="contractRenewal">合同续签</option>
						<option value="originalNumber">原编号</option>
						<option value="secrecyAgreement">保密协议</option>
						<option value="panCard">办理工资卡</option>
						<option value="email">邮箱</option>
					</select>
					<div style="height: 2px; border: 0px;"></div>
					<input name="configurable_value" class="f1 easyui-textbox" />
				</div>
			</td>
		</tr>
		<tr>
			<td>入职时间:</td>
			<td><input name="hiredate" class="easyui-datetimebox" style="width:160px"/></td>

			<td>离职时间:</td>
			<td><input name="leaveTime" class="easyui-datetimebox" style="width:160px"/></td>

			<td>转正时间:</td>
			<td><input name="zhuanzhengTime" class="easyui-datetimebox" style="width:160px"/></td>

			<td>计划入保时间:</td>
			<td><input name="dueSocialSecurity" class="easyui-datetimebox" style="width:160px"/>
			</td>

		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>
				<input class="easyui-linkbutton do_action" appaction="center.queryEmployee"
				type="button" value="查询" onclick="" style="width:46px;height:26px;" />
			</td>
			<td></td>
			<td><input class="easyui-linkbutton" id="btn_reset" type="button" value="重置"
				onclick="$('#renshiquery_form').form('clear')" style="width:46px;height:26px;" /></td>
		</tr>
	</table>
</form>