<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/views/admin/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>${empty area.id?'添加':'编辑'}地区</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<meta name="decorator" content="admin"/>

<!-- 业务js -->
<script type="text/javascript" src="${ctx}/views/admin/logistics/logisticsAreaForm.js"></script>
<style type="text/css">
	.select_left{padding-left: 0px;}
	 .jbox-title-panel{height: 30px!important;}
	 .jbox-title{line-height:15px!important; }
	 .jbox-close{top:7px!important;}
	 .jbox-content{ overflow: inherit!important;}
	 .jbox-button-panel{height: 35px!important;}
</style>
</head>
<body>
	<!-- panel start -->
	<section class="panel">
		<header class="panel-heading custom-tab tab-right ">
			<h4 class="title">${empty area.id?'添加':'编辑'}地区</h4>
			<%@ include file="../include/functionBtn.jsp"%>
			<ul class="nav nav-tabs pull-right">
				<li class=""><a href="${ctxa}/sys/area.do"> <i class="fa fa-home"></i> 地区列表</a></li>
				<li class="active"><a href="javascript:;"> <i class="fa fa-user"></i> ${empty area.id?'添加':'编辑'}地区</a></li>
			</ul>
		</header>
		<div class="panel-body">

			<!-- 提示 start -->
			<div class="alert alert-info alert-block fade in ${cookie.fdp_operationTips.value=='0'?'point_hidden':''}" id="point">
				<h5>操作提示</h5>
				<ul>
					<li>请认真填写地区名称,地区设定后将直接印象订单,收货地址等重要信息,请慎重操作</li>
					<li>系统将根据所选的上级菜单层级决定新增项的所在级,即选择上级菜单为"北京",则新增项为北京地区的下级区域,依次类推.</li>
					<li>默认只有升级地区才需要填写大区项,目前全国极大区域有:华北\东北\华东\华南\华中\西南\西北\港澳台\海外.</li>
				</ul>
			</div>
			<!-- 提示 end -->	 
			 <sys:message content="${message}"/>
			<div class="tab-content" style="margin-top:15px;">
				<div class="tab-pane active" id="home-3">
					<form class="cmxform form-horizontal adminex-form" id="inputForm" action="${ctxa}/sys/area/save.do" method="post">
						<input type="hidden" value="${area.id}" name="id">
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess">上级地区&nbsp;:</label>
							<div class="col-sm-5">
								<input type="hidden" value="${area.parentIds}" name="preIds" id="preIds">
								<sys:treeselect id="area" name="parent.id" value="${area.parent.id}" labelName="parent.name" labelValue="${area.parent.name}"
									title="区域" url="/sys/area/treeData.do" extId="${area.id}" cssClass="" allowClear="true"/>
							</div>
							<div class="col-sm-5">
								<p class="help-block">选择上级地区时，应用treeselect进行选择<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"><font color="red">*</font> 地区名称&nbsp;:</label>
							<div class="col-sm-5">
								<input class="form-control input-sm" type="text" name="name" value="${area.name}"> 
							</div>
							<div class="col-sm-5">
								<p class="help-block">必填选项，可使用中英文、内容长度控制在2-30个字符。<p>
							</div>
						</div>
						<div class="form-group" id="largeArea" style="display:${idsLength == 1 || idsLength == 2 || empty idsLength?'':'none'}">
							<label class="control-label col-sm-2" for="inputSuccess"><font color="red">*</font> 所属大区&nbsp;:</label>
							<div class="col-sm-5">
								<!-- <input class="form-control input-sm" type="text"> -->
								<select class="form-control m-bot15" name="largeArea">
									<c:forEach items="${fns:getDictList('large_area')}" var="la">
										<option ${la.value eq area.largeArea?"selected":""} value="${la.value}">${la.label}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-5">
								<p class="help-block">选填选项，可使用中英文、内容长度控制在1-30个字符。<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"></label>
							<div class="col-sm-5">
								<c:if test="${not empty area.id}">
									<button class="btn btn-primary" type="button">
										<i class="fa fa-times"></i> 返 回 
									</button>
								</c:if>
								<shiro:hasPermission name="sys:area:edit">
								<c:if test="${empty area.id}">
									<button type="submit" name="submit" class="btn btn-info submitBtn" value="1">
										<i class="fa fa-check-circle"></i> 保存并继续添加
									</button>
								</c:if>
								<button type="submit" name="submit" class="btn btn-info" value="2">
									<i class="fa fa-check"></i> 保 存 
								</button>
								</shiro:hasPermission>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<!-- panel end -->
</body>
</html>