# xue-gao-framework
xuegao，开始写自己的框架

```java
cache，只是缓存的处理
config，读取配置文件，或者接入外部的配置文件
core
filter-debug，可以远程链接dev,stg,uat
jdbc，封装或者改造mybatis
kafka，封装kafka
log，链路追踪等
sharding-jdbc，分库分表
mapper，对两个jdbc进行封装
micro-services，对服务的行为，工具进行封装
util，工具类

```
# maven
mvn  clean source:jar install -Dmaven.test.skip=true


# 0.0.1
项目搭建起来了

# 0.0.2
新增了几个工具类

# 0.0.3
每个包应该都有名字，，否则直接扫描com.xuegao，引入的东西太多了。
升级0.0.3 ，增加每个包的三级包名
