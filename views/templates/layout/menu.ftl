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
			<#if sec.allGranted("index:dashboard")>
				<li <#if page_tab == 'dashboard'>class="active"</#if>>
					<a href="/admin/dashboard">
						<i class="fa fa-dashboard"></i>
						<span>仪表盘</span>
					</a>
				</li>
			</#if>
			<#if sec.allGranted("user:list") || sec.allGranted("role:list") || sec.allGranted("permission:list")>
				<li class="treeview <#if page_tab?index_of("user_") == 0>active</#if>">
					<a href="javascript:;">
						<i class="fa fa-user"></i>
						<span>用户</span>
						<span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
					</a>
					<ul class="treeview-menu">
						<#if sec.allGranted("user:list")>
							<li <#if page_tab == "user_list">class="active"</#if>>
								<a href="/admin/user/list"><i class="fa fa-list"></i> 用户列表</a>
							</li>
						</#if>
						<#if sec.allGranted("role:list")>
							<li <#if page_tab == "user_role">class="active"</#if>>
								<a href="/admin/role/list"><i class="fa fa-list"></i> 角色列表</a>
							</li>
						</#if>
						<#if sec.allGranted("permission:list")>
							<li <#if page_tab == "user_permission">class="active"</#if>>
								<a href="/admin/permission/list"><i class="fa fa-list"></i> 权限列表</a>
							</li>
						</#if>
					</ul>
				</li>
			</#if>
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