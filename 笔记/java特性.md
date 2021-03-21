## jdk8

1. Lambda表达式：https://zhuanlan.zhihu.com/p/92687444

   > 1. 特性：
   >    - java8引入lambda表达式，使原本需要用匿名类实现接口来传递行为，现在通过lambda可以更直观的表达。
   >    - lambda表达式的本质是一个语法糖，由编译器推断并帮我们转换为常规代码。
   >    - lambda表达式是一个匿名函数，有些函数如果只是临时用，并且业务逻辑也很简单，就可以使用lambda
   >    - lambda允许把函数作为一个方法的参数
   >    - lambda主要用来定义一个简单方法接口，任何一个只包含一个方法的接口（即**函数式接口**），我们都可以用lambda来表示。自定义函数式接口时，我们应当在接口前加上@FunctionalInterface注解（虽然不加也不会有错误），加了注解后，编译器注意到接口中定义了多个方法时会报错。
   >    - 只能访问局部对应的外部区域的局部final变量（局部非final变量只能访问不能修改），以及成员变量和静态变量。
   >    - lambda通过表达式所在的上下文来进行目标类型推断，通过检查lambda表达式的入参类型及返回类型，和对应的目标类型的方法签名是否一致，推导出合适的函数接口。
   >    - 还可以用**方法引用**来表示lambda，方法引用是用来直接访问类或者实例的**已经存在**的方法或构造方法。java8允许我们通过"::"关键字获取方法或者构造器函数的引用。如下面compareTo是String类型已经实现的方法。
   > 2. 表达：(parameters)->expression   或  (parameters)->{statements;}  多条语句时才需要用大括号。  例子：Comparator<String> comparator = (a,b)->a.compareTo(b);     方法引用：Collections.sort(s, String::compareTo);

2. Stream API：

   > 1. 特性：
   >    - 对容器对象功能的增强。专注于对容器对象进行各种非常便利，高效的聚合操作或者大批量数据操作。同时，它提供串行和并行两种模式，并发模式能够充分利用多核处理器的优势，使用fork/join并行方式来拆分任务和加速处理过程。
   >    - 与迭代器不同，Stream可以并行处理，即数据会被分成多个端，其中每一个都在不同的线程中处理，然后将结果一起输出。
   >    - 数据源本身可以是无限的。
   >    - 三种操作：intermediate：一个流可以后面跟随零个或多个intermediate操作，主要目的是打开流，做数据映射/过滤操作，然后返回一个新的流，交给下一个操作使用。Terminal：一个流只能有一个terminal操作，当这个操作执行后，流就被用光了，无法在被操作。short-circuiting（短路）：接受数据量大的Stream时，返回一个数据量小的Stream
   >    - intermediate转换操作都是lazy的，多个转换操作只会在Terminal操作的时候融合起来，一次循环完成。
   >    - 虽然可以并行操作，但是每次转换都是有次序的，即List.stream().map().limit()一定是全部完成了map操作，形成了一个新的流才进行limit操作。
   > 2. Stream生成方式：
   >    - 从Collection和数组获得：如Collection.stream()，Collection.parallelStream()，Arrays.stream
   >    - 从BufferedReader中获得：java.io.BufferedReader.lines()
   > 3. intermediate操作：
   >    - map：将容器中每个元素都经过某个函数的转换。如List.stream().map(n->n*n).collect(Collectors.toList())把List中的元素都开平方，因为intermediate操作返回的是一个Stream，所以需要转换成list。
   >    - flatmap：将包含容器的容器，即二维结构（或多维），展开为一维结构。如将Stream<String[]>变为Stream<String>。("k,j","p,h").map(a->a.split(",")).flatMap(Arrays::stream)，变为一个包含4个元素k,j,p,h的容器，其中因为容器中的容器为数组，所以用Arrays::stream函数，如果为list，则Collection::stream。
   >    - filter：过滤。如List.stream().filter(n->n%2 == 0)留下偶数。
   >    - peek：与forEach相同，但是是intermediate操作。
   >    - distinct：去除重复元素。
   >    - limit，skip：取前几个元素，跳过前几个元素。
   > 4. Terminal操作：
   >    - forEach：接受一个lamdba表达式，对每一个操作执行该表达式。如List.stream().forEach(p->System.out.println(p))打印List每个元素。
   >    - findFirst：返回第一个元素或者空值，它的返回类型为Optional。用于避免空指针异常。
   >    - reduce：需要提供一个起始种子，然后依照运算规则，和容器的第一个，第二个，第n个元素组合。如List.stream().reduce(0,(a,b)->a+b)。0为初始值。可以用此方法完成字符串拼接，数值的sum，min，max，average。
   >    - allMatch/anyMatch/noneMatch：所有的/有一个/没有 元素都匹配时返回true，如boolean flag = List.stream().allMatch(p->p>10)；所有元素都大于10时flag为true，一旦某个元素不大于10，则立刻返回false。
   >    - collect：把流变为容器。
   >    - min，max
   > 5. short-circuiting：
   >    - limit：取前几个元素。如List.stream().map().limit(10)，此时map操作只会进行10次，即容器的前10个元素，因为后面的元素不是我们需要的所有没必要映射。但是，当limit放置在sorted排序后时，不能达成short-circuiting的目的，因为数据的正确性会被破坏。
   >    - findFirst，allMatch/anyMatch/noneMatch























