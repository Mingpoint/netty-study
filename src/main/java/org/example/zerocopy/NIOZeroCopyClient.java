package org.example.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @ProjectName: netty-study
 * @Package: org.example.zerocopy
 * @ClassName: NIOZeroCopyClient
 * @Author: lee.min
 * @Description:
 * @Date: 2020/12/13 2:13 下午
 */
public class NIOZeroCopyClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",7003));
        String fileName = "/Users/limin/code/yunten/netty-study/mqtt-cn.pdf";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        long start = System.currentTimeMillis();
        long size = fileChannel.size();
        System.out.println(size);
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发送字节数:"+transferCount);
        System.out.println("耗时:"+(System.currentTimeMillis()-start));
        fileChannel.close();
        socketChannel.close();
    }
}
