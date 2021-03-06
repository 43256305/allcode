### 简介

1. 线程的优势：发挥多处理器的强大能力，建模的简单性，异步事件的简化处理，响应更灵敏的用户界面    风险：安全性问题，活跃性问题，性能问题

### 线程安全性

1. 什么是线程安全性：当一个或多个线程访问某个类时，这个类始终能表现出正确的行为，则这个类是线程安全的

2. 为什么会发生线程安全问题：编译器，硬件都可以随意安排操作的执行时间和顺序，并且其他线程都不能适时看到操作的结果

3. 编写线程安全代码的核心：在于对状态（即类的属性），特别是共享与可变的变量的访问操作进行管理

4.  确保线程安全的三个方法

   不在线程间共享状态变量（所以封装有利于实现线程安全）

   将状态变量修改为不可变变量

   在访问状态变量时使用同步

   所以除非确实要共享的域，要不然都使用private修饰，除非确实要修改的域，要不然都用final修饰。可以简化对变量的分析，因为声明为final的变量肯定是不可变的，在分析线程安全是可以不予考虑

### 使用同步确保线程安全性

1. 同步包括什么：保证可见性，保证原子性，保证有序性
2. 竞态条件：某个计算的正确性取决于多个线程交替执行的时序时，就会发生竞态条件
3. 原子性：操作的几个步骤不可分割（原子类如AtomicLong可以保证某个操作的原子性，但无法保证复合操作的原子性）
4. 加锁：能保证复合操作的原子性，java提供的加锁关键字：synchronized（可重入锁），当给某个变量加锁时，要确保写入访问都加同一个锁（为什么访问也要加锁呢？访问时加锁能保证变量的可见性）。同步块大小要适合，要确保安全性，简单性，性能
5. 可见性：我们无法确保执行读操作的线程能适时看到其他线程写入的值
6. volatile：保证可见性以及禁止指令重排序（如书上循环的例子，可能优先读取到第二个修改的值）。
7. 注意64位数据（如double与long）读取是两步进行，所以保证可见性（加锁或者volatile）。**double与long的赋值都不是原子操作，其他类型赋值包括引用类型是原子操作。**

### 通过不共享变量保证线程安全

1. 名词解释：

   发布对象：指对象能够在当前作用域之外的代码中使用。发布有显式发布（如public对象）与隐式发布（发布的数组中的对象）

   逸出：当某个不该发布的对象被发布，称之为逸出。注意别在构造中使this逸出，如在构造中启动一个线程。

2. 线程封闭：

   Ad-hoc线程封闭：由程序设计来实现。（不推荐）

   栈封闭：方法中的变量。

   ThreadLocal：线程独有对象。

### 通过不可变性保证线程安全

1. 不可变对象：对象创建以后不能修改，所有域都是final类型，创建期间没有逸出this。不可变对象一定是线程安全的。

### 安全发布

1. 不正确的发布：因为对象生成时也存在可见性问题，可能使得这个对象被使用时还没有创建完成（虽然执行完了constructor，但是值还没有真正写入对象的域或者这个对象的引用还是指向空，还没有真正更新引用）。
2. java为不可变对象提供了一种机制：任何线程都可以在不需要额外同步的情况下安全的访问不可变对象，即使在发布这些对象发布时没有使用同步。（即解决上面的问题，但是如果对象的域是可变对象，那么在访问这些域时仍需要同步）
3. 安全发布：可变对象通常必须通过安全的方式发布，可以通过以下几种方式安全发布：1.在静态初始化函数中初始化一个对象引用，2.将对象的引用保存到volatile类型的域或者AtomicReferance对象中，3.将对象的引用保存在某个正确构造对象的final类型域中，4.将对象引用保存到一个由锁保护的域中（所以放在线程安全容器中可以确保安全发布）
4. 事实不可变对象：不是不可变对象，但是使用时不会改变他。安全发布事实不可变对象后，任何线程都可以在没有额外同步的时候使用它。

### 对象的组合(通过组合模式使一个类成为线程安全的)

