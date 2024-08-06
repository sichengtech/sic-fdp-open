 $(document).ready(function(){
	if(jsValidate){
		$("#inputForm").validate({
			rules: {
				"name":{required: true,maxlength:100,},
				"code":{maxlength:100,},
				"address":{maxlength:255,},
				"zipCode":{regex:/^[1-9]\d{5}(?!\d)$/,maxlength:6,},
				"master":{maxlength:100,},
				"phone":{regex:/^0\d{2,3}-?\d{7,8}$/,maxlength:64,},
				"fax":{maxlength:200,},
				"email":{regex:/^[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?$/,maxlength:200,},
				"remarks":{maxlength:255,},
			},
			messages: {
				"name":{required:"不能为空",maxlength:"最大长度不能超过100位",},
				"code":{maxlength:"最大长度不能超过100位",},
				"address":{maxlength:"最大长度不能超过255位",},
				"zipCode":{regex:"请输入正确格式邮编",maxlength:"最大长度不能超过6位",},
				"master":{maxlength:"最大长度不能超过100位",},
				"phone":{regex:"请输入正确格式电话",maxlength:"最大长度不能超过64位",},
				"fax":{maxlength:"最大长度不能超过200位",},
				"email":{regex:"请输入正确格式邮箱",maxlength:"最大长度不能超过64位",},
				"remarks":{maxlength:"最大长度不能超过255位",},
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
 });