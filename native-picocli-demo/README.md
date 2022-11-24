##介绍

####picocli
* 命令交互应用开发扩展框架，帮助开发cli命令程序

#### graalvm
* 通过graalvm native image 打包 项目为原生可执行文件

###环境
 * 安装 graalvm 和 native image
 
 * ####linux 原生环境
    * yum install gcc
    * yum install zlib zlib-devel
 * ####windows 原生环境
    * 安装 VS 
    * 注意：语言包选择英文
###编译
 * cmd 执行 vcvars64.bat
 * mvn clean package 
