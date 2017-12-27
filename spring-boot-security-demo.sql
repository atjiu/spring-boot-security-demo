# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.20)
# Database: spring-boot-security-demo
# Generation Time: 2017-12-27 08:37:14 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table permission
# ------------------------------------------------------------

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;

INSERT INTO `permission` (`id`, `description`, `name`, `pid`, `url`)
VALUES
	(1,'首页','index',0,NULL),
	(2,'仪表盘','index:dashboard',1,'/admin/dashboard'),
	(4,'用户','user',0,''),
	(5,'用户列表','user:list',4,'/admin/user/list'),
	(6,'添加用户','user:add',4,'/admin/user/add'),
	(7,'编辑用户','user:edit',4,'/admin/user/edit'),
	(8,'删除用户','user:delete',4,'/admin/user/delete'),
	(9,'禁用户','user:block',4,'/admin/user/block'),
	(10,'解禁用户','user:unblock',4,'/admin/user/unblock'),
	(11,'角色','role',0,''),
	(12,'权限','permission',0,''),
	(13,'权限列表','permission:list',12,'/admin/permission/list'),
	(14,'添加权限','permission:add',12,'/admin/permission/add'),
	(15,'编辑权限','permission:edit',12,'/admin/permission/edit'),
	(16,'删除权限','permission:delete',12,'/admin/permission/delete'),
	(18,'角色列表','role:list',11,'/admin/role/list'),
	(19,'编辑角色','role:edit',11,'/admin/role/edit'),
	(20,'添加角色','role:add',11,'/admin/role/add'),
	(21,'删除角色','role:delete',11,'/admin/role/delete');

/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table remember_me_token
# ------------------------------------------------------------

LOCK TABLES `remember_me_token` WRITE;
/*!40000 ALTER TABLE `remember_me_token` DISABLE KEYS */;

INSERT INTO `remember_me_token` (`id`, `date`, `series`, `token_value`, `username`)
VALUES
	(6,'2017-12-27 16:34:37','EIbahK9j/yLq8vSpdr1EXw==','NyvwEU5o26G3QGYmH6PF6g==','tomoya'),
	(7,'2017-12-27 16:35:10','ZCD50STia3d5bVr2cgzz9g==','Viy4quQzc5nl6l5X/oBc2w==','test'),
	(8,'2017-12-27 16:36:30','gAkqBk8rcWmPESUfzHh2jg==','TVqRR9ue3IDft3Rkuc840A==','tomoya');

/*!40000 ALTER TABLE `remember_me_token` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table role
# ------------------------------------------------------------

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;

INSERT INTO `role` (`id`, `description`, `name`)
VALUES
	(1,'管理员','admin'),
	(4,'test','test');

/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table role_permissions
# ------------------------------------------------------------

LOCK TABLES `role_permissions` WRITE;
/*!40000 ALTER TABLE `role_permissions` DISABLE KEYS */;

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
	(1,21);

/*!40000 ALTER TABLE `role_permissions` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `attempts`, `attempts_time`, `block`, `in_time`, `password`, `username`, `role_id`)
VALUES
	(1,0,'2017-12-27 16:36:30',b'0','2017-12-27 13:47:24','$2a$10$eA3cfL//b8F4/dBWQIDVDuL2Wc1TlHNbvEZhHNq/mmNmUrFOWdnAi','tomoya',1),
	(2,0,'2017-12-27 16:35:10',b'0','2017-12-27 13:47:24','$2a$10$eA3cfL//b8F4/dBWQIDVDuL2Wc1TlHNbvEZhHNq/mmNmUrFOWdnAi','test',4);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
