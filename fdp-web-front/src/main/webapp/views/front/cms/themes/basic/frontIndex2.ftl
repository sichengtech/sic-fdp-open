<#assign c=JspTaglibs["/WEB-INF/tlds/c.tld"] />
<#assign shiro=JspTaglibs["/WEB-INF/tlds/shiros.tld"] />
<#assign fns=JspTaglibs["/WEB-INF/tlds/fns.tld"] />
<#assign fnc=JspTaglibs["/WEB-INF/tlds/fnc.tld"] />


<!DOCTYPE html>
<html>
<head>
	<title>首页</title>
	<meta name="decorator" content="cms_default_${site.theme}"/>
	<meta name="description" content="fdp ${site.description}" />
	<meta name="keywords" content="fdp ${site.keywords}" />
</head>
<body>
首页（Freemarker） 

<@c.set var="temp">true</@c.set>    ${temp!}
<@c.if test=true>
本体内容2
</@c.if>



</body>
</html>