1. Mybatis中$/#的区别：**$是静态文本直接替换，#是参数占位符**，mybatis会将#{}替换成问号，在 sql 执行前会使用 PreparedStatement 的参数设置方法，按序给 sql 的?号占位符设置参数值。  Mybatis传递参数用@Param注解

2. mybatis与springboot集成配置：在application.properties中通过mybatis.config-location配置mybatis配置文件的位置，通过mybatis.mapper-locations配置mapper文件的位置。

3. mybatis使用xml还是注解？简单 SQL 可以用注解开发，如果是一些有诸如条件判断类的需求的 SQL 还是要写在 xml 文件中。

4. https://mybatis.org/mybatis-3/zh/index.html

5. 每个基于 MyBatis 的应用都是以一个 SqlSessionFactory 的实例为核心的。SqlSessionFactoryBuilder 则可以从 XML 配置文件或一个预先配置的 Configuration 实例来构建出 SqlSessionFactory 实例。xml中包含了mybatis的核心设置，如数据源，事务管理器等。我们可以从SqlSessionFactoryBuilder中获得 SqlSession 的实例。SqlSession 提供了在数据库执行 SQL 命令所需的所有方法。可以通过 SqlSession 实例来直接执行已映射的 SQL 语句。

6. 作用域与生命周期：

   > 1. SqlSessionFactoryBuilder：这个类可以被实例化、使用和丢弃，一旦创建了 SqlSessionFactory，就不再需要它了。 因此 SqlSessionFactoryBuilder 实例的最佳作用域是方法作用域（也就是局部方法变量）。 
   > 2. SqlSessionFactory：一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例。 SqlSessionFactory 的最佳作用域是应用作用域，即整个应用都可以使用。
   > 3. SqlSession：每个线程都应该有它自己的 SqlSession 实例。SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。每次收到 HTTP 请求，就可以打开一个 SqlSession，返回一个响应后，就关闭它。

7. mybatis配置

   > 1. 属性：如username，password等
   > 2. 设置：开启缓存，开启驼峰命名自动映射等。
   > 3. 别名：通过<typeAliases>配置java类全限定名的别名，或者@Alias注解。java内置的别名：byte  _byte，long _long，String string，Long long，List list等。
   > 4. 类型处理器：数据库列到java类的映射处理。可以自定义。枚举类型的映射。
   > 5. 对象工厂：mybatis创建对象时，他都会使用一个对象工厂实例来完成实例化工作，可以自定义对象工厂。
   > 6. 插件：mybatis允许你在映射语句执行过程中的某一点进行拦截调用，允许拦截的方法包括：Executor,ParameterHandler,ResultSetHandler,StatementHandler。我们需要实现Interceptor接口，并制定想要拦截的方法签名即可。
   > 7. 环境配置：可以配置开发，测试等多种环境，但每个SqlSessionFactory只能选择一种环境。通过<environments>标签配置多个环境，每个环境需要配置数据源，事务管理器（mybatis中的事务管理器会被spring的覆盖，所以是无用的）。
   > 8. 数据库厂商标识（databaseIdProvider）
   > 9. 映射器（mappers）：<mappers>标签配置mappers的路径。

8. Select映射器属性：

   > 1. id：此select语句的标志符
   > 2. parameterType：传入参数的类型，可选，mybatis可以根据类型处理器自己判断
   > 3. resultType：返回结果类型，如果返回的为集合则应该设置为集合包含的类型，resultType与resultMap只能使用一个
   > 4. resultMap：对外部resultMap的命名引用
   > 5. flushCache：只要语句被调用，都会导致本地缓存和二级缓存被清空，默认false
   > 6. useCache：导致本条语句的结果被二级缓存缓存起来，select默认为true

9. Insert，Update，Delete属性：

   > 1. id,parameterType,FlushCache
   > 2. useGeneratedKeys：仅适用于insert与update，返回自动生成的主键，默认false

10. Sql：用来定义可重用的SQL代码片段， 参数可以静态地（在加载的时候）确定下来，并且可以在不同的 include 元素中定义不同的参数值。

