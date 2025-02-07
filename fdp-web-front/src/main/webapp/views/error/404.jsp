<%
response.setStatus(404);
//System.out.println("##########");
// 如果是异步请求或是手机端，则直接返回信息
if (Servlets.isAjaxRequest(request)) {
	out.print("页面不存在.");
}

//输出异常信息页面
else {
%>
<%@page import="com.sicheng.common.web.Servlets"%>
<%@page contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<%@include file="/views/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>404 - 页面不存在</title>
	<style>
		html,body{ margin:0px; padding:50px; height:100%;}
		.conn{font-family: "Microsoft Yahei",Arial, Helvetica, sans-serif; font-size:40px;color:#99a2ae;}
		.conn a{color:#99a2ae; text-decoration:none}
		.conn a:hover{ color:#f75d5b; border-bottom: solid 2px #f75d5b;text-decoration: none!important;}
		.page-header{border-bottom:none!important;}
	</style>
	<%@include file="/views/front/include/head.jsp" %>
</head>
<body>
	<div class="container-fluid">
		<div class="page-header">
			<h1 style='color:#505863; font-family:Impact, Haettenschweiler, "Franklin Gothic Bold", "Arial Black", sans-serif; font-size:130px;'>404 ERROR</h1>
		</div>
		<div class="conn">
			<p style="margin-top: 65px;">对不起，您访问的页面不存在或已经删除。</p>
			<p style="margin-top: 35px;">请点击  <a href="javascript:" onclick="history.go(-1);">[ 这里 ]</a> 返回上一页。</p>
		</div>
		<script>try{top.$.jBox.closeTip();}catch(e){}</script>
	</div>
</body>
</html>
<%
out.print("<!--"+request.getAttribute("javax.servlet.forward.request_uri")+"-->");
} out = pageContext.pushBody();
%>