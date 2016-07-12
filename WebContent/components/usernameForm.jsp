<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<style>
    .form_width input{
        width: 100px;
    }
</style>
<div class="easyui-panel" style="width:768px;overflow: hidden" >
    <div style="padding:2px">
        <form id="updateUsesrname_form" class="easyui-form form_width"  method="post" data-options="novalidate:false">
            <table cellpadding="2">
                <tr>
                    <td>姓名:</td>
                    <td><input class="easyui-textbox" type="text" name="username" data-options="required:true"/></td>
                    <td>家庭住址:</td>
                    <td><input class="easyui-textbox" type="text" name="homeAddress" data-options="required:true"/></td>
                    <td>毕业时间:</td>
                    <td><input class="easyui-textbox" type="text" name="degreeCertificate" data-options="required:true"/></td>
                    <td>户口地址:</td>
                    <td><input class="easyui-textbox" type="text" name="registeredAddress" data-options="required:true"/></td>
                </tr>
                <tr>
                    <td>公司:</td>
                    <td><input class="easyui-textbox" type="text" name="company" data-options="required:true" readonly="true"/></td>
                    <td>联系电话:</td>
                    <td><input class="easyui-textbox" type="text" name="phoneNumber" data-options="required:true"/></td>
                    <td>身份证复印件:</td>
                    <td><input class="easyui-textbox" type="text" name="identityCardCopy" data-options="required:true"/></td>
                    <td>邮编:</td>
                    <td><input class="easyui-textbox" type="text" name="Postcode" data-options="required:true"/></td>
                </tr>
                <tr>
                    <td>部门:</td><!-- 部门ID下拉 -->
                    <td><input class="easyui-textbox" type="text" name="部门ID" data-options="required:true" readonly="true"/></td>
                    <td>家庭电话:</td>
                    <td><input class="easyui-textbox" type="text" name="homeNumber" data-options="required:true"/></td>
                    <td>户口本:</td>
                    <td><input class="easyui-textbox" type="text" name="familyRegister" data-options="required:true"/></td>
                    <td>备注:</td>
                    <td><input class="easyui-textbox" type="text" name="remarks" data-options="required:true"/></td>
                </tr>
                <tr>
                    <td>岗位:</td><!-- 三级 -->
                    <td><input class="easyui-textbox" type="text" name="" data-options="required:true" readonly="true"/></td>
                    <td>入职时间:</td>
                    <td><input class="easyui-textbox" type="text" name="hiredate" data-options="required:true"/></td>
                    <td>离职证明:</td>
                    <td><input class="easyui-textbox" type="text" name="leavingCertificate" data-options="required:true"/></td>
                    <td>合同编号:</td>
                    <td><input class="easyui-textbox" type="text" name="contractNumber" data-options="required:true"/></td>
                </tr>
                <tr>
                    <td>小组:</td><!-- 四级 -->
                    <td><input class="easyui-textbox" type="text" name="" data-options="required:true" readonly="true"/></td>
                    <td>转正时间:</td>
                    <td><input class="easyui-textbox" type="text" name="zhuanzhengTime" data-options="required:true"/></td>
                    <td>合同:</td>
                    <td><input class="easyui-textbox" type="text" name="contract" data-options="required:true"/></td>
                    <td>计划入保时间:</td>
                    <td><input class="easyui-textbox" type="text" name="dueSocialSecurity" data-options="required:true"/></td>
                </tr>
                <tr>
                    <td>职位:</td>
                    <td><input class="easyui-textbox" type="text" name="position" data-options="required:true"/></td>
                    <td>过去离职日期:</td>
                    <td><input class="easyui-textbox" type="text" name="pastLeaveTime" data-options="required:true"/></td>
                    <td>入职表:</td>
                    <td><input class="easyui-textbox" type="text" name="entryForm" data-options="required:true"/></td>
                    <td>社保医院:</td>
                    <td><input class="easyui-textbox" type="text" name="socialSecurityHospital" data-options="required:true"/></td>
                </tr>
                <tr>
                    <td>性别:</td>
                    <td><input class="easyui-textbox" type="text" name="sex" data-options="required:true"/></td>
                    <td>早期入职时间:</td>
                    <td><input class="easyui-textbox" type="text" name="earlyEntryDate" data-options="required:true"/></td>
                    <td>未签原因:</td>
                    <td><input class="easyui-textbox" type="text" name="noSignReason" data-options="required:true"/></td>
                    <td>现居地址:</td>
                    <td><input class="easyui-textbox" type="text" name="nowAddress" data-options="required:true"/></td>
                </tr>
                <tr>
                    <td>身份证号:</td>
                    <td><input class="easyui-textbox" type="text" name="identityCard" data-options="required:true"/></td>
                    <td>户口性质:</td>
                    <td><input class="easyui-textbox" type="text" name="householdType" data-options="required:true"/></td>
                    <td>签定时间:</td>
                    <td><input class="easyui-textbox" type="text" name="signedTime" data-options="required:true"/></td>
                    <td>现工作地:</td>
                    <td><input class="easyui-textbox" type="text" name="workPlace" data-options="required:true"/></td>
                </tr>
                <tr>
                    <td>出生年月:</td>
                    <td><input class="easyui-textbox" type="text" name="borthDay" data-options="required:true"/></td>
                    <td>保险:</td>
                    <td><input class="easyui-textbox" type="text" name="insurance" data-options="required:true"/></td>
                    <td>终止时间:</td>
                    <td><input class="easyui-textbox" type="text" name="terminationTime" data-options="required:true"/></td>
                    <td>邮箱:</td>
                    <td><input class="easyui-textbox" type="email" name="email" data-options="required:true"/></td>
                </tr>
                <tr>
                    <td>民族:</td>
                    <td><input class="easyui-textbox" type="text" name="nation" data-options="required:true"/></td>
                    <td>保险公司:</td>
                    <td><input class="easyui-textbox" type="text" name="insuranceCompany" data-options="required:true"/></td>
                    <td>离职时间:</td>
                    <td><input class="easyui-textbox" type="text" name="leaveTime" data-options="required:true"/></td>
                    <td>部门调动时间:</td>
                    <td><input class="easyui-textbox" type="text" name="" data-options="required:true"/></td>
                </tr>
                <tr>
                    <td>学历:</td>
                    <td><input class="easyui-textbox" type="text" name="degree" data-options="required:true"/></td>
                    <td>公司名称:</td>
                    <td><input class="easyui-textbox" type="text" name="company" data-options="required:true"/></td>
                    <td>原编号:</td>
                    <td><input class="easyui-textbox" type="text" name="originalNumber" data-options="required:true"/></td>
                    <td>办理工资卡:</td>
                    <td><input class="easyui-textbox" type="text" name="" data-options="required:true"/></td>
                </tr>
                <tr>
                    <td>毕业院校:</td>
                    <td><input class="easyui-textbox" type="text" name="graduatedSchool" data-options="required:true"/></td>
                    <td>报表:</td>
                    <td><input class="easyui-textbox" type="text" name="reportForm" data-options="required:true"/></td>
                    <td>保密协议:</td>
                    <td><input class="easyui-textbox" type="text" name="secrecyAgreement" data-options="required:true"/></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>专业:</td>
                    <td><input class="easyui-textbox" type="text" name="major" data-options="required:true"/></td>
                    <td>照片:</td>
                    <td><input class="easyui-textbox" type="text" name="photo" data-options="required:true"/></td>
                    <td></td>
                    <td></td>
                    <td colspan="2" rowspan="4">
                        <div style="text-align:center;padding:5px">
                            <a id="btn_employeeSave" href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="submitForm($(this))">保存</a>
                            <a id="btn_employeeRest" href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="clearForm($(this))">重置</a>
                            <a id="btn_employeeEdit" href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="center.editEmployee()">编辑</a>
                            <a id="btn_employeeMailto" href="mailto:dengxuefeng@chaoxing.com?cc=dengxuefeng@chaoxing.com&bcc=dengxuefeng@chaoxing.com" style="width:60px;"  class="easyui-linkbutton">发送邮件</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:60px;" onclick="closeDialog($(this))">关闭</a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>负责人:</td>
                    <td><input class="easyui-textbox" type="text" name="" data-options="required:true"/></td>
                    <td>招聘来源:</td>
                    <td><input class="easyui-textbox" type="text" name="recruitmentSources" data-options="required:true"/></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>签字人:</td>
                    <td><input class="easyui-textbox" type="text" name="" data-options="required:true"/></td>
                    <td>关系:</td>
                    <td><input class="easyui-textbox" type="text" name="" data-options="required:true"/></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>级别:</td>
                    <td><input class="easyui-textbox" type="text" name="level" data-options="required:true"/></td>
                    <td>合同续签:</td>
                    <td><input class="easyui-textbox" type="text" name="contractRenewal" data-options="required:true"/></td>
                    <td></td>
                    <td></td>
                </tr>

                </table>
        </form>
    </div>
</div>