11. resultMap：此标签把java类属性与表列名一一映射。也可以不使用resultMap，而是在sql语句中使用as为列起别名。

    > 属性：
    >
    > 1. id：resultMap标识符
    > 2. type：对应要映射的类
    > 3. autoMapping：开启自动映射
    >
    > 子标签：
    >
    > 1. id与result：表中一列与某个java类属性的映射。id对应的属性会被标记为对象的标识符。
    >
    >    属性：
    >
    >    1. property，column，typeHandler
    >    2. javaType：java类型，如果映射到一个javaBean，通常可以自己检测，如果映射的是HashMap，则必须指定。
    >    3. jdbcType：在可能执行插入、更新和删除的且允许空值的列上指定 JDBC 类型。如果不指定，在为空值时会报1111错误。（常用）支持的JDBC类型有：BIT,FLOAT,BIGINT等。
    >
    > 2. constructor：通过构造方法设置属性值。
    >
    > 3. association：has-a关系，如人这个类包含电脑类。两种方法加载关联：嵌套Select查询或者嵌套结果映射。
    >
    > 4. collection：人有多个电脑。

12. 自动映射：可以混合使用自动映射与手动映射，手动映射没有的列，则使用自动映射。默认的自动映射等级为：对association以外的属性自动映射。

13. 缓存：默认情况下，只启用了本地的会话缓存，它仅仅对一个会话中的数据进行缓存。 要启用全局的二级缓存，只需要在你的 SQL 映射文件中添加一行：<cache/>。缓存的作用：映射语句文件中的所有 select 语句的结果将会被缓存，映射语句文件中的所有 insert、update 和 delete 语句会刷新缓存，缓存会使用最近最少使用算法（LRU, Least Recently Used）算法来清除不需要的缓存，缓存不会定时进行刷新，缓存会保存列表或对象（无论查询方法返回哪种）的 1024 个引用。  这些属性可以通过cache标签来修改。

14. 动态SQL：

    > 1. if：用if判断传入的某个值，决定是否拼接sql语句
    > 2. choose,when,otherwise：相当于switch语句，满足直接退出，都不满足则otherwise
    > 3. trim,where,set：使用if拼接时，如何处理where/and呢？要不要放入if中呢？很容易导致语法错误。而使用where标签，我们不用写where，他会自动帮我们拼接where，或者去除多余的and。也可以通过自定义的trim来定制where的功能。set标签用于update语句中，自动判断set出现的位置，处理逗号。
    > 4. foreach：遍历传入的集合（list，map，set），根据集合拼接sql
    > 5. script：在使用注解的@Select/@Update等标签中使用动态SQL，需要使用script包裹

15. sdf

16. sdf

### mybatisplus使用

1. 注解：https://mp.baomidou.com/guide/#%E7%89%B9%E6%80%A7

   > 1. @TableName：表名注解
   > 2. @TableId：主键注解，type属性可以设置自增，分配UUID，雪花算法等。
   > 3. @TableField：字段注解，可以设置更新插入的非空判断，字段自动填充等
   > 4. @Version：乐观锁注解（mysql实现乐观锁就是根据数据的版本标识，版本与原来的版本一致则可以更新）
   > 5. @TableLogic：逻辑处理注解
   > 6. @InterceptorIgnore：插件如动态表名，分表等。

2. 代码生成器：自动生成controller，entity，service，mapper。复制官网的代码，修改数据库，包等相关信息即可。注意引入正确的包，要不然会报错。

3. mapper要继承BaseMapper，Service接口要继承IService，service实现类要继承ServiceImpl。BaseMapper包括：insert，delete，update，select等方法。IService包括：save，remove，update，get，list，page，count等方法。

4. 分页插件：需要配置PaginationInterceptor的@Bean，使用时创建Page类传入当前页数，每页大小即可返回携带了数据的IPage<T>对象。

5. 条件构造器：AbstractWrapper，拥有两个子类：QueryWrapper与UpdateWrapper。用于生成sql的where条件。包括以下方法：allEq，eq，ne，gt，between，like，isNull，orderBy，having，exist等方法。QueryWrapper自身的entity属性也用于生成where条件