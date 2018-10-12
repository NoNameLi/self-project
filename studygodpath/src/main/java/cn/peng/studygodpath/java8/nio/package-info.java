/**
 * Created by remote on 2018/1/12.
 * nio
 * Channel(通道) 《==数据==》 Buffer
 *
 * channel实现：(主要的不是全部的)
 *  FileChannel (读写文件中)
 *  DatagramChannel （UDP 读写网络数据）
 *  SocketChannel (TCP 读写网络数据)
 *  ServerSocketChannel （可以监听新的TCP连接，对新的连接都会创建一个SocketChannel）
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
 */
package cn.peng.studygodpath.java8.nio;