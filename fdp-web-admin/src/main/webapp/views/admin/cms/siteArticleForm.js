	 $(document).ready(function(){
		 if($("#link").val()){
			 $('#linkBody').show();
			 $('#url').attr("checked", true);
		 }
		//如果js验证开关是开的就进行js验证
		if(jsValidate){
			$("#inputForm").validate({
				rules: {
					"title":{required:true,maxlength:100,},
				},
				messages: {
					"title":{required:"标题不能为空",maxlength:"标题不能超过100位",},
				},
				submitHandler: function(form){
					if ($("#categoryId").val()==""){
						fdp.msg("请选择归属栏目");
						return false;
					}
					//验证文章内容
					 var content = UE.getEditor('container').getContent();
			         if(content==""||content==null){
			        	 fdp.msg("<i class='fa fa-meh-o' style='font-size:24px;color:red'></i> 文章内容不能为空",2000);
			             return false;
			         }
					layer.msg('正在提交，请稍等...', {icon: 16,shade: 0.30,time: 0});
					form.submit();
				},
			});
		}
	 });