1. 实例封闭：安全地使用非线程安全对象。即被使用对象只能在使用它的对象的内部使用，不能逸出到外面。例如Collections把ArrayList封装为线程安全的。
2. java监视器模式：用对象的锁来保护此对象的所有可变域的访问，为什么不使用公有的锁：使用公有的锁时，检查安全问题需要检查整个程序。
3. 线程安全的委托：对象的域都是线程安全的，那么此对象需要加锁吗？看对象的域之间是否有不变性条件（即是否会相互影响），如果会就需要加锁。
4. 变量的发布：对象的某个变量是线程安全的，并且没有任何不变性条件，变量的操作也没有不正确的状态转换，则可以安全地发布此变量。
5. 在现有的安全类中添加功能：扩展类加锁（通过继承，在使用子类的锁）与客户端加锁（把原对象变为它的域，使用此域的锁）与组合（实现同一个接口，把原对象变为它的域，再使用子类的锁 相比前两者更好）

### 基础构建模块

通过线程安全的委托实现线程安全，介绍java类库中的线程安全的实用类

1. 同步容器类：hashtable，vector等直接用syn封装的类。他们使用迭代器（或者foreach，任何实现iterable接口的类都可以用于foreach）时修改会产生ConcurrentModificationException。

2. 并发容器：ConcurrentHashMap，ConcurrentMap接口，CopyOnWriteArrayList

3. 阻塞队列：

   - BlockingQueue的几种实现：LinkedBlockingQueue，ArrayBlockingQueue，PriorityBlockingQueue，SynchronousQueue。，take/put与offer/poll区别：不能加入时take会一直阻塞直到加入，offer会返回一个失败状态。

   - Deque与BlockingDeque：双端队列，在队头与队尾插入与移除。工作密取：每个消费者都有各自的双端队列，如果一个消费者完成了自己队列的全部工作，那么他可以从其他消费者队列末尾获取工作。适用于既是消费者又是生产者问题，可以确保线程不等待。

   - 阻塞状态：BLOCKED,WAITING,TIMED_WAITING 阻塞方法都会抛出InterruptedException，处理此异常：1.向上传递该异常 2.捕获异常并使用interrupt()方法恢复中断

   - 生产者消费者模式：通过阻塞队列解耦生产者与消费者（类似消息队列）

4. 同步工具类：CountDownLatch，FutureTask，Semaphore，CyclicBarrier

###  线程池：任务执行

1. 为什么使用线程池？相比穿行执行加强性能，缩短响应时间，相比直接使用多线程更利于管理线程。
2. 几种线程池：newFixedThreadpool，newCachedThreadPool，newSingleThreadPool，newScheduledThreadPool
3. ExecutorService接口管理Executor生命周期，三种状态：运行，关闭，已终止。shutdown：不接受新任务，等待已经接受的任务完成。 shutdownNow：尝试取消正在运行的任务（通过中断正在执行的线程，如果线程并不响应中断，则无法停止），并且不再启动队列中的任务。 awaitTermination：阻塞直到线程池终止（返回true），或者超时（false），或者被中断（通常与shutdown方法一起使用）。 isTerminated：查看是否已经终止
4. Future管理任务生命周期，获取结束后的返回结构。线程池中任务的生命周期：创建，提交，开始，完成。
5. jvm只有在所有非守护线程结束后才会终止，由主线程创建的线程也是主线程，所以需要及时关闭线程池
6. 找出可利用的并行性：通过异步来实现性能提升最多提升50%，并且几个并行任务开销不能相差太大。
7. CompletionService：将Executor与BlockingQueue结合，将Callable任务一个一个提交，然后一个一个获取结果。类似可以处理任务并返回结果的阻塞队列。
8. 通过在future的get方法中传入时间参数，当超过此时间还没有完成任务时，将会抛出TimeoutException，捕获此异常进行超时处理（此时可以使用future取消任务或者重新开始）。

### 线程池：取消与关闭

