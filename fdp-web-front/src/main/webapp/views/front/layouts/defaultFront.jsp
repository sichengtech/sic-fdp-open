<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/views/front/cms/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<html style="overflow-x: auto; overflow-y: auto;">
<head>
<title><sitemesh:title /></title>
<%@include file="/views/member/include/head.jsp"%>
<sitemesh:head />
</head>
<body>
<%@ include file="/views/front/include/header_all.jsp"%>
<sitemesh:body />
<%@ include file="/views/front/include/footer.jsp"%>
</body>
</html>