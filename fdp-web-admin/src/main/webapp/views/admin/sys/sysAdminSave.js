$(function(){
	if(jsValidate){
		$("#inputForm").validate({
			rules: {
				"no":{required:true,maxlength:100,},
				"name":{required:true,maxlength:100,},
				"loginName":{required:true,maxlength:100,},
				"newPassword":{regex:/^\S{6,}$/,required:true,maxlength:100,},
				"confirmNewPassword":{required:true,equalTo:"#newPassword",maxlength:200,},
				"qq":{regex:/^[1-9][0-9]{4,}$/,maxlength:64,},
				"email":{regex:/^[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?$/,maxlength:200,},
				"phone":{regex:/^0\d{2,3}-?\d{7,8}$/,maxlength:64,},
				"mobile":{regex:/^1\d{10}$/,maxlength:64,},
				"roleIdList":{required:true,},
				"remarks":{maxlength:255,},
			},
			messages: {
				"no":{required:"工号不能为空",maxlength:"工号不能超过100位",},
				"name":{required:"姓名不能为空",maxlength:"姓名不能超过100位",},
				"loginName":{required:"登录名不能为空",maxlength:"登录名不能超过100位",},
				"newPassword":{regex:"密码至少6位",required:"新密码不能为空",maxlength:"密码不能超过10位",},
				"confirmNewPassword":{required:"确认新密码不能为空",equalTo:"新密码和确认新密码不一致",maxlength:"密码不能超过10位",},
				"qq":{regex:"请输入4位以上的数字",maxlength:"最大长度不能超过64位",},
				"email":{regex:"请输入正确格式邮箱",maxlength:"最大长度不能超过64位",},
				"phone":{regex:"请输入正确格式电话",maxlength:"最大长度不能超过64位",},
				"mobile":{regex:"请输入正确格式手机",maxlength:"最大长度不能超过64位",},
				"roleIdList":{required:"请选择用户角色",},
				"remarks":{maxlength:"最大长度不能超过255位",},
			},
			errorPlacement: function(error, element) {
					//$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent().parent());
					} else {
						error.insertAfter(element);
					}
				},
			submitHandler: function(form){
				layer.msg('正在提交，请稍等...', {icon: 16,shade: 0.30,time: 0});
				form.submit();
			}
		});
	}
 });