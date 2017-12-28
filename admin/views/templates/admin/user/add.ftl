<#include "../../layout/layout.ftl">
<@html page_title="添加用户" page_tab="user_list">
<section class="content-header">
	<h1>
		用户
		<small>添加</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="/admin/dashboard"><i class="fa fa-dashboard"></i> 首页</a></li>
		<li class="active"> 用户</li>
	</ol>
</section>
<div class="content">
	<div class="row">
		<div class="col-md-8">
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">添加用户</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form role="form" id="form" action="/admin/user/add" method="post">
					<input type="hidden" name="${_csrf.parameterName!}" value="${_csrf.token!}"/>
					<div class="box-body">
						<div class="form-group">
							<label for="username">用户名</label>
							<input type="text" class="form-control" id="username" name="username" placeholder="用户名">
						</div>
						<div class="form-group">
							<label for="password">密码</label>
							<input type="password" class="form-control" id="password" name="password" placeholder="密码">
						</div>
						<div class="form-group">
							<label for="">角色</label>
							<div class="iradio">
								<#list roles as role>
									<input <#if role_index == 0>checked</#if> type="radio" id="role_${role.id?c}" value="${role.id?c}" name="roleId"/>&nbsp;
									<label for="role_${role.id?c}">${role.description!}</label>
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