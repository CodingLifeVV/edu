# 在线教育网
<p>
    <a href="https://github.com/CodingLifeVV/edu-admin/tree/master"><img src="https://img.shields.io/badge/%E5%89%8D%E7%AB%AF%E9%A1%B9%E7%9B%AE-edu--admin-blue" alt="前端项目"></a>
    <a href="https://github.com/CodingLifeVV/edu"><img src="https://img.shields.io/badge/%E5%90%8E%E7%AB%AF%E9%A1%B9%E7%9B%AE-edu-orange" alt="后端项目"></a>
</p>

# 项目介绍

在线教育网是基于 SpringBoot 的前后端分离项目，后端使用 SpringBoot 开发，JDK 版本为 17.0.2，后端前台基于 vue-element-admin 快速开发，使用到的相关技术如下：

**后端技术与工具：**

| 技术         | 版本          | 说明                  |
| ------------ | ------------- | --------------------- |
| SpringBoot   | 2.6.4         | Java 后端系统开发框架 |
| MyBatis-Plus | 3.5.1         | Mybatis 增强工具      |
| Druid        | 1.2.8         | 阿里巴巴数据库连接池  |
| Swagger      | 3.0.0         | 文档生成工具          |
| slf4j+log4j2 | 1.7.32+2.17.1 | 日志实现              |
|              |               |                       |
|              |               |                       |

**前端技术与工具**：

| 技术               | 版本  | 说明                                                         |
| ------------------ | ----- | ------------------------------------------------------------ |
| vue-admin-template | 4.4.0 | 基于 vue-element-admin，后台的<br/>前端管理系统基础模板，用于二次开发 |
|                    |       |                                                              |
|                    |       |                                                              |

**前后端版本对应**：
| 后端         | 后端前台          | 前端                  |
| ------------ | ------------- | --------------------- |
|   master      |   master |      master         |
|   edu-v1.0      |   edu-admin-v1.0 |               |
|   edu-v1.1.0    |   edu-admin-v1.1.0 |             |
|   edu-v1.1.1    |   edu-admin-v1.1.0 |   edu-front-v1.1.0 |


# 版本

## edu-v1.0

项目**初始版本**，您可以克隆该版本的源码进行项目**快速入门**，主要实现以下功能：

`edu-common`模块：

- Swagger3 配置

- 全局异常处理
- 统一日志处理 Slf4j+Log4j2

`edu-service`模块：

- Mybatis-Plus 代码自动生成
- 教师模块部分功能
- 登陆模块部分功能，为了能够和前端连通，进行了简单的实现

## edu-v1.1.0

新增以下内容：

- 课程分类管理模块
  - 课程分类列表功能
  - 添加课程分类功能（添加功能使用EasyExcel上传Excel文件数据到数据库）
- 课程管理模块
  - 添加课程功能
  - 课程列表功能

## edu-v1.1.1

新增以下内容：
- 微服务注册、OpenFeign使用、Sentinel限流
- 实现Banner微服务功能，创建`edu-service-cms`子模块，并集成Redis服务
- 实现阿里云短信微服务，创建`edu-service-msm`子模块
- 创建用户微服务子模块`edu-service-ucenter`

# 内容开发中尽情期待......

