# Tomcat总体架构

## 浏览器访问服务器的流程

## Tomcat总体架构

## Tomcat组件介绍

# Tomcat 服务器核⼼配置详解

问题⼀：去哪⼉配置？ 核⼼配置在tomcat⽬录下conf/server.xml⽂件

问题⼆：怎么配置？

注意：

 - Tomcat 作为服务器的配置，主要是 server.xml ⽂件的配置；
 - server.xml中包含了 Servlet容器的相关配置，即 Catalina 的配置；
 - Xml ⽂件的讲解主要是标签的使⽤

整体标签结构如下：
```xml
<!--
Server 根元素，创建⼀个Server实例，⼦标签有 Listener、GlobalNamingResources、
Service
-->
<Server>
    <!--定义监听器-->
    <Listener/>
    <!--定义服务器的全局JNDI资源 -->
    <GlobalNamingResources/>
    <!-- 定义⼀个Service服务，⼀个Server标签可以有多个Service服务实例 -->
    <Service/>
</Server>
```