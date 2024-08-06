<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/views/admin/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>Shop管理后台</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<meta name="decorator" content="admin"/>
<style>
	/*body, h1, h2, h3, h4, h5 {*/
	/*	overflow: inherit;*/
	/*}*/
	table, td, th {
		padding: 3px
	}
</style>
</head>
<body>

	<!--body wrapper start-->
	<div class="wrapper">
		<!--breadcrumbs start -->
		<ul class="breadcrumb panel">
			<li><a href="#"><i class="fa fa-home"></i> 控制台</a></li>
			<li><a href="#">组件演示</a></li>
			<li class="active">提示框演示</li>
		</ul>
		<!--breadcrumbs end -->
		<br/>
		<a href="javascript:fdp.alert('你好!');" class="btn btn-info btn-sm"><i class="fa fa-edit"></i> alert提示框</a>
		<button type="button" class="btn btn-danger btn-sm deleteSure" href="${ctxa}/demo/demo2.do?id=-1"><i class="fa fa-trash-o"></i> confirm提示框</button>
	</div>

	<h3>JSTL标签使用演示</h3>
	<table border="1" style="padding: 3px">
		<tr>
			<th>主题</th>
			<th>代码</th>
			<th>输出结果</th>
		</tr>
		<tr>
			<td>fns:getConfig()函数测试</td>
			<td>\${fns:getConfig('productName')}</td>
			<td>${fns:getConfig('productName')}</td>
		</tr>
		<tr>
			<td>fns:getConfig()函数测试</td>
			<td>\${fns:getConfig('version')}</td>
			<td>${fns:getConfig('version')}</td>
		</tr>
		<tr>
			<td>c:if标签测试</td>
			<td>&lt;c:if test=&quot;true&quot; &gt; c标签body &lt;/c:if&gt;</td>
			<td><c:if test="true" > c标签body </c:if></td>
		</tr>
		<tr>
			<td>sys:message宏模板测试</td>
			<td>&lt;sys:message content=&quot;sys:message宏模板--弹出的提示信息&quot;/&gt;</td>
			<td><sys:message content="sys:message宏模板--弹出的提示信息"/>（会弹出提示信息）</td>
		</tr>
		<tr>
			<td>shiro权限标签测试</td>
			<td>
				&lt;shiro:hasPermission name=&quot;cms:category:edit&quot;&gt;<br/>
				有cms:category:edit权<br/>
				&lt;/shiro:hasPermission&gt;<br/>
				&lt;shiro:lacksPermission name=&quot;cms:category:edit&quot;&gt;<br/>
				没有cms:category:edit权限<br/>
				&lt;/shiro:lacksPermission&gt;<br/>
			</td>
			<td>
				<shiro:hasPermission name="cms:category:edit">
					有cms:category:edit权
				</shiro:hasPermission>
				<shiro:lacksPermission name="cms:category:edit">
					没有cms:category:edit权限
				</shiro:lacksPermission>
			</td>
		</tr>
	</table>

	<!--body wrapper end-->
	<script type="text/javascript">
	$(function(){
		//删除提示
		$(".deleteSure").click(function(e){
			var href=$(this).attr("href");
			fdp.confirm('确定要删除么？',href);
		});
	});
	</script>
</body>
</html>