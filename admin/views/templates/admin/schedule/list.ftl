<#include "../../layout/layout.ftl">
<@html page_title="定时任务" page_tab="jobs_list">
<section class="content-header">
  <h1>
    定时器
    <small>列表</small>
  </h1>
  <ol class="breadcrumb">
    <li><a href="/admin/dashboard"><i class="fa fa-dashboard"></i> 首页</a></li>
    <li class="active"> 定时器</li>
  </ol>
</section>
<div class="content">
  <form class="form-horizontal" id="IntentSearch" action="/admin/schedule/add" method="post">
    <input type="hidden" name="${_csrf.parameterName!}" value="${_csrf.token!}"/>
    <div class="modal fade in" id="add" tabindex="-1" role="dialog"
         aria-labelledby="myLargeModalLabel" style="display: none;overflow: hidden;">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                id="closeWindowQuery" aria-hidden="true">×</span></button>
            <h4 class="modal-title" id="myLargeModalLabel">添加定时任务</h4>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <label class="col-sm-3 control-label">任务名:</label>
              <div class="col-sm-9">
                <input type="text" id="name" name="name" class="form-control">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">任务组:</label>
              <div class="col-sm-9">
                <input type="text" id="group" name="group" class="form-control">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">cron表达式:</label>
              <div class="col-sm-9">
                <input type="text" id="cronExpression" name="cronExpression" class="form-control">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">任务类:</label>
              <div class="col-sm-9">
                <input type="text" id="className" name="className" class="form-control">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">描述:</label>
              <div class="col-sm-9">
                <input type="text" id="description" name="description" class="form-control">
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="submit" class="btn btn-primary flat">提交</button>
          </div>
        </div>
      </div>
    </div>
  </form>
  <div class="row">
    <div class="col-md-12">
      <div class="box">
        <div class="box-header with-border">
          <h3 class="box-title">定时器列表</h3>
          <#if sec.allGranted("schedule:add")>
            <button type="button" class="btn btn-primary btn-xs pull-right" data-toggle="modal" data-target="#add">添加任务</button>
          </#if>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
          <div class="table-responsive">
            <table class="table table-bordered">
              <thead>
              <tr>
                <th>任务名</th>
                <th>任务组</th>
                <th>cron表达式</th>
                <th>状态</th>
                <th>任务类</th>
                <th>描述</th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody>
                <#list scheduleJobs as scheduleJob>
                <tr>
                  <td>${scheduleJob.name!}</td>
                  <td>${scheduleJob.group!}</td>
                  <td>${scheduleJob.cronExpression!}</td>
                  <td <#if scheduleJob.status == "PAUSED"> class="danger" <#elseif scheduleJob.status == "NORMAL">
                                                           class="success" </#if>>
                    ${scheduleJob.status!}
                  </td>
                  <td>${scheduleJob.className!}</td>
                  <td>${scheduleJob.description!}</td>
                  <td>
                    <#if sec.allGranted("schedule:edit")>
                      <button type="button" class="btn btn-primary btn-xs flat" data-toggle="modal"
                              data-target="#modify_quartz_${scheduleJob.name!}">
                        修改表达式
                      </button>
                      <div class="modal fade in" id="modify_quartz_${scheduleJob.name}" tabindex="-1" role="dialog"
                           aria-labelledby="myLargeModalLabel">
                        <div class="modal-dialog">
                          <div class="modal-content">
                            <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                  aria-hidden="true">×</span></button>
                              <h4 class="modal-title">修改定时任务</h4>
                            </div>
                            <div class="modal-body" style="overflow: hidden;">
                              <input type="hidden" id="name_${scheduleJob.name!}" value="${scheduleJob.name!}"
                                     class="form-control">
                              <input type="hidden" id="group_${scheduleJob.name!}" value="${scheduleJob.group!}"
                                     class="form-control">
                              <input type="hidden" id="description_${scheduleJob.name!}"
                                     value="${scheduleJob.description!}" class="form-control">
                              <div class="form-group">
                                <label class="col-sm-3 control-label">cron表达式:</label>
                                <div class="col-sm-9">
                                  <input type="text" id="cronExpression_${scheduleJob.name!}"
                                         value="${scheduleJob.cronExpression!}" class="form-control">
                                </div>
                              </div>
                            </div>
                            <div class="modal-footer">
                              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                              <button type="button" id="submitQueryButton_${scheduleJob.name!}" class="btn btn-primary">
                                修改
                              </button>
                            </div>
                          </div>
                        </div>
                      </div>
                      <script>
                        $("#submitQueryButton_${scheduleJob.name!}").click(function () {
                          var name = $("#name_${scheduleJob.name!}").val();
                          var group = $("#group_${scheduleJob.name!}").val();
                          var cronExpression = $("#cronExpression_${scheduleJob.name!}").val();
                          var description = $("#description_${scheduleJob.name!}").val();
                          $.ajax({
                            url: "/admin/schedule/edit",
                            type: "post",
                            async: false,
                            cache: false,
                            dataType: "json",
                            data: {
                              name: name,
                              group: group,
                              cronExpression: cronExpression,
                              description: description,
                              '${_csrf.parameterName!}': '${_csrf.token!}'
                            },
                            success: function (data) {
                              if (data.code === 200) {
                                layer.msg("修改成功", {icon: 6});
                                window.location.reload();
                              } else {
                                layer.msg(data.description, {icon: 5});
                              }
                            }
                          })
                        })
                      </script>
                    </#if>
                    <#if sec.allGranted("schedule:start_now")>
                      <button type="button" class="btn btn-primary btn-xs flat"
                              onclick="handleCli('startNow','${scheduleJob.name!}','${scheduleJob.group!}')">立即运行一次
                      </button>
                    </#if>
                    <#if scheduleJob.status=='NORMAL'>
                      <#if sec.allGranted("schedule:stop")>
                        <button type="button" class="btn bg-orange btn-xs flat"
                                onclick="handleCli('stop','${scheduleJob.name!}','${scheduleJob.group!}')">暂停
                        </button>
                      </#if>
                    <#else>
                      <#if sec.allGranted("schedule:resume")>
                        <button type="button" class="btn bg-purple btn-xs flat"
                                onclick="handleCli('resume','${scheduleJob.name!}','${scheduleJob.group!}')">恢复
                        </button>
                      </#if>
                    </#if>
                    <#if sec.allGranted("schedule:delete")>
                      <button type="button" class="btn btn-danger btn-xs flat"
                              onclick="handleCli('delete','${scheduleJob.name!}','${scheduleJob.group!}')">删除
                      </button>
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
</div>
<script>
  function handleCli(type, name, group) {
    layer.confirm("确定此操作吗?", function () {
      $.ajax({
        url: '/admin/schedule/' + type,
        async: false,
        cache: false,
        type: 'post',
        dataType: "json",
        data: {
          name: name,
          group: group,
          '${_csrf.parameterName!}': '${_csrf.token!}'
        },
        success: function (data) {
          if (type != "startNow") {
            window.location = "/admin/schedule/list";
          }
        }
      });
    });
  }
</script>
</@html>