package cn.peng.studygodpath.jes269;


/*
https://blog.csdn.net/justry_deng/article/details/106176181
插件化注解处理API的使用步骤大概如下：
        自定义一个Annotation
        Processor，需要继承javax.annotation.processing.AbstractProcessor，并覆写process方法。
        自定义一个注解，注解的元注解需要指定@Retention(RetentionPolicy.SOURCE)。
        需要在声明的自定义Annotation
        Processor中使用javax.annotation.processing.SupportedAnnotationTypes指定在第2步创建的注解类型的名称(注意需要全类名，“包名.注解类型名称”，否则会不生效)。
        需要在声明的自定义Annotation
        Processor中使用javax.annotation.processing.SupportedSourceVersion指定编译版本。
        可选操作，可以通在声明的自定义Annotation
        Processor中使用javax.annotation.processing.SupportedOptions指定编译参数。
*/
