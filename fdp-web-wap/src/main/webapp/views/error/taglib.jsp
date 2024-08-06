<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxa" value="${true?'/admin':''}${fns:getAdminPath()}"/>
<c:set var="ctxs" value="${false?'/seller':''}${fns:getSellerPath()}"/>
<c:set var="ctxm" value="${false?'/seller':''}${fns:getMemberPath()}"/>
<c:set var="ctxf" value="${false?'/seller':''}${fns:getFrontPath()}"/>
<c:set var="ctxsso" value="${false?'/seller':''}${fns:getSsoPath()}"/>
<c:set var="ctxu" value="/upload"/>
<c:set var="ctxStatic" value="/static/static"/>
<c:set var="ctxfs" value="${ctxu}${fns:getConfig('filestorage.dir')}"/>
<c:set var="ctxw" value="/wap${fns:getWapPath()}"/>

