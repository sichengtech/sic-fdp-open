<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/views/admin/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>文章列表</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<meta name="decorator" content="admin"/>
<!-- 业务js -->
<script type="text/javascript" src="${ctx}/views/admin/cms/siteArticleList.js"></script>
<style type="text/css">
.clearArticleChannel{
    position: absolute;
    z-index: 3;
    border-left: 1px solid #ccc;
    width: 35px;
    height: 30px;
    margin-top: 0px;
    padding-left: 11px;
    padding-top: 5px;
    font-size: 16px;
    cursor:pointer;
    right: 56px;}
</style>
</head>
<body>
	<!-- panel start -->
	<section class="panel">
		<header class="panel-heading custom-tab tab-right ">
			<h4 class="title">文章列表</h4>
			<%@ include file="../include/functionBtn.jsp"%>
			<ul class="nav nav-tabs pull-right">
				<li class="active"><a href="javaScript:;"> <i class="fa fa-user"></i> 文章列表</a></li>
				<li class=""><a href="<c:url value='${fns:getAdminPath()}/cms/article/form.do?id=${article.id}&category.id=${article.category.id}'><c:param name='category.name' value='${article.category.name}'/></c:url>"><i class="fa fa-user"></i> 文章<shiro:hasPermission name="cms:article:edit">${not empty article.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cms:article:view">查看</shiro:lacksPermission></a></li>
			</ul>
		</header>
		<sys:message content="${message}"/>
		<div class="panel-body">
			<!-- 提示 start -->
			<div class="alert alert-info alert-block fade in ${cookie.fdp_operationTips.value=='0'?'point_hidden':''}" id="point">
				<h5>操作提示</h5>
				<ul>
					<li>文章列表默认显示系统全部文章，按添加时间顺序排列。可通过高级搜索通过分类项索引某分类内的文章并进行编辑。</li>
					<li>文章排序数值越小，排列位置越靠前，排序相同的文章，最新发布排在前面。</li>
				</ul>
			</div>
			<!-- 提示 end --> 
			<!-- 按钮开始 -->
			<div class="row" style="margin-bottom:10px;">
				<div class="col-sm-2">
					<div class="btn-group">
						<!--刷新按钮 -->
						<button type="button" class="btn btn-default btn-sm tooltips" title="刷新" onclick="location.reload();"><i class="fa fa-refresh"></i></button>
						<!-- 新增记录按钮 -->
						<shiro:hasPermission name="cms:article:edit">
						<a class="btn btn-default btn-sm tooltips" title="添加" href="${ctxa}/cms/article/form.do?id=${article.id}&category.id=${article.category.id}"><i class="fa fa-plus"></i></a>
						</shiro:hasPermission>
					</div>
				</div>
				<div class="col-sm-1"></div>
				<form action="${ctxa}/cms/article.do" method="get">
					<div class="col-sm-1" style="margin-top: 3px">栏目：</div>
					<div class="col-sm-3">
						<div class="clearArticleChannel">
							<i class="fa fa-times-circle"></i>
						</div>
						<sys:treeselect id="category" name="category.id" value="${article.category.id}" labelName="category.name" labelValue="${article.category.name}"
						title="栏目" url="/cms/category/treeData.do" module="article" notAllowSelectRoot="false" cssClass="input-sm"/>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control tooltips input-sm" id="inputEmail1" placeholder="标题" value="${article.title}" name="title" maxlength="20">
					</div>
					 <div class="col-sm-2">
						 <select name="delFlag" class="form-control m-bot15 input-sm">
							<c:forEach items="${fns:getDictList('cms_del_flag')}" var="item">
								<option value="${item.value}" ${item.value == article.delFlag?"selected":""}>${item.label}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-sm-1" style="margin-left: -8px">
						<button type="submit" class="btn btn-info btn-sm" ><i class="fa fa-search"></i>搜索</button>
					</div>
				</form>
			</div>
			<!-- 按钮结束--> 
			<!-- Table开始 -->
			<div class="table-responsive">
				<table class="table table-hover table-condensed table-bordered">
					<thead> 
						<tr>
							<th>栏目</th> 
							<th>标题</th> 
							<th>排序</th> 
							<th>发布者</th> 
							<th>状态</th> 
							<th>更新时间</th> 
							<th>操作</th> 
						</tr>
					</thead> 
					<tbody>
						<c:forEach items="${page.list}" var="article">
							<tr>
								<td>${article.category.name}</td> 
								<td><a href="${ctxa}/cms/article/form.do?id=${article.id}" title="${article.title}">${fns:abbr(article.title,40)}</a></td> 
								<td>${article.weight}</td>
								<td>${article.user.name}</td>
								<td>${fns:getDictLabel(article.delFlag, 'cms_del_flag', '无')}</td>
								<td><fmt:formatDate value="${article.updateDate}" type="both"/></td>
								<td>
									<shiro:hasPermission name="cms:article:edit">
									<a class="btn btn-info btn-sm" href="${ctxa}/cms/article/form.do?id=${article.id}">
										<i class="fa fa-edit"></i>编辑
									</a>
									</shiro:hasPermission>
									<shiro:hasPermission name="cms:article:drop">
										<button type="button" class="btn btn-danger btn-sm deleteSure" href="${ctxa}/cms/article/delete.do?id=${article.id}${article.delFlag ne 0?'&isRe=true':''}&categoryId=${article.category.id}"><i class="fa fa-trash-o"></i> 删除</button>
									</shiro:hasPermission>
								</td> 
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- table结束 -->
			<!-- 分页信息开始 -->
			<%@ include file="../include/page.jsp"%>
			<!-- 分页信息结束 -->
		</div>
	</section>
	<!-- panel end -->
</body>
</html>