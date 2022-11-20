# xue-gao-framework

xuegao，开始写自己的框架

```java
        cache，只是缓存的处理
        config，读取配置文件，或者接入外部的配置文件
        core
        filter-debug，可以远程链接dev,stg,uat
        id-center，分布式id的生成
        jdbc，封装或者改造mybatis
        kafka，封装kafka
        log，链路追踪的实现
        mapper，对两个jdbc进行封装，同时也对mybatisplus进行封装
        micro-services，对服务的行为，工具进行封装
        sharding-jdbc，分库分表
        util，工具类

        引用说明：log模块，部分为自己研究琢磨，，涉及链路追踪的相关代码都来自于开源项目，Tlog（tlog-webroot，tlog-task）

```

# maven

mvn clean source:jar install -Dmaven.test.skip=true

# 0.0.1

项目搭建起来了

# 0.0.2

新增了几个工具类

# 0.0.3

每个包应该都有名字，，否则直接扫描com.xuegao，引入的东西太多了。 升级0.0.3 ，增加每个包的三级包名