1. 自定义线程取消：可以设置一个volatile标志，为true时退出循环，但是当线程阻塞时，此标志不起作用。
2. 线程中断（通常，中断是实现取消的最合理方式）：interrupt（设置中断标志，线程并不会立刻中断，而是会等到下一个合适的时刻中断），isInterrupted，interrupted（重置中断标志）。
3. 线程阻塞时发生中断，会提前返回，**清除中断状态**，抛出InterruptedException。中断并不能直接终止另一个线程，而需要被中断的线程自己处理中断。  如果被中断的线程正在阻塞，则会抛出一个异常。抛出异常后面的代码都不会继续执行，但如果这是一个循环，则抛出异常后直接开始下一个循环。如果被中断的线程不会阻塞，也没有检测中断的机制，则中断对他无影响（只是中断状态变为了true）  future.cancel(true)：参数的作用：为true时，会发出中断请求，为false时，如果线程正在运行则会允许线程继续执行。
4. 中断编程原则：1.除非知道线程的中断策略，否则不应该中断它（不要随意中断线程，除非使用future.cancel()） 2.任务代码不该猜测中断对执行线程的含义（捕获到InterruptedException时，应该合理的处理，即传递或恢复）
5. 处理中断：抛出或恢复而不应该忽视该中断，所有在catch块中，要么用throw抛出中断异常，要么调用interrupt方法把中断状态码置为true
6. 检测到中断时，在取消过程中可能涉及除了中断状态之外的其他状态，如当线程池的工作线程检测到中断时，他会检查线程池是否正在关闭，如果是则会执行一些清理工作，否则他可能创建一个新的线程将线程池恢复到合理规模。
7. 处理不可响应中断的阻塞：io包中的Socket I/O（write与read不会响应中断，但通过关闭Socket，可以使得阻塞方法抛出SocketException），io包中的同步I/O，Selector的异步I/O，获取某个锁（lock锁中的lockInterruptibly方法：此方法获取锁时可以响应中断）。
8. 关闭生产者消费者服务：使用毒丸对象，把毒丸放入队列，消费者得到此对象时立刻停止。当多个消费者生产者时，检测结果哦累计到N时停止。
9. 关闭只执行一次的方法：在方法中创建线程池并在方法最后关闭线程池。
10. shutdownNow的局限性：他会返回所有已经提交但尚未开始的任务，那么如何记录那些正在运行的任务呢？可以扩展AbstractExecutorService，实现execute方法，每当一个任务退出运行（任务被中断则会退出运行），都可以判断此时线程池是否已被shutdown且线程是否被中断，如果是则加入队列（可能会存在已经运行完成的任务被加入队列）。
11. RuntimeException（未检查异常）的处理：要明确，任何代码都可能抛出此异常，多线程环境中，抛出此异常只是在控制台打印，我们很可能注意不到。下面两种互补使用。1.手动捕获，如在线程池中，每次调用线程的run方法时，都会声明一个Throwable异常，用来捕获run方法可能抛出的异常。只有通过execute提交的任务，才能将异常给异常处理器，通过submit提交的任务，所有异常都会被认为是返回的一部分，可以通过future捕获。 2.每一个线程都有UncaughtExceptionHandler接口（线程发生未捕获异常而退出时，jvm会报告给异常处理器），默认的异常处理器是把报错信息输出到控制台，我们可以实现这个接口并通过set方法设置给某个线程（可以实现ThreadFactory，给线程池中所有线程设置handler），实现把报错信息输出到日志。 可以实现afterExecute方法实现异常后的一些操作。
12. jvm关闭：正常关闭（所有非守护线程结束或System.exit()）或强行关闭（Runtime.halt或杀死进程）。
13. 关闭钩子（通过Runtime.addShutDownHook()注册）：正常关闭jvm时，所有关闭钩子将与其他线程并行执行。关闭钩子执行结束后将会调用未被调用的finalize()方法，之后关闭jvm。关闭钩子要确保线程安全，所有服务使用同一个钩子，在关闭钩子中执行关闭操作。
14. 守护线程：当一个程序退出时，jvm会检查其他正在运行的线程，如果这些线程都是守护线程，那么jvm会正常退出。当jvm被停止时，所有仍然存在的守护线程将被抛弃。

