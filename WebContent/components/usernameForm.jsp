<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    .form_width input{
        width: 100px;
    }
</style><!-- 768 -->
<div class="easyui-panel" style="width:960px;overflow: hidden" >
    <div style="padding:2px">
        <form id="updateUsesrname_form" class="easyui-form"  method="post">
            <table cellpadding="2">
                <tr>
                    <td>姓名:</td>
                    <td>
					<input id="form_hidden_ID" type="hidden" name="id" />
                    <input class="easyui-textbox" type="text" name="username"  data-options="required:true" /></td>
                    <td>家庭住址:</td>
                    <td><input id="textbox_addrss" class="easyui-textbox" type="text" name="homeAddress" /></td>
                    <td>毕业时间:</td>
                    <td><input class="easyui-datebox" type="text" name="degreeCertificate" /></td>
                    <td>户口地址:</td>
                    <td><input id="textbox_hukou" class="easyui-textbox" type="text" name="registeredAddress" /></td>
                </tr>
                <tr>
                    <td>公司:</td>
                    <td>
                    <input id="input_firlevel" class="easyui-textbox" type="text" name="firstLevel" data-options="required:true" readonly="true"/>
                    <!-- <input class="easyui-textbox" type="text" name="company"  readonly="true"/> -->
                    </td>
                    <td>联系电话:</td>
                    <td><input class="easyui-textbox" type="text" name="phoneNumber" data-options="prompt:'请输入11位手机号码...',validType:'mobile'
                    " /></td>
                    <td>身份证复印件:</td>
                    <td><input class="easyui-textbox" type="text" name="identityCardCopy" /></td>
                    <td>邮编:</td>
                    <td><input class="easyui-textbox" type="text" name="postCode" /></td>
                </tr>
                <tr>
                    <td>部门:</td><!-- 部门ID下拉 -->
                    <td><input id="input_secondLevel" class="easyui-textbox" type="text" name="secondLevel" data-options="required:true"  readonly="true"/></td>
                    <td>家庭电话:</td>
                    <td><input class="easyui-textbox" type="text" name="homeNumber" /></td>
                    <td>户口本:</td>
                    <td><input class="easyui-textbox" type="text" name="familyRegister" /></td>
                    <td>备注:</td>
                    <td><input class="easyui-textbox" type="text" name="remarks" /></td>
                </tr>
                <tr>
                    <td>岗位:</td><!-- 三级 -->
                    <td><input id="input_thirdLevel" class="easyui-textbox" type="text" name="thirdLevel" data-options="required:true" readonly="true"/></td>
                    <td>入职时间:</td>
                    <td><input id="date_hiredate" class="easyui-datebox" type="text" name="hiredate" /></td>
                    <td>离职证明:</td>
                    <td><input class="easyui-textbox" type="text" name="leavingCertificate" /></td>
                    <td>合同编号:</td>
                    <td><input class="easyui-textbox" type="text" name="contractNumber" /></td>
                </tr>
                <tr>
                    <td>小组:</td><!-- 四级 -->
                    <td>
                    <input id="form_hidden_departmentId" type="hidden" name="departmentId" />
                     <input id="combox" class="easyui-combobox" name="fourthLevel" style="width:103px;height:26px;" data-options="
                    url: 'employee/getOStruct.action',
                    valueField:'fourthLevel',
                    textField:'fourthLevel',
                    onSelect: function(rec){
                    	$.each(session.organizationStructure, function(i, obj){
                    	if(obj.fourthLevel == rec.fourthLevel){
                    		$('#updateUsesrname_form').form('load',{
                    			firstLevel : obj.firstLevel,
                    			secondLevel : obj.secondLevel,
                    			thirdLevel : obj.thirdLevel,
                    			cellCore : obj.cellCore,
                    			departmentId : obj.departmentId
                    		});
                    	}
                    	})
		            },
			        onLoadSuccess: function(res){
			        session.organizationStructure = res;
			        }
                    ">
                    </td>
                    <td>转正时间:</td>
                    <td><input class="easyui-datebox" type="text" name="zhuanzhengTime" /></td>
                    <td>合同:</td>
                    <td><input class="easyui-textbox" type="text" name="contract" /></td>
                    <td>投保时间:</td>
                    <td><input class="easyui-textbox" type="text" name="dueSocialSecurity" /></td>
                </tr>
                <tr>
                    <td>职位:</td>
                    <td><input class="easyui-textbox" type="text" name="position" /></td>
                    <td>过去离职日期:</td>
                    <td><input class="easyui-datebox" type="text" name="pastLeaveTime" /></td>
                    <td>入职表:</td>
                    <td><input class="easyui-textbox" type="text" name="entryForm" /></td>
                    <td>社保医院:</td>
                    <td><input class="easyui-textbox" type="text" name="socialSecurityHospital" /></td>
                </tr>
                <tr>
                    <td>性别:</td>
                    <td>
                    	<input id="combox_sex" class="easyui-combobox" name="sex" data-options="
							valueField: 'label',
							textField: 'value',
							data: [{
								label: '男',
								value: '男'
							},{
								label: '女',
								value: '女'
							}]" />
                    </td>
                    <td>早期入职时间:</td>
                    <td><input id="datebox_entry" class="easyui-datebox" type="text" name="earlyEntryDate" /></td>
                    <td>未签原因:</td>
                    <td><input class="easyui-textbox" type="text" name="noSignReason" /></td>
                    <td>现居地址:</td>
                    <td><input class="easyui-textbox" type="text" name="nowAddress" /></td>
                </tr>
                <tr>
                    <td>身份证号:</td>
                    <td><input id="textbox_id" class="easyui-textbox" name="identityCard" data-options="prompt:'请输入18位身份证号...',required:true,validType:'idcard'
                    "/></td>
                    <td>户口性质:</td>
                    <td>
                    <input id="combox_hoseholdType" class="easyui-combobox" name="householdType"  data-options="
                    valueField:'name',
                    textField:'name',
                    data : [{name:'外埠城镇'},{name:'外埠农村'},{name:'本市城镇'},{name:'本市农村'},{name:'其他'}]
                    ">
                    </td>
                    <td>签定时间:</td>
                    <td><input class="easyui-datebox" type="text" name="signedTime" /></td>
                    <td>现工作地:</td>
                    <td><input class="easyui-textbox" type="text" name="workPlace" /></td>
                </tr>
                <tr>
                    <td>出生年月:</td>
                    <td><input id="textbox_borth" class="easyui-textbox" type="text" name="borthDay" /></td>
                    <td>保险:</td>
                    <td><input class="easyui-textbox" type="text" name="insurance" /></td>
                    <td>终止时间:</td>
                    <td><input class="easyui-datebox" type="text" name="terminationTime" /></td>
                    <td>邮箱:</td>
                    <td><input id="vie_mail" class="easyui-textbox" type="email" name="email" data-options="required:true, validType:'email'" /></td>
                </tr>
                <tr>
                    <td>民族:</td>
                    <td><input id="text_nation" class="easyui-textbox" type="text" name="nation" ></td>
                    <td>保险公司:</td>
                    <td><!-- <input class="easyui-textbox" type="text" name="insuranceCompany" /> -->
                    <input class="easyui-combobox" name="insuranceCompany" data-options="
                    url: 'employee/getInsuranceCompany.action',
                    valueField:'value',
                    textField:'text',
                    " />
                    </td>
                    <td>离职时间:</td>
                    <td><input class="easyui-datebox" type="text" name="leaveTime" /></td>
                    <td>部门调动时间:</td>
                    <td><input class="easyui-datebox" type="text" name="" /></td>
                </tr>
                <tr>
                    <td>学历:</td>
                    <td>
                   <!--  <input class="easyui-textbox" type="text" name="degree" /> -->
                    	<input id="combox_degree" class="easyui-combobox" style="width:103px;" name="degree"  data-options="
							valueField: 'name',
							textField: 'name',
							data: [
							{name : '博士'	},
							{name : '硕士'	},
							{name : '本科'	},
							{name : '大专'	},
							{name : '高中'	},
							{name : '中专'	},
							{name : '技校'	},
							{name : '初中'	},
							{name : '其他'	}
							]" />
                    </td>
                    <td>公司名称:</td>
                    <td>
                     <input class="easyui-combobox" name="company" data-options="
                    url: 'employee/getCompany.action',
                    valueField:'cmopany',
                    textField:'cmopany',
                    " />
                    </td>
                    <td>原编号:</td>
                    <td><input class="easyui-textbox" type="text" name="originalNumber" /></td>
                    <td>办理工资卡:</td>
                    <td><input class="easyui-textbox" type="text" name="panCard" /></td>
                </tr>
                <tr>
                    <td>毕业院校:</td>
                    <td><input class="easyui-textbox" type="text" name="graduatedSchool" /></td>
                    <td>报表:</td>
                    <td><input class="easyui-textbox" type="text" name="reportForm" /></td>
                    <td>保密协议:</td>
                    <td><input class="easyui-textbox" type="text" name="secrecyAgreement" /></td>
                    <c:if test="${sessionInfo.roleId <= 1}">
                    <td>工资保密</td>
                    <td><input name="ifSecret" class="easyui-switchbutton" data-options="onText:'Yes',offText:'No'" onclick="center.ifSecret"></td>
                    </c:if>
                </tr>
                <tr>
                    <td>专业:</td>
                    <td><input class="easyui-textbox" type="text" name="major" /></td>
                    <td>照片:</td>
                    <td><input class="easyui-textbox" type="text" name="photo" /></td>
                    <td></td>
                    <td></td>
                    <td colspan="2" rowspan="4">
                        <div style="text-align:center;padding:5px">
                            <a id="btn_employeeSave" href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="submitForm($(this))">保存</a>
                            <a id="btn_employeeRest" href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="clearForm($(this))">重置</a>
                            <a id="btn_employeeEdit" href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="center.editEmployee()">编辑</a><br/>
                            <a id="btn_wagesInfo" href="javascript:void(0)" class="easyui-linkbutton" style="width:60px;" onclick="center.wages.openWages(this)">工资信息</a>
                            <!-- <a id="btn_wagesInfo" href="javascript:void(0)" class="easyui-linkbutton" style="width:60px;" onclick="$('#dialog_wagesInfo').dialog('open')">工资信息</a>  -->
                            <a id="btn_employeeMailto" href="mailto:dengxuefeng@chaoxing.com?cc=dengxuefeng@chaoxing.com&bcc=dengxuefeng@chaoxing.com" style="width:60px;"  class="easyui-linkbutton">发送邮件</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:60px;" onclick="closeDialog($(this))">关闭</a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>负责人:</td>
                    <td><input class="easyui-textbox" type="text" name="cellCore" /></td>
                    <td>招聘来源:</td>
                    <td><input class="easyui-textbox" type="text" name="recruitmentSources" /></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>签字人:</td>
                    <td><input class="easyui-textbox" type="text" name="cellCore" /></td>
                    <td>关系:</td>
                    <td><input class="easyui-textbox" type="text" name="managementSystem" /></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>级别:</td>
                    <td>
                    <input id="combox_level" class="easyui-combobox" name="level" data-options="
                    url: 'employee/getLevel.action',
                    valueField:'name',
                    textField:'name',
			        onLoadSuccess: function(res){
			        session.level = res;
			        }
                    ">
                    </td>
                    <td>合同续签:</td>
                    <td><input class="easyui-textbox" type="text" name="contractRenewal" /></td>
                    <td></td>
                    <td></td>
                </tr>

                </table>
        </form>
    </div>
</div>
