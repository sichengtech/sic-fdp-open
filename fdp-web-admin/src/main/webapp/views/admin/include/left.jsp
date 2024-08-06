<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/views/admin/include/taglib.jsp"%>
		<div class="left-side sticky-left-side">
			<!--log和图标 start-->
			<div class="logo">
				<c:set var="logoLength" value="${fn:length(fns:getConfig('productName'))}" />
				<c:if test="${logoLength<=4}"><c:set var="fzise" value="28" /></c:if>
				<c:if test="${logoLength>=6}"><c:set var="fzise" value="18" /></c:if>
				<c:if test="${logoLength>=8}"><c:set var="fzise" value="16" /></c:if>
				<c:if test="${logoLength>=10}"><c:set var="fzise" value="12" /></c:if>
				<c:if test="${logoLength>=12}"><c:set var="fzise" value="10" /></c:if>
				<a target="_blank" href="${ctx}/index.do" style="font-size:<c:out value="${fzise}"/>px">${fns:getConfig('productName')}</a>
			</div>
			<!--logo和图标 end-->
			<div class="left-side-inner">
				<!-- 二级菜单 sidebar nav start-->
				<ul id="menu_2" class="nav nav-pills nav-stacked custom-nav">
		 			<c:forEach items="${fns:getChildMenuList(menu1id)}" var="menu2">
	 				<c:set value="0,1,${menu1id}," var="parentIds1"/>
					<c:if test="${menu2.parentIds == parentIds1 && menu2.isShow eq '1'}">
						<li class="menu-list nav-active">
							<a href="javascript:void(0);"><i class="fa fa-th-list"></i><span>${menu2.name}</span><small>${fn:substring(menu2.name,"0","2")}</small></a>
							<!-- 三级菜单 sidebar nav start-->
							<ul class="sub-menu-list">
							<c:forEach items="${fns:getChildMenuList(menu2.id)}" var="menu3">
							<c:set value="${menu2.parentIds}${menu2.id}," var="parentIds2"/>
							<c:if test="${menu3.parentIds == parentIds2 && menu3.isShow eq '1'}">
								<li class="${menu3.id == menu3id?'nav-hover':'' }" style="background:${menu3.id == menu3id?'#2a323f':'' }"><a href="${fn:indexOf(menu3.href, '://') eq -1 ? ctxa : ''}${menu3.href}"><i class="fa fa-user"></i> <span>${menu3.name}</span></a></li>
							</c:if>
							</c:forEach>
							</ul>
							<!-- 三级菜单 sidebar nav end-->
						</li>
					</c:if>
					</c:forEach> 
				</ul>
				<!-- 二级菜单 sidebar nav end-->
			</div>
		</div>
		