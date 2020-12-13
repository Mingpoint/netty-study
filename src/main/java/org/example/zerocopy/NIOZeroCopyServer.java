package org.example.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @ProjectName: netty-study
 * @Package: org.example.zerocopy
 * @ClassName: NIOZeroCopy
 * @Author: lee.min
 * @Description:
 * @Date: 2020/12/13 2:07 下午
 */
public class NIOZeroCopyServer {
    public static void main(String[] args) throws IOException {
        try {

            InetSocketAddress inetSocketAddress = new InetSocketAddress(7003);
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(inetSocketAddress);

            ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

            while (true) {
                SocketChannel accept = serverSocketChannel.accept();
                int readCount = 0;
                while (-1 != readCount) {
                    readCount = accept.read(byteBuffer);
                }
                byteBuffer.rewind();//倒带，position=0,mark废弃
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
