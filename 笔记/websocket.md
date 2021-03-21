<<<<<<< HEAD
1. websocket作用：允许服务器端主动向客户端发送数据。在WebSocket API中，浏览器和服务器只需要完成一次握手，两者之间就直接可以创建持久性的连接，并进行双向数据传输。为什么不用客户端轮询呢？轮询占用的宽带资源比较大。
2. 握手：首先，WebSocket连接必须由浏览器发起，因为请求协议是一个标准的HTTP请求格式（请求网址的http换成ws（ws类似http，wss类似https），并且用upgrade关键字指定了将被转换为websocket连接，还指定了websocket的版本）。服务器返回一个状态码为101的响应，表示本次连接的HTTP协议即将被更改，更改后的协议就是Upgrade: websocket指定的WebSocket协议。这个过程称为握手。握手后就可以双向通信了，通信的消息有两种：文本和二进制数据，通常我们可以发送json格式的文本。
3. 为什么WebSocket连接可以实现全双工通信而HTTP连接不行呢？实际上HTTP协议是建立在TCP协议之上的，TCP协议本身就实现了全双工通信，但是HTTP协议的请求－应答机制限制了全双工通信。WebSocket连接建立以后，其实只是简单规定了一下：接下来，咱们通信就不使用HTTP协议了，直接互相发数据吧。
4. 前端使用：new一个WebSocket对象后，绑定onopen、onmessage、onclose事件。属性：readState（只读属性，表示连接状态，如0表示连接未建立，1表示已建立）。bufferedAmount：已被 send() 放入正在队列中等待传输，但是还没有发出的 UTF-8 文本字节数。方法：send(),close()；
5. 后端使用：引入starter，创建一个configuration然后new一个ServerEndpointExporter（注意，如果不使用springboot自带的tomcat，即使用war包，则不用注入，要不然会报错）。然后创建一个WebSocket类（类名自定），加上Component与ServerEndpoint（与Controller类似，注明访问ws的网址）注解，此类中要有Session对象，静态的ConcurrentHashmap保存每次连接的WebSocket，我们每个连接都可以通过从map中获取WebSocket中的session来发送信息。编写@OnOpen，@OnMessage，@OnClose，@OnError等注解对应的事件，编写私有的发送信息类，发送信息语句：this.session.getBasicRemote().sendText(message);
=======
1. websocket作用：允许服务器端主动向客户端发送数据。在WebSocket API中，浏览器和服务器只需要完成一次握手，两者之间就直接可以创建持久性的连接，并进行双向数据传输。为什么不用客户端轮询呢？轮询占用的宽带资源比较大。
2. 握手：首先，WebSocket连接必须由浏览器发起，因为请求协议是一个标准的HTTP请求格式（请求网址的http换成ws（ws类似http，wss类似https），并且用upgrade关键字指定了将被转换为websocket连接，还指定了websocket的版本）。服务器返回一个状态码为101的响应，表示本次连接的HTTP协议即将被更改，更改后的协议就是Upgrade: websocket指定的WebSocket协议。这个过程称为握手。握手后就可以双向通信了，通信的消息有两种：文本和二进制数据，通常我们可以发送json格式的文本。
3. 为什么WebSocket连接可以实现全双工通信而HTTP连接不行呢？实际上HTTP协议是建立在TCP协议之上的，TCP协议本身就实现了全双工通信，但是HTTP协议的请求－应答机制限制了全双工通信。WebSocket连接建立以后，其实只是简单规定了一下：接下来，咱们通信就不使用HTTP协议了，直接互相发数据吧。
4. 前端使用：new一个WebSocket对象后，绑定onopen、onmessage、onclose事件。属性：readState（只读属性，表示连接状态，如0表示连接未建立，1表示已建立）。bufferedAmount：已被 send() 放入正在队列中等待传输，但是还没有发出的 UTF-8 文本字节数。方法：send(),close()；
5. 后端使用：引入starter，创建一个configuration然后new一个ServerEndpointExporter（注意，如果不使用springboot自带的tomcat，即使用war包，则不用注入，要不然会报错）。然后创建一个WebSocket类（类名自定），加上Component与ServerEndpoint（与Controller类似，注明访问ws的网址）注解，此类中要有Session对象，静态的ConcurrentHashmap保存每次连接的WebSocket，我们每个连接都可以通过从map中获取WebSocket中的session来发送信息。编写@OnOpen，@OnMessage，@OnClose，@OnError等注解对应的事件，编写私有的发送信息类，发送信息语句：this.session.getBasicRemote().sendText(message);
>>>>>>> e8025749749535851e740978543ff79fdeab5a23
6. 内部：后端WsSession（实现Session接口）对象返回一个WsRemoteEndpointBasic（实现了RemoteEndpoint接口中的Basic接口）对象，此对象调用它的父类WsRemoteEndpointBase中的属性WsRemoteEndpointImplBase对象的sendString()方法。