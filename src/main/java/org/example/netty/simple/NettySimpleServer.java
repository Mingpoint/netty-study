package org.example.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @ProjectName: netty-study
 * @Package: org.example.netty.simple
 * @ClassName: NettySimpleServer
 * @Author: lee.min
 * @Description:
 * @Date: 2020/12/13 4:45 下午
 */
public class NettySimpleServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup work = new NioEventLoopGroup(4);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {


            serverBootstrap
                    .group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new NettyServerSimpleHandler());
                        }
                    });
            System.out.println("-------------------------------");
            ChannelFuture sync = serverBootstrap.bind(6669).sync();
            sync.addListener(new GenericFutureListener() {
                @Override
                public void operationComplete(Future future) throws Exception {
                    boolean success = future.isSuccess();
                    System.out.println(success);
                }
            });
            sync.channel().closeFuture().sync();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
        }
    }
}
