package cn.peng.chat.common.handler;

import cn.peng.chat.common.data.Package;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.ByteToMessageCodec;

public abstract class AbstractPackageCodec<T extends Package> extends ByteToMessageCodec<T> {

    public boolean findPackageHead(ByteBuf in) {
        while (true) {
            if (in.readableBytes() >= 4) {
                if (in.readableBytes() > 20480) {
                    in.skipBytes(in.readableBytes());
                    return false;
                }
                in.markReaderIndex();
                if (in.readInt() == Package.packageHead) {
                    return true;
                } else {
                    in.resetReaderIndex();
                    in.readByte();
                }
            } else {
                return false;
            }
        }
    }
}
