/**
 * @Author: Administrator
 * @CreateTime:2023-03-13 10:59
 * QDescription:grpc demo
 */
/**
 * 使用 grpc-spring-boot-starter项目 间接开发grpc
 *  包含服务端、客户端项目，可以分开直接依赖内部服务端依赖，客户端依赖
 *
 * 1. 定义接口，参数的proto文件，
 *  使用 protobuf-maven-plugin 编辑 proto文件，成为java代码，并用grpc 模板生成接口
 *  protobuf:compile ==》编译参数、响应类
 *  protobuf:compile-custom ==> 编译出grpc 接口文件
 *2. 服务端 继承 编译出的接口文件，并重写接口方法
 *3.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package cn.peng.studygodpath.frame.grpc;