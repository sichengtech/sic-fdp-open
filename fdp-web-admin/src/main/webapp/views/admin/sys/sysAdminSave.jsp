<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/views/admin/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>新增管理员</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<meta name="decorator" content="admin"/>
<meta name="menu" content="8"/><!-- 表示使用N号二级菜单 -->
<!-- 引入iCheck文件-->
<%@ include file="../include/head_iCheck.jsp"%>
<%@ include file="../include/head_bootstrap-switch.jsp"%>
<!-- 业务js -->
<script type="text/javascript" src="${ctxStatic}/ajaxfileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctxStatic}/fdp/upload.js"></script>
<script type="text/javascript" src="${ctx}/views/admin/sys/sysAdminSave.js"></script>
<style type="text/css">
.icheck label{padding-left: 17px;}
</style>
</head>
<body>
	<!-- panel开始 -->
	<section class="panel">
		<header class="panel-heading custom-tab tab-right ">
			<h4 class="title">新增管理员</h4>
			<%@ include file="../include/functionBtn.jsp"%>
			<ul class="nav nav-tabs pull-right">
				<li class=""><a href="${ctxa}/sys/user/list.do"> <i class="fa fa-user"></i> 管理员列表</a></li>
				<shiro:hasPermission name="sys:user:save">
				<li class="active"><a href="javaScript:;" > <i class="fa fa-user"></i> 新增管理员</a></li>
				</shiro:hasPermission>
			</ul>
		</header>
		<!-- panel-body开始 -->
		<div class="panel-body">
			<!-- 提示开始 -->
			<div class="alert alert-info alert-block fade in ${cookie.fdp_operationTips.value=='0'?'point_hidden':''}" id="point">
				<h5>操作提示</h5>
				<ul>
					<li>通过管理员管理，你可以进行查看、编辑管理员资料等操作。</li>
				</ul>
			</div>
			<!-- 提示结束 -->
			<sys:message content="${message}"/>	 
			<div class="tab-content" style="margin-top:15px;">
				<div class="tab-pane active" id="home-3">
					<form class="cmxform form-horizontal adminex-form" id="inputForm" action="${ctxa}/sys/user/save2.do" method="post">
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> 管理员头像&nbsp;:</label>
							<div class="col-sm-5">
								<input type="hidden" class="existSize_photo" value="${not empty user.photo?'1':'0'}"/>
								<input id="fileUpload_photo" name="fileUpload" type="file" thumbnail="100x100" tokenPath="${ctxa}/sys/sysToken/getToken.do" suffix="photo" allowType="jpg,jpeg,png,bmp" fileSize="5242880" class="fileUploadClass"/>
								<div class="result_photo"></div>
								<div class="uploadImgDiv" id="img_photo_0" style="${not empty user.photo?'':'display:none;'}">
									<input type="hidden" class="imgPath" name="photo" value="${user.photo}"/>
									<div class="result"></div>
									<img class="preview" src="${ctxfs}${user.photo}@!100x100" style="${not empty user.photo?'':'display:none;'}"/>
									<div class="uploadCloseBtn">
										<img alt="" src="${ctxStatic}/images/close.gif"/>
									</div>
								</div>
							</div>
							<div class="col-sm-5">
								<p class="help-block">管理员头像尺寸要求宽度为120像素、高度为120像素，比例为1:1的图片；支持格式gif，jpg，png。<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess">归属公司&nbsp;:</label>
							<div class="col-sm-5">
								<div class="controls">
									<sys:treeselect id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}"
										title="公司" url="/sys/office/treeData.do?type=1" cssClass="input-sm"/>
								</div> 
							</div>
							<div class="col-sm-5">
								<p class="help-block">必填项，请选择归属公司<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess">归属部门&nbsp;:</label>
							<div class="col-sm-5">
								<div class="controls">
									<sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
										title="部门" url="/sys/office/treeData.do?type=2" cssClass="input-sm" notAllowSelectParent="true"/>
								</div> 
							</div>
							<div class="col-sm-5">
								<p class="help-block">必填项，请选择归属部门<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"><font color="red">*</font> 工号&nbsp;:</label>
							<div class="col-sm-5">
								<input class="form-control input-sm" type="text" placeholder="" name="no"> 
							</div>
							<div class="col-sm-5">
								<p class="help-block">必填项，请填写工号<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"><font color="red">*</font> 姓名&nbsp;:</label>
							<div class="col-sm-5">
								<input class="form-control input-sm" type="text" placeholder="" name="name"> 
							</div>
							<div class="col-sm-5">
								<p class="help-block">必填项，请填写姓名<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"><font color="red">*</font> 登录名&nbsp;:</label>
							<div class="col-sm-5">
								<input class="form-control input-sm" type="text" placeholder="" name="loginName"> 
							</div>
							<div class="col-sm-5">
								<p class="help-block">必填项，请填写登录名<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"><font color="red">*</font> 密码&nbsp;:</label>
							<div class="col-sm-5">
								<input class="form-control input-sm" type="password" placeholder="" id="newPassword" name="newPassword"> 
							</div>
							<div class="col-sm-5">
								<p class="help-block">必填项，请确保两次密码输入一致<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"><font color="red">*</font> 确认密码&nbsp;:</label>
							<div class="col-sm-5">
								<input class="form-control input-sm" type="password" placeholder="" name="confirmNewPassword"> 
							</div>
							<div class="col-sm-5">
								<p class="help-block">必填项，请确保两次密码输入一致<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess">QQ&nbsp;:</label>
							<div class="col-sm-5">
								<input class="form-control input-sm" type="text" placeholder="" name="qq"> 
							</div>
							<div class="col-sm-5">
								<p class="help-block">请填写QQ<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess">邮箱&nbsp;:</label>
							<div class="col-sm-5">
								<input class="form-control input-sm" type="text" placeholder="" name="email"> 
							</div>
							<div class="col-sm-5">
								<p class="help-block">请填写邮箱<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess">电话&nbsp;:</label>
							<div class="col-sm-5">
								<input class="form-control input-sm" type="text" placeholder="" name="phone"> 
							</div>
							<div class="col-sm-5">
								<p class="help-block">请填写电话<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess">手机&nbsp;:</label>
							<div class="col-sm-5">
								<input class="form-control input-sm" type="text" placeholder="" name="mobile"> 
							</div>
							<div class="col-sm-5">
								<p class="help-block">请填写手机<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"><font color="red">*</font> 用户角色&nbsp;:</label>
							<div class="col-sm-5 icheck">
								<c:forEach items="${allRoles}" var="roles" >
									<div class="square-blue single-row">
										<div class="checkbox">
											<input type="checkbox" style="display: none" data-switch-no-init name="roleIds" value="${roles.id}">
											<label>${roles.name}</label>
										</div>
									</div> 
								</c:forEach>
							</div>
							<div class="col-sm-5">
								<p class="help-block">请选择用户的角色<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess">是否允许登录&nbsp;:</label>
							<div class="col-sm-5">
							 <input type="checkbox" checked data-size="small" style="display: none" value="1" data-on-text="是" data-off-text="否" name="loginFlag" value="66"/>
							</div>
							<div class="col-sm-5">
								<p class="help-block">是否允许登录<p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"> 备注&nbsp;:</label>
							<div class="col-sm-5">
								<textarea rows="6" class="form-control" name="remarks"></textarea>
							</div>
							<div class="col-sm-5">
								<p class="help-block">请填写备注<p>
							</div>
						</div>
						<shiro:hasPermission name="sys:user:drop">
						<div class="form-group">
							<label class="control-label col-sm-2" for="inputSuccess"></label>
							<div class="col-sm-5">
								<button type="submit" class="btn btn-info" name="submit" value="1">
									<i class="fa fa-check-circle"></i> 保存并继续添加
								</button>
								<button type="submit" class="btn btn-info" name="submit" value="2">
									<i class="fa fa-check"></i> 保 存 
								</button>
							</div>
						</div>
						</shiro:hasPermission>
					</form>
				</div>
			</div>
		</div>
		<!-- panel-body结束 -->
	</section>
	<!-- panel结束 -->
</body>
</html>