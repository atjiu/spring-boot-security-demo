<#include "../../layout/layout.ftl">
<@html page_title="添加角色" page_tab="user_role">
<section class="content-header">
	<h1>
		角色
		<small>添加</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="/admin/dashboard"><i class="fa fa-dashboard"></i> 首页</a></li>
		<li class="active"> 角色</li>
	</ol>
</section>
<div class="content">
	<div class="row">
		<div class="col-md-8">
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">添加角色</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form role="form" id="form" action="/admin/role/add" method="post">
					<input type="hidden" name="${_csrf.parameterName!}" value="${_csrf.token!}"/>
					<div class="box-body">
						<div class="form-group">
							<label for="description">描述</label>
							<input type="text" class="form-control" id="description" name="description" placeholder="描述, 如: 管理员">
						</div>
						<div class="form-group">
							<label for="name">角色名</label>
							<input type="text" class="form-control" id="name" name="name" placeholder="角色名, 如: admin">
						</div>
						<div class="form-group">
							<label for="">权限</label>
							<div>
								<#list list as l>
									<p><b>${l.permission.description!}</b></p>
									<#list l.childPermissions as childPermission>
										<input type="checkbox" name="permissionIds" value="${childPermission.id?c}"
										       id="permission_${childPermission.id?c}">
										<label for="permission_${childPermission.id?c}">${childPermission.description!}</label>&nbsp;
									</#list>
								</#list>
							</div>
						</div>
					</div>
					<!-- /.box-body -->
					<div class="box-footer">
						<button type="submit" class="btn btn-primary">提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</@html>
<@javascript>
<script>
	$(function () {
		$('input').iCheck({
			checkboxClass: 'icheckbox_square-blue',
			radioClass: 'iradio_square-blue',
		});
	})
</script>
</@javascript>