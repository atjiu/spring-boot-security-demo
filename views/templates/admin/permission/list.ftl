<#include "../../layout/layout.ftl">
<@html page_title="权限列表" page_tab="user_permission">
<style>
	.list-group>.active>a:last-child{
		color: #fff;
	}
</style>
<section class="content-header">
	<h1>
		权限
		<small>列表</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="/admin/dashboard"><i class="fa fa-dashboard"></i> 首页</a></li>
		<li class="active"> 权限</li>
	</ol>
</section>
<div class="content">
	<div class="row">
		<div class="col-md-3">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">父节点</h3>
				</div>
				<div class="box-body">
					<ul class="list-group">
						<#list permissions as permission>
							<li class="list-group-item <#if pid?? && pid == permission.id>active</#if>">
								<a href="javascript:if(confirm('确认删除吗?'))location.href='/admin/permission/delete?id=${permission.id?c}'">
									<span class="text-danger glyphicon glyphicon-trash"></span>
								</a>&nbsp;
								<a href="/admin/permission/list?pid=${permission.id!}">
								${permission.description!}
								</a>
							</li>
						</#list>
					</ul>
				</div>
			</div>
		</div>
		<div class="col-md-9">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">权限列表</h3>
					<#if sec.allGranted("permission:add")>
						<a class="btn btn-primary btn-xs pull-right" href="/admin/permission/add?pid=${pid!}">添加</a>
					</#if>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table class="table table-bordered">
						<tbody>
						<tr>
							<th>#</th>
							<th>权限名</th>
							<th>描述</th>
							<th>URL</th>
							<th>操作</th>
						</tr>
							<#list childPermissions as permission>
							<tr>
								<td>${permission.id!}</td>
								<td>${permission.name!}</td>
								<td>${permission.description!}</td>
								<td>${permission.url!}</td>
								<td>
									<#if sec.allGranted("permission:edit")>
										<a href="/admin/permission/edit?id=${permission.id?c}" class="btn btn-xs btn-warning">编辑</a>
									</#if>
									<#if sec.allGranted("permission:delete")>
										<a href="javascript:if(confirm('确认删除吗?')) location.href='/admin/permission/delete?id=${permission.id?c}'"
										   class="btn btn-xs btn-danger">删除</a>
									</#if>
								</td>
							</tr>
							</#list>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
</@html>
<@javascript></@javascript>