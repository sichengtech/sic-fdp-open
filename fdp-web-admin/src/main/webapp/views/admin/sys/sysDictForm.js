$(function(){
	//如果js验证开关是开的就进行js验证
	if(jsValidate){
		$("#inputForm").validate({
			rules: {
				"value":{required:true,maxlength:100},
				"label":{required:true,maxlength:100},
				"type":{regex:/^[A-Za-z0-9_]+$/,required:true,maxlength:100},
				"sort":{required: true,maxlength:10,regex:/^(0|[1-9][0-9]*)$/,},
				"description":{required:true,maxlength:100},
				"remarks":{maxlength:255}
			},
			messages: {
				"value":{required:"字典值不能为空",maxlength:"字典值不能超过100位"},
				"label":{required:"字典标签不能为空",maxlength:"字典标签不能超过100位"},
				"type":{regex:"请输入字母数字或下划线",required:"字典类型不能为空",maxlength:"字典类型不能超过100位"},
				"sort":{required: "必填项",maxlength:"最大长度不能超过10位",regex:"排序要输入数字",},
				"description":{required:"描述不能为空",maxlength:"描述不能超过100位"},
				"remarks":{maxlength:"备注不能超过255位"}
			},
			submitHandler: function(form){
				layer.msg('正在提交，请稍等...', {icon: 16,shade: 0.30,time: 0});
				form.submit();
			}
		});
	}
});