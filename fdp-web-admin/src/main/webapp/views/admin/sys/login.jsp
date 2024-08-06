<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/views/admin/include/taglib.jsp"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>${fns:getConfig('productName')}登录</title>
<!-- jquery -->
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.10.2.min.js"></script>
<!-- layer -->
<script type="text/javascript" src="${ctxStatic}/layer/layer.js"></script>
<!-- 业务js -->
<script type="text/javascript" src="${ctx}/views/admin/sys/login.js"></script>

<link rel="stylesheet" type="text/css" href="${ctxStatic}/sicheng-admin/css/shopstyle.css"/>
<style type="text/css">
.form-signin-heading {
	font-family: Helvetica, Georgia, Arial, sans-serif, 黑体;
	font-size: 36px;
	margin-bottom: 20px;
	color: #0663a2;
}
</style>
</head>
<!--body wrapper start-->
<body class="admin-login">
<div class="position-content">
	<div class="box">
		<div class="logo">
			<h1 class="form-signin-heading">${fns:getConfig('productName')}</h1>
		</div>
		<p class="message">${message}</p>
		<form id="inputForm" class="form-signin" action="${ctxa}/login.do" method="post">

			<input type="text" id="username" name="username" class="input-username" placeholder="请输入管理员帐号" value="${username}">
			<label class="control-label label-name">
				<i class="icon"></i>
			</label>

			<label class="control-label label-password">
				<i class="icon"></i>
			</label>
			<input type="password" class="input-password" placeholder="请输入密码" id="password" name="password">

			<!--验证码开始-->
			<c:if test="${isValidateCodeLogin}">
				<div class="validateCode">
					<label class="control-label label-code"><i class="icon"></i>
					<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
				</div>
			</c:if>
			<!--验证码结束-->
			<label class="control-label"><input type="submit" class="input-ok" value="登 录"></label>
			<div class="textl"><input type="checkbox" class="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''} > 记住我（公共场所慎用）</div>
			<div class="clear"></div>
			<div class="cp">
				${fns:getConfig('productName')}&nbsp;${fns:getConfig('version')} &copy; 2016-<fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy"/>
				<a style="text-decoration:none;color:inherit;" target="_blank" href="http://www.sicheng.net"> sicheng.net</a>
			</div>
		</form>
	</div>
</div>
<img src="${ctxStatic}/sicheng-admin/images/login-bg.png" width="100%" height="100%" alt="" class="admin-login-bgimg"/><!--body wrapper start-->
<!--body wrapper end-->
</body>
</html>