# e-chain

## 免责声明
详情请见本项目/doc/代码开源免责声明.txt

## 模块说明

echain-admin 管理端



echain-web 应用端 (前端VUE单独部署/接口统一以 /api开头)

nginx 配置仅供参考

```conf
location /api/ {
        proxy_next_upstream     http_500 http_502 http_503 http_504 error timeout invalid_header;
        proxy_set_header        Host  $host;
        proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_pass              http://tomcat_api;
        expires                 0;
}
location / {
        root /data/domain/api/dist/;
        autoindex on;
}
```



## 环境

java : 1.8+

mysql: 5.7+

redis: 5.*

maven:3



## 框架

springboot + mybatis plus +layuiadmin pro



## 部署

1,安装mysql

2,安装redis

3,恢复数据库 echain.sql(文件位于/doc文件夹下)

配置文件:

application-dev.properties

```properties
##redis ip
redis.host = 127.0.0.1
##redis的port
redis.port = 33790
##redis 密码
redis.password = 1qaz@WSX

##mysql 地址
mysql.url = jdbc:mysql://127.0.0.1:3306/echain?characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&autoReconnect=true&failOverReadOnly=false&allowPublicKeyRetrieval=true
##驱动
mysql.driverClass = com.mysql.cj.jdbc.Driver
##账号
mysql.username = root
##密码
mysql.password = 1qaz@WSX
```



4,启动

见脚本config/sh/start.sh



5,停止

见脚板 config/sh/stop.sh







