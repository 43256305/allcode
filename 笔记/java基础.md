1. overriding与overloading（返回值，参数），枚举（用法，定义：类中，接口中，自定义），hashcode（equels：String，Integer，对象与hashcode，线程），数组（初始化，二维数组，使用：Arrays），常量（类/方法，变量：成员/局部/String/static final（编译），对象），正则表达式（Matcher：类不能为私有，构造方法可以。Pattern与Matcher。Replace。//w），String（相加（变量，常量），常量池（不可变对象）与堆，intern，build与buffer，方法：转换，截取，定位），基本数据类型（大小（浮点，char），字符串格式化：printf/format，dowhile），变量（初始化：局部/成员/数组/对象，类型转换（基本，String），大数，移位/&/^/|）,包装类（String：final/+，是否有new/范围，常量池（4），+与拆箱），函数（传参（基本类型/对象），可变参数），时间（SimpleDateFormat，Date与Calender），static（父子加载，类加载，静态常量不加载类），反射（创建对象/数组，获取变量/方法，调用方法），继承（子类初始化父类必定初始化，子类调用父类static，子类不加载），异常（继承关系，抛出/声明异常，执行语句，finally/return顺序，自定义异常），接口内部类（接口中的static：变量/方法，静态/非静态内部类：资源访问，创建，匿名/局部），范型（声明，extends，Class<T>，）

2. 范型的理解和使用场景：在没有用范型时，想要实现一个通用的，可以处理不同类型的方法，需要使用Objec类强转：1.每次使用时**都需要强制转换**成想要的类型2.在编译时编译器并不知道类型转换是否正常，运行时才知道，**不安全**。而使用范型的好处：1.类型**安全**，写编译时就能发现错误 2.**消除强制类型转换**

3. 反射的机制与作用：反射机制允许程序在**执行时动态获取**某个类的各种信息，也可以实现动态**创建**类的对象、**变更**属性的内容或执行特定的方法等功能，从而增强了程序的灵活性与可移植性。反射机制的作用：1.当需要使用的类还没写出来时，可以用反射而不是import，避免了编译期间报错。 2.**解耦**，可以把类名字放置在配置文件中，要修改时只需要修改配置文件即可，而不用修改代码。3.**idea**中的方法属性提示，就是运用反射获取方法属性的例子。

4. 反射缺点：1.性能问题，要慢于直接调用  2.降低代码可读性，维护比较难  3.内部属性暴漏，反射能访问私有属性和方法，可能带来一些问题。

5. 多态：（存在继承关系，子类重写父类方法，父类引用指向子类实例）一个引用变量到底会指向哪个类的**实例对象**，该引用变量发出的**方法调用**到底是哪个类中实现的方法，必须在由程序运行期间才能决定。从而实现**不修改程序代码就可以改变程序运行时所绑定的具体代码**，让程序可**以选择多个运行状态**，这就是多态性。

6. 引用拷贝：引用之间直接赋值，导致两个引用指向的对象地址相同，即同一个对象 对象拷贝：调用clone方法，两个引用指向的对象不同（浅拷贝：对象中的变量拷贝，对象中的对其他对象的引用共享  深拷贝：对象中的变量拷贝，对象中的引用所指向的对象也深拷贝，消耗最大）。

7. 什么是面向对象：**对象包括属性与行为**，面向对象就是把构成问题的事物分解成一个个对象，建立对象**不是为了实现一个步骤**，而是为了**描述**某个事物在解决问题中的行为。**程序是对象的集合**，它们通过**发送消息**来告知彼此所要做的。也就是说：以对象为中心，以消息(发送消息即为函数调用)为驱动。

8. 面对对象的四个特性：封装（核心思想就是“隐藏细节”、“数据安全”，将对象不需要让外界访问的成员变量和方法私有化，只提供符合开发者意愿的公有方法来访问这些数据和逻辑，保证了数据的**安全和程序的稳定**），继承（从一般到特殊，是一种扩展关系），多态，抽象（抽象就是将一类实体的共同特性抽象出来，封装在一个新的概念(类) 中）

9. Java序列化和反序列化：序列化就是将对象写入到IO流中。序列化机制允许将实现序列化的Java对象转换为**字节序列**，这些字节序列可以保存在磁盘上，或通过网络传输，以达到以后恢复成原来的对象。序列化机制使得对象可以脱离程序的运行而独立存在。使用场景：网络上传输的对象都必须序列化（Controller传递的对象不是序列化，而是转化为json）。（Serializable只是一个标记接口，没有方法） （ArrayList的数组虽然是Transient，但是因为他实现了writeObject/readObject方法，此方法是自定义序列化，可以选择序列化内容，或者加密序列化）

10. Java中util包和sql包中都有Date类，其中，sql包中的Date继承自util包中的Date。

11. Java中数组构造：Java中的所有数组都是通过Array类实例化。Array类没有public的构造方法，数组是通过Array的newInstance()方法进行实例化。静态初始化：声明时就用{}赋值了，动态初始化：只声明大小，那个位置有什么元素后来赋值。数组的引用存储在栈中，数组对象存储在堆中（数组中存储基本类型也是存储在堆中，因为数组本身就是对象）。

12. Finalize方法：他在对象被垃圾回收前被调用。不建议用它来清除回收对象，建议用于某些非内存资源的释放，如socket，文件等。可以通过调用System.gc()来手动回收垃圾，但jvm并不保证一定会GC，执行GC也不保证一定执行finalize方法。执行finalize的过程：当对象变成不可达时，判断此对象是否覆盖了finalize方法，没有覆盖则直接回收，覆盖了，并且从未执行finalize方法，则放入队列，等待低优先级线程执行，执行完成后若还不可达，则回收。

13. Java中异常：所有异常都继承自Throwable，有两种异常：Error（系统错误，我们无法解决，如栈溢出）Exception，其中Exception又分为非运行时异常：IOExeception/ClassNotFoundException和运行时异常：RunTimeException。非运行时异常需要我们手动catch。运行时异常则不用，如1/0系统会帮我们抛出。

    重写equels方法：1.自反性（x.equels(x)一定为true） 2.对称性（x.equels(y)为true，则y.equels(x)也为true）  3.传递性  4.一致性（x，y只要没有修改，多次调用equels方法返回的值都一样）  5.非空性（x.equels(null)一定为false） 6.重写equels最好重写hashcode方法，否则两个等价对象可能产生不同hashcode，这在集合框架中可能产生严重后果。