# 项目简介
使用 netty + spring bean + hibernate 实现的聊天小项目  
* netty 监听端口  
* spring bean 容器管理业务出来代码  
* hibernate 数据落地  
* 客户端使用java swing，使用别人的源码

# 实现功能
* 注册
* 登录 
* 世界聊天
* 私聊

# 模块
* chat-common
    * 抽取公共代码，如 请求、响应包，编解码器、抽象session、业务接口api dto

* chat-server
    * 服务段实现，netty 监听端口 根据请求执行对应的业务方法，返回数据

# TODO
* 整合logback log4j2
* 账号 hibernate
* 业务模块实现

