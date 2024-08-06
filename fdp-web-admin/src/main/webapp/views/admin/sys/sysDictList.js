 $(document).ready(function(){
	 $(".deleteSure").click(function(){
		var href=$(this).attr("href");
		fdp.confirm('确定要删除么？',href); 
	});

 });