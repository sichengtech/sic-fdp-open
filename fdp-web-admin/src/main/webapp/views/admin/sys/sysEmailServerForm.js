$(function(){
	if(jsValidate){
		$("#inputForm").validate({
			rules: {
				"status":{required: true,maxlength:1,},
				"sender":{maxlength:64,},
				"smtp":{maxlength:64,},
				"port":{maxlength:64,},
				"username":{maxlength:64,},
				"pwd":{maxlength:64,},
				"safe":{maxlength:64,},
			},
			messages: {
				"status":{required: "必填项",maxlength:"最大长度不能超过1位",},
				"sender":{maxlength:"最大长度不能超过64位",},
				"smtp":{maxlength:"最大长度不能超过64位",},
				"port":{maxlength:"最大长度不能超过64位",},
				"username":{maxlength:"最大长度不能超过64位",},
				"pwd":{maxlength:"最大长度不能超过64位",},
				"safe":{maxlength:"最大长度不能超过64位",},
			},
			errorPlacement: function(error, element) {
				//错误提示信息的显示位置
				if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			},
			submitHandler: function(form){
				//对checkbox处理，1：选中；0：未选中
				$("input[type=checkbox]").each(function(){
					$(this).after("<input type=\"hidden\" name=\""+$(this).attr("name")+"\" value=\""
							+($(this).attr("checked")?"1":"0")+"\"/>");
					$(this).attr("name", "_"+$(this).attr("name"));
				});
				layer.msg('正在提交，请稍等...', {icon: 16,shade: 0.30,time: 0});
				form.submit();
			}
		});
	}
	$(".test-email-btn").click(function(){
		$(this).parent().css({"display":"none"});
		$(".test-email").css({"display":"block"});
	});
	$("#send").click(function(){
		var myreg = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
		var email=$("#email").val();
		if(email==null || email==''){
			fdp.msg("请输入邮箱地址");
			return false;
		}
		if (!myreg.test(email)){
			fdp.msg("邮箱地址格式不正确");
			return false;
		}
		$.ajax({
			url:ctxa+"/sys/sysEmailServer/emailTest.do",
			type:'POST',
			data:{"email":email},
			dataType:'json',
			success:function(data){
				if(data.status == 1){
					fdp.msg("测试邮件发送成功");
				}
				if(data.status == 0){
					fdp.msg("测试邮件发送失败");
				}
			}
		});
	});
});