### 线程池：使用

1. 线程池使用需要注意：依赖性任务，threadlocal任务，线程饥饿死锁（有界线程池中，一个正在线程池运行的线程把另一个线程加入线程池，并等待此任务结果，会形成死锁），运行时间较长的任务。
2. 设置线程池大小：根据cpu周期，内存，数据库，套接字等资源。可以通过Runtime.getRuntime().availableProcessors()获得cpu数目。
3. 通过自定义ThreadPoolExecutor参数自定义线程池。ThreadPoolExecutor创建后还可以调用Setter更新参数，使用Executors创建的线程池可以向上转型为ThreadPoolExecutor再设置参数。也可以使用Executors中的unconfigurableExecutorService方法返回不可配置的线程池，避免后面有人随意配置线程池。
4. 饱和策略：Abort（默认，抛出异常） Discard（抛弃） DiscardOldest（抛弃最旧的） Caller-Runs（调用者运行，在提交任务的线程中执行该任务，如主线程。此时主线程不会接受新的请求，请求就会保存在TCP层，TCP队列也满了时才抛弃请求）
5. 当工作队列满了时，并没有饱和策略，上面的策略是线程池满了的策略。这时可以自己封装线程池与Semaphore来控制线程的提交。
6. 线程工厂：ThreadFactory接口，只有一个newThread方法，可以指定线程名字，设置错误handler，维护线程池的统计信息等。
7. 扩展ThreadPoolExecutor：beforeExecute，afterExecute，terminated方法。无论任务是从run中正常返回，还是抛出一个异常，afterExecute都会被调用，如果beforeExecute抛出一个RuntimeException，那么run方法与after方法都不会被执行。before与after方法都是跟随线程执行，有几个线程执行就会执行多少次，而terminated只会执行一次，terminated都是交由最后一个线程去做，所以线程池的运行时间就是最后一个结束的线程的运行时间。
8. 递归任务并行化：每一个递归方法中运算的部分并行，调用下一个递归则串行（适用于各个迭代之间彼此独立，并且每个迭代操作计算量比管理一个新线程带来的开销更多，深度优先遍历）。 或者计算与迭代都是并行，一个迭代会得出多个迭代，这多个迭代可以并行。（适用于迭代的执行顺序不会影响结果，例如广度优先遍历）
9. 携带结果的闭锁：在广度优先并行递归搜索结果时，主线程怎么得到这个结果呢？可以用CountDownLatch封装一个类，当取结果时调用await方法阻塞，找到结果时调用counDown方法并写入结果。找到结果后，主线程即可关闭线程池了。如果没有结果呢？我们可以加入一个原子变量，计算线程池中task数量，为0时则设置结果。当想设置等待时间时，可以用latch的await方法设置等待时限。

### 避免活跃性危险

1. 死锁：1.锁顺序死锁：要获取两个锁时，因为获取顺序不一样，两个线程持有互相需要的锁。可以通过锁获取顺序一致解决。 2.动态的锁顺序死锁：虽然代码中获取锁顺序一样，但因为传入的两个拥有锁的参数顺序调用时反了，造成死锁。通过传入参数的哈希码/唯一id解决，永远获取哈希码小的锁，当哈希码相同时，可以设置一个额外的锁，获取到了额外的锁才能获取传入的两个参数的锁。（哈希相同的概率非常低，所以并不影响效率）3.在协作对象之间死锁：如A类的所有方法都需要A锁，B类的所有方法都需要B锁，A有些方法会调用B的方法，B的有些方法会调用A的方法。只查看代码不容易发现的死锁。4.资源死锁：嵌套获取资源导致死锁。
2. 开放调用：调用某方法时不需要持有锁（并不意味着方法中不使用锁）。通过缩小锁的范围，使得方法调用时不会发生锁获取嵌套的情形（即每次获取一个锁）。
3. 死锁避免：1.每次只获取一个锁。 2.当需要多个锁时，规定锁获取顺序。3.使用定时锁，随机时间后尝试。
4. 饥饿：现场由于无法获取他所需要的资源而不能继续执行时。如线程优先级使用不当导致cpu时钟周期饥饿，无限循环使用某个资源导致其他线程无法获取。java中有10等级优先级，jvm将他们映射到操作系统的调度优先级，不同操作系统优先级不同，所以映射的级数也不同，会导致程序的行为与平台相关。所以不要更改线程优先级。
5. 活锁：如事务处理中的活锁，事务处理失败后返回到事务队列，然后又从事务队列取事务执行，又取到这个事务，又失败，一直循环。当多个相互协作的线程都对彼此进行响应从而修改各自的状态，并使得任何一个线程都无法继续执行时，就发生了活锁。解决：引入随机性。

