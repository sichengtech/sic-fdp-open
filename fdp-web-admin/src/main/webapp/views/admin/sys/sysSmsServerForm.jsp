<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/views/admin/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>短信通道管理</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<meta name="decorator" content="admin"/>
<%@ include file="../include/head_bootstrap-switch.jsp"%>
<!-- 业务js -->
<script type="text/javascript" src="${ctx}/views/admin/sys/sysSmsServerForm.js"></script>
<style>
	.test-email{display:none}
</style>
</head>
<body>
	<!-- panel开始 -->
	<section class="panel">
		<header class="panel-heading custom-tab tab-right ">
			<c:set var="isEdit" value ="${not empty sysSmsServer.id?true:false}"></c:set >
			<h4 class="title">${isEdit?'编辑':'添加'}短信通道</h4>
			<%@ include file="../include/functionBtn.jsp"%>
			<ul class="nav nav-tabs pull-right">
				<li class=""><a href="${ctxa}/sys/sysSmsServer/list.do"> <i class="fa fa-user"></i> 短信通道列表</a></li>
				<shiro:hasPermission name="sys:sysSmsServer:edit">
				<li class="active"><a href="javaScript:;" > <i class="fa fa-user"></i> 短信通道${isEdit?'编辑':'添加'}</a></li>
				</shiro:hasPermission>
			</ul>
		</header>
		<!-- panel-body开始 -->
		<div class="panel-body">
			<!-- 提示开始 -->
			<div class="alert alert-info alert-block fade in ${cookie.fdp_operationTips.value=='0'?'point_hidden':''}" id="point">
				<h5>操作提示</h5>
				<ul>
					<li>短信通道管理</li>
					<li>配置用于发送短信的短信网关相关信息。</li>
					<li>使用手机号注册、通过手机找回密码、各种通知类短信的发送都需要使用此配置。若未正确配置以上业务无法发出短信。</li>
				</ul>
			</div>
			<!-- 提示结束 -->
			<sys:message content="${message}"/>
			<div class="tab-content" style="margin-top:15px;">
				<div class="tab-pane active" id="home-3">
					<form class="cmxform form-horizontal adminex-form" id="inputForm" action="${ctxa}/sys/sysSmsServer/${isEdit?'edit2':'save2'}.do" method="post">
						<input type="hidden" name="id" value="${sysSmsServer.id}">
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"><font color="red">*</font> 状态&nbsp;:</label>
							<div class="col-sm-5">
								<input type="checkbox" name="status" value="1" ${"1"==sysSmsServer.status?"checked":""} 
								data-size="small" style="display: none" data-on-text="启用" data-off-text="停用"/>
							</div>
							<div class="col-sm-5">
								<p class="help-block">必填项，请填写状态<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> 短信网关URL&nbsp;:</label>
							<div class="col-sm-5">
								<input type="text" name="url"  maxlength="255" class="form-control input-sm" value="${sysSmsServer.url}"/>

							</div>
							<div class="col-sm-5">
								<p class="help-block">请输入你的短信网关地址<p>
							</div>
						</div>
						<%-- <div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> 用户名&nbsp;:</label>
							<div class="col-sm-5">
								<input type="text" name="username"  maxlength="64" class="form-control input-sm" value="${sysSmsServer.username}"/>

							</div>
							<div class="col-sm-5">
								<p class="help-block">请输入短信网关的用户名<p>
							</div>
						</div> --%>
						<%-- <div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> 密码&nbsp;:</label>
							<div class="col-sm-5">
								<input type="text" name="pwd"  maxlength="64" class="form-control input-sm" value="${sysSmsServer.pwd}"/>

							</div>
							<div class="col-sm-5">
								<p class="help-block">请输入短信网关的密码，如果没有密码，请留空<p>
							</div>
						</div> --%>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> APP KEY&nbsp;:</label>
							<div class="col-sm-5">
								<input type="text" name="accessKey"  maxlength="64" class="form-control input-sm" value="${sysSmsServer.accessKey}"/>

							</div>
							<div class="col-sm-5">
								<p class="help-block">请填写APP KEY<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> APP SECRET&nbsp;:</label>
							<div class="col-sm-5">
								<input type="text" name="appId"  maxlength="64" class="form-control input-sm" value="${sysSmsServer.appId}"/>

							</div>
							<div class="col-sm-5">
								<p class="help-block">请填写APP SECRET<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> 签名名称&nbsp;:</label>
							<div class="col-sm-5">
								<input type="text" name="clientId"  maxlength="64" class="form-control input-sm" value="${sysSmsServer.clientId}"/>

							</div>
							<div class="col-sm-5">
								<p class="help-block">请填写申请的短息网关签名名称<p>
							</div>
						</div>
						<%-- <div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> 令牌&nbsp;:</label>
							<div class="col-sm-5">
								<input type="text" name="token"  maxlength="64" class="form-control input-sm" value="${sysSmsServer.token}"/>

							</div>
							<div class="col-sm-5">
								<p class="help-block">请填写令牌<p>
							</div>
						</div> --%>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> 短信测试&nbsp;:</label>
							<div class="col-sm-5">
								<a class="btn btn-warning btn-sm test-email-btn" href="javascript:void(0);">
								<i class="fa fa-stethoscope"></i> 测试短信
								</a> 
							</div>
							<div class="col-sm-2 test-email">
								<input id="phone" class="form-control input-sm" type="text" placeholder="请输入手机号"> 
							</div>
							<div class="col-sm-2 test-email">
								<input id="text" class="form-control input-sm" type="text" value="这是短信内容"> 
							</div>
							<div class="col-sm-1 test-email">
								<a id="send" class="btn btn-warning btn-sm">
									<i class="fa fa-stethoscope"></i> 发送
								</a>
							</div>
							<div class="col-sm-5">
								<p class="help-block">使用上面的短信网关发出一条测试短信,短信模板参数格式{code:'32658'}<p>
							</div>
						</div>
					
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"></label>
							<div class="col-sm-5">
								<button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);">
									<i class="fa fa-times"></i> 返 回
								</button>
								<shiro:hasPermission name="sys:sysSmsServer:edit">
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