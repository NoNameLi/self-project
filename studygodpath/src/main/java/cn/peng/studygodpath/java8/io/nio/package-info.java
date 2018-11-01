/**
 * Created by remote on 2018/1/12.
 * nio
 * Channel(通道) 《==数据==》 Buffer
 *
 * channel实现：(主要的不是全部的)
 *  FileChannel (读写文件中)
 *  DatagramChannel （UDP 读写网络数据）
 *  SocketChannel (TCP 读写网络数据 对应 Socker)
 *  ServerSocketChannel （可以监听新的TCP连接，对新的连接都会创建一个SocketChannel 对应 ServerSocket）
 * Buffer 实现：
 *  ByteBuffer
 *  CharBuffer
 *  DoubleBuffer
 *  FloatBuffer
 *  IntBuffer
 *  LongBuffer
 *  ShortBuffer
 *  MappedByteBuffer
 * Selector:
 *  相等于channel的管理者 通道都需要想某个selector 注册感兴趣的事件（OP_READ OP_WRITE OP_CONNECT OP_ACCEPT 这几个事件的意义？）
 *
 */
package cn.peng.studygodpath.java8.io.nio;