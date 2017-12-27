# spring-boot-security-demo
spring-boot + spring-security整合后的一个后台框架的空壳

## 启动

- git clone https://github.com/tomoya92/spring-boot-security-demo
- 修改配置文件 `application.yml`
- 创建数据库：spring-boot-security-demo
- mvn spring-boot:run 程序启动表会自动创建
- 导入数据脚本：spring-boot-security-demo.sql
- 重启运行 mvn spring-boot:run
- 访问 http://localhost:8080/
- 用户名: tomoya 密码: 123123
- 用户名: test 密码: 123123

## 技术栈

- spring-boot
- spring-security
- spring-data-jpa
- adminlte
- mysql
- freemarker

## 权限结构

用户 角色 多对一
角色 权限 多对多

## 截图

![](Screenshot/1.png)

![](Screenshot/2.png)

![](Screenshot/3.png)

![](Screenshot/4.png)

![](Screenshot/5.png)

![](Screenshot/6.png)

![](Screenshot/7.png)

![](Screenshot/8.png)

