$(document).ready(function(){
	//如果js验证开关是开的就进行js验证
	var oldMenuNum=$("#oldMenuNum").val();
	if(jsValidate){
		$("#inputForm").validate({
			rules: {
				"name":{required:true,maxlength:100},
				// "menuNum":{remote: ctxa+"/sys/menu/checkMenuNum.do?oldMenuNum=" + encodeURIComponent(oldMenuNum),required:true,maxlength:64},
				"sort":{required: true,maxlength:10,regex:/^(0|[1-9][0-9]*)$/,},
			},
			messages: {
				"name":{required:"菜单名不能为空",maxlength:"菜单名不能超过100位"},
				// "menuNum":{remote: "菜单编号已存在",required:"菜单编号不能为空",maxlength:"菜单编号不能超过64位"},
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
$(function(){
	//图标选择框
	$(".selectIcon").click(function(){
		var orderId=$(this).attr("orderId");
		var myRemoteUrl=ctxa+"/tag/iconselect.do";
		$.post(myRemoteUrl, {}, function(str){
			layer.open({
				type: 1,
				title:'选择图标',
				area: ['868px', '600px'],
				shadeClose: true, //点击遮罩关闭
				btn: ['确定','关闭'],
				content: str //注意，如果str是object，那么需要字符拼接。
			});
		});
	});
});