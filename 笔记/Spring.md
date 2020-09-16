1. Ioc（Inversion of Control）与aop（Aspect Oriented Programming）：（是，内部，用处）1.ioc控制反转，是一种**设计思想**，就是将原本在程序中手动创建对象的控制权交由spring框架来管理。**Ioc容器**是spring用来实现 IoC 的载体， IoC 容器实际上就是个**Map**（key，value）,Map 中存放的是各种对象。Ioc可以很大程度上简化应用的开发，把应用从复杂的**依赖关系中解放出来**。当我们需要创建一个对象的时候，只需要配置好配置文件/注解即可，完全不用考虑对象是如何被创建出来的。  2.aop：面向切面编程，能够将那些**与业务无关**，却为业务模块所共同调用的逻辑或责任（例如事务处理、日志管理、权限控制等）**封装起来，便于减少系统的重复代码**，降低模块间的耦合度，并有利于未来的可拓展性和可维护性。Spring aop是基于动态代理的。3. Spring aop与AspectJ区别：Spring AOP 属于**运行**时增强，而 AspectJ 是**编译**时增强。 Spring AOP **基于代理(Proxying)**，而 AspectJ 基于**字节码操作**，AspectJ 应该算的上是 Java 生态系统中最完整的 AOP 框架了。**AspectJ 相比于 Spring AOP 功能更加强大**，但是 Spring AOP 相对来说更简单，当切面太多的话，最好选择 AspectJ ，它比Spring AOP 快很多。

2. @Controller与@RestController：1.单独使用@Controller的话一般使用在要返回一个**视图**的情况，这种情况属于比较传统的Spring MVC 的应用，对应于前后端不分离的情况。2.@RestController返回**json或xml数据**，@RestController**只返回对象**，对象数据直接以 **JSON 或 XML 形式**写入 HTTP 响应(Response)中，这种情况属于 RESTful Web服务，这也是目前日常开发所接触的最常用的情况（前后端分离），同时时用**@Controller+@ResponseBody**也可以实现@RestController

3. Spring中bean的作用域：**singleton**：唯一 bean 实例，Spring 中的 bean 默认都是单例的。  **prototype** : 每次请求（从context中获取）都会创建一个新的 bean 实例。  **request** : 每一次HTTP请求都会产生一个新的bean，该bean仅在当前HTTP request内有效。  **session** :同一个HTTP session共享一个bean，该bean仅在当前 HTTP session 内有效。 

4. 将一个类声明为spring的bean有哪些注解：@Component：通用的注解，可标注任意类为 Spring 组件。如果一个Bean不知道属于哪个层，可以使用@Component 注解标注  @Repository : 对应持久层即 Dao 层，主要用于数据库相关操作  @Service : 对应服务层，主要涉及一些复杂的逻辑，需要用到 Dao层。  @Controller : 对应 Spring MVC 控制层，主要用户接受用户请求并调用 Service 层返回数据给前端页面。

5. Spring mvc工作流程：1.客户端（浏·览器）发送请求，直接请求到 DispatcherServlet 2. DispatcherServlet 根据请求信息调用 HandlerMapping，解析请求对应的 Handler  3. 解析到对应的 Handler（也就是我们平常说的 Controller 控制器）后，开始由 HandlerAdapter 适配器处理（HandlerAdapter作用就是调用具体的方法对用户发来的请求来进行处理）  4. HandlerAdapter 会根据 Handler 来调用真正的处理器来处理请求，并处理相应的业务逻辑  5. 处理器处理完业务后，会返回一个 ModelAndView 对象，Model 是返回的数据对象，View 是个逻辑上的 View 6. ViewResolver 会根据逻辑 View 查找实际的 View  7. DispaterServlet 把返回的 Model 传给 View（视图渲染）  8.把 View 返回给请求者（浏览器）

6. 什么是spring boot starter：是一系列**依赖关系的集合**，因为它的存在，项目的依赖之间的关系对我们来说变的更加简单了。比如没有spring boot之前，开发web应用需要使用像Spring MVC，Tomcat和Jackson这样的库，这些依赖我们需要手动一个一个添加，但是，有了 Spring Boot Starters 我们只需要一个只需添加一个spring-boot-starter-web一个依赖就可以了。（jar包就是starter）

7. Spring boot嵌入式容器：tomcat，jetty，undertow。如何使用jetty而不是默认的tomcat：在maven中从starter-web中排除tomcat，然后添加jetty的依赖就可以了。

