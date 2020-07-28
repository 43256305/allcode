1. Mybatis中$/#的区别：**$是静态文本直接替换，#是参数占位符**，mybatis会将#{}替换成问号，在 sql 执行前会使用 PreparedStatement 的参数设置方法，按序给 sql 的?号占位符设置参数值。  Mybatis传递参数用@Param注解

2. Mybatis的动态sql：以标签的形式编写动态 sql，完成逻辑判断和动态拼接 sql 的功能。Foreach/if/choose/when等

3. Springboot中mybatis集成分页插件（pagehelper）：首先在pom中引入，然后修改配置文件，再执行PageHelper.startPage(pageNo,pageSize);就可以了。