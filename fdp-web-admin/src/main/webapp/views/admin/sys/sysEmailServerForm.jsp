<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/views/admin/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>邮件通道管理</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<meta name="decorator" content="admin"/>
<%@ include file="../include/head_bootstrap-switch.jsp"%>
<!-- 业务js -->
<script type="text/javascript" src="${ctx}/views/admin/sys/sysEmailServerForm.js"></script>
<style>
	.test-email{display:none}
</style>
</head>
<body>
	<!-- panel开始 -->
	<section class="panel">
		<header class="panel-heading custom-tab tab-right ">
			<c:set var="isEdit" value ="${not empty sysEmailServer.id?true:false}"></c:set >
			<h4 class="title">${isEdit?'编辑':'添加'}邮件通道</h4>
			<%@ include file="../include/functionBtn.jsp"%>
			<ul class="nav nav-tabs pull-right">
				<shiro:hasPermission name="sys:sysEmailServer:edit">
				<li class="active"><a href="javaScript:;" > <i class="fa fa-user"></i> 邮件通道${isEdit?'编辑':'添加'}</a></li>
				</shiro:hasPermission>
			</ul>
		</header>
		<!-- panel-body开始 -->
		<div class="panel-body">
			<!-- 提示开始 -->
			<div class="alert alert-info alert-block fade in ${cookie.fdp_operationTips.value=='0'?'point_hidden':''}" id="point">
				<h5>操作提示</h5>
				<ul>
					<li>邮件通道管理</li>
					<li>配置用于发送邮件的SMTP服务器相关信息。</li>
					<li>注册激活邮件、通过邮箱找回密码、各种通知类邮件的发送都需要使用此配置。若未正确配置以上业务无法发出邮件。</li>
				</ul>
			</div>
			<!-- 提示结束 -->
			<sys:message content="${message}"/>
			<div class="tab-content" style="margin-top:15px;">
				<div class="tab-pane active" id="home-3">
					<form class="cmxform form-horizontal adminex-form" id="inputForm" action="${ctxa}/sys/sysEmailServer/${isEdit?'edit2':'save2'}.do" method="post">
						<input type="hidden" name="id" value="${sysEmailServer.id}">
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"><font color="red">*</font> 开关&nbsp;:</label>
							<div class="col-sm-5">
								<input type="checkbox" name="status" value="1" ${"1"==sysEmailServer.status?"checked":""} 
								data-size="small" style="display: none" data-on-text="启用" data-off-text="停用"/>
							</div>
							<div class="col-sm-5">
								<p class="help-block">必填项，关闭后无法发出邮件<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> 发件人展示名称或网站名称&nbsp;:</label>
							<div class="col-sm-5">
								<input type="text" name="sender"  maxlength="64" class="form-control input-sm" value="${sysEmailServer.sender}"/>

							</div>
							<div class="col-sm-5">
								<p class="help-block">示例：admin@oneshop.com，以此人的身份向外发出邮件。一般应与用户名相同<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> SMTP服务器地址&nbsp;:</label>
							<div class="col-sm-5">
								<input type="text" name="smtp"  maxlength="64" class="form-control input-sm" value="${sysEmailServer.smtp}"/>

							</div>
							<div class="col-sm-5">
								<p class="help-block">示例：smtp.oneshop.com<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> SMTP服务器端口&nbsp;:</label>
							<div class="col-sm-5">
								<input type="text" name="port"  maxlength="64" class="form-control input-sm" value="${sysEmailServer.port}"/>

							</div>
							<div class="col-sm-5">
								<p class="help-block">示例：25<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> SMTP用户名&nbsp;:</label>
							<div class="col-sm-5">
								<input type="text" name="username"  maxlength="64" class="form-control input-sm" value="${sysEmailServer.username}"/>

							</div>
							<div class="col-sm-5">
								<p class="help-block">示例：admin@oneshop.com<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> SMTP密码&nbsp;:</label>
							<div class="col-sm-5">
								<input type="text" name="pwd"  maxlength="64" class="form-control input-sm" value="${sysEmailServer.pwd}"/>

							</div>
							<div class="col-sm-5">
								<p class="help-block">如果没有密码，请留空<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> 安全连接&nbsp;:</label>
							<div class="col-sm-5">
								<%-- <input type="text" name="safe"  maxlength="64" class="form-control input-sm" value="${sysEmailServer.safe}"/> --%>
								<input type="checkbox" name="safe" value="1" ${"1"==sysEmailServer.safe?"checked":""} 
									data-size="small" style="display: none" data-on-text="是" data-off-text="否"/>
							</div>
							<div class="col-sm-5">
								<p class="help-block">是否启用SSL安全连接<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> 邮件测试&nbsp;:</label>
							<div class="col-sm-5">
								<a class="btn btn-warning btn-sm test-email-btn" href="javascript:void(0);">
								<i class="fa fa-stethoscope"></i> 测试邮件
								</a> 
							</div>
							<div class="col-sm-3 test-email">
								<input id="email" class="form-control input-sm" type="text" placeholder="请输入邮箱地址"> 
							</div>
							<div class="col-sm-2 test-email">
								<a id="send" class="btn btn-warning btn-sm">
									<i class="fa fa-stethoscope"></i> 发一封测试邮件
								</a>
							</div>
							<div class="col-sm-5">
								<p class="help-block">使用上面的SMTP信息发出一封测试邮件<p>
							</div>
						</div>
					
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"></label>
							<div class="col-sm-5">
								<button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);">
									<i class="fa fa-times"></i> 返 回
								</button>
								<shiro:hasPermission name="sys:sysEmailServer:edit">
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