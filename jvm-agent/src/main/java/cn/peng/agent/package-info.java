package cn.peng.agent;

/**
 *
 * ClassFileTransformer
 *      //注册ClassFileTransformer实例，注册多个会按照注册顺序进行调用。所有的类被加载完毕之后会调用ClassFileTransformer实例，
 *      相当于它们通过了redefineClasses方法进行重定义。布尔值参数canRetransform决定这里被重定义的类是否能够通过retransformClasses方法进行回滚。
 *      void addTransformer(ClassFileTransformer transformer, boolean canRetransform)
 *
 *      //相当于addTransformer(transformer, false)，也就是通过ClassFileTransformer实例重定义的类不能进行回滚。
 *      void addTransformer(ClassFileTransformer transformer)
 *
 *      //移除(反注册)ClassFileTransformer实例。
 *      boolean removeTransformer(ClassFileTransformer transformer)
 *
 *      //已加载类进行重新转换的方法，重新转换的类会被回调到ClassFileTransformer的列表中进行处理。
 *      void retransformClasses(Class<?>... classes)
 *
 *      //将某个jar加入到Bootstrap Classpath里优先其他jar被加载。
 *      void appendToBootstrapClassLoaderSearch(JarFile jarfile)
 *
 *      //将某个jar加入到Classpath里供AppClassloard去加载。
 *      void appendToSystemClassLoaderSearch(JarFile jarfile)
 *
 *      //获取所有已经被加载的类。
 *       Class[] getAllLoadedClasses()
 *
 *      //获取所有已经被初始化过了的类。
 *      Class[] getInitiatedClasses(ClassLoader loader)
 *
 *      //获取某个对象的(字节)大小，注意嵌套对象或者对象中的属性引用需要另外单独计算。
 *      long getObjectSize(Object objectToSize)
 *
 *      //判断对应类是否被修改过。
 *      boolean isModifiableClass(Class<?> theClass)
 *
 *      //是否支持设置native方法的前缀。
 *      boolean isNativeMethodPrefixSupported()
 *
 *      //返回当前JVM配置是否支持重定义类（修改类的字节码）的特性。
 *      boolean isRedefineClassesSupported()
 *
 *      //返回当前JVM配置是否支持类重新转换的特性。
 *      boolean isRetransformClassesSupported()
 *
 *      //重定义类，也就是对已经加载的类进行重定义，ClassDefinition类型的入参包括了对应的类型Class<?>对象和字节码文件对应的字节数组。
 *      void redefineClasses(ClassDefinition... definitions)
 *
 *      //设置某些native方法的前缀，主要在找native方法的时候做规则匹配。
 *      void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix)
 *
 * Javassist:特殊符合
 *      $0, $1, $2, ...     this和方法参数（1-N是方法参数的顺序）
 *      $args	            方法参数数组.它的类型为 Object[]
 *      $$	                所有实参。例如, m($$) 等价于 m($1,$2,...)
 *      $cflow(...)	        cflow 变量
 *      $r	                返回结果的类型，用于强制类型转换
 *      $w	                包装器类型，用于强制类型转换
 *      $_	                返回值
 *      $sig	            类型为 java.lang.Class 的参数类型数组
 *      $type	            一个 java.lang.Class 对象，表示返回值类型
 *      $class	            一个 java.lang.Class 对象，表示当前正在修改的类
 */
