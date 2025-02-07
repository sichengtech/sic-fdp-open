$(function(){
	if(jsValidate){
		$("#inputForm").validate({
			rules: {
				"status":{required: true,maxlength:1,},
				"ebusinessId":{required: true,maxlength:64,},
				"appkey":{required: true,maxlength:64,},
				"url":{required: true,maxlength:128,},
			},
			messages: {
				"status":{required: "必填项",maxlength:"最大长度不能超过1位",},
				"ebusinessId":{required: "必填项",maxlength:"最大长度不能超过64位",},
				"appkey":{required: "必填项",maxlength:"最大长度不能超过64位",},
				"url":{required: "必填项",maxlength:"最大长度不能超过128位",},
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
	
	$(".test-express-btn").click(function(){
		$(this).parent().css({"display":"none"});
		$(".test-express").css({"display":"block"});
	});
	
	/**
	 * 测试发送短信
	 */
	$("#send").click(function(){
		var companyNumber=$("#companyNumber").val();//快递公司编号
		var expressNumber=$("#expressNumber").val();//运单号
		if(companyNumber==null || companyNumber==''){
			fdp.msg("请输入快递公司编号");
			return false;
		}
		if(expressNumber==null || expressNumber==''){
			fdp.msg("请输入运单号");
			return false;
		}
		$.ajax({
			url:ctxa+"/sys/sysExpressServer/expressTest.do",
			type:'POST',
			data:{"companyNumber":companyNumber,"expressNumber":expressNumber},
			dataType:'json',
			success:function(data){
				if(data.status == 1){
					fdp.msg("测试快递发送成功");
				}
				if(data.status == 0){
					fdp.msg("测试快递发送失败");
				}
			}
		});
	});
});