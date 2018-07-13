INSERT INTO `permission` (`id`, `description`, `name`, `pid`, `url`)
VALUES
	(1,'首页','index',0,NULL),
	(2,'仪表盘','index:dashboard',1,'/admin/dashboard'),
	(4,'后台用户','admin_user',0,''),
	(5,'用户列表','admin_user:list',4,'/admin/admin_user/list'),
	(6,'添加用户','admin_user:add',4,'/admin/admin_user/add'),
	(7,'编辑用户','admin_user:edit',4,'/admin/admin_user/edit'),
	(8,'删除用户','admin_user:delete',4,'/admin/admin_user/delete'),
	(9,'禁用户','admin_user:block',4,'/admin/admin_user/block'),
	(10,'解禁用户','admin_user:unblock',4,'/admin/admin_user/unblock'),
	(11,'角色','role',0,''),
	(12,'权限','permission',0,''),
	(13,'权限列表','permission:list',12,'/admin/permission/list'),
	(14,'添加权限','permission:add',12,'/admin/permission/add'),
	(15,'编辑权限','permission:edit',12,'/admin/permission/edit'),
	(16,'删除权限','permission:delete',12,'/admin/permission/delete'),
	(18,'角色列表','role:list',11,'/admin/role/list'),
	(19,'编辑角色','role:edit',11,'/admin/role/edit'),
	(20,'添加角色','role:add',11,'/admin/role/add'),
	(21,'删除角色','role:delete',11,'/admin/role/delete'),
	(22, '定时器', 'schedule', 0, ''),
	(23, '定时任务', 'schedule:list', 22, '/admin/schedule/list'),
	(24, '添加', 'schedule:add', 22, '/admin/schedule/add'),
	(25, '修改', 'schedule:edit', 22, '/admin/schedule/edit'),
	(26, '删除', 'schedule:delete', 22, '/admin/schedule/delete'),
	(27, '恢复', 'schedule:resume', 22, '/admin/schedule/resume'),
	(28, '暂停', 'schedule:stop', 22, '/admin/schedule/stop'),
	(29, '立即运行', 'schedule:start_now', 22, '/admin/schedule/startNow');


INSERT INTO `role` (`id`, `description`, `name`)
VALUES
	(1,'管理员','admin'),
	(4,'test','test');

INSERT INTO `role_permissions` (`role_id`, `permission_id`)
VALUES
	(1,2),
	(4,2),
	(1,5),
	(1,6),
	(1,7),
	(1,8),
	(1,9),
	(1,10),
	(1,13),
	(1,14),
	(1,15),
	(1,16),
	(1,18),
	(1,19),
	(1,20),
	(1,21),
	(1, 23),
	(1, 24),
	(1, 25),
	(1, 26),
	(1, 27),
	(1, 28),
	(1, 29);


INSERT INTO `admin_user` (`id`, `attempts`, `attempts_time`, `block`, `in_time`, `password`, `username`, `role_id`)
VALUES
	(1,0,'2017-12-27 16:36:30',b'0','2017-12-27 13:47:24','$2a$10$eA3cfL//b8F4/dBWQIDVDuL2Wc1TlHNbvEZhHNq/mmNmUrFOWdnAi','tomoya',1),
	(2,0,'2017-12-27 16:35:10',b'0','2017-12-27 13:47:24','$2a$10$eA3cfL//b8F4/dBWQIDVDuL2Wc1TlHNbvEZhHNq/mmNmUrFOWdnAi','test',4);