8. SpringBootApplication注解：@SpringBootApplication可以看作是@Configuration（允许在Context中注册额外的bean或导入其他配置类），@EnableAutoConfiguration（启用 SpringBoot 的自动配置机制）、@ComponentScan（扫描被@Component (@Service,@Controller)注解的bean，注解默认会扫描该类所在的包下所有的类） 注解的集合。

9. Cookie与session的使用：1.由于HTTP协议是无状态的协议，**所以服务端需要记录用户的状态时，就需要用某种机制来识具体的用户，这个机制就是Session**。典型的场景比如购物车，当你点击下单按钮时，由于HTTP协议无状态，所以并不知道是哪个用户操作的，所以服务端要为特定的用户创建了特定的Session，用于标识这个用户，并且跟踪用户，这样才知道购物车里面有几本书（购物车中的书都放在此用户的session中），**当用户第一次打开浏览器访问服务器的时候会创建session，默认有效期30分钟**  2. 思考一下服务端如何识别特定的客户？这个时候Cookie就登场了。每次HTTP请求的时候，客户端都会发送相应的Cookie信息到服务端。实际上大多数的应用都是用 Cookie 来实现Session跟踪的，第一次创建Session的时候，服务端会在HTTP协议中告诉客户端，**需要在 Cookie 里面记录一个Session ID，以后每次请求把这个会话ID发送到服务器，我就知道你是谁了**。有人问，如果客户端的浏览器禁用了 Cookie 怎么办？一般这种情况下，会使用一种叫做URL重写的技术来进行会话跟踪，即每次HTTP交互，URL后面都会被附加上一个诸如 sid=xxxxx 这样的参数，服务端据此来识别用户。（**cookie也可以用来保存用户的一些信息，如账号密码，网站设置等**），cookie默认有效时间为浏览器窗口关闭。

10. Cookie与Session的区别：1.cookie**只能存储阿斯克码**，假如需求存取Unicode字符或者二进制数据，需要**先进行编码**。Cookie中也不能直接存取Java对象。若要存储略微复杂的信息，运用Cookie是比较艰难的。  Session中能够存取**任何类型的数据**，包括而不限于String、Integer、List、Map等。Session中也能够直接保管Java Bean乃至任何Java对象等，运用起来十分便当 2.cookie存储在**客户端**，对客户端是可见的。 而Session存储在**服务器**上，对客户端是透明的，不存在**敏感信息泄露的风险**。所以cookie上的信息尽量都进行加密。 3.cookie不会占用**服务器资源**，session会占用。 

11. Spring boot的自动配置是如何实现的：

12. Spring中自定义注解：（https://juejin.im/post/5cf376e16fb9a07eee5eb6eb） 字段注解：通过@interface声明一个注解，@Target定义注解的使用位置（ElementType.FIELD，表示此注解只能被声明在一个类的方法前），@Constraint通过使用validatedBy来指定与注解关联的验证器，@Retention用来说明该注解类的生命周期（如RetentionPolicy.RUNTIME: 注解保留在程序运行期间，此时可以通过反射获得定义在某个类上的所有注解）。 类注解：也是@interface

13. Spring中单例bean的线程安全：在类中定义一个ThreadLocal成员变量，将需要的可变成员变量保存在 ThreadLocal 中。

14. Spring中循环依赖：导致程序一直循环调用，直至内存溢出。1.构造器的循环依赖：直接抛出异常  2.单例模式下的setter循环依赖：通过三级缓存处理 3.非单例循环依赖：无法处理。  构造器的循环依赖：bean创建过程中会把一个标识符记录在缓存中，创建完毕后会把标识符清除，如果bean在创建过程中发现自己已经在缓存中，则抛出异常。  单例模式下的setter循环依赖：首先，单例对象的初始化分为三步：实例化，属性填充，初始化。填充属性时，首先从一级缓存（singletonObjects）中获取对象，一级缓存没有获取到则二级缓存（earlySingletonObjects），还没有则三级缓存（singletonFactories），三级缓存获取到后则从三级缓存移除这个bean，并且放入二级缓存中。一个对象A在被实例化后还没有填充属性时，就会被提前放入到三级缓存中，这时如果这个对象依赖了对象B，则获取A对象时会获取对象B，因为B对象也依赖了对象A，所以B对象也要获取对象A，因为加入了三级缓存，A对象虽然还没有创建完，但是已经放入到三级缓存中，所以B对象可以成功获取到对象A。  来源：https://blog.csdn.net/u010853261/article/details/77940767

