<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/views/admin/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>系统变量管理</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<meta name="decorator" content="admin"/>
<!-- 业务js -->
<script type="text/javascript" src="${ctx}/views/admin/sys/sysVariableForm.js"></script>
</head>
<body>
	<!-- panel开始 -->
	<section class="panel">
		<header class="panel-heading custom-tab tab-right ">
			<c:set var="isEdit" value ="${not empty sysVariable.varId?true:false}"></c:set >
			<h4 class="title">${isEdit?'编辑':'添加'}系统变量</h4>
			<%@ include file="../include/functionBtn.jsp"%>
			<ul class="nav nav-tabs pull-right">
				<li class=""><a href="${ctxa}/sys/sysVariable/list.do"> <i class="fa fa-user"></i> 系统变量列表</a></li>
				<shiro:hasPermission name="${isEdit?'sys:sysVariable:edit':'sys:sysVariable:save'}">
				<li class="active"><a href="javaScript:;" > <i class="fa fa-user"></i> 系统变量${isEdit?'编辑':'添加'}</a></li>
				</shiro:hasPermission>
			</ul>
		</header>
		<!-- panel-body开始 -->
		<div class="panel-body">
			<!-- 提示开始 -->
			<div class="alert alert-info alert-block fade in ${cookie.fdp_operationTips.value=='0'?'point_hidden':''}" id="point">
				<h5>操作提示</h5>
				<ul>
					<li>变量值有两个，添加时只添加一个变量值</li>
					<li>99%的情况下使用变量值1,变量值1能够存储1024个英文或汉字</li>
					<li>少数情况下使用变量值2,变量值2可存储不限量英文或汉字</li>
				</ul>
			</div>
			<!-- 提示结束 -->
			<sys:message content="${message}"/>
			<div class="tab-content" style="margin-top:15px;">
				<div class="tab-pane active" id="home-3">
					<form class="cmxform form-horizontal adminex-form" id="inputForm" action="${ctxa}/sys/sysVariable/${isEdit?'edit2':'save2'}.do" method="post">
						<input type="hidden" name="varId" value="${sysVariable.varId}">
						<input id="oldName" name="oldName" value="${sysVariable.name}" type="hidden"/>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"><font color="red">*</font> 变量名&nbsp;:</label>
							<div class="col-sm-5">
								<input type="text" name="name"  maxlength="64" class="form-control input-sm" value="${sysVariable.name}"/>

							</div>
							<div class="col-sm-5">
								<p class="help-block">必填项，请填写变量名<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> 变量值1&nbsp;:</label>
							<div class="col-sm-5">
								<input type="text" name="value"  maxlength="1024" class="form-control input-sm" value="${sysVariable.value}"/>

							</div>
							<div class="col-sm-5">
								<p class="help-block">请填写变量值1<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> 变量值2&nbsp;:</label>
							<div class="col-sm-5">
								<textarea  name="valueClob" class="form-control input-sm" rows="5" data-parsley-id="8">${sysVariable.valueClob}</textarea>
							</div>
							<div class="col-sm-5">
								<p class="help-block">请填写变量值2<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> 描述&nbsp;:</label>
							<div class="col-sm-5">
								<textarea  name="bewrite" class="form-control input-sm" maxlength="255" rows="5" data-parsley-id="8">${sysVariable.bewrite}</textarea>
							</div>
							<div class="col-sm-5">
								<p class="help-block">请填写描述<p>
							</div>
						</div>
					
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"></label>
							<div class="col-sm-5">
								<button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);">
									<i class="fa fa-times"></i> 返 回
								</button>
								<shiro:hasPermission name="${isEdit?'sys:sysVariable:edit':'sys:sysVariable:save'}">
								<button type="submit" class="btn btn-info">
									<i class="fa fa-check"></i> 保 存
								</button>
								</shiro:hasPermission>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- panel-body结束 -->
	</section>
	<!-- panel结束 -->
</body>
</html>