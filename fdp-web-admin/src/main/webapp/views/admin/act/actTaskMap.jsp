<%@page import="java.io.InputStream"%>
<%@page import="org.activiti.engine.impl.*"%>
<%@page import="org.activiti.engine.impl.pvm.*"%>
<%@page import="org.activiti.engine.impl.pvm.process.*"%>
<%@page import="org.activiti.engine.repository.*"%>
<%@page import="org.activiti.engine.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/views/admin/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>流程跟踪</title>
<style type="text/css">
	.procPic{position:absolute;left:0;top:0;}
	.actImpl{position:absolute;border:2px solid red;-moz-border-radius:12px;-webkit-border-radius:12px;-khtml-border-radius:12px;border-radius:12px;}
</style>
</head>
<body>
	<div>
		<img src="${ctxa}/act/task/processPic.do?procDefId=${procDefId}" class="procPic" />
		<c:forEach items="${actImpls}" var="a">
			<div class="actImpl" style="left:${a.x-2}px;top:${a.y-2}px;width:${a.width}px;height:${a.height}px;"></div>
		</c:forEach>
	</div>
</body>
</html>
