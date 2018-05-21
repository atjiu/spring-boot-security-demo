<#include "../../layout/layout.ftl">
<@html page_title="角色列表" page_tab="user_role">
<section class="content-header">
  <h1>
    角色
    <small>列表</small>
  </h1>
  <ol class="breadcrumb">
    <li><a href="/admin/dashboard"><i class="fa fa-dashboard"></i> 首页</a></li>
    <li class="active"> 角色</li>
  </ol>
</section>
<div class="content">
  <div class="row">
    <div class="col-md-12">
      <div class="box">
        <div class="box-header with-border">
          <h3 class="box-title">角色列表</h3>
          <#if sec.allGranted("role:add")>
            <a class="btn btn-primary btn-xs pull-right" href="/admin/role/add">添加</a>
          </#if>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
          <table class="table table-bordered">
            <tbody>
            <tr>
              <th>#</th>
              <th>角色名</th>
              <th>描述</th>
              <th>操作</th>
            </tr>
              <#list roles as role>
              <tr>
                <td>${role.id?c}</td>
                <td>${role.name!}</td>
                <td>${role.description!}</td>
                <td>
                  <#if sec.allGranted("role:edit")>
                    <a class="btn btn-warning btn-xs" href="/admin/role/edit?id=${role.id?c}">编辑</a>
                  </#if>
                  <#if sec.allGranted("role:delete")>
                    <a class="btn btn-danger btn-xs"
                       href="javascript:if(confirm('确定要删除这个角色吗？'))location.href='/admin/role/delete?id=${role.id?c}'">删除</a>
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