### 性能与可伸缩性

1. 性能：运行速度：多快（服务时间，延迟时间等） 处理能力：多少（吞吐率，可伸缩性等）。
2. 可伸缩性：增加计算机资源时，程序的处理能力能相应提升。（串行操作过多不利于可伸缩性）
3. amdahl定律：在增加计算资源的情况下，程序在理论上能实现的最高加速比（加速比为顺序处理时间/串行处理时间）。speedup=1/(F+(1-F)/N) N为处理器个数，F为串行部分比率。串行部分越小，处理器越多，加速比越大。所有程序都存在串行部分，如在并行代码中使用一些同步容器，日志记录。
4. 线程引入的开销：1.上下文切换，2.内存同步（如syn与volatile的内存栅栏：刷新缓存，刷新硬件的写缓冲，停止执行管道，禁止指令重排序。有竞争的同步对性能有很大影响，无竞争几乎无影响：如syn优化：锁消除，锁粗化（连续的多个加锁操作变为一个））3.阻塞（自旋或挂起权衡）
5. 减少锁竞争：锁竞争会降低可伸缩性与性能。锁的请求频率（减小锁粒度）与锁的持有时间（缩小锁的范围，快进快出）将影响锁发送竞争的可能性，二者的乘积与锁竞争发生的几率成正比。
6. 减小锁的请求频率并不是说总的程序的所有锁的请求频率，而是同一个锁的请求频率，如果同一个锁有很多线程请求，那么锁的请求频率很高，如果有很多锁，但是都只有一个线程请求，则请求频率很小。读写锁也能降低锁的请求频率。
7. 减小锁粒度：1.把一个锁分解为多个锁，如在一个类中存在多个变量，如果这些变量相互独立的话，可以用多个锁保护。2.分段锁，如ConcurrentHashmap，劣势：需要给整个容器加锁时开销更大。
8. 热点域：类中的多个方法都需要访问的域，如hashmap中的size。但是访问size又增大了锁请求频率（多个方法都要请求size），为了解决这个问题，ConcurrentHashmap中将对每个分段维护了一个独立计数，并将每个分段中的元素数量相加得出总值，通过分段锁来维护这个值。没有不变性条件时可以用原子变量来维护热点域。
9. 读写锁也能降低锁的请求频率。
10. 减少上下文切换开销（运行与阻塞就是一次上下文切换）：如日志等需要频繁调用I/O流以及获取输出流锁的操作会导致频繁阻塞，这时可以将I/O操作放入消息队列中（从队列中获取请求的阻塞频率肯定小于原来每次获取输出流的锁的频率），把日志操作独立成一个单独线程，这样可以让原先需要记录日志的线程不阻塞，并且减少输出流锁的竞争。

### 测试

1. 并发测试：安全性测试（避免测试程序对被测程序造成影响）与活跃性测试（如何/多久判断线程发生故障？）。
2. 测试阻塞：等到线程阻塞后，给线程一个中断信号，然后测试线程是否还是活着的。注意，Thread.getState()并不能准确判断线程是否阻塞，不要使用。
3. 安全性测试：单生产者消费者：使用一个对顺序敏感的校验和计算函数来计算所有出入列元素，并出与入比较是否相等。多生产者消费者：对出入列顺序不敏感的校验和函数。 确保编译器不会对结果进行预测，所以使用随机数（使用非线程安全的伪随机函数）。
4. 资源测试：使用堆分析工具等，测试堆中无用对象/文件句柄/套接字/数据库连接等有限资源是否释放。
5. 使用回调测试：如线程池中的Runnable与ThreadFactory

