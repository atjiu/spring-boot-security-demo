<#macro menu page_tab>
<aside class="main-sidebar">
  <!-- sidebar: style can be found in sidebar.less -->
  <section class="sidebar" style="height: auto;">
    <!-- Sidebar user panel -->
    <div class="user-panel">
      <div class="pull-left image">
        <img src="/static/adminlte/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
      </div>
      <div class="pull-left info">
        <p>${sec.getPrincipal()!}</p>
        <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
      </div>
    </div>
    <!-- sidebar menu: : style can be found in sidebar.less -->
    <ul class="sidebar-menu">
      <li class="header">MAIN NAVIGATION</li>
      <li <#if page_tab == 'dashboard'>class="active"</#if>>
        <a href="/dashboard">
          <i class="fa fa-dashboard"></i>
          <span>仪表盘</span>
        </a>
      </li>
      <li class="treeview">
        <a href="javascript:;">
          <i class="fa fa-user"></i>
          <span>用户</span>
          <span class="pull-right-container">
            <i class="fa fa-angle-left pull-right"></i>
          </span>
        </a>
        <ul class="treeview-menu">
          <li><a href="/user/list"><i class="fa fa-list"></i> 用户列表</a></li>
          <li><a href="/role/list"><i class="fa fa-list"></i> 角色列表</a></li>
          <li><a href="/permission/list"><i class="fa fa-list"></i> 权限列表</a></li>
        </ul>
      </li>
      <li>
        <a href="/logout">
          <span class="glyphicon glyphicon-log-out"></span>
          <span>登出</span>
        </a>
      </li>
    </ul>
  </section>
  <!-- /.sidebar -->
</aside>
</#macro>