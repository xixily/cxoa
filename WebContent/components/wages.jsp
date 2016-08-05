<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<style>
    .form_width input{
        width: 86px;
    }
    .spanFont{
    	font-size: 12px;
    	color: gray;
    }
</style>
<div class="easyui-panel" style="width:960px;overflow: hidden" ><!-- height:560px; -->
	<!-- 个人文本信息 -->
	<div style="padding: 5px 20px;margin: 10 0px;">
		<span class = "spanFont"><strong>姓名 ：</strong><span id="wages_username"></span></span> 
		<span class="btn-separator" style="margin:0 30px;"></span>
		<span class = "spanFont"><strong>身份证号：</strong><span id="wages_identity"></span></span> 
		<span class="btn-separator" style="margin:0 30px;"></span>
		<span class = "spanFont"><strong>工资总额：</strong><span id="wages_totalSalary"></span>元</span> 
		<span class="btn-separator" style="margin:0 30px;"></span>
		<span class = "spanFont"><strong>公司名称：</strong><span id="wages_company"></span></span> 
		<!-- <input id="wages_hidden_employeeid" type="hidden" name="employeeId" /> -->
		<br/>
	</div>
	
	<div style="margin:0 8px;">
	<table id="datagrid_wages" class="easyui-datagrid" style="width:100%;height:250px;margin:5 20px;"> 
	</table>
	</div>
    <div style="padding:2px;margin:10px auto;border: 1px dashed LightGrey;width:934px;">
        <form id="updatewages_form" class="easyui-form form_width"  method="post"  data-options="novalidate:true">
            <table cellpadding="2">
                <tr>
               		<td>工资编号</td>
                    <td> 
                    <input id="wages_id" class="easyui-textbox" type="text" name="id" />
                    </td>
                    <td>姓名:</td>
                    <td>
                    <input id="wages_hidden_employeeid" type="hidden" name="employeeId" />
                    <input class="easyui-textbox" type="text" name="username" />
                    </td>
                    <td>工资总额:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="salary" />
                    </td>
                   
                    <td>保密补贴:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="secrecySubsidy" />
                    </td>
                    <td>通讯补贴:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="communicationSubsidy" />
                    </td>
                </tr>
                <tr>
                    <td>午餐补贴:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="lunchSubsidy" />
                    </td>
                    <td>备注:</td>
                    <td>
                    <input class="easyui-textbox" type="text" name="remarks" />
                    </td>
                     <td>公司名称:</td>
                    <td  id="wages_company">
                     <input class="easyui-combobox" name="company" data-options="
                    url: 'employee/getCompany.action',
                    valueField:'cmopany',
                    textField:'cmopany',
                    " />
                    </td>
                    <!-- <td>公司名称:</td>
                    <td>
                    <input class="easyui-textbox" type="text" name="company" />
                    </td> -->
                    <td>基数:</td>
                    <td>
                    <input id="wages_radix" class="easyui-numberbox" data-options="min:0,precision:2" name="radix" />
                    </td>
                    <td>户口性质:</td>
                    <td id="wages_hoseholdType" >
                    <input class="easyui-combobox" name="householdType"  data-options="
                    valueField:'name',
                    textField:'name',
                    data : [{name:'外埠城镇'},{name:'外埠农村'},{name:'本市城镇'},{name:'本市农村'},{name:'其他'}]
                    ">
                    </td>
                    <!-- <td>户口性质:</td>
                    <td>
                    <input class="easyui-textbox" type="text" name="householdType" />
                    </td> -->
                </tr>
                <tr>
                    <td>基本工资:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="basicWage" />
                    </td>
                    <td>岗位工资:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="postSalary" />
                    </td>
                    <td>绩效工资:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="performanceRelatedPay" />
                    </td>
                    <td>开户行:</td>
                    <td>
                    <input class="easyui-textbox" type="text" name="accountBank" />
                    </td>
                    <td>职工帐号:</td>
                    <td>
                    <input class="easyui-textbox" type="text" name="account" />
                    </td>
                </tr>
                <tr>
                	<td>病假累计:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="sickLleaveTotal" />
                    </td>
                    <td>代扣养老保险:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="subEndowmentIinsurance" />
                    </td>
                    <td>代扣医疗保险:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="subMedicare" />
                    </td>
                    <td>代扣失业保险:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="subUnemployedInsurance" />
                    </td>
                    <td>代扣住房保险:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="subHouseIinsurance" />
                    </td>
                </tr>
                <tr>
                    <td>公司养老保险:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="cEndowmentIinsurance" />
                    </td>
                    <td>公司医疗保险:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="cMedicare" />
                    </td>
                    <td>公司失业保险:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="cUnemployedInsurance" />
                    </td>
                    <td>公司住房保险:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="cHouseIinsurance" />
                    </td>
                    <td>累计带薪病假:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="cSickPayTotal" />
                    </td>
                </tr>
                <tr>
                    <td>公司工伤保险:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="cInjuryInsurance" />
                    </td>
                    <td>公司生育保险:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="cBirthIinsurance" />
                    </td>
                    <td>年假累计:</td>
                    <td>
                    <input class="easyui-numberbox" data-options="min:0,precision:2" name="annualLleave" />
                    </td>
                    <td>办公电话:</td>
                    <td>
                    <input class="easyui-textbox" type="text" name="workPhone" />
                    </td>
                    <td>内部编号:</td>
                    <td>
                    <input class="easyui-textbox" type="text" name="internalNumber" />
                    </td>
                </tr>
                <tr>
                 	<td>身份证号码:</td>
                    <td>
                    <input class="easyui-textbox" type="text" name="identityCard" />
                    </td>
                 	<td>入保时间:</td>
                    <td>
                    <input class="easyui-textbox" type="text" name="rubaoTime" />
                    </td>
                    <td colspan="6">
                    <div align="right" style="margin-right:10px;">
                      <a id="wages_save" href="javascript:void(0)" style="width:60px;display:none;" class="easyui-linkbutton" onclick="employee.wages.updateWages($(this));">保存</a>
                      <a id="wages_add" href="javascript:void(0)" style="width:60px;display:none;" class="easyui-linkbutton" onclick="employee.wages.addWages($(this));">新增</a>
<!--                       <a id="wages_add" href="javascript:void(0)" style="width:60px;display:none;" class="easyui-linkbutton" onclick="$('#updatewages_form').form({url:'employee/addWages.action'});submitForm($(this))">新增</a> -->
                      <a href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="clearForm($(this))">重置</a>
                      <a id="wages_edit" href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="employee.wages.editWages()">编辑</a>
                     <!--  <a href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="confirmDialog.createDialog()">删除</a> -->
                      <a href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="closeDialog($(this))">关闭</a>
                    </div>
                    </td>
                </tr>
                
                </table>
        </form>
    </div>
</div>