### lock锁

1. Lock锁优势：提供中断获取锁方法，定时获取锁方法（tryLock同样能响应中断），提供公平锁（性能下降很多）。劣势：需要手动释放，更危险。 性能从java6之后就差不多了，并且syn基于jvm之后还能优化，而lock是基于代码优化可能性不大，所以优先选择syn，除非必须使用lock。
2. 读写锁（ReentrantReadWriteLock）：实现了ReadWriteLock接口，内部有两个Lock锁（read and write），在公平锁中，一个线程获取了读锁，下一个线程请求写锁，则后面的读线程必须等待写锁释放。在非公平锁中，写线程可以降级为读线程，读线程升级为写线程是不可以的。（读锁通过AQS的共享锁实现，当锁可用时，CLH队列的头部线程请求写锁，则此线程会得到锁，如果是读锁，则从头结点开始直到第一个写结点之前的读线程都能获得读锁）

### 构建自定义同步工具

1. 条件队列：每个对象都可以作为一个锁，每个对象都可以作为一个条件队列，wait，nodify，nodifyAll就构成了内部条件队列的API，对象的内置锁与其内部条件队列是相互关联的。wait方法会自动释放锁，并请求操作系统挂起当前线程，当被挂起的线程醒来时，它将在返回之前重新获取锁。
2. 条件谓词：使某个操作成为状态依赖操作的前提条件，如对take方法来说，条件谓词即是“缓存不为空”。
3. 为什么不使用notify通知呢？可能随机唤醒的线程还是不满足条件，被唤醒后又需要阻塞。而使用notifyAll通知所有又太过低效。这时可以使用条件通知，并不是每次操作都通知，而是在条件满足时才通知，比如put线程，被唤醒后，如果此时为空，那么插入后立刻唤醒所有。
4. 阀门实现：条件谓词为什么需要generation而不只是open呢？当阀门打开后快速关闭，显然，这时应该让所有等待的线程通过，但是因为打开关闭操作太快了（我们不能规定操作阀门的类是怎么实现的），其他线程还没有反应过来，很可能有些线程一直通过不了。加入generation后，只要打开过一次（方法中的g与对象中的g就不会相同），线程无论醒来多慢，都能通过阀门。
5. Condition显式条件队列：支持单个锁上对应多个等待线程集（相当于多个队列），支持公平的队列操作，支持可中断，基于时限的等待。
6. AQS：加锁过程：循环判断当前状态是否允许获取操作，允许则更新同步器状态，如果位于队列中则退出队列，然后返回，如果不允许获取操作，则阻塞或返回失败。 释放过程：更新同步器状态，判断新的状态是否允许队列中某个被阻塞的线程获取成功，是则解除队列中的一个或多个线程的阻塞状态。
7. AQS的aquire与release方法会调用我们重写的tryaquire与tryrelease方法来判断某个操作能否执行（获取/释放）。客户端则调用aquire，release等方法。try方法中，tryaquire返回boolean类型值，tryaquireshared返回int类型。
8. state：lock代表重入次数，semaphore表示许可证数量，futuretask表示任务状态（尚未开始，运行，已完成，已取消）。使用getstate，setstate，compareandsetstate方法来操作此状态。

### 原子变量与非阻塞同步机制

