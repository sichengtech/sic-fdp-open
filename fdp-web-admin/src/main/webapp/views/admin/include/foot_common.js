/**
 * 工具条--操作提示的收起与展开
 */
$("#toolbar_operate_tips").click(function(){
	var key="fdp_operationTips";//存储在cookei中的key
	if($("#point").is(":hidden")){
		$('#point').show('normal');
		fdp.cookie(key, '1', {path:'/',expires:7}); //1表示显示，7表示有效期7天
	} else {
		$('#point').hide('normal');
		fdp.cookie(key, '0', {path:'/',expires:7}); //0表示隐藏，7表示有效期7天
	}
});

/**
 * 工具条--全屏
 */
$("#toolbar_full_screen").click(function(){
	jQuery('.toggle-btn').trigger("click");
});

/**
 * 列表页的表格，点击加号，展开详情
 */
$(".detail").click(function(){
	var e=$(this).parent().next();
	if($(e).is(":hidden")){
		$(e).show();
		$(this).find("i").attr("class","fa fa-minus");
	}else{
		$(e).hide();
		$(this).find("i").attr("class","fa fa-plus");
	}
});

/**
 * jQuery.validator添加正则验证规则
 */
(function(){
	jQuery.validator.addMethod("regex", //addMethod第1个参数:方法名称
			function(value, element, params) { //addMethod第2个参数:验证方法，参数（被验证元素的值，被验证元素，参数） 
			var exp = new RegExp(params); //实例化正则对象，参数为传入的正则表达式 
			return this.optional(element) || exp.test(value); //测试是否匹配 
			}, 
			"格式错误"); //addMethod第3个参数:默认错误提示信息
})();