#  后端项目通用模版（待完善）

## 已引入依赖
- SpringBoot 2.7.6
- MySQL 
- lombok
- knife4j

## 模块
- 异常处理
- 全局跨域

## MySQL 数据库
### user 表
```mysql
CREATE TABLE `user` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '昵称',
                        `userAccount` varchar(32) NOT NULL COMMENT '登录账号',
                        `avatarUrl` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'https://travel-head.oss-cn-shenzhen.aliyuncs.com/head/77770ab4-44b1-4a92-9121-1d36054a8cb7.png' COMMENT '登录头像',
                        `gender` tinyint(4) DEFAULT NULL COMMENT '性别：1男 0女',
                        `userPassword` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
                        `phone` varchar(32) DEFAULT NULL COMMENT '电话',
                        `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
                        `userRole` int(11) NOT NULL COMMENT '角色',
                        `profile` varchar(512) DEFAULT NULL COMMENT '个人简介',
                        `isValid` int(11) NOT NULL DEFAULT '0' COMMENT '是否有效(是否被封号)',
                        `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        `isDelete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

## 接口
### 注册
- 账号长度不能小于8位，长度不能大于12位
- 密码长度不能小于8位，长度不能大于20位
- 用户注册的默认权限为`user`
- 用户昵为`无名字`
- 账号不能已经被注册

## 注解
### @AuthCheck
> 检查用户是否拥有指定的权限，没有权限则禁止调用
- 参数`mustRole`