15. Spring注入方式：构造器注入，字段注入，方法参数注入（都是针对@Autowired注解）

16. IOC核心流程（https://zhuanlan.zhihu.com/p/104519509）：

    > 1. 容器构建启动入口：以web.xml的配置方式来说，首先我们在 web.xml 中配置 ContextLoaderListener 监听器，当 Tomcat 启动时，会触发 ContextLoaderListener 的 contextInitialized 方法，从而开始 IoC 的构建流程。另一个常用的参数是 contextConfigLocation，用于指定 Spring 配置文件的路径。
    > 2. ApplicationContext 刷新前配置：A. 确定要使用的容器，通常使用的是：XmlWebApplicationContext，如果是用 Spring Boot，一般是 AnnotationConfigApplicationContext，但其实都差别不大，最终都会继承 AbstractApplicationContext，核心逻辑也都是在 AbstractApplicationContext 中实现。B. 提供一个给开发者初始化 ApplicationContext 的机会
    > 3. 初始化 BeanFactory、加载 Bean 定义：A. 创建一个新的 BeanFactory，默认为 DefaultListableBeanFactory。  B. 根据 web.xml 中 contextConfigLocation 配置的路径，读取 Spring 配置文件，并封装成 Resource。  C. 根据 Resource 加载 XML 配置文件，并解析成 Document 对象 。 D. 从根节点开始，遍历解析 Document 中的节点。 D1. 对于默认命名空间的节点：先将 bean 节点内容解析封装成 BeanDefinition，然后将 beanName、BeanDefinition 放到 BeanFactory 的缓存中，用于后续创建 bean 实例时使用。  D2. 对于自定义命名空间的节点：会拿到自定义命名空间对应的解析器，对节点进行解析处理
    > 4. 触发 BeanFactoryPostProcessor：实例化和调用**所有**（包括人为定义的，只要实现了BeanFactoryPostProcessor接口） BeanFactoryPostProcessor，包括其子类 BeanDefinitionRegistryPostProcessor。 BeanFactoryPostProcessor 接口是 Spring 初始化 BeanFactory 时对外暴露的扩展点，Spring IoC 容器允许 BeanFactoryPostProcessor 在容器实例化任何 bean 之前读取 bean 的定义，并可以修改它（此接口的方法传入了beanfactory，我们可以在beanfactory中获取beandefinition并做一些修改）。  BeanDefinitionRegistryPostProcessor 继承自 BeanFactoryPostProcessor，比 BeanFactoryPostProcessor 具有更高的优先级，主要用来在常规的 BeanFactoryPostProcessor **激活之前注册**一些 bean 定义。特别是，你可以通过 BeanDefinitionRegistryPostProcessor 来注册一些常规的 BeanFactoryPostProcessor，因为此时所有常规的 BeanFactoryPostProcessor 都还没开始被处理。
    > 5. 注册 BeanPostProcessor：注册所有的 BeanPostProcessor，将所有实现了 BeanPostProcessor 接口的类加载到 BeanFactory 中。BeanPostProcessor 接口是 Spring 初始化 bean 时对外暴露的扩展点，Spring IoC 容器允许 BeanPostProcessor 在容器初始化 bean 的前后，添加自己的逻辑处理。在这边只是注册到 BeanFactory 中，具体调用是在 bean 初始化的时候。   beanpostprocessor中的两个方法，允许我们在bean初始化前后进行逻辑处理
    > 6. 实例化所有剩余的非懒加载单例 bean：遍历所有被加载到缓存中的 beanName，触发所有剩余的非懒加载单例 bean 的实例化。首先通过 beanName 尝试从缓存中获取，如果存在则跳过实例化过程；否则，进行 bean 的实例化。根据 BeanDefinition，使用构造函数创建 bean 实例。根据 BeanDefinition，进行 bean 实例属性填充。执行 bean 实例的初始化。触发 Aware 方法。触发 BeanPostProcessor 的 postProcessBeforeInitialization 方法。如果 bean 实现了 InitializingBean 接口，则触发 afterPropertiesSet() 方法。如果 bean 设置了 init-method 属性，则触发 init-method 指定的方法。触发 BeanPostProcessor 的 postProcessAfterInitialization 方法。将创建好的 bean 实例放到缓存中，用于之后使用。
    > 7. 完成上下文的刷新：使用应用事件广播器推送上下文刷新完毕事件（ContextRefreshedEvent ）到相应的监听器。我们可以创建一个自定义的监听器，实现ApplicationListener接口即可。

17. sdf

18. sdf

19. sdf