1. 锁的劣势：挂起的线程恢复运行时，必须等待其他线程执行完成他们的时间片之后才能被执行。在挂起到再次执行存在很大开销与较长时间中断。优先级反转：持有锁的优先级较低，被阻塞的线程优先级较高，优先级高的线程由于需要锁，会等待低优先级先执行释放锁，导致高优先级降至低优先级。对于细粒度如递增的操作加锁开销太大。
2. 几乎所有的现代处理器都包含了某种形式的原子读-改-写指令，如比较并交换（compareandswap）。CAS：首先从V中读取A，并根据A计算值B，然后通过CAS以原子方式将V中的值变为由A变为B。（保证了原子性与可见性）
3. 在加锁时至少需要一次CAS，加锁时需要遍历jvm中一条非常复杂的代码路径，并可能导致线程挂起与上下文切换，所以性能低于CAS操作，CAS的缺点是调用者需要处理竞争问题，而加锁会自动阻塞，不需要人为处理。在竞争高度激烈时，加锁性能更好一些，而在竞争适中时，原子类更好，因为高度竞争时，加锁能通过阻塞减少竞争（但是原子变量也能通过代码编写减少竞争，并且高度竞争是本地只做非常少的操作，大多在竞争锁，这在现实中是不可能的）。
4. 原子变量类底层都使用了CAS操作。包括4中原子类：标量类（AtomicInteger，AtomicLong等，short/byte/float/double等可以转换为int来实现原子类），更新器类，数组类（为数组每个元素提供volatile类型的访问，volatile修饰引用只是保证了引用的可见性），复合变量类。原子类是可修改的，不宜做hashmap的key值。
5. 对多个有一致性条件的变量修改时，我们可以把多个变量放到某个类中，然后通过AtomicReference原子地修改此类对象中的各个变量。
6. 非阻塞算法：一个线程的失败或挂起不会导致其他线程也失败或挂起（加锁是阻塞算法，某个算法由于内存页缺页，阻塞I/O等操作而导致推迟执行时，其他需求锁的线程都不能继续执行下去）。无锁算法：在算法的每个步骤都存在某个线程能够执行下去。仅将CAS用于协调线程之间的操作，那么他即使非阻塞也是无锁的。
7. 非阻塞的栈（push：新节点的next指向头结点，比较并交换头结点与新节点），非阻塞的链表（插入：取得尾结点与他的next，判断他的next是否为null，不是则说明别的线正在插入，我们可以帮助他插入，比较并交换尾结点与next，是则说明没有其他线程正在插入，则比较并更新尾结点的next与新节点，比较并更新尾结点与他的next）
8. ABA问题：版本号与时间戳。

### Java内存模型

1. java内存模型：解决的问题：一个线程写入的变量，在什么条件下其他线程能够看到？
2. 每个处理器都有自己的缓存，并且定期与主存同步，不同的处理器架构允许不同级别的缓存一致性。底层的内存模型将告诉应用程序可以从内存系统中获得怎样的保证，此外还定义了一些特殊的指令（即内存栅栏），当需要共享数据时，这些指令就能实现额外的存储协调保证。jvm通过在适当的位置插入内存栅栏来屏蔽jmm与底层平台之间的差异。
3. happens-before：jmm为变量的读写，加锁与释放，线程的启动与合并操作定义的偏序关系。操作a与操作b之间满足此关系，则b的线程能看到a的结果，否则jvm可以对他们任意重排序。
4. 规则：监视器锁：同一个锁解锁在加锁之前。 volatile变量：写操作在读操作之前。 中断：发出中断请求在检测到中断之前。等等  类库中的happens-before规则：放入线程安全容器在取出之前，释放semaphore许可证在获取之前等等。
5. happens-before比安全发布提供了更强的可见性与顺序保证，如在创建并发布某个对象时，安全发布可以保证此对象的可见性，但无法保证其他变量的可见性，而happens-before能保证此操作之前所有操作的可见性。安全发布是程序代码级别的，而happens-before是内存访问级别的。
6. 双重校验锁：当对象没有使用volatile修饰时，可能访问到一个没有构造完成的对象（因为虽然提供了加锁，但是创建只会让一个线程加锁，其他线程并没有加锁，所以并不满足happens-before）。推荐使用内部类单例模式。
7. 初始化安全性：对于被正确构造的对象，所有线程都能看到由构造函数为对象各个final域设置的正确值，而且，对于可以通过被正确构造对象中某个final域到达的任意变量（如容器hashmap中的内容），将同样可见。对于含有final域的对象，可以防止对对象的初始化引用被重排序到构造方法完成之前。（final只能保证构造开始到完成时的可见性，完成后的可见性并不能保证）