<#include "../../layout/layout.ftl">
<@html page_title="添加权限" page_tab="user_permission">
<section class="content-header">
	<h1>
		权限
		<small>编辑</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="/admin/dashboard"><i class="fa fa-dashboard"></i> 首页</a></li>
		<li class="active"> 权限</li>
	</ol>
</section>
<div class="content">
	<div class="row">
		<div class="col-md-8">
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">编辑权限</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form role="form" id="form" action="/admin/permission/edit" method="post">
					<input type="hidden" name="${_csrf.parameterName!}" value="${_csrf.token!}"/>
					<input type="hidden" name="id" value="${permission.id?c}">
					<div class="box-body">
						<div class="form-group">
							<label for="pid">父节点</label>
							<select name="pid" id="pid" class="form-control">
								<option value="0">父节点</option>
								<#list permissions as ppermission>
									<option value="${ppermission.id?c}" <#if permission.pid == ppermission.id>selected</#if>>${ppermission.description!}</option>
								</#list>
							</select>
						</div>
						<div class="form-group">
							<label for="description">描述</label>
							<input type="text" class="form-control" value="${permission.description!}" id="description" name="description" placeholder="描述, 如: 用户列表">
						</div>
						<div class="form-group">
							<label for="name">权限名</label>
							<input type="text" class="form-control" value="${permission.name!}" id="name" name="name" placeholder="角色名, 不能跟已存在的权限名重复, 如: user:list">
						</div>
						<div class="form-group">
							<label for="url">URL</label>
							<input type="text" class="form-control" value="${permission.url!}" id="url" name="url" placeholder="URL, 父节点可为空">
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
<@javascript></@javascript>