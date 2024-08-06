<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/views/admin/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>缓存管理</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<meta name="decorator" content="admin"/>
<!-- 业务js -->
<script type="text/javascript" src="${ctx}/views/admin/sys/sysCacheIndex.js"></script>
<style type="text/css">
    .block{width: 30%;height: 50px;margin: 10px;font-size: 15px;}
</style>
</head>
<body>
	<!-- panel start -->
	<section class="panel">
		<header class="panel-heading custom-tab tab-right ">
			<h4 class="title">缓存管理</h4>
			<%@ include file="../include/functionBtn.jsp"%>
		 	<ul class="nav nav-tabs pull-right">
				<li class="active"><a href="javaScript:;" > <i class="fa fa-user"></i> 缓存管理</a></li>
			</ul>
		</header>
		<div class="panel-body">
			<!-- 提示 start -->
			<div class="alert alert-info alert-block fade in ${cookie.fdp_operationTips.value=='0'?'point_hidden':''}" id="point">
				<h5>操作提示</h5>
				<ul>
					<li>缓存操作管理</li>
				</ul>
			</div>
			<sys:message content="${message}"/>
			<div class="tab-content" style="margin-top:15px;">
				<div class="tab-pane active" id="home-3">
					<table class="table table-hover table-condensed table-bordered">
						 <thead> 
							<tr> 
								<th width="50%">清理</th> 
								<th width="50%">提示</th> 
							</tr>
						</thead> 
						<tbody>
							<tr>
								<td width="50%">
									<button type="button" class="btn btn-info block delPage" href="${ctxa}/sys/sysCache/delPageCache.do"> 清除页面缓存</button>
								</td>
								<td width="50%">
									首页、分类主页已启用缓存；当数据变化时，需手动清理缓存，才可生效。
								</td>
							</tr>
							<tr>
								<td width="50%">
									<button type="button" class="btn btn-danger block delSQL" href="${ctxa}/sys/sysCache/delSQLCache.do"> 清除SQL数据缓存</button>
								</td>
								<td width="50%">
									一般情况下不需要清理此缓存。
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</section>
	<!-- panel end -->
</body>
</html>