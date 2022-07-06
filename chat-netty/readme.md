使用 netty + spring bean + hibernate 实现的聊天小项目
实现功能
    注册
    登录
    世界聊天
    私聊


netty 监听端口
spring bean 容器管理业务出来代码
hibernate 数据落地
客户端使用java swing，二次开发

chat-common
    抽取公共代码，如 请求、响应包、抽象session、

chat-server
    服务段实现，netty 监听端口 根据请求执行对应的业务方法，返回数据


