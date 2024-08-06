$(document).ready(function(){
	if(jsValidate){
		$("#inputForm").validate({
			rules: {
				qq:{regex:/^[1-9][0-9]{4,}$/,maxlength:64,},
				email:{regex:/^[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?$/,maxlength:64,},
				phone:{regex:/^0\d{2,3}-?\d{7,8}$/,maxlength:64,},
				mobile:{regex:/^1\d{10}$/,maxlength:64,},
				remarks:{maxlength:255,},
			},
			messages: {
				qq:{regex:"请输入4位以上的数字",maxlength:"最大长度不能超过64位",},
				email:{regex:"请输入正确格式邮箱",maxlength:"最大长度不能超过64位",},
				phone:{regex:"请输入正确格式电话",maxlength:"最大长度不能超过64位",},
				mobile:{regex:"请输入正确格式手机",maxlength:"最大长度不能超过64位",},
				remarks:{maxlength:"最大长度不能超过255位",},
			},
			submitHandler: function(form){
				layer.msg('正在提交，请稍等...', {icon: 16,shade: 0.30,time: 0});
				form.submit();
			}
		});
	}
 });