# 一个springboot+vue的博客系统

## 环境配置（略）
- JDK 17+
- MySQL 8+
- Redis 6+
- Node.js 18+


## 1. 构建数据库
新建数据库 `rocketblog`，导入 [rocketblog.sql](./rocketblog/src/main/resources/rocketblog.sql) 文件即可。


## 2. 前端打包
进入 `vueblog` 目录，执行 `npm install` 安装依赖，然后执行 `npm run build` 进行打包。

将 `vueblog/dist` 目录下的文件复制到 `rocketblog/src/main/resources/static` 目录下。


## 3. 后端配置
修改 `rocketblog/src/main/resources/application.yml` 文件中的配置为自己的配置：
- redis: `spring.data.redis.url`、`spring.data.redis.port`、`spring.data.redis.password`
- mysql: `spring.datasource.url`、`spring.datasource.username`、`spring.datasource.password`
- mail: `spring.mail.host`、`spring.mail.username`、`spring.mail.password`


## 4. 后端打包
进入 `rocketblog` 目录，执行 `mvn clean package` 进行打包。

> 以上打包步骤可以直接运行脚本 [build.bat](./build.bat) 。


## 5. 运行
执行 `java -jar target/rocketblog-0.0.1-SNAPSHOT.jar` 即可运行项目。

