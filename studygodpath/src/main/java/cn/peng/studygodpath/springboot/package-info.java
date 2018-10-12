/**
 * Created by remote on 2018/3/2.
 * Spring boot
 * 新建项目
 * 1. 空白maven项目
 * 2. <partner>标签 pom文件 继承 spring-boot-starter-parent
 * 3. <build><plugins> 添加spring boot编译插件  spring-boot-maven-plugin
 * 4. 依赖添加 spring-boot-starter-web、spring-boot-starter-test
 * 5. 控制器类加 @RestController 、@SpringBootApplication 注解
 * 6. 控制器中加入main方法，SpringApplication.run(FirstController.class, args)，spring boot的入口类启动
 *
 *@SpringBootApplication spring boot 核心注解类，开启自动配置
 *  @SpringBootConfiguration  SpringBoot项目的配置注解
 *  @EnableAutoConfiguration  启用自动配置，该注解会使SpringBoot根据项目中依赖的jar包自动配置项目的配置项 eg:添加了spring-boot-starter-web的依赖，项目中也就会引入SpringMVC的依赖，SpringBoot就会自动配置tomcat和SpringMVC
 *  @ComponentScan 默认扫描@SpringBootApplication所在类的同级目录以及它的子目录。
 *
 *  1.5之前的 可以将配置文件和bean一一对应 使用注解 @ConfigurationProperties 的 prefix 和 locations 属性
 *  之后的虽然也可以 (见 first 中的 projiectSetting) 但官方提供的方式的是：实现 ApplicationListener<ApplicationEnvironmentPreparedEvent>
 *  对环境属性的事件监听 将配置文件加入到 Environment 中，在应用启动的时候加入监听类，通过 Environment 对象获取配置文件中的数据（见 first 中的 projiectSetting2）
 *
 *
 * spring boot 连接数据库
 *  @Entity 标记 该类是实体类
 *  @Table 指定数据库中的表名
 *  @Id 定义表的唯一标识
 *  @GeneratedValue 将属性设置为自动生成
 *  @DateTimeFormat 时间日期类型对象必须用该注解进行格式化，以保证来回转换的正确性
 *  @ManyToOne 指定实体类与被注解实体类对象的多对一关系
 *  @ManyToMany 多对多
 *  @JoinTable 多对多的情况下 使用中间表存储各自的唯一标示
 *  @JoinColumn 指定两个对应关系的实体类的外键关联 name 指定外键的列名
 *  @JsonBackReference 防止关系对象的递归访问？
 *  @EnableTransactionManagement 启用jpa的事务管理功能
 *  @EnableJpaRepositories(basePackages = "cn.peng.studygodpath.springboot.data.**.repository") 启用jpa资源库 并指定位置
 *  @EntityScan(basePackages = "cn.peng.studygodpath.springboot.data.**.entity") 指定实体类的位置
 *
 *
 * spring boot 环境单元测试
 *  @RunWith(SpringRunner.class)
 *  @SpringBootTest
 *  SpringRunner 是继承与 SpringJUnit4ClassRunner
 *
 */
package cn.peng.studygodpath.springboot;