$(function(){ 
	//如果js验证开关是开的就进行js验证
	if(jsValidate){
		$("#inputForm").validate({
			rules: {
				"name":{required:true,maxlength:100,},
				"sort":{required: true,maxlength:10,regex:/^(0|[1-9][0-9]*)$/,},
			},
			messages: {
				"name":{required:"栏目名称不能为空",maxlength:"标题不能超过100位",},
				"sort":{required: "必填项",maxlength:"最大长度不能超过10位",regex:"排序要输入数字",},
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