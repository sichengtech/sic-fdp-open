$(function(){
	if(jsValidate){
		var oldNum = $("#oldNum").val();
		$("#inputForm").validate({
			rules: {
				"taskName":{required: true,maxlength:64,},
				"taskExplain":{maxlength:64,},
				"taskTime":{required: true,maxlength:64,},
				"timeExplain":{maxlength:64,},
				"status":{maxlength:1,},
				"timedTaskNum":{required: true,maxlength:10,regex:/^[0-9]*$/,
					remote: ctxa+"/sys/sysTimedTask/exitNum.do?oldNum=" + encodeURIComponent(oldNum),},
			},
			messages: {
				"taskName":{required: "必填项",maxlength:"最大长度不能超过64位",},
				"taskExplain":{maxlength:"最大长度不能超过64位",},
				"taskTime":{required: "必填项",maxlength:"最大长度不能超过64位",},
				"timeExplain":{maxlength:"最大长度不能超过64位",},
				"status":{maxlength:"最大长度不能超过1位",},
				"timedTaskNum":{required: "必填项",maxlength:"最大长度不能超过10位",regex:"编号要输入数字",remote: "编号已存在",},
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
});