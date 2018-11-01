/**
 * Created by remote on 2017/9/28.
 *
 * ClassLoader 类加载器
 *
 * BootStrap ClassLoader  启动类加载器 不是一个普通的java类，底层是c++编写，嵌入到jvm中，岁jvm启动而启动加载 ，加载java核心类类库，并构造Extension ClassLoader和App ClassLoader类加载器
 * Extension ClassLoader  扩展类加载器 加载jre/lib/ext/下的jar
 * App ClassLoader  称为系统类加载器 加载应用程序classpath目录下的所有jar和class文件
 *注： 除 BootStrap ClassLoader 以外，其他类加载器都包含一个父类加载器的引用，这个引用实际在 ClassLoader中定义【parent】，在ExtClassLoader 实例时，并没有parent 实际上是 null
 * AppClassLoader实例时 parent 是ExtClassLoader。
 * 但由于 在ClassLoader中的loadClass(String name, boolean resolve) 当 parent == null时，会执行 findBootstrapClassOrNull -》 findBootstrapClass 方法，
 * 也就是使用jvm内置的加载器去加载，也就是Bootstrap ClassLoader，这也解释了ExtClassLoader的parent为null,但仍然说Bootstrap ClassLoader是它的父加载器。
 *
 *双亲委托:
 *
 * 当一个ClassLoader实例需要加载某个类时，它会试图亲自搜索某个类之前，先把这个任务委托给它的父类加载器，这个过程是由上至下依次检查的，
 * 首先由最顶层的类加载器Bootstrap ClassLoader试图加载，如果没加载到，则把任务转交给Extension ClassLoader试图加载，如果也没加载到，
 * 则转交给App ClassLoader 进行加载，如果它也没有加载得到的话，则返回给委托的发起者，由它到指定的文件系统或网络等URL中加载该类。
 * 如果它们都没有加载到这个类时，则抛出ClassNotFoundException异常
 *
 * JVM在判定两个class是否相同时，不仅要判断两个类名是否相同，而且要判断是否由同一个类加载器实例加载的。只有两者同时满足的情况下，JVM才认为这两个class是相同的
 *
 * ClassLoader
 *
 * loadClass() // 双亲委托加载类逻辑
 * findLoadedClass() // 判断类是否加载，加载返回类对象，没有返回null
 * findClass()  // 一般自定义ClassLoader 重写此方法 实现自定义加载class 使用双亲委派 ，不使用就直接重写 loadClass 即可
 * defineClass() //将二进制内容转换成class对象
 *
 *
 * 探究对象加载（初始化）顺序
 * class 被使用时 首先
 *  先加载 class , 会执行 静态代码块 初始化静态变量，按父类、子类 ，同一级别的代码的先后顺序执行
 *   父类静态变量，父类静态代码块 子类静态变量，子类静态代码块，
 *  创建对象时：
 *   父类的成员变量 代码块 构造函数 子类的成员变量 代码块 构造函数
 *
 * 父类静态变量-》父类静态方法-》子类静态变量-》子类静态方法 -》 父类构造方法 -》 子类构造方法
 * 注: 变量 和 代码块 按代码的先后顺序初始化
 *
 */
package cn.peng.studygodpath.java8.sourcecode.classLoad;