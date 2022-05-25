package cn.peng.studygodpath.java8.io;

// 同步阻塞的BIO、 BIO是一个连接一个线程。 客户端有连接请求时服务器端就需要启动一个线程进行处理，如果这个连接不做任何事情会造成不必要的线程开销，当然可以通过线程池机制改善
// 同步非阻塞的NIO、 NIO是一个请求一个线程。即客户端发送的连接请求都会注册到多路复用器上，多路复用器轮询到连接有I/O请求时才启动一个线程进行处理。
// 异步非阻塞的AIO。 AIO是一个有效请求一个线程。 客户端的I/O请求都是由OS先完成了再通知服务器应用去启动线程进行处理，

// SelectionKey.OP_CONNECT  selectionKey.isConnectable()    监听客户端连接事件
// SelectionKey.OP_ACCEPT   selectionKey.isAcceptable()     监听接受事件
// SelectionKey.OP_WRITE    selectionKey.isWritable()       监听写事件
// SelectionKey.OP_READ     selectionKey.isReadable()       监听读事件