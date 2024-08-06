	 $(function(){
		if(jsValidate){
			$("#inputForm").validate({
				rules: {
					oldPassword:{required:true,maxlength:100,},
					newPassword:{regex:/^\S{6,}$/,required:true,maxlength:100,},
					confirmNewPassword:{required:true,equalTo:"#newPassword",maxlength:10,},
				},
				messages: {
					oldPassword:{required:"旧密码不能为空",maxlength:"密码不能超过11位",},
					newPassword:{regex:"密码至少6位",required:"新密码不能为空",maxlength:"密码不能超过10位",},
					confirmNewPassword:{required:"确认新密码不能为空",equalTo:"新密码和确认新密码不一致",maxlength:"密码不能超过10位",},
				},
				submitHandler: function(form){
					top.$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
					$("button[type='submit']").prop("disabled",true);
					form.submit();
				}
			});
		}
	 });