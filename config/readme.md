### 1、项目配置（虚拟目录），在tomcat的server.xml中，在 <Host></>里添加以下代码

* 影藏项目名称

```
<!-- docBase设置为本地tomcat下的webapps目录/项目名称 或/项目名称 -->
<Context debug="0" docBase="/jittest" path="/" reloadable="true"/>
```

* 文件上传地址

```
<Context docBase="D:\jittest\file\upload" path="/upload" reloadable="true"/>
```



### 2、跨域请求，使用 http://127.0.0.1:8080 访问

