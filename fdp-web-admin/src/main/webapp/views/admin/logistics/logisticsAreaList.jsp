<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/views/admin/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>地区管理</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<meta name="decorator" content="admin"/>

<%@include file="/views/admin/include/treetable.jsp" %>
<!-- 业务js -->
<script type="text/javascript" src="${ctx}/views/admin/logistics/logisticsAreaList.js"></script>
</head>
<body>

	<!-- panel start -->
	<section class="panel">
		<header class="panel-heading custom-tab tab-right ">
			<h4 class="title">地区管理</h4>
			<%@ include file="../include/functionBtn.jsp"%>
			<ul class="nav nav-tabs pull-right">
				<li class="active"><a href="javascript:;"> <i class="fa fa-home"></i> 地区列表</a></li>
				<shiro:hasPermission name="sys:area:edit">
				<li class=""><a href="${ctxa}/sys/area/form.do"> <i class="fa fa-user"></i> 添加地区</a></li>
				</shiro:hasPermission>
			</ul>
		</header>
			<div class="panel-body">
				<!-- 提示 start -->
				<div class="alert alert-info alert-block fade in ${cookie.fdp_operationTips.value=='0'?'point_hidden':''}" id="point">
					<h5>操作提示</h5>
					<ul>
						<li>网站所涉及的所有地区内容均来源于此设置项，强烈建议对该项慎重进行操作修改。</li>
						<li>所属大区为默认的全国性的几个大区域，只有省级地区才需要填写大区域，目前全国几大区域有:华北、东北、华东、华南、西南、西北、港澳台、海外。</li>
						<li>所在层级为该地区的所在的层级深度，如北京 › 北京市 › 朝阳区，其中北京层级为1，北京市层级为2，朝阳区层级为3。</li>
					</ul>
				</div>
				<!-- 提示 end --> 
				<!-- 按钮开始 -->
				<div class="row" style="margin-bottom:10px;">
					<div class="col-sm-5">
						<div class="btn-group">
							<!--刷新按钮 -->
							<button type="button" class="btn btn-default btn-sm tooltips" title="刷新" onclick="location.reload();"><i class="fa fa-refresh"></i></button>
							<!-- 新增记录按钮 -->
							<a class="btn btn-default btn-sm tooltips" title="添加地区" href="${ctxa}/sys/area/form.do"><i class="fa fa-plus"></i></a>
							<!-- 返回 -->
							<a class="btn btn-default btn-sm tooltips" title="" href="javaScript:void(0);" onclick="javascript:history.go(-1);" data-original-title="返回"><i class="fa fa-mail-reply"></i></a>
						</div>
					</div>
					<div class="col-sm-2"></div>
					<form action="${ctxa}/sys/area/list.do" method="get" >
						<div class="col-sm-2">
							<input type="text" class="form-control input-sm" maxLength="64" placeholder="地区名称" name="name" value="${name}">
						</div>
						<div class="col-sm-2">
							<select class="form-control" name="largeArea" style="height: 30px;font-size: 12px;">
								<option value="">所属大区</option>
								<c:forEach items="${fns:getDictList('large_area')}" var="la">
									<option value="${la.value}" ${area.largeArea eq la.value?"selected":""}>${la.label}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-1" style="text-align: right;">
							<button type="submit" class="btn btn-info btn-sm" ><i class="fa fa-search"></i> 搜索</button>
						</div>
					</form>
				</div>
				<!-- 按钮结束--> 
				<!-- Table开始 -->
				<table id="treeTable" class="table table-hover table-condensed table-bordered">
					<thead> 
						<tr>
							<th>地区ID</th>
							<th>地区名称</th>
							<th>上级地区</th>
							<th>所属大区</th>
							<th>操作管理</th>
						</tr>
					</thead> 
					<tbody>
						<c:forEach var="area" items="${list}">
							<tr>
								<td>${area.id}</td>
								<td>${area.name}</td>
								<td>${area.areaParentNames}</td>
								<td>${fns:getDictLabel(area.largeArea, 'large_area', '')}</td>
								<td>
									<c:if test="${area.level!=4}">
										<a type="button" class="btn btn-default btn-sm" href="${ctxa}/sys/area/list.do?parent.id=${area.id}">
											<i class="fa fa-view"></i> 查看下级
										</a>
									</c:if>
									<shiro:hasPermission name="sys:area:edit">
									<a type="button" class="btn btn-info btn-sm" href="${ctxa}/sys/area/form.do?id=${area.id}">
										<i class="fa fa-edit"></i> 编辑
									</a>
									<c:if test="${area.level!=4}">
										<a type="button" class="btn btn-info btn-sm" href="${ctxa}/sys/area/form.do?parent.id=${area.id}">
											<i class="fa fa-level-down"></i> 添加下级地区
										</a>
									</c:if>
									</shiro:hasPermission>
									<shiro:hasPermission name="sys:area:drop">
									<button type="button" href="${ctxa}/sys/area/delete.do?id=${area.id}" class="btn btn-danger btn-sm deleteSure">
										<i class="fa fa-trash-o"></i> 删除
									</button>
									</shiro:hasPermission>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- table结束 -->
			</div>
	</section>
	<!-- panel end -->
</body>
</html>